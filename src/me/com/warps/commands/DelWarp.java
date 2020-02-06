package me.com.warps.commands;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.com.warps.Main;

public class DelWarp implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player)sender;
			if(p.hasPermission("warp.admin")) {
				switch(args.length) {
				case 0: {
					p.sendMessage("§cUtilize: /delwarp <warp>");
					break;
					}
				case 1: {
					 ItemStack item = new ItemStack(Material.getMaterial(1));
					if(!Main.getInstance().warp_list.containsKey(args[0])) {
						p.sendMessage("§cEsta warp não existe");
						return true;
						}else if (Main.getInstance().warp_list.containsKey(args[0])) {
							p.sendMessage("§eYAY! Warp deletada com sucesso");
							Main.getInstance().manager.delWarp(args[0], p, item);
							Main.getInstance().warp_list.remove(args[0]);
							break;
						}
					}
				default:
					p.sendMessage("§cUtilize: /delwarp <warp>");
				}
			}else {
				p.sendMessage("§cVocê não possui permissão para executar este comando");
				return true;
			}
		}else {
			sender.sendMessage("§cApenas in-game console, que pena.");
		}
		return false;
	}

}
