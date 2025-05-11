package com.elly.athena.data.interfaceType.attachment;

import com.elly.athena.data.types.JobType;

public interface IPlayerStatus{
    int getCoin();
    boolean HaveCoin(int value);
    void setCoin(int value);
    void addCoin(int value);
    void spendCoin(int value);

    int getDex();
    void setDex(int value);
    void addDex(int value);

    float getExpProgress(int level);
    int getExp();
    int getExpMaximum(int level);
    void addExp(int value);
    void setExp(int value);
    boolean isLevelUp(int level);

    int getHealthMaximum();
    void setMaxHealth(int value);
    void addMaxHealth(int value);

    int getInt();
    void setInt(int value);
    void addInt(int value);

    JobType getJob();
    void setJob(JobType value);

    int getLevel();
    void setLevel(int value);
    void addLevel(int value);

    int getLuk();
    void setLuk(int value);
    void addLuk(int value);

    int getManaMaximum();
    void setManaMaximum(int value);
    void addManaMaximum(int value);
    int getMana();
    void setMana(int value);
    void addMana(int value);

    int getPoint();
    void setPoint(int value);
    void addPoint(int value);
    void consumer(int value);

    int getSkillPoint();
    void setSkillPoint(int value);
    void addSkillPoint(int value);
    void consumerSkill(int value);

    int getStr();
    void setStr(int value);
    void addStr(int value);

    int getMode();
    void setMode(int value);
}
