package me.com.warps.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.com.warps.Main;

public class Warps implements CommandExecutor{
	
	private static Main instance;
	public static Main getInstance() {
		return instance;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player)sender;
				switch(args.length) {
				case 0:{
					p.sendMessage("§cUtilize: /warp <nome da warp>");
					break;
					}
				case 1: {
					if(Main.getInstance().warp_list.containsKey(args[0])) {
						Location loc = Main.getInstance().warp_list.get(args[0]);
						p.teleport(loc);
						p.sendMessage("");
						p.sendMessage("§eYAY! Você foi teleportado para a warp " + args[0] + " com sucesso");
						p.sendMessage("");
						}else {
							p.sendMessage("§cEsta warp não existe");
							return true;
						}
					break;
					}
				default:
					return true;
				}
			}else {
				sender.sendMessage("§cApenas jogadores");
			}
		return false;
	}

}
