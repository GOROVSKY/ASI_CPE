$.ajax({
	url: "http://localhost:8080/menu",
	contentType: "application/json",
	dataType: 'json',
	success: function (user) {
					console.log(card);
	
					let template = document.querySelector("#header");
					let clone = document.importNode(template.content, true);
					newContent = clone.firstElementChild.innerHTML
										.replace(/{{wallet}}/g, user.wallet)
										.replace(/{{username}}/g, user.surname);
					clone.firstElementChild.innerHTML = newContent;
							}
})
