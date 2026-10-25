# Bedrock forms

[← Documentation index](index.md)

Configure `bedrock-forms.yml` with `enable: true` and entries under `forms:`. All forms have an ID, a `type` and a `title`. Forms use [actions](actions.md) after a button click or valid custom form submission.

## Simple form: one-click navigation

~~~yaml
forms:
  warps:
    type: simple_form
    title: "Warps"
    content: "Choose where to go"
    buttons:
      - text: "Spawn"
        image: "textures/items/compass_item"
        actions:
          - commands: "player; spawn"
      - text: "Shop"
        actions:
          - form: shop
~~~

`buttons` are a **list**, not a map of inventory slots. Each button may have `text`, optional `image` and `actions`. An image is interpreted as a URL when it starts with `https://` or `http://`; otherwise as a Bedrock texture path. Use a texture that exists on the Bedrock client/resource pack; Java custom-model IDs do not automatically become Bedrock button icons.

`fillers` can generate additional buttons, such as a player list:

~~~yaml
fillers:
  - type: player
    format:
      actions:
        - message: "Selected %raw_text%"
~~~

## Modal form: confirm or cancel

~~~yaml
forms:
  confirm:
    type: modal_form
    title: "Confirm"
    content: "Return to spawn?"
    button1:
      text: "Yes"
      actions:
        - commands: "player; spawn"
    button2:
      text: "No"
~~~

Both `button1` and `button2` are required.

## Custom form: collect values

~~~yaml
forms:
  preferences:
    type: custom_form
    title: "Preferences"
    components:
      - type: label
        text: "Choose your settings"
      - type: dropdown
        text: "Favourite world"
        options: ["Survival", "Creative"]
        default-option: 0
      - type: toggle
        text: "Show tips"
        default-value: true
      - type: input
        text: "Nickname"
        placeholder: "Optional"
        parsers:
          - type: block-placeholders
    actions:
      - message: "World: %result_1% | Tips: %result_2% | Nickname: %result_3%"
~~~

The registered component types are `label`, `dropdown`, `input`, `slider`, `step_slider` and `toggle`. Common component options include `text`, `parsers`, `should-show`, `strip-formatting` and `mode`. The implementation includes labels in result indexing: the first component is `%result_0%`. A hidden component uses its configured fallback/default value.

| Component | Useful fields |
| --- | --- |
| `label` | `text` |
| `dropdown` | `options`, `default-option`, `return-text`, `fillers` |
| `input` | `placeholder`, `default-text`, `parsers` |
| `slider` | `min`, `max`, `step`, `default-value` |
| `step_slider` | `steps`, `default-step`, `return-text`, `fillers` |
| `toggle` | `default-value` |

For `dropdown` and `step_slider`, `return-text` is true by default; setting false returns the selected numeric index. `incorrect-actions` can be configured on a form for closed or invalid responses (the underlying handler shares this behaviour across types).

See [Placeholders and arguments](placeholders-and-arguments.md) for input handling, dynamic values and security.
