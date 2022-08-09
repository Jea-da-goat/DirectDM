package com.itndev.privatemsg;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class staffspy extends Command {
    public staffspy() {
        super("스파이토글", "itndev.staffspy", new String[] { "스파이토글", "socialspy" });
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer proxiedPlayer = (ProxiedPlayer) sender;
            if(!proxiedPlayer.hasPermission("itndev.staffspy")) {
                proxiedPlayer.sendMessage((BaseComponent)new TextComponent(ChatColor.translateAlternateColorCodes('&', "&f&l[&r&c귓말&f&l] &f권한이 없습니다")));
                return;
            }
            if(Main.staffspytoggle.containsKey(proxiedPlayer.getUniqueId())) {
                Boolean iftoggled = Main.staffspytoggle.get(proxiedPlayer.getUniqueId());
                if(iftoggled) {
                    proxiedPlayer.sendMessage((BaseComponent)new TextComponent(ChatColor.translateAlternateColorCodes('&', "&f&l[&r&c귓말&f&l] &f귓말 스파이 기능을 껏습니다")));
                    Main.staffspytoggle.put(proxiedPlayer.getUniqueId(), false);
                } else {
                    proxiedPlayer.sendMessage((BaseComponent)new TextComponent(ChatColor.translateAlternateColorCodes('&', "&f&l[&r&c귓말&f&l] &f귓말 스파이 기능을 켰습니다")));
                    Main.staffspytoggle.put(proxiedPlayer.getUniqueId(), true);
                }
            } else {
                proxiedPlayer.sendMessage((BaseComponent)new TextComponent(ChatColor.translateAlternateColorCodes('&', "&f&l[&r&c귓말&f&l] &f귓말 스파이 기능을 켰습니다")));
                Main.staffspytoggle.put(proxiedPlayer.getUniqueId(), true);
            }
        }
    }
}
