package controller

import scala.swing.event.Event

class FieldChanged extends Event
case class GBSizeChanged(newSize: Int) extends Event
