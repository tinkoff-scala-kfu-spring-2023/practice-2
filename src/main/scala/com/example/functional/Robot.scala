package com.example.functional

case class Robot(goods: Option[Goods], moves: Array[Move]) {
  val maxWeight: Int = 1000
  def runCommand(command: Command): Robot = {
    command match {
      case c: Load => load(c)
      case c: Unload => unload(c)
      case m: Move => move(m)
      case unknown => throw new RuntimeException(s"Illegal command $unknown")
    }
  }

  def move(move: Move): Robot = {
    println(s"Moving to ${move.direction} ${move.length} cm")
    Robot(goods, moves.appended(move))
  }

  def load(load: Load): Robot = {
    if (load.goods.mass > maxWeight) throw new RuntimeException("I cant carry anymore")
    println(s"loaded ${load.goods.mass}")
    Robot(Some(load.goods), moves)
  }

  def unload(unload: Unload): Robot = {
    println(s"unloaded ${unload.mass}")
    Robot(None, moves)
  }

}

trait Command

case class Load(goods: Goods) extends Command

case class Unload(mass: Int) extends Command

case class Move(length: Int, direction: String) extends Command

