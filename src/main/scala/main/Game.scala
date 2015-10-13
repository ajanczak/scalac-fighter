package main

import main.Settings._
import objects.{PlayerShip => Player, SelfMoving, TexturedObject, Bullet, EnemyShip}
import org.denigma.threejs._
import org.scalajs.dom.raw.HTMLElement

import scala.scalajs.js
import scala.scalajs.js.timers.RawTimers
import scala.util.Random

class Game(val container: HTMLElement) {

  val screenWidth = container.clientWidth
  val screenHeight = container.clientHeight

  val gameScene = new GameScene()
  val renderer = new WebGLRenderer()
  val camera = new OrthographicCamera(screenWidth / -2, screenWidth / 2, screenHeight / 2, screenHeight / -2, 1, 10000)
  camera.position.z = 1000

  val playerShip = createPlayerShip

  //game setup
  setUpScene()


  def detectCollisions() = {
    val player = gameScene.objects.filter { case (id, obj) => obj.isInstanceOf[Player] }
    val bullets = gameScene.objects.filter { case (id, obj) => obj.isInstanceOf[Bullet] }
    val mobs = gameScene.objects.filter { case (id, obj) => obj.isInstanceOf[EnemyShip] }
    for {
      (mobId, mob) <- mobs
      (bulletId, bullet) <- bullets
    } {
      if (mob.inCollisionWith(bullet)) {
        gameScene.remove(mobId)
        gameScene.remove(bulletId)
      }
    }
    for {
      (_, playerObj) <- player
      (mobId, mob) <- mobs
    } {
      if (playerObj.inCollisionWith(mob)) {
        gameScene.scene.autoUpdate = false
        println(s"BOOM!")
      }
    }
  }

  def createMob:js.Function0[js.Any] = { () =>
    val mobsCnt = gameScene.objects.count { case (id, obj) => obj.isInstanceOf[EnemyShip] }
    if (mobsCnt < Mobs.MaxCount) {
      val textureName = Random.shuffle(Mobs.Textures).head
      val randomY = Random.nextInt(screenHeight) - screenHeight / 2
      val newMobShip = new EnemyShip(TexturePath + textureName, screenWidth / 2, randomY)
      gameScene.add(newMobShip)
    }
    RawTimers.setTimeout(createMob, Mobs.CreationInterval)
  }

  def renderStep() = {
    detectCollisions()
    cleanUp()
    moveObject()
  }

  def moveObject() = {
    gameScene.objects.foreach {
      case (id, obj: SelfMoving) => obj.move
      case _ =>
    }
  }

  def cleanUp() = {
    val toRemove = gameScene.objects.filterNot {
      case (id, obj) =>
        (obj.object3d.position.x >= -screenWidth / 2) &&
        (obj.object3d.position.x <= +screenWidth / 2)
    }
    toRemove.foreach { case (id, _) => gameScene.remove(id) }
  }


  def shoot() = {
    val newBullet = new Bullet(playerShip.x, playerShip.y)
    gameScene.add(newBullet)
  }

  def movePlayerShip(newX: Option[Double] = None, newY: Option[Double] = None) = {
    import playerShip.object3d._
    position.set(newX.getOrElse(position.x), newY.getOrElse(position.y), position.z)
  }

  private def setUpScene() = {
    renderer.setSize(screenWidth, screenHeight)
    container.appendChild(renderer.domElement)

    //light
    val ambientLight = new AmbientLight(0xbbbbbb)
    gameScene.scene.add(ambientLight)

    //player's ship
    gameScene.add(playerShip)

    //mob create
    RawTimers.setTimeout(createMob, Mobs.CreationInterval)
  }

  private def createPlayerShip: TexturedObject = new Player(
      s"$TexturePath${PlayerShip.Texture}",
      PlayerShip.Width,
      PlayerShip.Height,
      -screenWidth / 2 + PlayerShip.Height / 2,
      0
    )
}