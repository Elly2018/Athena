package com.elly.rpg.tabs;

import com.elly.rpg.block.Blocks_Register;
import com.elly.rpg.blockitem.BlockItems_Register;
import com.elly.rpg.item.Item_Register;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;

public class CreativeTabs_Register {

    public static class RegisterCollection {
        public final Blocks_Register _Blocks_Register;
        public final BlockItems_Register _BlockItems_Register;
        public final Item_Register _Item_Register;

        public RegisterCollection(Blocks_Register blocksRegister, BlockItems_Register blockItemsRegister, Item_Register itemRegister) {
            _Blocks_Register = blocksRegister;
            _BlockItems_Register = blockItemsRegister;
            _Item_Register = itemRegister;
        }
    }

    private final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS;
    private RegistryObject<CreativeModeTab> example_tab;

    public CreativeTabs_Register(DeferredRegister<CreativeModeTab> _CREATIVE_MODE_TABS) {
        this.CREATIVE_MODE_TABS = _CREATIVE_MODE_TABS;
    }

    public void RegisterAllTabs(RegisterCollection collection)
    {
        RegistryObject<Item> hp = collection._Item_Register.RegisterDict.get("hp_potion");
        RegistryObject<Item> mp = collection._Item_Register.RegisterDict.get("mp_potion");
        RegistryObject<BlockItem> sa = collection._BlockItems_Register.RegisterDict.get("symmetric_anchor");

        this.example_tab = this.CREATIVE_MODE_TABS.register("example_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("YES"))
            .icon(Items.CAKE::getDefaultInstance)
            .displayItems((parameters, output) -> {
                if(hp != null) output.accept(hp.get());
                if(mp != null) output.accept(mp.get());
                if(sa != null) output.accept(sa.get());
            }).build());
    }
}
