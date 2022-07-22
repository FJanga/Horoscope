//Here we are getting our login button on the index page by the ID we gave it in HTML
let signupButton = document.getElementById("signup_button")

/*Here we are saying that we want an event to occur...
...once we click on our 'login_button'*/
signupButton.addEventListener("click",(event) => {
/*Click is the action, Event is what occurs...
...inside of the lambda (=>) expression.*/  

/*This method prevents any default values from being entered...
...once we click on the login_button and begin our event.*/
event.preventDefault();

/*Allows us to invoke a new XMLHttpRequest...
...for our server to respond to.*/
let xhttp = new XMLHttpRequest();

/*This is how we get our values from the login_form*/
let user_name = document.getElementById("user_name_signup").value;
let pass_word = document.getElementById("pass_word_signup").value;
let first_name = document.getElementById("first_name_signup").value;
let last_name = document.getElementById("last_name_signup").value;
let email = document.getElementById("email_signup").value;
let zodiac_sign = document.getElementById("zodiac_sign_signup").value;

let signupInfo = {
    user_name: user_name,
    pass_word: pass_word,
    first_name: first_name,
    last_name: last_name,
    email: email,
    zodiac_sign: zodiac_sign
};

console.log(signupInfo);

xhttp.onreadystatechange = function(){

    if(this.readyState == 4 && xhttp.status == 200){

        console.log(xhttp.responseText);
        let data = JSON.parse(xhttp.responseText);
        console.log(data);

        localStorage.setItem('currentUser',JSON.stringify(data));

        window.location.replace("welcome.html");
    }//if statement ending

    else if(this.readyState == 4 && xhttp.status === 204){

        console.log(xhttp.responseText);
        console.log();
            alert("Failed. Status Code: " + xhttp.status)

    }//else if statement ending

};//onreadystatechange function() ending 

xhttp.open("POST",`http://localhost:8080/HoroscopeApp/signUp`)

xhttp.setRequestHeader("Content-Type","application/json");
xhttp.setRequestHeader("Access-Control-Allow-Origin","*");
console.log(xhttp);
xhttp.send(JSON.stringify(signupInfo));

});