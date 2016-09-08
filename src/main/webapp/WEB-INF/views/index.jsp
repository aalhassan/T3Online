<%@include file="head.jsp"%>
<html>
<body>
<h2>User Display Exercise - Week 1</h2>
<a href = "searchUser">Go to the User Search</a> <br/>

<form name="search_employee" action="searchUser" method="POST">
    <table cellpadding="20">
        <tr>
            <td>
                <h4 >Search User By Last Name:</h4>

            </td>
        </tr>

        <tr>
            <td><div class="searchBox"><input type="search" name="lName" placeholder="Enter Last Name Here" required/><input type="submit" value="Go"/></div></td>
        </tr>


    </table>

</form>
</body>
</html>
