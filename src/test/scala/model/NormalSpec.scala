package model
import model.gameBoardComponent.gameBoardBaseImpl.Normal
import org.scalatest._
import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec._
import model.gameBoardComponent.gameBoardBaseImpl.GameBoard

class NormalSpec extends AnyWordSpec {
  "The piece normal" should {
    val mode = Normal("normal", 8,8,"black")
    val modeSchwarz = Normal("normal", 7, 8, "black")
    val gb = new GameBoard(8)
    "have a row" in {
      mode.row should be (8)
    }
    "have a col" in {
      mode.col should be (8)
    }
    "have a color" in {
      mode.getColor should be ("black")
    }
    "have a state" in {
      mode.state should be ("normal")
    }
    "should build a String" in {
      mode.toString should be ("O")
    }
    "should be allowed to Move" in {
      mode.whiteMovePossible("A2", gb) should be (true)
    }
    "should be allowed to Move as a Black Piece" in {
      modeSchwarz.blackMovePossible("A1", gb) should be (true)
    }
  }
}
