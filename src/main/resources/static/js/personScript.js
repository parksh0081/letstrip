// 로그인 화면에서 입력 검사
function checkLogin() {
	var frm = document.form1;  // form 객체 얻기
	// 아이디 입력 검사
	if(!frm.id.value) {
		alert("아이디를 입력하세요");
		frm.id.focus();
		return false;
	}
		
	// 비밀번호 입력 검사
	if(!frm.pwd.value) {
		alert("비밀번호를 입력하세요");
		frm.pwd.focus();
		return false;
	}
	// 데이터 전송 
	frm.submit();
}

function checkWrite() {
	var frm = document.form1; 
	
	if(!frm.name.value) {
		alert("이름을 입력하세요.");
		frm.name.focus();
		return false;
	}
	
	if(!frm.id.value) {
		alert("아이디를 입력하세요.");
		frm.id.focus();
		return false;
	}
	
	if(!frm.pwd.value) {
		alert("비밀번호를 입력하세요.");
		frm.pwd.focus();
		return false;
	}
	
	if(!frm.pwd_check.value) {
		alert("비밀번호를 재확인해주세요.");
		frm.pwd_check.focus();
		return false;
	}
	
	if(frm.pwd.value != frm.pwd_check.value) {
		alert("비밀번호가 틀립니다.");
		frm.pwd_check.value = ""; 
		frm.pwd_check.focus();
		return false;
	}
	
	if(!frm.email1.value) {
		alert("이메일을 입력하세요.");
		frm.email1.focus();
		return false;
	}	
	
	if(!frm.tel2.value || !frm.tel3.value || isNaN(frm.tel2.value) || isNaN(frm.tel3.value)) {
		alert("전화번호를 숫자로 입력해주세요.");
		frm.tel2.value = "";
		frm.tel3.value = "";
		frm.tel2.focus();
		return false;
	}	
	
	frm.submit();
}
