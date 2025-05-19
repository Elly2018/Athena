package com.elly.athena.item;

import com.elly.athena.item.entity.Entity_MagicBall;
import com.elly.athena.item.equipment.belt.LeatherBelt;
import com.elly.athena.item.equipment.cape.OldCape;
import com.elly.athena.item.equipment.face.Headband;
import com.elly.athena.item.equipment.face.Mask;
import com.elly.athena.item.equipment.glove.KnightGlove;
import com.elly.athena.item.equipment.glove.ShinyGlove;
import com.elly.athena.item.equipment.neck.*;
import com.elly.athena.item.equipment.orb.EvilOrb;
import com.elly.athena.item.equipment.orb.NatureOrb;
import com.elly.athena.item.equipment.orb.SkyOrb;
import com.elly.athena.item.equipment.ring.*;
import com.elly.athena.item.etc.Coin;
import com.elly.athena.item.etc.CoinBag;
import com.elly.athena.item.etc.GoldenCoin;
import com.elly.athena.item.etc.GoldenCoinBag;
import com.elly.athena.item.skill.commom.Healing;
import com.elly.athena.item.skill.commom.SpeedBoost;
import com.elly.athena.item.skill.magician.MagicBall;
import com.elly.athena.item.skill.warrior.HPBoost;
import com.elly.athena.item.skill.warrior.Phalanx;
import com.elly.athena.item.skill.warrior.WindSlash;
import com.elly.athena.item.special.quest.QuestStaff;
import com.elly.athena.item.special.setting.NPCStaff;
import com.elly.athena.item.special.setting.TeleportStaff;
import com.elly.athena.item.special.setting.ZoneStaff;
import com.elly.athena.item.use.book.ArcherSkillBook;
import com.elly.athena.item.use.book.CommonSkillBook;
import com.elly.athena.item.use.book.MagicianSkillBook;
import com.elly.athena.item.use.book.WarriorSkillBook;
import com.elly.athena.item.use.potion.*;
import com.elly.athena.item.weapon.archer.Bow;
import com.elly.athena.item.weapon.archer.Bow2;
import com.elly.athena.item.weapon.archer.ElementBow;
import com.elly.athena.item.weapon.archer.MoonBow;
import com.elly.athena.item.weapon.common.CandyStaff;
import com.elly.athena.item.weapon.magician.Staff;
import com.elly.athena.item.weapon.magician.Wand;
import com.elly.athena.item.weapon.warrior.Spear;
import com.elly.athena.item.weapon.warrior.Sword;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.Potion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Supplier;

import static com.elly.athena.Athena.ITEMS;
import static com.elly.athena.Athena.MODID;

public class Item_Register {

    public interface ItemRegisterData {
        String get_key();
        Item.Properties get_behaviour();
        Item get_binding(Item.Properties props);
    }

    public interface ItemRegisterData_Upgrade {
        String get_key();
        Item.Properties[] get_behaviours();
        Item get_binding(int index, Item.Properties props);
    }

    public static final int MAX_UPGRADE = 10;

