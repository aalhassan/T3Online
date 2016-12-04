<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="top_top"></div>
<div id="header" class="top_content">
    <c:import url="jsp/top_nav.jsp" />
    <!--Beginning of included content -->
    <h2 class="question">Your Game Records</h2> <br/>
    <c:choose>
        <c:when test="${requestScope.foundRecords == true}">
            <table border="1" cellpadding="20px">
                <tr class="table_header"><td>Result</td><td>Opponent</td></tr>
                <c:forEach var="foundRecord"  items="${requestScope.gameRecords}" >
                    <tr><td>${foundRecord.result}</td><td>${foundRecord.opponentId}</td></tr>
                </c:forEach>
            </table>
        </c:when >
        <c:otherwise>
            <p>No game record(s) found.</p>
        </c:otherwise>
    </c:choose><br/>

    <table>
        <c:forEach var="error"  items="${requestScope.errors}" >
            <tr><td>${error.key}: </td><td>${error.value}</td></tr>
        </c:forEach>
    </table>

    <a class="button" href="/myGames"/>Search Again</a>
    <!--End of included content -->
</div>  <!--End of header Div-->
<div class="top_bot"></div>
