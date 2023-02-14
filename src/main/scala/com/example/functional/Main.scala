package com.example.functional

import scala.annotation.tailrec

object Main {

  def main(args: Array[String]): Unit = {
    val route: Array[Command] = Array(Load(Goods(500)), Move(100, "South"), Move(300, "West"), Move(450, "North"), Unload(250))

    val robot = Robot(None, Array())
    val path = route

    val robotAfterRun = run(robot, path)
    robotAfterRun.moves.foreach(m => println(s"Made move ${m.direction} ${m.length} cm"))
  }

  @tailrec
  private def run(robot: Robot, commands: Array[Command]): Robot = {
    if (commands.isEmpty)
      robot
    else
      run(robot.runCommand(commands.head), commands.tail)
  }
}