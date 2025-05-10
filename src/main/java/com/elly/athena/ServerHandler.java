package com.elly.athena;

import com.elly.athena.data.Attachment_Register;
import com.elly.athena.data.implementation.BattleHotbar;
import com.elly.athena.data.implementation.PlayerEquipment;
import com.elly.athena.data.implementation.PlayerSkill;
import com.elly.athena.data.implementation.PlayerStatus;
import com.elly.athena.data.interfaceType.IDamage_Record;
import com.elly.athena.data.interfaceType.IPlayerStatus;
import com.elly.athena.data.types.ModContainer;
import com.elly.athena.gui.menu.Equipment_Menu;
import com.elly.athena.gui.menu.Skill_Menu;
import com.elly.athena.item.Item_Register;
import com.elly.athena.item.potion.RPGPotion_Base;
import com.elly.athena.item.skill.RPGSkill_Base;
import com.elly.athena.network.general.*;
import com.elly.athena.sound.Sound_Register;
import com.elly.athena.system.BattleSystem;
import com.elly.athena.system.SkillSystem;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingExperienceDropEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.player.ItemEntityPickupEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.event.server.ServerStoppedEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

import static com.elly.athena.keymap.KeyMap_Register.EQUIPMENT_MAPPING;
import static com.elly.athena.keymap.KeyMap_Register.SKILL_MAPPING;


@EventBusSubscriber(modid = Athena.MODID)
public class ServerHandler {
    private static MinecraftServer m_Server;

    @SubscribeEvent
    public static void onItemPickup(ItemEntityPickupEvent.Pre event){
        ItemEntity ie = event.getItemEntity();
        ItemStack is = ie.getItem();
        Item item = is.getItem();
        ServerPlayer player = (ServerPlayer) event.getPlayer();

        PickupGoldenHelper(player, ie, is);
        if(!ie.hasPickUpDelay()){
            var tag = LootPayload.Generate(item.getName().getString(), 16777215, is.getCount());
            PacketDistributor.sendToPlayer(player, new LootPayload.LootData(tag));
        }
    }

    @SubscribeEvent
    public static void onEntityInteract(PlayerInteractEvent.RightClickItem event){
        Player player = event.getEntity();
        Athena.LOGGER.debug(String.format("PlayerInteractEvent.RightClickItem: %s", player.getName().getString()));
        IPlayerStatus status = player.getData(Attachment_Register.PLAYER_STATUS);
        if(status.getMode() == 1) {
            onSkillUse(player, status);
            event.setCanceled(true);
        }
    }
    @SubscribeEvent
    public static void onEntityInteract(PlayerInteractEvent.RightClickEmpty event){
        Player player = event.getEntity();
        Athena.LOGGER.debug(String.format("PlayerInteractEvent.RightClickEmpty: %s", player.getName().getString()));
        IPlayerStatus status = player.getData(Attachment_Register.PLAYER_STATUS);
        if(status.getMode() == 1) {
            onSkillUse(player, status);
        }
    }

    private static void onSkillUse(Player player, IPlayerStatus status){
        ModContainer container = new ModContainer(player);
        ItemStack iss = container.getSelected();
        if(iss == ItemStack.EMPTY) return;
        if(iss.getItem() instanceof RPGPotion_Base){
            iss.use(player.level(), player, InteractionHand.MAIN_HAND);
        }
        else if(iss.getItem() instanceof RPGSkill_Base){
            RPGSkill_Base item = (RPGSkill_Base) iss.getItem();
            item.use(player.level(), player, InteractionHand.MAIN_HAND);
        }
    }

