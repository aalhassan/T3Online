<div class="top_top"></div>
        <div id="header" class="top_content">
            <c:import url="jsp/top_nav.jsp" />   
            <!--Beginning of included content -->
            <h1 class="question"> Add Employee Page </h1>
                <form name="add_employee" action="AddPageHandler" method="POST">
                <table >
                    <tr><td>First Name:</td><td><input type="text" name="firstName" value="" size="30" required/></td></tr>
                    <tr><td>Last Name:</td><td><input type="text" name="lastName" value="" size="30"required/></td></tr>
                    <tr><td>SSN:</td><td><input type="text" name="ssn" value="" size="10"required/></td></tr>
                    <tr><td>Department:</td><td><input type="text" name="dept" value="" size="25"required/></td></tr>
                    <tr><td>Room Number:</td><td><input type="text" name="room" value="" size="10" required/></td></tr>
                    <tr><td>Phone:</td><td><input type="text" name="phone" value="" size="10" required/></td></tr>                
                    
                </table>
                <input type="submit" value="Add Employee"/>
                </form> <br/> <br/>  
            <h3>${project4AddMessage}</h3> 
            <!--End of included content -->
        </div>  <!--End of header Div-->
<div class="top_bot"></div>

