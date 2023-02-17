package com.example

import scala.annotation.tailrec

object Main {
  def main(args: Array[String]): Unit = {
    val commands: Array[Command] = Array(
      Load(Goods(500)),
      Move("North", 100),
      Move("South", 1000),
      Unload(300),
      Move("West", 300),
      Unload(200)
    )
    val robot = Robot(None, Array())
    val result = runCommands(robot, commands)
    result.madeMoves.foreach(println)
  }

  def runCommands(robot: Robot, commands: Array[Command]): Robot = {
    if (commands.isEmpty) robot else commands.head match {
      case Load(goods) => runCommands(robot.load(goods), commands.tail)
      case Unload(mass) => runCommands(robot.unload(mass), commands.tail)
      case Move(direction, length) => runCommands(robot.go(direction, length), commands.tail)
    }
  }
}

trait Command

case class Load(goods: Goods) extends Command
case class Unload(mass: Int) extends Command
case class Move(direction: String, length: Int) extends Command
