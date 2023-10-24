# Concept

>  Money représente une somme d'argent et est utilisée pour gérer des montants financiers.

## Properties

- Amount : Un nombre réel (double) représentant la valeur monétaire.
- Currency : Un objet de la classe Currency qui spécifie la devise de la somme d'argent.

## Responsibilities

- La classe Money a pour responsabilité de représenter des montants d'argent avec leurs devises associées, de permettre des opérations mathématiques sur ces montants tout en vérifiant la compatibilité des devises, et de gérer la validation des montants pour éviter les valeurs négatives ou infinies.

## Invariants

- Le montant d'argent (amount) doit être un nombre réel positif et fini.
- Les opérations d'addition, de multiplication et de division ne sont possibles qu'entre deux objets Money de la même devise

## Collaborators

- Currency (Devise) : Une classe ou un concept qui spécifie la devise associée à une somme d'argent (Money). La devise est utilisée pour garantir la compatibilité lors des opérations financières.
