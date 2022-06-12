Membres du groupe : 
- Driss Duto 
- Pierre Khettal
- Guillaume Laville 
- Vargiolu Thomas

Lien projet GIT : https://github.com/GOROVSKY/ASI_CPE

Elements réalisés par membre de groupe :
- Thomas Vargiolu : Utilisation de tensorflow pour créer les tags des images. L'API est disponible via Flask et prend en parametre l'url puis retourne un json contenant les tags. 
- Guillaume Laville : Création du micro-service UserMicroservice (gestion des utilisateurs et de leurs cartes). Mise en place de tests unitaires couvrant en partie le code du UserService.
- Driss Dutto : Création des micro-services CardMicroservice, AuthentificationMicroservice et RoomMicroservice. Mise en place du reverse proxy sous nginx.
- Pierre Khettal : Création du micro-service TransacationMicroservice. Aide à la création de l'API des tags des images.

Elements non réalisés :
- L'API de detection de tag n'est pas inclus dans le projet global, elle n'est toujours pas appelée lors de la création d'une carte. Les codes créés pour cela ne sont pas encore complets. 
- Les tests unitaires n'ont été mis qu'en place pour le UserMicroservice. Par soucis de temps, ne nous l'avons pas généralisé aux autres services.
- Nous n'avons pas pu tester notre couverture de tests avec Sonar.
- Le micro-service des Rooms est présent mais n'est pas mis en place côté client.

Elements réalisés en plus :
Mise en place du reverse proxy et de l'API de tags dans des containers Docker.