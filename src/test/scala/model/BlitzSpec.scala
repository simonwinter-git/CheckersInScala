package model
import model.playerComponent.Player
import org.scalatest._
import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec._
class BlitzSpec extends AnyWordSpec {
  "The Gamemode Blitz" should {
    val mode = Blitz()
    val player = Player("Simon", 0, "White")
    val player2 = Player("Yannick", 1, "Black")
    "have an end" in {
      mode.timeOver(player) should be (true)
    }
    "Go on" in {
      mode.timeOver(player2) should be (false)
    }
  }
}
