<%@ page import ="java.util.LinkedList, models.*" %>

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
        out.println("<span> Order # "+ order.getId() +"</span>");
        out.println("<span> Placed on: " + order.getDateString() + "</span>");
        out.println("Items: ");
        if(order.getItems() != null){
            for(Item item : order.getItems()){
                out.print(item.getName());
                if(item != order.getItems().getLast())
                out.println(",");
            }
        }
        out.println("<button>Delete Order</button>");
        out.println("</div>");
       }
    %>
        <!-- Edit, Name, Price, Available -->
    </div>

</body>
</html>
