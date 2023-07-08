function joinEncrypt(){
	var form = document.createElement('form');
	form.setAttribute('method', 'post');
	form.setAttribute('action', root+'/user/join');
	document.charset = "UTF-8";
	
	let info = {};
	info.userId = document.querySelector('#regid').value;
	info.userName = document.querySelector('#nickname').value;
	info.userPass = CryptoJS.SHA256(document.querySelector('#regpw').value).toString();
	info.userEmail = document.querySelector('#email').value;
	
	for(let user in info){
		var hiddenField = document.createElement('input');
		hiddenField.setAttribute('type', 'hidden');
		hiddenField.setAttribute('name', user);
		hiddenField.setAttribute('value', info[user]);
		form.appendChild(hiddenField);
	}
	document.body.appendChild(form);
	form.submit();
}

function loginEncrypt(){
	var form = document.createElement('form');
	form.setAttribute('method', 'post');
	form.setAttribute('action', root+'/user/login');
	document.charset = "UTF-8";
	
	let info = {};
	info.userId = document.querySelector('#userid').value;
	info.userPass = CryptoJS.SHA256(document.querySelector('#userpass').value).toString();
	
	for(let user in info){
		var hiddenField = document.createElement('input');
		hiddenField.setAttribute('type', 'hidden');
		hiddenField.setAttribute('name', user);
		hiddenField.setAttribute('value', info[user]);
		form.appendChild(hiddenField);
	}
	document.body.appendChild(form);
	form.submit();
}