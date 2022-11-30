package checkers.model.fileIoComponent.fileIoXmlImpl

import checkers.CheckersModule
import com.google.inject.Guice
import com.google.inject.name.Names
import checkers.model.fileIoComponent.FileIOInterface
import checkers.model.gameBoardComponent.GameBoardInterface
import checkers.model.gameBoardComponent.gameBoardBaseImpl.Piece
import net.codingwell.scalaguice.InjectorExtensions.ScalaInjector

import scala.xml.{Elem, PrettyPrinter}

class FileIO extends FileIOInterface {
  override def load: GameBoardInterface = {
    var gameBoard: GameBoardInterface = null
    val file = scala.xml.XML.loadFile("gameBoard.xml")
    val sizeAttr = (file \\ "gameBoard" \ "@size")
    val size = sizeAttr.text.toInt
    val injector = Guice.createInjector(new CheckersModule)
    size match {
      case 8 => gameBoard = injector.instance[GameBoardInterface](Names.named("8"))
      case 10 => gameBoard = injector.instance[GameBoardInterface](Names.named("10"))
      case _ =>
    }

    val fieldNodes = file \\ "field"

    for (field <- fieldNodes) {
      val pos: String = (field \ "@pos").text
      val row: Int = Integer.parseInt(pos.tail) - 1
      val col: Int = pos.charAt(0).toInt - 65
      val piece = field.text
      piece match {
        case " value = \uD83D\uDD34 " => gameBoard = gameBoard.set(row, col, Some(Piece("normal", row, col, "black")))
        case " value = \uD83D\uDD35 " => gameBoard = gameBoard.set(row, col, Some(Piece("normal", row, col, "white")))
        case " value = \uD83D\uDFE0 " => gameBoard = gameBoard.set(row, col, Some(Piece("queen", row, col, "black")))
        case " value = \uD83D\uDFE3 " => gameBoard = gameBoard.set(row, col, Some(Piece("queen", row, col, "white")))
        case " value = " => gameBoard = gameBoard.remove(row, col)
      }
    }
    gameBoard
  }

  def save(gameBoard: GameBoardInterface): Unit = saveString(gameBoard)

  def saveXML(gameBoard: GameBoardInterface): Unit = {
    scala.xml.XML.save("gameBoard.xml", gameBoardToXml(gameBoard))
  }

  def saveString(gameBoard: GameBoardInterface): Unit = {
    import java.io._
    val pw = new PrintWriter(new File("gameBoard.xml"))
    val pp = new PrettyPrinter(120, 4)
    val xml = pp.format(gameBoardToXml(gameBoard))
    pw.write(xml)
    pw.close
  }

  def gameBoardToXml(gameBoard: GameBoardInterface): Elem = {
    <gameBoard size={ gameBoard.size.toString }>
      {
      for {
        row <- 0 until gameBoard.size
        col <- 0 until gameBoard.size
      } yield {
        fieldToXml(gameBoard, row, col)
        }
      }
    </gameBoard>
  }

  def fieldToXml(gameBoard: GameBoardInterface, row: Int, col: Int): Elem = {
    val piece = gameBoard.getPiece(row, col)
    val pos = gameBoard.posToStr(row, col)
    val xmlField = {
      <field pos={pos}>
        value = {
          piece match {
            case Some(value) => value.toString
            case None => ""
          }
        }
      </field>
    }
    xmlField
  }


}
