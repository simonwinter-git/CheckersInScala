package model
import controller.controllerComponent.GameState.WHITE_TURN
import controller.controllerComponent.controllerMockImpl.Controller
import model.playerComponent.Player
import org.scalatest._
import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec._
//import model.gameBoardComponent.gameBoardBaseImpl.{Classic}
import model.gameBoardComponent.gameBoardMockImpl.GameBoard
class ControllerMockSpec extends AnyWordSpec {
  "A ControllerMock" should {
    val gb = new GameBoard(3)
    val controller = new Controller(gb)

    "have a size" in {
      controller.gameBoardSize should be (1)
    }

    "have a GameState" in {
      controller.gameState should be (WHITE_TURN)
    }
  }
}
