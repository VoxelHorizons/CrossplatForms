
dependencies {
    compileOnly("org.spigotmc:spigot-api:1.12.2-R0.1-SNAPSHOT")
    api(projects.spigotCommon.v19R2)
}

    // Run legacy-head checks against the real 1.12.2 Material enum: the
    // flattened PLAYER_HEAD constant must not be needed by this adapter.
    testImplementation("org.spigotmc:spigot-api:1.12.2-R0.1-SNAPSHOT")
