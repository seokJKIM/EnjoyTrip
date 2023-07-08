const button = document.getElementById("btn-search");
const searchArea = document.getElementById("search-area");
const content = document.getElementById("search-content-id");
const key =
  "tfDmUSbPW7w4tFMBoa1dONgt34vD57W5tY%2FleKR9DbeoS7kVJNF%2B86jpJVI%2FsYS3Jn9DqYYjbuwAtElJVbEnhQ%3D%3D";
console.log("asdf");
let areaUrl =
  "https://apis.data.go.kr/B551011/KorService1/areaCode1?serviceKey=" +
  key +
  "&numOfRows=20&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json";

// fetch(areaUrl, { method: "GET" }).then(function (response) { return response.json() }).then(function (data) { makeOption(data); });
fetch(areaUrl, { method: "GET" })
  .then((response) => response.json())
  .then((data) => makeOption(data));

/*
function makeOption(data) {
  let areas = data.response.body.items.item;
  // console.log(areas);
  areas.forEach((area) => {
    let opt = document.createElement("option");
    opt.setAttribute("value", area.code);
    opt.appendChild(document.createTextNode(area.name));

    searchArea.appendChild(opt);
  });
}

button.addEventListener("click", async (e) => {
  e.preventDefault();
  let url = `https://apis.data.go.kr/B551011/KorService1/searchKeyword1?serviceKey=${serviceKey}&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A`;
  let areaCode = document.getElementById("search-area").value;
  let contentTypeId = document.getElementById("search-content-id").value;
  let keyword = document.getElementById("search-keyword").value;

  if (parseInt(areaCode)) url += `&areaCode=${areaCode}`;
  if (parseInt(contentTypeId)) url += `&contentTypeId=${contentTypeId}`;
  if (!keyword) {
    alert("검색어를 입력하세요");
    return;
  } else url += `&keyword=${keyword}`;

  let response = await fetch(url);
  let json = await response.json();

  let result = json.response.body.items.item;
  console.log(result);
  localStorage.removeItem("searchResult");
  localStorage.setItem("searchResult", JSON.stringify(result));
  location.href = "themetrip.html";
});
 */