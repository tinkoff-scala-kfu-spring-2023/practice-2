package com.example

case class Robot(carry: Array[Goods], madeMoves: Array[Move]) {
  private val maxWeight = 1000
  def load(goods: Goods): Either[Throwable, Robot] = {
    if ((carry.map(_.mass).sum + goods.mass) > maxWeight)
      Left(new RuntimeException("I can't carry anymore"))
    else {
      println(s"Loading ${goods.mass}")
      Right(copy(carry appended goods, madeMoves))
    }
  }

  def unload(goods: Goods): Either[Throwable, Robot] = {
    println(s"unloading $goods")
    ???
  }

  def go(direction: String, length: Int): Either[Throwable, Robot] = {
    println(s"Moving $direction $length cm")
    Right(copy(madeMoves = madeMoves.appended(Move(direction, length))))
  }
}

