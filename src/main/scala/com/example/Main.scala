package com.example

import scala.util.Try


object Main {
  def main(args: Array[String]): Unit = {
    val input = io.Source.fromFile(args(0))
    val commands = input.getLines().zipWithIndex.map { case (line, idx) =>
      val splitted = line.split(";")
      val mayBeCommand = splitted.headOption.map {
        case "move"   => modifyError(Try {
          val direction = splitted(1)
          val distance = splitted(2).toInt
          Move(direction, distance)
        }, idx)
        case "load"   => modifyError(Try(Load(Goods(splitted(1).toInt, splitted(2).toInt))), idx)
        case "unload" => modifyError(Try(Unload(Goods(splitted(1).toInt, splitted(2).toInt))), idx)
        case _ => Left(s"$line has no command")
      }
      if (mayBeCommand.isEmpty) Left(s"$idx line is empty")
      else mayBeCommand.get
    }.toArray
    val (errors, success) = split(commands, Array(), Array())
    if (errors.nonEmpty) println(s"ACHTUNG: ${errors.mkString("\n")}")
    else {
      val robot = Robot(Array(), Array())
      val result = runCommands(Right(robot), success)
      result match {
        case Right(robot) => robot.madeMoves.foreach(println)
        case Left(error) => println(s"ERROR! ${error.getMessage}")
      }
    }
  }

  def modifyError(attempt: Try[Command], idx: Int): Either[String, Command] =
    attempt.toEither.left.map(ex => s"LINE $idx: " + ex.getMessage)

  def split(array: Array[Either[String, Command]], error: Array[String], success: Array[Command]): (Array[String], Array[Command]) = {
    if (array.isEmpty) (error, success)
    else array.head match {
      case Left(exception) => split(array.tail, error.appended(exception), success)
      case Right(value) => split(array.tail, error, success.appended(value))
    }
  }

  def runCommands(robotOr: Either[Throwable, Robot], commands: Array[Command]): Either[Throwable, Robot] = {
    if (commands.isEmpty)
      robotOr
    else
      commands.head match {
        case Load(goods)   => runCommands(robotOr.flatMap(_.load(goods)), commands.tail)
        case Unload(goods) => runCommands(robotOr.flatMap(_.unload(goods)), commands.tail)
        case Move(direction, length) =>
          runCommands(robotOr.flatMap(_.go(direction, length)), commands.tail)
      }
  }
}

trait Command extends Product with Serializable

case class Load(goods: Goods) extends Command
case class Unload(goods: Goods) extends Command
case class Move(direction: String, distance: Int) extends Command
