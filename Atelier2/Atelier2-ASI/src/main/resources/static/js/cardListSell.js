var cards = []

$.ajax({
	url: "http://localhost:8080/inventory/1", //Todo
	contentType: "application/json",
	dataType: 'json',
	success: function (userCards) {
		for (const userCard of userCards) {
			$.ajax({
				url: "http://localhost:8080/cards/" + userCard.id.cardId,
				contentType: "application/json",
				dataType: 'json',
				success: function (card) {
					cards.push(card);
					let template = document.querySelector("#row");
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
						.replace(/{{id}}/g, card.id)
						.replace(/{{quantity}}/g, userCard.quantity)
						.replace(/{{price}}/g, card.price);
					clone.firstElementChild.innerHTML = newContent;

					clone.firstElementChild.addEventListener("click", () => {
						displayCard(card)
					});


					let cardContainer = document.querySelector("#tableContent");
					cardContainer.appendChild(clone);


				}
			})
		}
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

function sell(e) {
	var idCard = e.getAttribute("data-id");

	var card = cards.find(x => x.id == idCard);
	var reponse = confirm(`Voulez-vous vendre la carte ${card.name} ?`)
	if (reponse) {
		var myDate = new Date();
		myDate = myDate.toISOString()
		myDate = myDate.replace('T', ' ')
		myDate = myDate.substring(0, 19);

		let json = {
			cardId: idCard,
			sellerId: 1, //TODO
			dateCreation: myDate 
		};

		var xhr = new XMLHttpRequest();
		xhr.open("POST", "http://localhost:8080/transactions", true);
		xhr.setRequestHeader('Content-Type', 'application/json');
		xhr.send(JSON.stringify(json));
		document.location = "http://localhost:8080/menu";
	}
}

