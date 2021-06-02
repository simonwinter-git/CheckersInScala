package model
import util.Mode
case class GameBoard(fields: Matrix[Field]) {

  def this(size: Int) = this(new Matrix[Field](size, Field("", None)))
  val size: Int = fields.size
  var mode: Mode = Classic()

  def getField(pos: String): Field = field(pos.charAt(1).toInt, pos.charAt(0).asDigit - 65)
  def remove(row: Int, col: Int): GameBoard = copy(fields.replaceField(row, col, Field((col+49).toChar.toString + (row+65).toChar.toString, None)))
  def set(row: Int, col: Int, piece: Piece): GameBoard = copy(fields.replaceField(row, col, Field( posToStr(row, col), Some(piece) )))
  def field(row: Int, col: Int): Field = fields.field(row, col)


  def colToInt(pos: String): Int = pos.charAt(1).asDigit - 49
  def rowToInt(pos: String): Int = pos.charAt(0).asDigit - 65

  def posToStr(row: Int, col: Int): String = (col+49).toChar.toString + (row+65).toChar.toString

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

  def move(start: String, dest: String): GameBoard = {
    getField(start).piece match {
      case Some(piece) => remove(start.charAt(0).asDigit - 65, start.charAt(1).asDigit - 49).set(start.charAt(1).asDigit - 49, start.charAt(0).asDigit - 65, Piece(piece.state, dest.charAt(0).asDigit - 65, dest.charAt(1).asDigit - 49, piece.color))
      case _ => this
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

  /*

    val preID = (val,val)
    for (e <- fieldVec) {
      preID._1 = e/x
      preID._2 = e%x

    }
    val preID = fieldVec[(fieldVec(0)/x),(fieldVec[0]%x)]

      //name state field_type id

    if ((preID[0] - preID[1]) % 2 == 0) {
      fieldType = false
    }
    var s = new StringBuilder()
    s = s.append()
    */
