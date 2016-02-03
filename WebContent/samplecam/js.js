var video = document.getElementById('video');
var canvas = document.getElementById('canvas');
var ctx = canvas.getContext('2d');
var localMediaStream = null;
 
//カメラ使えるかチェック
var hasGetUserMedia = function() {
    return (navigator.getUserMedia || navigator.webkitGetUserMedia ||
        navigator.mozGetUserMedia || navigator.msGetUserMedia);
};
 
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
};


var CreateFolder = new Folder ("/c/sample/");
CreateFolder.create();
////フォルダの作成
//function mkdir()
//{
//	var folderPath = "C:/" + "aaa";
//	var success = Folder(folderPath).create();
//	
//	folderPath = "C:/" + "aaa\\bbb";
//	success = Folder(folderPath).create();
//}




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
    snapshot();
    document.getElementById('capture_images').style.visibility = "visible";
});
$("#stop").click(function() {
    localMediaStream.stop();
});

$("#mkdir").click(function(){
            var CreateFolder = new Folder ("/c/sample/");
CreateFolder.create();
                  });


$("video").click(function() {
    snapshot();
    document.getElementById('capture_images').style.visibility = "visible";
});