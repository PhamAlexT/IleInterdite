# Notes de développement

Le sujet est disponible [ici](https://www.lri.fr/~blsk/POGL/IleInterdite.pdf).

## Structure globale du programme

Nous n'avons pas réellement eu de problème pour cette partie là du projet. Nous avons juste eu à séparer en plusieurs packages/classes [ce fichier de Monsieur Balabonski](https://www.lri.fr/~blsk/POGL/Notes/Conway.html).

Les problèmes de développement venaient principalement de l'interface graphique, de son utilisation etc. car c'est quelque chose de nouveau pour nous.
Nous allons détailler ici plusieurs problèmes rencontrés lors du développement.

## Déplacement d'un joueur

Nous avons pensé à 2 moyens pour déplacer un joueur:
1. Via des boutons
2. Via la souris en cliquant sur la grille

Nous avons choisi la deuxième méthode et avons alors été confronté à ce problème: *Comment enlever la possibilité de se déplacer une fois qu'un joueur ait fait 3 actions?*

Le but est alors de vérifier autant de fois que c'est possible si la limite d'action a été atteinte dans la grille et lorsque c'est le cas d'enlever dans la grille les MouseListener.

Au final, notre solution a été de faire les vérifications et éventuels suppressions dans la méthode paint de Grille qui est énormément appelé dans le programme.

Une autre solution serait peut-être d'utiliser [EventQueue](https://docs.oracle.com/javase/7/docs/api/java/awt/EventQueue.html) mais n'ayant ni la maîtrise ni la certitude que cela pouvait être une solution, nous ne sommes pas penché dessus.

## Multijoueur et déplacement

Nous avons eu ici un problème qui était le suivant: Lorsqu'on voulait déplacer un personnage, on déplaçait tout les personnages qui pouvaient se déplacer vers la zone sélectionnée en accord avec les règles de déplacement. Ceci était dû au fait que pour un tour, nous faisons une boucle sur les Joueurs ([voir ici - classe DéplacementJoueur](https://gitlab.u-psud.fr/alexandre.pham/ile-interdite/commit/63772ab8ec0506dc5a2fdd47850491bb5764d192)) alors que ce que l'on veut c'est un tour = un joueur.

On avait détecté ce problème assez tôt du fait qu'on avait à ce stade, initialisé les joueurs près les uns des autres, il aurait été plus difficile de trouver ce bug et de le résoudre si on avait directement initialisé la position des joueurs de manière aléatoire.

Avec un peu de remaniement, nous avons pu résoudre ce problème.
1. Nous avons déjà créer une classe abstraite *ActionJoueur* qui est implémentée par toutes les actions que nous allons implémenter. Par ailleurs, cette classe prend **un Joueur** en constructeur et pas une île comme précédement.
2. Ensuite, nous avons créer une classe *ActionsJoueurs* (notons le s en plus) qui a en attribut toutes les actions pour un Joueur, cela permet de vérifier plus facilement si le quota d'action est atteint.
3. Enfin, nous avons créer une classe *DéroulementPartie* qui gère la partie, le joueur actuel et ces actions. Cette classe est d'ailleurs énormément liée aux différentes vues.