package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response)
            throws jakarta.servlet.ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        if (true) {
            response.sendRedirect("gerente/inicio.jsp");
        } else if ("logistica@castores.com".equals(email) && "L0g1st1c4.Cast0r3s".equals(password)) {
            response.sendRedirect("logistica/inventario.jsp");
        } else {
            request.setAttribute("error", "login.error.credentials");
            request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
        }
    }
}