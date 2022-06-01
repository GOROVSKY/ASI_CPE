function getCookie(cname) {
  let name = cname + "=";
  let decodedCookie = decodeURIComponent(document.cookie);
  let ca = decodedCookie.split(';');
  for(let i = 0; i <ca.length; i++) {
    let c = ca[i];
    while (c.charAt(0) == ' ') {
      c = c.substring(1);
    }
    if (c.indexOf(name) == 0) {
      return c.substring(name.length, c.length);
    }
  }
  return "";
}

function parseJwt (token) {
  var base64Url = token.split('.')[1];
  var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
  var jsonPayload = decodeURIComponent(atob(base64).split('').map(function(c) {
      return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
  }).join(''));

  return JSON.parse(jsonPayload);
};


let token = getCookie('token').substring(7);
let parsedToken = parseJwt(token);
console.log(parsedToken['sub']);
document.getElementById("userNameId").textContent=parsedToken['sub'];
$.ajax({
	url: "http://localhost:80/api/users?userName="+parsedToken['sub'],
	contentType: "application/json",
	dataType: 'json',
	success: function (user) {
				document.getElementById("wallet").textContent=user['wallet'];
	}
})


