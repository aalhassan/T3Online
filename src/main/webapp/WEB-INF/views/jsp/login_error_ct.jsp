<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="top_top"></div>
<div id="header" class="top_content">
    <c:import url="jsp/top_nav.jsp" />
    <!--Beginning of included content -->
    <h1 class="question">Login Error</h1>
    <p class ="errorMessages">Authentication Error.  <br/>
        <table>
            <c:forEach var="error"  items="${requestScope.errors}" >
                <tr><td>${error.key}: </td><td>${error.value}</td></tr>
            </c:forEach>
        </table>
    </p>
    <p> Please sign in from <a href="home">home page</a>a> or <a href="register"> create a new account</a>
    <!--End of included content -->
</div>  <!--End of header Div-->
<div class="top_bot"></div>
