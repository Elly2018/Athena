package com.elly.athena.item.skill.warrior;

import com.elly.athena.Athena;
import com.elly.athena.item.Item_Register;
import com.elly.athena.item.skill.RPGSkill_Base;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import org.jetbrains.annotations.NotNull;

/**
 * This is the passive skill which make player Max HP permanently increase
 */
public class HPBoost implements Item_Register.ItemRegisterData {

    static class HPBoost_RPG_Skill extends RPGSkill_Base {

        ResourceLocation modifier = ResourceLocation.fromNamespaceAndPath(Athena.MODID, "skill.passive.hp_boost");

        public HPBoost_RPG_Skill(Properties properties) {
            super(properties);
        }

        @Override
        public ItemAttributeModifiers use_passive_effect(Player player, int level) {
            float t = 1.0F + (level * 0.05F);
            return ItemAttributeModifiers.builder()
                    .add(Attributes.MAX_HEALTH, new AttributeModifier(modifier, t, AttributeModifier.Operation.ADD_MULTIPLIED_BASE), EquipmentSlotGroup.ANY)
                    .build();
        }
    }

    @Override
    public String get_key() {
        return "skill_hp_boost";
    }

    @Override
    public Item.Properties get_behaviour() {
        return new Item.Properties()
                .stacksTo(1);
    }

    @Override
    public Item get_binding(Item.Properties props) {
        return new HPBoost_RPG_Skill(props);
    }
}
