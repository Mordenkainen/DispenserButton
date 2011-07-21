package redsgreens.DispenserButton;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockPhysicsEvent;

public class DispenserButtonBlockListener extends BlockListener {

	@Override
	public void onBlockPhysics(BlockPhysicsEvent event)
	{
		Block block = event.getBlock();
		Material material = block.getType();
		if(material == Material.LEVER || material == Material.STONE_BUTTON)
		{
	    	Material[] adjMaterials = {block.getRelative(BlockFace.NORTH).getType(), block.getRelative(BlockFace.EAST).getType(), block.getRelative(BlockFace.SOUTH).getType(), block.getRelative(BlockFace.WEST).getType()};
	    	for(int i=0; i<adjMaterials.length; i++)
	    		if(adjMaterials[i] == Material.PISTON_BASE || adjMaterials[i] == Material.PISTON_STICKY_BASE || adjMaterials[i] == Material.PISTON_MOVING_PIECE)
	    		{
	    			event.setCancelled(true);
	    			return;
	    		}
	    		
		}
	}
	
}
