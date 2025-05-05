package com.elly.athena.command;

import com.elly.athena.data.interfaceType.IPlayerStatus;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class ActionStruct <T> {
    public final Consumer<T> setter;
    public final Consumer<T> adder;
    public final Supplier<T> getter;

    public ActionStruct(Consumer<T> setter, Consumer<T> adder, Supplier<T> getter) {
        this.setter = setter;
        this.adder = adder;
        this.getter = getter;
    }

    public static Function<IPlayerStatus, ActionStruct<Integer>> LEVEL() {
        return (ps) -> new ActionStruct<Integer>(ps::setLevel, ps::addLevel, ps::getLevel);
    }

    public static Function<IPlayerStatus, ActionStruct<Integer>> COIN() {
        return (ps) -> new ActionStruct<Integer>(ps::setCoin, ps::addCoin, ps::getCoin);
    }

    public static Function<IPlayerStatus, ActionStruct<Integer>> MAXMANA() {
        return (ps) -> new ActionStruct<Integer>(ps::setManaMaximum, ps::addManaMaximum, ps::getManaMaximum);
    }

    public static Function<IPlayerStatus, ActionStruct<Integer>> MANA() {
        return (ps) -> new ActionStruct<Integer>(ps::setMana, ps::addMana, ps::getMana);
    }

    public static Function<IPlayerStatus, ActionStruct<Integer>> EXP() {
        return (ps) -> new ActionStruct<Integer>(ps::setExp, ps::addExp, ps::getExp);
    }

    public static Function<IPlayerStatus, ActionStruct<Integer>> MAXHP() {
        return (ps) -> new ActionStruct<Integer>(ps::setMaxHealth, ps::addMaxHealth, ps::getHealthMaximum);
    }

    public static Function<IPlayerStatus, ActionStruct<Integer>> STR() {
        return (ps) -> new ActionStruct<Integer>(ps::setStr, ps::addStr, ps::getStr);
    }

    public static Function<IPlayerStatus, ActionStruct<Integer>> DEX() {
        return (ps) -> new ActionStruct<Integer>(ps::setDex, ps::addDex, ps::getDex);
    }

    public static Function<IPlayerStatus, ActionStruct<Integer>> INT() {
        return (ps) -> new ActionStruct<Integer>(ps::setInt, ps::addInt, ps::getInt);
    }

    public static Function<IPlayerStatus, ActionStruct<Integer>> LUK() {
        return (ps) -> new ActionStruct<Integer>(ps::setLuk, ps::addLuk, ps::getLuk);
    }

    public static Function<IPlayerStatus, ActionStruct<Integer>> POINT() {
        return (ps) -> new ActionStruct<Integer>(ps::setPoint, ps::addPoint, ps::getPoint);
    }
}
