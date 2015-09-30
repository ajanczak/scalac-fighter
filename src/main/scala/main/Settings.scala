package main

object Settings {

  val ContainerName = "3d-container"
  val TexturePath = "resources/textures/"

  object Mobs {
    val MaxCount = 30
    val CreationInterval = 300
    val Textures = List("java.png", "ruby.png", "dotnet.png")
  }

  object EnemyShip {
    val Width = 44
    val Height = 60
    val Speed = 4
  }

  object PlayerShip {
    val Texture = "scalac.png"
    val Width = 44
    val Height = 60
  }

  object Bullet {
    val Width = 10
    val Height = 3
    val Speed = 7
  }

}
