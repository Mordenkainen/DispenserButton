package redsgreens.DispenserButton;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.inventory.ItemStack;

/**
 * Handle events for all Player related events
 * @author redsgreens
 */
public class DispenserButtonPlayerListener extends PlayerListener {

    @Override
    public void onPlayerInteract(PlayerInteractEvent event)
    // catch player click events
    {
    	
    	// return if the event is cancelled
    	if(event.isCancelled()) return;
    	
		// return if the event is not a right-click-block action
		Action action = event.getAction();
		if(action != Action.RIGHT_CLICK_BLOCK) return;

		// return if the block is not a dispenser or piston
		Block block = event.getClickedBlock();
		Material blockMaterial = block.getType();
		if(blockMaterial != Material.DISPENSER && blockMaterial != Material.PISTON_BASE && blockMaterial != Material.PISTON_STICKY_BASE && blockMaterial != Material.PISTON_MOVING_PIECE) return;

		// return if they don't have a button or lever in hand
		Player player = event.getPlayer();
		ItemStack item = player.getItemInHand();
		Material itemMaterial = item.getType();
		if(itemMaterial != Material.STONE_BUTTON && itemMaterial != Material.LEVER) return;

		// determine which face was clicked and attach the corresponding button
		Block button;
		switch(event.getBlockFace())
		{
		case EAST: // facing east
			button = block.getRelative(BlockFace.EAST); 
			if(button.getType() == Material.AIR)
			{
				button.setType(itemMaterial);
				button.setData((byte)4);
			}
			break;
		case WEST: // facing west
			button = block.getRelative(BlockFace.WEST); 
			if(button.getType() == Material.AIR)
			{
				button.setType(itemMaterial);
				button.setData((byte)3);
			}
			break;
		case NORTH: // facing north
			button = block.getRelative(BlockFace.NORTH); 
			if(button.getType() == Material.AIR)
			{
				button.setType(itemMaterial);
				button.setData((byte)2);
			}
			break;
		case SOUTH: // facing south
			button = block.getRelative(BlockFace.SOUTH); 
			if(button.getType() == Material.AIR)
			{
				button.setType(itemMaterial);
				button.setData((byte)1);
			}
			break;
		case UP:
			if(itemMaterial == Material.STONE_BUTTON) return; // buttons can't go on the top
			button = block.getRelative(BlockFace.UP); 
			if(button.getType() == Material.AIR)
			{
				button.setType(itemMaterial);
				button.setData((byte)5);
			}
			break;
		default: // top or bottom was clicked, do nothing
			return;			
		}

		// take the item from the player
		if(item.getAmount() == 1)
			player.setItemInHand(null);
		else
		{
			item.setAmount(item.getAmount() - 1);
			player.setItemInHand(item);
		}

		// cancel the event so the inventory screen doesn't load
		event.setCancelled(true);

    }
}

