package model
import org.scalatest._
import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec._
class MatrixSpec extends AnyWordSpec {
  "A Matrix" should {
    val mat = new Matrix[Field](2,Field("", None))
    "have size" in {
      mat.size should be (2)
    }
    "have lines" in {
      mat.lines should be (Vector(Vector(Field("", None))))
    }
    "copy the Matrix" in {
      mat.copy(Vector(Vector(Field("", None)))) should be (new Matrix(Vector(Vector())))
    }
  }
}