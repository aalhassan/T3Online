<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="top_top"></div>
        <div id="header" class="top_content">
            <c:import url="jsp/top_nav.jsp" />   
            <!--Beginning of included content -->
            <jsp:useBean id="player" class="com.ticCore.beans.Player" scope="request"/>
                <h1 class="question">Admin Login</h1>
            <FORM ACTION="j_security_check" METHOD="POST">
                <TABLE>
                    <TR><TD>User name: <INPUT class="inputFields" TYPE="TEXT" NAME="j_username">
                    <TR><TD>Password: <INPUT class="inputFields" TYPE="PASSWORD" NAME="j_password">
                    <TR><TH><INPUT TYPE="SUBMIT" VALUE="Log In">
                </TABLE>
            </FORM>
            <!--End of included content -->
        </div>  <!--End of header Div-->
<div class="top_bot"></div>

