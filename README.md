<img alt="CrossplatForms" width="500" src="https://github.com/kejonaMC/CrossplatForms/blob/main/images/crossplatForms.svg" /> 

[![Build Status](https://ci.kejonamc.dev/job/CrossplatForms/job/main/badge/icon)](https://ci.kejonamc.dev/job/CrossplatForms/job/main/)
[![Version](https://img.shields.io/badge/version-1.5.0-blue)](https://github.com/kejonaMC/CrossplatForms/releases)
[![License](https://img.shields.io/badge/License-GPL-orange)](https://github.com/kejonaMC/CrossplatForms/blob/master/LICENSE)
[![Discord](https://img.shields.io/discord/853331530004299807?color=7289da&label=discord&logo=discord&logoColor=white)](https://discord.gg/M2SvqCu4e9)
[![bStats](https://img.shields.io/badge/bStats-click%20me-yellow)](https://bstats.org/author/Konicai)
[![Spigot Page](https://img.shields.io/spiget/downloads/101043?color=yellow&label=Spigot%20Page)](https://www.spigotmc.org/resources/crossplatforms.101043/)  

CrossplatForms is a Java Edition plugin aimed at creating Bedrock Edition Forms for [Geyser](https://github.com/GeyserMC/Geyser) players through flexible configurations. Bedrock Forms allow servers to provide players with abritrary choices in a styled menu, which the server then handles in any way desired. This allows you to customize forms specifically for your server, plugins, etc.

Inventory menus for Java Edition players can also be created, on all platforms. Protocolize must be installed on BungeeCord/Velocity for inventory menus to be created on them.

Geyser and Floodgate are not required, and if not present, Bedrock Edition features will be simply disabled.

See the [gallery](images/README.md) for visual examples.  
See the [wiki](https://github.com/kejonaMC/CrossplatForms/wiki) for configuration information.

## Features:

* Define [Actions](https://github.com/kejonaMCs/CrossplatForms/wiki/Common-Configuration-Elements#actions) to run commands, open interfaces, change servers, etc
* Simple, Modal, and Custom [Forms](https://github.com/kejonaMC/CrossplatForms/wiki/bedrock-forms.yml) for Bedrock Edition players
  * Simple and Modal Forms: Trigger different Actions depending on the button pressed
  * Custom Forms: Actions are triggered, and the response of each component is available as a placeholder in actions
* Inventory [menus](https://github.com/kejonaMC/CrossplatForms/wiki/java-menus.yml). Trigger different Actions depending on the button pressed
* [Access Items](https://github.com/kejonaMC/CrossplatForms/wiki/access-items.yml) for players that trigger Actions when clicked in hand
* Register [custom commands](https://github.com/kejonaMC/CrossplatForms/wiki/config.yml) that trigger Actions
* Trigger Actions when existing commands are executed
* Broad placeholder support

## Supported Platforms:

#### For CrossplatForms on BungeeCord/Velocity: If you have both Geyser and Floodgate installed, you'll need to either:  
A: Use [Geyser-Standalone](https://wiki.geysermc.org/geyser/setup/#standalone-setup) instead  
B: Use our [fork](https://github.com/kejonaMC/Floodgate) of Floodgate that allows using the [Floodgate API](https://wiki.geysermc.org/floodgate/api/) on BungeeCord/Velocity while Geyser is also installed. Only install it on the proxy.

BungeeCord and Velocity do not support Access Items.

---

* [`CrossplatForms-Spigot.jar`](https://ci.kejonamc.dev/job/CrossplatForms/job/main/) :&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp; Spigot 1.8.8 - 1.19.4
* [`CrossplatForms-BungeeCord.jar`](https://ci.kejonamc.dev/job/CrossplatForms/job/main/) :&ensp;&ensp;&ensp; BungeeCord
* [`CrossplatForms-Velocity.jar`](https://ci.kejonamc.dev/job/CrossplatForms/job/main/) :&ensp;&ensp;&ensp;&ensp;&ensp; Velocity 3.x

Alternative downloads are available [here](https://github.com/kejonaMC/CrossplatForms/actions/workflows/push-main.yml) and development builds are available [here](https://github.com/kejonaMC/CrossplatForms/actions/workflows/push-dev.yml).

## Requirements:
* Java 8 or higher
* [Geyser](https://github.com/GeyserMC/Geyser) or [Floodgate](https://github.com/GeyserMC/Floodgate) are required for Bedrock Edition specific features. Other features will still work without them.
  * If you want to install CForms on Spigot servers behind a BungeeCord/Velocity proxy, Floodgate must also be installed on the backend servers. Follow this [guide](https://wiki.geysermc.org/floodgate/setup/) closely. If you need setup help or Bedrock players are being treated as Java players, ask the [Geyser Discord](https://discord.gg/geysermc) for help.

### How to use Geyser instead of Floodgate:

Although using Floodgate should always be preferred, it is not required. If you want to use Geyser, [Java 16](https://adoptium.net/) or higher is required. There may be additional steps depending on your platform:

Paper 1.16.5 and newer, Velocity:&ensp;&ensp;&ensp; No extra steps necessary  
Spigot, BungeeCord, Waterfall:&ensp;&ensp;&ensp;&ensp;&ensp; Add `-Djdk.util.jar.enableMultiRelease=force` to your JVM startup flags.

## VoxelHorizons 2026 compatibility fork

This fork adds a modern Bukkit API-backed adapter for **Minecraft Java Edition 26.3** and targets the current **Geyser API 2.11.2-SNAPSHOT**. Minecraft 26.3 Paper requires **Java 25**. It avoids versioned CraftBukkit package assumptions, uses the public skull owner profile API, and avoids removed `Inventory#getTitle` calls.

Install `CrossplatForms-Spigot.jar` on Paper 26.3, with an up-to-date Geyser-Spigot and optionally Floodgate. The Geyser direct-API implementation uses Java 21 multi-release classes; when Floodgate is available it remains the preferred form transport. Build using `./gradlew clean build` with Java 21 to run Gradle; compilation toolchains are provisioned automatically.

**Verification:** A successful build does not demonstrate runtime compatibility. On a Paper 26.3 server, exercise opening and closing simple, modal and custom forms, submitting custom form responses, player-head rendering, inventory clicks, persistent access items, and player transfers using an actual Bedrock client.


### Minecraft 26.3 Floodgate classloading hotfix (1.6.2-voxel)

Form APIs use `org.geysermc.cumulus.form.Form` provided by the running Floodgate/Geyser plugin. CrossplatForms must **not** bundle its own copy of Cumulus: doing so causes `LinkageError: loader constraint violation` when sending Bedrock forms. The fork treats Cumulus as `compileOnlyApi` and verifies that distribution jars contain no unrelocated Geyser, Floodgate or Cumulus API classes. Upgrade from 1.6.1-voxel to 1.6.2-voxel and restart the Paper server before testing form interactions.

## Legacy server compatibility (Minecraft 1.12.2)

The 1.12.2 CraftBukkit revision `v1_12_R1` selects the legacy skull adapter. Older Minecraft releases use `Material.SKULL_ITEM` with data/durability **3** for player heads; `Material.PLAYER_HEAD` only exists after the 1.13 flattening. The fork's legacy adapter creates the correct player-skull item, while modern releases continue to use `PLAYER_HEAD`. Regression tests load the legacy adapter against the **Spigot 1.12.2 API**, and the CI build verifies packaging. This is not a substitute for testing on a real 1.12.2 server with compatible plugins and Java runtime.
