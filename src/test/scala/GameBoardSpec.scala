import org.scalatest._
import wordspec._
import matchers.should.Matchers._
class GameBoardSpec extends AnyWordSpec {
  val standard = new GameBoard ("normal", 10 * 10)
  "A GameBoard" should {
    "have a name" in {
      standard.name should be ("normal")
    }
    "have a amount of fields" in {
      standard.fields should be (100)
    }
  }
}
