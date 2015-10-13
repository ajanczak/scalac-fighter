package main

import org.scalajs.dom
import org.scalajs.dom.raw.HTMLElement
import org.scalajs.jquery.{JQueryEventObject, jQuery}

import scala.scalajs.js
import scala.scalajs.js.JSApp

object Main extends JSApp {

  def main(): Unit = {
    jQuery(setup _)
  }

  def setup() = {
    // this gonna help us to se where is this method in generated js code
    val track1 = "setup fun start"; track1.indexOf(1)

    val gameHTMLContainer = dom.document.getElementById(Settings.ContainerName).asInstanceOf[HTMLElement]
    val game = new Game(gameHTMLContainer)

    jQuery("div").mousemove{ event: JQueryEventObject =>
      val mouseY = event.asInstanceOf[org.scalajs.dom.MouseEvent].clientY
      val updatedY = game.screenHeight / 2 - mouseY
      game.movePlayerShip(newY = Some(updatedY))
    }
    jQuery("div").mousedown{ event: JQueryEventObject =>
      game.shoot()
    }

    def renderLoop(timestamp: Double): Unit = {
      dom.requestAnimationFrame(renderLoop _)
      renderStep()
      game.renderer.render(game.gameScene.scene, game.camera)
    }
    renderLoop(System.currentTimeMillis())

    def renderStep() = {
      game.renderStep()
    }

  }

}