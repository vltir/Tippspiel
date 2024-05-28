let count = 0;
let button = document.querySelector('#submit');
let login = document.querySelector('#login');
let sinup = document.querySelector('#signup');
let form = document.querySelector('#form');

// Event: Wenn jemand auf  "login" oder "signup" klickt wechselt die Seite von login auf signup und andersrum
document.addEventListener("DOMContentLoaded", function() {
    var paragraph = document.getElementById("signup");
    paragraph.addEventListener("click", function() {


        button.innerHTML = "sign up";
        button.name = 'signup';
           



    });
});
document.addEventListener("DOMContentLoaded", function() {
    var paragraph = document.getElementById("login");
    paragraph.addEventListener("click", function() {

       

        button.innerHTML = "login";
        form.action = 'login';
     

     



    


    });
});

