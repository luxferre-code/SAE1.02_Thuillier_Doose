IthyphalGame
===========

Développé par Valentin Thuillier
Contact : valentin.thuillier.etu@univ-lille.fr
Et par Jules Doose
Contact: jules.doose.etu@univ-lille.fr

# Présentation de IthyphalGame

Vous enfermez dans un donjon d'Ithyphal ou des monstres vous empêchez de sortir. Pour sortir, il faut monter au dernier étage du donjon.
Il est impératif de répondre à des questions car, dans ce donjon, tous ce qui permet d'avancer est régis par des questions !
Alors, accrochez-vous bien à vos connaissances, car sans elles, impossible d'avancer !

# Utilisation de IthyphalGame

Afin d'utiliser le projet, il suffit de taper les commandes suivantes dans un terminal :

```
./compile.sh
```
Permet la compilation des fichiers présents dans 'src' et création des fichiers '.class' dans 'classes'

```
./run.sh IthyphalGame
```
Permet le lancement du jeu

# Modification possible
## Code source

Vous pouvez modifier les variables, nous vous conseillons de ne pas y toucher si vous ne savez pas ce que vous faites
Vous pouvez modifier les **valeurs** de la ligne __6 à 34__.

## Les cartes

Les cartes sont sauvegardé sous un format spécifique. Qui demande une grande rigueur !
Donc si vous voulez le modifier, merci de prendre en compte ces particularités

- Les maps sont sauvegarder dans le dossier ressources/maps/mapXYZ.csv
- X = etage | Y = ligne | Z = colonne
- Les caractère autorisés sont W, #, E, M, L, U, D
- W = mur | # = vide | E = sortie | M = monstre | L = coffre | U = escalier montant | D = escalier descendant
  
# Auteurs

[Valentin Thuillier](mailto:valentin.thuillier.etu@univ-lille.fr)
[Jules Doose](mailto:jules.doose.etu@univ-lille.fr)