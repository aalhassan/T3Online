<div class="top_top"></div>
        <div id="header" class="top_content">
            <c:import url="jsp/top_nav.jsp" />   
            <!--Beginning of included content -->
                <h1 class="question">Employee Search Page<h1>
                <form name="search_employee" action="SearchPageHandler" method="GET">
                <table cellpadding="20">               
                    <tr>
                        <td>
                        <h4 >Search Employee By:</h4>                                           
                        <input type="radio" id="idRadio" name="searchType" value="id">ID</input><br/>
                        <input type="radio" id="nameRadio" name="searchType" value="lName" checked="checked">Last Name</input><br/>
                        </td>
                    </tr>
                    
                    <tr>                       
                        <td><div class="searchBox"><input type="search" name="searchTerm" placeholder="Enter Search Term Here" required/><input type="submit" value="Go"/></div></td>
                    </tr>   
                    

                </table>              
                
                </form>
            <!--End of included content -->
        </div>  <!--End of header Div-->
<div class="top_bot"></div>

