import org.scalatest._
import wordspec._
import matchers.should.Matchers._

class PinSpec extends AnyWordSpec {
  "A Pin" should {
    val White1 = new Pin (true)
    White1.position = Field(0,0)
    "have a color" in {
      White1.color should be (true)
    }
    "have a position" in {
      White1.position should be (Field(0,0))
    }
  }
}
