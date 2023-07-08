callBoard(id)

function callBoard(userId){
	let config = {
			method : "get",
	};
	console.log(id)
	fetch(root + "/board/mypage?userId=" + userId,config)
	.then((response) => response.json())
	.then((data) => makeBoard(data, userId));
}

function makeBoard(data, userId){
	let mypageBoard = document.getElementById("mypageBoard");
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
	mypageBoard.innerHTML = articleList;
}

function moveDetail(num){
	location.href = root+"/board/detail?num="+num
}