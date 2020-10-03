package simplewarp.plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;






public class SimpleWarpMain extends JavaPlugin {
	
	static String githubLink = "https://github.com/RidayB/SimpleWarp";
	static String warpName;
	static int warpX;
	static int warpY;
	static int warpZ;
	static Map<String, Object> listPublicLocations = new HashMap<>();
	static Map<String, Object> listPrivateLocations= new HashMap<>();
	Player player;

	
	public void onEnable() {
		PluginManager pm = Bukkit.getServer().getPluginManager();
		SimpleWarpListener swl = new SimpleWarpListener(this);
		pm.registerEvents(swl, this);
		
		Map<String, Object> listPublicLocations = getConfig().getConfigurationSection("PublicWarpHash").getValues(false);
		Map<String, Object> listPrivateLocations = getConfig().getConfigurationSection("PrivateWarpHash").getValues(false);
		SimpleWarpMain.listPublicLocations = listPublicLocations;
		SimpleWarpMain.listPrivateLocations = listPrivateLocations;
		

	}
	
	public void onDisable() {
		getConfig().createSection("PublicWarpHash", listPublicLocations);
		saveConfig();
		getConfig().createSection("PrivateWarpHash", listPrivateLocations);
		saveConfig();
	}
	
	public static boolean isNumeric(String str) { 
		  try {  
		    Double.parseDouble(str);  
		    return true;
		  } 
		  catch(NumberFormatException e){  
		    return false;  
		  } 
	}

	
	


	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		
		
		try {player = (Player) sender;
		}
		catch (Exception e) {
			sender.sendMessage(ChatColor.RED + "This command can only be run by ingame players!");
			return true;
		}
		
