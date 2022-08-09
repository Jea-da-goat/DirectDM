package com.itndev.privatemsg;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.ArrayList;
import java.util.Locale;

public class endcommand extends Command {
    public endcommand() {
        super("endsafe", "directdm.use.admin.end", new String[] { "endsafe", "end" });
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(sender.hasPermission("directdm.use.admin.end")) {
            ProxyServer.getInstance().stop();
        }
    }
}
