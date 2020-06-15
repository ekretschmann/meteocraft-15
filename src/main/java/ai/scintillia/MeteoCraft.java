package ai.scintillia;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.Level;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(MeteoCraft.MODID)
public class MeteoCraft {
    // you also need to update the modid in two other places as well:
    //  build.gradle file (the version, group, and archivesBaseName parameters)
    //  resources/META-INF/mods.toml (the name, description, and version parameters)
    public static final String MODID = "meteocraft";

    // get a reference to the event bus for this mod;  Registration events are fired on this bus.
    public static IEventBus MOD_EVENT_BUS;

    public MeteoCraft() {
        final boolean HIDE_CONSOLE_NOISE = false;  // todo get rid of all the noise from the console (after mod is constructed) to show warnings more clearly.
//        if (HIDE_CONSOLE_NOISE) {
//            ForgeLoggerTweaker.setMinimumLevel(Level.WARN);
//            ForgeLoggerTweaker.applyLoggerFilter();
//        }

        MOD_EVENT_BUS = FMLJavaModLoadingContext.get().getModEventBus();

        registerCommonEvents();
        DistExecutor.runWhenOn(Dist.CLIENT, () -> MeteoCraft::registerClientOnlyEvents);
    }

    public static void registerCommonEvents() {
        MOD_EVENT_BUS.register(ai.scintillia.blockexample.StartupCommon.class);
//        MOD_EVENT_BUS.register(minecraftbyexample.mbe02_block_partial.StartupCommon.class);
//        MOD_EVENT_BUS.register(minecraftbyexample.mbe03_block_variants.StartupCommon.class);
//        MOD_EVENT_BUS.register(minecraftbyexample.mbe04_block_dynamic_block_models.StartupCommon.class);
//        MOD_EVENT_BUS.register(minecraftbyexample.mbe05_block_advanced_models.StartupCommon.class);
//        MOD_EVENT_BUS.register(minecraftbyexample.mbe06_redstone.StartupCommon.class);
//        MOD_EVENT_BUS.register(minecraftbyexample.mbe10_item_simple.StartupCommon.class);
//        MOD_EVENT_BUS.register(minecraftbyexample.mbe08_itemgroup.StartupCommon.class);
//        MOD_EVENT_BUS.register(minecraftbyexample.mbe11_item_variants.StartupCommon.class);
//        MOD_EVENT_BUS.register(minecraftbyexample.mbe12_item_nbt_animate.StartupCommon.class);
//        MOD_EVENT_BUS.register(minecraftbyexample.mbe15_item_dynamic_item_model.StartupCommon.class);
//        MOD_EVENT_BUS.register(minecraftbyexample.mbe20_tileentity_data.StartupCommon.class);
//        MOD_EVENT_BUS.register(minecraftbyexample.mbe21_tileentityrenderer.StartupCommon.class);
//        MOD_EVENT_BUS.register(minecraftbyexample.mbe30_inventory_basic.StartupCommon.class);
//        MOD_EVENT_BUS.register(minecraftbyexample.mbe31_inventory_furnace.StartupCommon.class);
//        MOD_EVENT_BUS.register(minecraftbyexample.mbe32_inventory_item.StartupCommon.class);
//        MOD_EVENT_BUS.register(minecraftbyexample.mbe35_recipes.StartupCommon.class);
//        MOD_EVENT_BUS.register(minecraftbyexample.mbe45_commands.StartupCommon.class);
//        MOD_EVENT_BUS.register(minecraftbyexample.mbe50_particle.StartupCommon.class);
//        MOD_EVENT_BUS.register(minecraftbyexample.mbe60_network_messages.StartupCommon.class);
//        MOD_EVENT_BUS.register(minecraftbyexample.mbe75_testing_framework.StartupCommon.class);
//        MOD_EVENT_BUS.register(minecraftbyexample.mbe80_model_renderer.StartupCommon.class);
//
//        //----------------
//        MOD_EVENT_BUS.register(minecraftbyexample.usefultools.debugging.StartupCommon.class);
    }

    public static void registerClientOnlyEvents() {
        MOD_EVENT_BUS.register(ai.scintillia.blockexample.StartupClientOnly.class);
//        MOD_EVENT_BUS.register(minecraftbyexample.mbe02_block_partial.StartupClientOnly.class);
//        MOD_EVENT_BUS.register(minecraftbyexample.mbe03_block_variants.StartupClientOnly.class);
//        MOD_EVENT_BUS.register(minecraftbyexample.mbe04_block_dynamic_block_models.StartupClientOnly.class);
//        MOD_EVENT_BUS.register(minecraftbyexample.mbe05_block_advanced_models.StartupClientOnly.class);
//        MOD_EVENT_BUS.register(minecraftbyexample.mbe06_redstone.StartupClientOnly.class);
//        MOD_EVENT_BUS.register(minecraftbyexample.mbe10_item_simple.StartupClientOnly.class);
//        MOD_EVENT_BUS.register(minecraftbyexample.mbe08_itemgroup.StartupClientOnly.class);
//        MOD_EVENT_BUS.register(minecraftbyexample.mbe11_item_variants.StartupClientOnly.class);
//        MOD_EVENT_BUS.register(minecraftbyexample.mbe12_item_nbt_animate.StartupClientOnly.class);
//        MOD_EVENT_BUS.register(minecraftbyexample.mbe15_item_dynamic_item_model.StartupClientOnly.class);
//        MOD_EVENT_BUS.register(minecraftbyexample.mbe20_tileentity_data.StartupClientOnly.class);
//        MOD_EVENT_BUS.register(minecraftbyexample.mbe21_tileentityrenderer.StartupClientOnly.class);
//        MOD_EVENT_BUS.register(minecraftbyexample.mbe30_inventory_basic.StartupClientOnly.class);
//        MOD_EVENT_BUS.register(minecraftbyexample.mbe31_inventory_furnace.StartupClientOnly.class);
//        MOD_EVENT_BUS.register(minecraftbyexample.mbe32_inventory_item.StartupClientOnly.class);
//        MOD_EVENT_BUS.register(minecraftbyexample.mbe35_recipes.StartupClientOnly.class);
//        MOD_EVENT_BUS.register(minecraftbyexample.mbe45_commands.StartupClientOnly.class);
//        MOD_EVENT_BUS.register(minecraftbyexample.mbe50_particle.StartupClientOnly.class);
//        MOD_EVENT_BUS.register(minecraftbyexample.mbe60_network_messages.StartupClientOnly.class);
//        MOD_EVENT_BUS.register(minecraftbyexample.mbe75_testing_framework.StartupClientOnly.class);
//        MOD_EVENT_BUS.register(minecraftbyexample.mbe80_model_renderer.StartupClientOnly.class);
//
//        //----------------
//        MOD_EVENT_BUS.register(minecraftbyexample.usefultools.debugging.StartupClientOnly.class);
    }

}
