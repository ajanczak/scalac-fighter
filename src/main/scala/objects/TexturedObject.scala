package objects

import org.denigma.threejs._
import scala.scalajs.js

class TexturedObject(
    val texturePath: String,
    val width: Int,
    val height: Int,
    val startX: Double = 0,
    val startY: Double = 0)
  extends GameObject {

  override val object3d: Object3D = cratePlane

  def cratePlane = {
    val texture = ImageUtils.loadTexture(texturePath)
    val material = new MeshLambertMaterial()
    material.setValues(js.Dynamic.literal(map = texture))
    val mesh = new Mesh(new PlaneGeometry(width, height), material)
    // setPosition(startX, startY)  ->  this cause errors but compilation is fine
    mesh.position.x = startX
    mesh.position.y = startY
    mesh
  }
}
