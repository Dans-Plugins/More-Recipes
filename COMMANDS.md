# More Recipes Commands

All commands use `/mr` or `/morerecipes` as the base.

| Command | Description | Permission |
|---------|-------------|------------|
| `/mr` | Show the plugin version, the authors and a link to the [user guide](USER_GUIDE.md). | None |
| `/mr help` | View a list of commands. | `morerecipes.help` |
| `/mr list` | List all items that can be crafted with More Recipes. | `morerecipes.listitems` |
| `/mr get <itemName> <amount>` | Give yourself a specified amount of a More Recipes item. Players only; the console is refused. | `morerecipes.get` |

`<itemName>` is one of the names `/mr list` prints (for example `NameTag`; case doesn't matter), and `<amount>` is a
whole number of at least `1`. Items that don't fit in the inventory are not given: the reply says
how many were left out, or `Inventory full.` if none fit.

Anything after `/mr` that is not one of the sub-commands above (a typo such as `/mr lsit`, for
example) is answered with `That command wasn't found.`

## When `/mr get` gives nothing

`/mr get` gives nothing and replies as follows when its arguments can't be used:

| Input | Reply |
|-------|-------|
| The item name or the amount is missing, or the amount is not a whole number (`/mr get Saddle two`) | `Usage: /morerecipes get (itemName) (amount)` |
| The amount is `0` or less | `Amount must be at least 1.` |
| The item name is not one `/mr list` prints | `That isn't an item in More Recipes!` |
| Typed at the server console, with or without arguments | `This command can't be used in the console.` |
| None of the items fit in the inventory | `Inventory full.` |
