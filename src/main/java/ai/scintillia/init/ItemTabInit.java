package ai.scintillia.init;

import ai.scintillia.MeteoCraft;
import ai.scintillia.objects.blocks.Meteorite1;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ItemTabInit extends ItemGroup {
    public static final ItemGroup instance = new ItemTabInit();

    private ItemTabInit() {
        super("Meteocraft");
    }

    @Override
    public ItemStack createIcon() {
        ItemStack stack = new ItemStack(Blocks.PUMPKIN);
        stack.addEnchantment(Enchantment.getEnchantmentByID(0), 1);
        return stack;
    }
}
