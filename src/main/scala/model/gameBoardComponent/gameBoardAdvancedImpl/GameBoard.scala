package model.gameBoardComponent.gameBoardAdvancedImpl
import com.google.inject.Inject
import com.google.inject.name.Named
import model.gameBoardComponent.gameBoardBaseImpl.{GameBoard => BaseGameBoard}

class GameBoard @Inject() (@Named("DefaultSize") size: Int) extends BaseGameBoard(size) {
}
