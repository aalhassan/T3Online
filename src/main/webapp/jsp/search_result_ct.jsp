<div class="top_top"></div>
        <div id="header" class="top_content">
            <c:import url="jsp/top_nav.jsp" />   
            <!--Beginning of included content -->
                <h2 class="question">Search Results</h2> <br/>
                <c:choose>
                    <c:when test="${searchResult.found == true}">
                        <table border="1">
                            <tr class="table_header"><td>ID</td><td>FirstName</td><td>LastName</td></tr> 
                            <c:forEach var="foundEmployee"  items="${searchResult.foundEmployees}" >
                                <tr><td>${foundEmployee.employeeId}</td><td>${foundEmployee.firstName}</td><td>${foundEmployee.lastName}</td></tr>
                            </c:forEach>
                        </table>
                    </c:when >
                    <c:otherwise>
                        <p>No employer matches your search criteria</p>
                    </c:otherwise>
                </c:choose><br/>
                    
                <a class="button" href="/java112/SearchPageServlet"/>Search Again</a>
            <!--End of included content -->
        </div>  <!--End of header Div-->
<div class="top_bot"></div>

