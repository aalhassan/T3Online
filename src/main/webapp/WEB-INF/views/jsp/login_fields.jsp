<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="login_status">
    <c:choose>
        <c:when test="${sessionScope.loggedIn != true}">
            <form id="login_form" name="login_form" method="POST" action = "/loggedIn">
                <p id = "login_info"> Please sign in with your email address and password:
                <table id="login_in_layout">
                    <tr><td>Email Address: <br/>
                        <input  class="email login_in_fields" type="text" name="email" size="17"/></td>
                        <td>Password:<br/><input id="password" class="login_in_fields"  type="password" name="password" size="10"/>
                            </td></tr>

                    <tr><td  ><a href="/register" id="register_link">New User? Create Account >>></a></td><td> <input id="login_button" type="submit" value="Sign In"></td></tr>
                    <tr><td colspan="2" class="errors login_error_message">   </tr>
                </table>

            </form>
        </c:when >
        <c:otherwise>
            <div class="info" id="logged_in_message"> Logged in as <b id="email">${sessionScope.logged_in_email}</b> <span class="logout_link"><a href="/loggedOut" id="logout_link" >Logout</a></span> </div>
        </c:otherwise>
    </c:choose><br/>

</div>
