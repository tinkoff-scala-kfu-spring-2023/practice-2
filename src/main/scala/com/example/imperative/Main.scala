package com.example.imperative

object Main {
  def main(args: Array[String]): Unit = {
    val robot = new Robot()
    robot.load(new Goods(500))
    robot.go("North", 100)
    robot.go("East", 350)
    robot.go("North", 300)
    robot.go("West", 400)
    robot.unload()
    robot.go("East", 400)
    robot.go("South", 300)
    robot.go("West", 350)
    robot.go("South", 100)
    robot.madeMoves.foreach(m => println(s"Made move ${m.direction} ${m.length} cm"))
  }
}
