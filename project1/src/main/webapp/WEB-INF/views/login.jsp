<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LOGIN</title>
<link rel="stylesheet" href="./resources/css/login.css">
<script src="https://code.jquery.com/jquery-3.7.0.min.js"
	integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g="
	crossorigin="anonymous"></script>
<script type="text/javascript">
	let text = "<p>올바른 아이디를 입력하세요.</p>";
	//스크립트 영역
	//호이스팅 let vs var vs json? vs const

	function checkID() {
		//alert("!");

		let msg = document.getElementById("msg");
		msg.innerHTML = "<p>" + document.getElementById("id").value
				+ "아이디를 변경했습니다.</p>";
	}
	function check() {
		let msg = document.getElementById("msg");
		//alert("!");
		let id = document.getElementById("id");
		//alert(id.value);
		if (id.value.length < 1) {
			alert("아이디는 4글자 이상이어야합니다.");
			msg.innerHTML = text;
			id.focus();
			return false;
		}

		let pw = document.getElementById("pw");
		if (pw.value.length < 3) {
			alert("암호는 4글자 이상이어야합니다.");
			pw.focus();
			return false;
		}
	}
	$(function(){
		   $(".btn").click(function(){
		      let id = $("#id").val();
		      let pw = $("#pw").val();
		         if (id.length < 5) {
		            alert("아이디를 5글자 이상 입력하세요.");
		         } else {
		            if (pw.length < 5) {
		               alert("암호를 5글자 이상 입력하세요.");
		            } else {
		               //아이디하고 암호가 정확하게 입력되었습니다.
		               let form = $("<form></form>");
		               form.attr("method", "post");
		               form.attr("action", "./login");
		               form.append($("<input/>", {type:"hidden", name:"id", value:id}));
		               form.append($("<input/>", {type:"hidden", name:"pw", value:pw}));
		               form.appendTo("body");
		               form.submit();
		            }
		         }
		      });
		   });
  
   </script>

</head>
<body>
	<%@ include file="menu.jsp"%>
	<br>
	<div class="form">
		<form action="./login" method="post" onsubmit="return check()"></form>
		<span></span> <span></span> <span></span> <span></span>
		<div class="form-inner">
			<h2>LOGIN</h2>
			<div class="content">
				<input class="input" name="id" id="id" type="text"
					required="required" placeholder="Username" onchange="checkID()" />
				<input class="input" name="pw" id="pw" type="password"
					placeholder="Password" />

				<button type="submit" class="btn">LOGIN</button>
				<div id="msg" style="color: white"></div>
			</div>
		</div>
		<div class="register">
			<a style="font-size: small;" href="./join">회원가입</a>
		</div>


	</div>
</body>
</html>