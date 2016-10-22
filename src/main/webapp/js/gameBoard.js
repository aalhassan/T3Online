var server= "http://192.168.75.130:8080";
var connectionMessage = null;
var playerX = null;
var playerO = null;
var currentPlayer = null;
var myId = null;
var winner = null;
var isTie = false;
var gameSession = null;

var $gameState = null;

var youWon = "<font color=\"green\">Game Over. You won!</font>";
var youLost = "<font color=\"red\">Game Over. You lost!</font>";
var youTied = "<font color=\"gray\">Game Over. It was a tie!</font>";


$(document).ready(function (){


	$("#startGame").bind("click",function(e) {
		var uri = server+"/gameServer/moves/newGame";

		if(typeof(EventSource) !== "undefined") {
			var source = new EventSource(uri);
			source.onmessage = function(event){
			    alert(event.data);
				var gameState = $.parseXML(event.data);
                $gameState = $(gameState);
                connectionMessage = $gameState.find("connectionMessage").text();
                playerX = $gameState.find("playerPlayingX").text();
                playerO = $gameState.find("playerPlayingO").text();
                currentPlayer = $gameState.find("currentPlayer").text();
                winner = $gameState.find("winner").text();
                isTie = $gameState.find("gameEndedInTie").text();
                gameSession = $gameState.find("gameSession").text();
                myId = $("#email").text();

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

function startGame (uri) {
	$.ajax({
		url: uri,
		success: function (data) {
			renderData(data, "#statusInfo")
			$("#startGame").fadeOut();

		},
		fail: function (data) {
			alert("Failed");
		}
	});
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

function renderGameStatus (){

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
            renderText("Your Move", "#curStatus");
            highlight("#curStatus");
        }
        else {
            renderText("Waiting for opponent's move", "#curStatus");
            highlight("#curStatus");
        }

	}
	else if (isTie == "false" && winner == myId ) {
        renderText(youWon, "#curStatus");
        $gameState.find("winCells").each( function (i, row) {
            row.find("item").each( function (j, cell) {
                highlight("#"+i+j);
            })
        });

    }
    else if (isTie == "false" && winner != myId && winner != "") {
        renderText(youLost, "#curStatus");
        $gameState.children("winCells").each( function (i, row) {
            row.children("item").each( function (j, cell) {
                highlight("#"+i+j);
            })
        });
    } else if (isTie  == "true") {
        renderText(youTied, "#curStatus");
    }

}

function renderBoard () {

    $gameState.children("board").each( function (i) {
        alert(this.text());
        row.children("item").each( function (j) {
            alert(this.text());
			if (cell.text() == "88")
				renderText("X", "#"+i+j);
			else if (cell.text() == "79")
                renderText("O", "#"+i+j);
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



/*var playerConnecting = "<font color=\"red\">Connecting to game hub...Please do not close browser throughout game session</font>";
 var playerWaiting = "<font color=\"black\">Player matchmaking in progress...</font>";
 var playerFound = "<font color=\"green\">Second player joined. Allocating X/O...</font>";
 var playingO = "<font color=\"green\">Game in progress. You are playing O</font>";
 var playingX = "<font color=\"green\">Game in progress. You are playing X</font>";
 var oppTurn= "<font color=\"red\">Waiting for opponent's move</font>";
 var yourTurn= "<font color=\"green\">Your move</font>";
 var youWon = "<font color=\"green\">Game Over. You won!</font>";
 var youLost = "<font color=\"red\">Game Over. You lost!</font>";
 var youTied = "<font color=\"gray\">Game Over. It was a tie!</font>";
 var X = "X";
 var O = "O";
 var gameInProgress = false;
 var currentPlayer = false;*/

