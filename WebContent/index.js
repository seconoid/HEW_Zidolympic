// forked from Yukisuke's "Canvasの画像(スクリーンショット)をローカルダウンロードします。" http://jsdo.it/Yukisuke/p311
function screenshot()
{
    var canvas = document.getElementById("canvas");
    var base64 = canvas.toDataURL();    // firfoxならtoblobで直接blobにして保存できます。
    var blob = Base64toBlob(base64);
    saveBlob(blob,"default.png");
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
