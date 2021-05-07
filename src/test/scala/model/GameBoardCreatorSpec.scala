package model
import org.scalatest._
import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec._
class GameBoardCreatorSpec extends AnyWordSpec {
  "A Creator" should {
    val gbc = new GameBoardCreator(8).createBoard()
    "have white corners at " in {
      gbc.field(0,0).state should be (0)
    }
  }
}