# CheckersInScala

[![Coverage Status](https://coveralls.io/repos/github/simonwinter-git/CheckersInScala/badge.svg?branch=master)](https://coveralls.io/github/simonwinter-git/CheckersInScala?branch=master)

WELCOME TO CHECKERS!
-

How to win:
- The game ends if one player has less than 2 pieces or is unable to move with any of its pieces. 

Preparation:
- You are playing on a checkered board with the classic size 8 x 8 or the more comon size 10 x 10 
- One does use only the red fields
- Every player gets the first three rows on each side filled up with normal pieces on every red field

<img src="Bild1.png" width = "275" height = "275"> <img src="Bild2.png" width = "275" height = "275"> <img src="Bild3.png" width = "275" height = "275">

The moves:
- A normal piece can just move forward
- Is an enemys piece on the field right on the next diagonal field, and the next field afterwards is empty, you can capture the enemys piece by jumping over and removing it from the gameboard.
- If you want to move a piece, but the piece is able to capture the other players piece, you have to.

<img src="Bild4.png" width = "275" height = "275"> <img src="Bild5.png" width = "275" height = "275"> <img src="Bild6.png" width = "275" height = "275">

- Is there an opportunity to capture a next piece in the same play move, you have to until there is no more possible move
- A piece which is reaching the opposite side of the gameboard is turning into a queen
- The Queen can move in every diagonal direction and as many field as you want as long as the fields are empty
- Is an enemys piece on a field and the field afterwards is emtpy as well, the queen can capture it over the whole distance of its move
