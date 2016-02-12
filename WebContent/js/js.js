<<<<<<< HEAD
var video = document.getElementById('video');
var canvas = document.getElementById('canvas');
var ctx = canvas.getContext('2d');
var localMediaStream = null;



//カメラ使えるかチェック
var hasGetUserMedia = function() {
    return (navigator.getUserMedia || navigator.webkitGetUserMedia ||
        navigator.mozGetUserMedia || navigator.msGetUserMedia);
}
 
//エラー
var onFailSoHard = function(e) {
    console.log('エラー!', e);
};
 
//カメラ画像キャプチャ
var snapshot = function() {
    if (localMediaStream) {
        ctx.drawImage(video, 0, 0, canvas.width, canvas.height);
        document.getElementById('img01').src = canvas.toDataURL('image/jpeg');
    }
    click++;
    click_count2++;
	$("#wrapper .text_box").text( click_count2 + "回キャプチャされました");
	
}

var click_count = 0; 
var click_count2 = 0; 
var click=0;




if (hasGetUserMedia()) {
    console.log("カメラ OK");
} else {
    alert("未対応ブラウザです。");
}
 
window.URL = window.URL || window.webkitURL;
navigator.getUserMedia  = navigator.getUserMedia || navigator.webkitGetUserMedia ||
                          navigator.mozGetUserMedia || navigator.msGetUserMedia;
navigator.getUserMedia({video: true}, function(stream) {
  video.src = window.URL.createObjectURL(stream);
  localMediaStream = stream;
}, onFailSoHard);
//ボタンイベント
$("#capture").click(function() {
	if(click_count2<5){
	snapshot();
    document.getElementById('capture_images').style.visibility = "visible";

	//一回5回までキャプチャOKで全15回までクリックイベント出来るよ
	}else if(click_count2==5 && click!=15){
		click_count2=0;
		snapshot();
	    document.getElementById('capture_images').style.visibility = "visible";
		
	}else{
		$("#wrapper .text_box").text( click_count2 + "回キャプチャされたので競技参加終了です");
	}
	});
$("#stop").click(function() {
    localMediaStream.stop();
});
$("video").click(function() {
    snapshot();
    document.getElementById('capture_images').style.visibility = "visible";
});




function kakunin(){
	
	//クリックしたらカウントしていく
	click_count++;
	//保存されたらクリック数加算する
	if(click_count==1){
		click_count2=5;
		click=5;
	}
	if(click_count==2){
		click_count2=5;
		click=10;
	}
	if(click_count==3){
		click_count2=5;
		click=15;
	}

	
	$("#wrapper .text_box").text( click_count + "回保存されました");

	
	
	
	
	
		var canvas = document.getElementById("canvas");
	    var base64 = canvas.toDataURL();    // firfoxならtoblobで直接blobにして保存できます。
	    var blob = Base64toBlob(base64);
	
	    document.getElementById("h").value=base64;
	    document.forms["x"].submit();
	    return true;
	    
	    
}
	




var onload = function()
{
    // 描画 好きなもの書いて～
    var canvas = document.getElementById("canvas");
    var context = canvas.getContext('2d');
    context.fillRect(0,0,50,50);
    context.fillRect(0,250,50,50);
    context.fillRect(250,0,50,50);
    context.fillRect(250,250,50,50);
}();
=======
var video = document.getElementById('video');
var canvas = document.getElementById('canvas');
var ctx = canvas.getContext('2d');
var localMediaStream = null;



//カメラ使えるかチェック
var hasGetUserMedia = function() {
    return (navigator.getUserMedia || navigator.webkitGetUserMedia ||
        navigator.mozGetUserMedia || navigator.msGetUserMedia);
}
 
//エラー
var onFailSoHard = function(e) {
    console.log('エラー!', e);
};
 
//カメラ画像キャプチャ
var snapshot = function() {
    if (localMediaStream) {
        ctx.drawImage(video, 0, 0, canvas.width, canvas.height);
        document.getElementById('img01').src = canvas.toDataURL('image/jpeg');
    }
    click++;
    click_count2++;
	$("#wrapper .text_box").text( click_count2 + "回キャプチャされました");
	
}

var click_count = 0; 
var click_count2 = 0; 
var click=0;




if (hasGetUserMedia()) {
    console.log("カメラ OK");
} else {
    alert("未対応ブラウザです。");
}
 
window.URL = window.URL || window.webkitURL;
navigator.getUserMedia  = navigator.getUserMedia || navigator.webkitGetUserMedia ||
                          navigator.mozGetUserMedia || navigator.msGetUserMedia;
navigator.getUserMedia({video: true}, function(stream) {
  video.src = window.URL.createObjectURL(stream);
  localMediaStream = stream;
}, onFailSoHard);
//ボタンイベント
$("#capture").click(function() {
	if(click_count2<5){
	snapshot();
    document.getElementById('capture_images').style.visibility = "visible";

	//一回5回までキャプチャOKで全15回までクリックイベント出来るよ
	}else if(click_count2==5 && click!=15){
		click_count2=0;
		snapshot();
	    document.getElementById('capture_images').style.visibility = "visible";
		
	}else{
		$("#wrapper .text_box").text( click_count2 + "回キャプチャされたので競技参加終了です");
	}
	});
$("#stop").click(function() {
    localMediaStream.stop();
});
$("video").click(function() {
    snapshot();
    document.getElementById('capture_images').style.visibility = "visible";
});




function kakunin(){
	
	//クリックしたらカウントしていく
	click_count++;
	//保存されたらクリック数加算する
	if(click_count==1){
		click_count2=5;
		click=5;
	}
	if(click_count==2){
		click_count2=5;
		click=10;
	}
	if(click_count==3){
		click_count2=5;
		click=15;
	}

	
	$("#wrapper .text_box").text( click_count + "回保存されました");

	
	
	
	
	
		var canvas = document.getElementById("canvas");
	    var base64 = canvas.toDataURL();    // firfoxならtoblobで直接blobにして保存できます。
	    var blob = Base64toBlob(base64);
	
	    document.getElementById("h").value=base64;
	    document.forms["x"].submit();
	    return true;
	    
	    
}
	




var onload = function()
{
    // 描画 好きなもの書いて～
    var canvas = document.getElementById("canvas");
    var context = canvas.getContext('2d');
    context.fillRect(0,0,50,50);
    context.fillRect(0,250,50,50);
    context.fillRect(250,0,50,50);
    context.fillRect(250,250,50,50);
}();
>>>>>>> af44184b23ec52970675c7159d8eb718dd7cd042
