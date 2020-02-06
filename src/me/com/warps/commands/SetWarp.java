package me.com.warps.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.com.warps.Main;

public class SetWarp implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		
		if(sender instanceof Player) {
			Player p = (Player)sender;
			if(p.hasPermission("warp.admin")) {
				switch (args.length) {
				case 0:{
					p.sendMessage("ßcUtilize: /setwarp <warp>");
					break;
					}
				case 1: {
					if(!Main.getInstance().warp_list.containsKey(args[0])) {
						 ItemStack item = new ItemStack(Material.getMaterial(1));
						 if(Main.getInstance().manager.setWarp(args[0], p, item)) {
							 p.sendMessage("ßeYAY! Warp setada com sucesso");
							 Main.getInstance().warp_list.put(args[0], p.getLocation());
							 return true;
						 	}
						}else if (Main.getInstance().manager.editWarp(args[0], p)) {
							if(Main.getInstance().warp_list.containsKey(args[0])) {
								Main.warp_list.remove(args[0]);
							}
							Main.warp_list.put(args[0], p.getLocation());
							p.sendMessage("ßeYAY! Warp editada com sucesso");
							return true;
						}
					break;
					}
				default:
                    p.sendMessage("ßcUtilize: /setwarp (nome da warp)");
                    return true;
				}
			}else {
				p.sendMessage("ßcSem permiss„o");
				return true;
			}
		}else {
			sender.sendMessage("ßcVoc  n„o e um jogador");
		}
		
		return false;
	}
	
	

}
