package com.sayler;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Objects;

/**
 * @author sayler
 */
@Data
@Accessors(chain = true)
public class JsonModelInfo {
    private String raw;
    private String json;
    private String timestamp;
    private String templateId;

    public static JsonModelInfo empty() {
        return new JsonModelInfo();
    }

    public boolean isEmpty() {
        return Objects.isNull(raw);
    }

    public boolean nonEmpty() {
        return Objects.nonNull(raw);
    }
}
