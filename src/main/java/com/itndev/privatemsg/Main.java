package com.itndev.privatemsg;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.event.PreLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

public class Main extends Plugin {
    private static Map<UUID, UUID> conversations = new HashMap<UUID, UUID>();
    public static Map<UUID, ArrayList<String>> ignore = new HashMap<>();
    public static Map<UUID, Boolean> staffspytoggle = new HashMap<>();


    public static Main instance;

    public static Main getInstance() {
        return instance;
    }

    public static ProxiedPlayer getConversation(ProxiedPlayer paramProxiedPlayer) {
        return !conversations.containsKey(paramProxiedPlayer.getUniqueId()) ? null : ProxyServer.getInstance().getPlayer(conversations.get(paramProxiedPlayer.getUniqueId()));
    }

    public static void msg(ProxiedPlayer paramProxiedPlayer1, ProxiedPlayer paramProxiedPlayer2, String paramString) {
        paramProxiedPlayer2.sendMessage((BaseComponent)new TextComponent(ChatColor.translateAlternateColorCodes('&' ,"&f&l[&a&r귓말 받음&f&l] &r&f나 &7<- &r&f" + paramProxiedPlayer1.getDisplayName() + " &8:&7 " + paramString)));
        paramProxiedPlayer1.sendMessage((BaseComponent)new TextComponent(ChatColor.translateAlternateColorCodes('&' ,"&f&l[&c&r귓말 보냄&f&l] &r&f" + paramProxiedPlayer2.getDisplayName() + " &7<- &f나 &8:&7 " + paramString)));
        utils.staffspy("&e&l[&7귓말 감시&e&l] &r&f" + paramProxiedPlayer1.getDisplayName() + " &7-> &f" + paramProxiedPlayer2.getDisplayName() + " &8:: &r&7( " + paramString + "&r&7)");
        conversations.put(paramProxiedPlayer1.getUniqueId(), paramProxiedPlayer2.getUniqueId());
        conversations.put(paramProxiedPlayer2.getUniqueId(), paramProxiedPlayer1.getUniqueId());
    }

    public static ArrayList<String> DisabledPlugins = new ArrayList<>();

    private void addDisabled() {
        DisabledPlugins.add("BungeeTabListPlus");
        DisabledPlugins.add("CoreAPI");
        DisabledPlugins.add("CustomProtocolSettings");
        DisabledPlugins.add("LuckPerms");
        DisabledPlugins.add("RedirectPlus");
        DisabledPlugins.add("SecuredNetwork");
        DisabledPlugins.add("WarpSystem");
    }

    public void onEnable() {
        instance = this;
        getProxy().getPluginManager().registerListener(this, new listener());
        addDisabled();
        if(LoadYaml("privatemsg_ignore") != null) {
            ignore = (Map<UUID, ArrayList<String>>) LoadYaml("privatemsg_ignore");
        }
        getProxy().getPluginManager().registerCommand(this, (Command)new ReplyCommand());
        getProxy().getPluginManager().registerCommand(this, (Command)new Messages());
        getProxy().getPluginManager().registerCommand(this, (Command)new endcommand());
        getProxy().getPluginManager().registerCommand(this, (Command)new staffspy());
        getProxy().getPluginManager().registerCommand(this, (Command)new ignoreaddremove());
        getProxy().getPluginManager().registerCommand(this, (Command)new FakeCommand());
        new Thread(() -> {
            try {
                Thread.sleep(10000);
                System.out.println("[TABLISTBLOCKER] UNREGISTERING COMMANDS");
                for(String PluginName : DisabledPlugins) {
                    getProxy().getPluginManager().unregisterCommands(getProxy().getPluginManager().getPlugin(PluginName));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while(true) {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                DumpYaml(ignore, "privatemsg_ignore");
            }
        }).start();
    }
    public void onDisable() {
        DumpYaml(ignore, "privatemsg_ignore");
    }

    public static Object LoadYaml(String filename) {
        try {
            if(!Main.getInstance().getDataFolder().isDirectory()) {
                Main.getInstance().getDataFolder().mkdir();
            }
            Yaml yaml = new Yaml();
            FileReader reader = new FileReader(java.nio.file.Paths.get(Main.getInstance().getDataFolder().getAbsolutePath(), filename + ".yml").toAbsolutePath().toString());
            return yaml.load(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("[ERROR] Cant Load Data");
        return null;
    }

    public static void DumpYaml(Object data, String filename) {
        try {
            if(!Main.getInstance().getDataFolder().isDirectory()) {
                Main.getInstance().getDataFolder().mkdir();
            }
            Yaml yaml = new Yaml(new Constructor(data.getClass()));
            FileWriter writer;
            //writer = new FileWriter(filename + ".yml");
            writer = new FileWriter(java.nio.file.Paths.get(Main.getInstance().getDataFolder().getAbsolutePath(), filename + ".yml").toAbsolutePath().toString());
            yaml.dump(data, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}