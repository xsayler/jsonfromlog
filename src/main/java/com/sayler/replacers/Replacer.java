package com.sayler.replacers;

/**
 * @author sayler
 */
public interface Replacer {
    String execute(String text) throws ReplacerException;
}
