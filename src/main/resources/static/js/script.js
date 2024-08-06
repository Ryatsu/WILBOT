const chatbotToggler = document.querySelector(".chatbot-toggler");
const chatbotCloseBtn = document.querySelector(".close-btn");
const chatInput = document.querySelector(".chat-input textarea");
const sendBtn = document.querySelector("#send-btn");

chatbotToggler.addEventListener("click", () => document.body.classList.toggle("show-chatbot"));
chatbotCloseBtn.addEventListener("click", () => document.body.classList.remove("show-chatbot"));

sendBtn.addEventListener("click", () => {
    const messageContent = chatInput.value.trim();
    if (messageContent) {
        sendMessage(messageContent);
        chatInput.value = "";
    }
});

function sendMessage(messageContent) {
    const message = {
        userId: "user1", // Replace with actual user ID
        message: messageContent
    };

    fetch('/api/messages', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(message)
    }).then(response => {
        if (response.ok) {
            console.log('Message sent successfully');
        } else {
            console.error('Failed to send message');
        }
    }).catch(error => {
        console.error('Error:', error);
    });
}

function sendMessage(messageContent) {
    const message = {
        userId: "user1", // Replace with actual user ID
        message: messageContent
    };

    fetch('/api/messages', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(message)
    }).then(response => response.text())
      .then(aiResponse => {
          if (aiResponse) {
              displayAIResponse(aiResponse);
          }
      }).catch(error => {
          console.error('Error:', error);
      });
}

function displayAIResponse(response) {
    const chatbox = document.querySelector(".chatbox");
    const aiMessage = document.createElement("li");
    aiMessage.classList.add("chat", "incoming");
    aiMessage.innerHTML = `
        <span class="material-symbols-outlined">smart_toy</span>
        <p>${response}</p>
    `;
    chatbox.appendChild(aiMessage);
    chatbox.scrollTop = chatbox.scrollHeight;
}
