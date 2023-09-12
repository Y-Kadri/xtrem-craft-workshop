# Backlog

## What can be improved in the codebase ?

--- 
Manque de documentation dans chaque méthode et classe, c'est important si un nouveau développeur veut comprendre.

Préfère mettre des sauts de ligne avant un return d'une fonction, il faut que partout soit pareil :

Exemple :
public static Bank withExchangeRate(Currency currency1, Currency currency2, double rate) {
        var bank = new Bank(new HashMap<>());
        bank.addExchangeRate(currency1, currency2, rate);

        return bank;
    }

et 

public double convert(double amount, Currency currency1, Currency currency2) throws MissingExchangeRateException {
        if (!(currency1 == currency2 || exchangeRates.containsKey(currency1 + "->" + currency2))) {
            throw new MissingExchangeRateException(currency1, currency2);
        }
        return currency1 == currency2
                ? amount
                : amount * exchangeRates.get(currency1 + "->" + currency2);
    }

n'ont pas la même facon de "linting"

--- 

la méthode withExchangeRate est mal nommé
(
public static Bank withExchangeRate(Currency currency1, Currency currency2, double rate) {
        var bank = new Bank(new HashMap<>());
        bank.addExchangeRate(currency1, currency2, rate);

        return bank;
    }
)


----

L'énumération vaut mieux faire des sauts de lignes pour améliorer la lisibilité de cette énumération, surtout si on ajoute 20 valeurs de plus

---

Parametre non utilisé dans la classe MoneyCalculator : currency sur les trois méthodes

---

Problème de nommage dans les parametres de fonction.
---

Problème de nommage dans les fonctions de fonction : 
    -mettre multiplicate au lieu de times 

---

Dans divide, gérer le cas d'erreur de la division par zero

Il ne faut pas qu'il y est une multiplication inférieur à zero dans la méthode times

---

Message d'erreur non explicite pour l'exection MissingExchangeRateException

---

