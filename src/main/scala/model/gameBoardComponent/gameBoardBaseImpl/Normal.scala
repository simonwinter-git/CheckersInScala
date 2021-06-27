package model.gameBoardComponent.gameBoardBaseImpl

import model.gameBoardComponent.GameBoardInterface
import util.Mover

case class Normal(state: String = "normal", row: Int, col: Int, getColor: String) extends Piece(state, row, col, getColor) {


  override def sList: List[String] = List("")
  override def toString: String = if (getColor == "black") "\uD83D\uDD34" //red
    else "\uD83D\uDD35" //blue
  override def posToStr(row: Int, col: Int): String = {(col + 65).toChar.toString + (row + 49).toChar.toString}

  override def whiteMovePossible(to: String, gameBoard: GameBoard): Mover = {
  val Last: Int = gameBoard.size - 1
  /*
    for {
      r <- 0 until gameBoard.size
      c <- 0 until gameBoard.size
    } if (gameBoard.field(r, c).piece.get.getColor == "white") {

      col match {

        case 0 => {
          if (gameBoard.field(row - 1, col + 1).piece.get.getColor == "black" && gameBoard.field(row - 2, col + 2).piece.isEmpty) {
            sList.+(gameBoard.field(r, c).pos)
          }
        }

        case Last => {
          if (gameBoard.field(row - 1, col - 1).piece.isEmpty) {
            true
          } else false
        }

        case _ => {
          if (gameBoard.field(row - 1, col + 1).piece.isEmpty && gameBoard.field(row - 1, col - 1).piece.isEmpty) {
            true
          } else false
        }

      }

    }
    */
    col match {

      case 0 => {
        if (gameBoard.field(row - 1, col + 1).piece.isEmpty) {
          if (to == gameBoard.posToStr(row - 1, col + 1)) new Mover(true, "", false)
          else new Mover(false, "", false)
        } else if (gameBoard.field(row - 1, col + 1).piece.isDefined && gameBoard.field(row - 1, col + 1).piece.get.getColor == "black") {
            if ((row != 0 && row != 1) && to == gameBoard.posToStr(row - 2, col + 2) && gameBoard.field(row - 2, col + 2).piece.isEmpty) {
              gameBoard.remove(row - 1, col + 1)
              new Mover(true, posToStr(row-1, col+1), false)
            }
            else new Mover(false, "", false)
        } else new Mover(false, "", false)
      }

      case Last => {
        if (gameBoard.field(row - 1, col - 1).piece.isEmpty) {
          if (to == gameBoard.posToStr(row - 1, col - 1)) {
            if (Integer.parseInt(to.tail)-1 == 0) {
              print("yaaass")
              new Mover(true, "", true)
            }
            else new Mover(true, "", false)
          } else new Mover(false, "", false)
        } else if (gameBoard.field(row - 1, col - 1).piece.isDefined && gameBoard.field(row - 1, col - 1).piece.get.getColor == "black") {
            if ((row != 0 && row != 1) && to == gameBoard.posToStr(row - 2, col - 2) && gameBoard.field(row - 2, col - 2).piece.isEmpty) {
              gameBoard.remove(row - 1, col - 1)
              new Mover(true, posToStr(row-1, col-1), false)
            }
            else new Mover(false, "", false)
        } else new Mover(false, "", false)
      }

      case _ => {
        if (gameBoard.field(row - 1, col - 1).piece.isEmpty && to == gameBoard.posToStr(row - 1, col - 1)) new Mover(true, "", false)
        else if (gameBoard.field(row - 1, col + 1).piece.isEmpty && to == gameBoard.posToStr(row - 1, col + 1)) new Mover(true, "", false)
        else if ((row != 0 && row != 1) && gameBoard.field(row - 1, col - 1).piece.isDefined && gameBoard.field(row - 1, col - 1).piece.get.getColor == "black" && gameBoard.field(row - 2, col - 2).piece.isEmpty && to == gameBoard.posToStr(row - 2, col - 2)) new Mover(true, posToStr(row-1, col-1), false)
        else if ((row != 0 && row != 1) && gameBoard.field(row - 1, col + 1).piece.isDefined && gameBoard.field(row - 1, col + 1).piece.get.getColor == "black" && gameBoard.field(row - 2, col + 2).piece.isEmpty && to == gameBoard.posToStr(row - 2, col + 2)) new Mover(true, posToStr(row-1, col+1), false)
        else new Mover(false, "", false)
      }

    }

  }

  override def blackMovePossible(to: String, gameBoard: GameBoard): Mover = {
    val Last: Int = gameBoard.size - 1
    /*
      for {
        r <- 0 until gameBoard.size
        c <- 0 until gameBoard.size
      } if (gameBoard.field(r, c).piece.get.getColor == "white") {

        col match {

          case 0 => {
            if (gameBoard.field(row - 1, col + 1).piece.get.getColor == "black" && gameBoard.field(row - 2, col + 2).piece.isEmpty) {
              sList.+(gameBoard.field(r, c).pos)
            }
          }

          case Last => {
            if (gameBoard.field(row - 1, col - 1).piece.isEmpty) {
              true
            } else false
          }

          case _ => {
            if (gameBoard.field(row - 1, col + 1).piece.isEmpty && gameBoard.field(row - 1, col - 1).piece.isEmpty) {
              true
            } else false
          }

        }

      }
      */
    col match {

      case 0 => {
        if (gameBoard.field(row + 1, col + 1).piece.isEmpty) {
          if (to == gameBoard.posToStr(row + 1, col + 1)) new Mover(true, "", false)
          else new Mover(false, "", false)
        } else if (gameBoard.field(row + 1, col + 1).piece.isDefined && gameBoard.field(row + 1, col + 1).piece.get.getColor == "white") {
          if ((row != Last-1 && row != Last-2) && to == gameBoard.posToStr(row + 2, col + 2) && gameBoard.field(row + 2, col + 2).piece.isEmpty) {
            gameBoard.remove(row + 1, col + 1)
            new Mover(true, posToStr(row+1, col+1), false)
          }
          else new Mover(false, "", false)
        } else new Mover(false, "", false)
      }

      case Last => {
        if (gameBoard.field(row + 1, col - 1).piece.isEmpty) {
          if (to == gameBoard.posToStr(row + 1, col - 1)) new Mover(true, "", false)
          else new Mover(false, "", false)
        } else if (gameBoard.field(row + 1, col - 1).piece.isDefined && gameBoard.field(row + 1, col - 1).piece.get.getColor == "white") {
          if ((row != Last-1 && row != Last-2) && to == gameBoard.posToStr(row + 2, col - 2) && gameBoard.field(row + 2, col - 2).piece.isEmpty) {
            gameBoard.remove(row + 1, col - 1)
            new Mover(true, posToStr(row+1, col-1), false)
          }
          else new Mover(false, "", false)
        } else new Mover(false, "", false)
      }

      case _ => {
        print(row)
        if (gameBoard.field(row + 1, col - 1).piece.isEmpty && to == gameBoard.posToStr(row + 1, col - 1)) new Mover(true, "", false)
        else if (gameBoard.field(row + 1, col + 1).piece.isEmpty && to == gameBoard.posToStr(row + 1, col + 1)) new Mover(true, "", false)
        else if ((row != Last-1 && row != Last-2) && gameBoard.field(row + 1, col - 1).piece.isDefined && gameBoard.field(row + 1, col - 1).piece.get.getColor == "white" && gameBoard.field(row + 2, col - 2).piece.isEmpty && to == gameBoard.posToStr(row + 2, col - 2)) new Mover(true, posToStr(row+1, col-1), false)
        else if ((row != Last-1 && row != Last-2) && gameBoard.field(row + 1, col + 1).piece.isDefined && gameBoard.field(row + 1, col + 1).piece.get.getColor == "white" && gameBoard.field(row + 2, col + 2).piece.isEmpty && to == gameBoard.posToStr(row + 2, col + 2)) new Mover(true, posToStr(row+1, col+1), false)
        else new Mover(false, "", false)
      }

    }

  }

}
