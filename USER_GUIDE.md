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

## Recipes

Every recipe is shaped: the ingredients go in a crafting table's 3×3 grid laid out as below. Each
row cell lists that row's three slots from left to right, and `—` is a slot left empty. Grass is the
short grass plant, not a grass block.

| Item | Top row | Middle row | Bottom row | Yields |
|------|---------|------------|------------|--------|
| BlazeRod | Iron Block, Lava Bucket, Iron Block | Iron Block, Lava Bucket, Iron Block | Iron Block, Lava Bucket, Iron Block | 1 |
| ChainmailBoots | —, —, — | Iron Bars, —, Iron Bars | Iron Bars, —, Iron Bars | 1 |
| ChainmailChestplate | Iron Bars, —, Iron Bars | Iron Bars, Iron Bars, Iron Bars | Iron Bars, Iron Bars, Iron Bars | 1 |
| ChainmailHelmet | Iron Bars, Iron Bars, Iron Bars | Iron Bars, —, Iron Bars | —, —, — | 1 |
| ChainmailLeggings | Iron Bars, Iron Bars, Iron Bars | Iron Bars, —, Iron Bars | Iron Bars, —, Iron Bars | 1 |
| Cobweb | String, —, String | —, String, — | String, —, String | 1 |
| DiamondHorseArmor | Diamond, Diamond, Diamond | Diamond, Diamond, Diamond | String, —, String | 1 |
| GoldenHorseArmor | Gold Ingot, Gold Ingot, Gold Ingot | Gold Ingot, Gold Ingot, Gold Ingot | String, —, String | 1 |
| GrassBlock | —, —, — | —, Grass, — | —, Dirt, — | 1 |
| Gunpowder | Sand, Gravel, Sand | Gravel, Sand, Gravel | Sand, Gravel, Sand | 1 |
| IronHorseArmor | Iron Ingot, Iron Ingot, Iron Ingot | Iron Ingot, Iron Ingot, Iron Ingot | String, —, String | 1 |
| Lead | String, String, — | String, Iron Block, — | —, —, String | 1 |
| NameTag | Paper, Paper, — | Paper, Paper, — | —, —, String | 1 |
| PrismarineShard | Kelp, —, Kelp | Brick, Iron Nugget, Brick | Brick, Iron Nugget, Brick | 8 |
| Saddle | Leather, Leather, Leather | Leather, —, Leather | Iron Block, —, Iron Block | 1 |
| SlimeBall | Kelp, Kelp, Kelp | Kelp, Water Bucket, Kelp | Kelp, Kelp, Kelp | 1 |
| String | Grass, Grass, — | —, Grass, — | —, Grass, Grass | 1 |
| TotemOfUndying | —, Diamond, — | Diamond Block, Diamond Block, Diamond Block | Emerald, Diamond Block, Emerald | 1 |

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
