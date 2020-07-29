package ai.scintillia.init;

import ai.scintillia.MeteoCraft;
import ai.scintillia.objects.blocks.Meteorite1;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ItemTabInit extends ItemGroup {
    public static final Block meteorite_1=new Meteorite1(Block.Properties.create(Material.IRON));
    public static final ItemGroup instance = new ItemTabInit();

    private ItemTabInit() {
        super("Meteocraft");
    }

    @Override
    public ItemStack createIcon() {
        ItemStack stack = new ItemStack(meteorite_1);
//        stack.addEnchantment(Enchantment.getEnchantmentByID(0), 1);
        return stack;
    }
}
