<% request.setAttribute("pageTitle", "EnterPrise Java Projects"); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <c:import url="head.jsp" />
    <body>
        <div id="main">
        <!-- Top Section begins,  page's main content goes here -->
            <c:import url="content_template.jsp" />
        <!-- Top Section Ends -->
        
        <!-- Mid Section begins -->
            <c:import url="mid_section.jsp" />
        <!-- Mid Section ends -->
        
        <!-- Bottom Section begins -->
            <c:import url="bot_section.jsp" />
        <!-- Bottom Section ends -->
        
        </div> <!--End of Main Div-->
    <!-- footer begins -->
        <c:import url="footer.jsp" />
    <!-- footer ends -->
    </body>
<!-- Note: opening html tag is in head.jsp -->
</html>
