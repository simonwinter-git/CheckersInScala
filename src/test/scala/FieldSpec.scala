import org.scalatest._
import wordspec._
import matchers.should.Matchers._

class FieldSpec extends AnyWordSpec {
  val A1: Field = Field(0,0)
  "A Field" should {
    "have a state" in {
      A1.state should be (0)
    }
    "have a type" in {
      A1.fieldType should be (true)
    }
    "have an id" in {
      A1.id should be ((0,0))
    }
  }
}
