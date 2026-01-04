<%@ page import="org.example.ims.model.User" %>
<%
    User u = (User) session.getAttribute("user");
    if(u == null || !"Admin".equals(u.getRole())) { response.sendRedirect("login.jsp"); return; }
%>
<html>
<head>
    <title>Admin - Users</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">

</head>
<body>
<h2>Admin Panel</h2>
<p>Welcome, <%= u.getFullName() %></p>

<% String success = request.getParameter("success");
    if("true".equals(success)){ %>
<p style="color:green;">User registered successfully</p>
<% } else if("false".equals(success)){ %>
<p style="color:red;">Failed to register user</p>
<% } %>

<form method="post" action="addUser">
    <input name="fullName" placeholder="Full Name" required><br>
    <input name="email" placeholder="Email" required><br>
    <input name="password" placeholder="Password" required><br>
    <select name="role">
        <option>Admin</option>
        <option>Instructor</option>
        <option>Department Head</option>
        <option>Inventory Officer</option>
        <option>Storekeeper</option>
    </select><br>
    <input name="department" placeholder="Department"><br><br>
    <button type="submit">Add User</button>
</form>
<br>
<a href="dashboard.jsp">Back to Dashboard</a>
</body>
</html>
