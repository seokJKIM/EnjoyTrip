function taplist(e) {
//  document.querySelectorAll(".tap").forEach(function (item) {
//    item.classList.remove("active");
//  });
//  e.classList.add("active");
}

//document.getElementById("userid").innerText = user.userid;
//console.log(user);
//document.getElementById("nickname").innerText = user.nickname;
//document.getElementById("email").innerText = user.email;

function moveContent() {
  //location.href = "boardContent.html";
}

function changePass(){
	var form = document.createElement('form');
	form.setAttribute('method', 'post');
	form.setAttribute('action', root+'/user/changePass');
	document.charset = "UTF-8";
	
	console.log(document.querySelector('#originalpw').value)
	console.log(document.querySelector('#changepw').value)
	let info = {};
	info.userId = document.querySelector('#hiddenId').value;
	info.userPass = CryptoJS.SHA256(document.querySelector('#originalpw').value).toString();
	info.changepw1 = CryptoJS.SHA256(document.querySelector('#changepw').value).toString();
	info.changepw2 = CryptoJS.SHA256(document.querySelector('#changepw2').value).toString();
	if(info.changepw1===info.changepw2){
		for(let user in info){
			var hiddenField = document.createElement('input');
			hiddenField.setAttribute('type', 'hidden');
			hiddenField.setAttribute('name', user);
			hiddenField.setAttribute('value', info[user]);
			form.appendChild(hiddenField);
		}
		document.body.appendChild(form);
		form.submit();
	}else{
		alert("새 비밀번호가 다르게 입력되었습니다.")
	}
	
}
