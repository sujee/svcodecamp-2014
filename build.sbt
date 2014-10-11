name := "Simple Project"

version := "1.0"

scalaVersion := "2.10.4"

libraryDependencies += "org.apache.spark" %% "spark-core" % "1.1.0"

libraryDependencies += "org.apache.hadoop" % "hadoop-client" % "2.2.0"   exclude("com.google.guava", "guava")
