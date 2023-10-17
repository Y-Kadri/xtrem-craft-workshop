# Example Mapping

## Format de restitution
*(rappel, pour chaque US)*


## As a Bank Customer (post-it jaunes)

- Pas de taux de change ?
- Quelle solution pour convertir la devise (A et B) pour donner C ? Qu'est-ce qu'il se passe s'il y a énormément devises ?
- Pas de taux de change si on ajoute deux monnaies similaires ?


### Règle Métier (post-it bleu)

Exemple :
```
- 5 USD + 10 EUR
- Taux de change EUR --> USD = 1.2
--> Evaluate USD
* Result = 17 USD

- 5 USD + 10 USD
--> Evaluate USD
* Result = 15 USD

- 5 USD + 10 EUR
- Taux de change USD --> EUR = 0.8
--> Evaluate EUR
* Result = 14.1 EUR

- 5 USD + 10 EUR
- Taux de change EUR --> KRW = 2
- Taux de change USD --> KRW = 3
--> Evaluate KRW
* Result = 35 KRW

```
Vous pouvez également joindre une photo du résultat obtenu en utilisant les post-its.

## Évaluation d'un portefeuille




