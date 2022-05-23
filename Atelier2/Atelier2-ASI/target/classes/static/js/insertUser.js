const form = document.getElementById('subscribe');
console.log(form)
form.addEventListener('submit', (event) => {
    event.preventDefault()
    console.log("INSERTED")    
    let json = {};
    json['name'] = form.elements['name'].value; 
    json['surname'] = form.elements['surname'].value;
    json['password'] = form.elements['password'].value; 


    var xhr = new XMLHttpRequest();
    xhr.open("POST", "http://localhost:8080/users", true);
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.send(JSON.stringify(json));
    window.open("http://localhost:8080/login");

});