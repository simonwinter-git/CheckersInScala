package model.gameBoardComponent.gameBoardBaseImpl

import scala.collection.mutable.ListBuffer
import util.Mover

case class Normal(state: String = "normal", row: Int, col: Int, getColor: String) extends Piece(state, row, col, getColor) {

  var sList: ListBuffer[String] = ListBuffer()
  var sListBlack: ListBuffer[String] = ListBuffer()

  override def toString: String = if (getColor == "black") "\u001B[37mO\u001B[0m" //red
  else "\u001B[30mO\u001B[0m" //blue
  override def posToStr(row: Int, col: Int): String = {
    (col + 65).toChar.toString + (row + 49).toChar.toString
  }


/*

// case 0 = piece_can_capture(curr_piece_location, pieceIsDefined, gamhasColor, canJumpto)
// helper function: ichhabkeinname(pieceIsDefined, pieceHasColor, fieldIsDefined, fieldIsEmpty)
// canJumpTo() -> case ich bin links, case ich bin rechts, ich bin mitte
// checkSchlagliste((row1, row2), (col1, col2), gameboard.piece.isDefined, gameboard.field.isDefined, getColor, isempty)
//
// checkoutofboundsField()
// capturable -> checkt piece defined, farbe richtig, feld leer -- glaube LR nicht wichtig
// SINN: checkNEWSchlagliste(  checkOOB(row,col), capturable()) -> schönere funktion, lesbarer
//      SINN: checkNEWSchlagliste(row,col)


if ((row != 0 && row != 1) && (col != 0 && col != 1) && gameBoard.field(row - 1, col - 1).piece.isDefined && gameBoard.field(row - 1, col - 1).piece.get.getColor == "black" && gameBoard.field(row - 2, col - 2).piece.isEmpty) {
  sList += gameBoard.field(row, col).pos + " " + gameBoard.field(row - 2, col - 2).pos
}
// rechts schlagen
if ((row != 0 && row != 1) && (col != Last && col != (Last-1)) && gameBoard.field(row - 1, col + 1).piece.isDefined && gameBoard.field(row - 1, col + 1).piece.get.getColor == "black" && gameBoard.field(row - 2, col + 2).piece.isEmpty) {
  sList += gameBoard.field(row, col).pos + " " + gameBoard.field(row - 2, col + 2).pos
}


*/

  def checkOOB(row: Int, col: Int, gameBoard: GameBoard): Boolean = {
    val Last: Int = gameBoard.size - 1
    val SndLast: Int = Last - 1
    getColor match {
      case "white" =>
        col match {
          case 0 | 1 | SndLast | Last =>
            if (row == 0 || row == 1) return true
          case _ =>
            return false
        }
      case "black" =>
        col match {
          case 0 | 1 | SndLast | Last =>
            if (Last == 0 || SndLast == 1) return true
          case _ =>
            return false
        }
    }
    false

  }







  override def whiteMovePossible(to: String, gameBoard: GameBoard): Mover = {
    val Last: Int = gameBoard.size - 1

    col match {

      case 0 =>
        // case 0 = piece_can_capture(curr_piece_location, pieceIsDefined, gamhasColor, canJumpto)
        // helper function: ichhabkeinname(pieceIsDefined, pieceHasColor, fieldIsDefined, fieldIsEmpty)
        // canJumpTo() -> case ich bin links, case ich bin rechts, ich bin mitte
        // checkSchlagliste((row1, row2), piece.isDefined)
        //
        if ((row != 0 && row != 1) && gameBoard.field(row - 1, col + 1).piece.isDefined && gameBoard.field(row - 1, col + 1).piece.get.getColor == "black" && gameBoard.field(row - 2, col + 2).piece.isEmpty) {
          sList += gameBoard.field(row, col).pos + " " + gameBoard.field(row - 2, col + 2).pos
        }

        if (sList.isEmpty) {
          if (row != 0 && gameBoard.field(row - 1, col + 1).piece.isEmpty) {
            if (to == gameBoard.posToStr(row - 1, col + 1)) {
              return new Mover(true, "", false)
            } else return new Mover(false, "", false)
          } else return new Mover(false, "", false)
        }

        else if (gameBoard.field(row - 1, col + 1).piece.isDefined && gameBoard.field(row - 1, col + 1).piece.get.getColor == "black") {
          if ((row != 0 && row != 1) && to == gameBoard.posToStr(row - 2, col + 2) && gameBoard.field(row - 2, col + 2).piece.isEmpty) {
            return new Mover(true, posToStr(row - 1, col + 1), false)
          } else return new Mover(false, "", false)
        } else new Mover(false, "", false)


      case Last =>

        if ((row != 0 && row != 1) && gameBoard.field(row - 1, col - 1).piece.isDefined && gameBoard.field(row - 1, col - 1).piece.get.getColor == "black" && gameBoard.field(row - 2, col - 2).piece.isEmpty) {
          sList += gameBoard.field(row, col).pos + " " + gameBoard.field(row - 2, col - 2).pos
        }

        if (sList.isEmpty) {
          if (row != 0 && gameBoard.field(row - 1, col - 1).piece.isEmpty) {
            if (to == gameBoard.posToStr(row - 1, col - 1)) {
              if (Integer.parseInt(to.tail) - 1 == 0) {
                return new Mover(true, "", true)
              } else return new Mover(true, "", false)
            } else return new Mover(false, "", false)
          } else return new Mover(false, "", false)
        }

        else if (gameBoard.field(row - 1, col - 1).piece.isDefined && gameBoard.field(row - 1, col - 1).piece.get.getColor == "black") {
          if ((row != 0 && row != 1) && to == gameBoard.posToStr(row - 2, col - 2) && gameBoard.field(row - 2, col - 2).piece.isEmpty) {
            return new Mover(true, posToStr(row - 1, col - 1), false)
          } else return new Mover(false, "", false)
        } else new Mover(false, "", false)


      case _ =>
        // case 0 = piece_can_capture(curr_piece_location, pieceIsDefined, gamhasColor, canJumpto)
        // helper function: ichhabkeinname(pieceIsDefined, pieceHasColor, fieldIsDefined, fieldIsEmpty)
        // canJumpTo() -> case ich bin links, case ich bin rechts, ich bin mitte
        // checkSchlagliste((row1, row2), (col1, col2), gameboard.piece.isDefined, gameboard.field.isDefined, getColor, isempty)
        //
        // checkoutofboundsField()
        // capturable -> checkt piece defined, farbe richtig, feld leer -- glaube LR nicht wichtig
        // SINN: checkNEWSchlagliste(  checkOOB(row,col), capturable()) -> schönere funktion, lesbarer
        //      SINN: checkNEWSchlagliste(row,col)


        if ((row != 0 && row != 1) && (col != 0 && col != 1) && gameBoard.field(row - 1, col - 1).piece.isDefined && gameBoard.field(row - 1, col - 1).piece.get.getColor == "black" && gameBoard.field(row - 2, col - 2).piece.isEmpty) {
          sList += gameBoard.field(row, col).pos + " " + gameBoard.field(row - 2, col - 2).pos
        }
        // rechts schlagen
        if ((row != 0 && row != 1) && (col != Last && col != (Last-1)) && gameBoard.field(row - 1, col + 1).piece.isDefined && gameBoard.field(row - 1, col + 1).piece.get.getColor == "black" && gameBoard.field(row - 2, col + 2).piece.isEmpty) {
          sList += gameBoard.field(row, col).pos + " " + gameBoard.field(row - 2, col + 2).pos
        }
        if (sList.isEmpty) {
          if (row != 0 && gameBoard.field(row - 1, col - 1).piece.isEmpty && to == gameBoard.posToStr(row - 1, col - 1)) {
            if (Integer.parseInt(to.tail) - 1 == 0) {
              return new Mover(true, "", true)
            } else return new Mover(true, "", false)
          }
          else if (row != 0 && gameBoard.field(row - 1, col + 1).piece.isEmpty && to == gameBoard.posToStr(row - 1, col + 1)) {
            if (Integer.parseInt(to.tail) - 1 == 0) {
              return new Mover(true, "", true)
            } else return new Mover(true, "", false)
          }
          new Mover(false, "", false)
        }
        else if ((row != 0 && row != 1 && col != 1 && col != 0) && gameBoard.field(row - 1, col - 1).piece.isDefined && gameBoard.field(row - 1, col - 1).piece.get.getColor == "black" && gameBoard.field(row - 2, col - 2).piece.isEmpty && to == gameBoard.posToStr(row - 2, col - 2)) {
          if (Integer.parseInt(to.tail) - 1 == 0) {
            new Mover(true, posToStr(row - 1, col - 1), true)
          } else new Mover(true, posToStr(row - 1, col - 1), false)
        }
        else if ((row != 0 && row != 1 && col != Last-1 && col != Last) && gameBoard.field(row - 1, col + 1).piece.isDefined && gameBoard.field(row - 1, col + 1).piece.get.getColor == "black" && gameBoard.field(row - 2, col + 2).piece.isEmpty && to == gameBoard.posToStr(row - 2, col + 2)) {
          if (Integer.parseInt(to.tail) - 1 == 0) {
            new Mover(true, posToStr(row - 1, col + 1), true)
          } else new Mover(true, posToStr(row - 1, col + 1), false)
        }
        else new Mover(false, "", false)

    }
  }


  override def blackMovePossible(to: String, gameBoard: GameBoard): Mover = {
    val Last: Int = gameBoard.size - 1

    col match {
      case 0 =>

        if ((row != gameBoard.size - 1 && row != gameBoard.size - 2) && gameBoard.field(row + 1, col + 1).piece.isDefined && gameBoard.field(row + 1, col + 1).piece.get.getColor == "white" && gameBoard.field(row + 2, col + 2).piece.isEmpty) {
          sListBlack += gameBoard.field(row, col).pos + " " + gameBoard.field(row + 2, col + 2).pos
        }

        if (sListBlack.isEmpty) {
          if (row != Last && gameBoard.field(row + 1, col + 1).piece.isEmpty) {
            if (to == gameBoard.posToStr(row + 1, col + 1)) {
              if (Integer.parseInt(to.tail) - 1 == Last) {
                return new Mover(true, "", true)
              }
              return new Mover(true, "", false)
            } else return new Mover(false, "", false)
          } else return new Mover(false, "", false)
        }

        else if (gameBoard.field(row + 1, col + 1).piece.isDefined && gameBoard.field(row + 1, col + 1).piece.get.getColor == "white") {
          if ((row != gameBoard.size - 1 && row != gameBoard.size - 2) && to == gameBoard.posToStr(row + 2, col + 2) && gameBoard.field(row + 2, col + 2).piece.isEmpty) {
            return new Mover(true, posToStr(row + 1, col + 1), false)
          } else return new Mover(false, "", false)
        } else new Mover(false, "", false)



      case Last =>

        if ((row != gameBoard.size - 1 && row != gameBoard.size - 2) && gameBoard.field(row + 1, col - 1).piece.isDefined && gameBoard.field(row + 1, col - 1).piece.get.getColor == "white" && gameBoard.field(row + 2, col - 2).piece.isEmpty) {
          sListBlack += gameBoard.field(row, col).pos + " " + gameBoard.field(row + 2, col - 2).pos
        }

        if (sListBlack.isEmpty) {
          if (row != Last && gameBoard.field(row + 1, col - 1).piece.isEmpty) {
            if (to == gameBoard.posToStr(row + 1, col - 1)) {
              return new Mover(true, "", false)
            } else return new Mover(false, "", false)
          } else return new Mover(false, "", false)
        }

        else if (gameBoard.field(row + 1, col - 1).piece.isDefined && gameBoard.field(row + 1, col - 1).piece.get.getColor == "white") {
          if ((row != gameBoard.size - 1 && row != gameBoard.size - 2) && to == gameBoard.posToStr(row + 2, col - 2) && gameBoard.field(row + 2, col - 2).piece.isEmpty) {
            return new Mover(true, posToStr(row + 1, col - 1), false)
          } else return new Mover(false, "", false)
        } else new Mover(false, "", false)


      case _ =>

        if ((col != 0 && col != 1 && row != Last && row != Last-1) && gameBoard.field(row + 1, col - 1).piece.isDefined && gameBoard.field(row + 1, col - 1).piece.get.getColor == "white" && gameBoard.field(row + 2, col - 2).piece.isEmpty) {
          sListBlack += gameBoard.field(row, col).pos + " " + gameBoard.field(row + 2, col - 2).pos
        }

        if ((col != Last && col != Last-1 && row != Last && row != Last-1) && gameBoard.field(row + 1, col + 1).piece.isDefined && gameBoard.field(row + 1, col + 1).piece.get.getColor == "white" && gameBoard.field(row + 2, col + 2).piece.isEmpty) {
          sListBlack += gameBoard.field(row, col).pos + " " + gameBoard.field(row + 2, col + 2).pos
        }

        if (sListBlack.isEmpty) {
          if (row != Last && gameBoard.field(row + 1, col - 1).piece.isEmpty && to == gameBoard.posToStr(row + 1, col - 1)) {
            if (Integer.parseInt(to.tail) - 1 == Last) {
              return new Mover(true, "", true)
            } else return new Mover(true, "", false)
          }

          else if (row != Last && gameBoard.field(row + 1, col + 1).piece.isEmpty && to == gameBoard.posToStr(row + 1, col + 1)) {
            if (Integer.parseInt(to.tail) - 1 == Last) {
              return new Mover(true, "", true)
            } else return new Mover(true, "", false)
          }
          new Mover(false, "", false)
        }

        else if ((row != Last && row != Last-1 && col != 0 && col != 1) && gameBoard.field(row + 1, col - 1).piece.isDefined && gameBoard.field(row + 1, col - 1).piece.get.getColor == "white" && gameBoard.field(row + 2, col - 2).piece.isEmpty && to == gameBoard.posToStr(row + 2, col - 2)) {
          if (Integer.parseInt(to.tail) - 1 == Last) {
            new Mover(true, posToStr(row + 1, col - 1), true)
          } else new Mover(true, posToStr(row + 1, col - 1), false)
        }

        else if ((row != Last && row != Last-1 && col != Last && col != Last-1) && gameBoard.field(row + 1, col + 1).piece.isDefined && gameBoard.field(row + 1, col + 1).piece.get.getColor == "white" && gameBoard.field(row + 2, col + 2).piece.isEmpty && to == gameBoard.posToStr(row + 2, col + 2)) {
          if (Integer.parseInt(to.tail) - 1 == Last) {
            new Mover(true, posToStr(row + 1, col + 1), true)
          } else new Mover(true, posToStr(row + 1, col + 1), false)
        }

        else new Mover(false, "", false)

    }
  }
}