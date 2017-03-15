name := "FormationEnsai"

version := "1.0"

scalaVersion := "2.11.8"

val scalaTestV = "2.2.6"

val sparkV = "1.6.2"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % sparkV,
  "org.apache.spark" %% "spark-sql" % sparkV,
  "com.databricks" %% "spark-csv" % "1.5.0"
)
    