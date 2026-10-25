# Java inventory menus

[← Documentation index](index.md)

Java menus are configured in `java-menus.yml` as a map of named `menus`, with an inventory `title`, optional `type`, `size`, `buttons` and `allow-bedrock` flag.

~~~yaml
menus:
  warps:
    title: "Warps"
    type: CHEST
    size: 27
    allow-bedrock: false
    buttons:
      11:
        material: COMPASS
        display-name: "Spawn"
        lore:
          - "Click to warp"
        any-click:
          - type: close
          - commands: "player; spawn"
      15:
        material: EMERALD
        display-name: "Shops"
        custom-model-data: 1234
        left-click:
          - form: shops
        right-click:
          - message: "Use left click to open shops."
~~~

Java menu buttons are mapped by **zero-based slot**. The sample above places items in slots 11 and 15. An item supports `material`, `display-name`, `lore`, `custom-model-data` and `skull`. `any-click` executes on both mouse buttons, in addition to the applicable `left-click` or `right-click` list.

Inventory layouts in source: `CHEST`, `HOPPER` and `DISPENSER`. For chest menus, use a multiple of nine from 9 through 54; `size: 5` is supported as a hopper-compatible case. Keep slots inside the chosen inventory. Invalid sizes or slot indices are configuration errors, not extra screen rows.

## Java and Bedrock routing

When a Bedrock form and Java menu share an identifier (e.g. `warps`), `/forms open warps` chooses the matching UI for the player. On Bedrock, forms are preferred. `allow-bedrock: false` prevents Java inventory menus from being offered to Bedrock players unless explicitly allowed.

**Do not emulate multi-slot Java image buttons as duplicate Bedrock buttons.** Bedrock simple forms present one button per list entry; Java background filler items have no direct equivalent.

For images and assets, Java `custom-model-data` is not the same thing as a Bedrock `image`. See [Cross-platform recipes](cross-platform-recipes.md).
