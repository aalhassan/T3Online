$(document).ready(function (){
	var playerConnecting = "<font color=\"red\">Connecting to game hub...Please do not close browser throughout game session</font>";
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
    var gameInProgress = false;
    var currentPlayer = false;

	$("#startGame").bind("click",function(e) {
		var uri = "http://localhost:8080/gameServer/moves/newGame";
		startGame(uri);
		$("#XO_info").html(playerConnecting);
		highlight("#XO_info");
		$("#cell01").text(X);
		highlight("#cell01");

	});

    $(".cell").bind("click",function(e) {
        if (!gameInProgress &&  !currentPlayer) {
            return;
        }
        var cellClicked = $(this).getAttribute("id");

        var uri = "http://localhost:8080/gameServer/moves/"+cellClicked;
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
        url: uri,
        success: function (data) {
           //

        },
        fail: function (data) {
            //alert("Failed");
        }
    });
}

function renderData( event, target ) {
	var output = event.data
	$(target).html("<font color=\"blue\">"+output+"</font>").fadeIn();
}

function highlight(target) {
	for(i=0;i<3;i++) {
		$(target).fadeTo('slow', 0.3).fadeTo('slow', 1.0);
	}

}

