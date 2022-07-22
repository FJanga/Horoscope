package com.servlets;

// import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.models.User;
import com.persistence.UserDAO;
import com.sun.net.httpserver.Headers;

import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class UserServlet  extends HttpServlet{


    UserDAO userDAO = new UserDAO();



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String URI = req.getRequestURI().replace("/HoroscopeApp/","");




        switch (URI){


            case "mood":

//                getAllTicketsForCurrentUser(req,resp);

                break;
            default:

//                System.out.println("WE are in the user Servlet ");

                break;


        }



    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);

        String URI = req.getRequestURI().replace("/HoroscopeApp/", "");

        System.out.println(URI);

        switch (URI) {
            case "SignUp":
                SignUpUser(req,resp);


                break;

            case "login":

                processLoginForUser(req,resp);

                break;

            case "updateMood":

                break;

            default:
                System.out.println("we're in the default");
                break;
        }


    }

    public void updateMood(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException  {
        BufferedReader br = req.getReader();

        StringBuilder sb = new StringBuilder();
        String line = br.readLine();

        while (line != null) {
            sb.append(line);
            line = br.readLine();
        }

        System.out.println(sb);

        String body = sb.toString();

        String[] sepByComma = body.split(",");

        ArrayList<String> values = new ArrayList<>();

        for (String value : sepByComma) {
            value = value.replaceAll("\\{", "").replaceAll("}", "").replaceAll("\"", "");
            System.out.println(value);
            String target = value.substring(value.indexOf(":") + 1);
            System.out.println(target);
            values.add(target);
        }

        Integer user_Id = Integer.valueOf(values.get(0));
        String mood = values.get(1);

        User user = new User(user_Id,mood);
        userDAO.update(user);

        if (user != null) {
            resp.setStatus(200);

            //get an HTTP Session
            HttpSession session = req.getSession(); //might work without these 2 lines
            session.setAttribute("user", user); //might work without these 2 lines

            PrintWriter out = resp.getWriter();
            resp.setContentType("application/json");
            resp.addHeader("Access-Control-Allow-Origin", "*");

            ObjectMapper om = new ObjectMapper();

            //convert the Java object to a JSON object with the object mapper

            out.println(om.writeValueAsString(user));



        } else {
            resp.setStatus(204);
        }
    }






    private void SignUpUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        BufferedReader br = req.getReader();
        StringBuilder sb = new StringBuilder();
        String line = br.readLine();

        while (line != null){
            sb.append(line);
            line = br.readLine();
        }


        System.out.println(sb);

        String body = sb.toString();

        String[] sepByComma = body.split(",");


        ArrayList<String> values = new ArrayList<>();

        // will print out index 0 and 1 that is separated by comma
        for(String value: sepByComma) {
//            System.out.println(value);

            // Here we want to trim all of the excess symbols as well as
            // keys and key the values
            // removes all "" and , commands

            value = value.replaceAll("\\{","").replaceAll("}","").replaceAll("\"","");

            String target = value.substring(value.indexOf(":") + 1);

            System.out.println(target);
            values.add(target);

        }/*for loop ending*/

        String first_name = values.get(0);
        String last_name = values.get(1);
        String email = values.get(2);
        String user_name = values.get(3);
        String pass_word = values.get(4);
        String zodiac_sign = values.get(5);

        User user = new User(user_name, pass_word, first_name, last_name, email, zodiac_sign);
        userDAO.create(user);

        if (user != null) {
            resp.setStatus(200);

            HttpSession session = req.getSession();
            session.setAttribute("user", user);

            PrintWriter out = resp.getWriter();
            resp.setContentType("application/json");

            ObjectMapper om = new ObjectMapper();
            out.println(om.writeValueAsString(user));
            System.out.println("The user " + user_name + "created an account.");
        }/*if statement ending*/ else {
            resp.setStatus(204);
            System.out.println("This was a 204 response code.");
        }/*else statement ending*/

    }/*registerSignUp method ending*/



    private void processLoginForUser(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        BufferedReader br = req.getReader();

        StringBuilder sb = new StringBuilder();

        String line = br.readLine();

        // here we are reading through each line of the req.getReader
        //it can be one line or more than one line
        //creating a while loop is safer
        while (line != null) {
            sb.append(line);
            //just in case we have more than one line in our reader
            line = br.readLine();
        }

//        System.out.println(sb);
        String body = sb.toString();

        String[] sepByComma = body.split(",");

        List<String> values = new ArrayList<String>();

        for (String value : sepByComma) { // each element in array looks like this
//            values.add(value.substring(value.indexOf("=") + 1)); // trim each String element in the array to just value -> [bob, pass]


            value = value.replaceAll("\\{","").replaceAll("}","").replaceAll("\"","");

            String target = value.substring(value.indexOf(":") + 1);

            System.out.println(target);
            values.add(target);

        }

        String username = values.get(0);
        String password = values.get(1);


        User user = userDAO.loginUser(username, password);


        if (user != null) {
            resp.setStatus(200);

            // grab the session & add the user to the session
            //it manages whos logged in and whose logged out
            //created a session between our client and server
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            //not that if you are 'logging a user out and you've set the session
            // use 'req.getSession().removeAttribute("user");' to remove them from the current session





            // print the logged in user to the screen (shows if there is no html present)
            PrintWriter out = resp.getWriter();
            resp.setContentType("application/json");

            ObjectMapper om = new ObjectMapper();
            // convert the object with the object mapper
            out.println(om.writeValueAsString(user));

            // log it!
//            System.out.println("The user " + username + " has logged in.");

        } else {
            resp.setStatus(204);
        }

    }}







