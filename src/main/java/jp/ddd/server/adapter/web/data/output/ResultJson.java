package jp.ddd.server.adapter.web.data.output;

import jp.ddd.server.adapter.web.data.Json;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class ResultJson<T> implements Json {
    private T result;

    public static <T> ResultJson create(T result) {
        return ResultJson.builder().result(result).build();
    }

}