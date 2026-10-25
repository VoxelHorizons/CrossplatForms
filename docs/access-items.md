# Spigot/Paper access items

[← Documentation index](index.md)

Access items are shortcuts that run actions when interacted with. The feature is provided by the Spigot/Paper module, **not the Velocity or BungeeCord distributions**. Configure `access-items.yml`:

~~~yaml
enable: true
set-held-slot: false
items:
  warps:
    material: COMPASS
    display-name: "Warps"
    lore:
      - "Right-click to open"
    slot: 4
    platform: ALL
    on-join: true
    on-respawn: true
    on-world-change: false
    persist: false
    actions:
      - form: warps
config-version: 4
~~~

The first hotbar slot is **0**. Set `platform` to `JAVA`, `BEDROCK` or `ALL`. `on-join`, `on-respawn` and `on-world-change` control when shortcuts are issued; `persist` controls whether they remain on player leave. `set-held-slot` controls selection after issue.

Available per-item `permission-defaults` include `POSSESS`, `EVENT`, `COMMAND`, `DROP`, `PRESERVE` and `MOVE`. Use the shipped [template](../access-item/src/main/resources/access-items.yml) for their precise default values and examples.

For player inventories in a live server, test giving, moving, dropping, switching worlds and reconnecting. Do not assume custom inventories or other inventory-management plugins are compatible merely because the plugin builds.

See [Actions](actions.md) for the action list and [Commands and permissions](commands-and-permissions.md) for `/forms give`.
