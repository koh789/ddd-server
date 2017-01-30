package jp.ddd.server.web.data.json;

import jp.ddd.server.web.data.Json;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ResultJson<T> implements Json {
    private T result;
}