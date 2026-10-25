
dependencies {
    compileOnly("org.spigotmc:spigot-api:1.12.2-R0.1-SNAPSHOT")
    api(projects.spigotCommon.v19R2)
    // Use the actual 1.12.2 Material enum at test runtime.
    testImplementation("org.spigotmc:spigot-api:1.12.2-R0.1-SNAPSHOT")
}
