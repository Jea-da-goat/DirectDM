package com.itndev.privatemsg;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class FakeCommand extends Command {
    public FakeCommand() {
        super("proxymananger", "proxymananger.admin.use", new String[] { "pm"});
    }

    @Deprecated
    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            return;
        }
        if(sender.hasPermission("proxymananger.admin.use")) {
            if(args.length < 2) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&l[FAX] &7명령어가 너무 짧습니다."));
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&l-----------[FAX]-----------\n" +
                        "&r&7/proxymananger setslot [INT]\n" +
                        "&r&7/proxymananger setfakeplayer [INT]\n" +
                        "&r&7/proxymananger setversion [STRING]\n" +
                        "&r&c&l-----------[FAX]-----------\n"));
                return;
            }
            if(args[0].equalsIgnoreCase("setslot")) {
                try {
                    int amount = Integer.parseInt(args[1]);
                    listener.SLOTAMOUNT = amount;
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&l[FAX] &7성공. 최대 입장가능인원수 표시를 " + amount + " 으로 변경"));
                } catch (Exception e) {
                    e.printStackTrace();
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&l[FAX] &7오류 . 사용법 : /proxymananger setslot [INT]"));
                }
            } else if(args[0].equalsIgnoreCase("setfakeplayer")) {
                try {
                    int amount = Integer.parseInt(args[1]);
                    listener.FAKEPLAYERS = amount;
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&l[FAX] &7성공. 가짜 동시접속자를 " + amount + " 으로 설정"));
                } catch (Exception e) {
                    e.printStackTrace();
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&l[FAX] &7오류 . 사용법 : /proxymananger setfakeplayer [INT]"));
                }
            } else if(args[0].equalsIgnoreCase("setversion")) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&l[FAX] &7성공. 서버 표시 버전을 " + listener.VERSION + " 으로 설정"));
                listener.VERSION = args[1].replace("[SPACE]", " ");
            }
        } else {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&l[FAX] &7권한이 없습니다"));
        }
    }
}
