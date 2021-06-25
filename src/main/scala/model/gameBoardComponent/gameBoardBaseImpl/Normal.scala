package model.gameBoardComponent.gameBoardBaseImpl

case class Normal(state: String = "normal", row: Int, col: Int, getColor: String) extends Piece(state, row, col, getColor) {


  override def sList: List[String] = List("")
  override def toString: String = if (getColor == "black") "\uD83D\uDD34" //red
    else "\uD83D\uDD35" //blue

  override def whiteMovePossible(to: String, gameBoard: GameBoard): Boolean = {
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
          if (to == gameBoard.posToStr(row - 1, col + 1)) true
          else false
        } else if (gameBoard.field(row - 1, col + 1).piece.isDefined && gameBoard.field(row - 1, col + 1).piece.get.getColor == "black") {
            if (to == gameBoard.posToStr(row - 2, col + 2) && gameBoard.field(row - 2, col + 2).piece.isEmpty) {
              gameBoard.remove(row - 1, col + 1)
              true
            }
            else false
        } else false
      }

      case Last => {
        if (gameBoard.field(row - 1, col - 1).piece.isEmpty) {
          if (to == gameBoard.posToStr(row - 1, col - 1)) true
          else false
        } else if (gameBoard.field(row - 1, col - 1).piece.isDefined && gameBoard.field(row - 1, col - 1).piece.get.getColor == "black") {
            if (to == gameBoard.posToStr(row - 2, col - 2) && gameBoard.field(row - 2, col - 2).piece.isEmpty) {
              gameBoard.remove(row - 1, col - 1)
              true
            }
            else false
        } else false
      }

      case _ => {
        if (gameBoard.field(row - 1, col - 1).piece.isEmpty && to == gameBoard.posToStr(row - 1, col - 1)) true
        else if (gameBoard.field(row - 1, col + 1).piece.isEmpty && to == gameBoard.posToStr(row - 1, col + 1)) true
        else if (gameBoard.field(row - 1, col - 1).piece.isDefined && gameBoard.field(row - 1, col - 1).piece.get.getColor == "black" && gameBoard.field(row - 2, col - 2).piece.isEmpty && to == gameBoard.posToStr(row - 2, col - 2)) {
          gameBoard.remove(row - 1, col - 1)
          true
        }
        else if (gameBoard.field(row - 1, col + 1).piece.isDefined && gameBoard.field(row - 1, col + 1).piece.get.getColor == "black" && gameBoard.field(row - 2, col + 2).piece.isEmpty && to == gameBoard.posToStr(row - 2, col + 2)) {
          print("Field: "+gameBoard.field(row - 1, col + 1))
          gameBoard.move("A1", "E6")
          print("Field: "+gameBoard.field(row - 1, col + 1))
          true
        }
        else false
      }

    }


  }

  override def blackMovePossible(to: String, gameBoard: GameBoard): Boolean = true

}
