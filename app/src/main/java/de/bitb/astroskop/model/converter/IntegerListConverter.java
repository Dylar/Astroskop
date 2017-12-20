package de.bitb.astroskop.model.converter;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

import io.objectbox.converter.PropertyConverter;

public class IntegerListConverter implements PropertyConverter<List<Integer>, String> {

    private static final java.lang.String SEPERATOR = ";";

    @Override
    public List<Integer> convertToEntityProperty(String databaseValue) {
        String[] split = databaseValue.split(SEPERATOR);
        List<Integer> result = new ArrayList<>();
        for (String aSplit : split) {
            result.add(Integer.valueOf(aSplit));
        }
        return result;
    }

    @Override
    public String convertToDatabaseValue(List<Integer> integers) {
        String result = "";
        for (Integer integer : integers) {
            if (!TextUtils.isEmpty(result)) {
                result += SEPERATOR;
            }
            result += integer;
        }
        return result;
    }

}
