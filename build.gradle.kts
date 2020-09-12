import java.net.URI

plugins {
  kotlin("jvm") version "1.4.0"
  kotlin("kapt") version "1.4.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
  mavenCentral()
  jcenter()
  maven { url = URI("https://dl.bintray.com/arrow-kt/arrow-kt/") }
}

val arrow_version = "0.10.5"
dependencies {
  implementation(kotlin("stdlib"))

  implementation("io.arrow-kt:arrow-core:$arrow_version")
  implementation("io.arrow-kt:arrow-syntax:$arrow_version")
  kapt("io.arrow-kt:arrow-meta:$arrow_version")
}
