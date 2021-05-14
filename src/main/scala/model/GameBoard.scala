package model
case class GameBoard(fields: Matrix[Field]) {
  def this(size: Int) = this(new Matrix[Field](size, Field("", None)))
  val size: Int = fields.size
  def getField(pos: String): Field = field(pos.charAt(1).toInt, pos.charAt(0).asDigit - 65)
  def remove(row: Int, col: Int): GameBoard = copy(fields.replaceField(row, col, Field((col+49).toChar.toString + (row+65).toChar.toString, None)))
  def set(row: Int, col: Int, piece: Piece): GameBoard = copy(fields.replaceField(row, col, Field((col+49).toChar.toString + (row+65).toChar.toString, Some(piece))))
  def field(row: Int, col: Int): Field = fields.field(row, col)

  override def toString: String = {
    val lineseparator = ("+-" + ("--" * size)) + "+\n"
    val line = ("| " + ("o " * size)) + "|\n"
    var box = "\n" + (lineseparator + (line * size)) + lineseparator
    for {
      row <- 0 until size
      col <- 0 until size
    } box = box.replaceFirst("o", field(row, col).toString)
      box
  }

  def move(start: String, dest: String): GameBoard = {

    start.charAt(1).asDigit - 49
    getField(start).piece match {
      case Some(piece) => remove(start.charAt(0).asDigit - 65, start.charAt(1).asDigit - 49).set(start.charAt(1).asDigit - 49, start.charAt(0).asDigit - 65, Piece(piece.state, dest.charAt(0).asDigit - 65, dest.charAt(1).asDigit - 49, piece.color))
      case _ => this
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
}