package com.elly.athena.capability;

import com.elly.athena.capability.implementation.PlayerStatus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class Attachment_Register {
    private final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES;
    public static Supplier<AttachmentType<PlayerStatus>> PLAYER_STATUS;

    public Attachment_Register(DeferredRegister<AttachmentType<?>> _ATTACHMENT_TYPES){
        ATTACHMENT_TYPES = _ATTACHMENT_TYPES;
        PLAYER_STATUS = ATTACHMENT_TYPES.register("status", () -> AttachmentType.serializable(() -> new PlayerStatus(0)).build());
    }
}
