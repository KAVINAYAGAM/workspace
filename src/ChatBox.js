import React, { useEffect, useRef, useState } from "react";
import { Prism as SyntaxHighlighter } from "react-syntax-highlighter";
import { tomorrow } from "react-syntax-highlighter/dist/esm/styles/prism";
import "./ChatBox.css";

export default function ChatApp() {
  const [messages, setMessages] = useState([
    { text: "Hi there! 👋 How can I help you today?", from: "bot", time: "09:41" }
  ]);
  const [input, setInput] = useState("");
  const [isTyping, setIsTyping] = useState(false);
  const [darkMode, setDarkMode] = useState(false);
  const chatEndRef = useRef(null);
  const copyTimeoutRef = useRef(null);
  const [copySuccess, setCopySuccess] = useState(false);

  useEffect(() => {
    chatEndRef.current?.scrollIntoView({ behavior: "smooth" });
  }, [messages, isTyping]);

  useEffect(() => {
    return () => clearTimeout(copyTimeoutRef.current);
  }, []);

  const handleSend = async () => {
    if (!input.trim()) return;

    const newMessage = {
      text: input,
      from: "user",
      time: new Date().toLocaleTimeString([], { hour: "2-digit", minute: "2-digit" })
    };
    setMessages((prev) => [...prev, newMessage]);
    setInput("");
    setIsTyping(true);

    try {
      const res = await fetch("http://localhost:8000/api/chat", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ session_id: "user123", message: input })
      });

      const data = await res.json();
      const botReply = data.response || "⚠️ No response";

      setMessages((prev) => [
        ...prev,
        {
          text: botReply,
          from: "bot",
          time: new Date().toLocaleTimeString([], { hour: "2-digit", minute: "2-digit" })
        }
      ]);
    } catch (err) {
      setMessages((prev) => [
        ...prev,
        {
          text: "⚠️ Error connecting to server.",
          from: "bot",
          time: new Date().toLocaleTimeString([], { hour: "2-digit", minute: "2-digit" })
        }
      ]);
    } finally {
      setIsTyping(false);
    }
  };

  const copyToClipboard = (code) => {
    navigator.clipboard.writeText(code);
    setCopySuccess(true);
    clearTimeout(copyTimeoutRef.current);
    copyTimeoutRef.current = setTimeout(() => setCopySuccess(false), 2000);
  };

  const renderMessage = (msg, index) => {
    const codeRegex = /``````/g;
    const parts = msg.text.split(codeRegex);

    const avatar = msg.from === "bot" ? "🤖" : "🙂";

    if (parts.length === 1) {
      return (
        <div className="message-bubble" key={index}>
          <div className="avatar">{avatar}</div>
          <div className="text-content">{msg.text}</div>
        </div>
      );
    }

    return (
      <div className="message-bubble code-support" key={index}>
        <div className="avatar">{avatar}</div>
        <div className="text-content">
          {parts.map((part, i) =>
            i % 2 === 1 ? (
              <div key={i} className="code-block">
                <SyntaxHighlighter language="javascript" style={tomorrow} showLineNumbers>
                  {part.trim()}
                </SyntaxHighlighter>
                <button
                  className="copy-btn"
                  onClick={() => copyToClipboard(part.trim())}
                  title="Copy code"
                  aria-label="Copy code to clipboard"
                >
                  📋
                </button>
              </div>
            ) : (
              part && <p key={i}>{part}</p>
            )
          )}
        </div>
      </div>
    );
  };

  const handleInputKeyDown = (e) => {
    if (e.key === "Enter" && !e.shiftKey) {
      e.preventDefault();
      handleSend();
    }
  };

  return (
    <div className={`chat-container ${darkMode ? "dark-mode" : ""}`}>
      <div className="chat-header">
        💬 Perplexity Chatbot
        <button
          className="dark-mode-toggle"
          onClick={() => setDarkMode(!darkMode)}
          aria-label="Toggle dark mode"
          title="Toggle Dark Mode"
        >
          {darkMode ? "🌞" : "🌙"}
        </button>
      </div>
      <div className="chat-body" role="log" aria-live="polite">
        {messages.map((msg, i) => (
          <div key={i} className={`chat-message ${msg.from}`}>
            {renderMessage(msg, i)}
            <div className="message-time" title={`Sent at ${msg.time}`}>
              {msg.time}
            </div>
          </div>
        ))}
        {isTyping && (
          <div className="chat-message bot">
            <div className="message-bubble typing">🤖 Bot is typing...</div>
          </div>
        )}
        <div ref={chatEndRef} />
      </div>
      <div className="chat-footer">
        <textarea
          rows={1}
          placeholder="Type a message..."
          value={input}
          onChange={(e) => setInput(e.target.value)}
          onKeyDown={handleInputKeyDown}
          className="message-input"
          aria-label="Chat message input"
        />
        <button onClick={handleSend} aria-label="Send message">
          ➤
        </button>
      </div>
      {copySuccess && <div className="copy-toast">✅ Code copied!</div>}
    </div>
  );
}
