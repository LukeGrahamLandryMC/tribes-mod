package io.github.lukegrahamlandry.tribes.init;

import io.github.lukegrahamlandry.tribes.TribesMain;
import io.github.lukegrahamlandry.tribes.client.CreateTribeScreen;
import io.github.lukegrahamlandry.tribes.client.JoinTribeScreen;
import io.github.lukegrahamlandry.tribes.client.TribeEffectScreen;
import io.github.lukegrahamlandry.tribes.item.GUIItem;
import io.github.lukegrahamlandry.tribes.item.TribeCompass;
import net.minecraft.item.*;
import net.minecraft.tileentity.BannerPattern;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TribesMain.MOD_ID);

    public static RegistryObject<Item> TRIBE_COMPASS = ITEMS.register("tribe_compass", () -> new TribeCompass(new Item.Properties().group(ItemGroup.MISC)));
    public static RegistryObject<Item> ALTER = ITEMS.register("alter", () -> new BlockItem(BlockInit.ALTER.get(), new Item.Properties().group(ItemGroup.MISC)));
}
