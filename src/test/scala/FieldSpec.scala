import org.scalatest._
import wordspec._
import matchers.should.Matchers._

class FieldSpec extends AnyWordSpec {
  "A Field" should {
    val A1: Field = Field(0,0)
    val A2: Field = Field(0,1)
    "have a state" in {
      A1.state should be (0)
    }
    "have a color" in {
      A1.isWhite should be (true)
      A2.isWhite should be (false)
    }
    "have an id" in {
      A1.id should be ((0,0))
    }
  }
}
