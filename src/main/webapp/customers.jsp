<html>
<body>
<%@ include file="header.jsp"%>
<h2>Customer Page</h2>
<div class="table-header">
    <span>Name<span>
    <span>Phone</span>
    <span>Address</span>
</div>
<hr />
    <div>
    <%
       LinkedList<Item> list = (LinkedList<Item>) request.getAttribute("list");
       for(Item item : list){
        out.println("<div>");
        out.println("<input type='text' value='"+ item.getName() +"'/>");
        out.println("<input type='number' placeholder='13.00' step='0.01' value='" + item.getPrice() + "'/>");
        out.println("<input type='checkbox' value='" + item.isAvailable() + "true' />");
        out.println("<button>Update</button>");
        out.println("</div>");
       }
    %>
        <!-- Edit, Name, Price, Available -->
    </div>
</body>
</body>
</html>
