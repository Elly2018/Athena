package com.elly.rpg.tabs;

import com.elly.rpg.block.Blocks_Register;
import com.elly.rpg.blockitem.BlockItems_Register;
import com.elly.rpg.item.Item_Register;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    public class TabsCategory {
        public final String[] BlockItem_ids;
        public final String[] Item_ids;
        public final String Tab_id;
        public final String Tab_Display;

        public TabsCategory(String[] blockItemIds, String[] itemIds, String tabId, String tabDisplay) {
            BlockItem_ids = blockItemIds;
            Item_ids = itemIds;
            Tab_id = tabId;
            Tab_Display = tabDisplay;
        }
    }

    private final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS;
    private final List<TabsCategory> categories;
    private final HashMap<String, RegistryObject<CreativeModeTab>> example_tab;

    public CreativeTabs_Register(DeferredRegister<CreativeModeTab> _CREATIVE_MODE_TABS) {
        this.CREATIVE_MODE_TABS = _CREATIVE_MODE_TABS;
        this.example_tab = new HashMap<String, RegistryObject<CreativeModeTab>>();
        this.categories = new ArrayList<TabsCategory>();
        this.categories.add(new TabsCategory(
                new String[] { "symmetric_anchor" },
                new String[] { "hp_potion", "mp_potion" },
                "rpg_", "RPG Use"));
    }

    public void RegisterAllTabs(RegisterCollection collection)
    {
        for(TabsCategory i: categories){
            RegistryObject<BlockItem>[] blockitems = new RegistryObject[i.BlockItem_ids.length];
            RegistryObject<Item>[] items = new RegistryObject[i.Item_ids.length];
            for(int y = 0; y < i.BlockItem_ids.length; y++){
                blockitems[y] = collection._BlockItems_Register.RegisterDict.get(i.BlockItem_ids[y]);
            }
            for(int y = 0; y < i.Item_ids.length; y++){
                items[y] = collection._Item_Register.RegisterDict.get(i.Item_ids[y]);
            }

            RegistryObject<CreativeModeTab> buffer = this.CREATIVE_MODE_TABS.register(i.Tab_id, () -> CreativeModeTab.builder()
                .title(Component.translatable(i.Tab_Display))
                .icon(Items.CAKE::getDefaultInstance)
                .displayItems((parameters, output) -> {
                    for(RegistryObject<BlockItem> y: blockitems){
                        if(y != null) output.accept(y.get());
                    }
                    for(RegistryObject<Item> y: items){
                        if(y != null) output.accept(y.get());
                    }
                }).build());
            example_tab.put(i.Tab_id, buffer);
        }
    }
}
