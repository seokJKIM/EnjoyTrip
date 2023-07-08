callBoard(1)

function callBoard(boardId){
	let config = {
			method : "get",
	};
	console.log(boardId)
	fetch(root + "/board/list?boardId=" + boardId,config)
	.then((response) => response.json())
	.then((data) => makeBoard(data, boardId));
}

function makeBoard(data, boardId){
	//공지사항 게시판
	if(boardId===1){
		let noticeBoard = document.getElementById("noticeBoard");
		let articleList = ``
		console.log(data)
		data.forEach((article) => {
	        articleList += `
            <tr onclick="moveDetail(${article.num});">
              <td>${article.num}</td>
              <td>${article.title}</td>
              <td>${article.writerId}</td>
              <td>${article.hits}</td>
            </tr>
          `;
		});
		noticeBoard.innerHTML = articleList;
	}
	//여행 후기 게시판
	else if(boardId===2){
		let reviewBoard = document.getElementById("reviewBoard");
		let articleList = ``
		data.forEach((article) => {
	        articleList += `
            <tr onclick="moveDetail(${article.num});">
              <td>${article.num}</td>
              <td>${article.title}</td>
              <td>${article.writerId}</td>
              <td>${article.hits}</td>
            </tr>
          `;
		});
		reviewBoard.innerHTML = articleList;
	}
	//자유 게시판
	else if(boardId===3){
		let freeBoard = document.getElementById("freeBoard");
		let articleList = ``
		data.forEach((article) => {
	        articleList += `
            <tr onclick="moveDetail(${article.num});">
              <td>${article.num}</td>
              <td>${article.title}</td>
              <td>${article.writerId}</td>
              <td>${article.hits}</td>
            </tr>
          `;
		});
		freeBoard.innerHTML = articleList;
	}
	//일행 구하기 게시판
	else{
		let teamBoard = document.getElementById("teamBoard");
		let articleList = ``
		data.forEach((article) => {
	        articleList += `
            <tr onclick="moveDetail(${article.num});">
              <td>${article.num}</td>
              <td>${article.title}</td>
              <td>${article.writerId}</td>
              <td>${article.hits}</td>
            </tr>
          `;
		});
		teamBoard.innerHTML = articleList;
	}
}

function moveDetail(num){
	location.href = root+"/board/detail?num="+num
}

function searchBoard(boardId){
	let config = {
			method : "get",
	};
	console.log(boardId)
	let condition = 0;
	let keyword = '';
	if(boardId===1){
		condition = document.getElementById("noticeCondition").value
		keyword = document.getElementById("noticeKeyword").value
	}else if(boardId===2){
		condition = document.getElementById("reviewCondition").value
		keyword = document.getElementById("reviewKeyword").value
	}else if(boardId===2){
		condition = document.getElementById("freeCondition").value
		keyword = document.getElementById("freeKeyword").value
	}else{
		condition = document.getElementById("teamCondition").value
		keyword = document.getElementById("teamKeyword").value
	}
	fetch(root + "/board/search?boardId=" + boardId + "&condition=" + condition+"&keyword=" + keyword,config)
	.then((response) => response.json())
	.then((data) => makeBoard(data, boardId));
}
