package model
import model.gameBoardComponent.gameBoardBaseImpl.{Field, Piece}
import org.scalatest._
import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec._
class FieldSpec extends AnyWordSpec {
  "A Field " should {
    val field = Field("A2", None)
    val field2 = Field("B3", Some(Piece.apply("normal", 2, 1, "white")))
    "have a state" in {
      field.pos should be ("A2")
      field2.pos should be ("B3")
    }
    "be a Piece" in {
      field2.piece should be ("normal", 2, 1, "white")
    }
  }
}