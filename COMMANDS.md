# More Recipes Commands

All commands use `/mr` or `/morerecipes` as the base.

| Command | Description | Permission |
|---------|-------------|------------|
| `/mr` | Show the plugin version, the authors and a link to the wiki. | None |
| `/mr help` | View a list of commands. | `morerecipes.help` |
| `/mr list` | List all items that can be crafted with More Recipes. | `morerecipes.listitems` |
| `/mr get <itemName> <amount>` | Give yourself a specified amount of a More Recipes item. Players only; the console is refused. | `morerecipes.get` |

`<itemName>` is one of the names `/mr list` prints (for example `NameTag`), and `<amount>` is a
whole number of at least `1`. Items that don't fit in the inventory are not given: the reply says
how many were left out, or `Inventory full.` if none fit.
