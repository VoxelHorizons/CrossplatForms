# Installation and first working form

[← Documentation index](index.md)

## Platform and dependencies

Download the relevant **fork-built** artifact from [VoxelHorizons GitHub Actions](https://github.com/VoxelHorizons/CrossplatForms/actions). Builds publish `CrossplatForms-Spigot.jar`, `CrossplatForms-Velocity.jar` and `CrossplatForms-BungeeCord.jar`. Do not install multiple platform jars on the same server.

| Server | Plugin jar | Notes |
| --- | --- | --- |
| Paper / Spigot | Spigot | The tested build pipeline targets Minecraft 26.3; in-game compatibility must also be checked |
| Velocity | Velocity | Proxy-side Java inventory menus require Protocolize |
| BungeeCord | BungeeCord | Proxy-side Java inventory menus require Protocolize |

For Paper **26.3**, use the Java runtime required by that Paper release (**Java 25** for the environment used by this fork). Geyser or Floodgate is needed for Bedrock-specific forms; **Floodgate takes priority when both are enabled**. Without either, Java-only menus can still work.

For Bedrock players connected through a proxy, configure Geyser/Floodgate forwarding according to their official setup guidance. If CrossplatForms is installed on a backend Paper server, Bedrock identity must be available there.

## Set up

1. Put `CrossplatForms-Spigot.jar` in `plugins/` (or the corresponding platform jar in the proxy plugin folder).
2. Install a compatible Geyser/Floodgate plugin, then restart. Confirm `[CForms]` enables and logs the selected Bedrock handler.
3. Start once to create configuration files, then edit `plugins/CrossplatForms/bedrock-forms.yml` and `java-menus.yml`. The installed folder is provided by the platform; confirm its name on your server.
4. Enable Bedrock forms with `enable: true` and add the form below under `forms:` (do **not** replace the entire file or remove `config-version`).
5. Reload with `/forms reload`, or restart if commands or dependencies were added.

~~~yaml
forms:
  welcome:
    type: simple_form
    title: "Welcome"
    content: "Pick a destination"
    buttons:
      - text: "Survival"
        actions:
          - commands: "player; spawn"
      - text: "Help"
        actions:
          - message: "Visit our help menu with /help."
~~~

As a Bedrock player, run `/forms open welcome`. Players need `crossplatforms.command.open` plus the form's `.use` and `.command` permissions (see [Commands and permissions](commands-and-permissions.md)).

## First Java menu

Place this entry under `menus:` in `java-menus.yml`. A Java player running `/forms open welcome` can open this version while Bedrock players see the form above.

~~~yaml
menus:
  welcome:
    title: "Welcome"
    size: 9
    buttons:
      4:
        material: COMPASS
        display-name: "Open help"
        any-click:
          - commands: "player; help"
~~~

See [Cross-platform recipes](cross-platform-recipes.md) for important differences between Bedrock buttons and Java inventory slots.
