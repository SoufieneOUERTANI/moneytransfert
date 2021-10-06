"# moneytransfert" 

<h3> Prototype d’une application bancaire </h3>  
« moneytransfert », permettant aux clients de transférer de l'argent pour gérer leurs finances ou payer leurs amis 
<br><br>
<lu>
<li> Les nouveaux utilisateurs doivent pouvoir s'enregistrer à l'aide d'un identifiant e-mail unique.
<li> Les utilisateurs doivent pouvoir se connecter à partir de leurs comptes dans la base de données.
<li> Après la connexion, les utilisateurs peuvent ajouter des personnes à leurs listes à partir de leur adresse e-mail
<li> Un utilisateur peut verser de l'argent sur son compte dans notre application
<li> À partir du solde disponible, les utilisateurs peuvent effectuer des paiements à tout autre utilisateur enregistré sur l'application
<li> À tout moment, les utilisateurs peuvent transférer l'argent vers leur compte bancaire
<li> À chaque transaction, nous prélevons un pourcentage de 0,5 % pour monétiser l’application
</lu>

<h3> Diagramme de classe :</h3>

<div itemprop="text" class="Box-body p-0 blob-wrapper data type-text  gist-border-0">
    <div class="text-center p-3">
          <span class="border-wrap">
            <img src="./src/main/resources/static/images/README/ClassDiagram.png?raw=true" alt="ClassDiagramsEntity.png">
          </span>
    </div>
</div>

<h3>Modèle Physique de Données :</h3>

<div itemprop="text" class="Box-body p-0 blob-wrapper data type-text  gist-border-0">
    <div class="text-center p-3">
          <span class="border-wrap">
            <img src="./src/main/resources/static/images/README/MPD.png?raw=true" alt="MPD.png">
          </span>
    </div>
</div>



<h3>Script d'initialisation de la base de données(MySql) :</h3>

<div itemprop="text" class="Box-body p-0 blob-wrapper data type-text  gist-border-0">
    <div class="text-center p-3">
        <span class="border-wrap">
            <a href=".\src\main\resources\static\sql\scripts\InitSchema&InjectSampleData.sql">InitSchema&InjectSampleData.sql</a>
        </span>
    </div>
</div>

<h3> Choix architecture </h3>
<lu>
<li> Spring Boot
<li> Spring MVC
<li> Spring Data JPA
<li> Maven
<li> Thymeleaf
<li> Spring Security
<li> Thymeleaf security
<li> MySQL(MYSQL spring Driver)
<li> Junit/AssertJ
<li> JaCoCo
<li> Logback
</lu>

<h3>Lancement de l'application</h3>
<p style="margin-left: 3em">
<br><b> Ces paramètres doivent être renseignés pour la connexion à la base de données</b><br>
spring.datasource.username=<br>
spring.datasource.password=<br>
<br><b>Tester la connexion en lançant la classe test : com.paymybuddy.moneytransfert.app.config.DataBaseConfigTest</b><br>
<br><b> Initialistion de la base de données seuleement lors du premier lancement</b><br>
spring.datasource.platform=init<br>
<br><b> Une fois la base de données initialisée lors du premier lancement, par la suite, commenter cette ligne </b><br>
#spring.datasource.platform=init<br>


<h2>Annexe </h2>
<div style="margin-left: 3em">

<b>To run the app under command line</b><br>
=> mvn spring-boot:run
<br><br>

<b>Windows, connaitre la version de la JVM en la ligne de commande</b><br>
=> java -version
<br><br>

<b>Windows, connaitre le path de la JVM en la ligne de commande</b><br>
=> for %I in (java.exe) do @echo %~$PATH:I
<br><br>

<b>Windows, connaitre les variables d'environnement</b><br>
=> env
<br><br>

<b>Winwdows, To externalize DB password </b>
<lu style="margin-left: 3em">
<li> Add a new Windos environment variable "spring.datasource.password" with the right password value corresponding to your DB</li>
<li> Remove the "spring.datasource.password" parameter from application.properties file, or comment it</li>
<li> run the app on the command line : mvn spring-boot:run</li>
<lu/>

</div>