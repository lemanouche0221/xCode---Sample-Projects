name := "smarter-playlists"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  "org.apache.commons" % "commons-io" % "1.3.2",
  "commons-collections" % "commons-collections" % "3.2.1"
)     

play.Project.playJavaSettings
