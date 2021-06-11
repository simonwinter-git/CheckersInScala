package model
import org.scalatest._
import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec._
class PlayerSpec extends AnyWordSpec {
  "A Player" should {
    val player = Player("Simon", 2, "white")
    "have a Name" in {
      player.name should be ("Simon")
    }
    "have a Checker" in {
      player.timer should be (2)
    }
    "have a color" in {
      player.color should be ("white")
    }
    "have a toString-representation" in {
      player.toString should be ("Simon")
    }
  }
}