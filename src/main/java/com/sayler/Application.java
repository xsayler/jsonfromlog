package com.sayler;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.UnixStyleUsageFormatter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @author Alexander_Rassamagin
 */
@Slf4j
public class Application {
    public static void main(String[] argv) {
        Args args = new Args();
        JCommander commander = JCommander.newBuilder()
                .addObject(args)
                .build();
        commander.parse(argv);
        commander.setProgramName("java -jar jsonfromlog.jar");
        commander.setUsageFormatter(new UnixStyleUsageFormatter(commander));

        if (args.isHelp() || argv.length < 1) {
            commander.usage();
            return;
        }

        if (args.isDebug()) {
            DebugUtils.debugEnable();
        }

        try {
            LogFinder.builder()
                    .outputPath(args.getOutPath())
                    .timestamp(args.getTimestamp())
                    .templateId(args.getTemplateId())
                    .build()
                    .find(args.getFile());
        } catch (IOException e) {
            log.error("Error load from file {}", args.getFile(), e);
        }
    }
}
