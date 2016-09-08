<div class="top_top"></div>
        <div id="header" class="top_content">
            <c:import url="jsp/top_nav.jsp" />   
            <!--Beginning of included content -->
			<% session.setAttribute("requestPage", "file_analyzer.jsp"); %>
            <h1 class="question"> File Analyzer Page</h1>
            <p>Start by uploading the file to be analyzed below</p>
                <form name="file_analyzer" action="AnalyzerServlet" method="POST"  enctype="multipart/form-data">
                <table >
                    <tr><td><input type="file" name="inputFile" value="" size="30" required/></td></tr>
                </table>
                <input type="submit" value="Upload"/>
                </form> <br/>           
            <!--End of included content -->
        </div>  <!--End of header Div-->
<div class="top_bot"></div>

