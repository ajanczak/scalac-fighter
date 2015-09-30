package objects

import main.Settings.Bullet._
import org.denigma.threejs._

import scala.scalajs.js

case class Bullet(
    startX: Double = 0,
    startY: Double = 0,
    override val width: Int = Width,
    override val height: Int = Height,
    override val speed: Int = Speed)
  extends GameObject
  with SelfMoving{

  override val object3d: Object3D = create()

  override def move = {
    object3d.position.x += speed
  }

  private def create() = {
    val geometry = new PlaneGeometry(width, height)
    val material = new MeshBasicMaterial
    material.setValues(js.Dynamic.literal(color = 0xf43b3c, opacity = 0.5))
    val mesh = new Mesh(geometry, material)
    mesh.position.x = startX
    mesh.position.y = startY
    mesh
  }
}
