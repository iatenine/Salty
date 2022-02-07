<%@ page import ="java.util.LinkedList, models.*" %>

<html>
<body>
<%@ include file="header.jsp"%>
<h2>Item Page</h2>
<div class="table-header">
    <span>Name<span>
    <span>Price</span>
    <span>Available</span>
</div>
<hr />
    <div>
    <%
       LinkedList<Item> list = (LinkedList<Item>) request.getAttribute("list");
       for(Item item : list){
        out.println("<div>");
        out.println("<input type='text' value='"+ item.getName() +"'/>");
        out.println("<input type='number' placeholder='13.00' step='0.01' value='" + Double.valueOf(item.getPrice()/100) + "'/>");
        out.println("<input type='checkbox' value='" + item.isAvailable() + "true' />");
        out.println("<button>Update</button>");
        out.println("</div>");
       }
    %>
        <!-- Edit, Name, Price, Available -->
    </div>
</body>
</html>
