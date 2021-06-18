package scala
import com.google.inject.AbstractModule
import com.google.inject.name.Names
import net.codingwell.scalaguice.ScalaModule
import controller.controllerComponent._
import model.gameBoardComponent.GameBoardInterface
import model.gameBoardComponent.gameBoardBaseImpl.GameBoard
//import model.gameBoardComponent.

class CheckersModule extends AbstractModule with ScalaModule {

  val defaultSize:Int = 8

  override def configure(): Unit = {
    bindConstant().annotatedWith(Names.named("DefaultSize")).to(defaultSize)
    //bind[GameBoardInterface].to[GameBoard]
    bind[ControllerInterface].to[controllerBaseImpl.Controller]
    bind[GameBoardInterface].toInstance(new GameBoard(8))
  }

}
