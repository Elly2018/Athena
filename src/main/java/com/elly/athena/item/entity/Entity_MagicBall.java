package com.elly.athena.item.entity;

import com.elly.athena.entity.spell.MagicBall;
import com.elly.athena.item.Item_Register;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileItem;
import net.minecraft.world.level.Level;

public class Entity_MagicBall implements Item_Register.ItemRegisterData {

    static class Entity_MagicBall_Item extends Item implements ProjectileItem {
        public Entity_MagicBall_Item(Properties properties) {
            super(properties);
        }

        @Override
        public Projectile asProjectile(Level level, Position position, ItemStack itemStack, Direction direction) {
            return new MagicBall(level, position.x(), position.y(), position.z(), itemStack);
        }
    }

    @Override
    public String get_key() {
        return "entity_magicball";
    }

    @Override
    public Item.Properties get_behaviour() {
        return new Item.Properties().stacksTo(1);
    }

    @Override
    public Item get_binding(Item.Properties props) {
        return new Entity_MagicBall_Item(props);
    }
}
