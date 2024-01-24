package io.funworld.bungeehubcommand;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class HubCommand extends Command {
    BungeeHubCommand instance;
    public HubCommand(BungeeHubCommand instance) {
        super("hub");
        this.instance = instance;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(sender instanceof ProxiedPlayer){
            ProxiedPlayer player = (ProxiedPlayer) sender;
            if(!player.getServer().getInfo().getName().equalsIgnoreCase(instance.server_name)){
                if(!player.getServer().getInfo().getName().equalsIgnoreCase(instance.default_name)){
                    player.sendMessage(new ComponentBuilder("You are already connected to the Hub!").color(ChatColor.RED).create());
                    return;
                }
                ServerInfo target = ProxyServer.getInstance().getServerInfo(instance.server_name);
                player.connect(target);
            }else{
                player.sendMessage(new ComponentBuilder("You are already connected to the Hub!").color(ChatColor.RED).create());
            }
        }else{
            sender.sendMessage(new ComponentBuilder("This command can only be run by a player!").color(ChatColor.RED).create());
        }
    }
}
