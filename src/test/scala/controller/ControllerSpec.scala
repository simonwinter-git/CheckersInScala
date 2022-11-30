package controller

import checkers.controller.controllerComponent.GameState
import checkers.controller.controllerComponent.controllerBaseImpl.Controller
import checkers.model.gameBoardComponent.gameBoardBaseImpl
import checkers.model.gameBoardComponent.gameBoardBaseImpl.{Field, GameBoard, Piece}
import org.scalatest._
import GameState.{BLACK_TURN, WHITE_TURN, WHITE_WON, BLACK_WON}
import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec._
import checkers.util.{Mover, Observer}
class ControllerSpec extends AnyWordSpec {
  "A Controller" should {
    val gb = new GameBoard(8)
    val controller = new Controller(gb)
    "use undo/redo correctly" in {
      controller.gameBoard.field(0,0).isSet should be (false)
      controller.set(0,0, Piece.apply("normal", 0, 0, "white"))
      controller.set(4,4, Piece.apply("normal", 4, 4, "white"))
      controller.set(5,5, Piece.apply("normal", 5, 5, "white"))
      controller.set(1,1, Piece.apply("normal", 1, 1, "black"))
      controller.gameBoard.field(0,0).isSet should be (true)
      controller.isSet(0,0) should be (true)
      controller.undo
      controller.undo
      controller.gameBoard.field(0,0).isSet should be (true)
      controller.redo
      controller.redo
      controller.gameBoard.field(0,0).isSet should be (true)
    } /*
    "be set correctly" in {
      controller.gameBoard.rowToInt("A2") should be (0)
      controller.gameBoard.colToInt("A2") should be (1)
      controller.gameBoard.getField("A2").toString should be (" ")
      controller.gameBoard.getField("A1").toString should be ("O")
      controller.gameBoard.getField("B2").toString should be ("Q")
    } */
    "have the right size" in {
      controller.gameBoardSize should be (8)
    }
    "deliver the right message about the next turn" in {
      controller.statusText should be ("It's White's turn")
      controller.setGameState(BLACK_TURN)
      controller.statusText should be ("It's Black's turn")
    } /*
    "move right" in {
      controller.gameBoard.field(0,0).isSet should be (true)
      controller.move("A1", "B2")
      controller.gameBoard.getField("A1").toString should be (" ")
      controller.gameBoard.field(0,0).isSet should be (false)
      controller.gameBoard.field(1,1).isSet should be (true)
      controller.gameBoard.getField("A2").toString should be (" ")
      controller.move("A2", "B3")
      controller.gameBoard.getField("A2").toString should be (" ")
      controller.gameBoard.getField("B3").toString should be (" ")
    }
    "have the correct String output" in {
      controller.gameBoardToString should be ("\n+-------+\n|       |\n|   O   |\n|       |\n+-------+\n")
    } */
    "be able to resize the Gameboard" in {
      controller.resize(8)
      controller.gameBoardSize should be (8)
    }
    "be able to move" in {
      val gb2 = new GameBoard(8)
      val controller = new Controller(gb2)
      controller.set(0,0, Piece.apply("normal", 0, 0, "white"))
      controller.set(4,4, Piece.apply("normal", 4, 4, "white"))
      controller.set(5,5, Piece.apply("normal", 5, 5, "white"))
      controller.set(5,5, Piece.apply("normal", 4, 3, "white"))
      controller.set(1,1, Piece.apply("normal", 1, 1, "black"))
      controller.set(7,7, Piece.apply("normal", 7, 7, "black"))
      controller.set(6,7, Piece.apply("normal", 6, 7, "black"))
      controller.set(6,7, Piece.apply("normal", 6, 7, "black"))
      controller.gameState should be (WHITE_TURN)
      controller.move("A1", "C3")
      controller.gameState should be (BLACK_TURN)
      //controller.move("B2", "C4")
      //controller.gameState should be (WHITE_TURN)
    }
    "be able to check if a move is possible" in {
      controller.createNewGameBoard()
      controller.set(0,0, Piece.apply("normal", 0, 0, "black"))
      controller.set(4,4, Piece.apply("normal", 4, 4, "black"))
      controller.set(5,5, Piece.apply("normal", 5, 5, "black"))
      controller.set(1,1, Piece.apply("normal", 1, 1, "white"))
      controller.set(4,3, Piece.apply("normal", 4, 3, "white"))
      controller.set(5,3, Piece.apply("normal", 5, 3, "white"))

      controller.remove(2, 2)
      controller.gameState = BLACK_TURN
      val mov: Mover = controller.movePossible("A1", "C3")
      controller.move("A1", "C3")
      controller.getPiece(2,2).get.getColor should be ("black")
      mov.getBool should be (true)
      mov.getRem should be ("B2")
      mov.getQ should be (false)
      controller.movePossible("C3", "C3")
      controller.gameState should be (WHITE_TURN)
    }
  }
}