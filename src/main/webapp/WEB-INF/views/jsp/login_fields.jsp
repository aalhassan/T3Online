<div id="login_status">
    <form id="login_form" name="login_form" method="POST" action = "/t3online/login">
        <p id = "login_info"> Please sign in with your email address and password: <span> <input id="login_button" type="submit" value="Sign In"></span></p>
        <table id="login_in_layout">
            <tr><td>Email Address: <br/>
                <input  class="email login_in_fields" type="text" name="email" size="17"/></td>
                        <td>Password:<br/><input id="password" class="login_in_fields"  type="password" name="password" size="10"/></td></tr>

            <tr><td colspan="2" class="errors login_error_message"></td></tr>
            <tr><td colspan="2"><a href="/t3online/register" id="register_link">New User? Create Account >>></a></tr>
        </table>

    </form>
    <div class="info" id="logged_in_message"> Logged in as <b id="email">${sessionScope.logged_in_email}</b> <span class="logout_link"><a href="logout"></a></span> </div>
</div>
