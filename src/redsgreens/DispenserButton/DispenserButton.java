package redsgreens.DispenserButton;

import java.util.HashMap;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

/**
 * DispenserButton for Bukkit
 *
 * @author redsgreens
 */
public class DispenserButton extends JavaPlugin {
    private final DispenserButtonPlayerListener playerListener = new DispenserButtonPlayerListener();
    private final HashMap<Player, Boolean> debugees = new HashMap<Player, Boolean>();
	private static WorldGuardPlugin WorldGuard = null;

    public String Name;
    public String Version;

    public void onEnable() {
        Name = getDescription().getName();
        Version = getDescription().getVersion();
 
		// attempt to hook to the worldguard plugin
    	try{
            Plugin test = getServer().getPluginManager().getPlugin("WorldGuard");

            if (test != null) {
            	WorldGuard = (WorldGuardPlugin)test;
            	System.out.println(this.Name + ": " + WorldGuard.getDescription().getName() + " " + WorldGuard.getDescription().getVersion() + " found");
            }
    	}
    	catch (Exception ex){
    		WorldGuard = null;
    	}
        
        getServer().getPluginManager().registerEvent(Type.PLAYER_INTERACT, playerListener, Priority.Monitor, this);

        System.out.println(this.Name + " v" + this.Version + " is enabled!" );
    }

	public static Boolean canBuild(Player p, Block b)
	{
		if(WorldGuard != null)
			return WorldGuard.canBuild(p, b);
		else
			return true;
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

