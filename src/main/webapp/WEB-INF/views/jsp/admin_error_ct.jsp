<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="top_top"></div>
<div id="header" class="top_content">
    <c:import url="jsp/top_nav.jsp" />
    <!--Beginning of included content -->
    <jsp:useBean id="player" class="com.ticCore.beans.Player" scope="request"/>
    <h1 class="question">Admin Login Error</h1>
    <p class ="errorMessages">Authentication Error. Please go back and try again</p>
    <!--End of included content -->
</div>  <!--End of header Div-->
<div class="top_bot"></div>
