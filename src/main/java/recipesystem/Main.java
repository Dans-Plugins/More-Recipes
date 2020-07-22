package recipesystem;

import org.bukkit.plugin.java.JavaPlugin;
import recipesystem.Subsystems.ItemStackSubsystem;

public final class Main extends JavaPlugin {

    // subsystems
    ItemStackSubsystem itemstacks = new ItemStackSubsystem(this);

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }
}
