package checkers.model
import checkers.model.gameBoardComponent.gameBoardBaseImpl.{GameBoard, Normal, Piece}
import org.scalatest._
import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec._

class NormalSpec extends AnyWordSpec {
  "A Piece called Normal" should {
    val dame = Normal("normal", 4, 4, "white")
    val dameLinks = Normal("normal", 4,0, "white")
    val dameRechts = Normal("normal", 7,9, "white")
    val dameSchwarz = Normal("normal", 2, 2, "black")

    val gb = new GameBoard(10)
    "have a row" in {
      dame.row should be (4)
      dameSchwarz.row should be (2)
    }
    "have a col" in {
      dame.col should be (4)
      dameSchwarz.col should be (2)
    }
    "have a color" in {
      dame.getColor should be ("white")
      dameSchwarz.getColor should be ("black")
    }
    "have the state named Normal" in {
      dame.state should be ("normal")
      dameSchwarz.state should be ("normal")
    }
    "produce a String" in {
      dame.toString should be("\u001B[30mO\u001B[0m")
      dameSchwarz.toString should be ("\u001B[37mO\u001B[0m")
    }
    "should be allowed to Move" in {
      dame.whiteMovePossible("D4", gb).getBool should be (true)
      dame.whiteMovePossible("F4", gb).getBool should be (true)
      dame.whiteMovePossible("A9", gb).getBool should be (false)
      dame.whiteMovePossible("J10", gb).getBool should be (false)
      dameLinks.whiteMovePossible("B4", gb).getBool should be (true)
      dameLinks.whiteMovePossible("E6", gb).getBool should be (false)
      dameRechts.whiteMovePossible("I7", gb).getBool should be (true)
      dameRechts.whiteMovePossible("I9", gb).getBool should be (false)
    }
    "should be allowed to Move as a Black Piece" in {
      val dameSchwarzLinks = Normal("normal", 2, 0, "black")
      val dameSchwarzRechts = Normal("normal", 1, 9, "black")
      dameSchwarz.blackMovePossible("B4", gb).getBool should be (true)
      dameSchwarz.blackMovePossible("D4", gb).getBool should be (true)
      dameSchwarz.blackMovePossible("A5", gb).getBool should be (false)
      dameSchwarz.blackMovePossible("H8", gb).getBool should be (false)
      dameSchwarzLinks.blackMovePossible("B4", gb).getBool should be (true)
      dameSchwarzLinks.blackMovePossible("B2", gb).getBool should be (false)
      dameSchwarzRechts.blackMovePossible("I3", gb).getBool should be (true)
      dameSchwarzRechts.blackMovePossible("I1", gb).getBool should be (false)
    }
    "should be allowed to Capture from the middle" in {
      var gbc = new GameBoard(10)
      gbc = gbc.set(3, 3, Some(Piece("normal", 3, 3, "black")))
      gbc = gbc.set(3, 5, Some(Piece("normal", 3, 5, "black")))
      gbc = gbc.set(5, 3, Some(Piece("normal", 5, 3, "black")))
      gbc = gbc.set(5, 5, Some(Piece("normal", 5, 5, "black")))
      gbc = gbc.set(4, 4, Some(Piece("normal", 4, 4, "white")))
      var str = ""
      str = gbc.getPiece(4, 4).get.getColor
      val dame2 = gbc.getPiece(4, 4).get //E5
      str should be ("white")
      dame2.whiteMovePossible("C3", gbc).getBool should be (true)
      dame2.whiteMovePossible("G3", gbc).getBool should be (true)
      dame2.whiteMovePossible("C7", gbc).getBool should be (false)
      dame2.whiteMovePossible("G7", gbc).getBool should be (false)
    }
    "should be allowed to Capture from the right" in {
      var gbc = new GameBoard(10)
      gbc = gbc.set(4, 8, Some(Piece("normal", 4, 8, "black")))
      gbc = gbc.set(6, 8, Some(Piece("normal", 6, 8, "black")))
      gbc = gbc.set(5, 9, Some(Piece("normal", 5, 9, "white")))
      var str = ""
      str = gbc.getPiece(5, 9).get.getColor
      val dame2 = gbc.getPiece(5, 9).get //E5
      str should be ("white")
      dame2.whiteMovePossible("H4", gbc).getBool should be (true)
      dame2.whiteMovePossible("H8", gbc).getBool should be (false)
    }
    "should be allowed to Capture from the left" in {
      var gbc = new GameBoard(10)
      gbc = gbc.set(8, 2, Some(Piece("normal", 8, 2, "black")))
      gbc = gbc.set(5, 1, Some(Piece("normal", 5, 1, "black")))
      gbc = gbc.set(6, 0, Some(Piece("normal", 6, 0, "white")))
      var str = ""
      str = gbc.getPiece(6, 0).get.getColor
      val dame2 = gbc.getPiece(6, 0).get //E5
      str should be ("white")
      dame2.whiteMovePossible("C5", gbc).getBool should be (true)
      dame2.whiteMovePossible("D10", gbc).getBool should be (false)
    }
    "should be allowed to Capture from the middle as Black" in {
      var gbc = new GameBoard(10)
      gbc = gbc.set(3, 3, Some(Piece("normal", 3, 3, "white")))
      gbc = gbc.set(3, 5, Some(Piece("normal", 3, 5, "white")))
      gbc = gbc.set(5, 3, Some(Piece("normal", 5, 3, "white")))
      gbc = gbc.set(5, 5, Some(Piece("normal", 5, 5, "white")))
      gbc = gbc.set(4, 4, Some(Piece("normal", 4, 4, "black")))
      var str = ""
      str = gbc.getPiece(4, 4).get.getColor
      val dame2 = gbc.getPiece(4, 4).get //E5
      str should be ("black")
      dame2.blackMovePossible("C3", gbc).getBool should be (false)
      dame2.blackMovePossible("G3", gbc).getBool should be (false)
      dame2.blackMovePossible("C7", gbc).getBool should be (true)
      dame2.blackMovePossible("G7", gbc).getBool should be (true)
    }
    "should be allowed to Capture from the right as Black" in {
      var gbc = new GameBoard(10)
      gbc = gbc.set(4, 8, Some(Piece("normal", 4, 8, "white")))
      gbc = gbc.set(6, 8, Some(Piece("normal", 6, 8, "white")))
      gbc = gbc.set(5, 9, Some(Piece("normal", 5, 9, "black")))
      var str = ""
      str = gbc.getPiece(5, 9).get.getColor
      val dame2 = gbc.getPiece(5, 9).get //E5
      str should be ("black")
      dame2.blackMovePossible("H8", gbc).getBool should be (true)
      dame2.blackMovePossible("H3", gbc).getBool should be (false)
    }
    "should be allowed to Capture from the left Black" in {
      var gbc = new GameBoard(10)
      gbc = gbc.set(7, 1, Some(Piece("normal", 7, 1, "white")))
      gbc = gbc.set(5, 1, Some(Piece("normal", 5, 1, "white")))
      gbc = gbc.set(6, 0, Some(Piece("normal", 6, 0, "black")))
      var str = ""
      str = gbc.getPiece(6, 0).get.getColor
      val dame2 = gbc.getPiece(6, 0).get //E5
      str should be("black")
      dame2.blackMovePossible("C9", gbc).getBool should be(true)
      dame2.blackMovePossible("C5", gbc).getBool should be(false)
    }
  }
}