package model.gameBoardComponent.gameBoardBaseImpl

import model.gameBoardComponent.GameBoardInterface
import scala.collection.mutable.ListBuffer
import util.Mover

case class Normal(state: String = "normal", row: Int, col: Int, getColor: String) extends Piece(state, row, col, getColor) {

  var sList: ListBuffer[String] = ListBuffer()
  //var sListBlack: ListBuffer[String] = ListBuffer()

  override def toString: String = if (getColor == "black") "\u001B[37mO\u001B[0m" //red
  else "\u001B[30mO\u001B[0m" //blue
  override def posToStr(row: Int, col: Int): String = {
    (col + 65).toChar.toString + (row + 49).toChar.toString
  }


  override def whiteMovePossible(to: String, gameBoard: GameBoard): Mover = {
    val Last: Int = gameBoard.size - 1

    col match {

      case 0 => {

        if ((row != 0 && row != 1) && gameBoard.field(row - 1, col + 1).piece.isDefined && gameBoard.field(row - 1, col + 1).piece.get.getColor == "black" && gameBoard.field(row - 2, col + 2).piece.isEmpty) {
          sList += gameBoard.field(row, col).pos + " " + gameBoard.field(row - 2, col + 2).pos
        }

        if (sList.isEmpty) {
          if (gameBoard.field(row - 1, col + 1).piece.isEmpty) {
            if (to == gameBoard.posToStr(row - 1, col + 1)) {
              sList.clear; return new Mover(true, "", false)
            } else sList.clear; return new Mover(false, "", false)
          } else sList.clear; return new Mover(false, "", false)
        }

        else if (gameBoard.field(row - 1, col + 1).piece.isDefined && gameBoard.field(row - 1, col + 1).piece.get.getColor == "black") {
          if ((row != 0 && row != 1) && to == gameBoard.posToStr(row - 2, col + 2) && gameBoard.field(row - 2, col + 2).piece.isEmpty) {
            sList.clear; return new Mover(true, posToStr(row - 1, col + 1), false) //gameBoard.remove(row - 1, col + 1);
          } else sList.clear; return new Mover(false, "", false)
        } else sList.clear; new Mover(false, "", false)
      }


      case Last => {

        if ((row != 0 && row != 1) && gameBoard.field(row - 1, col - 1).piece.isDefined && gameBoard.field(row - 1, col - 1).piece.get.getColor == "black" && gameBoard.field(row - 2, col - 2).piece.isEmpty) {
          sList += gameBoard.field(row, col).pos + " " + gameBoard.field(row - 2, col - 2).pos
        }

        if (sList.isEmpty) {
          if (gameBoard.field(row - 1, col - 1).piece.isEmpty) {
            if (to == gameBoard.posToStr(row - 1, col - 1)) {
              if (Integer.parseInt(to.tail) - 1 == 0) {
                return new Mover(true, "", true)
              } else return new Mover(true, "", false)
            } else return new Mover(false, "", false)
          } else return new Mover(false, "", false)
        }


        else if (gameBoard.field(row - 1, col - 1).piece.isDefined && gameBoard.field(row - 1, col - 1).piece.get.getColor == "black") {
          if ((row != 0 && row != 1) && to == gameBoard.posToStr(row - 2, col - 2) && gameBoard.field(row - 2, col - 2).piece.isEmpty) {
            sList.clear; return new Mover(true, posToStr(row - 1, col - 1), false) // gameBoard.remove(row - 1, col - 1)
          } else return new Mover(false, "", false)
        } else new Mover(false, "", false)

      }


      case _ => {

        if ((row != 0 && row != 1) && gameBoard.field(row - 1, col - 1).piece.isDefined && gameBoard.field(row - 1, col - 1).piece.get.getColor == "black" && gameBoard.field(row - 2, col - 2).piece.isEmpty) {
          sList += gameBoard.field(row, col).pos + " " + gameBoard.field(row - 2, col - 2).pos
        }

        if ((row != 0 && row != 1) && gameBoard.field(row - 1, col + 1).piece.isDefined && gameBoard.field(row - 1, col + 1).piece.get.getColor == "black" && gameBoard.field(row - 2, col + 2).piece.isEmpty) {
          sList += gameBoard.field(row, col).pos + " " + gameBoard.field(row - 2, col + 2).pos
        }

        if (sList.isEmpty) {
          if (gameBoard.field(row - 1, col - 1).piece.isEmpty && to == gameBoard.posToStr(row - 1, col - 1)) {
            if (Integer.parseInt(to.tail) - 1 == 0) {
              return new Mover(true, "", true)
            } else return new Mover(true, "", false)
          }

          else if (gameBoard.field(row - 1, col + 1).piece.isEmpty && to == gameBoard.posToStr(row - 1, col + 1)) {
            if (Integer.parseInt(to.tail) - 1 == 0) {
              return new Mover(true, "", true)
            } else return new Mover(true, "", false)
          }
          new Mover(false, "", false)
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


  override def blackMovePossible(to: String, gameBoard: GameBoard): Mover = {
    val Last: Int = gameBoard.size - 1

    col match {
    case 0 => {

      if ((row != gameBoard.size - 1 && row != gameBoard.size - 2) && gameBoard.field(row + 1, col + 1).piece.isDefined && gameBoard.field(row + 1, col + 1).piece.get.getColor == "white" && gameBoard.field(row + 2, col + 2).piece.isEmpty) {
        sList += gameBoard.field(row, col).pos + " " + gameBoard.field(row + 2, col + 2).pos
      }

      if (sList.isEmpty) {
        if (gameBoard.field(row + 1, col + 1).piece.isEmpty) {
          if (to == gameBoard.posToStr(row + 1, col + 1)) {
            sList.clear; return new Mover(true, "", false)
          } else sList.clear; return new Mover(false, "", false)
        } else sList.clear; return new Mover(false, "", false)
      }

      else if (gameBoard.field(row + 1, col + 1).piece.isDefined && gameBoard.field(row + 1, col + 1).piece.get.getColor == "white") {
        if ((row != gameBoard.size - 1 && row != gameBoard.size - 2) && to == gameBoard.posToStr(row + 2, col + 2) && gameBoard.field(row + 2, col + 2).piece.isEmpty) {
          sList.clear; return new Mover(true, posToStr(row + 1, col + 1), false) //gameBoard.remove(row - 1, col + 1);
        } else sList.clear; return new Mover(false, "", false)
      } else sList.clear; new Mover(false, "", false)
    }


    case Last => {

      if ((row != gameBoard.size - 1 && row != gameBoard.size - 2) && gameBoard.field(row + 1, col - 1).piece.isDefined && gameBoard.field(row + 1, col - 1).piece.get.getColor == "white" && gameBoard.field(row + 2, col - 2).piece.isEmpty) {
        sList += gameBoard.field(row, col).pos + " " + gameBoard.field(row + 2, col - 2).pos
      }

      if (sList.isEmpty) {
        if (gameBoard.field(row + 1, col - 1).piece.isEmpty) {
          if (to == gameBoard.posToStr(row + 1, col - 1)) {
            if (Integer.parseInt(to.tail) - 1 == 0) {
              return new Mover(true, "", true)
            } else return new Mover(true, "", false)
          } else return new Mover(false, "", false)
        } else return new Mover(false, "", false)
      }


      else if (gameBoard.field(row + 1, col - 1).piece.isDefined && gameBoard.field(row + 1, col - 1).piece.get.getColor == "white") {
        if ((row != gameBoard.size - 1 && row != gameBoard.size - 2) && to == gameBoard.posToStr(row + 2, col - 2) && gameBoard.field(row + 2, col - 2).piece.isEmpty) {
          sList.clear; return new Mover(true, posToStr(row + 1, col - 1), false) // gameBoard.remove(row - 1, col - 1)
        } else return new Mover(false, "", false)
      } else new Mover(false, "", false)

    }


    case _ => {

      if ((row != gameBoard.size - 1 && row != gameBoard.size - 2) && gameBoard.field(row + 1, col - 1).piece.isDefined && gameBoard.field(row + 1, col - 1).piece.get.getColor == "white" && gameBoard.field(row + 2, col - 2).piece.isEmpty) {
        sList += gameBoard.field(row, col).pos + " " + gameBoard.field(row + 2, col - 2).pos
      }

      if ((row != gameBoard.size - 1 && row != gameBoard.size - 2) && gameBoard.field(row + 1, col + 1).piece.isDefined && gameBoard.field(row + 1, col + 1).piece.get.getColor == "white" && gameBoard.field(row + 2, col + 2).piece.isEmpty) {
        sList += gameBoard.field(row, col).pos + " " + gameBoard.field(row + 2, col + 2).pos
      }

      if (sList.isEmpty) {
        if (gameBoard.field(row + 1, col - 1).piece.isEmpty && to == gameBoard.posToStr(row + 1, col - 1)) {
          if (Integer.parseInt(to.tail) - 1 == 0) {
            return new Mover(true, "", true)
          } else return new Mover(true, "", false)
        }

        else if (gameBoard.field(row + 1, col + 1).piece.isEmpty && to == gameBoard.posToStr(row + 1, col + 1)) {
          if (Integer.parseInt(to.tail) - 1 == 0) {
            return new Mover(true, "", true)
          } else return new Mover(true, "", false)
        }
        new Mover(false, "", false)
      }

      else if ((row != gameBoard.size - 1 && row != gameBoard.size - 2) && gameBoard.field(row + 1, col - 1).piece.isDefined && gameBoard.field(row + 1, col - 1).piece.get.getColor == "white" && gameBoard.field(row + 2, col - 2).piece.isEmpty && to == gameBoard.posToStr(row + 2, col - 2)) {
        if (Integer.parseInt(to.tail) - 1 == 0) {
          new Mover(true, posToStr(row + 1, col - 1), true)
        } else new Mover(true, posToStr(row + 1, col - 1), false)
      }

      else if ((row != gameBoard.size - 1 && row != gameBoard.size - 2) && gameBoard.field(row + 1, col + 1).piece.isDefined && gameBoard.field(row + 1, col + 1).piece.get.getColor == "white" && gameBoard.field(row + 2, col + 2).piece.isEmpty && to == gameBoard.posToStr(row + 2, col + 2)) {
        if (Integer.parseInt(to.tail) - 1 == 0) {
          new Mover(true, posToStr(row + 1, col + 1), true)
        } else new Mover(true, posToStr(row + 1, col + 1), false)
      }
      else new Mover(false, "", false)
    }
    /*col match {

      case 0 => {
        if (gameBoard.field(row + 1, col + 1).piece.isEmpty) {
          if (to == gameBoard.posToStr(row + 1, col + 1)) {
            if (Integer.parseInt(to.tail) - 1 == Last) {
              new Mover(true, "", true)
            } else new Mover(true, "", false)
          } else new Mover(false, "", false)
        } else if (gameBoard.field(row + 1, col + 1).piece.isDefined && gameBoard.field(row + 1, col + 1).piece.get.getColor == "white") {
          if ((row != Last + 1) && to == gameBoard.posToStr(row + 2, col + 2) && gameBoard.field(row + 2, col + 2).piece.isEmpty) {
            //gameBoard.remove(row + 1, col + 1)
            new Mover(true, posToStr(row + 1, col + 1), false)
          } else new Mover(false, "", false)
        } else new Mover(false, "", false)
      }

      case Last => {
        if (gameBoard.field(row + 1, col - 1).piece.isEmpty) {
          if (to == gameBoard.posToStr(row + 1, col - 1)) new Mover(true, "", false)
          else new Mover(false, "", false)
        } else if (gameBoard.field(row + 1, col - 1).piece.isDefined && gameBoard.field(row + 1, col - 1).piece.get.getColor == "white") {
          if ((row != Last - 1) && to == gameBoard.posToStr(row + 2, col - 2) && gameBoard.field(row + 2, col - 2).piece.isEmpty) {
            //gameBoard.remove(row + 1, col - 1)
            new Mover(true, posToStr(row + 1, col - 1), false)
          } else new Mover(false, "", false)
        } else new Mover(false, "", false)
      }


      case _ => {

        if (gameBoard.field(row + 1, col - 1).piece.isEmpty && to == gameBoard.posToStr(row + 1, col - 1)) {
          if (Integer.parseInt(to.tail) - 1 == Last) {
            new Mover(true, "", true)
          } else new Mover(true, "", false)
        }

        else if (gameBoard.field(row + 1, col + 1).piece.isEmpty && to == gameBoard.posToStr(row + 1, col + 1)) {
          if (Integer.parseInt(to.tail) - 1 == Last) {
            new Mover(true, "", true)
          } else new Mover(true, "", false)
        }

        else if ((row != Last - 1) && gameBoard.field(row + 1, col - 1).piece.isDefined && gameBoard.field(row + 1, col - 1).piece.get.getColor == "white" && gameBoard.field(row + 2, col - 2).piece.isEmpty && to == gameBoard.posToStr(row + 2, col - 2)) {
          if (Integer.parseInt(to.tail) - 1 == Last) {
            new Mover(true, posToStr(row + 1, col - 1), true)
          } else new Mover(true, posToStr(row + 1, col - 1), false)
        }

        else if ((row != Last - 1) && gameBoard.field(row + 1, col + 1).piece.isDefined && gameBoard.field(row + 1, col + 1).piece.get.getColor == "white" && gameBoard.field(row + 2, col + 2).piece.isEmpty && to == gameBoard.posToStr(row + 2, col + 2)) {
          if (Integer.parseInt(to.tail) - 1 == Last) {
            new Mover(true, posToStr(row + 1, col + 1), true)
          } else new Mover(true, posToStr(row + 1, col + 1), false)
        }

        else new Mover(false, "", false)
      }*/
    }
  }
}