<%@ page import="org.example.ims.model.User" %>
<%@ page session="true" %>
<%
    // Get the logged-in user from session
    User u = (User) session.getAttribute("user");
    if(u == null) {
        // If user is not logged in, redirect to login page
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Dashboard</title>

    <!-- Link CSS file correctly using context path -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">

</head>
<body>
<div class="container">
    <h2>Welcome <%= u.getFullName() %></h2>
    <h3>Role: <%= u.getRole() %></h3>

    <hr>

    <%-- Role-based navigation --%>
    <% if ("Instructor".equals(u.getRole())) { %>
    <a href="request.jsp">Request Item</a><br>
    <% } %>

    <% if ("Department Head".equals(u.getRole())) { %>
    <a href="dh-requests.jsp">Approve Requests</a><br>
    <% } %>

    <% if ("Inventory Officer".equals(u.getRole())) { %>
    <a href="io-requests.jsp">Verify Requests</a><br>
    <% } %>

    <% if ("Admin".equals(u.getRole())) { %>
    <a href="admin-users.jsp">Manage Users</a><br>
    <% } %>

    <% if ("Storekeeper".equals(u.getRole())) { %>
    <a href="store-requests.jsp">Issue Items</a><br>
    <a href="add-inventory.jsp">Add New Inventory Item</a><br>
    <a href="<%= request.getContextPath() %>/issued-items">Issued Items</a><br>
    <% } %>

    <hr>
    <a href="<%= request.getContextPath() %>/logout">Logout</a>
</div>
</body>
</html>
