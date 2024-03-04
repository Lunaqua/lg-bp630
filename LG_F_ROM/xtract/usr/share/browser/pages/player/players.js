var isDebug = 0;
var isInfo = false;
var media_url = "";
var isRadio = false;
var tickTimer = null;
var seekTimer = null;
var seekIsRunning = false;
var showingControls = false;
var buffering = false;
var goPosition = 0;
var barTop = 610;	//sync with css
var barWidth = 800;	//sync with css
var barLeft = 120;	//sync with css
var bigJump = 5*60*1000;
var smallJump = 30*1000;
var maxJumpTime = 0;
var player = null;

var checkFirstPlaying = -1; // hkyoum add , -1:init , 0: start1 , 1: start2
var checkCtrlBarOn = 0; // hkyoum add
var gnIgnoreKeyCount = 0; // hkyoum add, 0: default, 10:ignore key while value > 0

var gnErrorCount = 0;

function runPlayer() {
	hideScreen();

//	toggleDebug(true); // hkyoum add
//	isInfo = true;
	
	registerEvents();
	parseMediaURL();

	document.write("<object id=\"mediaID\" width=\"1280\" height=\"720\" data=\"" + media_url + "\"  playcount=\"1\" type=\"application/x-netcast-av\"></object>");
	playMedia();
//	player.showControls(0);
//	player.play(1); // 101202 blocked by KwangRim Ahn's request
}

var currentTime;
var totalTime;

function registerEvents() {
	document.onkeydown = navigate;
/* blocked by yhk
	document.onclick = toggleControls;
	document.getElementById('controls').onmouseover = showJump;
	document.getElementById('controls').onmousemove = moveJump;
	document.getElementById('controls').onmouseout = removeJump;
	document.getElementById('controls').onclick = doRandomAccess;
	document.getElementById('controlPlay').onclick = doPlayPause;
	document.getElementById('qMenu_bt').onclick = showQmenu;
	document.getElementById('ratio_bt').onclick = showRatio;
	//document.getElementById('info_bt').onclick = showInfo;
	document.getElementById('netCast_bt').onclick = goNetCast;
	document.getElementById('return_bt').onclick = goBack;
	//document.getElementById('exit_bt').onclick = goExit;
*/ 
	currentTime = document.getElementById('currentTime');
	totalTime = document.getElementById('totalTime');
}

function parseMediaURL() {
	var url_string = new String(window.location);
	var index;

	index = url_string.indexOf("lg_media_url");
	if (index == -1) {
		alert('no url found');
	} else {
		var encoded_url = url_string.substring(index+13);
		media_url = Base64.decode(encoded_url);
//		media_url = url_string.substring(index+13);
		if (media_url.indexOf("?") > 0)
			parseBBCiPlayerMeta(media_url);
		//else // for test
		//	document.getElementById('title1').innerHTML = "ABCDEFGHIJKLMNOPQRSTUVYWZabcdefghijklmnopqrstuvywz";
		//	document.getElementById('title2').innerHTML = "abcdefghijklmnopqrstuvywzABCDEFGHIJKLMNOPQRSTUVYWZ";
		printDebug(media_url);
	}
}

