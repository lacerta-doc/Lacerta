package one.nem.lacerta.vcs.internal;

import android.provider.ContactsContract;

import one.nem.lacerta.vcs.ActionType;
import one.nem.lacerta.vcs.model.action.DeletePage;
import one.nem.lacerta.vcs.model.action.InsertPage;
import one.nem.lacerta.vcs.model.action.UpdatePage;
import one.nem.lacerta.vcs.model.action.common.ActionBase;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {

    // TODO-rca: Injectionで実装しなおす

    // Public methods

    public static String toJson(Object object) {
        ActionBase converted;
        if (object == null) {
            return null;
        } else if (object instanceof ActionBase) {
            ObjectMapper mapper = new ObjectMapper();
            switch (((ActionBase) object).getActionType()) {
                case INSERT_PAGE:
                    converted = (InsertPage) object;
                    break;
                case UPDATE_PAGE:
                    converted = (UpdatePage) object;
                    break;
                case DELETE_PAGE:
                    converted = (DeletePage) object;
                    break;
                default:
                    throw new IllegalArgumentException("Unknown action type");
            }
            try {
                return mapper.writeValueAsString(converted);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        throw new IllegalArgumentException("Unknown object type");
    }


    public static <T> T fromJson(String json, ActionType actionType, Class<T> clazz) {
        return null;
    }

    // Internal methods


}
