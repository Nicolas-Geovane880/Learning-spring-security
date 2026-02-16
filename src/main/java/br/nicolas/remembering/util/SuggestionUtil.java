package br.nicolas.remembering.util;

import org.apache.commons.text.similarity.LevenshteinDistance;
import java.util.List;

public class SuggestionUtil {

    private final static LevenshteinDistance distance = new LevenshteinDistance();

    public static String suggest (String wrongOption, List<String> validOptions) {
        String closest = null;
        int minDistance = Integer.MAX_VALUE;

        for (String option : validOptions) {

            int distance = SuggestionUtil.distance.apply(wrongOption.toUpperCase(), option.toUpperCase());

            if (distance < minDistance && distance <= 3) {
                closest = option;
                minDistance = distance;
            }
        }

        return closest;
    }
}
