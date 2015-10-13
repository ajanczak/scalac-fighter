package objects

import main.Settings.EnemyShip._

case class EnemyShip(
    override val texturePath: String,
    override val startX: Double = 0,
    override val startY: Double = 0,
    override val width: Int = Width,
    override val height: Int = Height,
    override val direction: Direction = Direction.withRandomShift(Speed))
  extends TexturedObject(texturePath, width, height, startX, startY)
  with SelfMoving {

  override def move: Unit = {
    object3d.position.x -= direction.deltaX
    object3d.position.y -= direction.deltaY
  }
}
