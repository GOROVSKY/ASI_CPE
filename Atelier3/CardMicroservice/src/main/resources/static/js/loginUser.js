const form = document.getElementById('subscribe');
form.addEventListener('submit', (event) => {
    event.preventDefault()
    console.log("INSERTED")    
    let json = {};
    json['username'] = form.elements['name'].value; 
    json['password'] = form.elements['pwd'].value; 
	console.log(json)
	
    $.ajax({
    url: "http://localhost:8080/login",
    contentType: "application/json",
    dataType: 'json',
    data: JSON.stringify(json),
    type : 'POST',
    success: function(result){
		console.log(result);
		document.cookie = `token=Bearer ${result['token']}`; 
	    window.location.assign("http://localhost:8080/menu");
		
	}})
});