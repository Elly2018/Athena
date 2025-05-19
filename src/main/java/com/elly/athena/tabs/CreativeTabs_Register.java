package com.elly.athena.tabs;

import com.elly.athena.block.Blocks_Register;
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
        public final Supplier<BlockItem>[] BlockItem_ids;
        public final Supplier<Item>[] Item_ids;
        public final String Tab_id;
        public final String Tab_Display;

        public TabsCategory(Supplier<BlockItem>[] blockItemIds, Supplier<Item>[] itemIds, String tabId, String tabDisplay) {
            BlockItem_ids = blockItemIds;
            Item_ids = itemIds;
            Tab_id = tabId;
            Tab_Display = tabDisplay;
        }
    }

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);
    private static List<TabsCategory> categories = new ArrayList<>();
    private static HashMap<String, Supplier<CreativeModeTab>> RegisterDict = new HashMap<>();

    public static void CreativeTabs_Register() {
        RegisterDict = new HashMap<String, Supplier<CreativeModeTab>>();
        categories = new ArrayList<TabsCategory>();
        categories.add(new TabsCategory(
                new Supplier[]{
                        Blocks_Register.MARKET_BLOCK.getB()
                },
                new Supplier[]{
                        Item_Register.BOOK_COMMOM,
                        Item_Register.BOOK_WARRIOR,
                        Item_Register.BOOK_MAGICIAN,
                        Item_Register.BOOK_MAGICIAN,
                        Item_Register.POTION_HP,
                        Item_Register.POTION_HP_LARGE,
                        Item_Register.POTION_MP,
                        Item_Register.POTION_MP_LARGE,
                        Item_Register.POTION_ELIXIR,
                        Item_Register.POTION_POWER_ELIXIR,
                },
                "athena_use", "Athena Use"
        ));
        categories.add(new TabsCategory(
                new Supplier[]{},
                new Supplier[]{
                        Item_Register.BELT_LEATHER,
                        Item_Register.CAPE_OLDCAPE,
                        Item_Register.FACE_MASK,
                        Item_Register.FACE_HEADBAND,
                        Item_Register.GLOVE_KNIGHT,
                        Item_Register.GLOVE_SHINY,
                        Item_Register.NECKLACE_CHEAP,
                        Item_Register.NECKLACE_EVIL,
                        Item_Register.NECKLACE_GHOST,
                        Item_Register.NECKLACE_LAVA,
                        Item_Register.NECKLACE_NATURE,
                        Item_Register.WBIELUCK_NEWBIE,
                        Item_Register.NECKLACE_ORCS,
                        Item_Register.NECKLACE_SHELL,
                        Item_Register.NECKLACE_TRIBE,
                        Item_Register.NECKLACE_WOODELF,
                        Item_Register.NECKLACE_YGGDRASILL,
                        Item_Register.RING_CHAOS,
                        Item_Register.RING_COPPER,
                        Item_Register.RING_DRAGON,
                        Item_Register.RING_GEMBLUE,
                        Item_Register.RING_GEMGREEN,
                        Item_Register.RING_GEMGREY,
                        Item_Register.RING_GEMLIME,
                        Item_Register.RING_GEMPINK,
                        Item_Register.RING_GEMPURPLE,
                        Item_Register.RING_GEMRED,
                        Item_Register.RING_GEMSKY,
                        Item_Register.RING_GOLDEN,
                        Item_Register.RING_HOLY,
                        Item_Register.RING_MAGIC,
                        Item_Register.RING_SECRET,
                        Item_Register.RING_SILVER,
                        Item_Register.RING_SINGLE,
                        Item_Register.RING_UNDEAD,
                        Item_Register.RING_YGGDRASILL,
                        Item_Register.ORB_NATURE,
                        Item_Register.ORB_EVIL,
                        Item_Register.ORB_SKY,
                },
                "athena_equipment", "Athena Equipment"
        ));
        categories.add(new TabsCategory(
                new Supplier[]{},
                new Supplier[]{
                        Item_Register.WEAPON_CANDY,
                        Item_Register.WEAPON_BOW,
                        Item_Register.WEAPON_BOW2,
                        Item_Register.WEAPON_BOW_Element,
                        Item_Register.WEAPON_BOW_Moon,
                        Item_Register.WEAPON_SWORD,
                        Item_Register.WEAPON_SPEAR,
                        Item_Register.WEAPON_WAND,
                        Item_Register.WEAPON_STAFF,
                },
                "athena_weapon", "Athena Weapon"
        ));
        categories.add(new TabsCategory(
                new Supplier[]{},
                new Supplier[]{
                        Item_Register.SKILL_HEALING,
                        Item_Register.SKILL_SPEEDBOOST,
                        Item_Register.SKILL_HPBOOST,
                        Item_Register.SKILL_WINDSLASH,
                        Item_Register.SKILL_PHALANX,
                        Item_Register.SKILL_MAGICBALL,
                },
                "athena_skill", "Athena Skill"
        ));
        categories.add(new TabsCategory(
                new Supplier[]{},
                new Supplier[]{
                        Item_Register.SPECIAL_NPCSTAFF,
                        Item_Register.SPECIAL_QUESTSTAFF,
                        Item_Register.SPECIAL_TELEPORTSTAFF,
                        Item_Register.SPECIAL_ZONESTAFF,
                },
                "athena_op", "Athena OP"
        ));
    }

    public static void RegisterAllTabs()
    {
        CreativeTabs_Register();
        for(TabsCategory i: categories){
            Supplier<BlockItem>[] blockitems = new Supplier[i.BlockItem_ids.length];
            Supplier<Item>[] items = new Supplier[i.Item_ids.length];
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
}
