package redsgreens.DispenserButton;

import java.util.HashMap;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * DispenserButton for Bukkit
 *
 * @author redsgreens
 */
public class DispenserButton extends JavaPlugin {
    private final DispenserButtonPlayerListener playerListener = new DispenserButtonPlayerListener();
    private final DispenserButtonBlockListener blockListener = new DispenserButtonBlockListener();
    private final HashMap<Player, Boolean> debugees = new HashMap<Player, Boolean>();

    public String Name;
    public String Version;

    public void onEnable() {
        Name = getDescription().getName();
        Version = getDescription().getVersion();

        getServer().getPluginManager().registerEvent(Type.PLAYER_INTERACT, playerListener, Priority.Monitor, this);
        getServer().getPluginManager().registerEvent(Type.BLOCK_PHYSICS, blockListener, Priority.Monitor, this);

        System.out.println(this.Name + " v" + this.Version + " is enabled!" );
    }

    public void onDisable() {
        System.out.println(this.Name + " v" + this.Version + " is disabled." );
    }

    public boolean isDebugging(final Player player) {
        if (debugees.containsKey(player)) {
            return debugees.get(player);
        } else {
            return false;
        }
    }

    public void setDebugging(final Player player, final boolean value) {
        debugees.put(player, value);
    }
}

