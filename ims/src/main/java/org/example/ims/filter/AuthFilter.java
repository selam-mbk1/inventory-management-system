package org.example.ims.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebFilter(urlPatterns = {
        "/dashboard.jsp",
        "/inventory.jsp",
        "/add-inventory.jsp",
        "/issued-items.jsp",
        "/store-requests.jsp"
})
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        if (!isLoggedIn(req)) {
            res.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        chain.doFilter(request, response);
    }

    // 🔹 helper method (removes duplicated code)
    private boolean isLoggedIn(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        return session != null && session.getAttribute("user") != null;
    }
}
