package aview
import controller.Controller
import model.GameBoard
import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec._
class TuiSpec extends AnyWordSpec {
  "A tui" should {
    val controller = new Controller(new GameBoard(8))
    val tui = new Tui(controller)
    "create us a 8x8 gameboard when typing 'new 8' " in {
      tui.tuiEntry("new 8")
      controller.gameBoard should be (new GameBoard(8))
    }
    "create us a 10x10 gameboard when typing 'new 10' " in {
      tui.tuiEntry("new 10")
      controller.gameBoard should be(new GameBoard(10))
    }
  }
}
