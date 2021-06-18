package model.gameBoardComponent.gameBoardBaseImpl

//import com.google.inject.Inject

case class Matrix[Field] (lines: Vector[Vector[Field]]) {
  def this(size: Int, filling:Field) = this(Vector.tabulate(size, size) { (row,col) => filling })
  val size: Int = lines.size
  def copy(lines: Vector[Vector[Field]]) = new Matrix[Field](lines: Vector[Vector[Field]])
  def field(row: Int, col: Int): Field = lines(row)(col)
  def replaceField(row: Int, col: Int, field: Field): Matrix[Field] = {
    copy(lines.updated(row, lines(row).updated(col, field)))
  }
}
