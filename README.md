<p align="center"><img alt="CrossplatForms" width="500" src="images/crossplatForms.svg"></p>

# CrossplatForms — VoxelHorizons fork

[![Build](https://github.com/VoxelHorizons/CrossplatForms/actions/workflows/PR-master.yml/badge.svg)](https://github.com/VoxelHorizons/CrossplatForms/actions)
[![Version](https://img.shields.io/badge/version-1.6.2--voxel-blue)](https://github.com/VoxelHorizons/CrossplatForms/actions)
[![GPL licence](https://img.shields.io/badge/license-GPL-informational)](LICENSE)

An independently maintained fork of CrossplatForms, enabling **Bedrock Edition forms** through Geyser/Floodgate and **Java Edition inventory menus** with configurable commands, actions, placeholders and hotbar shortcuts.

## Documentation / wiki

**[Browse the maintained documentation](docs/index.md)** — source-aligned guides stored alongside the fork's implementation:

- [Getting started on Minecraft 26.3](docs/getting-started.md) · [Configuration](docs/configuration.md)
- [Bedrock simple, modal and custom forms](docs/bedrock-forms.md) · [Java menus](docs/java-menus.md)
- [Actions](docs/actions.md) · [Placeholders and arguments](docs/placeholders-and-arguments.md)
- [Commands and permissions](docs/commands-and-permissions.md) · [Access items](docs/access-items.md)
- [Cross-platform UI conversion recipes](docs/cross-platform-recipes.md)
- [Migration and troubleshooting](docs/migration-and-troubleshooting.md) · [Known limitations](docs/known-limitations.md) · [Development](docs/development.md)

The incomplete upstream GitHub wiki and outdated upstream CI are **not** the documentation/download sources for this fork.

## Downloads and installation

Download platform jars from **[VoxelHorizons GitHub Actions](https://github.com/VoxelHorizons/CrossplatForms/actions)**:

| Jar | Intended platform |
| --- | --- |
| `CrossplatForms-Spigot.jar` | Paper / Spigot |
| `CrossplatForms-Velocity.jar` | Velocity |
| `CrossplatForms-BungeeCord.jar` | BungeeCord |

Install the matching jar in the platform's plugin folder. For Bedrock-specific features, install compatible Geyser or Floodgate; Floodgate is preferred when both are present. Proxy-side Java inventory menus require Protocolize. Access items are available on Paper/Spigot only.

**Minecraft 26.3:** This fork has a modern CraftBukkit adapter and compiles against modern Geyser/Floodgate APIs. Paper 26.3 requires **Java 25** in the targeted deployment. The **1.6.2-voxel** build fixes a duplicate-Cumulus `LinkageError`; CI now rejects packaged Geyser/Floodgate/Cumulus API classes. A successful build is *not* a live Bedrock integration test—see [validation status](docs/known-limitations.md).

## Build from source

~~~sh
git clone https://github.com/VoxelHorizons/CrossplatForms.git
cd CrossplatForms
./gradlew clean build
python3 scripts/check_docs.py
~~~

The shaded distribution jars are written under `spigot/build/libs/`, `velocity/build/libs/` and `bungeecord/build/libs/`.

## Credits and support

Originally developed by the contributors of [kejonaMC/CrossplatForms](https://github.com/kejonaMC/CrossplatForms). Maintained for VoxelHorizons under the terms of the [repository licence](LICENSE).

[Open an issue](https://github.com/VoxelHorizons/CrossplatForms/issues) · [Submit a pull request](https://github.com/VoxelHorizons/CrossplatForms/pulls) · [Screenshot gallery](images/README.md).
