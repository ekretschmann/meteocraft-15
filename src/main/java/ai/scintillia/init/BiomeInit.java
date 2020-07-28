package ai.scintillia.init;

import ai.scintillia.MeteoCraft;
import ai.scintillia.world.biomes.Antarctic;
import ai.scintillia.world.biomes.AntarcticFrozenLake;
import ai.scintillia.world.biomes.LowErosionDesert;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BiomeInit {
    public static final DeferredRegister<Biome> BIOMES = new DeferredRegister<>(ForgeRegistries.BIOMES,
            MeteoCraft.MODID);

    public static final RegistryObject<Biome> ANTARCTIC = BIOMES
            .register("antarctic",
                    () -> new Antarctic(new Biome.Builder()));

    public static final RegistryObject<Biome> LOW_EROSION_DESERT = BIOMES
            .register("low_erosion_desert",
                    () -> new LowErosionDesert(new Biome.Builder()));
    public static final RegistryObject<Biome> ANTARCTIC_FROZEN_LAKE = BIOMES
            .register("antarctic_frozen_lake",
                    () -> new AntarcticFrozenLake(new Biome.Builder()));

    public static void registerBiomes() {
        registerBiome(ANTARCTIC.get(), BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.OVERWORLD);
        registerBiome(LOW_EROSION_DESERT.get(), BiomeDictionary.Type.SANDY, BiomeDictionary.Type.OVERWORLD);
        registerBiome(ANTARCTIC_FROZEN_LAKE.get(), BiomeDictionary.Type.RIVER, BiomeDictionary.Type.OVERWORLD);

    }

    private static void registerBiome(Biome biome, BiomeDictionary.Type... types) {

        BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(biome, 100));
        BiomeDictionary.addTypes(biome, types);
        BiomeManager.addSpawnBiome(biome);
    }

    @SuppressWarnings("deprecation")
    private static <C extends ISurfaceBuilderConfig, F extends SurfaceBuilder<C>> F register(String key, F builderIn) {
        return (F) (Registry.<SurfaceBuilder<?>>register(Registry.SURFACE_BUILDER, key, builderIn));
    }
}
