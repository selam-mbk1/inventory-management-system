package org.example.ims.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.ims.dao.RequestDAO;

import java.io.IOException;

@WebServlet("/approve")
public class ApprovalServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int requestId = Integer.parseInt(req.getParameter("requestId"));
        String action = req.getParameter("action");

        RequestDAO dao = new RequestDAO();

        if ("APPROVE".equals(action)) {       // matches dh-requests.jsp form
            dao.updateStatus(requestId, "PENDING_IO");
        } else if ("REJECT".equals(action)) {
            dao.updateStatus(requestId, "REJECTED");
        }

        resp.sendRedirect(req.getContextPath() + "/dh-requests.jsp");
    }
}
