package Converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.hibernate.type.descriptor.sql.VarcharTypeDescriptor;
import org.uqbar.geodds.Point;
import java.util.*;

@Converter
public class PointConverter implements AttributeConverter<Point, String>{

    private static final String SEPARATOR = ",";

    @Override
    public String convertToDatabaseColumn(Point point) {
        StringBuilder sb = new StringBuilder();
        sb.append(point.latitude()).append(SEPARATOR)
                .append(point.longitude());
        return sb.toString();
    }

    @Override
    public Point convertToEntityAttribute(String pointString) {
        String[] rgb = pointString.split(SEPARATOR);
        return new Point(Integer.parseInt(rgb[0]),
                Integer.parseInt(rgb[1]));

    }

}
