package ai.scintillia.init;

import ai.scintillia.MeteoCraft;
import ai.scintillia.world.biomes.ColdDesert;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BiomeInit {
    public static final DeferredRegister<Biome> BIOMES = new DeferredRegister<>(ForgeRegistries.BIOMES,
            MeteoCraft.MODID);

    public static final RegistryObject<Biome> EXAMPLE_BIOME = BIOMES
            .register("cold_desert",
                    () -> new ColdDesert(
//							new Biome.Builder().precipitation(RainType.SNOW).scale(1.2f).temperature(0.5f)
//									.waterColor(16724639).waterFogColor(16762304)
//									.surfaceBuilder(
//											new ConfiguredSurfaceBuilder<SurfaceBuilderConfig>(
//													register("example_surface",
//															new ExampleBiomeSurfaceBuilder(
//																	SurfaceBuilderConfig::deserialize)),
//													new SurfaceBuilderConfig(Blocks.COARSE_DIRT.getDefaultState(),
//															Blocks.DIRT.getDefaultState(),
//															Blocks.DIRT.getDefaultState())))
//									.category(Category.PLAINS).downfall(0.5f).depth(0.12f).parent(null)
                            new Biome.Builder()
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

    public static void registerBiomes() {

        registerBiome(EXAMPLE_BIOME.get(), BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.OVERWORLD);
    }

    private static void registerBiome(Biome biome, BiomeDictionary.Type... types) {
        // the line below will make it spawn in the overworld
        BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(biome, 100));
        BiomeDictionary.addTypes(biome, types);
        BiomeManager.addSpawnBiome(biome);
    }

    @SuppressWarnings("deprecation")
    private static <C extends ISurfaceBuilderConfig, F extends SurfaceBuilder<C>> F register(String key, F builderIn) {
        return (F) (Registry.<SurfaceBuilder<?>>register(Registry.SURFACE_BUILDER, key, builderIn));
    }
}
