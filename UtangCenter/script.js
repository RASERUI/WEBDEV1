function addUtang() {
    const name = document.getElementById("name").value;
    const utang = document.getElementById("utang").value;
  
    const lenders = document.getElementById("nakautangs");
  
    const newUtang = document.createElement("li");
    newUtang.textContent = name + " - " + utang;
  
    lenders.appendChild(newUtang);
  
    document.getElementById("name").value = '';
    document.getElementById("utang").value = '';
  }
  