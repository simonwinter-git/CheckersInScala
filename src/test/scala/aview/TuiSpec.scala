package aview
import controller.Controller
import model.GameBoard
import org.scalatest._
import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec._
class TuiSpec extends  AnyWordSpec {
  "A tui" should {
    val controller = new Controller(new GameBoard(8))
    val tui = new Tui(controller)
    "create us a 8x8 gameboard when typing 'new8' " in {
      tui.tuiEntry("new8")
      controller.gameBoard should be (new GameBoard(8))
    }
    "create us a 10x10 gameboard when typing 'new10' " in {
      tui.tuiEntry("new10")
      controller.gameBoard should be(new GameBoard(10))
    }
  }
}
