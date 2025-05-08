package com.elly.athena.tabs;

import com.elly.athena.blockitem.BlockItems_Register;
import com.elly.athena.item.Item_Register;
import net.minecraft.core.registries.Registries;
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

import static com.elly.athena.Athena.MODID;

public class CreativeTabs_Register {
    static class TabsCategory {
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

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);
    private static List<TabsCategory> categories = new ArrayList<>();
    private static HashMap<String, Supplier<CreativeModeTab>> RegisterDict = new HashMap<>();

    public static void RegisterAllTabs()
    {
        CreativeTabs_Register();
        for(TabsCategory i: categories){
            Supplier<BlockItem>[] blockitems = new Supplier[i.BlockItem_ids.length];
            Supplier<Item>[] items = new Supplier[i.Item_ids.length];
            for(int y = 0; y < i.BlockItem_ids.length; y++){
                blockitems[y] = BlockItems_Register.RegisterDict.get(i.BlockItem_ids[y]);
            }
            for(int y = 0; y < i.Item_ids.length; y++){
                items[y] = Item_Register.RegisterDict.get(i.Item_ids[y]);
            }

            Supplier<CreativeModeTab> buffer = CREATIVE_MODE_TABS.register(i.Tab_id, () -> CreativeModeTab.builder()
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

    public static void CreativeTabs_Register() {
        RegisterDict = new HashMap<String, Supplier<CreativeModeTab>>();
        categories = new ArrayList<TabsCategory>();
        categories.add(new TabsCategory(
                new String[]{
                        "symmetric_anchor"
                },
                new String[]{
                        "hp_potion",
                        "hp_potion_large",
                        "mp_potion",
                        "mp_potion_large",
                        "elixir",
                        "power_elixir"
                },
                "athena_use", "Athena Use"
        ));
        categories.add(new TabsCategory(
                new String[]{

                },
                new String[]{
                        "sword",
                        "spear",
                        "wand",
                        "staff",
                        "bow.json",
                },
                "athena_weapon", "Athena Weapon"
        ));
        categories.add(new TabsCategory(
                new String[]{

                },
                new String[]{
                        "skill.speed_boost",
                        "skill.hp_boost",
                        "skill.wind_slash",
                },
                "athena_skill", "Athena Skill"
        ));
    }
}
