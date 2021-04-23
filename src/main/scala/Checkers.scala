import scala.io.StdIn.readLine
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
    pin.position = Field((pin.position.id._1 + move._1), (pin.position.id._2 + move._2))
    return pin.position
  }
}
