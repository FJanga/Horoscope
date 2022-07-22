let user = localStorage.getItem('currentUser');
//here is my current user 


let myUser = JSON.parse(user);
console.log(myUser);


let moodButton = document.getElementById("btn")
let greetingScreen = document.getElementById("greeting")


greeting.innerHTML = `Welcome, ${myUser.first_name}, Get Your Horoscope!`;       





let button = document.getElementById("button")
button.addEventListener("click",async() =>{


   





    try{

        const raw_response = await fetch(`http://sandipbgt.com/theastrologer/api/horoscopeApp/${myUser.zodiac_sign}/today`);
        
        if(!raw_response.ok){
            throw new Error(raw_response.status);
        }

        const json_data = await raw_response.json();

        console.log(json_data);

        var input = document.getElementById("input")
        var horoscope = document.createElement("h2")

        horoscope.innerHTML = `Today's zodiac message: 
        ${json_data.horoscope}`;

        input.append(horoscope);

        var b = document.createElement('br');
        input.append(b);

        var mood = document.getElementById("mood");
        mood.innerText = `${json_data.meta.mood}`;

    

    } catch (error){

        console.log(error);
        alert("Error: " + error);
    }



})

moodButton.addEventListener('click', (event) => {

    event.preventDefault();

    let xhttp = new XMLHttpRequest();

    var mood2 = document.getElementById("mood");

    let mood3 = mood2.innerText;

    let username = currentUser.userid;

    let moodInfo = {
        username: username,
        mood: mood3,
        
    }

    console.log(moodInfo);

    xhttp.onreadystatechange = function(){

        if(this.readyState == 4 && xhttp.status === 200){
            console.log(xhttp.responseText);

            let data = JSON.parse(xhttp.responseText);
            console.log(data);

        } else if (this.readyState == 4 && xhttp.status == 204){
            console.log(xhttp.responseText)
            alert("Failed to Login: Status Code - " + xhttp.status)
        }
    };

    xhttp.open("POST", `http://localhost:8080/HoroscopeApp/mood`);

    xhttp.setRequestHeader("Access-Control-Allow-Origin", "*");

    xhttp.setRequestHeader("Content-Type", "application/json");

    console.log(xhttp);

    xhttp.send(JSON.stringify(moodInfo));









})


function logout(){
    
    
    localStorage.removeItem('currentUser')

    window.location.replace("index.html");
    //clear wold work here too because theres one thing in there
}