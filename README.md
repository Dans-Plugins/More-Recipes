# What is this?
This Minecraft plugin adds static recipes for items that are not craftable in vanilla Minecraft.

## Supported Minecraft Versions
This plugin is supported on the Minecraft versions listed in [`minecraft-versions.json`](minecraft-versions.json): currently **1.19.4**, **1.21.11** and **26.2** (Spigot and its forks). Every stable release is booted on a real server of each of these versions before it is published, and every build checks that the plugin only uses Bukkit API that exists on all of them. Other versions from 1.19.4 onwards are expected to work but are not tested. To support another version, add it to the file: both checks pick it up.

## Download
- [SpigotMC](https://www.spigotmc.org/resources/more-recipes.81832/)
- [GitHub releases](https://github.com/Dans-Plugins/More-Recipes/releases)

## Documentation

- [User Guide](USER_GUIDE.md) — installation, the items that gain recipes and what each recipe takes, and the permissions table
- [Commands](COMMANDS.md) — every `/mr` command and the permission it needs
- [Configuration](CONFIG.md) — every `config.yml` key
- [Contributing](CONTRIBUTING.md) — building, testing, and opening a pull request
- [Changelog](CHANGELOG.md)

## Usage reporting

Usage reporting is on by default: More-Recipes sends its name, version and command names (a `startup` event when it enables and a `command` event each time one of its commands is used) to <https://trace.danielstephenson.dev> so it is known which plugins are actually in use. Nothing about players, worlds, IPs or the server is sent, and nothing typed after a command is sent either.

To turn it off:

- for this plugin: `usage-reporting.enabled: false` in `plugins/More-Recipes/config.yml`
- for every plugin on the server that reports this way: `enabled: false` in `plugins/trace/config.yml` (created the first time such a plugin enables)
- for the whole server process: the environment variable `TRACE_USAGE_REPORTING=off` or `DO_NOT_TRACK=1`

Each startup logs whether reporting is on or, if it is off, why. Details: <https://github.com/Stephenson-Software/trace#usage-reporting>

## License

This project is licensed under the [GNU General Public License v3.0](LICENSE) (GPL-3.0).

You are free to use, modify, and distribute this software, provided that:
- Source code is made available under the same license when distributed.
- Changes are documented and attributed.
- No additional restrictions are applied.

See the [LICENSE](LICENSE) file for the full text of the GPL-3.0 license.
