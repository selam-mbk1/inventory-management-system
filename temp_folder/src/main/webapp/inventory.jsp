<%@ page import="java.util.*,org.example.ims.model.InventoryItem" %>
<h2>Inventory Items</h2>
<table border="1">
    <tr><th>Name</th><th>Category</th><th>Quantity</th><th>Location</th></tr>

    <%
        List<InventoryItem> items = (List<InventoryItem>) request.getAttribute("items");
        for(InventoryItem i : items){
    %>
    <tr>
        <td><%= i.getItemName() %></td>
        <td><%= i.getCategory() %></td>
        <td><%= i.getQuantity() %></td>
        <td><%= i.getLocation() %></td>
    </tr>
    <% } %>
</table>
<a href="dashboard.jsp">Back to Dashboard</a>
