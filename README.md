# TrainAPI
An API to book trains with REST and SOAP services


Important : avant toute exécution: Merci de mettre les 2 fichiers listeUsers.txt et listeTrains.txt au niveau du dossier d'exécution d'eclipse (dans le folder contenant eclipse.exe). Ce sont ces fichiers qui font office de base de données.


Pour installer :
Pour lancer notre projet, nous partons du principe que Eclipse est opérationnelle sur le PC.
Il vous faut ouvrir Eclipse, ouvrir File>Open Projects from File System, et importer les 3 fichiers nécessaires (SOAPClient, TrainFiltering et SOAPMultiService).

Pour exécuter :
Il vous faut ouvrir TrainFiltering>TrainFiltering>src>restAvailableTrain et faire un clic droit sur available.java, Run AS>Java Application.
Cela lance les services REST.

Pour tester le service REST, il faut faire un clic droit sur TrainFiltering>TrainFiltering>src>client, puis Run AS>Java Application.
Cela lance le client REST.

Ouvrez ensuite SOAPMultiService, ouvrir Java Resources>src>tps.multi.ws et faire un clic droit sur AuthentificationService.java, Run on Server.
Cela lance les services Soap.

Enfin, ouvrez SoapClient, Allez dans Java Resources>src>tps.multi.ws et faire un clic droit sur SoapClient.java, et sélectionnez Run AS>Java Application.
Cela lance le client SOAP.
