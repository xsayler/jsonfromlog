package com.sayler.replacers;

import lombok.Builder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author sayler
 */
@Builder
public class RegExpReplacer implements Replacer {
    private Pattern pattern;
    private String replacement;

    public String execute(String text) {
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return matcher.replaceAll(replacement);
        } else {
            return text;
        }
    }
}
