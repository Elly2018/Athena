package com.elly.athena.data;

import com.elly.athena.Athena;
import com.elly.athena.data.datacomponent.BowData;
import com.elly.athena.data.datacomponent.Upgrade;
import com.elly.athena.item.Item_Register;
import net.minecraft.core.component.DataComponentType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.ModifyDefaultComponentsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;

import static com.elly.athena.Athena.DATA;

@EventBusSubscriber(modid = Athena.MODID, bus = EventBusSubscriber.Bus.MOD)
public class DataComponent_Register {
    public static DeferredHolder<DataComponentType<?>, DataComponentType<Upgrade>> UPGRADE = DATA.registerComponentType(
            "upgrade",
            builder -> builder
                    .persistent(Upgrade.BASIC_CODEC)
                    .networkSynchronized(Upgrade.BASIC_STREAM_CODEC));
    public static DeferredHolder<DataComponentType<?>, DataComponentType<BowData>> BOWDATA = DATA.registerComponentType(
            "bowdata",
            builder -> builder
                    .persistent(BowData.BASIC_CODEC)
                    .networkSynchronized(BowData.BASIC_STREAM_CODEC));


    @SubscribeEvent
    public static void modifyComponents(ModifyDefaultComponentsEvent event){
        event.modify(Item_Register.WEAPON_CANDY.get(), builder -> builder.set(UPGRADE.get(), new Upgrade(0)));

        event.modify(Item_Register.WEAPON_SWORD.get(), builder -> builder.set(UPGRADE.get(), new Upgrade(0)));
        event.modify(Item_Register.WEAPON_SPEAR.get(), builder -> builder.set(UPGRADE.get(), new Upgrade(0)));

        event.modify(Item_Register.WEAPON_STAFF.get(), builder -> builder.set(UPGRADE.get(), new Upgrade(0)));
        event.modify(Item_Register.WEAPON_WAND.get(), builder -> builder.set(UPGRADE.get(), new Upgrade(0)));

        event.modify(Item_Register.WEAPON_BOW.get(), builder -> builder.set(UPGRADE.get(), new Upgrade(0)));
        event.modify(Item_Register.WEAPON_BOW.get(), builder -> builder.set(BOWDATA.get(), new BowData(20)));
    }
}
