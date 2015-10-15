package main

import objects.GameObject
import org.denigma.threejs.Scene

object GameScene {
  def apply = new GameScene
}

/**
 * Wrapper for three.js scene.
 * Allows us to use our objects types and move objects.
 * @param scene three.js scene
 * @param objects map holding objects
 */
class GameScene(
  val scene: Scene = new Scene,
  var objects: Map[Double, GameObject] = Map[Double, GameObject]()) {

  def add(gameObject: GameObject) = {
    scene.add(gameObject.object3d)
    objects = objects + (gameObject.object3d.id -> gameObject)
  }

  def remove(id: Double) = {
    scene.remove(objects(id).object3d)
    objects = objects - id
  }
}
