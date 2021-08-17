"# moneytransfert" 

<h3>Class Diagram :</h3>

<div itemprop="text" class="Box-body p-0 blob-wrapper data type-text  gist-border-0">
    <div class="text-center p-3">
          <span class="border-wrap">
            <img src="./src/main/resources/static/images/README/ClassDiagramsEntity.png?raw=true" alt="ClassDiagramsEntity.png">
          </span>
    </div>
</div>

<h3>MPD :</h3>

<div itemprop="text" class="Box-body p-0 blob-wrapper data type-text  gist-border-0">
    <div class="text-center p-3">
          <span class="border-wrap">
            <img src="./src/main/resources/static/images/README/MPD.png?raw=true" alt="MPD.png">
          </span>
    </div>
</div>

<h3>Fichiers sql de cration de la base de données et d'injection de données de test :</h3>

<a href=".\sql-scripts\InitTestsData.sql">InitTestsData.sql</a>

<h3>0- Créer et connecter manuellement la base de données MySql</h3> 
<div style="margin-left: 3em">
1 : avec le nom "moneytransfert" avec aucune table dedans

2 : Mettre à jour le fichier application.properties avec le user et le mot de passe de la base de donnees

spring.datasource.username=<br>
spring.datasource.password=

3 : Tester la connexion en lançant la classe test :
com.paymybuddy.moneytransfert.app.config.DataBaseConfigTest

</div>
<h3>1- Lancer l'application une première fois pour la création automatique des tables </h3> pour créer les tables de la base de donnée : Mettre à jour les paramètres suivants dans le fichier application.properties

<div style="margin-left: 3em">
1 : Vérifier que la ligne suivante est à "create" : 
<b>spring.jpa.hibernate.ddl-auto = create</b>

2 : Vérifier que la ligne suivante est commentée (#) : <b>#</b>spring.datasource.platform=update

3 : Lancer l'application

4 : A la fin du lancement de l'application, vérifer la création des tables, des clés étrangères, des séquances

</div>

<h3>2- Redémarrer l'aplication, pour l'injection des données de sécurité</h3> avec les paramètres suivants :
<div style="margin-left: 3em">

1 : "update" : <b>spring.jpa.hibernate.ddl-auto = update</b>

2 : Décommenter la ligne suivante : <b>spring.datasource.platform=update</b>

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
Add a new Windos environment variable "spring.datasource.password" with the right password value corresponding to your DB<br>
Remove the "spring.datasource.password" parameter from application.properties file, or comment it<br>
run the app on the command line : mvn spring-boot:run
<br><br>


</div>
