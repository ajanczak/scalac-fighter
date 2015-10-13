package objects

import main.Settings.PlayerShip._

class PlayerShip(
    override val texturePath: String,
    override val width: Int = Width,
    override val height: Int = Height,
    override val startX: Double = 0,
    override val startY: Double = 0
  ) extends TexturedObject(texturePath, width, height, startX, startY)  {

}
