package simplewarp.plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Tag;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class SimpleWarpListener implements Listener {
	
	public SimpleWarpListener(SimpleWarpMain plugin) {
		
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
	if (event.getPlayer().hasPermission("simplewarp.tpthroughsigns")) {
		 Player player = event.getPlayer();
		  Block block = event.getClickedBlock();
		  	
		  		
		if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
		  if (Tag.SIGNS.isTagged(block.getType())) { 
			  
			  Sign sign = (Sign) block.getState();
			  
			  Set<String> allPublicWarps = SimpleWarpMain.listPublicLocations.keySet();
			  Set<String> allPrivateWarps = SimpleWarpMain.listPrivateLocations.keySet();
			  
			  
			  List<String> allPublicWarpsList = new ArrayList<>(allPublicWarps);
			  List<String> allPrivateWarpsList = new ArrayList<>(allPrivateWarps);
			  
			  

			  
			allPublicWarpsList.addAll(allPrivateWarpsList);
			
			for (String next : allPublicWarpsList) {
				if ((sign.getLines()[0].equals("SimpleWarpTP") && (sign.getLines()[1].equals(next)))) {
					if (SimpleWarpMain.listPublicLocations.containsKey(next)) {
						player.teleport((Location) SimpleWarpMain.listPublicLocations.get(next));
					}
					
					else if (SimpleWarpMain.listPrivateLocations.containsKey(next)) {
						player.teleport((Location) SimpleWarpMain.listPrivateLocations.get(next));
					}
					
					
				
				}	 
			
				  }
				
				
			}
		  
		  
		 
	}
	}
	else {
		event.getPlayer().sendMessage(ChatColor.RED + "You do not have permission to use this warp!");
	}
	 
}
		}
				

