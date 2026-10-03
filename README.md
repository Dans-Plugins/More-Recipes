# What is this?
This Minecraft plugin adds static recipes for items that are not craftable in vanilla Minecraft.

## Supported Minecraft Versions
This plugin is supported on the Minecraft versions listed in [`minecraft-versions.json`](minecraft-versions.json): currently **1.19.4**, **1.21.11** and **26.2** (Spigot and its forks). Every stable release is booted on a real server of each of these versions before it is published, and every build checks that the plugin only uses Bukkit API that exists on all of them. Other versions from 1.19.4 onwards are expected to work but are not tested. To support another version, add it to the file: both checks pick it up.

## Download
- [SpigotMC](https://www.spigotmc.org/resources/more-recipes.81832/)
- [GitHub releases](https://github.com/Dans-Plugins/More-Recipes/releases)

## Works Well With
More Recipes is part of the **survival flavour** set of Dan's Plugins. These are companion plugins that suit the same kind of server and run side by side; More Recipes does not depend on or call into any of them.

- [Food Spoilage](https://github.com/Dans-Plugins/FoodSpoilage) ([SpigotMC](https://www.spigotmc.org/resources/food-spoilage.81507/), `/dpm get foodspoilage`): food goes bad over time.
- [Wild Pets](https://github.com/Dans-Plugins/Wild-Pets) ([SpigotMC](https://www.spigotmc.org/resources/wild-pets.95800/), `/dpm get wildpets`): players tame any entity and keep it as a pet.
- [SimpleSkills](https://github.com/Dans-Plugins/SimpleSkills) ([SpigotMC](https://www.spigotmc.org/resources/simpleskills.98039/), `/dpm get simpleskills`): skills that level up as players play and unlock benefits.
- [Medieval Cookery](https://github.com/Dans-Plugins/Medieval-Cookery) (no SpigotMC page, no stable release yet): cooking recipes for custom foods, defined by the server owner.

Running a medieval roleplay server? The [Medieval Roleplay Engine](https://github.com/Dans-Plugins/Medieval-Roleplay-Engine#works-well-with) set lists the plugins for that.

Every plugin above is listed on [dansplugins.com](https://dansplugins.com). More Recipes is listed at [dansplugins.com/resources/more-recipes](https://dansplugins.com/resources/more-recipes) and can be installed in game with [Dan's Plugin Manager](https://github.com/Dans-Plugins/Dans-Plugin-Manager): `/dpm get morerecipes`.

## Documentation

- [User Guide](USER_GUIDE.md) — installation, the items that gain recipes and what each recipe takes, and the permissions table
- [Commands](COMMANDS.md) — every `/mr` command and the permission it needs
- [Configuration](CONFIG.md) — every `config.yml` key
- [Contributing](CONTRIBUTING.md) — building, testing, and opening a pull request
- [Changelog](CHANGELOG.md)

## Usage reporting

Usage reporting is on by default: More-Recipes sends its name, version and command names (a `startup` event when it enables and a `command` event each time one of its commands is used) to <https://trace.danielstephenson.dev> so it is known which plugins are actually in use. Nothing about players, worlds or IPs is sent, and nothing typed after a command is sent either.

Each event also carries a random server ID (the `server-id` line in `plugins/trace/config.yml`) so
servers can be counted rather than events. It identifies no person, account or IP address; delete
the line to get a new one.

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
