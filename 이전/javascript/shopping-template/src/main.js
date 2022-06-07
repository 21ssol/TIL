//Fetch the items from the json file, return response

function loadItems(){   //함수 선언
    return fetch('data/data.json')  //fetch 인자값으로 데이터의 파일명 접근
    .then(response => response.json()) //json객체로 변환
    .then(json => json.items);  //변환된 json안에 items에 접근하여 배열값 뽑아옴
 
}
 
//update the list with the given items
function displayItems(items){  //items는 json파일에 있었던 배열을 가리킴
    const container = document.querySelector('.items'); //ul 태그 찾는다.(클래스가 .items로 있는 ul)
    //const html = items.map(item => createHTMLString(item)).join('');
    //console.log(html);
    // map의 item은 items배열에 있는 하나를 가리킴, 총 16개(배열갯수)를 만듦
    container.innerHTML = items.map(item => createHTMLString(item)).join(''); //생성된 li태그를 문자열로 변경(join)
}
 
//create HTML List iem from the given data item
function createHTMLString(item) {  //li값을 만들고 있음
    return `
    <li class="item">
        <img src="${item.image}" alt="${item.type}" class="item__thumbnail">
        <span class="item__description">${item.gender}, ${item.size}</span>  
    </li>
    `;
}
 
function onButtonClick(event, items){//이벤트가 발생하는 버튼에 필터링되는 값을 가지도록 한다.
    const dataset = event.target.dataset;  //data로 시작하는 것만 dataset을 쓸수 있다.
    const key = dataset.key;  // color
    const value = dataset.value;  //상품에 해당하는 호칭
    
    if(key == null || value == null){
        return;
    }
 
    const filtered = items.filter(item => item[key] === value);  //해당하는거 다 찾아서 집어넣음
    console.log(filtered);
    displayItems(filtered);
 
}
 
function setEventListeners(items){  //베열의 값을 가지게 됨
    const log = document.querySelector('.logo');  //querySelector 실제 쿼리를 찾는 것
    const buttons = document.querySelector('.buttons');
    log.addEventListener('click', () => displayItems(items));
    buttons.addEventListener('click', event => onButtonClick(event, items)); //버튼>> 6개의 버튼, items은 16개의 배열

}
//main (시작), 함수 호출
loadItems() // json의 배열값이 리턴
    .then(items => {  //.then : 프로미스
        console.log(items);
        displayItems(items);  //매개변수 전달, li안에 데이터가 처음에 들어감
        setEventListeners(items);  //16개의 배열의 값을 전달
    })
    .catch(console.log);