function parseBBCiPlayerMeta(url_string) {
	var index;
	var next;
	var avtype = "";
	var avtitle = "";
	var avtitle2 = "";
	var bimg = document.getElementById('bimg');

	index = url_string.indexOf("AVTYPE");
	if (index > 0) {
		media_url = url_string.substring(0, index-1);
		url_string = url_string.substring(index);
		avtype = url_string.substring(7, 12);
	}

	if (avtype.toUpperCase() == "AUDIO") {
		var avbimg_url = "";
		index = url_string.indexOf("AVBIMG");
		isRadio = true;
		if (index > 0) {
			avbimg_url = url_string.substring(index+7);
			next = avbimg_url.indexOf("&");
			if (next > 0)
				avbimg_url = avbimg_url.substring(0, next);
			avbimg_url = decodeURIComponent(avbimg_url);
			bimg.innerHTML += "<img src=\"" + avbimg_url + "\">";
		}

		var aveimg_url = "";
		index = url_string.indexOf("AVEIMG");
		if (index > 0) {
			aveimg_url = url_string.substring(index+7);
			next = aveimg_url.indexOf("&");
			if (next > 0)
				aveimg_url = aveimg_url.substring(0, next);
			aveimg_url = decodeURIComponent(aveimg_url);
			bimg.innerHTML += "&nbsp;&nbsp;&nbsp;";
			bimg.innerHTML += "<img src=\"" + aveimg_url + "\">";
		}

		var avsimg_url = "";
		index = url_string.indexOf("AVSIMG");
		if (index > 0) {
			avsimg_url = url_string.substring(index+7);
			next = avsimg_url.indexOf("&");
			if (next > 0)
				avsimg_url = avsimg_url.substring(0, next);
			avsimg_url = decodeURIComponent(avsimg_url);
			//bimg.innerHTML += "<img src=\"" + avsimg_url + "\">";
		}
	}

	index = url_string.indexOf("AVTITLE");
	if (index > 0) {
		avtitle = url_string.substring(index+8);
		next = avtitle.indexOf("&");
		if (next > 0)
			avtitle = avtitle.substring(0, next);
		avtitle = decodeURIComponent(avtitle);
	}

	index = url_string.indexOf("AVTITLE2");
	if (index > 0) {
		avtitle2 = url_string.substring(index+9);
		next = avtitle2.indexOf("&");
		if (next > 0)
			avtitle2 = avtitle2.substring(0, next);
		avtitle2 = decodeURIComponent(avtitle2);
	}

	// max length : 76
	if (avtitle != "") {
		var title = document.getElementById('title');

		var len1 = avtitle.length;
		var len2 = avtitle2.length;
		var len3 = len1 + len2;

		if(len3 <= 80) { //  76 + 4
			title.innerHTML =  "&nbsp;" + avtitle;
			if (avtitle2 != "")
				title.innerHTML += " - " + avtitle2 + "&nbsp;";
		} else {
			var len1_sub = Math.round((len1*76)/len3);
			var len2_sub = Math.round((len2*76)/len3);
			
			var avtitle_sub="";
			var avtitle2_sub="";

			if(len1 > len1_sub) {
				avtitle_sub = avtitle.slice(0,len1_sub)+"...";
			} else {
				avtitle_sub = avtitle;
			}
			if(len2 > len2_sub) {
				avtitle2_sub = avtitle2.slice(0,len2_sub)+"...";
			} else {
				avtitle2_sub = avtitle2;
			}

			title.innerHTML =  "&nbsp;" + avtitle_sub;
			if (avtitle2_sub != "")
				title.innerHTML += " - " + avtitle2_sub + "&nbsp;";
		}
	}
	printDebug("AV TYPE: " + avtype);
	printDebug("Title: " + avtitle + " - " + avtitle2);
}

function Player(playerObject) {
	this.playerObj = playerObject;

	if (this.playerObj) {
		this.playerObj.onPlayStateChange = this.playStateChanged;
//		this.playerObj.onReadyStateChange = this.readyStateChanged; // hkyoum add 
//		this.playerObj.onError = this.errorStateChanged; // read player.error property
	}
}

function playMedia() {
	if (player == null) {
		var media = document.getElementById('mediaID');
		if (media) {
			player = new Player(media);
//			setTimeout('player.tick()', 500); // block by hkyoum
		}
		else {
			setTimeout(playMedia, 500);
		}
	}
}

function PlayState() {
	this.stopped = 0;
	this.playing = 1;
	this.paused = 2;
	this.connecting = 3;
	this.buffering = 4;
	this.finished = 5;
	this.error = 6;
	this.undefined = 30;
}

Player.prototype.playStateEnum = new PlayState();
Player.prototype.playerObj = null;
Player.prototype.isPlaying = true; // 101202 false->treu by KwangRim Ahn's request
Player.prototype.isStop = false;

//Player.prototype.lastMediaPosition = FormatTime(0, false);
//Player.prototype.lastMediaDuration = FormatTime(0, false);

Player.prototype.lastMediaPosition = 0;
Player.prototype.lastMediaDuration = 0;

Player.prototype.lastBufferPosition = 0;
Player.prototype.showControlsTimeout = null;

Player.prototype.play = function(ActFunc) {
	player.isPlaying = true;
	if (player.playerObj) {
		player.playerObj.play(1);
	}
	player.updatePlayPause();
}

Player.prototype.pause = function(ActFunc) {
	player.isPlaying = false;
	if (player.playerObj) {
		player.playerObj.play(0);
	}
	player.updatePlayPause();
}

Player.prototype.stop = function(only) {
	if (player.playerObj) {
		if(player.isStop == false) {
			player.isStop = true;
			clearInterval(tickTimer);  // hkyoum
			player.hideControls(); // hkyoum
			player.playerObj.stop();
		}
	}
	if(only == 0) {
		goBackFunction(VK_BACK);
	}	
}

