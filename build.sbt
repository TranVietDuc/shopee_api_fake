lazy val json4sNative = "org.json4s" %% "json4s-native" % "4.0.5"
lazy val mysql = "mysql" % "mysql-connector-java" % "8.0.20"
lazy val scalikejdbc = "org.scalikejdbc" %% "scalikejdbc"  % "3.5.0"
lazy val scalikejdbcConfig = "org.scalikejdbc" %% "scalikejdbc-config" % "3.5.0"
lazy val scalikejdbcPlayInit = "org.scalikejdbc" %% "scalikejdbc-play-initializer" % "2.8.0-scalikejdbc-3.5"
lazy val mysqlConnector = "mysql" % "mysql-connector-java" % "8.0.28"


lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    name := """play-scala-starter-example""",
    version := "1.0-SNAPSHOT",
    scalaVersion := "2.13.8",
    libraryDependencies ++= Seq(
      guice,
      "org.scalatestplus.play" %% "scalatestplus-play" % "5.1.0" % Test,
      json4sNative,
      mysql,
      scalikejdbc,
      scalikejdbcConfig,
      scalikejdbcPlayInit,
      mysqlConnector
    ),
    scalacOptions ++= Seq(
      "-feature",
      "-deprecation",
      "-Xfatal-warnings"
    )
  )
