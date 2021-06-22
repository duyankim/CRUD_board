function submitForm(form, mode, Id) {
	if (form) {
		if(mode == "insert") {
			form.action = "insertDB.jsp?post="+Id + "&action=" +encodeURI("insert");
			form.submit();
		} else if (mode == "update") {
			form.action = "update.jsp?post="+Id + "&action=" +encodeURI("update");
			form.submit();
		} else if (mode == "delete") {
			form.action = "delete.jsp?post=" + id;
			form.submit();
		}
	}
}

function checkInput(txt, event, varCnt) {
    let reg = /^[ㄱ-ㅎ|가-힣|a-z|A-Z]+$/;
    if (txt.value == "") {
        txt.focus();
        alert('값을 입력해주세요!');
        event.preventDefault(); 
    } else if (!txt.value.match(reg)){
        txt.focus();
        alert('한글 또는 영어를 입력해주세요!');
        event.preventDefault(); 
    } 
    if (txt.value.length > varCnt) {
        txt.focus();
        alert(varCnt+ '자 이하만 입력 가능합니다!');
        event.preventDefault(); 
    };
}; 

function validateSubmit(form, event, mode, postId) {
	checkInput(form.author, event, 20);
	checkInput(form.title, event, 70);
	submitForm(form, mode, postId);
	console.log(form);
	console.log(author);
	console.log(mode);
	console.log(postId);
}