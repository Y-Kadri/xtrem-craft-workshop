# Concept

> La classe `BankBuilder` est responsable de la création d'instances de la classe `Bank`, avec la possibilité de définir une devise pivot et d'ajouter des taux de change.

## Properties

- **pivotCurrency :** La devise pivot utilisée comme référence pour les taux de change.
- **exchangeRate :** Une collection de taux de change associés à différentes devises.

## Responsibilities

- **Création d'une Banque :** Construit des instances de la classe `Bank` avec une devise pivot et des taux de change spécifiés.
- **Définition de la Devise Pivot :** Permet de spécifier la devise pivot pour la banque en cours de construction.
- **Ajout de Taux de Change :** Permet d'ajouter des taux de change entre la devise pivot et d'autres devises.
- **Construction de la Banque :** Finalise la construction de la banque en utilisant les informations fournies et les taux de change ajoutés.

## Invariants

- **Devise Pivot Définie :** La devise pivot doit être spécifiée avant de construire la banque.
- **Taux de Change Requis :** Au moins un taux de change doit être ajouté pour construire une banque valide.


## Collaborators

- **Bank :** La classe `BankBuilder` collabore étroitement avec la classe `Bank` pour créer et configurer des instances de banque.
- **Currency :** La classe utilise l'énumération `Currency` pour représenter différentes devises.