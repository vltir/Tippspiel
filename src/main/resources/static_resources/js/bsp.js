let count = 0;
let button = document.querySelector('#submit');
let login = document.querySelector('#login');
let sinup = document.querySelector('#signup');
let form = document.querySelector('#form');

// Event: Wenn jemand auf den Container "login | signup" klickt wechselt die Seite von login auf signup und zurück
document.addEventListener("DOMContentLoaded", function() {
    var paragraph = document.getElementById("switch");
    paragraph.addEventListener("click", function() {

       if(count == 0){
           count = 1;


           button.innerHTML = "sign up";
          button.name = 'signup';
           



       } else if(count == 1){
        count = 0;


        

        button.innerHTML = "login";
        form.action = 'login';
     

     



    }


    });
});
