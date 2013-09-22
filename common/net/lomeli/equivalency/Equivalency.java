package net.lomeli.equivalency;

import java.util.logging.Level;

import net.lomeli.equivalency.core.CommonProxy;
import net.lomeli.equivalency.helper.TransmutationHelper;
import net.lomeli.equivalency.lib.ModVars;
import net.lomeli.equivalency.recipes.AppliedEnergisticsRecipes;
import net.lomeli.equivalency.recipes.ForestryRecipes;
import net.lomeli.equivalency.recipes.IC2Recipes;
import net.lomeli.equivalency.recipes.DartCraftRecipes;
//import net.lomeli.equivalency.recipes.MagiksRecipes;
import net.lomeli.equivalency.recipes.RailCraftRecipes;
import net.lomeli.equivalency.recipes.TERecipes;
import net.lomeli.equivalency.recipes.ThaumCraftRecipes;
import net.lomeli.equivalency.recipes.UniversalRecipes;
import net.lomeli.equivalency.recipes.VanillaRecipes;

import net.lomeli.lomlib.util.LogHelper;
import net.lomeli.lomlib.util.ModLoaded;
import net.lomeli.lomlib.util.UpdateHelper;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid = ModVars.MOD_ID, name = ModVars.MOD_NAME, version = ModVars.VERSION, dependencies = "required-after:LomLib@[1.0.5,);required-after:EE3")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class Equivalency {
    public static boolean limitRecipes;
    public int numberInstalled;

    public static LogHelper logger = new LogHelper(ModVars.MOD_NAME);
    public static UpdateHelper updater = new UpdateHelper();

    @SidedProxy(clientSide = ModVars.CLIENT, serverSide = ModVars.COMMON)
    public static CommonProxy proxy;

    public static boolean emeraldTransmute;
    public static boolean blazeTransmute;
    public static boolean cQTransmute;
    public static boolean steelTransmute;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Configuration config = new Configuration(
                event.getSuggestedConfigurationFile());

        config.load();

        emeraldTransmute = config.get("general", "defaultEmeraldTransmute",
                true, ModVars.emeraldDesc).getBoolean(true);
        blazeTransmute = config.get("general", "blazeTransmute", false,
                ModVars.blazeDesc).getBoolean(false);
        cQTransmute = config
                .get("general", "cqTransmute", true, ModVars.cQDesc)
                .getBoolean(true);
        steelTransmute = config.get("general", "steelTransmute", true,
                "Disables steel transmutation").getBoolean(true);

        config.save();

        try {
            updater.check(ModVars.MOD_NAME, ModVars.UPDATE_XML, ModVars.MAJOR,
                    ModVars.MINOR, ModVars.REVISION);
        } catch (Exception e) {
        }

        proxy.registerTickHandler();
    }

    @Mod.EventHandler
    public void main(FMLInitializationEvent event) {
        if (ModLoaded.isModInstalled(ModVars.EE3_ID, true)) {
            numberInstalled = 0;
            limitRecipes = false;
            TransmutationHelper.addStones();
            logger.log(Level.INFO, "Loading Vanilla Recipes.");
            for (ItemStack transmutationStone : TransmutationHelper.transmutationStones) {
                VanillaRecipes.loadRecipes(transmutationStone);
            }
        }
    }

    @Mod.EventHandler
    public void postLoad(FMLPostInitializationEvent event) {
        if (ModLoaded.isModInstalled(ModVars.EE3_ID)) {
            logger.log(Level.INFO,
                    "Searching for additional mods and loading additional recipes.");
            for (ItemStack transmutationStone : TransmutationHelper.transmutationStones) {
                if (ModLoaded.isModInstalled(ModVars.IC2_ID))
                    numberInstalled++;
                if (ModLoaded.isModInstalled(ModVars.TE_ID))
                    numberInstalled++;
                if (ModLoaded.isModInstalled(ModVars.FORESTRY_ID))
                    numberInstalled++;
                if (ModLoaded.isModInstalled(ModVars.MM_ID))
                    numberInstalled++;

                if (numberInstalled > 1)
                    limitRecipes = true;

                if (ModLoaded.isModInstalled(ModVars.IC2_ID))
                    IC2Recipes.loadRecipes(transmutationStone, ModVars.IC2_ID);

                if (ModLoaded.isModInstalled(ModVars.TE_ID))
                    TERecipes.loadRecipes(transmutationStone, ModVars.TE_ID);

                if (ModLoaded.isModInstalled(ModVars.FORESTRY_ID))
                    ForestryRecipes.loadRecipes(transmutationStone,
                            ModVars.FORESTRY_ID);

                if(ModLoaded.isModInstalled(ModVars.DART_ID, false))
                    DartCraftRecipes.loadRecipes(transmutationStone, ModVars.DART_ID);

                if (ModLoaded.isModInstalled(ModVars.TC_ID))
                    ThaumCraftRecipes.loadRecipes(transmutationStone,
                            ModVars.TC_ID);

                if (ModLoaded.isModInstalled(ModVars.RC_ID))
                    RailCraftRecipes.loadRecipes(transmutationStone,
                            ModVars.RC_ID);

                if (ModLoaded.isModInstalled(ModVars.AE_ID))
                    AppliedEnergisticsRecipes.loadRecipes(transmutationStone,
                            ModVars.AE_ID);

                UniversalRecipes.loadRecipes(transmutationStone);
            }
        }
    }

    public static void loadModRecipes(String modName) {
        logger.log(Level.INFO, "Loading " + modName + " recipes...");
    }
}
