import { useEffect, useRef, useState } from "react";
import { Client } from "@stomp/@stompjs";
import SockJS from "sockjs-client";

export default function Chat() {
    const [name, setName] = useState("");
    const [joined, setJoined] = useState(false);

    const [connected, setConnected] = useState(false);
    const [messages, setMessages] = useState([]);

    const [input, setInput] = useState("");

    const clientRef = useRef(null);

    useEffect(() => {
        if (!joined || !name.trim()) return;

        const client = new Client({
            WebSocketFactory: () => new SockJS("http://localhost:9090/ws")

            onConnect: () => {
                setConnected(true);

                client.subcribe("/topic/channel1", (stompMessage) => {
                    const body = JSON.parse(stompMessage.body);
                    setMessages((prev) =>  [...prev, body]);
                });

                //Optional: announce join
                client.publish({
                    destination: "/app/chat",
                    body: JSON.stringify({
                        sender: name,
                        content: "joined the chat",
                        type: "JOIN"
                    }),
                });
            },
            onDisconnect: () => setConnected(false),
        });

        client.activate();
        clientRef.current = client;

        return () => client.deactivate();
    }, [joined, name]);

    const send = () => {
        if (!input.trim()) return;
        if (!clientRef.current?.connected) return;

        clientRef.current.publish({
            destination: "/app/chat",
            body: JSON.stringify({
                sender: name,
                content: input,
                type: "CHAT",
            }),
        });

        setInput("");
    };

    if (!joined) {
        return(
            <div style={{ maxWidth: 420 }}> <h3>Join Public Chat</h3>

            <input
                value={name}
            </div>
        )
        }
        )
    }
    )
}