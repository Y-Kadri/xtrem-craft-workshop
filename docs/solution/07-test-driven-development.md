# Test Driven Development

## 3 faits et une question à propos du Test Driven Development

Individuellement, donnez les 3 idées les plus importantes sur le TDD et une question qui reste en suspend.

Yanis KADRI : 
```
-Faire des tests avant le code
-Permet de produire du code qui répondra aux cas du test 
-Réflexion continue sur la conception : Le TDD favorise une conception modulaire et évolutive du code. La nécessité de créer des tests incite à réfléchir constamment à la structure du code et à son évolution.
Une question qui reste en suspend

Le TDD a-t-il sa place sur un projet où les besoin du client sont très simples mais changent régulièrement
```

Florian BERGER :
```
Les 3 idées les plus importantes du TDD :
- Le TDD : Le développement est conditionné par les tests
- Comment faire du TDD : Faire du code sale pour faire du code propre
- Méchanique de test : Act --> Arrange --> Assert

Une question qui reste en suspend
Le TDD a-t-il sa place sur un projet où les besoin du client sont très simples mais changent régulièrement ?
```

Robin PLUVLIAUX
```
L'introduction de nouveaux rôles en mob programming, à l'instar du TDD, met en avant l'importance de conditionner le développement par les tests, soulignant le rôle crucial du navigateur dans la coordination du groupe.
   
De manière similaire à l'idée de "Faire du code sale pour faire du code propre" dans le TDD, le mob programming suggère une amélioration progressive du code au fil du temps, parallèle à l'évolution du code projeté par le TDD.

La mécanique de test du TDD (Act --> Arrange --> Assert) trouve une résonance dans la coordination des rôles du mob programming, où le navigateur guide le conducteur dans l'action de coder.

```

Benjamin GANDELIN
```
-Avant même de penser à développer ou même intégrer dans le code un ajout d'une fonctionnalité quelconque, il faut d'abord réfléchir en amont à toutes les failles de cet ajout. En listant tout les tests possibles pour couvrir tout les cas d'erreurs potentielles ou non.

-Après s'être assuré que les tests passaient tous très bien, il faut donc rédigé une première version du code dans un premier temps.

-Enfin le dernier point qui me paraît être très important c'est la relecture du code aussi appelé le "clean code". Ce dernier a pour but de passer en revu l'intégralité du code qui vient d'être écrit pour s'attarder sur différents points afin de respecter les conventions de code.

Reste à savoir si cette méthode aide réellement à développé un code aussi propre que si nous ne passions pas par les tests en premiers lieux ?
```

Aurélien LUXEY
```
Les 3 idées les plus importantes sur le TDD

. Lorsque que l'on doit développer une fonctionnalité, il faut d'abord coder les tests qui vont échouer et ensuite écrire le code pour que les tests cette fois passent. 
. Le TDD dans un premier temps va fournir un code de mauvaise qualité, c'est avec le temps que le code du projet doit s'améliorer.
Puisque le TDD est basé sur les tests, il est impératif que les tests couvrent 100 % du code.

Comment est-ce qu'en utilisant le TDD, on est sûr que les tests couvrent tous les cas et non pas que les cas de base qui sont simples à imaginer ?
```


## Mob programming

> Qu'avez-vous appris de l'introduction de nouveaux rôles en mob programming ?

Yanis : 
```
Le mob programming, c'est quand toute l'équipe travaille ensemble sur un même problème. Il y a une personne qui écrit le code, c'est le pilote, et les autres, ce sont les navigateurs. Les navigateurs aident en donnant des idées, en regardant le code, et en disant ce qu'ils pensent. Cela permet à tout le monde de collaborer, de partager ce qu'ils savent, et de résoudre les problèmes ensemble. Ensuite y a aussi le Mob qui permet de réfléchir ensemble pour écrire des solutions/résoudre des problèmes. Ce permet mieux de rapproché l'équipe.
```

Florian
```
Ce que j'ai appris de l'introduction de nouveaux rôles en mob programming
Pendant ce cours, j'ai pû comprendre l'intérêt du rôle de navigateur.
En effet, lorsque nous programmons en groupe et que chaque personne donne son avis, il est difficile pour le driver de se concentrer sur l'écriture.
J'ai donc pu constater que ce rôle de navigateur que je ne connaissais pas permet un travail en groupe beaucoup plus rapide et coordonné.
```

Robin PLUVLIAUX
```
Le rôle de navigateur, mis en avant dans le mob programming, rejoint la question persistante du TDD sur la place de cette méthode dans les projets où les besoins du client sont simples mais changent régulièrement.
L'expérience du cours sur les nouveaux rôles en mob programming souligne l'efficacité du rôle de navigateur, comparable à la nécessité de guider le conducteur dans le TDD pour maintenir une écriture de code cohérente malgré les divers avis du groupe.
```


Benjamin GANDELIN
```
Pour moi, les trois rôles ont autant d'importance les uns que les autres. Nous avons d'une part ceux qui recherchent l'informations, qui donnent les différentes idées. Ces derniers sont missionné de faire leur recherches sous les directives d'une sorte de leader. Et d'autre part, nous avons celui qui est placé devant le clavier et qui agence les idées ainsi que les recherches des autres membres du groupe. Le mob programming interdit en aucun cas d'inverser les rôles, d'écouter les propositions de la personne devant le clavier ou même de donner le clavier à l'un des autres membres devant normalement faire des propositions et des recherches. Le tout est de rester cohérent avec l'idée de base de la méthode.
```

Aurélien LUXEY
```
Les rôles du mob programming 

Le rôle sur lequel j'ai beaucoup appris en mob programming est le driver. C'est celui qui écrit du code seulement en se faisant guider par les autres.
C'est un rôle qui demande tout d'abord des connaissances techniques puisqu'il doit savoir coder rapidement ce que le Mob propose, mais aussi il ne doit pas prendre au débat, ce qui est quelque chose qui peut-être dur au début. 
```