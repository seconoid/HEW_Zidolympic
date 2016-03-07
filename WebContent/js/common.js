// ラジオボタンの選択でフォームに自動入力
function selectCard(i) {
	if( i == 1 ){
		$('#digit1').val("AAAA");
		$('#digit2').val("BBBB");
		$('#digit3').val("CCCC");
		$('#digit4').val("DDDD");
	}else if( i == 2){
		$('#digit1').val("ZZZZ");
		$('#digit2').val("YYYY");
		$('#digit3').val("XXXX");
		$('#digit4').val("WWWW");
	}else{
		$('#digit1').val("0000");
		$('#digit2').val("1111");
		$('#digit3').val("2222");
		$('#digit4').val("3333");
	}
}