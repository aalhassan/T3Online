var server= "http://172.19.31.60:8080";
var connectionMessage = null;
var playerX = null;
var playerO = null;
var currentPlayer = null;
var myId = null;
var winner = null;
var isTie = false;
var gameSession = null;
var $gameState = null;


var oppTurn ="<font color=\"red\">Waiting for opponent's move</font>";
var yourTurn = "<font color=\"blue\">Your move</font>";
var youWon = "<font color=\"green\">Game Over. You won!</font>";
var youLost = "<font color=\"red\">Game Over. You lost.</font>";
var youTied = "<font color=\"gray\">Game Over. It was a tie!</font>";





$(document).ready(function (){


	$("#startGame").bind("click",function(e) {
        initInterface();
		var uri = server+"/gameServer/moves/newGame";
		if(typeof(EventSource) !== "undefined") {
			var source = new EventSource(uri);
			source.onmessage = function(event){
			    processEventData(event.data);
                renderBoard();
				renderGameStatus();
			};

		} else {
			// Sorry! No server-sent events support..
			$("#XO_info").html("<font color=\"red\">Sorry! Game not supported on this browser</font>");
		}

	});

    $(".cell").bind("click",function(e) {
        var cellClicked = $(this).attr("id");

        var uri = server+"/gameServer/moves/"+gameSession+"/"+cellClicked;
        sendMessage(uri);
    });
});

/**Maps most global variables to server sent data
 *
 * @param data Data sent in server sent event
 */
function processEventData (data) {
    //alert(event.data);
    var gameState = $.parseXML(data);
    $gameState = $(gameState);
    connectionMessage = $gameState.find("connectionMessage").text();
    playerX = $gameState.find("playerPlayingX").text();
    playerO = $gameState.find("playerPlayingO").text();
    currentPlayer = $gameState.find("currentPlayer").text();
    winner = $gameState.find("winner").text();
    isTie = $gameState.find("gameEndedInTie").text();
    gameSession = $gameState.find("gameSession").text();
    myId = $("#email").text();
}


function sendMessage(uri) {
    $.ajax({
        type: "POST",
        url: uri,

        success: function (data) {
           //

        },
        fail: function (data) {
            //alert("Failed");
        }
    });
}

function initInterface() {
    $("#startGame").fadeOut("slow");
    renderText("", ".cell");
}

function renderGameStatus (){
    //If there is no winner yet
	if (winner == "" &&  isTie == "false") {
	    if(playerX != "" && playerO == "")
            renderText(connectionMessage, "#XO_info");
        else if (isPlayerX()){
            renderText(connectionMessage+"X", "#XO_info");
            renderText(playerO, "#opponentStatus")
        }else {
            renderText(connectionMessage + "O", "#XO_info");
            renderText(playerX, "#opponentStatus")
        }

        if (isCurrentPlayer()) {
            renderText(yourTurn, "#curStatus");
            highlight("#curStatus");
        }
        else if (playerO != "") {
            renderText(oppTurn, "#curStatus");
            highlight("#curStatus");
        }

	}
	//if there is a winner and it's me
	else if (winner == myId ) {
        renderText(youWon, "#curStatus");
        $gameState.find("winCells").each( function () {
            highlight("#"+$(this).text());
            $("#startGame").fadeIn("slow");
        });

    }
    //if there is a winner and it's  not me
    else if (winner != myId && winner != "") {
        renderText(youLost, "#curStatus");
        $gameState.find("winCells").each( function () {
                highlight("#"+$(this).text());
                $("#startGame").fadeIn("slow");
        });
    }
    //if it's a tie
    else if (isTie  == "true") {
        renderText(youTied, "#curStatus");
        $("#startGame").fadeIn("slow");
    }

}

function renderBoard () {

    $gameState.find("board").each( function (i) {
        $(this).children("item").each( function (j) {
            var curChar = String.fromCharCode($(this).text());
            renderText(curChar, "#"+i+j);
		})
	});

}


function renderText( data, target ) {

    $(target).html(data);
}



function highlight(target) {
	for(i=0;i<3;i++) {
		$(target).fadeTo('slow', 0.3).fadeTo('slow', 1.0);
	}

}

function isPlayerX() {

    return (myId == playerX);
}

function isCurrentPlayer() {
    return (currentPlayer == myId);
}



