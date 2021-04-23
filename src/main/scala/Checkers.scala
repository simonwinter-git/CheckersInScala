import scala.io.StdIn.readLine
import java.util
object Checkers {
  var gameBoard = GameBoard
  val tui = new Tui
  var pin = new Pin(true)

  def main(args: Array[String]): Unit = {
    var entry: String = ""
    do {
      println("O O O")
      println("O O O")
      println("O O O")
      println(pin.position)
      entry = readLine()

     pin.position = tui.tuiEntry(entry)
    } while (entry != "f")
  }
  def movement(move:(Int, Int)): Field = {
    var p1 = pin.position.id._1 + move._1
    var p2 = pin.position.id._2 + move._2
    if ((p1 < 0 || p1 > 7) || (p2 < 0 || p2 > 7)) {
      println("Illegal Move! Watch your steps!")
      return pin.position
    } else {
      pin.position = Field((p1), (p2))
      return pin.position
    }
  }
}
