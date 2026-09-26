# What is this?
This Minecraft plugin adds static recipes for items that are not craftable in vanilla Minecraft.

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
