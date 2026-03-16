import { useState, useEffect, useRef  } from "react";

export default function App() {
  const [messages, setMessages] = useState([
    { role: "agent", content: "Hello, I'm your claims assistant. How can I help today?" }
  ]);

  const [input, setInput] = useState("");
  const [loading, setLoading] = useState(false);
  const [conversationId, setConversationId] = useState(crypto.randomUUID())
  const messagesEndRef = useRef(null);

  useEffect(() => {
    messagesEndRef.current?.scrollIntoView({ behavior: "smooth" });
  }, [messages]);

  async function sendMessage() {
    if (!input.trim()) return;

    const userMessage = { role: "customer", content: input };
    setMessages(m => [...m, userMessage]);
    setInput("");
    setLoading(true);

    try {
      const res = await fetch("/api/chat", {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify({ message: input, conversationId: conversationId })
      });

      const data = await res.json();

      setMessages(m => [
        ...m,
        { role: "agent", content: data.message }
      ]);
    } catch (err) {
      setMessages(m => [
        ...m,
        { role: "agent", content: "Server error contacting the agent." }
      ]);
    }

    setLoading(false);
  }

  function handleKey(e) {
    if (e.key === "Enter") sendMessage();
  }

  return (
      <div style={{
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        height: "100vh",
        background: "#f3f4f6",
        fontFamily: "Arial"
      }}>
        <div style={{
          width: "500px",
          height: "600px",
          background: "white",
          borderRadius: "5px",
          boxShadow: "0 10px 25px rgba(0,0,0,0.1)",
          display: "flex",
          flexDirection: "column",
          padding: "16px"
        }}>

          <div style={{fontWeight:"bold", marginBottom:"10px", backgroundColor:"#0033a0", color:"white", padding: "6px"}}>
            Claims Assistant
          </div>

          <div style={{
            flex:1,
            overflowY:"auto",
            display:"flex",
            flexDirection:"column",
            gap:"10px"
          }}>
            {messages.map((m,i)=>(
                <div key={i} style={{
                  alignSelf: m.role==="customer" ? "flex-end" : "flex-start",
                  background: m.role==="customer" ? "#2563eb" : "#e5e7eb",
                  textAlign: "left",
                  color: m.role==="customer" ? "white" : "black",
                  padding:"10px 14px",
                  borderRadius:"18px",
                  maxWidth:"70%"
                }}>
                  {m.content}
                </div>

            ))}
            <div ref={messagesEndRef} />
            {loading && (
                <div style={{fontSize:"12px", color:"#666"}}>
                  Agent is thinking...
                </div>
            )}
          </div>

          <div style={{display:"flex", gap:"8px", marginTop:"10px"}}>
            <input
                style={{
                  flex:1,
                  padding:"10px",
                  borderRadius:"8px",
                  border:"1px solid #ccc"
                }}
                placeholder="Type your message..."
                value={input}
                onChange={e=>setInput(e.target.value)}
                onKeyDown={handleKey}
            />

            <button
                onClick={sendMessage}
                style={{
                  padding:"10px 16px",
                  background:"#2563eb",
                  color:"white",
                  border:"none",
                  borderRadius:"8px",
                  cursor:"pointer",
                }}
            >
              Send
            </button>
          </div>

        </div>
      </div>
  );
}