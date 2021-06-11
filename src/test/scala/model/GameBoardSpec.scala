package model
import org.scalatest._
import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec._
class GameBoardSpec extends AnyWordSpec {
  "A GameBoard" should {
    val board = new GameBoard(3)
    "have fields" in {
      board.size should be (3)
    }
  }
}