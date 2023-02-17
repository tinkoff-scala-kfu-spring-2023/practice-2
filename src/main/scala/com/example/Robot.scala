package com.example

case class Robot(carry: Option[Goods], madeMoves: Array[Move]) {
  private val maxWeight = 1000
  def load(goods: Goods): Robot = {
    if (goods.mass > maxWeight) throw new RuntimeException("I can't carry anymore")
    println(s"Loading ${goods.mass}")
    Robot(Some(goods), madeMoves)
  }

  def unload(mass: Int): Robot = {
    println(s"Unloading ${carry.map(_.mass)}")
    copy(carry.map(goods => goods.copy(goods.mass - mass)))
  }

  def go(direction: String, length: Int): Robot = {
    println(s"Moving $direction $length cm")
    copy(madeMoves = madeMoves.appended(Move(direction, length)))
  }
}

