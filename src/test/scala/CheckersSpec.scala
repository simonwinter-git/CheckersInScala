import org.scalatest._
import wordspec._
import matchers.should.Matchers._
class CheckersSpec extends AnyWordSpec {
  "A Game Checkers" should {
    val checkers = Checkers
    "have a GameBoard" in {
      Checkers.gameBoard should be (GameBoard)
    }
  }
}

/* CheckersOldSpec
import org.scalatest._
import wordspec._
import matchers.should.Matchers._
class CheckersOldSpec extends AnyWordSpec {
  "A Game Checkers" should {
    val checkers = CheckersOld
    "have a GameBoard" in {
      CheckersOld.gameBoard should be (GameBoard)
    }
    "should have make a move" in {
      val field = CheckersOld.movement((-1,-1))
      field should be (Field(0,0))
    }
    "should not move to" in {
      val field = CheckersOld.movement(-1,-1)
      field should be (Field(0,0))
    }
  }
}

 */