# Commands and permissions

[← Documentation index](index.md)

## Built-in commands

| Command | Purpose |
| --- | --- |
| `/forms`, `/forms help` | Command help / list |
| `/forms list` | List accessible interfaces |
| `/forms open <id> [args...]` | Open your form or Java menu |
| `/forms send <player> <id> [args...]` | Send an interface to another player |
| `/forms inspect form <id>` | Inspect a parsed Bedrock form |
| `/forms inspect menu <id>` | Inspect a parsed Java menu |
| `/forms inspect item <id>` | Inspect an access item on supported Spigot |
| `/forms give <id> [player]` | Give an access item on supported Spigot |
| `/forms identify [player]` | Check Bedrock player detection |
| `/forms version` | Build metadata |
| `/forms reload` | Reload plugin configuration |

The default command root is `forms` and may be overridden with `root-command` in general config. Some subcommands/platforms have additional restrictions.

## Permission scheme

Base command permissions generally follow `crossplatforms.command.<subcommand>`, e.g. `crossplatforms.command.open`, `crossplatforms.command.send`, `crossplatforms.command.reload`, `crossplatforms.command.identify` and `crossplatforms.command.identify.others`.

For interface ID `warps`:
- Bedrock: `crossplatforms.form.warps.use` and `crossplatforms.form.warps.command`.
- Java: `crossplatforms.menu.warps.use` and `crossplatforms.menu.warps.command`.

The `USE` permission controls access; `COMMAND` additionally controls command opening. Global and per-interface `permission-defaults` accept `TRUE`, `FALSE` or `OP`. If not configured, the source defaults are `USE: TRUE` and `COMMAND: OP`; the shipped templates explicitly make both `TRUE`. Access item permissions have separate rules in [Access items](access-items.md).

## Custom commands / intercepts

In `config.yml`:

~~~yaml
commands:
  open_warps:
    method: register
    command: warpsui
    actions:
      - form: warps
  guard_old_command:
    method: intercept_cancel
    exact: "oldmenu"
    platform: bedrock
    actions:
      - form: warps
~~~

`method` accepts `register`, `intercept_pass` and `intercept_cancel`. `platform` can be `java`, `bedrock` or `all`. The two intercept methods differ in whether the original command still executes. A `register` entry may use a `permission`; if omitted, it uses the platform-specific shortcut default. `intercept_*` permissions may be omitted.

**Reload limitation:** newly defined registered commands may require a restart unless `unsafe-command-registration` is enabled. Use caution: interceptors can overlap commands owned by other plugins.
