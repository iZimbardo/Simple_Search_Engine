package search;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AllStrategy implements Strategy {

    @Override
    public void searchInformation(String query, List<String> people, Map<String, List<Integer>> invertedIndexes) {

//        invertedIndexes.forEach((k, v) -> System.out.println(k + " -> " + v.toString()));

        List<Integer> indexes = new ArrayList<>();
        boolean first = true;

        for (String term : query.toUpperCase().split("\\s+")) {
            if (invertedIndexes.containsKey(term)) {
                if (first) {
                    indexes.addAll(invertedIndexes.get(term));
                    first = false;
                }
                indexes.retainAll(invertedIndexes.get(term));
            } else {
                System.out.println("No matching people found.\n");
            }
        }

        if (!indexes.isEmpty()) {
            System.out.printf("%d persons found:\n", indexes.size());
            indexes.forEach(i -> System.out.println(people.get(i)));
        } else {
            System.out.println("No matching people found.\n");
        }
    }
}