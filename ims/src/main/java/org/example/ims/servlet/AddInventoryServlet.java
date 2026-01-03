package org.example.ims.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.ims.config.DBConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/add-inventory")
public class AddInventoryServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String itemName = req.getParameter("itemName");
        String category = req.getParameter("category");
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        String location = req.getParameter("location");

        boolean success = false;

        String sql = "INSERT INTO inventory_items(item_name, category, quantity, location) VALUES (?,?,?,?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, itemName);
            ps.setString(2, category);
            ps.setInt(3, quantity);
            ps.setString(4, location);

            success = ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        resp.sendRedirect(req.getContextPath() + "/add-inventory.jsp?success=" + success);
    }
}
