<% request.setAttribute("pageTitle", "EnterPrise Java Projects"); %>
<%@include file="jsp/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<body>
<div id="main">
    <div align="right"><c:import url="jsp/login_fields.jsp"/></div>
    <!-- Top Section begins,  page's main content goes here -->
    <c:import url="jsp/add_player_ct.jsp" />
    <!-- Top Section Ends -->

    <!-- Mid Section begins -->
    <c:import url="jsp/mid_section.jsp" />
    <!-- Mid Section ends -->

    <!-- Bottom Section begins -->
    <c:import url="jsp/bot_section.jsp" />
    <!-- Bottom Section ends -->

</div> <!--End of Main Div-->
<!-- footer begins -->
<c:import url="jsp/footer.jsp" />
<!-- footer ends -->
</body>
</html>
