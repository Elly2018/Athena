package com.elly.athena.data.types;

import net.minecraft.util.StringRepresentable;

import java.util.ArrayList;

public enum JobType implements StringRepresentable {
    NONE(-1, "none"),
    NEWBIE(0, "newbie"),
    WARRIOR(1, "warrior"),
    MAGICIAN(2, "magician"),
    ARCHER(3, "archer"),

    KNIGHT(4, "knight"),
    PRIEST(5, "priest"),
    PATHFINDER(6, "path finder");

    public static class Tree{
        public final JobType base;
        public final Tree[] children;

        public Tree(JobType base, Tree[] children) {
            this.base = base;
            this.children = children;
        }
    }

    private static final Tree[] GlobalTrees = {
        new Tree(JobType.WARRIOR, new Tree[]{
            new Tree(JobType.KNIGHT, new Tree[0])
        }),
        new Tree(JobType.MAGICIAN, new Tree[]{
            new Tree(JobType.PRIEST, new Tree[0])
        }),
        new Tree(JobType.ARCHER, new Tree[]{
            new Tree(JobType.PATHFINDER, new Tree[0])
        }),
    };
    public final int id;
    public final String name;

    JobType(int id, String name){
        this.id = id;
        this.name = name;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }

    public static JobType getEnumFromId(int Id){
        JobType[] types = JobType.values();
        for(int i = 0; i < types.length; i++){
            if(types[i].id == Id) return types[i];
        }
        return NONE;
    }

    public static boolean CheckJobInheritance(JobType base, JobType target){
        if(base == JobType.NEWBIE) return true;
        Tree baseTree = findBase(base);
        if(baseTree == null) return false;
        return find(baseTree, target) != null;
    }

    private static Tree findBase(JobType target){
        for (Tree globalTree : GlobalTrees) {
            Tree t = find(globalTree, target);
            if (t != null) return t;
        }
        return null;
    }

    private static Tree find(Tree tree, JobType base){
        if(tree.base == base) return tree;
        for(int i = 0; i < tree.children.length; i++){
            Tree t = find(tree.children[i], base);
            if(t != null) return t;
        }
        return null;
    }
}
