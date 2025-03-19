function reviewCheck(){
	var frm=document.form1;
	// 별점 검사 
	if(!frm.star.value){
		alert("falseStar");
		return false;
	}
	
	// 리뷰 검사 
	if(!frm.content.value){
		alert("falseContent")
		return false;
	}
	
	frm.submit();
	
	window.close();

}