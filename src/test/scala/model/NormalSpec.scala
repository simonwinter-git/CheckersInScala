package model
import org.scalatest._
import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec._

class NormalSpec extends AnyWordSpec {
  "The piece normal" should {
    val mode = Normal(8,8,"black")
    "have a row" in {
      mode.row should be (8)
    }
    "have a col" in {
      mode.col should be (8)
    }
    "have a color" in {
      mode.color should be ("black")
    }
  }
}
