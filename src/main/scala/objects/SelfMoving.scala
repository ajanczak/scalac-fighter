package objects

import scala.util.Random

trait SelfMoving {
  def move: Unit
  val direction: Direction
}

case class Direction(deltaX: Int, deltaY: Int)
object Direction {
  def straight(speed: Int)  = Direction(speed, 0)
  def withRandomShift(speed: Int) = Direction(speed, Random.nextInt(2) - 1)
}