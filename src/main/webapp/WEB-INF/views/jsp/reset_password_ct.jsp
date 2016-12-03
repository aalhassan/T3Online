<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="top_top"></div>
        <div id="header" class="top_content">
            <c:import url="jsp/top_nav.jsp" />   
            <!--Beginning of included content -->
                <h1 class="question">Reset Player Password</h1>
                    <form:form commandName="player" action="updateUser" method="post">
                    <fieldset>
                        <legend>Enter email and new password below</legend>
                        <table>

                        <tr>
                            <td>
                                <label for="email">Email: </label>
                            </td>

                            <td>
                                <form:input id="email" path="email" cssClass="inputFields" cssErrorClass="errorFields"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label for="password">Password: </label>
                            </td>

                            <td>
                                <form:password id="password" path="password" cssClass="inputFields" cssErrorClass="errorFields"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label for="password1">Enter Password Again: </label>
                            </td>

                            <td>
                                <form:password id="password1" path="password1" cssClass="inputFields" cssErrorClass="errorFields"/>
                            </td>
                        </tr>

                       </table>
                        <form:errors id="errors" path="*"  cssClass="errorMessages"/>
                        <br/>
                        <p id="form_buttons">
                            <input id="submit" type="submit" tabindex="5"
                                   value="Reset Password">
                        </p>
                    </fieldset>
                    </form:form>
            <!--End of included content -->
        </div>  <!--End of header Div-->
<div class="top_bot"></div>

