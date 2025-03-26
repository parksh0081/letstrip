function inputCheck() {
	var frm = document.form1;
	
	if (!frm.blogsubject.value) {
		alert("제목을 입력해주세요.");
		frm.blogsubject.focus();
		return false;
	}
	
	if (!frm.blogcontent.value) {
		alert("본문 내용을 입력해주세요.");
		frm.blogcontent.focus();
		return false;
	}
			
	// 데이터 전송
	frm.submit();
}