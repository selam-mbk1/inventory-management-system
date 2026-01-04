<%@ page import="java.util.*, org.example.ims.model.Request, org.example.ims.model.User" %>
<!DOCTYPE html>
<html>
<head>
    <title>Issued Items</title>

    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
</head>

<body>

<%
    User u = (User) session.getAttribute("user");
    if(u == null || !"Storekeeper".equals(u.getRole())) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }

    List<Request> issuedRequests =
            (List<Request>) request.getAttribute("issuedRequests");
%>

<div class="container">

    <h2>Issued Items</h2>
    <p>Welcome, <b><%= u.getFullName() %></b></p>

    <table>
        <tr>
            <th>Request ID</th>
            <th>Item ID</th>
            <th>Quantity</th>
            <th>Reason</th>
            <th>Status</th>
        </tr>

        <% if (issuedRequests == null || issuedRequests.isEmpty()) { %>
        <tr>
            <td colspan="5">No issued items yet</td>
        </tr>
        <% } else {
            for(Request r : issuedRequests){ %>
        <tr>
            <td><%= r.getRequestId() %></td>
            <td><%= r.getItemId() %></td>
            <td><%= r.getQuantity() %></td>
            <td><%= r.getReason() %></td>
            <td><%= r.getStatus() %></td>
        </tr>
        <% } } %>
    </table>

    <br>
    <a href="<%= request.getContextPath() %>/dashboard.jsp">Back to Dashboard</a>

</div>

</body>
</html>
