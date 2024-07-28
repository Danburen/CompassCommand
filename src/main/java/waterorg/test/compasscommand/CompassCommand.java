//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package waterorg.test.compasscommand;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;


public final class CompassCommand extends JavaPlugin {
    private static boolean hasPapi = false;
    protected static CompassCommand instance;

    public CompassCommand() {
    }

    public void onEnable() {
        this.saveDefaultConfig();
        if (this.getConfig().getBoolean("Enable")) {
            instance = this;
            this.getServer().getPluginManager().registerEvents(new EventListener(), this);
            say("§2Successfully enabled");
            say("§2Author:Hachett(Waterwood)");
            say("§2Version:beta1.2");
            if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null){
                say("PAPI is available");
                hasPapi  = true;
            }

        } else {
            say("§4CompassCommand has been disabled to load,to enable set the \"Enable\" to true in the config");
        }

    }

    public void onDisable() {
        say("§2Successfully Disabled");
    }

    public static void say(String s) {
        CommandSender sender = Bukkit.getConsoleSender();
        sender.sendMessage(String.format("[§2CompassCommand§f]:%s", s));
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if ((sender instanceof Player || sender instanceof ConsoleCommandSender) && (label.equalsIgnoreCase("reload") || sender.hasPermission("reload.me"))) {
            this.reloadConfig();
            sender.sendMessage("[§2CompassCommand§f]All configs have been reloaded");
        }

        return true;
    }

    public static CompassCommand getInstance() {
        return instance;
    }

    public static boolean hasPapi(){
        return hasPapi;
    }

}