Player.prototype.fastForward = function(stepSize) {
	if(checkFirstPlaying <= 0)
		return;

	if(seekIsRunning != true) {
		if((player.playerObj.playTime - player.playerObj.playPosition) <  smallJump) {
			return;
		}
	}

//	if (goPosition == 0) 
	if (seekIsRunning != true) // first seek
	{
		goPosition = player.playerObj.playPosition + stepSize;
	} else {
		goPosition += stepSize;
	}

	maxJumpTime = player.playerObj.playTime - smallJump;
	//printDebug("step: "+stepSize+", CP: "+player.playerObj.playPosition+", PP: "+pg+", GP: "+goPosition);
	if (goPosition >= maxJumpTime) {
		goPosition = maxJumpTime; 
	}
//	printDebug("goPosition="+goPosition);

	trySeek();
}

Player.prototype.fastBackward = function(stepSize) {
	if(checkFirstPlaying <= 0)
		return;

	if(player.playerObj.playPosition < (1*1000)) {
		return;
	}

//	if (goPosition == 0) 
	if(seekIsRunning != true) // first seek
	{
		goPosition = player.playerObj.playPosition - stepSize;
	} else {
		goPosition -= stepSize;
	}

	//printDebug("step: "+stepSize+", CP: "+player.playerObj.playPosition+", PP: "+pg+", GP: "+goPosition);
	if (goPosition < 0) {
		goPosition = 0;
	}
	trySeek();
}

function trySeek() {
	seekIsRunning = true;
	if (seekTimer != null)
		clearTimeout(seekTimer);
	
	updateArrow(goPosition);

	gnIgnoreKeyCount = 10;

	seekTimer = setTimeout('doSeek(1)', 1000);
}

function doSeek(flag) {
	if(flag == 1) {
		if (buffering != true) {
			player.showControls(5000); // hkyoum
		}
		player.playerObj.seek(goPosition); 
		printDebug("seek CP: "+player.playerObj.playPosition+", GP: "+goPosition);
/* do not block seek during buffering
		if (buffering != true) {
			player.showControls(5000); // hkyoum
			player.playerObj.seek(goPosition); 
			printDebug("seek CP: "+player.playerObj.playPosition+", GP: "+goPosition);
		} 
		else {
			updateArrow(player.lastMediaPosition);
		}
*/
	}
	gnIgnoreKeyCount = 10;
	seekIsRunning = false; 
/* 101124, do not send play cmd by new plug-in
	if(player.isPlaying != true) { // pause state
		player.play(1);
	}
*/
	goPosition = 0;
}
/*
function releaseSeek() {
	if(seekIsRunning == true) {
		if (seekTimer != null)
			clearTimeout(seekTimer);	
		
		updateArrow(player.lastMediaPosition);
		doSeek(0);
	}
}
*/
function updateArrow(position) {
//	var playerInfo = player.playerObj.mediaPlayInfo();
	var duration = player.playerObj.playTime; // hkyoum
	
	if (position < 0) position = 0;
	var percent = (duration == 0) ? 0 : ((position / duration) * 100);
	if (percent > 100) percent = 100;
	var dragObj = document.getElementById('dragDiv');
	if (dragObj) dragObj.style.left = percent + '%';

//	printDebug("updateArrow: position"+ position+"/percent:"+percent);

}
/* org block by hkyoum
function updateJump(position) {
	var playerInfo = player.playerObj.mediaPlayInfo();
	if (position < 0)
		position = 0;
	var percent = (playerInfo.duration == 0) ? 0 : ((position / playerInfo.duration) * 100);
	if (percent > 100)
		percent = 100;
	var jump = document.getElementById('jumpover');
	if (jump)
		jump.style.left = percent + '%';
}

Player.prototype.togglePlayPause = function() {
	if (player.isPlaying)
		player.pause();
	else
		player.play();
}
*/
Player.prototype.updatePlayPause = function() {

	var controlPlay = document.getElementById('controlPlay');
	if (controlPlay) {
		if (player.isPlaying)
			controlPlay.className = 'play';
		else
			controlPlay.className = 'pause';
	}
}

Player.prototype.showControls = function(ms) {
	showingControls = true;

	if (player.showControlsTimeout != null) {
		clearTimeout(player.showControlsTimeout);
	}

	var controlsDiv = document.getElementById('controls');
	var footerDiv = document.getElementById('footer');
//	var titleDiv = document.getElementById('title');

//	if (controlsDiv && footerDiv && titleDiv) 
	if (controlsDiv && footerDiv) 
	{
		controlsDiv.style.display = 'block';
		footerDiv.style.display = 'block';
//		titleDiv.style.display = 'block';
	}

	if (isRadio == false && (ms > 0)) {
		player.showControlsTimeout = setTimeout(player.hideControls, ms);
	}
}

