<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>IMS Login</title>

    <!-- ALWAYS use contextPath -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
</head>

<body class="login-body">

<div class="login-box">
    <h2>Inventory Management System</h2>

    <form method="post" action="<%= request.getContextPath() %>/login">

        <input type="email" name="email" placeholder="Email" required>

        <input type="password" name="password" placeholder="Password" required>

        <button type="submit">Login</button>

        <% if (request.getAttribute("error") != null) { %>
        <p class="error"><%= request.getAttribute("error") %></p>
        <% } %>

    </form>
</div>

</body>
</html>
