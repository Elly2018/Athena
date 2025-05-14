package com.elly.athena.data.leveldata.quest;

import com.elly.athena.data.interfaceType.leveldata.IQuestRegister;

public class QuestContext implements IQuestRegister.IQuestContext {

    @Override
    public void SetType(IQuestRegister.QuestType type) {

    }

    @Override
    public IQuestRegister.QuestType GetType() {
        return null;
    }

    @Override
    public void SetOrder(int order) {

    }

    @Override
    public int GetOrder() {
        return 0;
    }

    @Override
    public void SetReward(IQuestRegister.IQuestReward reward) {

    }
}
