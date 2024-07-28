package waterorg.test.compasscommand;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import me.clip.placeholderapi.PlaceholderAPI;

public class EventListener implements Listener{
    private Method is = new Method();
    @EventHandler
    public void onPlayJoin(PlayerJoinEvent evt){
        Player player = evt.getPlayer();
        Inventory inv = player.getInventory();
        is.updateItem(player);
        if(is.getConfig().getBoolean("GivePlayOnJoin")){
            if(!(inv.contains(is.getItemStack()))){
                inv.addItem(is.getItemStack());
            }
        }
    }
    @EventHandler
    public void onClick(PlayerInteractEvent evt) {
        if (evt.getMaterial() == Material.getMaterial(is.getConfig().getString("Material"))) {
            if (((evt.getAction().equals(Action.RIGHT_CLICK_AIR) || evt.getAction().equals(Action.RIGHT_CLICK_BLOCK) )&& is.getConfig().getBoolean("RightClick"))
                    || ((evt.getAction().equals(Action.LEFT_CLICK_AIR)||evt.getAction().equals(Action.LEFT_CLICK_BLOCK)) && is.getConfig().getBoolean("LeftClick"))) {
                evt.setCancelled(true);
                Player player = evt.getPlayer();
                if (is.getConfig().getString("PlayerCommand") != "") {
                    String commandText = "/" + is.getConfig().getString("PlayerCommand");
                    if (CompassCommand.hasPapi()) commandText = PlaceholderAPI.setPlaceholders(player, commandText);
                    evt.getPlayer().chat(commandText);
                }
                if (is.getConfig().get("ServerCommand") !=""){
                    CommandSender sender = Bukkit.getConsoleSender();
                    String commandText = is.getConfig().getString("ServerCommand");
                    if (CompassCommand.hasPapi()) commandText = PlaceholderAPI.setPlaceholders(player,commandText);
                    sender.getServer().dispatchCommand(sender,commandText);
                }
                if (is.getConfig().getString("CmdMessage") != "") {
                    String cmdMessageText = is.getConfig().getString("CmdMessage");
                    if (CompassCommand.hasPapi()) cmdMessageText = PlaceholderAPI.setPlaceholders(player,cmdMessageText);
                    evt.getPlayer().sendMessage(cmdMessageText);
                }

            }
        }
    }
    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent evt){
        if(!(is.getConfig().getBoolean("AllowPlayerDrop"))) {
            ItemStack Lis = evt.getItemDrop().getItemStack();
            if (is.getItemStack().equals(evt.getItemDrop().getItemStack())) {
                evt.getItemDrop().remove();
                evt.getPlayer().getInventory().addItem(is.getItemStack());
            }
        }
    }
    @EventHandler
    public void onPlayerDeath(PlayerRespawnEvent evt){
        Player player = evt.getPlayer();
        Inventory inv = player.getInventory();
        is.updateItem(player);
        if (!(inv.contains(is.getItemStack()))){
            inv.addItem(is.getItemStack());
        }
    }
}