    // System
    public static Supplier<Item> COIN;
    public static Supplier<Item> GOLDEN_COIN;
    public static Supplier<Item> COIN_BAG;
    public static Supplier<Item> GOLDEN_COIN_BAG;
    // Skill book
    public static Supplier<Item> BOOK_COMMOM;
    public static Supplier<Item> BOOK_WARRIOR;
    public static Supplier<Item> BOOK_MAGICIAN;
    public static Supplier<Item> BOOK_ARCHER;
    // Potion
    public static Supplier<Item> POTION_HP;
    public static Supplier<Item> POTION_HP_LARGE;
    public static Supplier<Item> POTION_MP;
    public static Supplier<Item> POTION_MP_LARGE;
    public static Supplier<Item> POTION_ELIXIR;
    public static Supplier<Item> POTION_POWER_ELIXIR;
    // Belt
    public static Supplier<Item> BELT_LEATHER;
    // Cape
    public static Supplier<Item> CAPE_OLDCAPE;
    // Mask
    public static Supplier<Item> FACE_MASK;
    public static Supplier<Item> FACE_HEADBAND;
    // Glove
    public static Supplier<Item> GLOVE_KNIGHT;
    public static Supplier<Item> GLOVE_SHINY;
    // Necklace
    public static Supplier<Item> NECKLACE_CHEAP;
    public static Supplier<Item> NECKLACE_EVIL;
    public static Supplier<Item> NECKLACE_GHOST;
    public static Supplier<Item> NECKLACE_LAVA;
    public static Supplier<Item> NECKLACE_NATURE;
    public static Supplier<Item> WBIELUCK_NEWBIE;
    public static Supplier<Item> NECKLACE_ORCS;
    public static Supplier<Item> NECKLACE_SHELL;
    public static Supplier<Item> NECKLACE_TRIBE;
    public static Supplier<Item> NECKLACE_WOODELF;
    public static Supplier<Item> NECKLACE_YGGDRASILL;
    // Ring
    public static Supplier<Item> RING_CHAOS;
    public static Supplier<Item> RING_COPPER;
    public static Supplier<Item> RING_DRAGON;
    public static Supplier<Item> RING_GEMBLUE;
    public static Supplier<Item> RING_GEMGREEN;
    public static Supplier<Item> RING_GEMGREY;
    public static Supplier<Item> RING_GEMLIME;
    public static Supplier<Item> RING_GEMPINK;
    public static Supplier<Item> RING_GEMPURPLE;
    public static Supplier<Item> RING_GEMRED;
    public static Supplier<Item> RING_GEMSKY;
    public static Supplier<Item> RING_GOLDEN;
    public static Supplier<Item> RING_HOLY;
    public static Supplier<Item> RING_MAGIC;
    public static Supplier<Item> RING_SECRET;
    public static Supplier<Item> RING_SILVER;
    public static Supplier<Item> RING_SINGLE;
    public static Supplier<Item> RING_UNDEAD;
    public static Supplier<Item> RING_YGGDRASILL;
    // Orb
    public static Supplier<Item> ORB_EVIL;
    public static Supplier<Item> ORB_SKY;
    public static Supplier<Item> ORB_NATURE;
    // Weapon common
    public static Supplier<Item> WEAPON_CANDY;
    // Weapon warrior
    public static Supplier<Item> WEAPON_SWORD;
    public static Supplier<Item> WEAPON_SPEAR;
    // Weapon magician
    public static ArrayList<Supplier<Item>> WEAPON_STAFF;
    public static Supplier<Item> WEAPON_WAND;
    // Weapon archer
    public static Supplier<Item> WEAPON_BOW;
    public static Supplier<Item> WEAPON_BOW2;
    public static Supplier<Item> WEAPON_BOW_Element;
    public static Supplier<Item> WEAPON_BOW_Moon;
    // Skill common
    public static Supplier<Item> SKILL_SPEEDBOOST;
    public static Supplier<Item> SKILL_HEALING;
    // Skill warrior
    public static Supplier<Item> SKILL_HPBOOST;
    public static Supplier<Item> SKILL_WINDSLASH;
    public static Supplier<Item> SKILL_PHALANX;
    // Skill magician
    public static Supplier<Item> SKILL_MAGICBALL;
    // Skill archer
    // Special
    public static Supplier<Item> SPECIAL_QUESTSTAFF;
    public static Supplier<Item> SPECIAL_NPCSTAFF;
    public static Supplier<Item> SPECIAL_TELEPORTSTAFF;
    public static Supplier<Item> SPECIAL_ZONESTAFF;
    // Entity
    public static Supplier<Item> ENTITY_MAGICBALL;

