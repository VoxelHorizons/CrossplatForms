# Cross-platform implementation recipes

[← Documentation index](index.md)

## One command, two interfaces

Create a `warps` Bedrock form in `bedrock-forms.yml`:

~~~yaml
forms:
  warps:
    type: simple_form
    title: "Warps"
    buttons:
      - text: "Survival spawn"
        actions:
          - commands: "player; spawn"
      - text: "Shops"
        actions:
          - commands: "player; shops"
~~~

Create a matching Java menu in `java-menus.yml`:

~~~yaml
menus:
  warps:
    title: "Warps"
    size: 9
    allow-bedrock: false
    buttons:
      3:
        material: COMPASS
        display-name: "Survival spawn"
        any-click:
          - commands: "player; spawn"
      5:
        material: EMERALD
        display-name: "Shops"
        any-click:
          - commands: "player; shops"
~~~

Both can be opened with `/forms open warps`; the API chooses the interface appropriate to the recipient. A separate command need not be registered per edition.

## Translating a DeluxeMenus interface

| Java inventory | Bedrock simple form |
| --- | --- |
| Multiple slots composing a single graphical button | **One** `buttons` entry |
| Glass/background items | Omit unless meaningful as an action |
| Paged inventory controls | Usually a single scrolling list of Bedrock buttons |
| Global navigation strip (profile, warps, settings) | Separate navigation form linked with `- form: navigation` |
| Custom model data item icon | A Bedrock URL or in-pack texture path; not Java model data |

CrossplatForms does **not** automatically parse DeluxeMenus YAML or convert Java resource pack models. Existing menus require deliberate field-by-field conversion.

## Global navigation as its own form

~~~yaml
forms:
  navigation:
    type: simple_form
    title: "Menu"
    buttons:
      - text: "Warps"
        actions:
          - form: warps
      - text: "Profile"
        actions:
          - form: profile
      - text: "Settings"
        actions:
          - form: settings
~~~

The `profile` and `settings` targets need to exist, and their USE permissions must allow the player. Their names here are examples, not built-in interfaces.

## Safety checks

Verify the same button performs the equivalent command or destination on each edition, and ensure Bedrock permission checks and identity detection work before exposing privileged actions. See [Migration and troubleshooting](migration-and-troubleshooting.md).
