package one.nem.lacerta.source.database.common;

import androidx.room.TypeConverter;

public class DateTypeConverter {

    @TypeConverter
    public static java.util.Date fromTimestamp(Long value) {
        return value == null ? null : new java.util.Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(java.util.Date date) {
        return date == null ? null : date.getTime();
    }
}
