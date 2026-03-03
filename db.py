import psycopg2
from psycopg2.extras import execute_batch
from faker import Faker
import random

# -------------------------
# DATABASE CONNECTION
# -------------------------
conn = psycopg2.connect(
    dbname="infoman_activity",   # ✅ fixed (underscore)
    user="postgres",
    password="1234",
    host="localhost",
    port="5432"
)

conn.autocommit = False
cursor = conn.cursor()
fake = Faker()

BATCH_SIZE = 10000

# -------------------------
# CREATE TABLES
# -------------------------
cursor.execute("""
DROP TABLE IF EXISTS order_items;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS categories;

CREATE TABLE categories (
    id INTEGER PRIMARY KEY,
    name TEXT
);

CREATE TABLE users (
    id INTEGER PRIMARY KEY,
    name TEXT,
    email TEXT,
    created_at DATE
);

CREATE TABLE products (
    id INTEGER PRIMARY KEY,
    name TEXT,
    price NUMERIC(10,2),
    category_id INTEGER REFERENCES categories(id),
    created_at DATE
);

CREATE TABLE orders (
    id INTEGER PRIMARY KEY,
    user_id INTEGER REFERENCES users(id),
    total NUMERIC(12,2),
    created_at DATE
);

CREATE TABLE order_items (
    id INTEGER PRIMARY KEY,
    order_id INTEGER REFERENCES orders(id),
    product_id INTEGER REFERENCES products(id),
    quantity INTEGER,
    price NUMERIC(10,2)
);
""")

conn.commit()

# -------------------------
# HELPER FUNCTION
# -------------------------
def batch_insert(query, data):
    execute_batch(cursor, query, data, page_size=BATCH_SIZE)
    conn.commit()

# -------------------------
# INSERT CATEGORIES (1,000)
# -------------------------
for batch_start in range(1, 1001, BATCH_SIZE):
    batch = []
    for i in range(batch_start, min(batch_start + BATCH_SIZE, 1001)):
        batch.append((i, fake.word().capitalize()))
    batch_insert("INSERT INTO categories VALUES (%s,%s)", batch)

# -------------------------
# INSERT USERS (100,000)
# -------------------------
for batch_start in range(1, 100001, BATCH_SIZE):
    batch = []
    for i in range(batch_start, min(batch_start + BATCH_SIZE, 100001)):
        batch.append((
            i,
            fake.name(),
            fake.email(),
            fake.date_this_decade()
        ))
    batch_insert("INSERT INTO users VALUES (%s,%s,%s,%s)", batch)

# -------------------------
# INSERT PRODUCTS (1,000,000)
# -------------------------
for batch_start in range(1, 1000001, BATCH_SIZE):
    batch = []
    for i in range(batch_start, min(batch_start + BATCH_SIZE, 1000001)):
        batch.append((
            i,
            fake.word().capitalize(),
            round(random.uniform(5, 500), 2),
            random.randint(1, 1000),
            fake.date_this_decade()
        ))
    batch_insert("INSERT INTO products VALUES (%s,%s,%s,%s,%s)", batch)

# -------------------------
# INSERT ORDERS (500,000)
# -------------------------
for batch_start in range(1, 500001, BATCH_SIZE):
    batch = []
    for i in range(batch_start, min(batch_start + BATCH_SIZE, 500001)):
        batch.append((
            i,
            random.randint(1, 100000),
            0.0,
            fake.date_this_year()
        ))
    batch_insert("INSERT INTO orders VALUES (%s,%s,%s,%s)", batch)

# -------------------------
# INSERT ORDER ITEMS (1,000,000)
# -------------------------
order_totals = {}

for batch_start in range(1, 1000001, BATCH_SIZE):
    batch = []

    for i in range(batch_start, min(batch_start + BATCH_SIZE, 1000001)):
        order_id = random.randint(1, 500000)
        product_id = random.randint(1, 1000000)
        quantity = random.randint(1, 5)
        price = round(random.uniform(5, 500), 2)

        batch.append((
            i,
            order_id,
            product_id,
            quantity,
            price
        ))

        order_totals[order_id] = order_totals.get(order_id, 0) + (price * quantity)

    batch_insert(
        "INSERT INTO order_items VALUES (%s,%s,%s,%s,%s)",
        batch
    )

# -------------------------
# UPDATE ORDER TOTALS
# -------------------------
updates = [
    (round(total, 2), order_id)
    for order_id, total in order_totals.items()
]

execute_batch(
    cursor,
    "UPDATE orders SET total = %s WHERE id = %s",
    updates,
    page_size=BATCH_SIZE
)

conn.commit()

# -------------------------
# INDEXES (Performance)
# -------------------------
cursor.execute("CREATE INDEX idx_orders_user_id ON orders(user_id);")
cursor.execute("CREATE INDEX idx_products_category_id ON products(category_id);")
cursor.execute("CREATE INDEX idx_order_items_order_id ON order_items(order_id);")
cursor.execute("CREATE INDEX idx_order_items_product_id ON order_items(product_id);")

conn.commit()

cursor.close()
conn.close()

print("✅ PostgreSQL database 'infoman_activity' populated successfully.")