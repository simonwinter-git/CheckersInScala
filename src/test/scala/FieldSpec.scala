import org.scalatest._
import wordspec._
import matchers.should.Matchers._

class FieldSpec extends AnyWordSpec {
  val A1 = new Field ("A1", false, false, (0,0))
  "A Field" should {
    "have a name" in {
      A1.name should be ("A1")
    }
    "have a state" in {
      A1.state should be (false)
    }
    "have a type" in {
      A1.fieldType should be (false)
    }
    "have an id" in {
      A1.id should be ((0,0))
    }
  }
}
