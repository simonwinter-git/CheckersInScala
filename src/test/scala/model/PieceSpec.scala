package model
import org.scalatest._
import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec._

class PieceSpec extends AnyWordSpec {
  "A trait" should {
    val norm = Normal(0,0, "white")
    val norm2 = Normal(0,5, "black")
    "make Integers out of a String " in {
      norm.movStrToInt("B2") should be (0,0, 1,1) //C = Ascii 67, "2" =  50
      norm2.movStrToInt("E2") should be (0,5, 1, 4)
    }
    "apply a Type to a Piece" in {
      val norm3 = Piece.apply("normal", 0,1, "white") should be (Normal(0,1,"white"))
      val queen = Piece.apply("queen", 0,3, "black") should be (Queen(0,3,"black"))
    }
  }
}
