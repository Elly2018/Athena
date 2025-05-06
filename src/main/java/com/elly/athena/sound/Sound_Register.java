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

    private final DeferredRegister<SoundEvent> SOUND;

    public Sound_Register(DeferredRegister<SoundEvent> _SOUND) {
        this.SOUND = _SOUND;
    }

    public void registerSounds(){
        Heal = SOUND.register("heal", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Athena.MODID, "heal")));
        Job = SOUND.register("job", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Athena.MODID, "job")));
        Meso = SOUND.register("meso", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Athena.MODID, "meso")));
        Potion = SOUND.register("potion", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Athena.MODID, "potion")));
        QuestFinish = SOUND.register("quest_finish", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Athena.MODID, "quest_finish")));
        LEVELUP = SOUND.register("levelup", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Athena.MODID, "levelup")));
    }
}
