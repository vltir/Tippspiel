const cursor = document.querySelector(".soccer-ball");

window.addEventListener("mousemove", function (e){

const x = e.clientX;
const y = e.clientY;

cursor.style.left = `${x}px`;

cursor.style.top = `${y}px`;


});