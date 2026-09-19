# Contributing

## Thank You

Thank you for your interest in contributing to More Recipes! This guide will help you get started.

## Links

- [Website](https://dansplugins.com)
- [Discord](https://discord.gg/xXtuAQ2)

## Requirements

- A GitHub account
- Git installed on your local machine
- A Java IDE or text editor
- A basic understanding of Java

## Getting Started

1. [Sign up for GitHub](https://github.com/signup) if you don't have an account.
2. Fork the repository by clicking **Fork** at the top right of the repo page.
3. Clone your fork: `git clone https://github.com/<your-username>/More-Recipes.git`
4. Open the project in your IDE.
5. Build the plugin: `mvn clean package`
   If you encounter errors, please open an issue.

## Identifying What to Work On

### Issues

Work items are tracked as [GitHub issues](https://github.com/Dans-Plugins/More-Recipes/issues).

### Milestones

Issues are grouped into [milestones](https://github.com/Dans-Plugins/More-Recipes/milestones) representing upcoming releases.

## Making Changes

1. Make sure an issue exists for the work. If not, create one.
2. Switch to `main`: `git checkout main`
3. Create a branch: `git checkout -b <branch-name>`
4. Make your changes.
5. Test your changes.
6. Commit: `git commit -m "Description of changes"`
7. Push: `git push origin <branch-name>`
8. Open a pull request against `main`, link the related issue with `#<number>`.
9. Address review feedback.

## Testing

Run the unit tests with:

    mvn test

The tests live under `src/test/java/dansplugins/recipesystem/`, mirroring the package of the class they cover (`commands/GetCommandTest.java` covers `commands/GetCommand.java`, and so on). They use JUnit 5, with Mockito standing in for Bukkit collaborators (a stub `Server` where the code reaches for `Bukkit` statically); no Spigot server is started. Add or update a test for every behaviour change, and put it in the mirrored package.

`mvn clean package` also runs the suite before building the JAR, and the `Build` workflow runs the same command on every pull request, so a failing test blocks CI.

Some things can only be checked on a real server — recipe registration is accepted without complaint at startup even when a recipe is malformed, and the tests inspect what is handed to the server rather than crafting with it. For manual testing, place the built JAR from `target/` into a local Spigot server's `plugins` folder and restart the server.

## Questions

Ask in the [Discord server](https://discord.gg/xXtuAQ2).
