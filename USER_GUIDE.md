# More Recipes User Guide

## What is More Recipes?

More Recipes is a Spigot plugin that adds crafting recipes for items that are normally unobtainable or difficult to craft in vanilla Minecraft, including saddles, chainmail armour, name tags, and more.

## Installation

1. Download the latest `More-Recipes-<version>.jar` from the [Releases](https://github.com/Dans-Plugins/More-Recipes/releases) page.
2. Place the JAR in your server's `plugins/` folder.
3. Restart the server.

## Craftable Items

The following items gain crafting recipes. Each name below is also the name `/mr get` accepts:

- BlazeRod
- ChainmailBoots
- ChainmailChestplate
- ChainmailHelmet
- ChainmailLeggings
- Cobweb
- DiamondHorseArmor
- GoldenHorseArmor
- GrassBlock
- Gunpowder
- IronHorseArmor
- Lead
- NameTag
- PrismarineShard
- Saddle
- SlimeBall
- String
- TotemOfUndying

Use `/mr list` in-game to see all available items.

## Getting Items Directly

Operators can use `/mr get <itemName> <amount>` in-game to give themselves a specific item without crafting it. The command can't be run from the server console.

## Permissions

| Permission | Default | Description |
|------------|---------|-------------|
| `morerecipes.help` | `true` | View the help menu. |
| `morerecipes.listitems` | `true` | List craftable items. |
| `morerecipes.get` | `op` | Use `/mr get` to receive items directly. |

## Support

Ask questions in the [Discord server](https://discord.gg/xXtuAQ2) or open a [GitHub issue](https://github.com/Dans-Plugins/More-Recipes/issues).