function hideScreen() {
	var controlsDiv = document.getElementById('controls');
	var footerDiv = document.getElementById('footer');
//	var titleDiv = document.getElementById('title');

//	if (controlsDiv && footerDiv && titleDiv) 
	if (controlsDiv && footerDiv) 
	{
		controlsDiv.style.display = 'none';
		footerDiv.style.display = 'none';
//		titleDiv.style.display = 'none';
	}
}

Player.prototype.hideControls = function() {
	
	if((this.isStop == false)&&(isRadio == true))
		return;

	if( checkCtrlBarOn == 1) { // hkyoum add
		return; // hkyoum for test...
	}
	showingControls = false;

	if (player.showControlsTimeout != null) {
		clearTimeout(player.showControlsTimeout);
	}
	
	hideScreen();
}

Player.prototype.currentPlayState = function() {
	if (player.playerObj) {
		return player.playerObj.playState;
	}

	return player.playStateEnum.undefined;
}

/* org
Player.prototype.currentMediaPosition = function() {
	if (player.playerObj) {
		return player.playerObj.playPosition / 1000;
	}
	return 0;
}

Player.prototype.currentMediaPositionFormatted = function() {
	var position = player.currentMediaPosition();
	var showHour = false;
	if (position >= 3600) {
		showHour = true;
	}
	return FormatTime(position, showHour);
}


Player.prototype.currentMediaDuration = function() {
	if (player.playerObj) {
		return player.playerObj.playTime / 1000;
	}

	return 0;
}

Player.prototype.currentMediaDurationFormatted = function() {
	var duration = player.currentMediaDuration();
	var showHour = false;
	if (duration >= 3600) {
		showHour = true;
	}
	return FormatTime(duration, showHour);
}
*/

Player.prototype.TimeFormatted = function(currTime) {
	var position = currTime / 1000;
	var showHour = true;
	/*
	var showHour = false;
	if (position >= 3600) {
		showHour = true;
	}
	*/
	return FormatTime(position, showHour);
}

Player.prototype.playStateChanged = function() {
	try {		
		var _playState = player.currentPlayState();
//		var currentTime = document.getElementById('currentTime');
//		var totalTime = document.getElementById('totalTime');

		printDebug("playStateChanged: "+ getPlayStateStr(_playState));

		switch (_playState) {
			case player.playStateEnum.buffering:
				if(checkFirstPlaying > 0) { // hkyoum add
					buffering = true;
				}
			case player.playStateEnum.connecting:
				currentTime.innerHTML = "Buffering...";
				totalTime.innerHTML = ' ';
				buffering = true;

				if(checkFirstPlaying > 0) { // hkyoum add
					currentTime.innerHTML = "Loading";
					totalTime.innerHTML = '...';
	
					document.getElementById('bufferBar').style.width = document.getElementById('progressBar').style.width;
					player.showControls(0);
				}

				break;
			case player.playStateEnum.error:
				gnErrorCount = 1;
				currentTime.innerHTML = "Error";
				totalTime.innerHTML = '';
				playerLoadingAni(0);
				player.showControls(0);
				break;
			case player.playStateEnum.playing:
				if(checkFirstPlaying == -1) { // hkyoum add
	//				playerLoadingAni(0);
					player.tick();
	//				setTimeout('player.tick()', 500); // hkyoum
	
	//				player.showControls(5000); // hkyoum
	
					/* 100902, support FF/REW by DQA request
					*/	
/*
					var nInterval = parseInt((player.playerObj.playTime)/10);
					bigJump = nInterval; // maxJumpTime = player.playerObj.playTime - smallJump;
					printDebug("bigJump interval: "+ bigJump + "maxJumpTime : " + maxJumpTime);
*/	
					checkFirstPlaying = 0;
				}
				if (buffering == true) {
					buffering = false;
					player.play(0); // test DQMS 01073
					player.updateTimeInfo();
					if (showingControls == true) {
					//	player.hideControls();
						player.showControls(5000);
					}
				}
				break;
			case player.playStateEnum.finished:
				player.stop(0);
				break;
		}

	} catch (err) {
	}
}

