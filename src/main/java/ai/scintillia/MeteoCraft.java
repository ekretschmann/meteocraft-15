package ai.scintillia;

import ai.scintillia.commands.HelloCommand;
import ai.scintillia.init.BiomeInit;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("deprecation")
@Mod(MeteoCraft.MODID)
@Mod.EventBusSubscriber(modid = MeteoCraft.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)

public class MeteoCraft {

    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "meteocraft";
    public static MeteoCraft instance;
    public static final ResourceLocation EXAMPLE_DIM_TYPE = new ResourceLocation(MODID, "example");




    public MeteoCraft() {

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::setup);

        BiomeInit.BIOMES.register(modEventBus);


        instance = this;
        MinecraftForge.EVENT_BUS.register(this);

    }
    @SubscribeEvent
    public static void onRegisterBiomes(final RegistryEvent.Register<Biome> event) {
        BiomeInit.registerBiomes();
    }

    private void setup(final FMLCommonSetupEvent event) {// K9#8016

    }



}
