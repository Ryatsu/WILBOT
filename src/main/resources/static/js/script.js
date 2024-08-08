const chatbotToggler = document.querySelector(".chatbot-toggler");
const chatbotCloseBtn = document.querySelector(".close-btn");
const chatbox = document.querySelector(".chatbox");
const chatInput = document.querySelector(".chat-input textarea");
const sendBtn = document.getElementById("send-btn");

// Toggle chatbox visibility
chatbotToggler.addEventListener("click", () => document.body.classList.toggle("show-chatbot"));
chatbotCloseBtn.addEventListener("click", () => document.body.classList.remove("show-chatbot"));

// Function to scroll to the bottom of the chatbox
function scrollToBottom() {
    chatbox.scrollTo({
        top: chatbox.scrollHeight,
        behavior: 'smooth'
    });
}

// Function to animate the "Thinking..." message
function animateThinkingMessage(thinkingMessage) {
    let dots = 0;
    return setInterval(() => {
        thinkingMessage.innerHTML = '<p>' + '.'.repeat(dots % 4) + '</p>';
        dots++;
    }, 500);
}

// Function to send a message
async function sendMessage(message) {
    // Show "Thinking..." message
    const thinkingMessage = document.createElement('li');
    thinkingMessage.classList.add('chat', 'incoming');
    thinkingMessage.innerHTML = '<p>...</p>';
    chatbox.appendChild(thinkingMessage);
    scrollToBottom(); // Scroll to bottom after adding "Thinking..." message

    // Start animating the "Thinking..." message
    const intervalId = animateThinkingMessage(thinkingMessage);

    try {
        // Prepare the request body as a JSON object
        const requestBody = JSON.stringify({
            userId: "someUserId", // Replace with actual userId if available
            query: message
        });

        const response = await fetch('/chatbot/respond', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json', // Set content type to application/json
            },
            body: requestBody // Send the request body as JSON string
        });

        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        const data = await response.text();
        clearInterval(intervalId); // Stop animating
        // Replace "Thinking..." message with the actual response
        thinkingMessage.innerHTML = `<p>${data}</p>`;
        scrollToBottom(); // Scroll to bottom after adding new message
    } catch (error) {
        console.error('Error:', error);
        clearInterval(intervalId); // Stop animating
        // Replace "Thinking..." message with an error message
        thinkingMessage.innerHTML = '<p>Sorry, there was an error processing your request.</p>';
        scrollToBottom(); // Scroll to bottom after adding new message
    }
}

// Function to display a message in the chatbox
function displayMessage(message, type) {
    const chatMessage = document.createElement('li');
    chatMessage.classList.add('chat', type);
    chatMessage.innerHTML = `<p>${message}</p>`;
    chatbox.appendChild(chatMessage);
    scrollToBottom(); // Scroll to bottom after adding new message
}

// Send message when clicking the send button
sendBtn.addEventListener("click", () => {
    const message = chatInput.value.trim();
    if (message) {
        displayMessage(message, 'outgoing');
        chatInput.value = '';
        sendMessage(message);
    }
});

// Send message when pressing Enter key
chatInput.addEventListener("keypress", (e) => {
    if (e.key === "Enter" && !e.shiftKey) { // Send message on Enter key press
        e.preventDefault(); // Prevent adding a new line
        const message = chatInput.value.trim();
        if (message) {
            displayMessage(message, 'outgoing');
            chatInput.value = '';
            sendMessage(message);
        }
    }
});

// Load initial greeting from the bot
document.addEventListener("DOMContentLoaded", async () => {
    const response = await fetch('/chatbot/start');
    const data = await response.text();
    document.getElementById('bot-greeting').textContent = data;
});
