package one.nem.lacerta.vcs.model.action.common;

import one.nem.lacerta.vcs.ActionType;

public class ActionBase {
    private ActionType actionType;

    public ActionBase() {
    }

    public ActionBase(ActionType actionType) {
        this.actionType = actionType;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }
}
