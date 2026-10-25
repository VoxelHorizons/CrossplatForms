# Maintenance roadmap and audit findings

[← Documentation index](index.md)

This is the fork's actionable backlog until GitHub Issues is enabled. Checked items below are based on source or CI evidence, not necessarily on a live Bedrock client's behaviour.

## Completed in the documentation overhaul

- [x] Replace obsolete external wiki references with a maintained `docs/` handbook.
- [x] Explain supported YAML schemas, actions, forms, Java menus, access items, permissions and cross-platform routing.
- [x] Introduce Markdown link integrity checking in GitHub Actions.
- [x] Fix shared Java menu layout state mutation and add bounds validation plus unit tests.
- [x] Correct the broken example player filler action and an outdated material in packaged sample YAML.

## High priority: live Bedrock compatibility

- [ ] Paper 26.3 with current Geyser/Floodgate: test a real Bedrock client opening, responding to and closing simple/modal/custom forms.
- [ ] Test custom form label indexing, dropdown/step slider text vs index, hidden components, input parsers and cancellation/invalid response.
- [ ] Verify same-ID Java/Bedrock menu routing, `/forms identify`, permission defaults, PlaceholderAPI and named argument forwarding.

## High priority: Java and integrations

- [ ] Test Java inventory click types, closing/opening menus, custom item models, skull/skin behaviour and inventory preservation.
- [ ] Test access item giving, clicking, dropping, world switching, reconnecting and compatibility with inventory-management plugins.
- [ ] Validate Velocity/BungeeCord inventories through Protocolize and backend transfers with Floodgate forwarding.

## Engineering debt

- [ ] Extend schema validation beyond inventory size/slots to action requirements and invalid numeric form inputs.
- [ ] Review all remaining TODO comments in configuration manager, builders and legacy adapter code. Each TODO needs a reproduction or an explicit reason to defer.
- [ ] Audit command actions receiving custom input to prevent unintended console command execution.
- [ ] Establish a repeatable integration smoke-test environment; Gradle/JUnit builds cannot substitute for live Bedrock form flows.
- [ ] Add versioned documentation updates to the definition of done for future API or YAML changes.

## Publication and project administration

- [ ] **Enable GitHub Issues** in repository Settings so maintainers/users can file bugs and track individual work. It is currently disabled.
- [ ] If a native GitHub Wiki is required, publish the reviewed `docs/` content via a separate wiki workflow or GitHub Pages. `docs/` already works as a Markdown handbook without additional hosting.
- [ ] Remove or update a limitation in [Known limitations](known-limitations.md) only when supported by tests or real server evidence.

All work and tracking belong to **VoxelHorizons/CrossplatForms**; do not rely on modifications to the original project's wiki or GitHub repository.