		if (sender instanceof Player) {
				String lowerCmd = cmd.getName().toLowerCase();
		
			switch (lowerCmd) {
			
					case "warp":
						
						
						if (args.length == 1 || args.length == 2 ||  args.length == 5) {
							switch (args[0]) {
								
							case "help":
								
								if (player.hasPermission("simplewarp.help")) {
									if (args.length == 1) {
										sender.sendMessage(ChatColor.AQUA + "Link to github with readme file:");
										sender.sendMessage(ChatColor.AQUA + githubLink + ".");
										return true;
									}
									
									else {
										sender.sendMessage(ChatColor.RED + "This command takes in 1 argument only.");
										return true;
									}
								}
								
								else {
									sender.sendMessage(ChatColor.RED + "You do not have permission to execute this command!");
									return true;
								}
								
						case "addpublic":
							if (player.hasPermission("simplewarp.createpublicwarp") || player.hasPermission("simplewarp.createallwarps")) {
								if (args.length == 5) {
									String warpName = ((args[1]));
									SimpleWarpMain.warpName = warpName;
									
							
									if (isNumeric(args[2]) == true) {
										int warpX = Integer.parseInt(args[2]);
										SimpleWarpMain.warpX = warpX;
									}
									else {
										sender.sendMessage(ChatColor.RED + "The x value you entered was non numeric");
										return true;
									}
										
									if (isNumeric(args[3]) == true) {
										int warpY = Integer.parseInt(args[3]);
										SimpleWarpMain.warpY = warpY;
									}
									else {
										sender.sendMessage(ChatColor.RED + "The y value you entered was non numeric");
										return true;
									}
									
									if (isNumeric(args[4]) == true) {
										int warpZ = Integer.parseInt(args[4]);
										SimpleWarpMain.warpZ = warpZ;
										
									}
									else {
										sender.sendMessage(ChatColor.RED + "The z value you entered was non numeric");
										return true;
									}
									
									if (warpName.equalsIgnoreCase("help") || (warpName.equalsIgnoreCase("createpublic") || (warpName.equalsIgnoreCase("createprivate") || (warpName.equalsIgnoreCase("setpublic") || (warpName.equalsIgnoreCase("setprivate") || (warpName.equalsIgnoreCase("list") || (warpName.equalsIgnoreCase("remove") || (warpName.equalsIgnoreCase("reset"))))))))) {
										// All the command arguments that will mess up the algorithm
										sender.sendMessage(ChatColor.RED + "Unfortunately, this cannot be used as a warp name as it is used as a command.");
										return true;
									}
										
									Location warp = new Location(player.getWorld(), warpX, warpY, warpZ);
									
										
									if (listPublicLocations.containsKey(warpName) || listPrivateLocations.containsKey(warpName)) {
										sender.sendMessage(ChatColor.RED + "This name has already been used for a warp.");
										return true;	
									}
									
									
									else {
										listPublicLocations.put(warpName, warp);
										sender.sendMessage(ChatColor.AQUA + "Successfully added " + warpName + " as a public warp.");
										return true;
									}
								}
								
								else {
									sender.sendMessage(ChatColor.RED + "This command uses 5 arguments!");
									return true;
								}
							}
							
							else {
								sender.sendMessage(ChatColor.RED + "You do not have permission to execute this command!");
								return true;
							}
								
							
						case "createpublic":
							if (player.hasPermission("simplewarp.createpublicwarp") || player.hasPermission("simplewarp.createallwarps")) {
								if (args.length == 5) {
									String warpName = ((args[1]));
									SimpleWarpMain.warpName = warpName;
									
							
									if (isNumeric(args[2]) == true) {
										int warpX = Integer.parseInt(args[2]);
										SimpleWarpMain.warpX = warpX;
									}
									else {
										sender.sendMessage(ChatColor.RED + "The x value you entered was non numeric");
										return true;
									}
										
									if (isNumeric(args[3]) == true) {
										int warpY = Integer.parseInt(args[3]);
										SimpleWarpMain.warpY = warpY;
									}
									else {
										sender.sendMessage(ChatColor.RED + "The y value you entered was non numeric");
										return true;
									}
									
									if (isNumeric(args[4]) == true) {
										int warpZ = Integer.parseInt(args[4]);
										SimpleWarpMain.warpZ = warpZ;
										
									}
									else {
										sender.sendMessage(ChatColor.RED + "The z value you entered was non numeric");
										return true;
									}
									
									if (warpName.equalsIgnoreCase("help") || (warpName.equalsIgnoreCase("createpublic") || (warpName.equalsIgnoreCase("createprivate") || (warpName.equalsIgnoreCase("setpublic") || (warpName.equalsIgnoreCase("setprivate") || (warpName.equalsIgnoreCase("list") || (warpName.equalsIgnoreCase("remove") || (warpName.equalsIgnoreCase("reset"))))))))) {
										// All the command arguments that will mess up the algorithm
										sender.sendMessage(ChatColor.RED + "Unfortunately, this cannot be used as a warp name as it is used as a command.");
										return true;
									}
										
									Location warp = new Location(player.getWorld(), warpX, warpY, warpZ);
									
										
									if (listPublicLocations.containsKey(warpName) || listPrivateLocations.containsKey(warpName)) {
										sender.sendMessage(ChatColor.RED + "This name has already been used for a warp.");
										return true;	
									}
									
									
									else {
										listPublicLocations.put(warpName, warp);
										sender.sendMessage(ChatColor.AQUA + "Successfully added " + warpName + " as a public warp.");
										return true;
									}
								}
								
								else {
									sender.sendMessage(ChatColor.RED + "This command uses 5 arguments!");
									return true;
								}
							}
							
							else {
								sender.sendMessage(ChatColor.RED + "You do not have permission to execute this command!");
								return true;
							}
						
							
							
							case "createprivate":
								if (player.hasPermission("simplewarp.createprivatewarp") || player.hasPermission("simplewarp.createallwarps")) {
									if (args.length == 5) {
										String warpName = ((args[1]));
										SimpleWarpMain.warpName = warpName;
										
								
										if (isNumeric(args[2]) == true) {
											int warpX = Integer.parseInt(args[2]);
											SimpleWarpMain.warpX = warpX;
										}
										else {
											sender.sendMessage(ChatColor.RED + "The x value you entered was non numeric");
											return true;
										}
											
										if (isNumeric(args[2]) == true) {
											int warpY = Integer.parseInt(args[3]);
											SimpleWarpMain.warpY = warpY;
										}
										else {
											sender.sendMessage(ChatColor.RED + "The y value you entered was non numeric");
											return true;
										}
										
										if (isNumeric(args[2]) == true) {
											int warpZ = Integer.parseInt(args[4]);
											SimpleWarpMain.warpZ = warpZ;
										}
										else {
											sender.sendMessage(ChatColor.RED + "The z value you entered was non numeric");
											return true;
										}
										
										if (warpName.equalsIgnoreCase("help") || (warpName.equalsIgnoreCase("createpublic") || (warpName.equalsIgnoreCase("createprivate") || (warpName.equalsIgnoreCase("setpublic") || (warpName.equalsIgnoreCase("setprivate") || (warpName.equalsIgnoreCase("list") || (warpName.equalsIgnoreCase("remove") || (warpName.equalsIgnoreCase("reset"))))))))) {
											// All the command arguments that will mess up the algorithm
											sender.sendMessage(ChatColor.RED + "Unfortunately, this cannot be used as a warp name as it is used as a command.");
											return true;
										}
											
										Location warp = new Location(player.getWorld(), warpX, warpY, warpZ);
										
											
										if (listPublicLocations.containsKey(warpName) || listPrivateLocations.containsKey(warpName)) {
											sender.sendMessage(ChatColor.RED + "This name has already been used for a warp.");
											return true;
										}
										
										else {
											listPrivateLocations.put(warpName, warp);
											sender.sendMessage(ChatColor.AQUA + "Successfully added " + warpName + " as a private warp.");
											return true;
										}
									}
									
									else {
										sender.sendMessage(ChatColor.RED + "This command uses 5 arguments!");
										return true;
									}	
									
								}
								
								else {
									player.sendMessage(ChatColor.RED + "You do not have permission to execute this command!");
									return true;
								}	
								
							
							
								
							case "addprivate":
							if (player.hasPermission("simplewarp.createprivatewarp") || player.hasPermission("simplewarp.createallwarps")) {
								if (args.length == 5) {
									String warpName = ((args[1]));
									SimpleWarpMain.warpName = warpName;
									
							
									if (isNumeric(args[2]) == true) {
										int warpX = Integer.parseInt(args[2]);
										SimpleWarpMain.warpX = warpX;
									}
									else {
										sender.sendMessage(ChatColor.RED + "The x value you entered was non numeric");
										return true;
									}
										
									if (isNumeric(args[2]) == true) {
										int warpY = Integer.parseInt(args[3]);
										SimpleWarpMain.warpY = warpY;
									}
									else {
										sender.sendMessage(ChatColor.RED + "The y value you entered was non numeric");
										return true;
									}
									
									if (isNumeric(args[2]) == true) {
										int warpZ = Integer.parseInt(args[4]);
										SimpleWarpMain.warpZ = warpZ;
									}
									else {
										sender.sendMessage(ChatColor.RED + "The z value you entered was non numeric");
										return true;
									}
									
									if (warpName.equalsIgnoreCase("help") || (warpName.equalsIgnoreCase("createpublic") || (warpName.equalsIgnoreCase("createprivate") || (warpName.equalsIgnoreCase("setpublic") || (warpName.equalsIgnoreCase("setprivate") || (warpName.equalsIgnoreCase("list") || (warpName.equalsIgnoreCase("remove") || (warpName.equalsIgnoreCase("reset"))))))))) {
										// All the command arguments that will mess up the algorithm
										sender.sendMessage(ChatColor.RED + "Unfortunately, this cannot be used as a warp name as it is used as a command.");
										return true;
									}
										
									Location warp = new Location(player.getWorld(), warpX, warpY, warpZ);
									
										
									if (listPublicLocations.containsKey(warpName) || listPrivateLocations.containsKey(warpName)) {
										sender.sendMessage(ChatColor.RED + "This name has already been used for a warp.");
										return true;
									}
									
									else {
										listPrivateLocations.put(warpName, warp);
										sender.sendMessage(ChatColor.AQUA + "Successfully added " + warpName + " as a private warp.");
										return true;
									}
								}
								
								else {
									sender.sendMessage(ChatColor.RED + "This command uses 5 arguments!");
									return true;
								}	
								
							}
							
							else {
								player.sendMessage(ChatColor.RED + "You do not have permission to execute this command!");
								return true;
							}	
							
							
							case "listall":
								if (player.hasPermission("simplewarp.listallwarps")) {
									if (args.length == 1) {
										
										
										
										Set<String> allPublicWarps = listPublicLocations.keySet();
										ArrayList<String> listPublicWarpNames = new ArrayList<String>();
										Iterator<String> warpItPublic = allPublicWarps.iterator();
										while (warpItPublic.hasNext()) {
											listPublicWarpNames.add(warpItPublic.next());
										}
										
										Set<String> allPrivateWarps = listPrivateLocations.keySet();
										ArrayList<String> listPrivateWarpNames = new ArrayList<String>();
										Iterator<String> warpItPrivate = allPrivateWarps.iterator();
										
										if (allPublicWarps.toArray().length == 0 && allPrivateWarps.toArray().length == 0) {
											sender.sendMessage(ChatColor.RED + "There are no warps set yet. Do /warp help to learn how to add some.");
											return true;
										}
										else {
											while (warpItPrivate.hasNext()) {
												listPrivateWarpNames.add(warpItPrivate.next());
											}
											
											for (String warps : listPublicWarpNames) {
												sender.sendMessage(ChatColor.AQUA + "Public: " + warps);
											}
											for (String warps : listPrivateWarpNames) {
												sender.sendMessage(ChatColor.BOLD + "" + ChatColor.DARK_AQUA + "Private: " + warps);
											}
											return true;
										}
										
										
									}
									else {
										sender.sendMessage(ChatColor.RED + "This command takes 1 argument.");
										return true;
									}
								}
								
								
								else {
									player.sendMessage(ChatColor.RED + "You do not have permission to execute this command!");
									return true;
								}			
								
							case "listpublic":
								if (player.hasPermission("simplewarp.listpublicwarps")) {
									if (args.length == 1) {
										
										
										
										Set<String> allPublicWarps = listPublicLocations.keySet();
										ArrayList<String> listPublicWarpNames = new ArrayList<String>();
										Iterator<String> warpItPublic = allPublicWarps.iterator();
										while (warpItPublic.hasNext()) {
											listPublicWarpNames.add(warpItPublic.next());
										}
										
										
										if (allPublicWarps.toArray().length == 0) {
											sender.sendMessage(ChatColor.RED + "There are no public warps set yet. Do /warp help to learn how to add some.");
											return true;
										}
										else {
					
											
											for (String warps : listPublicWarpNames) {
												sender.sendMessage(ChatColor.AQUA + "Public: " + warps);
											}
											return true;
										}
										
										
									}
									else {
										sender.sendMessage(ChatColor.RED + "This command takes 1 argument.");
										return true;
									}
								}
								
								
								else {
									player.sendMessage(ChatColor.RED + "You do not have permission to execute this command!");
									return true;
								}			
									
							
							case "listprivate":
								if (player.hasPermission("simplewarp.listprivatewarps")) {
									if (args.length == 1) {
										
										
										
										Set<String> allPrivateWarps = listPrivateLocations.keySet();
										ArrayList<String> listPrivateWarpNames = new ArrayList<String>();
										Iterator<String> warpItPrivate = allPrivateWarps.iterator();
										while (warpItPrivate.hasNext()) {
											listPrivateWarpNames.add(warpItPrivate.next());
										}
										
										
										if (allPrivateWarps.toArray().length == 0) {
											sender.sendMessage(ChatColor.RED + "There are no private warps set yet. Do /warp help to learn how to add some.");
											return true;
										}
										else {
					
											
											for (String warps : listPrivateWarpNames) {
												sender.sendMessage(ChatColor.BOLD + "" + ChatColor.DARK_AQUA + "Private: " + warps);
											}
											return true;
										}
										
										
									}
									else {
										sender.sendMessage(ChatColor.RED + "This command takes 1 argument.");
										return true;
									}
								}
								
								
								else {
									player.sendMessage(ChatColor.RED + "You do not have permission to execute this command!");
									return true;
								}			
										
							case "remove":
								if (player.hasPermission("simplewarp.remove")) {
									if (args.length == 2) {
										String warpToRemove = (args[1]);
										if (listPublicLocations.containsKey(warpToRemove) || listPrivateLocations.containsKey(warpToRemove)) {
											listPublicLocations.remove(warpToRemove);
											listPrivateLocations.remove(warpToRemove);
											sender.sendMessage(ChatColor.AQUA + "Sucesfully removed " + warpToRemove + " from the warps.");
											return true;
										}
										
										else {
											sender.sendMessage(ChatColor.RED + "That is not a warp. Do /warp list to see the warps.");
											return true;
										}
										
									}
								
									
									else {
										sender.sendMessage(ChatColor.RED + "This command takes 2 arguments!");
										return true;
									}
								}
								
								else {
									player.sendMessage(ChatColor.RED + "You do not have permission to execute this command!");
									return true;
								}						
								
								
							
							case "reset":
								if (player.hasPermission("simplewarp.reset")) {
									if (args.length == 1 || args.length == 2) {
										Set<String> allPublicWarps = listPublicLocations.keySet();
										Set<String> allPrivateWarps = listPrivateLocations.keySet();
										if (allPublicWarps.toArray().length == 0 && allPrivateWarps.toArray().length == 0) {
											sender.sendMessage(ChatColor.RED + "There are no warps to reset yet. Do /warp help to learn how to add some.");
											return true;
										}
										else {
											if (args.length == 1) {
												sender.sendMessage(ChatColor.AQUA + "Are you sure you want to reset? This cannot be undone. Type /warp reset confirm to confirm you want to reset it.");
												return true;
											}
											
											else if (args.length == 2 && (args[1]).equalsIgnoreCase("confirm")) {
												listPublicLocations.clear();
												listPrivateLocations.clear();
												sender.sendMessage(ChatColor.AQUA + "Successfully reset the warps.");
												return true;
											}
							
							
											else {
												sender.sendMessage(ChatColor.RED + "This command can either be used as /reset or /reset confirm.");
												return true;
									
											}
										}	
									}
										
									else {
										sender.sendMessage(ChatColor.RED + "This command uses 1 or 2 arguments!");
										return true;
									}
								}
								
								else {
									player.sendMessage(ChatColor.RED + "You do not have permission to execute this command!");
									return true;
								}
								
									
									
								
							default:
								if (player.hasPermission("simplewarp.warppublicwarp") || (player.hasPermission("simplewarp.warpallwarps")) || (player.hasPermission("simplewarp.warpprivatewarp"))) {
									if (args.length == 1) {
										
										String whereToWarp = (args[0]);
										if (listPublicLocations.containsKey(whereToWarp)) {
											if (player.hasPermission("simplewarp.warppublicwarp") || (player.hasPermission("simplewarp.warpallwarps"))) {
												player.teleport ((Location) listPublicLocations.get(whereToWarp));
												player.sendMessage(ChatColor.AQUA + "Sucesfully warped you to " + whereToWarp.toString() + ".");
												return true;
											}
											
											else {
												player.sendMessage(ChatColor.RED + "You do not have permission to execute this command.");
												return true;
											}

										}
										
										else if (listPrivateLocations.containsKey(whereToWarp)) {
											if (player.hasPermission("simplewarp.warppublicwarp") || (player.hasPermission("simplewarp.warpallwarps"))) {
												player.teleport((Location) listPrivateLocations.get(whereToWarp));
												player.sendMessage(ChatColor.AQUA + "Sucesfully warped you to " + whereToWarp.toString() + ".");
												return true;
											}
											
											else {

													player.sendMessage(ChatColor.RED + "You do not have permission to execute this command.");
													return true;

											}
											
										}
										
										else {
											player.sendMessage(ChatColor.RED + "Incorrect warp name. Double check what you entered.");
											return true;
										}
									
									}
									}
								
								else {

									player.sendMessage(ChatColor.RED + "You do not have permission to execute this command.");
									return true;

							}
								
								if (player.hasPermission("simplewarp.warpotherplayers")) {
									
									 if (args.length == 2){
										Player target = Bukkit.getServer().getPlayer(args[1]);
										
										if (target == null) {
											sender.sendMessage(ChatColor.RED + "That player does not exist or is not online!");
											return true;
										}
										
										else {
											String whereToWarp = (args[0]);
											if (listPublicLocations.containsKey(whereToWarp)) {
												
												target.teleport ((Location) listPublicLocations.get(whereToWarp));
												player.sendMessage("Sucesfully teleported " + target+ " to " + whereToWarp + ".");
												return true;
											}
											
											else if (listPrivateLocations.containsKey(whereToWarp)) {
												target.teleport((Location) listPrivateLocations.get(whereToWarp));
												player.sendMessage(ChatColor.AQUA + "Sucesfully teleported " + target.getName().toString() + " to " + whereToWarp + ".");
												return true;
											}
											
											else {
												player.sendMessage(ChatColor.RED + "Incorrect warp name. Double check what you entered.");
												return true;
											}
										}
										
										
									}
								}
								
								else {
									player.sendMessage(ChatColor.RED + "You do not have permission to execute this command!");
									return true;
								
						}}}
								else {
									sender.sendMessage(ChatColor.RED + "This command takes 1, 2, or 5 arguments!");
									return true;
								}
										
							
				
						}}
						
		
		return true;}}