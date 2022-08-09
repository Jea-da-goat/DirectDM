package com.itndev.privatemsg;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.Locale;

public class ReplyCommand extends Command {
    public ReplyCommand() {
        super("답장", null, new String[] { "답장" });
    }

    public void execute(CommandSender paramCommandSender, String[] paramArrayOfString) {
        if (!(paramCommandSender instanceof ProxiedPlayer)) {
            return;
        }

        ProxiedPlayer proxiedPlayer = (ProxiedPlayer)paramCommandSender;
        if (paramArrayOfString.length > 0) {
            ProxiedPlayer proxiedPlayer1 = Main.getConversation(proxiedPlayer);
            if(Main.ignore.containsKey(proxiedPlayer1.getUniqueId()) && !Main.ignore.get(proxiedPlayer1.getUniqueId()).isEmpty() && Main.ignore.get(proxiedPlayer1.getUniqueId()).contains(proxiedPlayer.getName().toLowerCase(Locale.ROOT))) {
                proxiedPlayer.sendMessage((BaseComponent)new TextComponent(ChatColor.translateAlternateColorCodes('&', "&f&l[&r&c귓말&f&l] &f해당 유저는 당신을 차단했습니다")));
                return;

            }
            if (proxiedPlayer1 != null) {
                String str = "";
                String[] arrayOfString;
                int i = (arrayOfString = paramArrayOfString).length;
                byte b;
                for (b = 0; b < i; b = (byte)(b + 1)) {
                    String str1 = arrayOfString[b];
                    str = String.valueOf(str) + str1 + " ";
                }
                Main.msg(proxiedPlayer, proxiedPlayer1, str.trim().replace("&", "§"));
            } else {
                proxiedPlayer.sendMessage((BaseComponent)new TextComponent(ChatColor.translateAlternateColorCodes('&', "&f&l[&r&c귓말&f&l] &f최근에 귓말을 받거나 보낸적이 없습니다".trim().replace("&", "§"))));
            }
        } else {
            paramCommandSender.sendMessage((BaseComponent)new TextComponent(ChatColor.translateAlternateColorCodes('&', "&f&l[&r&c귓말&f&l] &f/답장 &7[답장내용]".trim().replace("&", "§"))));
        }
    }
}
