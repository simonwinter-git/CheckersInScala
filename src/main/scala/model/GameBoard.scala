package model
case class GameBoard(fields: Matrix[Field]) {
  def this(size: Int) = this(new Matrix[Field](size, Field(0)))
  val size: Int = fields.size
  def set(row: Int, col: Int, state: Int): GameBoard = copy(fields.replaceField(row, col, Field(state)))
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