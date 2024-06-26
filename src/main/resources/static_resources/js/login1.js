const cursor = document.querySelector(".cursor");
const outline = document.querySelector(".outline");
const form = document.querySelector("#window");

const input = document.querySelector("input");

form.addEventListener("mouseover",function b(){


    outline.animate(

        {
            width:`200px`,
            height:`200px`,
            backdropFilter: `blur(5px) saturate(100%) invert(0%)`,
            
        },
        {
            duration: 500, fill: "forwards"
        }
    );

});
form.addEventListener("mouseleave",function b(){


    outline.animate(

        {
            width:`100px`,
            height:`100px`,
            backdropFilter: `blur(4px) saturate(10%) invert(100%)`,
            
        },
        {
            duration: 500, fill: "forwards"
        }
    );

});
window.addEventListener("mousemove", function (e){

const x = e.clientX;
const y = e.clientY;

cursor.style.left = `${x}px`;

cursor.style.top = `${y}px`;

outline.animate(

    {

        left:`${x}px`,
        top:`${y}px`,
    },
    {
        duration: 10000, fill: "forwards"
    }
);

});
