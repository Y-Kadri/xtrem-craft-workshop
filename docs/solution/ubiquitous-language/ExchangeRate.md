# Concept

> Une classe ExchangeRate représente un taux de change entre deux devises dans le contexte d'une banque.

## Properties

- **stringForExchange :** Une chaîne représentant la paire de devises associée au taux de change (par exemple, "USD->EUR").
- **rate :** Le taux de change associé à la paire de devises.

## Responsibilities

- Stocke et récupère des informations sur un taux de change spécifique entre deux devises, facilitant ainsi la gestion des conversions monétaires au sein de la banque.
-Cette classe sert de conteneur pour les informations relatives à un taux de change spécifique, facilitant ainsi la gestion des conversions monétaires au sein de la banque.


## Invariants

- La paire de devises (`stringForExchange`) doit être correctement définie pour représenter une conversion valide entre deux devises.
- Le taux de change (`rate`) doit être un nombre réel valide représentant la conversion entre les devises associées.


## Collaborators

- **Banque (Bank) :** La classe `ExchangeRate` est souvent utilisée en collaboration avec une instance de la classe `Bank` pour stocker et gérer les taux de change.
- **Currency :** Les devises associées au taux de change sont généralement représentées par l'énumération `Currency`.
  
