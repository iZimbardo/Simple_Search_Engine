package search;

import java.util.List;
import java.util.Map;

public class Context {
    private Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public void execute(String query, List<String> people, Map<String, List<Integer>> invertedIndexes) {
        this.strategy.searchInformation(query, people, invertedIndexes);
    }
}
