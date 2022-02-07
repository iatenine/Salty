<html>
<body>
<%@ include file="header.jsp"%>
<h2>Order Page</h2>
<body>
    <div>
    <%
       LinkedList<Order> list = (LinkedList<Order>) request.getAttribute("list");
       for(Order order : list){
       // order #
       // customer #
       // Date
       // total
        out.println("<div>");
        out.println("<span>"+ order.getId() +"'</span>");
        out.println("<span>" + order.getDate() + "'</span>");
        out.println("<button>Update</button>");
        out.println("</div>");
       }
    %>
        <!-- Edit, Name, Price, Available -->
    </div>

</body>
</html>
