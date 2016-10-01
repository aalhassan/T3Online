<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="top_top"></div>
        <div id="header" class="top_content">
            <c:import url="jsp/top_nav.jsp" />   
            <!--Beginning of included content -->
                <h1 class="question">New Player Registration</h1>
                    <form:form commandName="player" action="/savePlayer" method="post">
                    <fieldset>
                        <legend>Enter all  info below</legend>
                        <table>
                        <tr>
                            <td>
                                <label for="firstName">First Name: </label></td>
                            <td>
                                <form:input id="firstName" path="firstName" cssClass="inputFields" cssErrorClass="errorFields"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label for="lastName">Last Name: </label></td>
                            <td>
                                <form:input id="lastName" path="lastName" cssClass="inputFields" cssErrorClass="errorFields"/>
                            </td>
                        </tr>
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

                        <tr>
                            <td colspan="2">Passwords would only be reset using the answers provided below:</td>
                        </tr>
                        <tr>
                            <td>
                                <label for="answer1">Favourite vacation city:</label>
                            </td>
                            <td>
                                <form:input id="answer1" path="answer1" cssClass="inputFields" cssErrorClass="errorFields"/>
                            </td>
                        </tr>

                        <tr>
                            <td>
                                <label for="answer2">City of birth:</label>
                            </td>
                            <td>
                                <form:input id="answer2" path="answer2" cssClass="inputFields" cssErrorClass="errorFields"/>
                            </td>
                        </tr>
                       </table>
                        <form:errors id="errors" path="*"  cssClass="errorMessages"/>
                        <br/>
                        <p id="form_buttons">
                            <input id="reset" type="reset" tabindex="4">
                            <input id="submit" type="submit" tabindex="5"
                                   value="Register">
                        </p>
                    </fieldset>
                    </form:form>
            <!--End of included content -->
        </div>  <!--End of header Div-->
<div class="top_bot"></div>

