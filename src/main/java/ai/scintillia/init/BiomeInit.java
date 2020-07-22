package ai.scintillia.init;

import ai.scintillia.MeteoCraft;
import ai.scintillia.World.Biomes.IceDesert;

import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;



public class BiomeInit {
    public static final DeferredRegister<Biome> BIOMES = new DeferredRegister<>(ForgeRegistries.BIOMES, MeteoCraft.MODID);
    public static final RegistryObject<Biome> ICE_DESERT= BIOMES.register("Ice_Desert", () -> new IceDesert(new Biome.Builder()
            .precipitation(Biome.RainType.SNOW)
            .scale(2.0f)
            .temperature(-3.0f)
            .waterColor(53247)
            .waterFogColor(9359359)
            .surfaceBuilder(SurfaceBuilder.DEFAULT,
                            new SurfaceBuilderConfig(Blocks.SNOW.getDefaultState(),
                                                     Blocks.SNOW_BLOCK.getDefaultState(),
                                                     Blocks.COARSE_DIRT.getDefaultState() ))
            .category(Biome.Category.PLAINS)
            .category(Biome.Category.FOREST)
            .downfall(0.5f)
            .depth(0.13f)
            .parent(null)

    ));

    public static void registerBiomes(){
    registerBiome(ICE_DESERT.get(), BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.SNOWY,BiomeDictionary.Type.SAVANNA,BiomeDictionary.Type.PLATEAU, BiomeDictionary.Type.OVERWORLD );
    }

    private static void registerBiome(Biome biome, BiomeDictionary.Type...types){
        BiomeDictionary.addTypes(biome, types);
        BiomeManager.addSpawnBiome(biome);

    }

}
