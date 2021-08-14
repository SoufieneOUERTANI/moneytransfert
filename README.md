"# moneytransfert" 

<h3>0- Créer la base de données MySql</h3> avec le nom "moneytransfert" avec aucune table dedans

<h3>1- Lancer l'application une <b>première fois</b></h3> pour créer la base de donnée avec les paramètres suivants : Dans le fichier application.prperties

1-1 : Vérifier que la ligne suivante est à "create" : 
<b>spring.jpa.hibernate.ddl-auto = create</b>

1-2 : Vérifier que la ligne suivante est commentée (#) : <b>#</b>spring.datasource.platform=init

1-3 : A la fin du lancement de l'application,vérifer la création des tables, des clés étrangères, des séquances

<h3>2- <b>Relancer</b> l'aplication</h3> avec les paramètres suivants :

2-1 : "update" : <b>spring.jpa.hibernate.ddl-auto = update</b>

1-2 : Décommenter la ligne suivante : <b>spring.datasource.platform=init</b>

1-3 : A la fin du lancement de l'application,vérifer que les données du fichier "data-init.sql" ont été bien injectées.

<h3>3- Recommenter la ligne </h3><b>#</b>spring.datasource.platform=init