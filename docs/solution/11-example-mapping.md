# Example Mapping

## Format de restitution
*(rappel, pour chaque US)*

```markdown
## Define Pivot Currency (post-it jaunes)

> Question (post-it rouge)

### Règle Métier (post-it bleu)

Exemple: 

-Pas de devise 
-> créer une banque
* Erreur: devise Pivot obligatoire

```

```markdown
## Add an exchange rate (post-it jaunes)

> Question (post-it rouge)

### Règle Métier (post-it bleu)

Exemple: 

- Banque : devise EUR
- Taux de change : KRW -> 3 => 10 -> 30KRW 
-> Modifie Taux de change -> 4000€
* 10€ -> 40 000 KRW
```

```markdown
## Convert a Money (post-it jaunes)

> Question (post-it rouge)

### Règle Métier (post-it bleu)

Exemple:

- Banque : EUR (devise pivot)
- TC : USD -> 1.2 -> 1.08
                  -> 1.32
-> convert 100€ -> USD 
* 12 USD

Rule : Round Tapping 
- Banque : EUR
- TC : USD -> 1.2
-> couvert: 10€ -> USD -> €
* 9€ <= resultat <= 11€
 

Banque : EUR
-> 10€ -> 10€ -> 10€


KRW -> USD
KRW -> EUR -> USD

Banque EUR : 
    EUR -> (*) USD
    USD -> (%) EUR
    KRW -> (EUR) USD

```

Vous pouvez également joindre une photo du résultat obtenu en utilisant les post-its.

## Story 1: Define Pivot Currency

```gherkin
As a Foreign Exchange Expert
I want to be able to define a Pivot Currency
So that I can express exchange rates based on it
```

## Story 2: Add an exchange rate
```gherkin
As a Foreign Exchange Expert
I want to add/update exchange rates by specifying: a multiplier rate and a currency
So they can be used to evaluate client portfolios
```

## Story 3: Convert a Money

```gherkin
As a Bank Consumer
I want to convert a given amount in currency into another currency
So it can be used to evaluate client portfolios
```




