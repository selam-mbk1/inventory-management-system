package org.example.ims.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import org.example.ims.dao.InventoryDAO;

import java.io.IOException;

@WebServlet("/inventory")
public class InventoryServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        InventoryDAO dao = new InventoryDAO();
        req.setAttribute("items", dao.getAllItems());
        req.getRequestDispatcher("jsp/inventory.jsp").forward(req, resp);
    }
}
