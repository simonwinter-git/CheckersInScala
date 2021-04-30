case class GameBoard(name:String, size:Int) {
  def this(size: Int) = this(new Matrix[Field](size, Field(0)))


  val zero: Field = Field((0,0),0)
  val one: Field = Field((0,1),0)
  val two: Field = Field((0,2),0)
  val three: Field = Field((1,0),0)
  val four: Field = Field((1,1),0)
  val five: Field = Field((1,2),0)
  val six: Field = Field((2,0),0)
  val seven: Field = Field((2,1),0)
  val eight: Field = Field((2,2),0)
  //val fieldVec = Vector(Vector(zzz))
  /*
  val preID = (val,val)
  for (e <- fieldVec) {
    preID._1 = e/x
    preID._2 = e%x

  }
  val preID = fieldVec[(fieldVec(0)/x),(fieldVec[0]%x)]

    //name state field_type id

  if ((preID[0] - preID[1]) % 2 == 0) {
    fieldType = false
  }
  var s = new StringBuilder()
  s = s.append()
  */
}