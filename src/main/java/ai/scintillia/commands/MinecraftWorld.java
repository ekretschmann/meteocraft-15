package ai.scintillia.commands;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class MinecraftWorld implements Plane {

    private final World world;

    @Nonnull
    @Override
    public String getBiome(long x, long y) {
        return world.getBiome(new BlockPos(x,70, y)).toString();
    }

    public MinecraftWorld(World world){
        this.world=world;
    }


}