    @SubscribeEvent
    public static void update(ServerTickEvent.Pre event){
        event.getServer().getPlayerList().getPlayers().forEach( player -> {

            PlayerStatus ps = player.getData(Attachment_Register.PLAYER_STATUS);
            PlayerSkill pss = player.getData(Attachment_Register.PLAYER_SKILL);
            PlayerEquipment pe = player.getData(Attachment_Register.PLAYER_EQUIPMENT);
            BattleHotbar bh = player.getData(Attachment_Register.BATTLE_HOTBAR);

            if(ps.isLevelUp(ps.getLevel())){
                ps.setExp(0);
                ps.addLevel(1);
                player.level().playSound(null, player.getX(), player.getY(), player.getZ(), Sound_Register.LEVELUP.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
            }

            while (EQUIPMENT_MAPPING.get().consumeClick()){
                player.openMenu(new SimpleMenuProvider(
                        Equipment_Menu::new,
                        Component.empty()
                ));
            }
            while (SKILL_MAPPING.get().consumeClick()) {
                com.elly.athena.Athena.LOGGER.debug(String.format("%s is trying to check skill", player.getName().getString()));
                player.openMenu(new SimpleMenuProvider(
                        Skill_Menu::new,
                        Component.empty()
                ));
            }

            HolderLookup.Provider provider = new HolderLookup.Provider() {
                @Override
                public Stream<ResourceKey<? extends Registry<?>>> listRegistryKeys() {
                    return Stream.empty();
                }

                @Override
                public <T> Optional<? extends HolderLookup.RegistryLookup<T>> lookup(ResourceKey<? extends Registry<? extends T>> resourceKey) {
                    return Optional.empty();
                }
            };
            PacketDistributor.sendToPlayer(player, new StatusPayload.StatusData(ps.serializeNBT(player.registryAccess())));
            PacketDistributor.sendToPlayer(player, new SkillPayload.SkillData(pss.serializeNBT(player.registryAccess())));
            PacketDistributor.sendToPlayer(player, new EquipmentPayload.EquipmentData(pe.serializeNBT(player.registryAccess())));
            PacketDistributor.sendToPlayer(player, new HotbarPayload.HotbarData(bh.serializeNBT(player.registryAccess())));
        });
    }

    @SubscribeEvent
    public static void entityJoin(EntityJoinLevelEvent event){
        if (event.getEntity() instanceof Player){
            Player player = (Player) event.getEntity();
            if(!player.hasData(Attachment_Register.PLAYER_STATUS))
                player.setData(Attachment_Register.PLAYER_STATUS, new PlayerStatus());
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

    @SubscribeEvent
    public static void playerLoggin(PlayerEvent.PlayerLoggedInEvent event){
        SkillSystem.initCheck(event.getEntity());
    }

    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent.Pre event){
        if(!Config.damage_cooldown) event.getEntity().invulnerableTime = 0;
        Entity entity = event.getSource().getEntity();
        if(entity instanceof Player){
            Player player = (Player) entity;
            IPlayerStatus ps = player.getData(Attachment_Register.PLAYER_STATUS);
            var battle = new BattleSystem.BattleSystemProvider(player);
            event.setNewDamage(battle.DamageCalculat());
        }
    }

    @SubscribeEvent
    public static void onLivingComingDamage(LivingIncomingDamageEvent event){
        if(event.getSource().getEntity() instanceof Player){
            Player player = (Player)event.getSource().getEntity();
            LivingEntity le = event.getEntity();
            IDamage_Record record = event.getEntity().getData(Attachment_Register.DAMAGE_SOURCE);
            if(record != null && player != null){
                record.addPlayerSource(player, (int)event.getAmount());
                Athena.LOGGER.debug(String.format("Added player %s to damage table of the %s %d", player.getUUID(), le.getName().getString(), (int)event.getAmount()));
            }else{
                Athena.LOGGER.warn(String.format("Failed assign to damage table: %b, %b", record != null, player != null));
            }
        }
    }

    @SubscribeEvent
    public static void onLivingKill(LivingDeathEvent event){
        LivingEntity le = event.getEntity();
        IDamage_Record record = le.getData(Attachment_Register.DAMAGE_SOURCE);
        if(record != null && m_Server != null){
            HashMap<UUID, Float> table = record.getPlayerTable();
            var ref = new Object() {
                float total = 0F;
            };
            table.values().forEach(x -> { ref.total += x; });
            Athena.LOGGER.debug(String.format("Calcuate total damage: %.2f", ref.total));
            Athena.LOGGER.debug(String.format("Calcuate amount of key: %d", table.size()));
            table.keySet().forEach(x -> {
                float weight = table.get(x) / ref.total;
                ServerPlayer player = m_Server.getPlayerList().getPlayer(x);
                PlayerStatus ps = player.getData(Attachment_Register.PLAYER_STATUS);
                float baseAmmount = (le.getType().getCategory() == MobCategory.MONSTER ? 40 : 20);
                float a = baseAmmount * weight;
                ps.addExp((int)a);
                Athena.LOGGER.debug(String.format("Given %s player %.2f percentage exp killed", x.toString(), weight));
            });
        }else{
            Athena.LOGGER.warn(String.format("Failed given exp: %b, %b", record != null, m_Server != null));
        }
    }

    @SubscribeEvent
    public static void onLivingExpDrop(LivingExperienceDropEvent event){
        if(!Config.vanilla_exp_drop){
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void playerClone(PlayerEvent.Clone event){
        event.getEntity().setData(Attachment_Register.PLAYER_STATUS, event.getOriginal().getData(Attachment_Register.PLAYER_STATUS));
    }

    @SubscribeEvent
    public static void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        Athena.LOGGER.info("HELLO from Athena server handler");
        m_Server = event.getServer();
    }

    @SubscribeEvent
    public static void onServerStopped(ServerStoppedEvent event){
        Athena.LOGGER.info("BYE from Athena server handler");
        m_Server = null;
    }

    private static void PickupGoldenHelper(ServerPlayer player, ItemEntity ie, ItemStack is){
        Item cointype = Item_Register.RegisterDict.get("coin").get();
        Item goldencointype = Item_Register.RegisterDict.get("coin_golden").get();
        Item coinbagtype = Item_Register.RegisterDict.get("coin_bag").get();
        Item goldencoinbagtype = Item_Register.RegisterDict.get("coin_golden_bag").get();

        int count = 0;
        if(!ie.hasPickUpDelay()){
            if(is.getItem() == cointype){
                count = is.getCount();
            }
            else if(is.getItem() == goldencointype){
                count = is.getCount() * 100;
            }
            else if(is.getItem() == coinbagtype){
                count = is.getCount() * 1000;
            }
            else if(is.getItem() == goldencoinbagtype){
                count = is.getCount() * 10000;
            }
        }
        if(count > 0){
            ie.setDefaultPickUpDelay();
            IPlayerStatus ps = player.getData(Attachment_Register.PLAYER_STATUS);
            ps.addCoin(count);
            ie.remove(Entity.RemovalReason.KILLED);
            var tag = LootPayload.Generate(cointype.getName().getString(), 16777215, is.getCount());
            PacketDistributor.sendToPlayer(player, new LootPayload.LootData(tag));
        }
    }
}
