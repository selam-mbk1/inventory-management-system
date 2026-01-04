package org.example.ims.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.ims.dao.RequestDAO;

import java.io.IOException;

@WebServlet("/issued-items")
public class IssuedServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        RequestDAO dao = new RequestDAO();
        req.setAttribute("issuedRequests", dao.getIssuedRequests());

        req.getRequestDispatcher("/issued-items.jsp").forward(req, resp);
    }
}

