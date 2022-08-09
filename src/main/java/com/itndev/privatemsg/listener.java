package com.itndev.privatemsg;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.event.PreLoginEvent;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.event.TabCompleteEvent;
import net.md_5.bungee.api.event.TabCompleteResponseEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class listener implements Listener {

    public static String VERSION = "FelineCore 1.12.2";

    public static Integer SLOTAMOUNT = 420;

    public static Integer FAKEPLAYERS = 0;

    @EventHandler
    public void onConnect(PreLoginEvent e) {
        System.out.println("[LOG] CONNECTION VERIFIYING");
        int version = e.getConnection().getVersion();
        if(/*version != 340 && */version != 759 && version != 758 && version != 757) {
            e.setCancelReason((BaseComponent)new TextComponent(ChatColor.translateAlternateColorCodes('&', "&c&l1.12.2를 사용해 주세요")));
            e.setCancelled(true);
            return;
        }
    }
    @EventHandler(priority = 64)
    public void onProxyPing(ProxyPingEvent e) {
        ServerPing ping = e.getResponse();
        ServerPing.Players p = ping.getPlayers();
        ping.setPlayers(new ServerPing.Players(SLOTAMOUNT, p.getOnline() + FAKEPLAYERS, ping.getPlayers().getSample()));
        ServerPing.Protocol prot = new ServerPing.Protocol(VERSION, ping.getVersion().getProtocol());
        ping.setVersion(prot);
        e.setResponse(ping);
    }

}