Player.prototype.readyStateChanged = function(readyState) { // hkyoum
	try {
		printDebug("readyState : "+readyState);
	} catch (err) {
	}
}

/*
Player.prototype.errorStateChanged = function() { // hkyoum
	try {
		var errorState = player.playerObj.error;
		var errorDesp = "";
		
		switch(errorState) {
			case 0 : errorDesp = "A/V format not supported"; break;	
			case 1 : errorDesp = "Cannot connect to server or connection lost"; break;	
			case 2 : errorDesp = "Unidentified error";	 break;	
			case 1000 : errorDesp = "File is not found";	break;	
			case 1001 : errorDesp = "Invalid protocol";	break;	
			case 1002 : errorDesp = "DRM failure";	break;	
			case 1003 : errorDesp = "Play list is empty";	break;	
			case 1004 : errorDesp = "Unrecognized play list";	break;	
			case 1005 : errorDesp = "Invalid ASX format";	break;	
			case 1006 : errorDesp = "Error in downloading play list";	break;	
			case 1007 : errorDesp = "Out of memory";	break;	
			case 1008 : errorDesp = "Invalid URL list format";	break;	
			case 1009 : errorDesp = "Not playable in play list";	break;	
			case 1100 : errorDesp = "Unidentified WM-DRM error";	break;	
			case 1101 : errorDesp = "Incorrect license in local license store";	break;	
			case 1102 : errorDesp = "Fail in receiving correct license from server";	break;	
			case 1103 : errorDesp = "Stored license is expired";	break;	
		}
		printDebug("errorState : " + errorState + errorDesp);

		if (player.playerObj) {
			if(player.isStop == false) {
				player.isStop = true;
				clearInterval(tickTimer);  // hkyoum
				player.hideControls(); // hkyoum

				document.getElementById('error_popup').style.visibility = 'visible';

			//	document.getElementById('errorTitle').innerHTML += "errorState"+errorState+errorDesp;
		
				var controlsDiv = document.getElementById('controls');
				var footerDiv = document.getElementById('footer');
			
				if (controlsDiv && footerDiv) 
				{
					controlsDiv.style.display = 'none';
					footerDiv.style.display = 'block';
				}

				setTimeout('player.playerObj.stop()', 100);
			}
		}		
//		setTimeout('goBackFunction(0)', 5000);
		setTimeout('window.NetCastBack()', 5000);

//		location.href = "file://usr/local/browser/pages/netError_player/netError_webkit_en.html?e=1&c=UTF-8&n=DEU&l=en";
	} catch (err) {
	}
}
*/

Player.prototype.tick = function() {
	clearInterval(tickTimer);
	if (this.playerObj)
		this.updateTimeInfo();
	tickTimer = setTimeout('player.tick()', 300); // org : 500 
}

var prePositionPercent = 0;

