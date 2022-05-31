const form = document.getElementById('subscribe');
console.log(form)
form.addEventListener('submit', (event) => {
    event.preventDefault()
    console.log("INSERTED")    
    let json = {};
    json['name'] = form.elements['name'].value; 
    json['surname'] = form.elements['surname'].value;
    json['password'] = form.elements['password'].value; 
	
	
$.ajax({
    url: "http://localhost:8080/users",
    contentType: "application/json",
    dataType: 'json',
    data: JSON.stringify(json),
    type : 'POST',
    complete: function(result){
		console.log(result)
		window.location.assign("http://localhost:8080/login");
	}})
    
});