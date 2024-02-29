package waterorg.test.compasscommand;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public  class Method {
    private ItemStack is;
    private FileConfiguration config = CompassCommand.getInstance().getConfig();;
    Method(){
        is = new ItemStack(Material.getMaterial(config.getString("Material")));
        updateItem();
    }
    public ItemStack getItemStack(){
        return is;
    }

    public  void updateItem(){
        config = CompassCommand.getInstance().getConfig();
        ItemMeta im = is.getItemMeta();
        ArrayList<String> lores = (ArrayList)config.getStringList("Lores");
        im.setDisplayName(config.getString("DisplayName"));
        im.setLore(lores);
        is.setItemMeta(im);
    }

    public FileConfiguration getConfig(){
        config = CompassCommand.getInstance().getConfig();
        return config;
    }
}
