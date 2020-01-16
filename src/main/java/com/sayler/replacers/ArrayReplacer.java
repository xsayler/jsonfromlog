package com.sayler.replacers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author sayler
 */
public class ArrayReplacer implements Replacer {

    public String execute(String text) {
        Matcher matcher = Pattern.compile("(?<=\":\\s\\[)([a-zA-Z0-9а-яА-Я,\\s_]+)(?=])").matcher(text);

        while (matcher.find()) {
            String found = matcher.group(1);
            String fixed = fixSimpleArray(found);
            text = text.replaceAll(String.format("\\[%s\\]", found), String.format("\\[%s\\]", fixed));
        }

        return text;
    }

    private String fixSimpleArray(String text) {
        Matcher matcher = Pattern.compile("([a-zA-Z0-9а-яА-Я][a-zA-Z0-9а-яА-Я\\s_]+[a-zA-Z0-9а-яА-Я])(?=[,\\s]+|$)").matcher(text);
        return matcher.find() ? matcher.replaceAll("\"$1\"") : text;
    }
}
