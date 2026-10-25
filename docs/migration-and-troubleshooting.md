# Migration and troubleshooting

[← Documentation index](index.md)

## Updating from the original project

1. Back up all `config.yml`, `bedrock-forms.yml`, `java-menus.yml` and `access-items.yml` files.
2. Replace only the CrossplatForms jar with the **matching fork distribution**, and ensure your Geyser/Floodgate plugin versions are compatible.
3. Keep your existing configs. The plugin has versioned migrations; review any transformed output and confirm all custom actions survived.
4. Run `/forms version`, `/forms list`, `/forms inspect form <id>` / `menu <id>` and `/forms identify`.
5. Test one simple form, modal form, custom form submission, Java menu click and every important command/action with **real clients**.

Documentation and downloads from the original upstream wiki/CI are not authoritative for this fork.

## Symptoms and first checks

| Symptom | Likely check |
| --- | --- |
| `This server version is unsupported` on 26.3 | Outdated jar. Confirm fork build at least `1.6.1-voxel`; current fork includes modern CraftBukkit adapter detection |
| `LinkageError` for `org.geysermc.cumulus.form.Form` | Duplicate Cumulus bundled into an old CrossplatForms build. Use `1.6.2-voxel` or later; current CI inspects each built jar |
| `No Bedrock Handler being used` | Neither Geyser nor Floodgate detected / available at plugin startup |
| Player opens Java inventory instead of form | Verify Floodgate forwarding, `/forms identify`, target `forms:` ID and `allow-bedrock` |
| `<id> doesn't exist` | Check the name, YAML indentation, `enable`, form/menu configuration parsing logs and platform routing |
| No permission / no visible entry | Check root command permission plus per-interface `.use` and `.command` |
| Command not registered after reload | New shortcuts may require a restart; see `unsafe-command-registration` |
| Missing Bedrock icon | Texture path/URL must exist for Bedrock; Java resource-pack icons are not automatically converted |
| Invalid form result | Enable `enable-debug: true` temporarily; check component field types and result parser sequence |
| Player switched to nonexistent backend | `server` action requires a correctly named proxy destination |

## Diagnostics to attach to an issue

- Fork version (`/forms version`), server name/release and Java version.
- Geyser/Floodgate versions and installation locations (proxy vs backend).
- Result of `/forms identify <player>` where applicable.
- The smallest YAML interface reproducing the problem (redact secrets and private endpoints).
- Full relevant console exception from start through final `Caused by:`.

For compatibility status and unverified cases read [Known limitations](known-limitations.md) and the [Maintenance roadmap](maintenance-roadmap.md). GitHub Issues is currently disabled for this fork; enabling it in repository settings is needed before submitting bug reports through the issue tracker.
