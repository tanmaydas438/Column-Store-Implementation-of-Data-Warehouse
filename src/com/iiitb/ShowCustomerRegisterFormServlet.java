package com.iiitb;
import java.io.*;
import java.util.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowCustomerRegisterFormServlet extends HttpServlet {
 
    public ShowCustomerRegisterFormServlet() {
        super();
    }
 
    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         
        String[] countryCodes = Locale.getISOCountries();
 
        Map<String, String> mapCountries = new TreeMap<>();
 
        for (String countryCode : countryCodes) {
            Locale locale = new Locale("", countryCode);
            String code = locale.getCountry();
            String name = locale.getDisplayCountry();
            mapCountries.put(code, name);
        }
 
        request.setAttribute("mapCountries", mapCountries);
 
        String registerForm = "register_form.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(registerForm);
        dispatcher.forward(request, response);     
         
    }
 
}
