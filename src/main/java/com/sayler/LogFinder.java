package com.sayler;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * @author sayler
 */
@Slf4j
@Builder
public class LogFinder {
    private String outputPath;
    private String timestamp;
    private String templateId;

    private static final Pattern PATTERN =
            Pattern.compile("(\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}\\.\\d{3}).+templateId=([a-zA-Z0-9\\-_.]+),\\s" +
                    "format.+,\\sdataModel=(.+),\\sparameters=\\{.+}\\).");

    public void find(String sourceFileName) throws IOException {
        try (Stream<String> stream = Files.lines(Paths.get(sourceFileName))) {
            stream.map(this::extractJsonModel)
                    .filter(JsonModelInfo::nonEmpty)
                    .filter(m -> Objects.isNull(timestamp) || m.getTimestamp().contains(timestamp))
                    .filter(m -> Objects.isNull(templateId) || m.getTemplateId().equals(templateId))
                    .map(this::recoveryJsonModel)
                    .forEach(this::saveToFile);
        }
    }

    public JsonModelInfo extractJsonModel(String lineFromLog) {
        Matcher matcher = PATTERN.matcher(lineFromLog);
        if (matcher.find()) {
            log.info("Found json model templateId={} from {}", matcher.group(2), matcher.group(1));
            return new JsonModelInfo()
                    .setRaw(matcher.group(3))
                    .setTimestamp(matcher.group(1))
                    .setTemplateId(matcher.group(2));
        } else {
            return JsonModelInfo.empty();
        }
    }

    public JsonModelInfo recoveryJsonModel(JsonModelInfo model) {
        log.info("Start recovery json model templateId={} from {}", model.getTemplateId(), model.getTimestamp());
        JsonFixer jsonFixer = new JsonFixer();
        try {
            String json = jsonFixer.fix(model.getRaw());
            log.info("Recovery successful. Json model templateId={} from {}", model.getTemplateId(), model.getTimestamp());
            return model
                    .setJson(json);
        } catch (Exception e) {
            log.error("Error recovery json model templateId={} from {}", model.getTemplateId(), model.getTimestamp(), e);
            return model;
        }
    }

    public void saveToFile(JsonModelInfo model) {
        String fileName = FileUtils.calcUniqueFileName(
                outputPath,
                String.format("%s-%s", model.getTimestamp(), model.getTemplateId()),
                ".json"
        );
        Path path = Objects.nonNull(outputPath) ? Paths.get(outputPath, fileName) : Paths.get(fileName);
        try {
            Files.write(path, model.getJson().getBytes());
            log.info("Restored model templateId={} from {} saved to file {}", model.getTemplateId(), model.getTimestamp(), fileName);
        } catch (IOException e) {
            log.error("Error save file {}", fileName, e);
        }
    }

}
