package org.example.ims.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.ims.config.DBConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/issue")
public class IssueServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        int requestId = Integer.parseInt(req.getParameter("requestId"));
        int itemId = Integer.parseInt(req.getParameter("itemId"));
        int qty = Integer.parseInt(req.getParameter("quantity"));

        try (Connection con = DBConnection.getConnection()) {

            PreparedStatement ps1 = con.prepareStatement(
                    "UPDATE inventory_items SET quantity=quantity-? WHERE item_id=?");
            ps1.setInt(1, qty);
            ps1.setInt(2, itemId);
            ps1.executeUpdate();

            PreparedStatement ps2 = con.prepareStatement(
                    "UPDATE requests SET status='ISSUED' WHERE request_id=?");
            ps2.setInt(1, requestId);
            ps2.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        resp.sendRedirect(req.getContextPath() +"/dashboard.jsp");
    }
}

