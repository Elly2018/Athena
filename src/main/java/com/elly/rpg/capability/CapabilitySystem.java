package com.elly.rpg.capability;

import com.elly.rpg.RPG;
import com.elly.rpg.capability.capability.ICoin;
import com.elly.rpg.capability.capability.IExp;
import com.elly.rpg.capability.capability.IJob;
import com.elly.rpg.capability.capability.IMana;
import com.elly.rpg.capability.capability.status.*;
import com.elly.rpg.capability.implementation.Coin;
import com.elly.rpg.capability.implementation.Exp;
import com.elly.rpg.capability.implementation.Job;
import com.elly.rpg.capability.implementation.Mana;
import com.elly.rpg.capability.implementation.status.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.neoforged.neoforge.capabilities.EntityCapability;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

public class CapabilitySystem {
    public static final EntityCapability<IMana, Void> MANA = EntityCapability.create(ResourceLocation.fromNamespaceAndPath(RPG.MODID, "mana"), IMana.class, Void.class);
    public static final EntityCapability<IJob, Void> JOB = EntityCapability.create(ResourceLocation.fromNamespaceAndPath(RPG.MODID, "job"), IJob.class, Void.class);
    public static final EntityCapability<ILevel, Void> LEVEL = EntityCapability.create(ResourceLocation.fromNamespaceAndPath(RPG.MODID, "level"), ILevel.class, Void.class);
    public static final EntityCapability<IExp, Void> EXP = EntityCapability.create(ResourceLocation.fromNamespaceAndPath(RPG.MODID, "exp"), IExp.class, Void.class);
    public static final EntityCapability<IStr, Void> STR = EntityCapability.create(ResourceLocation.fromNamespaceAndPath(RPG.MODID, "str"), IStr.class, Void.class);
    public static final EntityCapability<IDex, Void> DEX = EntityCapability.create(ResourceLocation.fromNamespaceAndPath(RPG.MODID, "dex"), IDex.class, Void.class);
    public static final EntityCapability<IInt, Void> INT = EntityCapability.create(ResourceLocation.fromNamespaceAndPath(RPG.MODID, "int"), IInt.class, Void.class);
    public static final EntityCapability<ILuk, Void> LUK = EntityCapability.create(ResourceLocation.fromNamespaceAndPath(RPG.MODID, "luk"), ILuk.class, Void.class);
    public static final EntityCapability<IPoint, Void> POINT = EntityCapability.create(ResourceLocation.fromNamespaceAndPath(RPG.MODID, "point"), IPoint.class, Void.class);
    public static final EntityCapability<ICoin, Void> COIN = EntityCapability.create(ResourceLocation.fromNamespaceAndPath(RPG.MODID, "coin"), ICoin.class, Void.class);

    public void onAttachingCapabilities(RegisterCapabilitiesEvent event) {
        event.registerEntity(MANA, EntityType.PLAYER, (player, context) -> new Mana());
        event.registerEntity(JOB, EntityType.PLAYER, (player, context) -> new Job());
        event.registerEntity(LEVEL, EntityType.PLAYER, (player, context) -> new Level());
        event.registerEntity(EXP, EntityType.PLAYER, (player, context) -> new Exp());
        event.registerEntity(STR, EntityType.PLAYER, (player, context) -> new Str());
        event.registerEntity(DEX, EntityType.PLAYER, (player, context) -> new Dex());
        event.registerEntity(INT, EntityType.PLAYER, (player, context) -> new Int());
        event.registerEntity(LUK, EntityType.PLAYER, (player, context) -> new Luk());
        event.registerEntity(POINT, EntityType.PLAYER, (player, context) -> new Point());
        event.registerEntity(COIN, EntityType.PLAYER, (player, context) -> new Coin());
    }
}
