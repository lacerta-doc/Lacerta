package one.nem.lacerta.source.database.common;

import androidx.room.TypeConverter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TagListConverter {
    @TypeConverter
    public static String fromList(List<String> list) {
        return list == null ? null : String.join(",", list);
    }

    @TypeConverter
    public static List<String> fromString(String value) {
        return value == null ? Collections.emptyList() : Arrays.asList(value.split(","));
    }
}
