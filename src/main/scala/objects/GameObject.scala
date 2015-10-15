package objects

import org.denigma.threejs.Object3D

trait GameObject {

  def width: Int

  def height: Int

  def object3d: Object3D

  def inCollisionWith(other: GameObject): Boolean = {
    val yCollide = y - height / 2 < other.y + other.height / 2 && y + height / 2 > other.y + other.height / 2
    val xCollide = x - width / 2 < other.x + other.width / 2 && x + width / 2 > other.x + other.width / 2
    xCollide && yCollide
  }

  def setPosition(x: Double = object3d.position.x, y: Double = object3d.position.y) = {
    object3d.position.x = x
    object3d.position.y = y
  }

  def x = object3d.position.x
  def y = object3d.position.y
}
