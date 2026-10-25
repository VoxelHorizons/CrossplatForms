# Development and maintenance

[← Documentation index](index.md)

## Clone and build

~~~sh
git clone https://github.com/VoxelHorizons/CrossplatForms.git
cd CrossplatForms
./gradlew clean build
~~~

Gradle uses automatically provisioned Java toolchains. GitHub Actions builds with Java 21 and packages platform-specific jars:

- `spigot/build/libs/CrossplatForms-Spigot.jar`
- `velocity/build/libs/CrossplatForms-Velocity.jar`
- `bungeecord/build/libs/CrossplatForms-BungeeCord.jar`

Paper 26.3's **server runtime** Java requirement is independent of the Gradle build launcher. Avoid packaging Geyser/Floodgate/Cumulus APIs: an extra copy of `org.geysermc.cumulus.form.Form` produces a loader constraint violation when calling Floodgate. The distribution CI checks this invariant.

## Relevant modules

| Module | Responsibility |
| --- | --- |
| `core` | Forms, components, action processing, config, permissions |
| `spigot-common` and `spigot` | Version adapters, Paper/Spigot inventory and commands |
| `access-item` | Inventory shortcuts |
| `proxy` | Protocolize inventory abstraction |
| `velocity`, `bungeecord` | Proxy integrations |

## Maintenance rules

- The public contract of YAML fields is the source of truth. Add a migration transformer and tests when changing schema; don't silently repurpose existing fields.
- For newly registered actions, components, parsers or fillers, update the corresponding docs page and demonstrate an actual valid YAML example.
- Add unit tests for parsers and version routing, and smoke-test Bedrock form submission on a live Paper 26.3 environment before claiming full support.
- Do not reintroduce upstream wiki/CI URLs as primary fork links.
- Documentation links are validated by `python3 scripts/check_docs.py`, including the README and this section.

For targeted contributions, open a PR against this fork's `main` branch. Read [Known limitations](known-limitations.md) before claiming an integration is fully tested.
