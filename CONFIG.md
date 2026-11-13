# More Recipes Configuration

More Recipes creates a `config.yml` in `plugins/More-Recipes/` on first run. Which recipes are
registered is not configurable; the file only controls usage reporting.

| Key | Default | Description |
|-----|---------|-------------|
| `usage-reporting.enabled` | `true` | Whether the plugin reports usage events (see below). Set to `false` to turn it off. |
| `usage-reporting.endpoint` | `https://trace.danielstephenson.dev` | The trace server events are sent to. `""` means reporting is off regardless of `enabled`. |
| `usage-reporting.key` | the plugin's key | Identifies this plugin to the trace server so reports are attributed to it. Not a secret: it ships in the default config and can only report as More-Recipes. `""` means reporting is off regardless of `enabled`. |

A key left with no value at all (`endpoint:` with nothing after it) reads as its default, not as `""`, so reporting stays on.

## Usage reporting

When the plugin is enabled, and each time a player uses one of its commands, a small event is sent to the
author's [trace](https://github.com/Stephenson-Software/trace-client-java) server so it is known which
plugins are actually in use. An event carries the plugin's name, the event name (`startup` or
`command`), the plugin version, and for a `command` event the command name — `mr` or `morerecipes`, whichever
base command was used, never the sub-command (`get`, `list`, `help`) or anything after it — plus a random server ID (the
`server-id` line in `plugins/trace/config.yml`, which identifies no person, account or IP address; deleting the
line gets a new one) and any tags set in a `tags:` block of that same file (none unless the server owner adds
some; the file's header shows `ci: "true"` as an example) — nothing about players or the world. Commands whose
sender is not a player — the console, a command block, or another plugin dispatching as the console — send no event;
a command another plugin runs as a player (`player.performCommand`, or `Bukkit.dispatchCommand` with a player as
the sender) is sent like one the player typed. Sending happens off the main thread, never delays a
tick, and is dropped silently if the
server cannot be reached. Set `usage-reporting.enabled` to `false` to turn it off.

Repeats are counted rather than sent one by one. The first `command` event with a given command name goes out at
once, and further ones with that name in the next minute are sent together as one event when the minute ends, for as
long as they keep coming. That event carries how many it stands for, as its value and as a `coalesced` tag. Whatever is
still being counted is sent when the plugin is disabled. `/mr list` and `/mr get` both send the command name `mr`, so
they are counted together, and `startup` events are never held back.

It can also be turned off for every plugin on the server that reports this way, with `enabled: false`
in `plugins/trace/config.yml` (created the first time such a plugin enables; plugins never turn it
back on), or for the whole server process with the environment variable `TRACE_USAGE_REPORTING=off`
or `DO_NOT_TRACK=1`. Each startup logs whether reporting is on or, if it is off, why. The
`usage-reporting` block is written into `config.yml` on the first enable that finds it missing, so
the switch is visible on servers upgraded from before it existed. Details:
<https://danielstephenson.dev/usage-reporting>.

## bStats

Separately from usage reporting, the plugin also starts [bStats](https://bstats.org/) metrics when it
is enabled (plugin ID `12140`). bStats is not controlled by this plugin's `config.yml`, and none of the
usage-reporting switches above (`usage-reporting.enabled`, `plugins/trace/config.yml`,
`TRACE_USAGE_REPORTING`, `DO_NOT_TRACK`) turn it off.

Five minutes after startup, and every thirty minutes after that, data is sent to `https://bStats.org`:
the plugin's name, version and bStats ID, and about the server, a random server UUID (the `serverUuid`
line in `plugins/bStats/config.yml`), the number of players online, whether the server is in online
mode, the server software and its version, the Java version, the operating system's name, architecture
and version, and the number of CPU cores. No player names or UUIDs are sent. If another plugin on the
server also uses bStats, the data may be sent by that plugin rather than this one.

`plugins/bStats/config.yml` is created by the first plugin on the server that uses bStats. Set
`enabled: false` there to turn bStats off for every plugin on the server that uses it.
