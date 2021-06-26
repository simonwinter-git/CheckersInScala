package model.fileIoComponent.fileIoJsonImpl

import com.google.inject.Guice
import com.google.inject.name.Names
import model.fileIoComponent.FileIOInterface
import model.gameBoardComponent.{FieldInterface, GameBoardInterface, PieceInterface}
import model.gameBoardComponent.gameBoardBaseImpl.{Field, Piece}
import net.codingwell.scalaguice.InjectorExtensions.ScalaInjector
import play.api.libs.functional.syntax.toFunctionalBuilderOps

import scala.io.Source
import play.api.libs.json._

class FileIO extends FileIOInterface{
  override def load: GameBoardInterface = {
    var gameBoard: GameBoardInterface = null
    val source: String = Source.fromFile("gameBoard.json").getLines.mkString
    val json: JsValue = Json.parse(source)
    val size = (json \ "gameBoard" \ "size").get.toString.toInt
    val injector = Guice.createInjector(new CheckersModule)
    size match {
      case 8 => gameBoard = injector.instance[GameBoardInterface](Names.named("8"))
      case 10 => gameBoard = injector.instance[GameBoardInterface](Names.named("10"))
      case _ =>
    }
    for (index <- 0 until size * size) {
      val row = (json \\ "row")(index).as[Int]
      val col = (json \\ "col")(index).as[Int]
      val field = (json \\ "field")(index)
      val piece = (json \ "piece")(index).as[Piece]
      gameBoard = gameBoard.set(row, col, Some(piece))
    }
    gameBoard
  }

  override def save(gameBoard: GameBoardInterface): Unit = {
    import java.io._
    val pw = new PrintWriter(new File("gameBoard.json"))
    pw.write(Json.prettyPrint(gameBoardToJson(gameBoard)))
    pw.close
  }

  implicit def optionFormat[T: Format]: Format[Option[T]] = new Format[Option[T]]{
    override def reads(json: JsValue): JsResult[Option[T]] = json.validateOpt[T]

    override def writes(o: Option[T]): JsValue = o match {
      case Some(t) ⇒ implicitly[Writes[T]].writes(t)
      case None ⇒ JsNull
    }
  }

  val pieceReadsBuilder =
    (JsPath \ "state").read[String] and
      (JsPath \ "row").read[Int] and
      (JsPath \ "col").read[Int] and
      (JsPath \ "color").read[String]

  //implicit val pieceReads = pieceReadsBuilder.apply(Some(Piece.apply _))

  implicit val pieceReads: Reads[Piece] = (
    (JsPath \ "state").read[String] and
      (JsPath \ "row").read[Int] and
      (JsPath \ "col").read[Int] and
      (JsPath \ "color").read[String]
    ) (Piece.apply _)




  implicit val fieldWrites = new Writes[FieldInterface] {
    def writes(field: FieldInterface) = Json.obj(
    "pos" -> field.getPos,
      "piece" -> pieceWrites.writes(field.getPiece)
    )
  }

  implicit val pieceWrites = new Writes[Option[Piece]] {
    def writes(piece: Option[Piece]) = Json.obj(
      "state" -> piece.get.state,
      "row" -> piece.get.row,
      "col" -> piece.get.col,
      "color" -> piece.get.getColor
    )

  }


  /*
  implicit val fieldWrites = new Writes[FieldInterface] {
    def writes(field: FieldInterface) = Json.obj(
      "piece" -> field.piece.get
    )
  }
  */


  def gameBoardToJson(gameBoard: GameBoardInterface) = {
    Json.obj(
      "gameBoard" -> Json.obj(
        "size" -> JsNumber(gameBoard.size),
        "fields" -> Json.toJson(
          for {
            row <- 0 until gameBoard.size;
            col <- 0 until gameBoard.size
          } yield {
            Json.obj(
              "row" -> row,
              "col" -> col,
              "field" -> Json.toJson(gameBoard.field(row, col))
            )
          }
        )
      )
    )
  }


}