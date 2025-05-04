package com.elly.athena.sound;

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

    private final DeferredRegister<SoundEvent> SOUND;

    public Sound_Register(DeferredRegister<SoundEvent> _SOUND) {
        this.SOUND = _SOUND;
    }

    public void registerSounds(String modid){
        Heal = SOUND.register("heal", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(modid, "heal")));
        Job = SOUND.register("job", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(modid, "job")));
        Meso = SOUND.register("meso", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(modid, "meso")));
        Potion = SOUND.register("potion", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(modid, "potion")));
        QuestFinish = SOUND.register("quest_finish", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(modid, "quest_finish")));
    }
}
