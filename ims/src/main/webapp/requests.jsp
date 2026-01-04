<%@ page import="org.example.ims.model.User" %>
<%@ page session="true" %>
<%
    User u = (User) session.getAttribute("user");
    if(u == null || !"Instructor".equals(u.getRole())) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Request Item</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>
<h2>Request Item</h2>
<form method="post" action="request">
    <label>Item</label>
    <select name="itemId" required>
        <option value=""> Select Item </option>
        <option value="1">Marker</option>
        <option value="2">Pen</option>
        <option value="3">Dell Monitor</option>
        <option value="4">Notebook</option>
        <option value="5">Whiteboard</option>
        <option value="6">Projector</option>
        <option value="7">Stapler</option>
        <option value="8">Chair</option>
    </select><br>

    <label>Quantity</label>
    <input type="number" name="quantity" required><br>

    <label>Reason</label>
    <textarea name="reason"></textarea><br>

    <button type="submit">Submit Request</button>
</form>

<a href="<%= request.getContextPath() %>/dashboard.jsp">Back to Dashboard</a>
</body>
</html>
