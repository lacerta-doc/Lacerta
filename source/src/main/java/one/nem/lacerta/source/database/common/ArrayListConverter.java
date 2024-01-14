package one.nem.lacerta.source.database.common;

import androidx.room.TypeConverter;

import java.util.ArrayList;
import java.util.Arrays;

public class ArrayListConverter {

    @TypeConverter
    public static ArrayList<String> fromString(String value) {
        return value == null ? null : new ArrayList<String>(Arrays.asList(value.split(",")));
    }

    @TypeConverter
    public static String fromArrayList(ArrayList<String> list) {
        if (list == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        for (String s : list) {
            sb.append(s);
            sb.append(",");
        }
        return sb.toString();
    }
}
