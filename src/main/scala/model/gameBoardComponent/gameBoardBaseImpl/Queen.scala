package model.gameBoardComponent.gameBoardBaseImpl

import util.Mover

import scala.collection.mutable.ListBuffer

case class Queen(state: String = "queen", row: Int, col: Int, getColor: String) extends Piece(state, row, col, getColor) {

  var sList: ListBuffer[String] = ListBuffer()
  var sListBlack: ListBuffer[String] = ListBuffer()
  override def toString: String = if (getColor == "black") "\u001B[37mQ\u001B[0m"//"\uD83D\uDFE0" //orange/black
  else "\u001B[30mQ\u001B[0m"//"\uD83D\uDFE3" //purple/white



  override def whiteMovePossible(to: String, gameBoard: GameBoard): Mover = {
    //var blockPieceR: Piece = gameBoard.getPiece()
    var x = 1
    var toRow: Int = Integer.parseInt(to.tail) - 1
    var toCol: Int = to.charAt(0).toInt - 65
    val Last: Int = gameBoard.size - 1

    col match {
      case 0 => {

        x = 0
        while (gameBoard.field(row - x, col + x).piece.isEmpty) {
          x += 1
        }
        if ((row != 0 && row != 1) && gameBoard.field(row - x, col + x).piece.isDefined && gameBoard.field(row - x, col + x).piece.get.getColor == "black" && gameBoard.field(row - (x + 1), col + (x + 1)).piece.isEmpty) {
          sList += gameBoard.field(row, col).pos + " " + gameBoard.field(row - (x + 1), col + (x + 1)).pos
        }

        x = 0
        while (gameBoard.field(row + x, col + x).piece.isEmpty) {
          x += 1
        }
        if ((row != 0 && row != 1) && gameBoard.field(row + x, col + x).piece.isDefined && gameBoard.field(row + x, col + x).piece.get.getColor == "black" && gameBoard.field(row + (x + 1), col + (x + 1)).piece.isEmpty) {
          sList += gameBoard.field(row, col).pos + " " + gameBoard.field(row + (x + 1), col + (x + 1)).pos
        }

        if (sList.isEmpty) {
        x = 0

        while ((col + x <= Last && row - x >= 0) && gameBoard.field(row - x, col + x).piece.isEmpty || (x == 0)) {
          x += 1
        }
        if ((toCol - (x - 1) <= 0) && ((x - 1) >= (row - toRow)) && ((toCol - col) - (row - toRow) == 0) && (toCol - col > 0) && (toRow - row < 0)) return new Mover(true, "", false) // linker rand nach rechts oben #CHECKED

        x = 0

        while ((col + x <= Last && row + x <= Last) && gameBoard.field(row + x, col + x).piece.isEmpty || (x == 0)) {
          x += 1
        }
        if ((toCol - (x - 1) <= 0) && ((x - 1) >= (toRow - row)) && ((toCol - col) - (toRow - row) == 0) && (toCol - col > 0) && (toRow - row > 0)) return new Mover(true, "", false) // linker rand nach rechts unten #CHECKED
        } //end sList.isEmpty

        x = 0
        while ((col + x <= Last && row - x >= 0) && gameBoard.field(row - x, col + x).piece.isEmpty || x == 0) {
          x += 1
        }
        if (toCol == x+1 && row-toRow == x+1 && gameBoard.field(row - x, col + x).piece.get.getColor == "black" && gameBoard.field(row - (x+1), col + (x+1)).piece.isEmpty && row - x > 0) return new Mover(true, posToStr(row - x, col + x), false) // schlagen linker rand nach rechts oben
          //  dest = 6, 5+1 & 6 - 0 = 5+1 &&                  6-5 = 1, 0+5 = 5                                                      6-6 == 0, 0 + 6 = 6
        x = 0
        while ((col + x <= Last && row + x <= Last) && gameBoard.field(row + x, col + x).piece.isEmpty || x == 0) {
          x += 1
        }
        if (toCol == x+1 && toRow-row == x+1 && gameBoard.field(row + x, col + x).piece.get.getColor == "black" && gameBoard.field(row + (x+1), col + (x+1)).piece.isEmpty && row + x < Last) return new Mover(true, posToStr(row + x, col + x), false) // schlagen linker rand nach rechts unten

        else new Mover(false, "", false)
      }


      case Last => {

        x = 0
        while (gameBoard.field(row - x, col - x).piece.isEmpty || x == 0) {
          x += 1
        }
        if ((row != 0 && row != 1) && gameBoard.field(row - x, col - x).piece.isDefined && gameBoard.field(row - x, col - x).piece.get.getColor == "black" && gameBoard.field(row - (x + 1), col - (x - 1)).piece.isEmpty) {
          sList += gameBoard.field(row, col).pos + " " + gameBoard.field(row - (x + 1), col - (x + 1)).pos
        }

        x = 0
        while (gameBoard.field(row + x, col - x).piece.isEmpty || x == 0) {
          x += 1
        }
        if ((row != 0 && row != 1) && gameBoard.field(row + x, col - x).piece.isDefined && gameBoard.field(row + x, col - x).piece.get.getColor == "black" && gameBoard.field(row + (x + 1), col - (x + 1)).piece.isEmpty) {
          sList += gameBoard.field(row, col).pos + " " + gameBoard.field(row + (x + 1), col - (x + 1)).pos
        }


        if (sList.isEmpty) {
        x = 0
        while ((col - x >= 0 && row - x >= 0) && gameBoard.field(row - x, col - x).piece.isEmpty || x == 0) {
          x += 1
        }
        if (((col - toCol) - (x - 1) <= 0) && ((x - 1) >= (row - toRow)) && ((col - toCol) - (row - toRow) == 0) && (toCol - col < 0) && (toRow - row < 0)) return new Mover(true, "", false) // rechter rand nach links oben

        x = 0
        while ((col - x >= 0 && row + x <= Last) && gameBoard.field(row + x, col - x).piece.isEmpty || x == 0) {
          x += 1
        }
        if (((col - toCol) - (x - 1) <= 0) && ((x - 1) >= (toRow - row)) && ((col - toCol) - (toRow - row) == 0) && (toCol - col < 0) && (toRow - row > 0)) return new Mover(true, "", false) // rechter rand nach links unten
        } //end sList.isEmpty

        x = 0
        while ((col - x >= 0 && row - x >= 0) && gameBoard.field(row - x, col - x).piece.isEmpty || x == 0) {
          x += 1
        }
        if (col - toCol == x+1 && row-toRow == x+1 && gameBoard.field(row - x, col - x).piece.get.getColor == "black" && gameBoard.field(row - (x+1), col - (x+1)).piece.isEmpty && row - x > 0) return new Mover(true, posToStr(row - x, col - x), false) // schlagen rechter rand nach links oben

        x = 0
        while ((col - x >= 0 && row + x <= Last) && gameBoard.field(row + x, col - x).piece.isEmpty || x == 0) {
          x += 1
        }
        if (col - toCol == x+1 && toRow-row == x+1 && gameBoard.field(row + x, col - x).piece.get.getColor == "black" && gameBoard.field(row + (x+1), col - (x+1)).piece.isEmpty && row + x < Last) return new Mover(true, posToStr(row + x, col - x), false) // schlagen rechter rand nach links unten

        else new Mover(false, "", false)
      }

      case _ => {

        x = 0
        while ((col + x <= Last && row - x >= 0) && gameBoard.field(row - x, col + x).piece.isEmpty || (x == 0)) {
          x += 1
        }
        if ((row-x != 0 && row != 1 && col != Last-1 && col+x != Last) && gameBoard.field(row - (x-1), col + (x-1)).piece.isDefined && gameBoard.field(row - x, col + x).piece.get.getColor == "black" && gameBoard.field(row - (x+1), col + (x+1)).piece.isEmpty) {
          sList += gameBoard.field(row, col).pos + " " + gameBoard.field(row - (x + 1), col + (x + 1)).pos
        }

        x = 0
        while ((col + x <= Last && row + x <= Last) && gameBoard.field(row + x, col + x).piece.isEmpty || (x == 0)) {
          x += 1
        }
        if ((row != Last-1 && row+x != Last && col != Last-1 && col+x != Last) && gameBoard.field(row + (x-1), col + (x-1)).piece.isDefined && gameBoard.field(row + x, col + x).piece.get.getColor == "black" && gameBoard.field(row + (x+1), col + (x+1)).piece.isEmpty) {
          sList += gameBoard.field(row, col).pos + " " + gameBoard.field(row + (x + 1), col + (x + 1)).pos
        }

        x = 0
        while ((col - x >= 0 && row - x >= 0) && gameBoard.field(row - x, col - x).piece.isEmpty || (x == 0)) {
          x += 1
        }
        if ((row-x != 0 && row != 1 && col-x != 0 && col != 1) && gameBoard.field(row - x, col - x).piece.isDefined && gameBoard.field(row - x, col - x).piece.get.getColor == "black" && gameBoard.field(row - (x+1), col - (x+1)).piece.isEmpty) {
          sList += gameBoard.field(row, col).pos + " " + gameBoard.field(row - (x + 1), col - (x + 1)).pos
        }

        x = 0
        while ((col - x >= 0 && row + x <= Last) && gameBoard.field(row + x, col - x).piece.isEmpty || (x == 0)) {
          x += 1
        }
        print(x)
        if ((row != Last-1 && row+x != Last && col-x != 0 && col != 1) && gameBoard.field(row + (x-1), col - (x-1)).piece.isDefined && gameBoard.field(row + x, col - x).piece.get.getColor == "black" && gameBoard.field(row + (x+1) , col - (x+1)).piece.isEmpty) {
          sList += gameBoard.field(row, col).pos + " " + gameBoard.field(row + (x + 1), col - (x + 1)).pos
        }

        if (sList.isEmpty) {
        x = 0
        while ((col + x <= Last && row - x >= 0) && gameBoard.field(row - x, col + x).piece.isEmpty || (x == 0)) { //CHECKED
          x += 1
        }
        if (((toCol - col) - (x - 1) <= 0) && ((x - 1) >= (row - toRow)) && ((toCol - col) - (row - toRow) == 0) && (toCol - col > 0) && (toRow - row < 0)) return new Mover(true, "", false) //mitte nach rechts oben
          // (6 - 1) - (6 - 1)                        5     5 - 0               6 - 1          5 - 0                   6 - 1                0 - 5

        x = 0
        while ((col + x <= Last && row + x <= Last) && gameBoard.field(row + x, col + x).piece.isEmpty || (x == 0)) {
          x += 1
        }
        if (((toCol - col) - (x - 1) <= 0) && ((x - 1) >= (toRow - row)) && ((toCol - col) - (toRow - row) == 0) && (toCol - col > 0) && (toRow - row > 0)) return new Mover(true, "", false) //mitte nach rechts unten


        x = 0
        while ((col - x >= 0 && row - x >= 0) && (gameBoard.field(row - x, col - x).piece.isEmpty || (x == 0))) {
          x += 1
        }
        if (((col - toCol) - (x - 1) <= 0) && ((x - 1) >= (row - toRow)) && ((col - toCol) - (row - toRow) == 0) && (toCol - col < 0) && (toRow - row < 0)) return new Mover(true, "", false) //mitte nach links oben


        x = 0
        while ((col - x >= 0 && row + x <= Last) && gameBoard.field(row + x, col - x).piece.isEmpty || (x == 0)) {
          x += 1
        }
        if (((col - toCol) - (x - 1) <= 0) && ((x - 1) >= (toRow - row)) && ((col - toCol) - (toRow - row) == 0) && (toCol - col < 0) && (toRow - row > 0)) return new Mover(true, "", false) //mitte nach links unten

        }

//schlagen:

        x = 0
        while ((col + x <= Last) && (row - x >= 0) && gameBoard.field(row - x, col + x).piece.isEmpty || x == 0) {
          x += 1
        }
        if (toCol - col == x+1 && row-toRow == x+1 && gameBoard.field(row - x, col + x).piece.get.getColor == "black" && gameBoard.field(row - (x+1), col + (x+1)).piece.isEmpty && row - x > 0) return new Mover(true, posToStr(row - x, col + x), false) // schlagen mitte nach rechts oben

        x = 0
        while ((col + x <= Last) && (row + x <= Last) && gameBoard.field(row + x, col + x).piece.isEmpty || x == 0) {
          x += 1
        }
        if (toCol - col == x+1 && toRow-row == x+1 && gameBoard.field(row + x, col + x).piece.get.getColor == "black" && gameBoard.field(row + (x+1), col + (x+1)).piece.isEmpty && row + x < Last) return new Mover(true, posToStr(row + x, col + x), false) // schlagen mitte nach rechts unten

        x = 0
        while ((col - x >= 0) && (row - x >= 0) && gameBoard.field(row - x, col - x).piece.isEmpty || x == 0) {
          x += 1
        }
        if (col - toCol == x+1 && row-toRow == x+1 && gameBoard.field(row - x, col - x).piece.get.getColor == "black" && gameBoard.field(row - (x+1), col - (x+1)).piece.isEmpty && row - x > 0) return new Mover(true, posToStr(row - x, col - x), false) // schlagen mitte nach links oben

        x = 0
        while ((col - x >= 0) && (row + x <= Last) && gameBoard.field(row + x, col - x).piece.isEmpty || x == 0) {
          x += 1
        }
        if (col - toCol == x+1 && toRow-row == x+1 && gameBoard.field(row + x, col - x).piece.get.getColor == "black" && gameBoard.field(row + (x+1), col - (x+1)).piece.isEmpty && row + x < Last) return new Mover(true, posToStr(row + x, col - x), false) // schlagen mitte nach links unten
        else new Mover(false, "", false)
      }



      }
    }

  override def blackMovePossible(to: String, gameBoard: GameBoard): Mover = new Mover(true, "", false)
}
//Schlagcombo