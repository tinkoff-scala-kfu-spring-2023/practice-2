package com.example.imperative

class Robot {
  var carry: Option[Goods] = None
  private var maxWeight = 1000
  var madeMoves: Array[Moves] = Array()
  def load(goods: Goods): Unit = {
    if (goods.mass > maxWeight) throw new RuntimeException("I can't carry anymore")
    carry = Some(goods)
    println(s"Loading ${goods.mass}")
  }

  def unload(): Unit = {
    println(s"Unloading ${carry.map(_.mass)}")
    carry = None
  }

  def go(direction: String, length: Int): Unit = {
    println(s"Moving $direction $length cm")
    madeMoves = madeMoves.appended(new Moves(direction, length))
  }
}

class Moves(dir: String, len: Int) {
  var direction: String = dir
  var length: Int = len
}
