# Actions reference

[← Documentation index](index.md)

An `actions` entry is an ordered YAML list of operations triggered by a form button, menu click, custom-form response, access item or registered command. Only use actions in contexts where the corresponding platform implementation supports them.

## Common actions

~~~yaml
actions:
  - message: "Hello %player_name%"
  - commands:
      - "player; say Hello"
      - "console; tell %player_name% Teleporting..."
  - form: warps
~~~

| Operation | Syntax | Notes |
| --- | --- | --- |
| Message | `- message: "text"` or `messages: [ ... ]` | Adventure legacy text by default; configurable `format` / `character` |
| Command | `- commands: "player; spawn"` | A scalar or list. Without a prefix, commands default to **console** |
| Another interface | `- form: warps` | Resolves Java menu or Bedrock form for recipient |
| Proxy/backend switch | `- server: survival` | Needs a named backend and working platform transfer |
| Bedrock packet transfer | `- type: transfer_packet` | Requires `address` and optional `port` (default 19132); Bedrock only |
| Close current Java menu | `- type: close` | Registered for supported Java menu platforms |

## Command execution contexts

~~~yaml
- commands:
    - "player; spawn"
    - "console; effect give %player_name% speed 5"
~~~

Available prefixes: `player;`, `op;` and `console;`. **An unprefixed command also runs as console.** Treat `op;` and `console;` as privileged operations; do not directly inject untrusted Bedrock input such as `%result_0%` into them. Restrict who can open the form, and validate or whitelist choices.

## Opening another form with named arguments

~~~yaml
- form: player_profile
  arguments:
    player: "%player_name%"
~~~

The target interface must declare `arguments:`. Arguments are **not implicitly inherited** when opening a child interface; pass them explicitly. See [Placeholders and arguments](placeholders-and-arguments.md).

## Bedrock transfer packet

~~~yaml
- type: transfer_packet
  address: "play.example.net"
  port: "19132"
~~~

Use this for Bedrock-client transfer requests. It is different from `server: survival`, which asks the proxy/server implementation to switch a player to an existing backend.

## Closing / invalid responses

Set `incorrect-actions` on a Bedrock form to handle closing or invalid form responses. Avoid assuming a specific response or form-opening order when a client disconnects mid-submission.
