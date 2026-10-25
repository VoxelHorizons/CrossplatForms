# Placeholders, form responses and arguments

[← Documentation index](index.md)

## Built-in player placeholders

The fallback placeholder implementation replaces `%player_name%` and `%player_uuid%`. On Paper, installing PlaceholderAPI enables additional compatible placeholders (e.g. those provided by your other plugins), where supported by the resolver.

Placeholders are evaluated against the **recipient** of a form/menu. A form that opens another form must explicitly forward named arguments if needed.

## Named parameters

Declare positional arguments on an interface:

~~~yaml
forms:
  profile:
    type: simple_form
    title: "Profile: %target%"
    arguments:
      - target
    buttons:
      - text: "Open again"
        actions:
          - form: profile
            arguments:
              target: "%target%"
~~~

Open with `/forms open profile Steve`, or send with `/forms send Steve profile Alex`. The declared identifier `target` becomes `%target%`. The current command handler expects the number of space-delimited extra words to match the number of arguments exactly; use short single-token values.

## Results of custom forms

Custom form components are indexed from zero in their configured order. `%result_0%` refers to component 0, including labels (labels receive a fallback result). `%result_1%` refers to component 1, and so on. For a dropdown or step slider, `return-text: true` returns the option text; `return-text: false` returns an index. The result placeholders are available while its configured `actions` run.

## User input and parsers

A Bedrock text field can contain arbitrary user input. **Never concatenate it directly into privileged server commands**. The included parser types are:

| Type | What it does |
| --- | --- |
| `block-placeholders` | Replaces placeholder-like substrings (such as `%token%` or `{token}`) with `<blocked-placeholder>` |
| `placeholders` | Resolves supported placeholders on the submitted value |
| `replacements` | Replaces literal strings using a configured mapping |

Example of explicit literal replacements:

~~~yaml
parsers:
  - type: replacements
    replacements:
      "&": "and"
~~~

The parser list processes entries in their declared order. **Blocking placeholders is not a general command-injection sanitizer.** Prefer selecting from controlled options rather than executing commands derived from free text.
