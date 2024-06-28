const cursor = document.querySelector(".cursor");

const form = document.querySelector("form");

  function switchSite(value){
    window.location.replace("http://localhost:8080/em2024/results/" + value);
}
window.addEventListener("mousemove", function (e){

const x = e.clientX;
const y = e.clientY;

cursor.style.left = `${x}px`;

cursor.style.top = `${y}px`;


});