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


let token = getCookie('token').substring(7);
$.ajax({
	url: "http://localhost:8080/token/"+token,
	contentType: "application/json",
	dataType: 'json',
	success: function (user) {
				document.getElementById("userNameId").textContent=user['name'];
				document.getElementById("wallet").textContent=user['wallet'];
	}
})
