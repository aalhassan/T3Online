<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="js/gameBoard.js"></script>
<div class="top_top"></div>
        <div id="header" class="top_content">
            <c:import url="jsp/top_nav.jsp"/>
            <!--Beginning of included content -->
            <p colspan="3" class="statusInfo" id= "XO_info"></p>
                    <table id="gameBoard">
                        <tr class="statusRow"><td  class="statusLabel">Opponent:</td><td colspan="2" class="statusInfo" id= "opponentStatus">N/A</td></tr>
                        <tr class="statusRow"><td  class="statusLabel">Game Status:</td><td colspan="2" class="statusInfo" id= "curStatus">N/A</td></tr>
                        <tr class="boardRow"><td id="00" class="cell"></td><td id="01" class="cell"></td><td id="02" class="cell"></td></tr>
                        <tr class="boardRow"><td id="10" class="cell"></td><td id="11" class="cell"></td><td id="12" class="cell"></td></tr>
                        <tr class="boardRow"><td id="20" class="cell"></td><td id="21" class="cell"></td><td id="22" class="cell"></td></tr>
                        <tr><td colspan="3" id= "startButton"><button type="submit" href="" id="startGame">Start New Game</button></td></tr>

                    </table>





            <!--End of included content -->
        </div>  <!--End of header Div-->
        
<div class="top_bot"></div>