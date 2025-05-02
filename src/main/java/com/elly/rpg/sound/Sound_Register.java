package com.elly.rpg.sound;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.Optional;

public class Sound_Register {
    public static RegistryObject<SoundEvent> Heal;
    public static RegistryObject<SoundEvent> Job;
    public static RegistryObject<SoundEvent> Meso;
    public static RegistryObject<SoundEvent> Potion;
    public static RegistryObject<SoundEvent> QuestFinish;

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
