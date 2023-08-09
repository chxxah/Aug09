<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>
<link rel="stylesheet" href="./css/join.css?version=0.2">
<script src="./js/jquery-3.7.0.min.js"></script>

<script type="text/javascript">
	$(function() {
		$("#idCheck").click(
				function() {
					let id = $("#id").val();
					//console.log(id);
					if (id == "" || id.length < 5) {
						$("#id").focus();
						$("#resultMSG")
								.text("ID MUST HAVE MORE THAN 4 LETTERS");
						$("#resultMSG").css("color", "red").css("font-weight",
								"bold").css("font-size", "10pt");

					} else {
						$.ajax({ //ajax 시작
							url : "./checkID",
							type : "post",
							data : {
								"id" : id
							},
							dataType : "json", // {result : 0}
							success : function(data) {
								if (data.result == 1) {
									$("#id").css("background-color", "red");
									$("#id").focus();
									$("#resultMSG").css("color", "red").css(
											"font-weight", "bold").css(
											"font-size", "10pt");
									$("#resultMSG").text("이미 등록된 ID");
								} else {
									$("#id").css("background-color", "white");
									$("#resultMSG").css("color","green")
									$("#resultMSG").text("가능");
								}

								//$("#resultMSG").text("성공시 결과값 : " + data);
							},
							error : function(request, status, error) {
								
								$("#resultMSG").text("오류 발생");
							}
						});

					}

					return false;
				});
	});
</script>

</head>
<body>
	<%@ include file="menu.jsp"%>
	<br>
	<br>
	<br>

	<div class="join">
		<form action="./join" method="post">

			<h1 style="font-size: xx-large;">USER REGISTRATION</h1>
			<div class="text">
				PLEASE ENTER YOUR PERSONAL<BR> INFORMATION BELOW TO SIGN UP.
			</div>
			<div class="title">

				ID<br> <input type="text" name="id" id="id"
					style="width: 410px; float: left; margin-right: 6px;'">
				<button id="idCheck">CHECK</button>
				<span id="resultMSG"></span> <br> <br> <br> PASSWORD<br>
				<input type="password" name="pw1"> <br> <br> <br>
				CONFIRM PASSWORD<br> <input type="password" name="pw2">
				<br> <br> <br> NAME<br> <input type="text"
					name="name"> <br> <br> <br> SEX<br>
				<div class="select">
					<input type="radio" id="select" name="gender" value="1"><label
						for="select">MALE</label> <input type="radio" id="select2"
						name="gender" value="0"><label for="select2">FEMALE</label>
				</div>
				<br> <br> BIRTH DATE<br> <input type="date"
					name="birth"> <br> <br> <br>ADDRESS<br>
				<input type="text" name="addr"> <br> <br> <br>
				MBTI <br>
				<div class="mbti">
					<!-- <label for="lang">mbti</label><br> -->
					<select class="mb" name="mbti" id="mbti">
						<option value="CHOOESE">SELECT ONE</option>
						<optgroup label="내향형">
							<option value="ISTJ">ISTJ</option>
							<option value="ISFJ">ISFJ</option>
							<option value="INFJ">INFJ</option>
							<option value="INTJ">INTJ</option>
							<option value="ISTP">ISTP</option>
							<option value="ISFP">ISFP</option>
							<option value="INFP">INFP</option>
							<option value="INTP">INTP</option>
						</optgroup>
						<optgroup label="외향형">
							<option value="ESTP">ESTP</option>
							<option value="ESFP">ESFP</option>
							<option value="ENFP">ENFP</option>
							<option value="ENTP">ENTP</option>
							<option value="ESTJ">ESTJ</option>
							<option value="ESFJ">ESFJ</option>
							<option value="ENFJ">ENFJ</option>
							<option value="ENTJ">ENTJ</option>
						</optgroup>
					</select>
				</div>
				<br> <br> <br>
				<button type="reset"
					style="width: 250px; height: 40px; font-weight: bold;">CANCEL</button>
				<button type="submit" id="h"
					style="width: 250px; height: 40px; font-weight: bold;">SIGN
					UP</button>
			</div>

		</form>

	</div>

	<div style="height: 100px;"></div>

</body>
</html>