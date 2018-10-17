name := "akka-api-gateway-example"

version := "0.1.0"

scalaVersion := "2.12.4"

organization := "jp.co.dzl"

libraryDependencies ++= Seq(
  "com.typesafe.akka"      %% "akka-http"                   % "10.1.1",
  "com.typesafe.akka"      %% "akka-stream"                 % "2.5.11",
  "io.circe"               %% "circe-generic"               % "0.10.0-M1",
  "io.circe"               %% "circe-parser"                % "0.10.0-M1",
  "de.knutwalker"          %% "akka-http-circe"             % "3.5.0",
  "org.scaldi"             %% "scaldi"                      % "0.5.8",
  "com.pauldijou"          %% "jwt-core"                    % "0.16.0",
  "org.scala-lang.modules" %% "scala-xml"                   % "1.1.0-newCollectionsBootstrap",
  "org.apache.curator"      % "curator-framework"           % "4.0.1",
  "org.apache.curator"      % "curator-recipes"             % "4.0.1",
  "com.typesafe.akka"      %% "akka-http-testkit"           % "10.1.1" % "test",
  "com.typesafe.akka"      %% "akka-stream-testkit"         % "2.5.11" % "test",
  "org.scalatest"          %% "scalatest"                   % "3.0.3"  % "test",
  "org.scalamock"          %% "scalamock-scalatest-support" % "3.6.0"  % "test"
)

test in assembly := {}

mainClass in assembly := Some("jp.co.dzl.example.akka.api")

fork in Test := true
