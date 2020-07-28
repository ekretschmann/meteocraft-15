package ai.scintillia.objects.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.DirectionalBlock;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;

public class Meteorite1 extends Block {
    // for this model, we're making the shape match the block model exactly - see assets\minecraftbyexample\models\block\mbe02_block_partial_model.json
    private static final Vec3d BASE_MIN_CORNER = new Vec3d(2.0, 0.0, 1.0);
    private static final Vec3d BASE_MAX_CORNER = new Vec3d(14.0, 7.0, 16.0);
    private static final Vec3d PILLAR_MIN_CORNER = new Vec3d(1.0, 7.0, 4.0);
    private static final Vec3d PILLAR_MAX_CORNER = new Vec3d(9.0, 10.0, 11.0);

    private static final VoxelShape BASE = Block.makeCuboidShape(BASE_MIN_CORNER.getX(), BASE_MIN_CORNER.getY(), BASE_MIN_CORNER.getZ(),
            BASE_MAX_CORNER.getX(), BASE_MAX_CORNER.getY(), BASE_MAX_CORNER.getZ());
    private static final VoxelShape PILLAR = Block.makeCuboidShape(PILLAR_MIN_CORNER.getX(), PILLAR_MIN_CORNER.getY(), PILLAR_MIN_CORNER.getZ(),
            PILLAR_MAX_CORNER.getX(), PILLAR_MAX_CORNER.getY(), PILLAR_MAX_CORNER.getZ());

    private static VoxelShape COMBINED_SHAPE = VoxelShapes.or(BASE, PILLAR);  // use this method to add two shapes together

    private static VoxelShape EMPTY_SPACE = VoxelShapes.combineAndSimplify(VoxelShapes.fullCube(), COMBINED_SHAPE, IBooleanFunction.ONLY_FIRST);
    // use this method if you need to make "holes"; eg in this case we are making a VoxelShape for the empty (non-solid) space in this block
    // Vanilla uses this to (eg) make a cavity in a composter block or cauldron.

    public Meteorite1(Properties properties) {
        super(properties.create(Material.ROCK)
                        .hardnessAndResistance(0,50)

        );


    }

    // render using a BakedModel (mbe02_block_partial.json --> mbe02_block_partial_model.json)
    // not strictly required because the default (super method) is BlockRenderType.MODEL
    @Override
    public BlockRenderType getRenderType(BlockState blockState) {
        return BlockRenderType.MODEL;
    }

    // returns the shape of the block:
    //  The image that you see on the screen (when a block is rendered) is determined by the block model (i.e. the model json file).
    //  But Minecraft also uses a number of other "shapes" to control the interaction of the block with its environment and with the player.
    // See  https://greyminecraftcoder.blogspot.com/2020/02/block-shapes-voxelshapes-1144.html
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return COMBINED_SHAPE;
    }


}

