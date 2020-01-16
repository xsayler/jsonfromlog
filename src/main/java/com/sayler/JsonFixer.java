package com.sayler;

import com.sayler.replacers.ArrayReplacer;
import com.sayler.replacers.BeautifyJsonReplacer;
import com.sayler.replacers.RegExpReplacer;
import com.sayler.replacers.ReplacerException;
import com.sayler.replacers.ReplacerRunner;

import java.util.regex.Pattern;

/**
 * @author sayler
 */
public class JsonFixer {
    public String fix(String text) throws ReplacerException {
        return new ReplacerRunner()
                .addReplacer(
                        RegExpReplacer.builder()
                                .pattern(Pattern.compile("(\")"))
                                .replacement("\\\\$1")
                                .build()
                )
                .addReplacer(
                        RegExpReplacer.builder()
                                .pattern(Pattern.compile("([a-zA-Z0-9]+)="))
                                .replacement("\"$1\": ")
                                .build()
                )
                .addReplacer(
                        RegExpReplacer.builder()
                                .pattern(Pattern.compile("(\"[a-zA-Z0-9]+\":\\s)([^{^\\[^}^\\]]+?)(?=,\\s\"[a-zA-Z0-9]+\":)"))
                                .replacement("$1\"$2\"")
                                .build()
                )
                .addReplacer(
                        RegExpReplacer.builder()
                                .pattern(Pattern.compile("(?<=:\\s)([^\"^\\[^}^\\]]+)(?=[}\\]]+)"))
                                .replacement("\"$1\"")
                                .build()
                )
                .addReplacer(new ArrayReplacer())
                .addReplacer(new BeautifyJsonReplacer())
                .execute(text);
    }
}
