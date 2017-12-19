package de.bornholdtlee.snh.model.converter;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

import io.objectbox.converter.PropertyConverter;

public class StringListConverter implements PropertyConverter<List<String>, String> {

    private static final String SEPERATOR = ";";

    @Override
    public List<String> convertToEntityProperty(String databaseValue) {
        String[] split = databaseValue.split(SEPERATOR);
        List<String> result = new ArrayList<>();
        for (String aSplit : split) {
            result.add(aSplit);
        }
        return result;
    }

    @Override
    public String convertToDatabaseValue(List<String> strings) {
        String result = "";
        for (String integer : strings) {
            if (!TextUtils.isEmpty(result)) {
                result += SEPERATOR;
            }
            result += integer;
        }
        return result;
    }

}
