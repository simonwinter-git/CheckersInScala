package checkers.model.gameBoardComponent.gameBoardBaseImpl
import com.google.inject.Inject
import checkers.model.gameBoardComponent.{FieldInterface, GameBoardInterface}
import play.api.libs.json.{JsNull, JsNumber, JsValue, Json, Writes}
import checkers.util.Mover
case class GameBoard @Inject() (fields: Matrix[Field]) extends GameBoardInterface {

  def this(size: Int) = this(new Matrix[Field](size, Field("", None)))

  val size: Int = fields.size

  def getField(pos: String): Field = {
    field(Integer.parseInt(pos.tail)-1, pos.charAt(0).toInt - 65)
  }

  def remove(row: Int, col: Int): GameBoard = copy(fields.replaceField(row, col, Field(posToStr(row, col), None)))

  def set(row: Int, col: Int, piece: Option[Piece]): GameBoard = copy(fields.replaceField(row, col, Field(posToStr(row, col), piece)))

  def field(row: Int, col: Int): Field = fields.field(row, col)

  def colToInt(pos: String): Int = pos.charAt(0).toInt - 65

  def rowToInt(pos: String): Int = Integer.parseInt(pos.tail) - 1

  def posToStr(row: Int, col: Int): String = (col + 65).toChar.toString + (row+1).toString


  override def toString: String = {
    val lineSeparator = ("+-" + ("--" * (size+1))) + "+\n"
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
      case Some(piece) => remove(Integer.parseInt(start.tail)-1, start.charAt(0).toInt - 65).set(Integer.parseInt(dest.tail)-1, dest.charAt(0).toInt - 65, Some(Piece(piece.state, Integer.parseInt(dest.tail)-1, dest.charAt(0).toInt - 65, piece.getColor)))
      case None => print("Field " + start + " is empty"); this
    }
  }

  def whiteMovePossible(start: String, dest: String): Mover = {
    getField(start).piece match {
      case Some(piece) => piece.whiteMovePossible(dest, this)
      case _ => new Mover(false, "", false)
    }
  }

  def blackMovePossible(start: String, dest: String): Mover = {
    getField(start).piece match {
      case Some(piece) => piece.blackMovePossible(dest, this)
      case _ => new Mover(false, "", false)
    }
  }

  implicit val fieldWrites: Writes[FieldInterface] = new Writes[FieldInterface] {
    def writes(field: FieldInterface) = Json.obj(
      "pos" -> field.getPos,
      "piece" -> pieceWrites.writes(field.getPiece)
    )
  }


  implicit val pieceWrites: Writes[Option[Piece]] = new Writes[Option[Piece]] {
    def writes(piece: Option[Piece]): JsValue = piece match {
      case Some(t) => Json.obj(
        "state" -> t.state,
        "prow" -> t.row,
        "pcol" -> t.col,
        "color" -> t.getColor,
      )
      case None => Json.obj(
        "state" -> "",
        "prow" -> "",
        "pcol" -> "",
        "color" -> ""
      )
    }
  }


  def toJson: JsValue = {
    Json.obj(
      "gameboard" -> Json.obj(
        "size" -> JsNumber(size),
        "fields" -> Json.toJson(
          for {row <- 0 until size;
               col <- 0 until size} yield {
            Json.obj(
              "row" -> row,
              "col" -> col,
              "field" -> Json.toJson(field(row, col): FieldInterface))
          }
        )
      )
    )
  }

}