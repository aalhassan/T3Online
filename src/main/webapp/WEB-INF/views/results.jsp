<%@include file="head.jsp"%>

<html><body>

<%--TODO Pretty up the results!--%>
<div class="container-fluid">
    <h2>Search Results: </h2>
    <table border="1" cellpadding="20">
        <tr class="table_header"><td>ID</td><td>FirstName</td><td>LastName</td><td>BirthDate</td><td>Age</td></tr>
        <c:forEach var="user"  items="${users}" >
            <tr><td>${user.userid}</td><td>${user.firstName}</td><td>${user.lastName}</td><td>${user.birthDate}</td><td>${user.age}</td></tr>
        </c:forEach>
    </table>
</div>

</body>
</html>
