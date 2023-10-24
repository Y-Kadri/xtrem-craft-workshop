# Concept

> Concept : Un portefeuille (Portfolio) est une classe qui permet de gérer un ensemble de devises et leurs montants associés. Il est utilisé pour suivre et évaluer la valeur totale des avoirs dans différentes devises.

## Properties

- 'wallet' : Une carte (Map) qui stocke les montants de différentes devises dans le portefeuille. La clé est un objet de type Currency (représentant une devise) et la valeur est un montant de type double.

## Responsibilities

- Gérer un ensemble de devises et les montants associés, permettant l'ajout de montants, le calcul de la valeur totale dans une devise spécifiée, et la cohérence des montants de chaque devise.

## Invariants

- Le montant pour chaque devise dans le portefeuille doit être un nombre réel (double) positif ou nul.

## Collaborators

- La classe Bank qui permet gérer les taux de change entre devises et d'effectuer des conversions monétaires
- L'énumération Currency : Une énumération contenant les devise de la banque