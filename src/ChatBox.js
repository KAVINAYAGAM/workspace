import React, { useState } from "react";
import axios from "axios";

const backendUrl = "http://localhost:8000";

const ChatBox = () => {
  const [messages, setMessages] = useState([]);
  const [input, setInput] = useState("");
  const [fileContext, setFileContext] = useState("");

  const sendMessage = async () => {
    if (!input.trim()) return;

    setMessages((prev) => [...prev, { sender: "user", text: input }]);
    const userMessage = input;
    setInput("");

    try {
      const res = await axios.post(`${backendUrl}/api/chat`, {
        message: userMessage,
        context: fileContext
      });
      setMessages((prev) => [...prev, { sender: "bot", text: res.data.response }]);
    } catch (err) {
      setMessages((prev) => [...prev, { sender: "bot", text: "Error: Could not fetch response" }]);
    }
  };

  const handleUpload = async (e) => {
    const file = e.target.files[0];
    if (!file) return;
    const formData = new FormData();
    formData.append("file", file);

    try {
      const res = await axios.post(`${backendUrl}/api/upload`, formData, {
        headers: { "Content-Type": "multipart/form-data" },
      });
      setFileContext(res.data.text || "");
      alert("File uploaded and text extracted!");
    } catch (err) {
      alert("Error uploading file");
    }
  };

  return (
    <div style={{ maxWidth: "600px", margin: "0 auto", padding: "1rem" }}>
      <h2>Perplexity Chatbot</h2>

      <input type="file" onChange={handleUpload} style={{ marginBottom: "1rem" }} />

      <div style={{ height: "400px", overflowY: "auto", border: "1px solid #ccc", padding: "0.5rem" }}>
        {messages.map((msg, idx) => (
          <div key={idx} style={{ textAlign: msg.sender === "user" ? "right" : "left", margin: "0.5rem 0" }}>
            <span
              style={{
                display: "inline-block",
                padding: "0.5rem 1rem",
                borderRadius: "1rem",
                background: msg.sender === "user" ? "#DCF8C6" : "#ECECEC",
              }}
            >
              {msg.text}
            </span>
          </div>
        ))}
      </div>

      <div style={{ marginTop: "1rem" }}>
        <input
          type="text"
          value={input}
          onChange={(e) => setInput(e.target.value)}
          onKeyDown={(e) => e.key === "Enter" && sendMessage()}
          style={{ width: "80%", padding: "0.5rem" }}
        />
        <button onClick={sendMessage} style={{ padding: "0.5rem 1rem", marginLeft: "0.5rem" }}>
          Send
        </button>
      </div>
    </div>
  );
};

export default ChatBox;
