package com.sayler;

import com.beust.jcommander.Parameter;
import lombok.Getter;

/**
 * @author sayler
 */
@Getter
public class Args {
    @Parameter(description = "file")
    private String file;

    @Parameter(names = {"-h", "--help"}, description = "справка", help = true)
    private boolean help;

    @Parameter(names = {"-o", "--outpath"}, description = "путь сохранения результатов")
    private String outPath;

    @Parameter(names = {"-s", "--timestamp"}, description = "поиск по штампу времени (формат как в логе, пример: 2020-01-13 13:20:41)")
    private String timestamp;

    @Parameter(names = {"-t", "--template"}, description = "фильтровать по идентификатору шаблона")
    private String templateId;

    @Parameter(names = {"-d", "--debug"}, description = "включить отладочную информацию")
    private boolean debug = false;
}
