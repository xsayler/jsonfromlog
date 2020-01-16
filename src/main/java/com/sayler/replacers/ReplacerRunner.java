package com.sayler.replacers;

import static com.sayler.DebugUtils.debug;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sayler
 */
public class ReplacerRunner {
    private List<Replacer> replacers = new ArrayList<>();

    public ReplacerRunner addReplacer(Replacer replacer) {
        replacers.add(replacer);
        return this;
    }

    public String execute(String text) throws ReplacerException {
        for (Replacer replacer : replacers) {
            text = replacer.execute(text);
            debug("Replacer {} text:\n{}\n", replacer.getClass().getSimpleName(), text);
        }

        return text;
    }
}
