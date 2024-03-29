package de.hype.perms;

import de.hype.perms.commands.RangCommand;
import de.hype.perms.listener.ConnectionListener;
import de.hype.perms.utils.MySQL;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

public class HypePermsBungee extends Plugin {

    private static HypePermsBungee instance;
    private MySQL mySQL;

    @Override
    public void onEnable() {
        instance = this;

        mySQL = new MySQL("127.0.0.1", "root", "", "test");

        ProxyServer.getInstance().getPluginManager().registerListener(this, new ConnectionListener());

        ProxyServer.getInstance().getPluginManager().registerCommand(this, new RangCommand("rang"));
    }

    @Override
    public void onDisable() {

    }

    public static HypePermsBungee getInstance() {
        return instance;
    }

    public String getPrefix() {
        return "§cHypePerms §8| §7";
    }

    public MySQL getMySQL() {
        return mySQL;
    }
}
