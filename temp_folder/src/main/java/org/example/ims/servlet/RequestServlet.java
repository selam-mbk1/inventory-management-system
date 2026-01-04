package org.example.ims.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.ims.dao.RequestDAO;
import org.example.ims.model.Request;
import org.example.ims.model.User;

import java.io.IOException;

@WebServlet("/request")
public class RequestServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        Request r = new Request();
        r.setRequesterId(user.getUserId());
        r.setItemId(Integer.parseInt(req.getParameter("itemId")));
        r.setQuantity(Integer.parseInt(req.getParameter("quantity")));
        r.setReason(req.getParameter("reason"));

        new RequestDAO().createRequest(r);
        resp.sendRedirect(req.getContextPath() +"/dashboard.jsp");
    }
}
