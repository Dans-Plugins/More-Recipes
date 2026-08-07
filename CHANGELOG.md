# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/).

## [Unreleased]

### Fixed
- `/mr get` now rejects amounts below `1` instead of reporting success while adding nothing
- `/mr get` now reports how many items didn't fit when the inventory can only hold part of the requested amount, instead of silently discarding the remainder
- `/mr get` no longer refuses to run when every inventory slot is occupied but an existing stack still has room
- `/mr help` can now be used from the server console
- `/mr get` no longer throws an uncaught `NumberFormatException` (and a generic error message) when given a non-numeric amount; it now shows the usage message instead
- `/mr list` now checks the `morerecipes.listitems` permission (matching `plugin.yml` and the docs) instead of the undeclared `morerecipes.list`

### Removed
- Stray empty `master` file at the repository root

## [1.7.0]

### Added
- Crafting recipes for saddle, chainmail armour, horse armour, name tag, lead, cobweb, grass block, gunpowder, prismarine shard, slime ball, string, totem of undying, and blaze rod
- `/mr help`, `/mr list`, and `/mr get` commands
