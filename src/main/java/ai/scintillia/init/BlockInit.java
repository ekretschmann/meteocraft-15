package ai.scintillia.init;

import ai.scintillia.MeteoCraft;
import ai.scintillia.objects.blocks.Meteorite1;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.DyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;


@ObjectHolder(MeteoCraft.MODID)
@Mod.EventBusSubscriber(modid = MeteoCraft.MODID, bus = Mod.EventBusSubscriber.Bus.MOD )

public class BlockInit {

    public static final Block meteorite_1=null;

    @SubscribeEvent
    public static void registerBlocks(final RegistryEvent.Register<Block> event){
        event.getRegistry().register(
                new Meteorite1(Block.Properties.create(Material.IRON))
        .setRegistryName("meteorite_1"));
    }

    @SubscribeEvent
    public static void registerBlockItems(final RegistryEvent.Register<Item> event){

        event.getRegistry().register(new BlockItem(meteorite_1,new Item.Properties().maxStackSize(10).group(ItemGroup.BUILDING_BLOCKS)).setRegistryName("meteorite_1")  );
    }

}
