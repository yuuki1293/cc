ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.0"

lazy val root = (project in file("."))
  .settings(
    name := "cc"
  )

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest-flatspec" % "3.2.16" % "test",
  "org.scalatest" %% "scalatest-diagrams" % "3.2.16" % "test",
)