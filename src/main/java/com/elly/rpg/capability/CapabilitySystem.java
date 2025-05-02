package com.elly.rpg.capability;

import com.elly.rpg.capability.implementation.ManaPointImplementation;
import com.elly.rpg.capability.capability.IManaPoint;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.IntTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.*;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CapabilitySystem {
    public static final Capability<IManaPoint> MANA = CapabilityManager.get(new CapabilityToken<>(){});

    public void onAttachingCapabilities(final String modid, AttachCapabilitiesEvent<Entity> event) {
        if (!(event.getObject() instanceof Player)) return;

        ManaPointImplementation backend = new ManaPointImplementation();
        LazyOptional<IManaPoint> optionalMana = LazyOptional.of(() -> backend);
        ICapabilityProvider provider = new ICapabilitySerializable<IntTag>() {
            @Override
            public IntTag serializeNBT(HolderLookup.Provider registryAccess) {
                return backend.serializeNBT(registryAccess);
            }

            @Override
            public void deserializeNBT(HolderLookup.Provider registryAccess, IntTag nbt) {
                backend.deserializeNBT(registryAccess, nbt);
            }

            @Override
            public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
                if (cap == MANA) {
                    return optionalMana.cast();
                }
                return LazyOptional.empty();
            }
        };

        event.addCapability(ResourceLocation.fromNamespaceAndPath(modid, "mana_compatibility"), provider);
    }
}
