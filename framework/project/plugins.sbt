resolvers += Classpaths.typesafeResolver

addSbtPlugin("com.typesafe.sbtscalariform" % "sbt-scalariform" % "0.1.4")

ivyLoggingLevel := UpdateLogging.DownloadOnly
