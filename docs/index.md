# CrossplatForms — VoxelHorizons documentation

**Source of truth for the maintained fork:** [VoxelHorizons/CrossplatForms](https://github.com/VoxelHorizons/CrossplatForms). These pages are stored in the repository and reviewed alongside code, rather than depending on the incomplete upstream GitHub wiki.

CrossplatForms builds Bedrock-native forms for Geyser/Floodgate players and conventional inventory menus for Java players. **The same interface name can resolve to a different interface on each platform.** Forms, actions, commands, placeholders and permissions are YAML-configured.

## Choose a guide

| Task | Guide |
| --- | --- |
| Install on Paper 26.3 or a proxy | [Getting started](getting-started.md) |
| Understand the four YAML files | [Configuration](configuration.md) |
| Build Bedrock simple, modal and custom forms | [Bedrock forms](bedrock-forms.md) |
| Build Java inventory interfaces | [Java menus](java-menus.md) |
| Send commands, messages and open other interfaces | [Actions](actions.md) |
| Use placeholders, form results and arguments | [Placeholders and arguments](placeholders-and-arguments.md) |
| Register/intercept commands; set permissions | [Commands and permissions](commands-and-permissions.md) |
| Make hotbar shortcuts | [Access items](access-items.md) |
| Integrate Java menus with Bedrock forms | [Cross-platform recipes](cross-platform-recipes.md) |
| Upgrade from upstream or resolve errors | [Migration and troubleshooting](migration-and-troubleshooting.md) |
| Build and maintain the fork | [Development](development.md) |
| See unimplemented/untested features | [Known limitations](known-limitations.md) |
| Track remaining engineering gaps | [Maintenance roadmap](maintenance-roadmap.md) |

## Included configuration templates

These are the **actual packaged example files**, useful for comparing a config to the guide:

- [General config](../core/src/main/resources/config.yml)
- [Bedrock forms](../core/src/main/resources/bedrock-forms.yml)
- [Java menus](../core/src/main/resources/java-menus.yml)
- [Access items](../access-item/src/main/resources/access-items.yml)

Avoid copying entire example configs into a production server without reviewing the sample commands, server names and permissions. Examples are illustrative, not a server security policy.

For screenshots see [the gallery](../images/README.md). For live failures, consult [Migration and troubleshooting](migration-and-troubleshooting.md) and the [Maintenance roadmap](maintenance-roadmap.md). GitHub Issues is currently disabled for this fork; maintainers must enable it in repository settings before new issue reports can be filed.
