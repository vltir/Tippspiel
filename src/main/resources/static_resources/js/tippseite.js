const cursor = document.querySelector(".soccer-ball");

const form = document.querySelector("form");

  function switchSite(value){
    window.location.replace("http://localhost:8080/em2024/tipps/" + value);
}
window.addEventListener("mousemove", function (e){

const x = e.clientX;
const y = e.clientY;

cursor.style.left = `${x}px`;

cursor.style.top = `${y}px`;


});

document.querySelector(".submit").addEventListener('submit',function(event){

        event.preventDefault();


});