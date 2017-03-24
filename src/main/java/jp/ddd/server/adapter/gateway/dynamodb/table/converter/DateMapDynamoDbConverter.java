package jp.ddd.server.adapter.gateway.dynamodb.table.converter;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import jp.ddd.server.other.utils.Dates;
import jp.ddd.server.other.utils.DsMaps;
import org.eclipse.collections.api.map.ImmutableMap;
import org.eclipse.collections.impl.tuple.Tuples;

import java.util.Date;
import java.util.Map;

/**
 * Created by noguchi_kohei 
 */
public class DateMapDynamoDbConverter implements DynamoDBTypeConverter<Map<Integer, String>, Map<Integer, Date>> {

    @Override
    public Map<Integer, String> convert(Map<Integer, Date> dateMap) {
        final ImmutableMap<Integer, Date> imtMaps = DsMaps.toImt(dateMap);

        return imtMaps.collect((k, v) -> Tuples.pair(k, Dates.toString(v, Dates.DEFAULT_DATE_FORMAT))).castToMap();
    }

    @Override
    public Map<Integer, Date> unconvert(Map<Integer, String> dateStrMap) {
        if (dateStrMap == null) {
            return null;
        }
        final ImmutableMap<Integer, String> imtMaps = DsMaps.toImt(dateStrMap);
        return imtMaps.collect((k, v) -> Tuples.pair(k, Dates.toDate(Dates.DEFAULT_DATE_FORMAT, v))).castToMap();
    }
}
