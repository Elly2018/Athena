package com.elly.athena.sound;

import com.elly.athena.Athena;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class Sound_Register {
    public static Supplier<SoundEvent> Heal;
    public static Supplier<SoundEvent> Job;
    public static Supplier<SoundEvent> Meso;
    public static Supplier<SoundEvent> Potion;
    public static Supplier<SoundEvent> QuestFinish;
    public static Supplier<SoundEvent> LEVELUP;

    public static void registerSounds(){
        Heal = Athena.SOUNDS.register("heal", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Athena.MODID, "heal")));
        Job = Athena.SOUNDS.register("job", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Athena.MODID, "job")));
        Meso = Athena.SOUNDS.register("meso", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Athena.MODID, "meso")));
        Potion = Athena.SOUNDS.register("potion", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Athena.MODID, "potion")));
        QuestFinish = Athena.SOUNDS.register("quest_finish", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Athena.MODID, "quest_finish")));
        LEVELUP = Athena.SOUNDS.register("levelup", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Athena.MODID, "levelup")));
    }
}
