<%@ page import ="java.util.LinkedList, models.*" %>

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
       LinkedList<Customer> list = (LinkedList<Customer>) request.getAttribute("list");
       for(Customer customer : list){
        out.println("<div>");
        out.println("<input type='text' value='"+ customer.getCustomer_name() +"'/>");
        if(customer.getCustomer_data() != null){
            out.println(customer.getCustomer_data().getPhone());
            out.println(customer.getCustomer_data().getAddress());
        } else{
            out.println("N/A");
            out.println("N/A");
        }
        out.println("<button>Update</button>");
        out.println("</div>");
       }
    %>
        <!-- Edit, Name, Price, Available -->
    </div>
</body>
</body>
</html>
