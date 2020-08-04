package ai.scintillia.commands;


import ai.scintillia.init.BlockInit;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.BlockPosArgument;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.server.ServerWorld;
import java.util.List;

public class MeteoriteRainCommand {


    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(Commands.literal("mrain").requires((commandSource) ->
                commandSource.hasPermissionLevel(2))
        .then(Commands.argument("pos", BlockPosArgument.blockPos()).executes((commandSource) ->
                setMeteorite(commandSource.getSource(), BlockPosArgument.getLoadedBlockPos(commandSource, "pos")))
        ));
    }


    private static int setMeteorite(CommandSource source, BlockPos pos) {

        ServerWorld serverworld = source.getWorld();


        List<MeteoritePlacer.Coordinate> coordinateList = null;

        if(serverworld.getBiome(pos).toString().contains("Antarctic")) {
            coordinateList=MeteoritePlacer.placeMeteorites(pos.getX(), pos.getZ(), "Antarctic", new MinecraftWorld(serverworld), 0.001f);
        }
        if(serverworld.getBiome(pos).toString().contains("AntarcticFrozenLake")){
            coordinateList=MeteoritePlacer.placeMeteorites(pos.getX(), pos.getZ(), "AntarcticFrozenLake", new MinecraftWorld(serverworld), 0.001f);
        }
        if(serverworld.getBiome(pos).toString().contains("LowErosionDesert")){
            coordinateList=MeteoritePlacer.placeMeteorites(pos.getX(), pos.getZ(), "LowErosionDesert", new MinecraftWorld(serverworld), 0.001f);
        }

        for (MeteoritePlacer.Coordinate c: coordinateList) {

            place(source.getWorld(),new BlockPos(c.x,240,c.y)); // y doesn't matter at all here because it gets changed in the place method
        }

        source.sendFeedback(new TranslationTextComponent("Meteorites have fallen"),true);
        return 1;
        }


    public static boolean place(IWorld worldIn, BlockPos pos) {

        BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();

                int x = pos.getX() ;
                int z = pos.getZ() ;
                int y = worldIn.getHeight(Heightmap.Type.MOTION_BLOCKING, x, z);

                blockpos$mutable.setPos(x, y, z);


        worldIn.setBlockState(blockpos$mutable, BlockInit.meteorite_1.getDefaultState(), 2);
        return true;
    }

}