package com.elly.athena.data.interfaceType.attachment;

import java.util.UUID;

public interface IPlayerQuest {

    interface IQuestRecord {
        enum ProgressType{
            Undone, InProgress, Finish, AllFinish
        }
        int SetStage(int value);
        int GetStage();
        void SetState(ProgressType state);
        ProgressType GetState();
    }

    IQuestRecord AddRecord(UUID target);
    IQuestRecord GetRecord(UUID target);
    void RemoveRecord(UUID target);
    int Size();
    void Clean();
}
