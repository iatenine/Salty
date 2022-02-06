<html>
<body>
<%@ include file="header.jsp"%>
<h2>Order Page</h2>

<div id = "date">
    date
</div>
<c:forEach var="order" items="orderList">
   <c:out value="${or.id}" />
   <c:out value="${or.customer}" />
   <c:out value="${or.date}" />
</c:forEach>

<div>
    <% ArrayList<order> userList =(ArrayList<user>) request.getAttribute("order");
        Iterator<order> inter = userList.iterator();
        while(inter.hasNext()){

            user user = inter.next();

    user.getUsername(); user.getPassword();%>
</div>

</body>
</html>
