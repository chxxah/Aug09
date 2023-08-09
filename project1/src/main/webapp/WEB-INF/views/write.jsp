<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="menu.jsp"%>
<script type="module"
	src="https://ryj9d86xjh.execute-api.ap-northeast-2.amazonaws.com/v1/api/fontstream/djs/?sid=gAAAAABktObTyz-ThGGFX4r37eibzD3bEsOwbCE1d8-Qn7DjepOaWDVkRVp8gho2iF2FLe50eehHgfqjOqQlFPNKuPaN-t_tdrnU6XO-4bhgzXsAfJ7_JAQeRJJeRflv0nWDpz6j6JzZW0AJ0Um3ZFTVrJuxQyoretDZprv9pxHT2A_IG-2aHV9SMByVydiXkyUr60DErWdcbgW7hY2StrLJwm4vuGIR4cRyIBEVAu66wYWQD83HG6Y-OtFy_dEyQINw30UzfzaO"
	charset="utf-8"></script>
<meta charset="UTF-8">
<title>Write</title>
<link rel="stylesheet" href="./css/menu.css">
<link rel="stylesheet" href="./css/write.css">

<!-- include libraries(jQuery, bootstrap) -->
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"
	rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<!-- include summernote css/js -->
<link
	href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
	
	
<script type="text/javascript">


function check() {
   let msg = document.getElementById("msg")
   let msg2 = document.getElementById("msg2")
   
   let text = "<p>올바른 제목을 입력하세요.</p>";
   let text2 = "<p>올바른 내용을 입력하세요.</p>";
   
   let title = document.getElementById("title");
   let summernote = document.getElementById("summernote");
   //alert(title.value)
   //alert(content.value)
   
   if (title.value.length == "") {
      alert("제목은 1글자 이상이어야 합니다.");
      msg.innerHTML = text;
      title.focus();
      return false;
   }
   
   if (summernote.value.length < 12) {
      alert("내용은 5글자 이상이어야 합니다.");
      msg2.innerHTML = text2;
      summernote.focus();
      return false;
   }
}

</script>
	
	
	

</head>
<body>
<br>
<br>
<br>
	<div class="write">WRITE</div>
	
	<button class="btn btn btn-outline-primary">버튼</button>
	
	<div class="neonText">
		<form action="./write" method="post" onsubmit="return check()">
			<input type="text" id="title" name="title" placeholder="제목을 입력하세요."
				maxlength="30">
				<span style="color: white" id="msg"></span>
			<textarea id="summernote" name="content"></textarea>
			<span style="color: white" id="msg2"></span>
			<button class="button" type="submit">저장하기</button>
			<!-- => 제출버튼. 폼데이터로 데이터 전송 -->
		</form>
	</div>
	
	<script type="text/javascript">
		/* JQuery 문법 : 문서가 모두 로딩되었다면, 익명함수를 실행하세요. */
		/* textarea에 서머노트를 실행해주세요. */
		$(document).ready(function() {
			$('#summernote').summernote({
				height : 450
			});
		});
	</script>
</body>
</html>