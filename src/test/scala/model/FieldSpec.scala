package model
import org.scalatest._
import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec._
class FieldSpec extends AnyWordSpec {
  "A Field " should {
    val field = Field(2)
    val field2 = Field(0)
    "have a state" in {
      field.state should be (2)
    }
    "show 0 as empty" in {
      field2.toString should be ("X")
    }
  }
}
