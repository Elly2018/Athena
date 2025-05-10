package com.elly.athena.data;

import com.elly.athena.data.implementation.*;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

import static com.elly.athena.Athena.MODID;

public class Attachment_Register {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, MODID);
    // Player
    public static final Supplier<AttachmentType<PlayerStatus>> PLAYER_STATUS = ATTACHMENT.register("player_status", () -> AttachmentType.serializable(PlayerStatus::new).build());
    public static final Supplier<AttachmentType<PlayerSkill>> PLAYER_SKILL = ATTACHMENT.register("player_skill", () -> AttachmentType.serializable(PlayerSkill::new).build());
    public static final Supplier<AttachmentType<PlayerEquipment>> PLAYER_EQUIPMENT = ATTACHMENT.register("equipment", () -> AttachmentType.serializable(PlayerEquipment::new).build());
    public static final Supplier<AttachmentType<DamageSource>> DAMAGE_SOURCE = ATTACHMENT.register("damage_source", () -> AttachmentType.serializable(DamageSource::new).build());
    public static final Supplier<AttachmentType<BattleHotbar>> BATTLE_HOTBAR = ATTACHMENT.register("battle", () -> AttachmentType.serializable(BattleHotbar::new).build());
    // Entity
    public static final Supplier<AttachmentType<EntityRPGRecord>> ENTITY_RECORD = ATTACHMENT.register("entity_record", () -> AttachmentType.serializable(EntityRPGRecord::new).build());
}
