package com.elly.athena.entity;

import com.elly.athena.Athena;
import com.elly.athena.data.Attachment_Register;
import com.elly.athena.data.Attribute_Register;
import com.elly.athena.data.implementation.PlayerStatus;
import com.elly.athena.entity.mob.TestUseZombie;
import com.elly.athena.entity.mob.WoodElf;
import com.elly.athena.entity.npc.RPGNPC;
import com.elly.athena.entity.npc.RPGNPC_Renderer;
import com.elly.athena.entity.spell.MagicBall;
import com.elly.athena.item.skill.RPGSkill_Base;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterRenderBuffersEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

import java.util.function.Supplier;

import static com.elly.athena.Athena.MODID;

@EventBusSubscriber(modid = Athena.MODID)
public class Entity_Register {
    // BOSS
    public static final Supplier<EntityType<WoodElf>> WOODELF = register("woodelf", Entity_Register::woodelf);
    // MOB
    public static final Supplier<EntityType<TestUseZombie>> TESTZOMBIE = register("testzombie", Entity_Register::testzombie);
    // NPC
    public static final Supplier<EntityType<RPGNPC>> NPC = register("npc", Entity_Register::npc);
    // SPELL
    public static final Supplier<EntityType<MagicBall>> MAGICBALL = register("magic_ball", Entity_Register::magicball);

    private static <E extends Entity> Supplier<EntityType<E>> register(final String name, final Supplier<EntityType.Builder<E>> sup) {
        return Athena.ENTITY.register(name, () -> sup.get().build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse(MODID + ":" + name))));
    }

    private static EntityType.Builder<WoodElf> woodelf(){
        return EntityType.Builder.<WoodElf>of(WoodElf::new, MobCategory.MONSTER)
                .sized(0.5f, 0.5f)
                .setTrackingRange(4)
                .setUpdateInterval(3)
                .setShouldReceiveVelocityUpdates(true);
    }
    private static EntityType.Builder<TestUseZombie> testzombie(){
        return EntityType.Builder.<TestUseZombie>of(TestUseZombie::new, MobCategory.MONSTER)
                .sized(0.5f, 0.5f)
                .setTrackingRange(4)
                .setUpdateInterval(3)
                .setShouldReceiveVelocityUpdates(true);
    }
    private static EntityType.Builder<RPGNPC> npc(){
        return EntityType.Builder.<RPGNPC>of(RPGNPC::new, MobCategory.CREATURE)
                .sized(0.5f, 0.5f)
                .setTrackingRange(4)
                .setUpdateInterval(3)
                .setShouldReceiveVelocityUpdates(true);
    }
    private static EntityType.Builder<MagicBall> magicball(){
        return EntityType.Builder.<MagicBall>of(MagicBall::new, MobCategory.CREATURE)
                .sized(0.5f, 0.5f)
                .setTrackingRange(4)
                .setUpdateInterval(3)
                .setShouldReceiveVelocityUpdates(true);
    }

    @SubscribeEvent
    public static void entityJoin(EntityJoinLevelEvent event){
        if (event.getEntity() instanceof Player){
            Player player = (Player) event.getEntity();
            if(!player.hasData(Attachment_Register.PLAYER_STATUS))
                player.setData(Attachment_Register.PLAYER_STATUS, new PlayerStatus());
            Attribute_Register.ApplyChange(player);
        }
    }

    @SubscribeEvent
    public static void entityTick(EntityTickEvent.Pre event){
        if (event.getEntity() instanceof ItemEntity){
            ItemEntity item = (ItemEntity) event.getEntity();
            if(item.getItem().getItem() instanceof RPGSkill_Base){
                item.remove(Entity.RemovalReason.KILLED);
            }
        }
    }
}
