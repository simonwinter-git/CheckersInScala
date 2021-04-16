import org.scalatest._
import wordspec._
import matchers.should.Matchers._

class PlayerSpec extends AnyWordSpec {
  "A Player" should {
    val Peter = new Player("Peter")
    "have a name" in {
      Peter.name should be ("Peter")
    }
  }
}
