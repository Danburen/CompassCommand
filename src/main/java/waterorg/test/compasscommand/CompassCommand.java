package waterorg.test.compasscommand;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;


public final class CompassCommand extends JavaPlugin {
    protected static CompassCommand instance;
    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        if(getConfig().getBoolean("Enable")) {
            instance = this;
            getServer().getPluginManager().registerEvents(new EventListener(),this);
            say("§2Successfully enabled");
            say("§2Author:Hachett(Waterwood)");
            say("§2Version:beta1.0");
        }else say("§4CompassCommand has been disabled to load,to enable set the \"Enable\" to true in the config");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        say("§2Successfully Disabled");
    }

    public static void say(String s){
        CommandSender sender = Bukkit.getConsoleSender();
        sender.sendMessage(s.format("[§2CompassCommand§f]:%s",s));
    }

    public boolean onCommand(CommandSender sender, Command cmd,String label,String args[]){
        if (sender instanceof Player || sender instanceof org.bukkit.command.ConsoleCommandSender){
            if(label.equalsIgnoreCase("reload")|| sender.hasPermission("reload.me")) {
                reloadConfig();

                say("§2All configs have been reloaded");
                sender.sendMessage("[§2CompassCommand§f]All configs have been reloaded");
            }
        }
        return true;
    }
    public static CompassCommand getInstance() {
        return instance;
    }
}
