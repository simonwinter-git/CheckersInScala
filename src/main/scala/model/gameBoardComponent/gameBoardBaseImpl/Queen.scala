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

        x = 1
        while (gameBoard.field(row - x, col + x).piece.isEmpty) {
          x += 1
        }
        if ((toCol - (x-1) <= 0) && ((x-1) >= (row - toRow)) && ((toCol-col) - (row-toRow) == 0) && (toCol - col > 0) && (toRow - row < 0)) return new Mover(true, "", false) // linker rand nach rechts oben

        x = 1
        while (gameBoard.field(row + x, col + x).piece.isEmpty) {
          x += 1
        }
        if ((toCol - (x-1) <= 0) && ((x-1) >= (toRow - row)) && ((toCol-col) - (toRow-row) == 0) && (toCol - col > 0) && (toRow - row > 0)) return new Mover(true, "", false) // linker rand nach rechts unten

        x = 1
        while (gameBoard.field(row - x, col + x).piece.isEmpty) {
          x += 1
        }
        if (toCol == x+1 && row-toRow == x+1 && gameBoard.field(row - x, col + x).piece.get.getColor == "black" && gameBoard.field(row - (x+1), col + (x+1)).piece.isEmpty && row - x > 0) return new Mover(true, posToStr(row - x, col + x), false) // schlagen linker rand nach rechts oben

        x = 1
        while (gameBoard.field(row + x, col + x).piece.isEmpty) {
          x += 1
        }
        if (toCol == x+1 && toRow-row == x+1 && gameBoard.field(row + x, col + x).piece.get.getColor == "black" && gameBoard.field(row + (x+1), col + (x+1)).piece.isEmpty && row + x < Last) return new Mover(true, posToStr(row + x, col + x), false) // schlagen linker rand nach rechts unten

        else new Mover(false, "", false)
      }


      case Last => {

        x = 1
        while (gameBoard.field(row - x, col - x).piece.isEmpty) {
          x += 1
        }
        if (((col - toCol) - (x-1) <= 0) && ((x-1) >= (row - toRow)) && ((col-toCol) - (row-toRow) == 0) && (toCol - col < 0) && (toRow - row < 0)) return new Mover(true, "", false) // rechter rand nach links oben

        x = 1
        while (gameBoard.field(row + x, col - x).piece.isEmpty) {
          x += 1
        }
        if (((col - toCol) - (x-1) <= 0) && ((x-1) >= (toRow - row)) && ((col-toCol) - (toRow-row) == 0) && (toCol - col < 0) && (toRow - row > 0)) return new Mover(true, "", false) // rechter rand nach links unten

        x = 1
        while (gameBoard.field(row - x, col - x).piece.isEmpty) {
          x += 1
        }
        if (col - toCol == x+1 && row-toRow == x+1 && gameBoard.field(row - x, col - x).piece.get.getColor == "black" && gameBoard.field(row - (x+1), col - (x+1)).piece.isEmpty && row - x > 0) return new Mover(true, posToStr(row - x, col - x), false) // schlagen rechter rand nach links oben

        x = 1
        while (gameBoard.field(row + x, col - x).piece.isEmpty) {
          x += 1
        }
        if (col - toCol == x+1 && toRow-row == x+1 && gameBoard.field(row + x, col - x).piece.get.getColor == "black" && gameBoard.field(row + (x+1), col - (x+1)).piece.isEmpty && row + x < Last) return new Mover(true, posToStr(row + x, col - x), false) // schlagen rechter rand nach links unten

        else new Mover(false, "", false)
      }

      case _ => {

        x = 1
        while (gameBoard.field(row - x, col + x).piece.isEmpty) {
          x += 1
        }
        if (((toCol-col) - (x-1) <= 0) && ((x-1) >= (row - toRow)) && ((toCol-col) - (row-toRow) == 0) && (toCol - col > 0) && (toRow - row < 0)) return new Mover(true, "", false) //mitte zu rechts oben


        x = 1
        while (gameBoard.field(row + x, col + x).piece.isEmpty) {
          x += 1
        }
        if (((toCol-col) - (x-1) <= 0) && ((x-1) >= (toRow - row)) && ((toCol-col) - (toRow-row) == 0) && (toCol - col > 0) && (toRow - row > 0)) return new Mover(true, "", false) //mitte zu rechts unten


        //x = 1
        //while (gameBoard.field(row - x, col - x).piece.isEmpty) {
        //  x += 1
        //}
        //if (((col - toCol) - (x-1) <= 0) && ((x-1) >= (row - toRow)) && ((col-toCol) - (row-toRow) == 0) && (toCol - col < 0) && (toRow - row < 0)) //mitte zu links hoch




        else new Mover(false, "", false)
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
