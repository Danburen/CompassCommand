package waterorg.test.compasscommand;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class EventListener implements Listener{
    private Method is = new Method();
    @EventHandler
    public void onPlayJoin(PlayerJoinEvent evt){
        Player player = evt.getPlayer();
        Inventory inv = player.getInventory();
        is.updateItem();
        if(is.getConfig().getBoolean("GivePlayOnJoin")){
            if(!(inv.contains(is.getItemStack()))){
                inv.addItem(is.getItemStack());
            }
        }
    }
    @EventHandler
    public void onClick(PlayerInteractEvent evt) {
        if (evt.getMaterial() == Material.getMaterial(is.getConfig().getString("Material"))) {
            if ((evt.getAction().isRightClick() && is.getConfig().getBoolean("RightClick"))
                    || (evt.getAction().isLeftClick() && is.getConfig().getBoolean("LeftClick"))) {
                evt.setCancelled(true);
                evt.getPlayer().chat("/" + is.getConfig().getString("Command"));
                if (is.getConfig().getString("CmdMessage") != "") {
                    evt.getPlayer().sendMessage(is.getConfig().getString("CmdMessage"));
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
}
