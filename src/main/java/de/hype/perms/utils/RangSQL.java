package de.hype.perms.utils;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.apache.tools.ant.taskdefs.SQLExec;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RangSQL {

    public static boolean playerExists(ProxiedPlayer player) {
        try {
            ResultSet resultSet = MySQL.getResult("SELECT * FROM rang WHERE UUID= '" + player.getUniqueId().toString() + "'");

            assert resultSet != null;
            if(resultSet.next()) {
                return resultSet.getString("UUID") != null;
            }
        } catch(SQLException ignored) {

        }
        return false;
    }

    public static void registerPlayer(ProxiedPlayer player) {
        if(!(playerExists(player))) {
            MySQL.update("INSERT INTO rang(UUID, Rang) VALUES ('" + player.getUniqueId().toString() + "', 'Spieler')");
        } else {
            ProxyServer.getInstance().getConsole().sendMessage("User existiert bereits");
        }
    }

    public static String getRangName(String uuid) {
        try {
            ResultSet result = MySQL.getResult("SELECT * FROM rang WHERE UUID= '" + uuid + "'");

            assert result != null;
            if (result.next()) {
                return result.getString("Rang");
            }
        } catch (SQLException ignored) {
            return "Spieler";
        }
        return "Spieler";
    }

    public static Rang getRang(String uuid) {
        try {
            ResultSet result = MySQL.getResult("SELECT * FROM rang WHERE UUID= '" + uuid + "'");

            assert result != null;
            if (result.next()) {
                return Rang.valueOf(result.getString("Rang"));
            }
        } catch (SQLException ignored) {
            return Rang.Spieler;
        }
        return Rang.Spieler;
    }

    public static Integer getRangId(String uuid) {
        try {
            ResultSet result = MySQL.getResult("SELECT * FROM rang WHERE UUID= '" + uuid + "'");

            assert result != null;
            if (result.next()) {
                return Rang.valueOf(result.getString("Rang")).getId();
            }
        } catch (SQLException | NullPointerException exception) {
            return Rang.Spieler.getId();
        }
        return Rang.Spieler.getId();
    }

    public static void removeOldRang(String uuid) {
        try {
            ResultSet result = MySQL.getResult("SELECT * FROM rang WHERE UUID= '" + uuid + "'");

            assert result != null;
            if (result.next()) {
                MySQL.update("UPDATE rang SET Rang= 'Spieler' WHERE UUID= '" + uuid + "'");
            }
        } catch (SQLException ignored) {

        }
    }
}