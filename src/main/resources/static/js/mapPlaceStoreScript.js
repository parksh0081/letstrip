function storeCheck(placeid,personId){
	// 로그인 후 저장 가능 
	if(personId!=null){	// Ajax 요청을 통해 서버에 데이터 전송 
		fetch("mapPlaceStore", {
		            method: "POST", // POST로 변경
		            headers: {
		                "Content-Type": "application/json"
		            },
		            body: JSON.stringify({ id: personId, placeid: placeid }) // DTO와 동일한 구조로 전송
		        })
			.then(response=>response.json())
			.then(()=>{
				alert("store inset/delete success.");
				updateStoreList(personId);
			})
			.catch(err=>{
				alert("Err:"+err.message);
			}); 

	}else{
		alert("로그인 후 이용가능합니다.");
		location.href="/personLoginForm";
	} 
}

function updateStoreList(personId){
	fetch("mapPlaceStoreTab?id=" + personId)
	        .then(response => response.text())
	        .then(html => {
	            document.getElementById("placeBody2").innerHTML = html; // 최신 데이터로 UI 업데이트
	        })
	        .catch(error => {
	            alert("Err: " + error.message);
	        });
}