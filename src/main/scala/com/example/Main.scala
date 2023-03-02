package com.example


object Main {
  def main(args: Array[String]): Unit = {
    val input = io.Source.fromFile(args(0))
    val commands = input.getLines().map { line =>
      val splitted = line.split(";")
      splitted.headOption.map {
        case "move"   => Move(splitted(1), splitted(2).toInt)
        case "load"   => Load(Goods(splitted(1).toInt, splitted(2).toInt))
        case "unload" => Unload(Goods(splitted(1).toInt, splitted(2).toInt))
      }.get
    }
    val robot = Robot(Array(), Array())
    val result = runCommands(robot, commands.toArray)
    result.madeMoves.foreach(println)
  }

  def runCommands(robot: Robot, commands: Array[Command]): Robot = {
    if (commands.isEmpty)
      robot
    else
      commands.head match {
        case Load(goods)   => runCommands(robot.load(goods), commands.tail)
        case Unload(goods) => runCommands(robot.unload(goods), commands.tail)
        case Move(direction, length) =>
          runCommands(robot.go(direction, length), commands.tail)
      }
  }
}

trait Command

case class Load(goods: Goods) extends Command
case class Unload(goods: Goods) extends Command
case class Move(direction: String, distance: Int) extends Command
