<div class="top_top"></div>
        <div id="header" class="top_content">
            <c:import url="jsp/top_nav.jsp" />   
            <!--Beginning of included content -->
			<% session.setAttribute("requestPage", "analyzer_results.jsp"); %>
            <h1 class="question">Analyzer Results</h1>
                <form name="analyzer_results" action="AnalyzerServlet" method="GET"  enctype="multipart/form-data">
                <table>
                <!--<tr><td><input type="hidden" name="runAnalyzer" value="runAnalyzer"  required/></td></tr>-->
                </table>
                 <h3>${analyzerResp}</h3>
                </form> <br/> <br/>  
				<a class="button" href="/java112/file_analyzer.jsp"/>Back to upload page</a>
            <!--End of included content -->
        </div>  <!--End of header Div-->
<div class="top_bot"></div>
