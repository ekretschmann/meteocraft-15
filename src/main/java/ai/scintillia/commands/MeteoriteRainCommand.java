package ai.scintillia.commands;
import ai.scintillia.MeteoCraft;
import ai.scintillia.init.BlockInit;
import ai.scintillia.world.biomes.Antarctic;
import com.mojang.brigadier.CommandDispatcher;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SnowyDirtBlock;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.BlockPosArgument;
import net.minecraft.command.arguments.BlockStateArgument;
import net.minecraft.command.arguments.BlockStateInput;
import net.minecraft.inventory.IClearable;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.CachedBlockInfo;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.Random;
import java.util.function.Predicate;

public class MeteoriteRainCommand {
    private static final SimpleCommandExceptionType FAILED_EXCEPTION = new SimpleCommandExceptionType(new TranslationTextComponent("commands.meteorite_rain.success"));

    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(Commands.literal("mrain").requires((commandSource) -> {
            return commandSource.hasPermissionLevel(2);
        }).then(Commands.argument("pos", BlockPosArgument.blockPos()).executes((commandSource) -> {
            return setMeteorite(commandSource.getSource(), BlockPosArgument.getLoadedBlockPos(commandSource, "pos"),   (Predicate<CachedBlockInfo>)null);
        }))
        );
    }


    Biome Antarctic = new Antarctic(new Biome.Builder());

    private static int setMeteorite(CommandSource source, BlockPos pos, @Nullable Predicate<CachedBlockInfo> predicate) throws CommandSyntaxException {
        ServerWorld serverworld = source.getWorld();

        if (predicate != null && !predicate.test(new CachedBlockInfo(serverworld, pos, true))) {
            throw FAILED_EXCEPTION.create();
        }
        else {
            int loopBreaker=0;
            boolean inAntarctic=false;
            if(source.getWorld().getBiome(new BlockPos(pos)).toString().toLowerCase().contains("antarctic")  ){
                inAntarctic=true;
            }
            while(inAntarctic){
              pos =  pos.offset(Direction.WEST);
                if(loopBreaker++>3){
                    inAntarctic=false;
                }

//                source.asPlayer().world.setBlockState(pos, Blocks.BEDROCK.getDefaultState());
                    place(source.getWorld(),pos);

            }
            source.getWorld().getBiome(new BlockPos(pos));
            source.sendFeedback(new TranslationTextComponent(source.getWorld().getBiome(new BlockPos(pos)).toString()),true);

            return 1;

        }
    }

    public enum Mode {
        DESTROY;
    }

    public static boolean place(IWorld worldIn, BlockPos pos) {
        BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();
        BlockPos.Mutable blockpos$mutable1 = new BlockPos.Mutable();

        for(int i = 0; i < 2; ++i) {
            for(int j = 0; j < 2; ++j) {
                int k = pos.getX() + i;
                int l = pos.getZ() + j;
                int i1 = worldIn.getHeight(Heightmap.Type.MOTION_BLOCKING, k, l);
                blockpos$mutable.setPos(k, i1, l);
                blockpos$mutable1.setPos(blockpos$mutable);
                BlockState blockstate = worldIn.getBlockState(blockpos$mutable1);
                if(blockstate.getBlock()==Blocks.SNOW) {
                    worldIn.setBlockState(blockpos$mutable, BlockInit.meteorite_1.getDefaultState(), 2);
                }

//                if(blockstate.isAir(worldIn,pos)) {
//                    do {
//                        pos.add(0, -1, 0);
//
//                    } while (blockstate.isAir(worldIn, pos));
//                    worldIn.setBlockState(blockpos$mutable, BlockInit.meteorite_1.getDefaultState(), 2);
//                }
            }
        }

        return true;
    }
//    private static final DynamicCommandExceptionType UNKNOWN_COLOR = new DynamicCommandExceptionType(color -> {
//        return new TranslationTextComponent("commands.hello.unknown", color);
//    });
//
//    private static final SuggestionProvider<CommandSource> SUGGEST_COLOR = (source, builder) -> {
//        return ISuggestionProvider.suggest(TextFormatting.getValidValues(true, false).stream(), builder);
//    };
//
//    public static void register(CommandDispatcher<CommandSource> dispatcher) {
//        dispatcher.register(Commands.literal("hello").executes(source -> {
//            return hello(source.getSource(), source.getSource().asPlayer());
//        }).then(Commands.argument("target", EntityArgument.player()).executes(source -> {
//            return hello(source.getSource(), EntityArgument.getPlayer(source, "target"));
//        }).then(Commands.argument("color", StringArgumentType.string()).suggests(SUGGEST_COLOR).executes(source -> {
//            return hello(source.getSource(), EntityArgument.getPlayer(source, "target"), StringArgumentType.getString(source, "color"));
//        }))));
//    }
//
//    private static int hello(CommandSource source, PlayerEntity player) {
//        source.sendFeedback(new TranslationTextComponent("commands.hello", player.getDisplayName()), true);
//        return 1;
//    }
//
//    private static int hello(CommandSource source, PlayerEntity player, String color) throws CommandSyntaxException {
//        if(TextFormatting.getValueByName(color) == null) {
//            throw UNKNOWN_COLOR.create(color);
//        }
//        source.sendFeedback(new TranslationTextComponent("commands.hello.color", TextFormatting.getValueByName(color).toString(), player.getDisplayName()), true);
//        return 1;
//    }
}