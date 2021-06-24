package model.gameBoardComponent.gameBoardBaseImpl
import com.google.inject.Inject
import model.gameBoardComponent.{GameBoardInterface}
import util.Mode
case class GameBoard @Inject() (fields: Matrix[Field]) extends GameBoardInterface {

  def this(size: Int) = this(new Matrix[Field](size, Field("", None)))

  val size: Int = fields.size
  var mode: Mode = Classic()

  def getField(pos: String): Field = field(pos.charAt(1).toInt - 49, pos.charAt(0).toInt - 65)

  def remove(row: Int, col: Int): GameBoard = copy(fields.replaceField(row, col, Field(posToStr(row, col), None)))

  def set(row: Int, col: Int, piece: Piece): GameBoard = copy(fields.replaceField(row, col, Field(posToStr(row, col), Some(piece))))

  def field(row: Int, col: Int): Field = fields.field(row, col)

  def colToInt(pos: String): Int = pos.charAt(1).toInt - 49

  def rowToInt(pos: String): Int = pos.charAt(0).toInt - 65

  def posToStr(row: Int, col: Int): String = (col + 65).toChar.toString + (row + 49).toChar.toString

  def setMode(mode: Mode): Unit = {
    println("Mode set")
    this.mode = mode
  }

  override def toString: String = {
    val lineSeparator = ("+-" + ("--" * size)) + "+\n"
    val line = ("| " + ("o " * size)) + "|\n"
    var box = "\n" + (lineSeparator + (line * size)) + lineSeparator
    for {
      row <- 0 until size
      col <- 0 until size
    } box = box.replaceFirst("o", field(row, col).toString)
    box
  }

  def getPiece(row: Int, col: Int): Option[Piece] = {
    def pos = posToStr(row, col)
    getField(pos).piece
  }

  def move(start: String, dest: String): GameBoard = {
    getField(start).piece match {
      case Some(piece) => remove(start.charAt(1).toInt - 49, start.charAt(0).toInt - 65).set(dest.charAt(1).toInt - 49, dest.charAt(0).toInt - 65, Piece(piece.state, dest.charAt(1).toInt - 49, dest.charAt(0).toInt - 65, piece.getColor))
      case None => this
    }
  }

  def whiteMovePossible(start: String, dest: String): Boolean = {
    getField(start).piece match {
      case Some(piece) => piece.whiteMovePossible(dest, this)
      case _ => false
    }
  }

  def blackMovePossible(start: String, dest: String): Boolean = {
    getField(start).piece match {
      case Some(piece) => piece.blackMovePossible(dest, this)
      case _ => false
    }
  }

}