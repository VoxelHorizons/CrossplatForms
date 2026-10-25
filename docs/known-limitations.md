# Known limitations and validation status

[← Documentation index](index.md)

This page distinguishes what exists in source from what has been confirmed on a live server.

## Confirmed from source

- YAML-defined Bedrock simple/modal/custom forms and Java inventories.
- Cumulus components for label, input, dropdown, slider, step slider and toggle.
- Actions for messages, commands, opening another interface, backend server switching, and Bedrock packet transfers.
- Spigot/Paper access items; proxy-side Java inventory menus depend on Protocolize.
- Separate Java and Bedrock interfaces under the same ID.
- Minecraft 26.3 compatibility changes and protection against bundling duplicate Cumulus API classes in distribution jars.

## Not automatically provided

- DeluxeMenus config conversion, splitting/merging pages, or automated asset/image generation.
- Conversion of Java custom model data or fonts into Bedrock texture paths.
- A universal bridge for every plugin-specific placeholder without relevant integrations.
- Cross-platform inventory rendering identical to Minecraft Java UI.

## Still needs real-server verification

- Every form type and response path (including closing/invalid responses) on the latest Geyser/Floodgate pair.
- Different Bedrock and Java clients exercising the same logical UI.
- Proxy transfer paths on Velocity/BungeeCord and standalone Geyser topology.
- Access item interaction and preservation with other inventory plugins.
- Head textures, placeholders and nontrivial live data fillers.
- Older supported server releases; compiling legacy adapters alone does not prove runtime success.

Current automated builds verify tests, shaded jar packaging and classloader boundary checks, **not** end-to-end Bedrock sessions.

## Backlog surfaced by source audit

- JavaMenu now validates chest sizes and button slots before opening. Additional config validation for other modules (including action-specific validation) remains future work.
- Documentation was not versioned with the fork. This documentation set now replaces obsolete upstream wiki links; every new feature should update its reference page and example.
- Several integrations and old compatibility paths need runtime regression tests rather than relying only on build success.

See [Development](development.md) to contribute tests and update these statements when validation is available.
