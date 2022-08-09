package com.itndev.privatemsg;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.ArrayList;
import java.util.Locale;

public class ignoreaddremove extends Command {
    public ignoreaddremove() {
        super("차단", null, new String[] { "차단" });
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer p = (ProxiedPlayer) sender;
            if(args.length >= 2) {

                if(args[0].equalsIgnoreCase("추가")) {
                    //ProxiedPlayer player = ProxyServer.getInstance().getPlayer(args[1]);

                        if(Main.ignore.containsKey(p.getUniqueId()) && Main.ignore.get(p.getUniqueId()) != null &&!Main.ignore.get(p.getUniqueId()).isEmpty() && Main.ignore.get(p.getUniqueId()).contains(args[1].toLowerCase(Locale.ROOT))) {
                            utils.sendmsg(p, "&f&l[&r&c귓말&f&l] &r&f해당유저 " + args[1] + "(이)를 이미 차단했습니다");
                        } else {
                            if(!Main.ignore.containsKey(p.getUniqueId()) || Main.ignore.get(p.getUniqueId()).isEmpty() || Main.ignore.get(p.getUniqueId()) == null) {
                                ArrayList<String> ignores = new ArrayList<>();
                                ignores.add(args[1].toLowerCase(Locale.ROOT));
                                Main.ignore.put(p.getUniqueId(), ignores);
                            } else if(Main.ignore.containsKey(p.getUniqueId())) {
                                ArrayList<String> ignores = Main.ignore.get(p.getUniqueId());
                                ignores.add(args[1].toLowerCase(Locale.ROOT));
                                Main.ignore.put(p.getUniqueId(), ignores);
                            }


                            utils.sendmsg(p, "&f&l[&r&c귓말&f&l] &r&f해당유저 " + args[1].toLowerCase(Locale.ROOT) + "(이)를 성공적으로 차단했습니다");
                        }

                } else if(args[0].equalsIgnoreCase("해제")) {
                    ProxiedPlayer player = ProxyServer.getInstance().getPlayer(args[1]);
                    if(args[1] != null) {
                        if(Main.ignore.containsKey(p.getUniqueId()) && !Main.ignore.get(p.getUniqueId()).isEmpty() && Main.ignore.get(p.getUniqueId()).contains(args[1].toLowerCase(Locale.ROOT))) {
                            ArrayList<String> ignores = Main.ignore.get(p.getUniqueId());
                            ignores.remove(args[1].toLowerCase(Locale.ROOT));
                            Main.ignore.put(p.getUniqueId(), ignores);
                            utils.sendmsg(p, "&f&l[&r&c귓말&f&l] &r&f해당유저 " + args[1].toLowerCase(Locale.ROOT) + "(이)에 대한 차단을 해제했습니다");
                        } else {
                            utils.sendmsg(p, "&f&l[&r&c귓말&f&l] &r&f해당유저 " + args[1].toLowerCase(Locale.ROOT)  + "(이)를 차단한 적이 없습니다");
                        }
                    } else {
                        utils.sendmsg(p, "&l&f[&r&c귓말&l&f] &r&f온라인인 유저만 차단이 가능합니다");
                    }
                } else {
                    utils.sendmsg(p, "&f&l[&r&c귓말&f&l] &r&f명령어 사용법 &8: &f/차단 &7(추가/해제) &7[닉네임]");
                    utils.sendmsg(p, "&f&l[&r&c귓말&f&l] &r&f또는 &8: &f/차단 목록");
                }
            } else if(args.length == 1) {
                if(args[0].equalsIgnoreCase("목록")) {

                    ArrayList<String> ignorednames = new ArrayList<>();
                    if(Main.ignore.containsKey(p.getUniqueId())) {
                        ignorednames = Main.ignore.get(p.getUniqueId());
                    }
                    utils.sendmsg(p, "&m&7=====&r&f&l[&r&c차단목록&f&l]&r&m&7=====");
                    if(ignorednames.isEmpty()) {
                        utils.sendmsg(p, "- 차단한유저 &8: &f없음");
                    }
                    for(String names : ignorednames) {
                        utils.sendmsg(p, "- 차단한유저 &8: &f" + names);
                    }
                    utils.sendmsg(p, "&m&7=====&r&f&l[&r&c차단목록&f&l]&r&m&7=====");
                } else {
                    utils.sendmsg(p, "&f&l[&r&c귓말&f&l] &r&f명령어 사용법 &8: &f/차단 &7(추가/해제) &7[닉네임]");
                    utils.sendmsg(p, "&f&l[&r&c귓말&f&l] &r&f또는 &8: &f/차단 목록");
                }

            } else {
                utils.sendmsg(p, "&f&l[&r&c귓말&f&l] &r&f명령어 사용법 &8: &f/차단 &7(추가/해제) &7[닉네임]");
                utils.sendmsg(p, "&f&l[&r&c귓말&f&l] &r&f또는 &8: &f/차단 목록");
            }
        }

    }
}
