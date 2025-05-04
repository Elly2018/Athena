package com.elly.athena.tabs;

import com.elly.athena.block.Blocks_Register;
import com.elly.athena.blockitem.BlockItems_Register;
import com.elly.athena.item.Item_Register;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Supplier;

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
    private final HashMap<String, Supplier<CreativeModeTab>> RegisterDict;

    public CreativeTabs_Register(DeferredRegister<CreativeModeTab> _CREATIVE_MODE_TABS) {
        this.CREATIVE_MODE_TABS = _CREATIVE_MODE_TABS;
        this.RegisterDict = new HashMap<String, Supplier<CreativeModeTab>>();
        this.categories = new ArrayList<TabsCategory>();
        this.categories.add(new TabsCategory(
                new String[] {
                        "symmetric_anchor"
                },
                new String[] {
                        "hp_potion",
                        "hp_potion_large",
                        "mp_potion",
                        "mp_potion_large",
                        "elixir",
                        "power_elixir"
                },
                "rpg_use", "RPG Use"
            )
        );
    }

    public void RegisterAllTabs(RegisterCollection collection)
    {
        for(TabsCategory i: categories){
            Supplier<BlockItem>[] blockitems = new Supplier[i.BlockItem_ids.length];
            Supplier<Item>[] items = new Supplier[i.Item_ids.length];
            for(int y = 0; y < i.BlockItem_ids.length; y++){
                blockitems[y] = collection._BlockItems_Register.RegisterDict.get(i.BlockItem_ids[y]);
            }
            for(int y = 0; y < i.Item_ids.length; y++){
                items[y] = collection._Item_Register.RegisterDict.get(i.Item_ids[y]);
            }

            Supplier<CreativeModeTab> buffer = this.CREATIVE_MODE_TABS.register(i.Tab_id, () -> CreativeModeTab.builder()
                .title(Component.translatable(i.Tab_Display))
                .icon(Items.CAKE::getDefaultInstance)
                .displayItems((parameters, output) -> {
                    for(Supplier<BlockItem> y: blockitems){
                        if(y != null) output.accept(y.get());
                    }
                    for(Supplier<Item> y: items){
                        if(y != null) output.accept(y.get());
                    }
                }).build());
            RegisterDict.put(i.Tab_id, buffer);
        }
    }
}