Player.prototype.updateTimeInfo = function() {
	var checkSamePosition = 0;
	try {
//		var currentTime = document.getElementById('currentTime');
//		var totalTime = document.getElementById('totalTime');

		var duration ;//= this.lastMediaDuration;
		var position ;//= this.lastMediaPosition;
		var _playState = player.playerObj.playState;
		var playerInfo = player.playerObj.mediaPlayInfo();
	//*
		if (isInfo == true) 
		{
			var mediaInfo = document.getElementById('mediaInfo');
			var media = player.playerObj;
//			mediaInfo.innerHTML  = "playTime: " + player.playerObj.playTime + "<br>"; // duration
//			mediaInfo.innerHTML += "playPosition: " + player.playerObj.playPosition + "<br>";
			mediaInfo.innerHTML = "currentPosition: " + playerInfo.currentPosition + "<br/>";
			mediaInfo.innerHTML += "duration: " + playerInfo.duration + "<br/>";
			mediaInfo.innerHTML += "bufBegin: " + playerInfo.bufBegin + "<br/>";
			mediaInfo.innerHTML += "bufEnd: " + playerInfo.bufEnd + "<br/>";
			mediaInfo.innerHTML += "bufRemain: " + playerInfo.bufRemain + "<br/>";
			mediaInfo.innerHTML += "bitrateInstant: " + playerInfo.bitrateInstant + "<br/>";
			mediaInfo.innerHTML += "bitrateTarget: " + playerInfo.bitrateTarget + "<br/>";
			mediaInfo.innerHTML += "playState: " + getPlayStateStr(player.playerObj.playState) + "<br/>";
			mediaInfo.innerHTML += "gnIgnoreKeyCount: " + gnIgnoreKeyCount + "<br/>";			
			mediaInfo.innerHTML += "bigJump interval: "+ bigJump + "<br/>";
//			mediaInfo.innerHTML += "readyState: " + getReadyStateStr(player.playerObj.readyState) + "<br/>";
		}
	//*/
		if (_playState == this.playStateEnum.playing) {			
			if(player.isPlaying != true) { // pause state, // adjust player status and icon
				player.play(1);
			}
			//duration = this.currentMediaDurationFormatted();
			//position = this.currentMediaPositionFormatted();
	
			//hkyoum
			if( this.lastMediaPosition == playerInfo.currentPosition) {
				checkSamePosition == 1;
			} else if( this.lastMediaDuration == playerInfo.duration) {
				checkSamePosition == 1;
			}
			this.lastMediaPosition = playerInfo.currentPosition;
			this.lastMediaDuration = playerInfo.duration;
	
			if(checkSamePosition == 0) {				
				duration = this.TimeFormatted(playerInfo.duration);
				position = this.TimeFormatted(playerInfo.currentPosition);

				if(checkFirstPlaying == 0) {
					if(playerInfo.currentPosition > 0)	{
						playerLoadingAni(0);
						player.showControls(5000); // hkyoum
	
						checkFirstPlaying = 1;

//						bigJump = parseInt((player.playerObj.playTime)/10);
//						printDebug("bigJump interval: "+ bigJump );
					}
				}

				// this.lastMediaPosition = position;
				// this.lastMediaDuration = duration;
	
				currentTime.innerHTML = position;
				totalTime.innerHTML = "/" + duration;
			}
		}
		else if (_playState == this.playStateEnum.paused) { // adjust player status and icon
			if(player.isPlaying == true) { // play state
				player.pause(1);
			}
		}

		var percent = (playerInfo.duration > 0) ? ((playerInfo.currentPosition / playerInfo.duration) * 100) : 0;

		if((prePositionPercent != percent)&& (percent <= 100)) {
			document.getElementById('progressBar').style.width = percent + '%';
//			if ((!seekIsRunning)&&(_playState == this.playStateEnum.playing)) 
			if ((!seekIsRunning)&&(_playState != this.playStateEnum.paused)) 
			{
	//			updateArrow(playerInfo.currentPosition); // org
				var dragObj = document.getElementById('dragDiv');
				if (dragObj) dragObj.style.left = percent + '%';
			}
	//			printDebug("percent: "+percent);
		}
		if (isInfo == true) {
			mediaInfo.innerHTML += "CurrentTimePercent: " + percent + "<br/>";
		}
		prePositionPercent = percent;

		if (playerInfo.bufRemain >= 0) {
			percent = ( ((playerInfo.currentPosition + playerInfo.bufRemain) / playerInfo.duration) * 100 );
		} else {
			percent = 100;
		}
		if (isInfo == true) {
			mediaInfo.innerHTML += "BufferPercent: " + percent + "<br/>";
		}		
//		if (percent > this.lastBufferPosition && percent <= 100) 
		if ((percent != this.lastBufferPosition) && (percent <= 100)) 
		{
			document.getElementById('bufferBar').style.width = percent + '%';
			this.lastBufferPosition = percent;
		}

		updateNetworkSignal(((playerInfo.bitrateTarget > 0) ? Math.floor(((playerInfo.bitrateInstant * 100) / playerInfo.bitrateTarget)/ 20) : 0));
		
		if(gnIgnoreKeyCount > 0) {
			gnIgnoreKeyCount--;
		}
	} catch (err) {
	}
}

function updateNetworkSignal(value) {
	value = (value <= 0) ? 1 : (value > 5) ? 5 : value;	
	document.getElementById('networkDiv').className = 'network l' + value;
}

function FormatTime(timeString, printHour)
{
	var hour = parseInt(timeString / 3600).toString();
	timeString = timeString % 3600;
	var min = parseInt(timeString / 60).toString();
	timeString = timeString % 60;
	var sec = parseInt(timeString).toString();

	if(hour < 10)
		hour = '0' + hour;
	if(min < 10)
		min = '0' + min;
	if(sec < 10)
		sec = '0' + sec;

	if(printHour)
		return hour + ":" + min + ":" + sec;
	else
		return min + ":" + sec;
}

