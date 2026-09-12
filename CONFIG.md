# More Recipes Configuration

More Recipes creates a `config.yml` in `plugins/More-Recipes/` on first run. Which recipes are
registered is not configurable; the file only controls usage reporting.

| Key | Default | Description |
|-----|---------|-------------|
| `usage-reporting.enabled` | `true` | Whether the plugin reports usage events (see below). Set to `false` to turn it off. |
| `usage-reporting.endpoint` | `https://trace.danielstephenson.dev` | The trace server events are sent to. |
| `usage-reporting.key` | the plugin's key | Identifies this plugin to the trace server so reports are attributed to it. Not a secret: it ships in the default config and can only report as More-Recipes. Empty means reporting is off regardless of `enabled`. |

## Usage reporting

When the plugin is enabled, and each time one of its commands is used, a small event is sent to the
author's [trace](https://github.com/Stephenson-Software/trace-client-java) server so it is known which
plugins are actually in use. An event carries the plugin's name, the event name (`startup` or
`command`), and either the plugin version or the command name — nothing about players, the world, or
the server. Sending happens off the main thread, never delays a tick, and is dropped silently if the
server cannot be reached. Set `usage-reporting.enabled` to `false` to turn it off.
