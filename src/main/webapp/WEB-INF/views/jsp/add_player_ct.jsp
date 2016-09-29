<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="top_top"></div>
        <div id="header" class="top_content">
            <c:import url="jsp/top_nav.jsp" />   
            <!--Beginning of included content -->
                <h1 class="question">New Player Registration</h1>
                    <form:form commandName="player" action="/accountService/savePlayer" method="post">
                    <fieldset>
                        <legend>Enter all  info below</legend>
                        <p>
                            <label for="firstName">First Name: </label>
                            <form:input id="firstName" path="firstName"/>

                        </p>
                        <p>
                            <label for="lastName">Last Name: </label>
                            <form:input id="lastName" path="lastName"/>

                        </p>
                        <p>
                            <label for="email">Email: </label>
                            <form:input id="email" path="email"/>

                        </p>
                        <p>Please provide answers below for password reset purposes. Passwords would only be
                        reset using the answers below</p>
                        <p>
                            <label for="answer1">Favourite vacation city:</label>
                            <form:input id="answer1" path="answer1"/>

                        </p>

                        <p>
                            <label for="answer2">City of birth:</label>
                            <form:input id="answer2" path="answer2"/>

                        </p>

                        <p id="buttons">
                            <input id="reset" type="reset" tabindex="4">
                            <input id="submit" type="submit" tabindex="5"
                                   value="Register">
                        </p>
                    </fieldset>
                    </form:form>
            <!--End of included content -->
        </div>  <!--End of header Div-->
<div class="top_bot"></div>

