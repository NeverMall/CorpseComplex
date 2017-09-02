package c4.corpsecomplex.common.modules.inventory.enchantment;

import c4.corpsecomplex.CorpseComplex;
import c4.corpsecomplex.common.Module;
import c4.corpsecomplex.common.Submodule;
import c4.corpsecomplex.common.helpers.ModuleHelper;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.apache.logging.log4j.Level;

import java.util.*;

public class EnchantmentModule extends Submodule {

    @GameRegistry.ObjectHolder("corpsecomplex:soulbound")
    public static EnchantmentSoulbound soulbound;

    public static boolean registerEnchant;
    public static String rarity;
    public static double levelDrop;
    public static double baseSave;
    public static double extraPerLevel;

    static boolean allowedOnBooks;
    static boolean canApplyEnchantingTable;
    static int maxLevel;

    private static boolean cfgEnabled;

    public EnchantmentModule(Module parentModule) {
        super(parentModule, "Soulbound");
        configCategory.setComment("Enable and customize the soulbound enchantment");
    }

    public void loadModuleConfig() {
        setCategoryComment();
        cfgEnabled = getBool("Enable Soulbound Enchantment", false, "Set to true to enable Soulbound enchantment", true);
        canApplyEnchantingTable = getBool("Can Apply at Enchanting Table", true, "Set to true to allow enchanting Soulbound at the enchantment table", false);
        rarity = getString("Rarity", "VERY_RARE", "The rarity of the enchantment (COMMON, UNCOMMON, RARE, or VERY_RARE)", new String[] {"COMMON", "UNCOMMON", "RARE", "VERY_RARE"}, true);
        maxLevel = getInt("Max Level", 1, 1, 5, "The max level of the enchantment", true);
        levelDrop = getDouble("Chance to Drop Level on Saved Item", 0, 0, 1, "The percent chance that the item will drop a level in the enchantment on death when kept", false);
        baseSave = getDouble("Base Save Probability", 1, 0, 1, "The base percent chance that the enchantment will save an item on death regardless of level", false);
        extraPerLevel = getDouble("Extra Save Probability per Level", 0, 0, 1, "The percent chance increase that each level in the enchantment will give to saving an item on death", false);
        allowedOnBooks = getBool("Allowed on Books", true, "Set to true to allow enchanting Soulbound on books", false);
    }

    public void initPropOrder() {
        propOrder = new ArrayList<>(Arrays.asList("Enable Soulbound Enchantment", "Max Level", "Chance to Drop Level on Saved Item", "Base Save Probability",
                "Extra Save Probability per Level", "Can Apply at Enchanting Table", "Allowed on Books"));
    }

    public void setEnabled() {
        enabled = registerEnchant = cfgEnabled && parentModule.enabled;
    }
}
