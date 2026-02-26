import { useState, useEffect } from 'react';
import { Client } from '@stomp/stompjs';
import SockJS from 'sockjs-client';

const WebSocketClient = () => {
  const [messages, setMessages] = useState([]);

  useEffect(() => {
    const client = new Client({
      webSocketFactory: () => new SockJS("http://localhost:9090/ws"), // use your Spring port
      reconnectDelay: 5000, // retry every 5 seconds
      debug: (str) => console.log(str),
      onConnect: () => {
        console.log("Connected to STOMP!");
        client.subscribe("/topic/channel1", (msg) => {
          const message = JSON.parse(msg.body);
          setMessages((prev) => [...prev, message]);
        });
      },
      onStompError: (frame) => {
        console.error("STOMP error:", frame);
      }
    });

    client.activate();

    return () => client.deactivate();
  }, []);

  return (
    <div>
      <h1>Server Messages</h1>
      {messages.map((m, i) => (
        <p key={i}>{m.sender}: {m.content}</p>
      ))}
    </div>
  );
};

export default WebSocketClient;