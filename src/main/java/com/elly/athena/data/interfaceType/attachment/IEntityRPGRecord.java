package com.elly.athena.data.interfaceType.attachment;

public interface IEntityRPGRecord {
    void setEntityId(String value);
    String getEntityId();

    void setSkillItem(String value);
    String getSkillItem();

    void setSkillLevel(int value);
    int getSkillLevel();

    void setDamage(int value);
    int getDamage();

    void setMeta(String value);
    String getMeta();
}
