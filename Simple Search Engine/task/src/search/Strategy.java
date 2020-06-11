package search;

import java.util.List;
import java.util.Map;

public interface Strategy {
    void searchInformation(String query, List<String> people, Map<String, List<Integer>> invertedIndexes);
}
