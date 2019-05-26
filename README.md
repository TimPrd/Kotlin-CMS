# Kotlin-CMS

## Dump SQL

**Warning**: 

The dump will create a database named "CMS".
Please, remove those two lines if needed (l.15).

````
CREATE DATABASE CMS;
USE CMS;
````

Export from DB is here : [dump.sql](dump.sql)


## Config

Please change the values in [config.properties](src/main/kotlin/pardieu/timothé/cms/config.properties)

**Warning**: port by default is **8003**


## Users

Two users are available : 
- admin :
  - username: Tim
  - password: tim
- user
  - username: user
  - password: user
  
-----
**To-Do**:

 - [x] Style

 - [x] Affichage des commentaires d'un article.

 - [x] Possibilite de poster un commentaire (sans etre connecté) depuis la page d'un article.

 - [x] Connection a une interface d'administration avec login / mot de passe.

 - [x]  Gestion de la session, possibilité de se déconnecter.

 - [x]  Une fois connecté en tant qu'admin, possibilité d'ajouter ou supprimer un article.

 - [x]  Si on supprime un article, ses commentaires doivent etre supprimés.

 - [x]  Une fois connecté en tant qu'admin, possibilité de supprimer un commentaire.

---

**Bonus**: 

 - [x] Utilisation d'Argon2
 - [x] Properties
 - [x] Ajout d'autres champs (email, date..)
 - [x] Inscription
 - [x] Si connecté, le commentaire est posté avec le pseudo de l'utilisateur
 - [x] Gestion utilisateur / admin
 - [x] Possibilité pour l'admin de changer les rôles des utilisateurs ou de les supprimer


