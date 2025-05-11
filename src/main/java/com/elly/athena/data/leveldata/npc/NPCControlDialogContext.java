package com.elly.athena.data.leveldata.npc;

import com.elly.athena.data.interfaceType.leveldata.INPCRegister;

public class NPCControlDialogContext implements INPCRegister.INPCControlDialogContent {
    private String text = "";
    private INPCRegister.INPCControlDialogContentSelection selection = null;
    private final NPCControl control;

    public NPCControlDialogContext(NPCControl control) {
        this.control = control;
    }

    @Override public void SetText(String text) { this.text = text; }
    @Override public String GetText() { return text; }

    @Override public void SetSelection(INPCRegister.INPCControlDialogContentSelection data) { selection = data; }
    @Override public INPCRegister.INPCControlDialogContentSelection GetSelection() { return selection; }
}