function goBackFunction(keycode) {
	// DQMS 1011-02029 , player-> error page repeat problem

	printDebug("goBackFunction: 1");


	if(gnErrorCount == 1) {
		if((player.lastMediaDuration <= 0)&&(player.lastMediaPosition <= 0)) { // fail : start playback
	printDebug("goBackFunction: 2");
			window.location.replace("file:///usr/local/browser/pages/netError/netError_webkit_en.html?e=1&c=UTF-8&g=EU&l=en-gb");
			//
		}
		else {
	printDebug("goBackFunction: 3");
			window.history.go(-2);
		}		
	} else 
	{
	printDebug("goBackFunction: 4");
		window.history.go(-2);
	}
//	window.location.reload();
	
// for againg test
//	window.history.go(-3);

	/*
	   if (navigator.userAgent.search(/LG Browser/) > -1) {
	   window.NetCastReturn(keycode);
	   }
	   */
}
/*
var goNetCastHomeFunction = function() {
	var obj = document.getElementById('netCast_bt');
	if (obj) 
		obj.className += ' over';
	alert("Go NetCast Home");
}
*/
function getKeyCode(e) {
	var keycode;
	if (window.event)
		keycode = e.keyCode;
	else if (e.which)
		keycode = e.which;

	return keycode;
}

var debugCheckStr="";
var debugModeOn = 0;

function navigate(e) {
	var keycode = getKeyCode(e);
	
	printDebug(keycode);

//	goBackFunction(0);
	
	switch (keycode) {
		case VK_BACK:		//hkyoum
		case VK_BACK_SPACE: 	//hkyoum
		case VK_STOP:
			if (player)
				player.stop(0);
			else
				goBackFunction(VK_BACK);
		return;	
		case VK_0 :
			updateDebug("");
			if(debugModeOn != 1)
				debugCheckStr+="0";
		return;	
		case VK_2:
			if(debugModeOn != 1) {
				debugCheckStr+="2";
				if(debugCheckStr == "0852") {
					debugModeOn = 1;
				} else {
					debugCheckStr="";
				}
			} else {
				location.href = "file://../../browser/pages/testpage.html";
			}
		return;	
		case VK_4:
			if(debugModeOn) {
				isInfo = false;
				toggleDebug(false);
			}
		return;	
		case VK_5:
			if(debugModeOn != 1) {
				debugCheckStr+="5";
			} else {
				isInfo = true;
				toggleDebug(true);
			}
		return;	
		case VK_6:
			if(debugModeOn) {
				if(checkCtrlBarOn == 1) checkCtrlBarOn = 0;
				else {
					player.showControls(5000);
					checkCtrlBarOn = 1;
				}
			}
		return;	
		case VK_8:
			if(debugModeOn != 1)
				debugCheckStr+="8";
		return;	
		case VK_UP:
		case VK_DOWN:
		case VK_RED:
		case VK_BLUE:
		case VK_GREEN:
			debugCheckStr="";
			debugModeOn = 0;
		return;	
		case VK_YELLOW:
		return;	
		default:
	}
	if(checkFirstPlaying <= 0) {
		return;
	}
	if (player == null) {
		return;
	}
	if(player.isStop == true) {
		return;
	}
	switch (keycode) {
		case VK_RIGHT:
			if(buffering != true) {
				player.showControls(5000);
			}
			player.fastForward(smallJump);
			break;
		case VK_LEFT:
			if(buffering != true) {
				player.showControls(5000);
			}		
			player.fastBackward(smallJump);
			break;
/* 100811, DQA request, Do not support FF,REW with Skip func. 
** 100902, DQA Request, support FF,REW again
*/
		case VK_FAST_FWD:
			if(buffering != true) {
				player.showControls(5000);
			}
			bigJump = parseInt((player.playerObj.playTime)/10);
			player.fastForward(bigJump);
			break;
		case VK_REWIND:
			if(buffering != true) {
				player.showControls(5000);
			}		
			bigJump = parseInt((player.playerObj.playTime)/10);
			player.fastBackward(bigJump);
			break;

		case VK_ENTER:
/*
			if (showingControls == true)
				player.hideControls();
			else
*/
//				player.showControls(5000);
			if(buffering != true) {
				player.showControls(5000);
			}
			break;
/* org
		case VK_BACK:
		case VK_BACK_SPACE:
			goBackFunction(keycode);
			break;
*/

		case VK_PLAY:
			if(gnIgnoreKeyCount > 0) {
				return true;
			}
			if(buffering != true) {
				player.showControls(5000);
				gnIgnoreKeyCount = 3; // 101104 , DQMS, 1010-00832, ignore play key for 1 sec after pressing play/pause key.
				player.play(1);
			}		
			break;
		case VK_PAUSE:
			if(gnIgnoreKeyCount > 0) {
				return true;
			}
			if(buffering != true) {
				player.showControls(5000);
				gnIgnoreKeyCount = 3; // 101104 , DQMS, 1010-00832, ignore play key for 1 sec after pressing play/pause key.
				player.pause(1);
			}		
			break;
	}

	return true;
}

