package ai.scintillia.world.biomes;

import com.mojang.datafixers.Dynamic;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

import java.util.Random;
import java.util.function.Function;

public class ColdDesertSurfaceBuilder extends SurfaceBuilder<SurfaceBuilderConfig> {

    public ColdDesertSurfaceBuilder(Function<Dynamic<?>, ? extends SurfaceBuilderConfig> function) {
        super(function);
    }

    @Override
    public void buildSurface(Random random, IChunk chunkIn, Biome biomeIn, int x, int z, int startHeight, double noise,
                             BlockState defaultBlock, BlockState defaultFluid, int seaLevel, long seed, SurfaceBuilderConfig config) {
        Random rd = new Random();
        int i = rd.nextInt(100);
        if (0<i && i<=20 ) {
            SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock,
                    defaultFluid, seaLevel, seed,
                    new SurfaceBuilderConfig(Blocks.SNOW_BLOCK.getDefaultState(),
                            Blocks.SNOW_BLOCK.getDefaultState(), Blocks.ACACIA_PLANKS.getDefaultState()));
        } else if(20<i && i<40 ) {
            SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock,
                    defaultFluid, seaLevel, seed,
                    new SurfaceBuilderConfig(Blocks.BLUE_ICE.getDefaultState(),
                            Blocks.PACKED_ICE.getDefaultState(), Blocks.PACKED_ICE.getDefaultState()));
        }
        else if(40<i && i<=60){SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock,
                defaultFluid, seaLevel, seed,
                new SurfaceBuilderConfig(Blocks.BLUE_ICE.getDefaultState(),
                        Blocks.SNOW_BLOCK.getDefaultState(), Blocks.PACKED_ICE.getDefaultState()));

        }
        else {SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock,
                defaultFluid, seaLevel, seed,
                new SurfaceBuilderConfig(Blocks.SNOW_BLOCK.getDefaultState(),
                        Blocks.BLUE_ICE.getDefaultState(), Blocks.PACKED_ICE.getDefaultState()));

        }
    }

}
