package waterorg.test.compasscommand;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import me.clip.placeholderapi.PlaceholderAPI;
import java.util.ArrayList;

public  class Method {
    private ItemStack is;
    private FileConfiguration config = CompassCommand.getInstance().getConfig();;
    Method(){
        is = new ItemStack(Material.getMaterial(config.getString("Material")));
    }
    public ItemStack getItemStack(){
        return is;
    }
    public  void updateItem(Player player){
        config = CompassCommand.getInstance().getConfig();
        ItemMeta im = is.getItemMeta();
        ArrayList<String> lores = (ArrayList)config.getStringList("Lores");
        String disPlayerNameText = config.getString("DisplayName");
        if (CompassCommand.hasPapi()){
            for(String lore: lores){
                lores.set(lores.indexOf(lore),PlaceholderAPI.setPlaceholders(player, lore)) ;
            }
            disPlayerNameText = PlaceholderAPI.setPlaceholders(player,disPlayerNameText);
        }
        im.setDisplayName(disPlayerNameText);
        im.setLore(lores);
        is.setItemMeta(im);
    }

    public FileConfiguration getConfig(){
        config = CompassCommand.getInstance().getConfig();
        return config;
    }

}
