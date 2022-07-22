let submitButton = document.getElementById("login");
submitButton.addEventListener("click", (event) => {
event.preventDefault();
    //do AJAX
    //1. Create the XMLHttpRequest obj
    let xhttp = new XMLHttpRequest();
    // //next were going to get the values from the form
    let username= document.getElementById("user_name-sign-in").value;
    let password = document.getElementById("password-sign-in").value;


    let loginInfo = {
        user_name: username,
        pass_word: password,
    }
    console.log(loginInfo);
    //STEP 2: Define the behaviors of our responses as they come back from the server
    /*
        A readyState is a property which signifies that state that the request is currently in.
        There are several states:
        0: UNSENT - opening of the request has yet to be called
        1: OPENED - open() has been called
        2: HEADERS_RECEIVED: send() has been called[aka the request has been sent], and the headers of the response as well as the status are now available.
        3: LOADING: downloading the response. Therefore, the responseText (which is a xhr property) is holding partial data.
        4: DONE: the entire operation is now complete
        Why need readyStates?
        Ofter you can implement other transitions or animations to your webpages by triggering them at given readyStates.
        ex. loading screens
    */
        xhttp.onreadystatechange = function(){
            
            //200 status code is a OK response
       //which means that everything was processed correctly
       if(this.readyState == 4 && xhttp.status == 200){

             console.log(xhttp.responseText);


        let data = JSON.parse(xhttp.responseText);

         console.log(data);


         //global caching funciton
         localStorage.setItem('currentUser', JSON.stringify(data));
        window.location.replace("welcome.html")



        } else if(this.readyState == 4 && xhttp.status ===204) {
            console.log(xhttp.responseText)
            console.log()
                alert("Failed. Status Code: " + xhttp.status)
        }
    };


    //STEP 3: OPEN THE REQUEST
    xhttp.open("POST",`http://localhost:8080/HoroscopeApp/login`)

    xhttp.setRequestHeader("Content-Type", "application/json");
    xhttp.setRequestHeader("Access-Control-Allow-Origin", "*");
     console.log(xhttp);



    //STEP 4- send the request
    xhttp.send(JSON.stringify(loginInfo));

});