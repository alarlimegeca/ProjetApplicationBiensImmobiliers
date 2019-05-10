# ProjetApplicationBiensImmobiliers

Notre projet est une application de gestion de biens immobiliers capable de répondre aux besoins de particuliers, de clients ou bien encore du personnel de l'agence. 

## <a name="developpe-par"></a>Développé par 
* "Alfred MENGIN" <Alfred.Mengin@ensg.eu>
* "Arthur GENET" <Arthur.Genet@ensg.eu>
* "Lilian CALAS" <Lilian.Calas@ensg.eu>
</br>Dans le cadre du cours de projet JAVA, de première année d'ingénieur à l'ENSG.


## <a name="installation et lancement"></a> Installation et lancement
Afin de pouvoir utiliser notre application, télechargez tous les fichiers du gitHUB en cliquant sur [ce lien](https://github.com/LePaulM/ProjetJavaEcosysteme.git)

Vous aurez besoin de télécharger un browser de SQLite :

Si votre OS est Windows cliquez sur https://sqlitebrowser.org/dl/

Si votre OS est Linux cliquez sur https://sqlitebrowser.org (rubrique 2017 appimage builds for Linux)

Il faut maintenant télécharger la librairie jar : https://bitbucket.org/xerial/sqlite-jdbc/downloads/

Exécutez l'installateur du Browser. 

Dans la fenètre Eclipse, faites un clic droit sur le projet "Immobilier" > Build Path > Configure Build Path > Librairies > add external jars > retrouvez la librairie précédemment recherchée

Pour lancer l'application, il suffit maintenant d'exécuter la classe Application qui se trouve dans le package "interactions".
A chaque fois que vous voulez changer de rôle, il faut fermer la fenêtre et relancer l'application.

## <a name="exemples"></a> Exemples

* Exemple client *
Client test : id : vide / mdp : italien

- Créer un compte :
Espaces>Client>Créer un nouveau compte
Puis rentrer vos informations
C'est maintenant au responsable d'agence de valider votre compte (voir : Exemple responsable d'agence>Valider compte client)

- Soumettre une annonce :
Espaces>Client>Se connecter>Actions>Soumettre une annonce
Rentrer les informations

- Choisir le particulier :
Espaces>Client>Se connecter>Actions>Choisir particulier



  
* Exemple agent *
Agent test : id : lilica / mdp : azertyuiop


- Voir ses statistiques :
Espaces>Agent immobilier>Se connecter>Actions>

- Ajouter créneau :
Espaces>Agent immobilier>Se connecter>Actions>

* Exemple particulier *

- Prendre rendez-vous : 
Espaces>Particulier>


* Exemple responsable d'agence *
Responsable test : id : argen / mdp : dbislocked

- Valider compte client :
Espaces>Responsable d'agence>Se connecter>Actions>Valider les nouveaux clients

- Valider transaction :
Espaces>Responsable d'agence>Se connecter>Actions>Valider les transactions

- Valider annonce :
Espaces>Responsable d'agence>Se connecter>Actions>Valider les annonces

- Valider rendez-vous : 
Espaces>Responsable d'agence>Se connecter>Actions>Valider les rendez-vous

- Noter les agents :
Espaces>Responsable d'agence>Se connecter>Actions>Noter les agents


Etapes pour créer un compte client :

1) En tant que client : Cliquez sur "Créer un compte" : remplissez les informations à votre guise (notez votre pseudo / mot de passe)
2) En tant que responsable : Cliquez sur "Valider compte client"


Etapes pour soumettre un bien :

1) En tant que client : Connectez vous avec votre identifiant puis cliquez sur "Soumettre annonce" (retenez le type de bien, le type d'environnement, et le type d'habitation)
2) En tant que responsable : Cliquez sur "Valider annonces"


Etapes pour réaliser une transaction :

1) En tant que particulier : Cliquez sur "Rechercher des biens / Prendre rendez-vous / Acquérir bien", arrangez vous pour retrouver le bien que vous avez mis en ligne, choisissez "Acheter le bien" lorsque la page "Prendre rendez-vous ou acheter le bien" s'affiche. Rentrez le nom d'une autre personne quand on vous demande qui vous êtes.
2) En tant que client : Connectez vous sur votre compte et cliquez sur "Choisir Particulier"
3) En tant que responsable : Connectez-vous puis cliquez sur "Valider transactions"


Etapes pour réaliser un rendez-vous : 

1) En tant que particulier : Cliquez sur "Rechercher des biens / Prendre rendez-vous / Acquérir bien", choisissez les critères que vous souhaitez, choisissez "Prendre un rendez-vous" lorsque la page "Prendre rendez-vous ou acheter le bien" s'affiche. 
2) En tant que responsable : Connectez-vous et cliquez sur "Valider les rendez-vous"


Les fonctions notation, ajouter un créneau et voir les statistiques se réalisent de manière indépendante.



## <a name="maj"></a> Et encore... 
Nous sommes content du résultat final de notre projet. Bien qu'il y ait de nombreux petits ajouts qui permettraient d'ajouter des fonctionnalités ou de corriger quelques erreurs, les fonctions principales fonctionnent et l'application est utilisable en l'état. 
Un de nos problèmes concerne peut-être plus la partie orienté objet de notre programme. Quand nous avons choisi ce sujet, nous savions qu'il demanderait un gros travail sur la partie base de données. N'étant pas encore très à l'aise avec ces outils, nous avons décidé de les prendre en main assez rapidement. A partir de là, nous nous sommes peut-être trop concentrés sur cet aspect et n'avons pas utilisé pleinement le potentiel de l'orienté objet.
Dans la classe Transaction, nous avons également rencontré un problème pour la récupération du prix d’estimation du bien immobilier. En effet « 0 € » s’affiche au lieu de la bonne valeur.
Nous aurions aimé pousser un peu plus la partie statistiques avec des critères supplémentaires, il en est de même pour le nombre de biens et leurs attributs et ainsi avoir une recherche de bien plus élaborée.
Nous avons aussi un problème avec la fonctionnalité « valider une demande de rendez-vous ». Malgré nos efforts, nous n’avons pas réussi à empêcher l’erreur « database is locked » qui nous a empêcher de modifier notre table « rendezvous » pour le valider ou le supprimer.

