/*
package model.fileIoComponent.fileIoJsonImpl

import com.google.inject.Guice
import com.google.inject.name.Names
import model.fileIoComponent.FileIOInterface
import model.gameBoardComponent.{FieldInterface, GameBoardInterface, PieceInterface}
import model.gameBoardComponent.gameBoardBaseImpl.{Field, Piece}
import net.codingwell.scalaguice.InjectorExtensions.ScalaInjector

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
      val piece = (field \ "piece")(index).as[Piece]
      gameBoard = gameBoard.set(row, col, piece)
    }
    gameBoard
  }

  override def save(gameBoard: GameBoardInterface): Unit = {
    import java.io._
    val pw = new PrintWriter(new File("gameBoard.json"))
    pw.write(Json.prettyPrint(gameBoardToJson(gameBoard)))
    pw.close
  }

  implicit val pieceWrites = new Writes[PieceInterface] {
    def writes(piece: PieceInterface) = Json.obj(
      "state" -> piece.state
    )
  }

  implicit val fieldWrites = new Writes[FieldInterface] {
    def writes(field: FieldInterface) = Json.obj(
      "piece" -> field.piece.get
    )
  }


}
*/