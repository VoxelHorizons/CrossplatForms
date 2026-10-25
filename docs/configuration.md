# Configuration reference

[← Documentation index](index.md)

Each configuration is YAML. Fields use hyphenated names; indentation matters. Keep `config-version` intact so built-in migration/transformation logic knows which schema you have. **Back up configurations before upgrading**.

| File | Schema version in source | Root data |
| --- | --- | --- |
| `config.yml` | 3 | `commands`, `unsafe-command-registration`, `enable-debug`, optional `root-command` |
| `bedrock-forms.yml` | 5 | `enable`, `global-permission-defaults`, `forms` |
| `java-menus.yml` | 2 | `enable`, `global-permission-defaults`, `menus` |
| `access-items.yml` | 4 | `enable`, `set-held-slot`, `global-permission-defaults`, `items` |

The templates live in [core resources](../core/src/main/resources/config.yml) and [access item resources](../access-item/src/main/resources/access-items.yml). They are shipped with the plugin, not fetched from the old wiki.

## General configuration

~~~yaml
enable-debug: false
unsafe-command-registration: false
commands:
  lobby_button:
    method: register
    command: lobbyui
    permission: crossplatforms.command.open
    actions:
      - form: lobby
config-version: 3
~~~

The `commands` mapping configures shortcuts and interceptors. At runtime, new `register` commands cannot normally be introduced by a reload unless `unsafe-command-registration` allows it; prefer restarting after adding a command. See [Commands and permissions](commands-and-permissions.md).

## Form and menu settings

Each interface is keyed by its ID. Common fields are `title`, optional `arguments` and `permission-defaults`. Form IDs belong under `forms`; Java interface IDs belong under `menus`. Different platform entries may share an ID.

~~~yaml
global-permission-defaults:
  USE: TRUE
  COMMAND: TRUE
~~~

Possible values are `TRUE`, `FALSE` and `OP`. A specific entry may override global values:

~~~yaml
permission-defaults:
  USE: FALSE
  COMMAND: OP
~~~

Permission names and defaults are detailed in [Commands and permissions](commands-and-permissions.md).

## Reload and diagnostics

`/forms reload` reloads configuration; `/forms inspect form <id>` or `/forms inspect menu <id>` displays a parsed interface. Set `enable-debug: true` temporarily for additional diagnostics, especially when debugging form responses. Don't edit plugin defaults inside a jar: they will be replaced on build.
