package model
case class GameBoard(fields: Matrix[Field]) {
  def this(size: Int) = this(new Matrix[Field](size, Field(0)))
  val size: Int = fields.size
  def set(row: Int, col: Int, state: Int): GameBoard = copy(fields.replaceField(row, col, Field(state)))




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