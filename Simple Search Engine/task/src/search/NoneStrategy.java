package search;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NoneStrategy implements Strategy {

    @Override
    public void searchInformation(String query, List<String> people, Map<String, List<Integer>> invertedIndexes) {
        List<String> foundedPeople = new ArrayList<>(people);

//        inverted.forEach((k, v) -> System.out.println(k + " -> " + v.toString()));

        for (String qw : query.toUpperCase().split("\\s+")) {
            if (invertedIndexes.containsKey(qw)) {
                for (Integer i : invertedIndexes.get(qw)) {
                    foundedPeople.remove(people.get(i));
                }
            }
        }

        if (!foundedPeople.isEmpty()) {
            System.out.printf("%d persons found:\n", foundedPeople.size());
            foundedPeople.forEach(System.out::println);
        } else {
            System.out.println("No matching people found.\n");
        }
    }
}
