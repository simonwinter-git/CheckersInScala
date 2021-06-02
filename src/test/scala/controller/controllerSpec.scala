 package controller
import model.GameBoard
import org.scalatest._
import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec._
import util.Observer
class controllerSpec extends AnyWordSpec {
 /* "A Controller" when {
   "observed by an Observer" should {
     val gameboard = new GameBoard(8)
     val controller = new Controller(gameboard)
     val observer = new Observer {
       var updated: Boolean = false
       def isUpdated: Boolean = updated
       override def update: Unit = updated = true
     }
     controller.add(observer)
     "should tell observer after creating board" in {
       controller.createEmptyGameBoard(8)
       observer.updated should be(true)
       controller.gameBoard.size should be (8)
     }
     "should tell observer after creating a Field" in {
       controller.set(1, 1, 2)
       observer.updated should be(true)
       controller.gameBoard.field(1, 1).state should be(2)
     }
   }
 } */
}
