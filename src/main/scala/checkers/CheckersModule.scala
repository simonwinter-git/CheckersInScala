package checkers

import checkers.controller.controllerComponent.ControllerInterface
import checkers.controller.controllerComponent.controllerBaseImpl.Controller
import checkers.model.fileIoComponent.{FileIOInterface, fileIoJsonImpl}
import checkers.model.gameBoardComponent.GameBoardInterface
import checkers.model.gameBoardComponent.gameBoardAdvancedImpl.GameBoard
import com.google.inject.AbstractModule
import com.google.inject.name.Names
import net.codingwell.scalaguice.ScalaModule

class CheckersModule extends AbstractModule with ScalaModule {

  val defaultSize:Int = 10

  override def configure(): Unit = {
    bindConstant().annotatedWith(Names.named("DefaultSize")).to(defaultSize)
    bind[GameBoardInterface].to[GameBoard]
    bind[ControllerInterface].to[Controller]
    bind[GameBoardInterface].annotatedWithName("8")toInstance(new GameBoard(8))
    bind[GameBoardInterface].annotatedWithName("10")toInstance(new GameBoard(10))
    bind[FileIOInterface].to[fileIoJsonImpl.FileIO]
  }

}
