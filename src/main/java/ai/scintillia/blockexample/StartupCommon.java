package ai.scintillia.blockexample;

import ai.scintillia.MeteoCraft;
import ai.scintillia.init.BiomeInit;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

/**
 * User: The Grey Ghost
 * Date: 24/12/2014
 *
 * The Startup classes for this example are called during startup, in the following order:
 * onBlocksRegistration then onItemsRegistration then onCommonSetupEvent
 *  See MinecraftByExample class for more information
 */
@Mod.EventBusSubscriber(modid = MeteoCraft.MODID,bus= Mod.EventBusSubscriber.Bus.MOD)
public class StartupCommon {
    public static BlockSimple BlockSimple;  // this holds the unique instance of your block
    public static BlockItem itemBlockSimple;  // this holds the instance of the ItemBlock for your Block

    @SubscribeEvent
    public static void onBlocksRegistration(final RegistryEvent.Register<Block> blockRegisterEvent) {
        BlockSimple = (BlockSimple)(new BlockSimple().setRegistryName("meteocraft", "mbe01_block_simple_registry_name"));

        blockRegisterEvent.getRegistry().register(BlockSimple);
    }

    @SubscribeEvent
    public static void onRegisterBiomes(final RegistryEvent.Register<Biome> event ) {
        BiomeInit.registerBiomes();
    }

    @SubscribeEvent
    public static void onItemsRegistration(final RegistryEvent.Register<Item> itemRegisterEvent) {
        // We need to create a BlockItem so the player can carry this block in their hand and it can appear in the inventory
        final int MAXIMUM_STACK_SIZE = 20;  // player can only hold one of this block in their hand at once

        Item.Properties itemSimpleProperties = new Item.Properties()
                .maxStackSize(MAXIMUM_STACK_SIZE)
                .group(ItemGroup.BUILDING_BLOCKS);   // which inventory tab
        itemBlockSimple = new BlockItem(BlockSimple, itemSimpleProperties);
        itemBlockSimple.setRegistryName(BlockSimple.getRegistryName());
        itemRegisterEvent.getRegistry().register(itemBlockSimple);
    }

    @SubscribeEvent
    public static void onCommonSetupEvent(FMLCommonSetupEvent event) {
        // not actually required for this example....
    }
}
