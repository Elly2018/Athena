package com.elly.rpg.capability;

import com.elly.rpg.capability.capability.IExp;
import com.elly.rpg.capability.capability.IJob;
import com.elly.rpg.capability.capability.status.*;
import com.elly.rpg.capability.implementation.Exp;
import com.elly.rpg.capability.implementation.Job;
import com.elly.rpg.capability.implementation.Mana;
import com.elly.rpg.capability.capability.IMana;
import com.elly.rpg.capability.implementation.status.*;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.IntTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.*;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CapabilitySystem {
    public static final Capability<IMana> MANA = CapabilityManager.get(new CapabilityToken<>(){});
    public static final Capability<IJob> JOB = CapabilityManager.get(new CapabilityToken<>(){});
    public static final Capability<ILevel> LEVEL = CapabilityManager.get(new CapabilityToken<>(){});
    public static final Capability<IExp> EXP = CapabilityManager.get(new CapabilityToken<>(){});
    public static final Capability<IStr> STR = CapabilityManager.get(new CapabilityToken<>(){});
    public static final Capability<IDex> DEX = CapabilityManager.get(new CapabilityToken<>(){});
    public static final Capability<IInt> INT = CapabilityManager.get(new CapabilityToken<>(){});
    public static final Capability<ILuk> LUK = CapabilityManager.get(new CapabilityToken<>(){});
    public static final Capability<IPoint> POINT = CapabilityManager.get(new CapabilityToken<>(){});

    public static class PlayerCapabilitesRegister<X extends Tag, T extends INBTSerializable<X>, U> {
        public void attackPlayerCapabilities(
                final String modid,
                AttachCapabilitiesEvent<Entity> event,
                T instance,
                Capability<U> cap_target,
                String name){
            T backend = instance;
            LazyOptional<U> optionalCap = (LazyOptional<U>) LazyOptional.of(() -> backend);
            ICapabilityProvider provider = new ICapabilitySerializable<X>() {
                @Override
                public X serializeNBT(HolderLookup.Provider registryAccess) {
                    return backend.serializeNBT(registryAccess);
                }

                @Override
                public void deserializeNBT(HolderLookup.Provider registryAccess, X nbt) {
                    backend.deserializeNBT(registryAccess, nbt);
                }

                @Override
                public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
                    if (cap == cap_target) {
                        return optionalCap.cast();
                    }
                    return LazyOptional.empty();
                }
            };
            event.addCapability(ResourceLocation.fromNamespaceAndPath(modid, name), provider);
        }
    }

    public void onAttachingCapabilities(final String modid, AttachCapabilitiesEvent<Entity> event) {
        if (!(event.getObject() instanceof Player)) return;
        PlayerCapabilitesRegister<IntTag, Mana, IMana> pcr_mana = new PlayerCapabilitesRegister<>();
        PlayerCapabilitesRegister<StringTag, Job, IJob> pcr_job = new PlayerCapabilitesRegister<>();
        PlayerCapabilitesRegister<IntTag, Level, ILevel> pcr_level = new PlayerCapabilitesRegister<>();
        PlayerCapabilitesRegister<IntTag, Exp, IExp> pcr_exp = new PlayerCapabilitesRegister<>();
        PlayerCapabilitesRegister<IntTag, Str, IStr> pcr_str = new PlayerCapabilitesRegister<>();
        PlayerCapabilitesRegister<IntTag, Dex, IDex> pcr_dex = new PlayerCapabilitesRegister<>();
        PlayerCapabilitesRegister<IntTag, Int, IInt> pcr_int = new PlayerCapabilitesRegister<>();
        PlayerCapabilitesRegister<IntTag, Luk, ILuk> pcr_luk = new PlayerCapabilitesRegister<>();
        PlayerCapabilitesRegister<IntTag, Point, IPoint> pcr_point = new PlayerCapabilitesRegister<>();

        pcr_mana.attackPlayerCapabilities(modid, event, new Mana(), MANA, "mana_compatibility");
        pcr_job.attackPlayerCapabilities(modid, event, new Job(), JOB, "job_compatibility");
        pcr_level.attackPlayerCapabilities(modid, event, new Level(), LEVEL, "level_compatibility");
        pcr_exp.attackPlayerCapabilities(modid, event, new Exp(), EXP, "exp_compatibility");
        pcr_str.attackPlayerCapabilities(modid, event, new Str(), STR, "str_compatibility");
        pcr_dex.attackPlayerCapabilities(modid, event, new Dex(), DEX, "dex_compatibility");
        pcr_int.attackPlayerCapabilities(modid, event, new Int(), INT, "int_compatibility");
        pcr_luk.attackPlayerCapabilities(modid, event, new Luk(), LUK, "luk_compatibility");
        pcr_point.attackPlayerCapabilities(modid, event, new Point(), POINT, "point_compatibility");
    }
}
