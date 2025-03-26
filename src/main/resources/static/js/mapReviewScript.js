

function reviewCheck(){
	var frm=document.form1;
	var placeid=frm.placeid.value;
	// 별점 검사 
	if(!frm.star.value){
		alert("별점을 입력해주세요.");
		return false;
	}
	
	// 리뷰 검사 
	if(!frm.content.value){
		alert("리뷰를 작성해주세요.")
		return false;
	}
	
	// frm.submit(); -> 동기처리할 경우 필요 
	
	var frmData=new FormData(frm); // form 데이터를 js 객체로 변환 
	
	// Ajax 요청을 통해 서버에 데이터 전송 
	fetch("mapReviewWrite",{
		method:"POST",
		body:frmData
	})
	.then(response=>response.text())
	.then(data=>{
		alert("리뷰가 등록되었습니다.");
		if(window.opener){
			window.opener.updateSideTab(placeid); 	// 리뷰 목록 창 새로고침을 위한 함수 호출  
			window.opener.updateStarRating(placeid); 
			window.close(); // 팝업 닫기 
		}
	})
	.catch(err=>{
		alert("Err:"+err.message);
	}); 
	return false; 	// 기본 폼 제출 방지 

	//window.close();

}

