<%@ page import="org.example.ims.model.User" %>
<%
    User u = (User) session.getAttribute("user");
    if(u == null || !"Storekeeper".equals(u.getRole())) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Add Inventory Item</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>

<div class="container">
    <h2>Add New Inventory Item</h2>
    <p>Welcome, <%= u.getFullName() %></p>

    <% String success = request.getParameter("success");
        if("true".equals(success)){ %>
    <p class="success">Item added successfully!</p>
    <% } else if("false".equals(success)){ %>
    <p class="error">Failed to add item. Try again.</p>
    <% } %>

    <form method="post" action="<%= request.getContextPath() %>/add-inventory">
        <label>Item Name:</label>
        <input type="text" name="itemName" placeholder="Enter item name" required>

        <label>Category:</label>
        <select name="category" required>
            <option value="">-- Select Category --</option>
            <option value="Consumable">Consumable</option>
            <option value="Non-Consumable">Non-Consumable</option>
        </select>

        <label>Quantity:</label>
        <input type="number" name="quantity" placeholder="Enter quantity" min="1" required>

        <label>Location:</label>
        <input type="text" name="location" placeholder="Enter location" required>

        <button type="submit">Add Item</button>
    </form>

    <br>
    <a href="dashboard.jsp" class="back-link">Back to Dashboard</a>
</div>

</body>
</html>
