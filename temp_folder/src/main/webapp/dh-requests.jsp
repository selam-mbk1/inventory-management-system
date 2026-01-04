<%@ page import="java.util.*,org.example.ims.model.User,org.example.ims.model.Request,org.example.ims.dao.RequestDAO" %>
<%@ page session="true" %>
<%
    User u = (User) session.getAttribute("user");
    if(u == null || !"Department Head".equals(u.getRole())) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }

    RequestDAO dao = new RequestDAO();
    List<Request> requests = dao.getRequestsByStatus("PENDING_DH");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Pending Requests</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>
<h2>Pending Requests</h2>
<p>Welcome, <%= u.getFullName() %></p>

<table border="1">
    <tr><th>Request ID</th><th>Item ID</th><th>Quantity</th><th>Reason</th><th>Action</th></tr>

    <% if(requests.isEmpty()){ %>
    <tr><td colspan="5">No pending requests</td></tr>
    <% } else {
        for(Request r : requests){ %>
    <tr>
        <td><%= r.getRequestId() %></td>
        <td><%= r.getItemId() %></td>
        <td><%= r.getQuantity() %></td>
        <td><%= r.getReason() %></td>
        <td>
            <form method="post" action="approve" style="display:inline;">
                <input type="hidden" name="requestId" value="<%= r.getRequestId() %>">
                <input type="hidden" name="action" value="APPROVE">
                <button type="submit">Approve</button>
            </form>
            <form method="post" action="approve" style="display:inline;">
                <input type="hidden" name="requestId" value="<%= r.getRequestId() %>">
                <input type="hidden" name="action" value="REJECT">
                <button type="submit">Reject</button>
            </form>
        </td>
    </tr>
    <% } } %>
</table>

<br>
<a href="<%= request.getContextPath() %>/dashboard.jsp">Back to Dashboard</a>
</body>
</html>
