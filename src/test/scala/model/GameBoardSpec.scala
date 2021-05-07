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
    "Test" in {
      val board = GameBoard(new Matrix(8, Field(0)))
    }
    "be able to be set" in {
      val testBoard = board.set(0, 0, 1)
      testBoard.field(0, 0) should be(Field(1))
      board.field(0, 0) should be(Field(0))
    }
  }
}
