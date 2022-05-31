var cards = []


let token2 = getCookie('token').substring(7);
let userId;

$.ajax({
	url: "http://localhost:8080/token/" + token2,
	contentType: "application/json",
	dataType: 'json',
	success: function (user) {
		userId = user['id'];

		$.ajax({
			url: "http://localhost:8080/transactions",
			contentType: "application/json",
			dataType: 'json',
			success: function (transactions) {
				for (const transaction of transactions) {
					console.log(transaction)
					$.ajax({
						url: "http://localhost:8080/cards/" + transaction.cardId,
						contentType: "application/json",
						dataType: 'json',
						success: function (card) {
							cards.push(card);
							let template = document.querySelector("#row");
							let clone = document.importNode(template.content, true);
							newContent = clone.firstElementChild.innerHTML
								.replace(/{{family_src}}/g, card.family_src)
								.replace(/{{family_name}}/g, card.family)
								.replace(/{{img_src}}/g, card.imageUrl)
								.replace(/{{name}}/g, card.name)
								.replace(/{{description}}/g, card.description)
								.replace(/{{hp}}/g, card.hp)
								.replace(/{{energy}}/g, card.energy)
								.replace(/{{attack}}/g, card.attack)
								.replace(/{{defense}}/g, card.defense)
								.replace(/{{id}}/g, transaction.id)
								.replace(/{{cardId}}/g, card.id)
								.replace(/{{price}}/g, card.price);
							clone.firstElementChild.innerHTML = newContent;

							clone.firstElementChild.addEventListener("click", () => {
								displayCard(card)
							})

							let cardContainer = document.querySelector("#tableContent");
							cardContainer.appendChild(clone);
						}
					})
				}
			}
		})
	}
})

function displayCard(card) {
	let container = document.getElementById("card");
	var template = document.getElementById("template-card");


	var template = document.getElementById("template-card");

	let clone = document.importNode(template.content, true);
	newContent = clone.firstElementChild.innerHTML
		.replace(/{{family_src}}/g, card.family_src)
		.replace(/{{family_name}}/g, card.family)
		.replace(/{{img_src}}/g, card.imageUrl ?? "https://upload.wikimedia.org/wikipedia/commons/thumb/6/65/No-Image-Placeholder.svg/495px-No-Image-Placeholder.svg.png?20200912122019")
		.replace(/{{name}}/g, card.name)
		.replace(/{{description}}/g, card.description)
		.replace(/{{hp}}/g, card.hp)
		.replace(/{{energy}}/g, card.energy)
		.replace(/{{attack}}/g, card.attack)
		.replace(/{{defense}}/g, card.defense)
		.replace(/{{price}}/g, card.price);

	clone.firstElementChild.innerHTML = newContent;
	container.innerHTML = '';
	container.appendChild(clone);
}

function buy(e) {
	var idTransaction = e.getAttribute("data-id");

	var idCard = e.getAttribute("data-cardId");
	var card = cards.find(x => x.id == idCard);

	var reponse = confirm(`Voulez-vous acheter la carte ${card.name} ?`)
	if (reponse) {
		var myDate = new Date();
		myDate = myDate.toISOString()
		myDate = myDate.replace('T', ' ')
		myDate = myDate.substring(0, 19);

		let json = {
			id: idTransaction,
			buyerId: userId,
			dateBuy: myDate
		};

		var xhr = new XMLHttpRequest();
		xhr.open("PUT", "http://localhost:8080/transactions", true);
		xhr.setRequestHeader('Content-Type', 'application/json');
		xhr.send(JSON.stringify(json));

		xhr.onreadystatechange = function() {
		    if (xhr.readyState === 4) {
				//Refresh market page
				location.reload();
		    }
  		}
	}
}



