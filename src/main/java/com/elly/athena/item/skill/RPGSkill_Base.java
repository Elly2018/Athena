package com.elly.athena.item.skill;

import com.elly.athena.Athena;
import com.elly.athena.data.Attachment_Register;
import com.elly.athena.data.Attribute_Register;
import com.elly.athena.data.interfaceType.attachment.IPlayerEquipment;
import com.elly.athena.data.interfaceType.attachment.IPlayerSkill;
import com.elly.athena.data.interfaceType.attachment.IPlayerStatus;
import com.elly.athena.data.types.JobType;
import com.elly.athena.item.weapon.RPGBow_Base;
import com.elly.athena.item.weapon.RPGMagic_Base;
import com.elly.athena.item.weapon.RPGMelee_Base;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Type;

public class RPGSkill_Base extends Item {
    public enum SkillType {
        Passive,
        Active
    }
    public enum MainItemType{
        None, Bow, Sword, Spear, Magic
    }

    public SkillType skillType = SkillType.Passive;
    public int MaxLevel = 10;
    public String Category = "common";

    public RPGSkill_Base(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResult use(@NotNull Level world, @NotNull Player player, @NotNull InteractionHand hand) {
        if(skillType == SkillType.Passive) {
            Athena.LOGGER.debug(String.format("Cannot use passive skill: %s %s", player.getName().getString(), descriptionId));
            return InteractionResult.FAIL;
        }
        AttributeMap map = player.getAttributes();
        IPlayerStatus ps = player.getData(Attachment_Register.PLAYER_STATUS);
        IPlayerSkill pss = player.getData(Attachment_Register.PLAYER_SKILL);
        AttributeInstance instance = map.getInstance(Attribute_Register.MANA);
        int mana_req = requireMana(1);
        assert instance != null;
        int p_mana = (int) instance.getValue();
        if(p_mana < mana_req && !player.isCreative()) {
            Athena.LOGGER.debug(String.format("Player does not have enough mana: player:%d  require:%d", p_mana, mana_req));
            return InteractionResult.FAIL;
        }
        if(requireWeapon() != MainItemType.None){
            boolean passMain = isHoldingTypeWeapon(player, requireWeapon());
            if(!passMain) return InteractionResult.FAIL;
        }
        JobType job = ps.getJob();
        if(!JobType.CheckJobInheritance(requireJob(), job)) {
            Athena.LOGGER.debug(String.format("Json check failed: base:%s player:%s", requireJob(), job));
            return InteractionResult.FAIL;
        }

        String skillName = descriptionId.replace("item.athena.", "");
        int level = pss.getPoint(Category, skillName);
        if(player.isCreative() && level < 0){
            level = 1;
        }
        if(level <= 1) {
            player.displayClientMessage(Component.literal("The skill point must at least 1 to use the skill"), true);
            return InteractionResult.FAIL;
        }
        boolean CanUse = pss.CheckCooldown(Category, skillName);
        if(!CanUse) {
            player.displayClientMessage(Component.literal("Skill still in cooldown"), true);
            return InteractionResult.FAIL;
        }

        if(!player.isLocalPlayer()) {
            if(!player.isCreative()){
                instance.setBaseValue(-mana_req);
            }
            pss.SetCooldown(Category, skillName, cooldown(level));
            server_apply(world, player, level, hand);
        }
        return InteractionResult.SUCCESS;
    }

    public void server_apply(Level world, Player player, int level, InteractionHand hand) { }
    public int requireMana(int level) { return 1; }
    public int cooldown(int level) { return 200; }
    public JobType requireJob() { return JobType.NEWBIE; }
    public MainItemType requireWeapon() { return MainItemType.None; }
    protected boolean isHoldingTypeWeapon(Player player, MainItemType type){
        IPlayerStatus ips = player.getData(Attachment_Register.PLAYER_STATUS);
        IPlayerEquipment ipe = player.getData(Attachment_Register.PLAYER_EQUIPMENT);
        if(ips.getMode() == 1 && ipe.hasMain()){
            ItemStack iss = ipe.getMain();
            switch (type){
                case Bow -> {
                    return iss.getItem() instanceof RPGBow_Base;
                }
                case Sword -> {
                    return iss.getItem() instanceof RPGMelee_Base;
                }
                case Spear -> {
                    return iss.getItem() instanceof RPGMelee_Base;
                }
                case Magic -> {
                    return iss.getItem() instanceof RPGMagic_Base;
                }
            }
        }
        return false;
    }
}
