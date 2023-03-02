package com.example

case class Robot(carry: Array[Goods], madeMoves: Array[Move]) {
  private val maxWeight = 1000
  def load(goods: Goods): Robot = {
    if ((carry.map(_.mass).sum + goods.mass) > maxWeight) throw new RuntimeException("I can't carry anymore")
    println(s"Loading ${goods.mass}")
    copy(carry appended goods, madeMoves)
  }

  def unload(goods: Goods): Robot = {
    println()
    ???
  }

  def go(direction: String, length: Int): Robot = {
    println(s"Moving $direction $length cm")
    copy(madeMoves = madeMoves.appended(Move(direction, length)))
  }
}

