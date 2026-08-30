# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/).

## [Unreleased]

### Changed

- The set of items the plugin provides is now enumerated once, in a `MoreRecipesItem` catalog, instead of being hand-maintained in the recipe registry, the `/mr get` lookup and the `/mr list` listing separately. Those three now iterate the catalog, so an item can no longer reach one of them and silently miss the others — previously, omitting one of the three edits produced an item with no recipe, an item `/mr get` refused, or an item invisible to `/mr list`, none of which failed the build. The items provided, their recipes and the order `/mr list` presents them in are unchanged.
- `USER_GUIDE.md` now lists items under the exact names `/mr get` accepts (`BlazeRod` rather than `Blaze Rod`), and a test holds that list in step with the catalog.
- Recipe registration is now covered by tests that inspect the recipes handed to the server: every catalog item registers exactly one recipe, yielding its own item, under a key of its own, with a shape the server will accept and an ingredient behind every filled slot. Nothing about a recipe is settled until a server loads the plugin, and a recipe that asks for the wrong ingredients, duplicates another recipe's key or yields the wrong item is accepted there without complaint, so until now the only way to find one was to craft with it. No recipe changed.

### Fixed

- The `Dev Release` workflow now retries publishing the `dev` prerelease before giving up. The release and its tag have to be deleted and recreated for the tag to move to the new commit, and a transient API failure inside that window previously left the repository with no `dev` release at all until the workflow was re-run by hand. Each attempt now starts from a clean slate, and an exhausted retry fails loudly.

### Added

- A `Dev Release` workflow, which republishes a rolling `dev` prerelease of `main` on every non-documentation push. This is what Dan's Plugin Manager's experimental channel installs from: `/dpm get morerecipes --experimental` reads `releases/tags/dev`, so without it there is nothing for that command to download. The prerelease is unreleased, unreviewed code and is marked as such.

## [2.0.0-SNAPSHOT-8-8-2026] – 2026-08-08

### Changed
- More-Recipes is now developed AI-first. Day-to-day feature work, grooming, review and maintenance run through AI agents working directly against this repository, with the maintainers setting direction and approving what lands. The major version bump marks that change in how the project is built — it is not a break in behaviour, configuration or stored data, and existing installations can upgrade in place. Released as `2.0.0-SNAPSHOT-8-8-2026`: the AI-first line has not yet been verified in live operation, and the dated snapshot designation stays until it has.

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