    public static void RegisterAllItems() {
        // System
        COIN = RegisterItem(new Coin());
        GOLDEN_COIN = RegisterItem(new GoldenCoin());
        COIN_BAG = RegisterItem(new CoinBag());
        GOLDEN_COIN_BAG = RegisterItem(new GoldenCoinBag());
        // Skill book
        BOOK_COMMOM = RegisterItem(new CommonSkillBook());
        BOOK_WARRIOR = RegisterItem(new WarriorSkillBook());
        BOOK_MAGICIAN = RegisterItem(new MagicianSkillBook());
        BOOK_ARCHER = RegisterItem(new ArcherSkillBook());
        // Potion
        POTION_HP = RegisterItem(new HP_Potion());
        POTION_HP_LARGE = RegisterItem(new HP_Potion_Large());
        POTION_MP = RegisterItem(new MP_Potion());
        POTION_MP_LARGE = RegisterItem(new MP_Potion_Large());
        POTION_ELIXIR = RegisterItem(new Elixir());
        POTION_POWER_ELIXIR = RegisterItem(new Power_Elixir());
        // Belt
        BELT_LEATHER = RegisterItem(new LeatherBelt());
        // Cape
        CAPE_OLDCAPE = RegisterItem(new OldCape());
        // Mask
        FACE_MASK = RegisterItem(new Mask());
        FACE_HEADBAND = RegisterItem(new Headband());
        // Glove
        GLOVE_KNIGHT = RegisterItem(new KnightGlove());
        GLOVE_SHINY = RegisterItem(new ShinyGlove());
        // necklace
        NECKLACE_CHEAP = RegisterItem(new CheapNecklace());
        NECKLACE_EVIL = RegisterItem(new EvilNecklace());
        NECKLACE_GHOST = RegisterItem(new GhostNecklace());
        NECKLACE_LAVA = RegisterItem(new LavaNecklace());
        NECKLACE_NATURE = RegisterItem(new NatureNecklace());
        WBIELUCK_NEWBIE = RegisterItem(new NewbieLuck());
        NECKLACE_ORCS = RegisterItem(new OrcsNecklace());
        NECKLACE_SHELL = RegisterItem(new ShellNecklace());
        NECKLACE_TRIBE = RegisterItem(new TribeNecklace());
        NECKLACE_WOODELF = RegisterItem(new WoodelfNecklace());
        NECKLACE_YGGDRASILL = RegisterItem(new YggdrasillNecklace());
        // Ring
        RING_CHAOS = RegisterItem(new ChaosRing());
        RING_COPPER = RegisterItem(new CopperRing());
        RING_DRAGON = RegisterItem(new DragonRing());
        RING_GEMBLUE = RegisterItem(new GemRingBlue());
        RING_GEMGREEN = RegisterItem(new GemRingGreen());
        RING_GEMGREY = RegisterItem(new GemRingGrey());
        RING_GEMLIME = RegisterItem(new GemRingLime());
        RING_GEMPINK = RegisterItem(new GemRingPink());
        RING_GEMPURPLE = RegisterItem(new GemRingPurple());
        RING_GEMRED = RegisterItem(new GemRingRed());
        RING_GEMSKY = RegisterItem(new GemRingSky());
        RING_GOLDEN = RegisterItem(new GoldenRing());
        RING_HOLY = RegisterItem(new HolyRing());
        RING_MAGIC = RegisterItem(new MagicRing());
        RING_SECRET = RegisterItem(new SecretRing());
        RING_SILVER = RegisterItem(new SilverRing());
        RING_SINGLE = RegisterItem(new Single_Earring());
        RING_UNDEAD = RegisterItem(new UndeadRing());
        RING_YGGDRASILL = RegisterItem(new YggdrasillRing());
        // Orb
        ORB_EVIL = RegisterItem(new EvilOrb());
        ORB_SKY = RegisterItem(new SkyOrb());
        ORB_NATURE = RegisterItem(new NatureOrb());
        // Weapon Common
        WEAPON_CANDY = RegisterItem(new CandyStaff());
        // Weapon warrior
        WEAPON_SWORD = RegisterItem(new Sword());
        WEAPON_SPEAR = RegisterItem(new Spear());
        // Weapon magician
        WEAPON_STAFF = RegisterItems(new Staff());
        WEAPON_WAND = RegisterItem(new Wand());
        // Weapon archer
        WEAPON_BOW = RegisterItem(new Bow());
        WEAPON_BOW2 = RegisterItem(new Bow2());
        WEAPON_BOW_Element = RegisterItem(new ElementBow());
        WEAPON_BOW_Moon = RegisterItem(new MoonBow());
        // Skill common
        SKILL_SPEEDBOOST = RegisterItem(new SpeedBoost());
        SKILL_HEALING = RegisterItem(new Healing());
        // Skill warrior
        SKILL_HPBOOST = RegisterItem(new HPBoost());
        SKILL_WINDSLASH = RegisterItem(new WindSlash());
        SKILL_PHALANX = RegisterItem(new Phalanx());
        // Skill magician
        SKILL_MAGICBALL = RegisterItem(new MagicBall());
        // Skill archer
        // Special
        SPECIAL_QUESTSTAFF = RegisterItem(new QuestStaff());
        SPECIAL_NPCSTAFF = RegisterItem(new NPCStaff());
        SPECIAL_TELEPORTSTAFF = RegisterItem(new TeleportStaff());
        SPECIAL_ZONESTAFF = RegisterItem(new ZoneStaff());
        // Entity
        ENTITY_MAGICBALL = RegisterItem(new Entity_MagicBall());
    }

    private static Supplier<Item> RegisterItem(ItemRegisterData itemRegisterData){
        String key = itemRegisterData.get_key();
        Item.Properties behaviour = itemRegisterData.get_behaviour();
        // https://stackoverflow.com/questions/79318791/item-texture-blank-in-minecraft-1-21-4-forge-mod
        behaviour.setId(ResourceKey.create(Registries.ITEM, ResourceLocation.parse(MODID + ":" + key)));
        Supplier<Item> buffer = ITEMS.register(key, () -> itemRegisterData.get_binding(behaviour));
        return buffer;
    }

    private static ArrayList<Supplier<Item>> RegisterItems(ItemRegisterData_Upgrade itemRegisterData){
        String key = itemRegisterData.get_key();
        Item.Properties[] behaviours = itemRegisterData.get_behaviours();
        ArrayList<Supplier<Item>> p = new ArrayList<>();
        for(int i = 0; i < MAX_UPGRADE && i < behaviours.length; i++){
            Item.Properties behaviour = behaviours[i];
            behaviour.setId(ResourceKey.create(Registries.ITEM, ResourceLocation.parse(MODID + ":" + key)));
            Item item = itemRegisterData.get_binding(i, behaviour);
            Supplier<Item> buffer = ITEMS.register(key, () -> item);
            p.add(buffer);
        }
        return p;
    }
}
