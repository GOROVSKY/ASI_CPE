$.ajax({
    url: "http://localhost:8080/transactions",
    contentType: "application/json",
    dataType: 'json',
    success: function(transactions){
		for(const transaction of transactions){
			$.ajax({
		    url: "http://localhost:8080/cards/"+transaction.cardId,
		    contentType: "application/json",
		    dataType: 'json',
		    success: function(result){
				console.log(result);
		       	let template = document.querySelector("#row");
		       	for(const card of result){
				    let clone = document.importNode(template.content, true);
				
				    newContent= clone.firstElementChild.innerHTML
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
				    clone.firstElementChild.innerHTML= newContent;
				
				    let cardContainer= document.querySelector("#tableContent");
				    cardContainer.appendChild(clone);
			}
		   }
		})
		}
    }
})




