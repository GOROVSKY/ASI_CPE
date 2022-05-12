const form = document.getElementById('subscribe');
console.log(form)
form.addEventListener('submit', (event) => {
    event.preventDefault()
    console.log("INSERTED")    
    let json = {};
    json['name'] = form.elements['name'].value; 
    json['description'] = form.elements['description'].value;
    json['family'] = form.elements['family'].value; 
    json['affinity'] =  form.elements['affinity'].value; 
    json['imgUrl'] =  form.elements['imgUrl'].value; 
    json['energy'] =  100; 
    json['hp'] =  form.elements['hp'].value; 
    json['defence'] =  form.elements['defence'].value; 
    json['attack'] =  form.elements['attack'].value; 
    json['price'] =  10;

    var xhr = new XMLHttpRequest();
    xhr.open("POST", "https://asi2-backend-market.herokuapp.com/card", true);
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.send(JSON.stringify(json));
    window.open("card.html")

});