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
    print("toCol: "+toCol+" toRow: "+toRow+"\n")

    col match {
      case 0 => {
        while (gameBoard.field(row - x, col + x).piece.isEmpty) {
          x += 1
        }
        if (((x-1) - toCol <= 0) && ((x-1) - toRow <= 0) && ((toCol-col) - (toRow-row) == 0) && (toCol - col > 0) && (toRow - row < 0)) new Mover(true, "", false) // Linker Rand nach rechts oben
        //else new Mover(false, "", false)

        x = 1
        while (gameBoard.field(row + x, col + x).piece.isEmpty) {
          x += 1
        }
        if (((x-1) - toCol <= 0) && ((x-1) - toRow <= 0) && ((toCol-col) - (toRow-row) == 0) && (toCol - col > 0) && (toRow - row > 0)) new Mover(true, "", false) // Linker Rand nach rechts unten
        //else new Mover(false, "", false)

        x = 1
        while (gameBoard.field(row - x, col + x).piece.isEmpty) {
          x += 1
        }
        if (toCol == x+1 && row-toRow == x+1 && gameBoard.field(row - x, col + x).piece.get.getColor == "black") new Mover(true, posToStr(row - x, col + x), false)
// bis hier alles korrekt
        x = 1
        while (gameBoard.field(row + x, col + x).piece.isEmpty) {
          x += 1
        }
        if (toCol == x+1 && row-toRow == x+1) new Mover(true, posToStr(toRow + 1, toCol - 1), false)
        else new Mover(false, "", false)
      }

      case 0 => {
        if (gameBoard.field(row - 1, col + 1).piece.isEmpty) {
          if (to == gameBoard.posToStr(row - 1, col + 1)) new Mover(false, "", false)
          else new Mover(false, "", false)
        } else if (gameBoard.field(row + 1, col + 1).piece.isEmpty){
          if (to == gameBoard.posToStr(row + 1, col + 1)) new Mover(false, "", false)
          else new Mover(false, "", false)
        } else if (gameBoard.field(row - 1, col + 1).piece.isDefined && gameBoard.field(row - 1, col + 1).piece.get.getColor == "black") {
          if ((row != 0 && row != 1) && to == gameBoard.posToStr(row - 2, col + 2) && gameBoard.field(row - 2, col + 2).piece.isEmpty) {
            new Mover(false, posToStr(row - 1, col + 1), false)
          } else new Mover(false, "", false)
        } else new Mover(false, "", false)
      }


      case Last => {
        if (gameBoard.field(row - 1, col - 1).piece.isEmpty) {
          if (to == gameBoard.posToStr(row - 1, col - 1)) {
            if (Integer.parseInt(to.tail) - 1 == 0) {
              new Mover(false, "", false)
            } else new Mover(false, "", false)
          } else new Mover(false, "", false)
        } else if (gameBoard.field(row - 1, col - 1).piece.isDefined && gameBoard.field(row - 1, col - 1).piece.get.getColor == "black") {
          if ((row != 0 && row != 1) && to == gameBoard.posToStr(row - 2, col - 2) && gameBoard.field(row - 2, col - 2).piece.isEmpty) {
            //gameBoard.remove(row - 1, col - 1)
            new Mover(false, posToStr(row - 1, col - 1), false)
          } else new Mover(false, "", false)
        } else new Mover(false, "", false)
      }

      case _ => {if (gameBoard.field(row - 1, col - 1).piece.isEmpty && to == gameBoard.posToStr(row - 1, col - 1)) {
          if (Integer.parseInt(to.tail) - 1 == 0) {
            new Mover(false, "", false)
          } else new Mover(false, "", false)
        }

        else if (gameBoard.field(row - 1, col + 1).piece.isEmpty && to == gameBoard.posToStr(row - 1, col + 1)) {
          if (Integer.parseInt(to.tail) - 1 == 0) {
            new Mover(false, "", false)
          } else new Mover(false, "", false)
        }

        else if ((row != 0 && row != 1) && gameBoard.field(row - 1, col - 1).piece.isDefined && gameBoard.field(row - 1, col - 1).piece.get.getColor == "black" && gameBoard.field(row - 2, col - 2).piece.isEmpty && to == gameBoard.posToStr(row - 2, col - 2)) {
          if (Integer.parseInt(to.tail) - 1 == 0) {
            new Mover(false, posToStr(row - 1, col - 1), false)
          } else new Mover(false, posToStr(row - 1, col - 1), false)
        }

        else if ((row != 0 && row != 1) && gameBoard.field(row - 1, col + 1).piece.isDefined && gameBoard.field(row - 1, col + 1).piece.get.getColor == "black" && gameBoard.field(row - 2, col + 2).piece.isEmpty && to == gameBoard.posToStr(row - 2, col + 2)) {
          if (Integer.parseInt(to.tail) - 1 == 0) {
            new Mover(false, posToStr(row - 1, col + 1), false)
          } else new Mover(false, posToStr(row - 1, col + 1), false)
        }

        else new Mover(false, "", false)
      }
    }
  }
  override def blackMovePossible(to: String, gameBoard: GameBoard): Mover = new Mover(true, "", false)
}
