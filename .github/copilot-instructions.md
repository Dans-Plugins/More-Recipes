# Copilot Instructions

This repository follows the DPC (Dans Plugins Community) conventions defined at
https://github.com/Dans-Plugins/dpc-conventions. Read those conventions before
making any changes.

## Technology Stack

- Language: Java
- Build tool: Maven
- Target platform: Spigot / Paper (Minecraft plugin)
- API version: 1.13+

## Project Structure

- `src/main/java/dansplugins/recipesystem/` – Plugin source code
- `src/main/java/dansplugins/recipesystem/commands/` – Command handlers
- `src/main/java/dansplugins/recipesystem/objects/` – Recipe definitions (one class per item)
- `src/main/resources/` – `plugin.yml`

## Coding Conventions

- Each craftable item has its own class in the `objects/` package.
- To add a new recipe, create a new class in `objects/` and register it in the main plugin class.

## Contribution Workflow

- Branch from `main` for all changes.
- Open a pull request against `main`.
- Reference the related GitHub issue in every pull request description.
