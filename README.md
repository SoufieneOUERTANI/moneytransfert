"# moneytransfert" 

<h3>0- Créer et connecter manuellement la base de données MySql</h3> 
<div style="margin-left: 3em">
1 : avec le nom "moneytransfert" avec aucune table dedans

2 : Mettre à jour e fichier application.properties avec le user et le mot de passe de la base de donnees

spring.datasource.username=<br>
spring.datasource.password=

</div>
<h3>1- Lancer l'application une première fois pour la création automatique des tables </h3> pour créer les tables de la base de donnée : Mettre à jour les paramètres suivants dans le fichier application.properties

<div style="margin-left: 3em">
1 : Vérifier que la ligne suivante est à "create" : 
<b>spring.jpa.hibernate.ddl-auto = create</b>

2 : Vérifier que la ligne suivante est commentée (#) : <b>#</b>spring.datasource.platform=init

3 : Lancer l'application

4 : A la fin du lancement de l'application, vérifer la création des tables, des clés étrangères, des séquances

</div>

<h3>2- Redémarrer l'aplication, pour l'injection des données de sécurité</h3> avec les paramètres suivants :
<div style="margin-left: 3em">

1 : "update" : <b>spring.jpa.hibernate.ddl-auto = update</b>

2 : Décommenter la ligne suivante : <b>spring.datasource.platform=init</b>

3 : Redémarrer l'application

3 : A la fin du lancement de l'application,vérifer que les données du fichier "data-init.sql" ont été bien injectées.

</div>

<h3>3- Recommenter la ligne </h3>
<div style="margin-left: 3em">

<b>#</b>spring.datasource.platform=init

</div>


<h3>3- Annexe </h3>
<div style="margin-left: 3em">

<b>To run the app under command line</b><br>
=> mvn spring-boot:run
<br><br>

<b>Windows, connaitre la version de la JVM en la ligne de commande</b><br>
=> java -version
<br><br>

<b>Windows, connaitre le path de ja JVM en la ligne de commande</b><br>
=> for %I in (java.exe) do @echo %~$PATH:I
<br><br>

<b>Windows, connaitre les variables d'environnement</b><br>
=> env
<br><br>

<b>Winwdows, To externalize DB password => </b><br>
Add a new Windos environment variable "spring.datasource.password" with the right value corresponding to your DB<br>
Remove it from the "spring.datasource.password" parameter from application.properties file<br>
run the app on the commande line : mvn spring-boot:run
<br><br>


</div>
