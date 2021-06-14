package model
import org.scalatest._
import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec._

class QueenSpec extends AnyWordSpec {
  "A Piece called Queen" should {
    val dame = Queen(1, 4, "white")
    val dameSchwarz = Queen(7,8, "black")
    val gb = new GameBoard(8)
    "have a row" in {
      dame.row should be (1)
    }
    "have a col" in {
      dame.col should be (4)
    }
    "have a color" in {
      dame.color should be ("white")
    }
    "have the state named queen" in {
      dame.state should be ("queen")
    }
    "produce a String" in {
      dame.toString should be ("Q")
    }
  }
}
