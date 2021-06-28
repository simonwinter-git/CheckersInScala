package model.gameBoardComponent.gameBoardBaseImpl

import util.Mover

case class Queen(state: String = "queen", row: Int, col: Int, getColor: String) extends Piece(state, row, col, getColor) {

  override def sList: List[String] = List("")
  override def toString: String = if (getColor == "black") "\uD83D\uDFE0"//"\uD83D\uDFE0" //orange/black
  else "\uD83D\uDFE3"//"\uD83D\uDFE3" //purple/white



  override def whiteMovePossible(to: String, gameBoard: GameBoard): Mover = new Mover(true, "", true) {
    val Last: Int = gameBoard.size - 1

    col match {
      case 0 => {
        if (gameBoard.field(row - 1, col + 1).piece.isEmpty) {
          if (to == gameBoard.posToStr(row - 1, col + 1)) new Mover(true, "", true)
          else new Mover(false, "", false)
        } else if (gameBoard.field(row + 1, col + 1).piece.isEmpty){
          if (to == gameBoard.posToStr(row + 1, col + 1)) new Mover(true, "", true)
          else new Mover(false, "", false)
        } else if (gameBoard.field(row - 1, col + 1).piece.isDefined && gameBoard.field(row - 1, col + 1).piece.get.getColor == "black") {
          if ((row != 0 && row != 1) && to == gameBoard.posToStr(row - 2, col + 2) && gameBoard.field(row - 2, col + 2).piece.isEmpty) {
            gameBoard.remove(row - 1, col + 1)
            new Mover(true, posToStr(row - 1, col + 1), false)
          } else new Mover(false, "", false)
        } else new Mover(false, "", false)
      }

      case Last => {
        if (gameBoard.field(row - 1, col - 1).piece.isEmpty) {
          if (to == gameBoard.posToStr(row - 1, col - 1)) {
            if (Integer.parseInt(to.tail) - 1 == 0) {
              new Mover(true, "", true)
            } else new Mover(true, "", false)
          } else new Mover(false, "", false)
        } else if (gameBoard.field(row - 1, col - 1).piece.isDefined && gameBoard.field(row - 1, col - 1).piece.get.getColor == "black") {
          if ((row != 0 && row != 1) && to == gameBoard.posToStr(row - 2, col - 2) && gameBoard.field(row - 2, col - 2).piece.isEmpty) {
            gameBoard.remove(row - 1, col - 1)
            new Mover(true, posToStr(row - 1, col - 1), false)
          } else new Mover(false, "", false)
        } else new Mover(false, "", false)
      }

      case _ => {
        if (gameBoard.field(row - 1, col - 1).piece.isEmpty && to == gameBoard.posToStr(row - 1, col - 1)) {
          if (Integer.parseInt(to.tail) - 1 == 0) {
            new Mover(true, "", true)
          } else new Mover(true, "", false)
        }

        else if (gameBoard.field(row - 1, col + 1).piece.isEmpty && to == gameBoard.posToStr(row - 1, col + 1)) {
          if (Integer.parseInt(to.tail) - 1 == 0) {
            new Mover(true, "", true)
          } else new Mover(true, "", false)
        }

        else if ((row != 0 && row != 1) && gameBoard.field(row - 1, col - 1).piece.isDefined && gameBoard.field(row - 1, col - 1).piece.get.getColor == "black" && gameBoard.field(row - 2, col - 2).piece.isEmpty && to == gameBoard.posToStr(row - 2, col - 2)) {
          if (Integer.parseInt(to.tail) - 1 == 0) {
            new Mover(true, posToStr(row - 1, col - 1), true)
          } else new Mover(true, posToStr(row - 1, col - 1), false)
        }

        else if ((row != 0 && row != 1) && gameBoard.field(row - 1, col + 1).piece.isDefined && gameBoard.field(row - 1, col + 1).piece.get.getColor == "black" && gameBoard.field(row - 2, col + 2).piece.isEmpty && to == gameBoard.posToStr(row - 2, col + 2)) {
          if (Integer.parseInt(to.tail) - 1 == 0) {
            new Mover(true, posToStr(row - 1, col + 1), true)
          } else new Mover(true, posToStr(row - 1, col + 1), false)
        }

        else new Mover(false, "", false)
      }
    }
  }
  override def blackMovePossible(to: String, gameBoard: GameBoard): Mover = new Mover(true, "", true)
}
