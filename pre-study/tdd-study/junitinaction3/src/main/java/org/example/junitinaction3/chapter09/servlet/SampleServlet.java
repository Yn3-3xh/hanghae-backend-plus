package org.example.junitinaction3.chapter09.servlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class SampleServlet {

    private static final long serialVersionUID = 1L;

    public boolean isAuthenticated(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return false;
        }
        String authenticationAttribute = (String) session.getAttribute("authenticated");

        return Boolean.parseBoolean(authenticationAttribute);
    }
}
