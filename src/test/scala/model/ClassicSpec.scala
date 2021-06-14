package model
import org.scalatest._
import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec._

class ClassicSpec extends AnyWordSpec {
  "The Mode Classic" should {
    val mode = Classic()
    val player1 = Player("Yannick", 0, "White")
    "not end when Timer = 0" in {
      mode.timeOver(player1) should be (false)
    }
  }
}
