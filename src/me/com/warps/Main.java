package me.com.warps;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import me.com.warps.Warp.Warp;
import me.com.warps.commands.DelWarp;
import me.com.warps.commands.SetWarp;
import me.com.warps.commands.Warps;
import me.com.warps.mysql.MySQL;


public class Main extends JavaPlugin{
	
	public static String Senha = null;//Senha MySQL
    public static String Usuario = null;//Usuario MySQL
    public static String Banco = null;//Base de Dados MySQL
    public static String Host = null;//Ip MySQL
    public static String Port = null;//Ip MySQL
    public static MySQL mysql = null;//Return MYSQL
    public static HashMap<String, Location> warp_list = new HashMap<>();
	private static Main instance;
	public static Warp manager;
	public static Main getInstance() {
		return instance;
	}
	
	@Override
	public void onEnable() {
		
		instance = this;
		
		Senha = getConfig().getString("MySQL.Senha");
		Usuario = getConfig().getString("MySQL.User");
		Banco = getConfig().getString("MySQL.Database");
		Host = getConfig().getString("MySQL.Host");
		Port = getConfig().getString("MySQL.Porta");
		new MySQL().initBanco();
		
		getCommand("warp").setExecutor(new Warps());
		getCommand("delwarp").setExecutor(new DelWarp());
		getCommand("setwarp").setExecutor(new SetWarp());
	
	}
	
	@Override
	public void onDisable() {
	}
	
}
