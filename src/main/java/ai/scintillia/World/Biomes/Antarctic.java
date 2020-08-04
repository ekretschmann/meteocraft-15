package ai.scintillia.world.biomes;

import ai.scintillia.init.FeatureInit;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.structure.MineshaftConfig;
import net.minecraft.world.gen.feature.structure.MineshaftStructure;
import net.minecraft.world.gen.feature.structure.VillageConfig;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.HeightWithChanceConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class Antarctic extends Biome {


    public Antarctic(Builder biomeBuilder) {
        super(biomeBuilder
                .surfaceBuilder(
                        new ConfiguredSurfaceBuilder<SurfaceBuilderConfig>(
                                register("cold_desert_icy",
                                        new ColdDesertSurfaceBuilder(
                                                SurfaceBuilderConfig::deserialize)),
                                new SurfaceBuilderConfig(Blocks.SNOW_BLOCK.getDefaultState(),
                                        Blocks.SNOW_BLOCK.getDefaultState(),
                                        Blocks.DIRT.getDefaultState())))

                .precipitation(Biome.RainType.SNOW)
                .category(Category.ICY)
                .depth(0.000125F)
                .scale(0.0005F)
                .temperature(0.0f)
                .downfall(0.5f)
                .waterColor(53247)
                .waterFogColor(9359359)
                .parent(null));

//        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(EntityType.BEE, 20, 20, 30));

        this.addCarver(GenerationStage.Carving.AIR,
                Biome.createCarver(WorldCarver.CAVE, new ProbabilityConfig(0.14285715F)));
        this.addCarver(GenerationStage.Carving.AIR,
                Biome.createCarver(WorldCarver.CANYON, new ProbabilityConfig(0.04F)));


        this.addStructure(Feature.MINESHAFT.withConfiguration(new MineshaftConfig(0.004D, MineshaftStructure.Type.NORMAL)));
        this.addStructure(Feature.STRONGHOLD.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));

        this.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, Feature.LAKE.withConfiguration(new BlockStateFeatureConfig(Blocks.WATER.getDefaultState())).withPlacement(Placement.WATER_LAKE.configure(new ChanceConfig(4))));
        DefaultBiomeFeatures.addOres(this);
        DefaultBiomeFeatures.addExtraGoldOre(this);
        DefaultBiomeFeatures.addExtraEmeraldOre(this);
        DefaultBiomeFeatures.addIcebergs(this);
        DefaultBiomeFeatures.addBlueIce(this);
        DefaultBiomeFeatures.addFreezeTopLayer(this);
        DefaultBiomeFeatures.addFossils(this);
        DefaultBiomeFeatures.addIcebergs(this);

        DefaultBiomeFeatures.addStructures(this);
        DefaultBiomeFeatures.addTaigaRocks(this);


//        FeatureInit.addExampleFeature(this, 10);


    }



    @Override
    public int getGrassColor(double posX, double posZ) {
        return 0xFF0000;
    }

    @SuppressWarnings("deprecation")
    private static <C extends ISurfaceBuilderConfig, F extends SurfaceBuilder<C>> F register(String key, F builderIn) {
        return (F) (Registry.<SurfaceBuilder<?>>register(Registry.SURFACE_BUILDER, key, builderIn));
    }
}