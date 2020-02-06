package me.com.warps.Warp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.com.warps.Main;
import me.com.warps.mysql.MySQL;

public class Warp {
	
	private Main plugin = null;
	
	public Warp(Main p) {
		this.plugin = p;
	}
	
	public Warp define() {
		java.sql.PreparedStatement st = null;
		java.sql.Connection conection = null;
		ResultSet rs = null;
		try {
			conection = new MySQL().pegaConexao();
			st = conection.prepareStatement("SELECT Nome FROM Warps");
			rs = st.executeQuery();
			while(rs.next()) {
				plugin.warp_list.put(rs.getString("Nome"), getWarp(rs.getString("Nome")));
					
				}
			}catch (SQLException ex) {
				java.util.logging.Logger.getLogger(Warp.class.getName()).log(Level.SEVERE, null, ex);
			
		}finally {
			MySQL.fechaConexao(conection, st , rs);
		}
		return this;
	}
	
	public boolean setWarp(String nome, Player p, ItemStack item) {
        java.sql.PreparedStatement st = null;
        java.sql.Connection connection = null;
        try {
            if (!hasWarp(nome)) {
                connection = new MySQL().pegaConexao();
                st = connection.prepareStatement("INSERT INTO Warps VALUES("
                        + "'" + nome + "', "
                        + "'" + p.getWorld().getName() + "', "
                        + "'" + p.getLocation().getX() + "', "
                        + "'" + p.getLocation().getY() + "', "
                        + "'" + p.getLocation().getZ() + "', "
                        + "'" + p.getLocation().getYaw() + "', "
                        + "'" + p.getLocation().getPitch() + "',"
                        + "'1')");
                st.execute();
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MySQL.fechaConexao(connection, st);
        }
        return false;
    }
	public boolean delWarp(String nome, Player p, ItemStack item) {
        java.sql.PreparedStatement st = null;
        java.sql.Connection connection = null;
        try {
            if (!hasWarp(nome)) {
                connection = new MySQL().pegaConexao();
                st = connection.prepareStatement("DELETE FROM Warps VALUES("
                        + "'" + nome + "', "
                        + "'" + p.getWorld().getName() + "', "
                        + "'" + p.getLocation().getX() + "', "
                        + "'" + p.getLocation().getY() + "', "
                        + "'" + p.getLocation().getZ() + "', "
                        + "'" + p.getLocation().getYaw() + "', "
                        + "'" + p.getLocation().getPitch() + "',"
                        + "'1')");
                st.execute();
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MySQL.fechaConexao(connection, st);
        }
        return false;
    }


	public Location getWarp(String nome) {
		java.sql.PreparedStatement st = null;
		java.sql.Connection connection = null;
		ResultSet rs = null;
		try {
			connection = new MySQL().pegaConexao();
			st = connection.prepareStatement("SELECT world,x,y,z,yaw,pitch FROM Warps WHERE Nome = (?)");
			st.setString(1, nome);
			rs = st.executeQuery();
			while(rs.next()) {
				Location loc = new Location(Bukkit.getWorld(
                        rs.getString("world")),
                        rs.getDouble("x"),
                        rs.getDouble("y"),
                        rs.getDouble("z"),
                        rs.getFloat("yaw"),
                        rs.getFloat("pitch"));
                return loc;

			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			MySQL.fechaConexao(connection, st ,rs);
		}
		return null;
	}
	public boolean hasWarp(String nome) {
        java.sql.PreparedStatement st = null;
        java.sql.Connection connection = null;
        ResultSet rs = null;
        try {
            connection = new MySQL().pegaConexao();
            st = connection.prepareStatement("SELECT Nome FROM Warps WHERE Nome = (?)");
            //
            st.setString(1, nome);
            rs = st.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MySQL.fechaConexao(connection, st, rs);
        }
        return false;
    }

	
	public boolean editWarp(String nome, Player p) {
        java.sql.PreparedStatement st = null;
        java.sql.Connection connection = null;
        try {
            if (hasWarp(nome)) {
                connection = new MySQL().pegaConexao();
                st = connection.prepareStatement("UPDATE Warps SET world = (?),x = (?),y = (?),z = (?),yaw = (?),pitch = (?) WHERE Nome = (?)");
                st.setString(1, p.getWorld().getName());
                st.setDouble(2, p.getLocation().getX());
                st.setDouble(3, p.getLocation().getY());
                st.setDouble(4, p.getLocation().getZ());
                st.setFloat(5, p.getLocation().getYaw());
                st.setFloat(6, p.getLocation().getPitch());
                st.setString(7, nome);
                st.execute();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MySQL.fechaConexao(connection, st);
        }
        return false;
    }
	public int getItemId(String nome) {
        java.sql.PreparedStatement st = null;
        java.sql.Connection connection = null;
        ResultSet rs = null;
        try {
            connection = new MySQL().pegaConexao();
            st = connection.prepareStatement("SELECT item FROM Warps WHERE Nome = (?)");
            //
            st.setString(1, nome);
            rs = st.executeQuery();
            while (rs.next()) {
                int i = rs.getInt("item");
                return i;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MySQL.fechaConexao(connection, st, rs);
        }
        return 1;
    }


}
