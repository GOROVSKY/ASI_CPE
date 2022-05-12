$.ajax({
    url: "https://asi2-backend-market.herokuapp.com/cards",
    contentType: "application/json",
    dataType: 'json',
    success: function(result){
        console.log(result);
        card = result[result.length-1]
        document.getElementById("nom").innerHTML=card.name
        document.getElementById("description").innerHTML=card.description
        document.getElementById("famille").innerHTML=card.family
        document.getElementById("attaque").innerHTML=card.attack
        document.getElementById("defence").innerHTML=card.defence
        document.getElementById("energy").innerHTML=card.energy
        document.getElementById("hp").innerHTML=card.hp
        document.getElementById("cardImgId").src=card.imgUrl
    }
})






