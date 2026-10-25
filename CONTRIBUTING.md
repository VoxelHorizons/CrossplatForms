# Contributing to VoxelHorizons/CrossplatForms

Please submit changes to **this fork**, not the dormant upstream repository. GitHub Issues is currently disabled; maintainers can enable it in repository settings to accept bug reports. Track known work in [the maintenance roadmap](docs/maintenance-roadmap.md).

## Build and validate

~~~sh
./gradlew clean build
python3 scripts/check_docs.py
~~~

The platform jars are `CrossplatForms-Spigot.jar`, `CrossplatForms-Velocity.jar` and `CrossplatForms-BungeeCord.jar` under their respective `build/libs/` directories. Keep Geyser/Floodgate/Cumulus APIs external to the packaged jar, since duplicate API classes produce runtime linkage errors.

## Contribution requirements

- Follow existing style and nullability conventions.
- Any breaking configuration change needs a `config-version` migration and regression tests.
- New actions, parsers, fillers, components or commands need working YAML examples and relevant updates to [the maintained documentation](docs/index.md).
- Cover parsers and version routing with unit tests; **live** Bedrock-client behaviour must be checked separately.
- Update [Known limitations](docs/known-limitations.md) when a formerly unverified feature is tested.
- Do not reintroduce dead upstream wiki/download links.

Include plugin/server/Geyser versions, sanitized YAML and stack traces in bug reports.
