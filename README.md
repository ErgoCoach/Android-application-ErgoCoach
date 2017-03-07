# Android-application-ErgoCoach

Nous sommes un groupe de 5 étudiants en 1ère année de master à l'ECE Paris (école d'ingénieurs). 
Dans le cadre d'un projet pluridisciplinaire en équipe, nous avons décidé de partager notre projet en open source.

Notre projet consiste à réaliser un siège muni de capteurs qui analyserait la posture assise d'un utilisateur toutes les 10 minutes. 
Les données collectées sont envoyées à une application Android. Le code de notre application est mis en ligne et permet d'accéder à un 
compte personnalisé avec des bilans par période, des conseils adaptés, des postures en temps réel...

Nous pouvons donc distinguer 2 parties :
- la partie siège : nous nous sommes fournis un siège de bureau et nous y avons placé 9 capteurs (4 au niveau de l'assise, devant-derrière- 
droite-gauche, et 5 au niveau du dossier, haut droit-haut gauche-milieu droit-milieu gauche-bas). 
Ce sont des capteurs de force de pression de 50kg chacun pour l'assise et 20kg chacun pour le dossier). Ils sont chacun branchés à un 
amplificateur puis à l'entrée d'une pin de l'arduino Leonardo. Tous les fils clk sont branchés à la pin 2, puis les fils de données 
respectivement aux pins 3,4,5,6,7,8,9,12 et 13. Pour envoyer les données par Bluetooth à l'application Android nous avons utilisé un 
module HC-05 que nous branchons aux pins 10 et 11. Le code Arduino est aussi en ligne, et 2 librairies supplémentaires sont nécessaires, 
une pour les capteurs et l'autre pour le bluetooth.

- la partie application : elle permet à chaque utilisateur de se créer un compte et d'accéder à un espace personnel. 
Par la suite, plusieurs fonctionnalités sont à sa disposition. La fonction 'temps réel' qui lui permet de visualiser sa posture, la 
fonction 'bilan' qui permet d'obtenir un bilan de sa posture sur un interval de temps choisi, la fonction 'évolution' qui permet de voir 
la courbe d'évolution de la posture de l'utilisateur au cours du temps, la fonction 'coaching' qui lui permet de recevoir des conseils 
adaptés...
