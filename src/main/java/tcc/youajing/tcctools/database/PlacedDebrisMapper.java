package tcc.youajing.tcctools.database;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;
import tcc.youajing.tcctools.entity.MCPlayer;
import tcc.youajing.tcctools.entity.MyLocation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.UUID;

import static tcc.youajing.tcctools.ObjectPool.gson;

public class PlacedDebrisMapper {
    public PlacedDebrisMapper() {
        PreparedStatement stmt = null;
        Connection conn = null;
        ResultSet rs = null;
        try {
            conn = MysqlConfig.connect();
            stmt = conn.prepareStatement("CREATE TABLE IF NOT EXISTS placed_debris (" +
                    "location Varchar(128)," +
                    "UNIQUE KEY (location)" +
                    ") ENGINE=InnoDB CHARACTER SET=utf8;");
            stmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "PlacedDebrisMapper.PlacedDebrisMapper:" + e.getMessage());
        } finally {
            try {
                if(stmt != null) stmt.close();
                if(rs != null) rs.close();
                if(conn != null) conn.close();
            } catch (SQLException e) {
            }
        }
    }

    public boolean exists(@NotNull Location location) {
        MyLocation myLocation = new MyLocation(location);

        PreparedStatement stmt = null;
        Connection conn = null;
        ResultSet rs = null;
        boolean res = false;
        try {
            conn = MysqlConfig.connect();
            conn.commit();
            stmt = conn.prepareStatement("SELECT * FROM placed_debris WHERE location=?");
            stmt.setString(1, gson.toJson(myLocation));
            rs = stmt.executeQuery();
            if(rs.next()) {
                res =  true;
            }
        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "PlacedDebrisMapper.exists:" + e.getMessage());
        } finally {
            try {
                if(stmt != null) stmt.close();
                if(rs != null) rs.close();
                if(conn != null) conn.close();
            } catch (SQLException e) {
            }
        }

        return res;
    }

    public void insert(@NotNull Location location) {
        // uuid已存在
        if (exists(location)) {
            // update
            return;
        }

        MyLocation myLocation = new MyLocation(location);
        PreparedStatement stmt = null;
        Connection conn = null;
        ResultSet rs = null;
        try {
            conn = MysqlConfig.connect();
            stmt = conn.prepareStatement("INSERT INTO placed_debris VALUES (?)");
            stmt.setString(1, gson.toJson(myLocation));
            stmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "PlacedDebrisMapper.insert:" + e.getMessage());
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (rs != null) rs.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
            }
        }
    }

    public void remove(@NotNull Location location) {
        MyLocation myLocation = new MyLocation(location);

        PreparedStatement stmt = null;
        Connection conn = null;
        ResultSet rs = null;
        try {
            conn = MysqlConfig.connect();
            stmt = conn.prepareStatement("DELETE FROM placed_debris WHERE location=?");
            stmt.setString(1, gson.toJson(myLocation));
            stmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "PlacedDebrisMapper.remove:" + e.getMessage());
        } finally {
            try {
                if(stmt != null) stmt.close();
                if(rs != null) rs.close();
                if(conn != null) conn.close();
            } catch (SQLException e) {
            }
        }
    }
}
