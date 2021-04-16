import org.scalatest._
import wordspec._
import matchers.should.Matchers._

class PlayerSpec extends AnyWordSpec {
  val Peter = new Player("Peter", 12, "white")
  "A Player" should {
    "have a name" in {
      Peter.name should be ("Peter")
    }
    "have checkers" in {
      Peter.checker should be(12)
    }
    "have color" in {
      Peter.color should be("white")
    }
  }
}
