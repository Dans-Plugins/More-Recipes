package recipesystem.Subsystems;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import recipesystem.Commands.GetCommand;
import recipesystem.Main;

public class CommandSubsystem {

    Main main = null;

    public CommandSubsystem(Main plugin) {
        main = plugin;
    }

    public boolean interpretCommand(CommandSender sender, String label, String[] args) {
        if (label.equalsIgnoreCase("morerecipes") || label.equalsIgnoreCase("mr")) {

            if (args.length == 0) {
                sender.sendMessage(ChatColor.AQUA + "== More Recipes " + main.version + " ==");
                sender.sendMessage(ChatColor.AQUA + "Author: DanTheTechMan");
            }

            if (args[0].equalsIgnoreCase("get")) {

                GetCommand command = new GetCommand(main);
                command.getItem(sender, args);

            }

        }

        return false;
    }

}
