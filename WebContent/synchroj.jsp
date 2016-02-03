<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta charset="UTF-8" />
</head>

<!-- main_layout.jspにコンテンツとタイトルを渡す -->
<c:import url="./layout/main_layout.jsp">
	<c:param name="title" value="シンクロジドリング"/>
	<c:param name="content">
<script type="text/javascript" src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
	<video id="video" autoplay width="320" height="240"></video><br />
<div id="wrapper"> 
    <button id="capture">capture</button>
    <button id="stop">stop</button>
	<button id="aaaaaa">保存</button>
    
</div>
<br />
	<div id="capture_images" style="visibility:hidden">ﾋﾞﾃﾞｵ<br />
    
    <img id="img01"  width="600" height="480" /><br />
    
    Canvas<br />
    
    
    <canvas id="canvas" width="600" height="480" ></canvas><br />
    
    <script type="text/javascript" src="./js/js.js"></script>
    
</div>

		<a href="index.jsp">インデックス</a>
	</c:param>
</c:import>
<body>

</body>
</html>