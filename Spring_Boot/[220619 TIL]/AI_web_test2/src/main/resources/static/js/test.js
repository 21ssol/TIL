$(function() {
	$("#login_btn").click(function() {

		// const test = alert('id: ' + document.getElementById("id").value+' pw: '+document.getElementById("pw").value); 
		const id = document.getElementById("id").value;
		const pw = document.getElementById("pw").value;
		// const name = document.getElementById("name").value;
		alert('id: ' + id + '  pw: ' + pw);

		 fetch(url="http://localhost:8000/loginCk?id='"+id+"'&passwd='"+pw+"'")
		.then((response)=>response.json())
		.then(data=>{
			alert(data.name+"님 login ok Status Success");
		})  
	})
});