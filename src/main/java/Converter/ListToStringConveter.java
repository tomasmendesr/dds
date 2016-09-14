package Converter;


import org.apache.commons.lang3.StringUtils;
import javax.persistence.*;
import java.util.*;

@Converter
public class ListToStringConveter implements AttributeConverter <List, String> {

    @Override
    public String convertToDatabaseColumn(List attribute) {
        if (attribute == null || attribute.isEmpty()) {
            return "";
        }
        return StringUtils.join(attribute, ",");
    }

    @Override
    public List<String> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.trim().length() == 0) {
            return new ArrayList<String>();
        }

        String[] data = dbData.split(",");
        return Arrays.asList(data);
    }
}

