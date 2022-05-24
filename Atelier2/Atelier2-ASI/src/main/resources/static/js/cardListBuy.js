$.ajax({
	url: "http://localhost:8080/transactions",
	contentType: "application/json",
	dataType: 'json',
	success: function (transactions) {
		for (const transaction of transactions) {
			$.ajax({
				url: "http://localhost:8080/cards/" + transaction.cardId,
				contentType: "application/json",
				dataType: 'json',
				success: function (card) {
					console.log(card);
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