function playerLoadingAni(flag) {
	if(flag == 1) { //show
		var loadingAniDiv = document.getElementById('loadingAni');
		loadingAniDiv.style.display = 'block';
	} else { // hidden
		var loadingAniDiv = document.getElementById('loadingAni');
		loadingAniDiv.style.display = 'none';
	}
}
/* org block by yhk
function doPlayPause() {
	if (player) {
		if (player.isPlaying == true)
			player.pause();
		else
			player.play();
	}
}

function showQmenu() {
	window.NetCastLaunchQMENU();
}

function showRatio() {
	window.NetCastLaunchRATIO();
}

function showInfo() {
	isInfo = !isInfo;
	toggleInfo(isInfo);
}

function goBack() {
	if (player)
		player.stop();
}

function goNetCast() {
	window.NetCastBack();			
}

function goExit() {
	window.NetCastExit();
}

function showJump() {
	var jump = document.getElementById('jumpover');
	jump.style.display = 'block';
}

function moveJump(e) {
	var evt = e || window.event;
	var point = evt.clientX;

	//updateDebug(point);
	if ((point < barLeft) || (point > barLeft+barWidth)) {
		removeJump();
		return;
	}
	point = point - barLeft;

	var duration;
	if (player.playerObj)
		duration = player.playerObj.playTime;

	if (duration > 0) {
		var seekPoint = (point * duration) / barWidth;
		seekPoint = Math.round(seekPoint);
		if (seekPoint > 0)
			updateJump(seekPoint);
	}
}

function removeJump() {
	var jump = document.getElementById('jumpover');
	jump.style.display = 'none';
}

function doRandomAccess(e) {
	var evt = e || window.event;
	var point = evt.clientX;

	if ((point < barLeft) || (point > barLeft+barWidth)) {
		removeJump();
		return;
	}
	point = point - barLeft;

	var duration;
	if (player.playerObj)
		duration = player.playerObj.playTime;

	if (duration > 0) {
		var seekPoint = (point * duration) / barWidth;
		goPosition = Math.round(seekPoint); 
		if (seekPoint > 0)
			trySeek();
	}
}

function toggleControls(e) {
	if (showingControls == false) 
		player.showControls(0);
	else {
		var evt = e || window.event;
		//printDebug("Y: " + evt.clientY);
		if (evt.clientY < barTop)
			player.hideControls();
	}
}

function toggleInfo(param) {
	var info = document.getElementById('infoBox');
	var titleDiv = document.getElementById('title');

	if (param == true) {
		info.style.display = 'block';
		titleDiv.style.display = 'block';
	} else {
		info.style.display = 'none';
		titleDiv.style.display = 'none';
	}

}
*/

function toggleDebug(param) {
	var debug = document.getElementById('debug');
	var mediaInfo = document.getElementById('mediaInfo');

	if (param == true) {
		debug.style.display = 'block';
		mediaInfo.style.display = 'block';
	} else {
		debug.style.display = 'none';
		mediaInfo.style.display = 'none';
		debug.innerHTML = "";
	}
	isDebug = 0;
}

function getPlayStateStr(st) {
	switch(st) {
		case 0 :
			return "0 - Stopped";
		case 1 :
			return "1 - Playing";
		case 2 :
			return "2 - Paused";
		case 3 :
			return "3 - Connecting";
		case 4 :
			return "4 - Buffering";
		case 5 :
			return "5 - Finished";
		case 6 :
			return "6 - Error";
		default :
			return "";
	}
	return null;
}

function getReadyStateStr(rs) {
	switch(rs) {
		case 0 :
			return "0 - Not set";
		case 1 :
			return "1 - Loading";
		case 3 :
			return "3 - Loaded data to play";
		case 4 :
			return "4 - Downloaded all";
		default :
			return "";
	}
	return null;
}

var gnDebugCount = 0;
function printDebug(msg) {
	var debug = document.getElementById('debug');

	if(gnDebugCount == 21) {
		debug.innerHTML = "";
		gnDebugCount = 0;
	}

	debug.innerHTML += msg + "<br>";
	
	gnDebugCount++;
}

function updateDebug(msg) {
	var debug = document.getElementById('debug');
	debug.innerHTML = msg;
}
