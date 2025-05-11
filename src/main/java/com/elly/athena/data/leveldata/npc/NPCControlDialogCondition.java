package com.elly.athena.data.leveldata.npc;

import com.elly.athena.data.interfaceType.leveldata.INPCRegister;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnknownNullability;

import java.util.ArrayList;
import java.util.UUID;

public class NPCControlDialogCondition implements INPCRegister.INPCControlDialogCondition {
    public final INPCRegister.DialogConditionType type;
    public final ArrayList<INPCRegister.INPCControlDialogContent> context;

    public NPCControlDialogCondition(INPCRegister.DialogConditionType type) {
        this.type = type;
        this.context = new ArrayList<>();
    }

    @Override
    public INPCRegister.DialogConditionType GetType() {
        return type;
    }

    @Override
    public void SetConditionQuest(UUID target) {

    }

    @Override
    public UUID GetConditionQuest() {
        return null;
    }

    @Override
    public void SetConditionLevel(int level) {

    }

    @Override
    public int GetConditionLevel() {
        return 0;
    }

    @Override
    public void AddContent(INPCRegister.INPCControlDialogContent _context) {
        context.add(_context);
    }

    @Override
    public INPCRegister.INPCControlDialogContent GetContent(int index) {
        return null;
    }

    @Override
    public void RemoveContent(int index) {

    }

    @Override
    public int GetSize() {
        return 0;
    }

    @Override
    public void Clean() {
        context.clear();
    }

    @Override
    public @UnknownNullability CompoundTag serializeNBT(HolderLookup.@NotNull Provider provider) {
        return null;
    }

    @Override
    public void deserializeNBT(HolderLookup.@NotNull Provider provider, @NotNull CompoundTag compoundTag) {

    }
}
