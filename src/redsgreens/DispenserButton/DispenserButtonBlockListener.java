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
			Material adjMaterial = getBlockBehindButton(block).getType();

	    	if(adjMaterial == Material.PISTON_BASE || adjMaterial == Material.PISTON_STICKY_BASE || adjMaterial == Material.PISTON_MOVING_PIECE)
	    	{
	    		event.setCancelled(true);
	    		return;
	    	}
	    		
		}
	}
	
	private Block getBlockBehindButton(Block b)
	{
		Material m = b.getType();
		if(m != Material.LEVER && m != Material.STONE_BUTTON)
			return b;

		Integer d = ((Byte)b.getData()).intValue();
		if(d>8) d-=8;

		switch(d)
		{
		case 4:
			return b.getRelative(BlockFace.WEST);
		case 3:
			return b.getRelative(BlockFace.EAST);
		case 2:
			return b.getRelative(BlockFace.SOUTH);
		case 1:
			return b.getRelative(BlockFace.NORTH);
		case 5:
			return b.getRelative(BlockFace.DOWN);
		default:
			return b;				
		}
	}
}
