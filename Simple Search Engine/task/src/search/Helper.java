package search;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public  class Helper  {
    private static final Scanner scanner = new Scanner(System.in);

    private Strategy strategy;
    private Context context;
    private QueryType queryType;
    private List<String> people;
    private String query;

    public Helper() {
        people = Main.getPeople();
    }

    public Helper(String fileName) {
        people = getPeople(fileName);
    }

    public void execute() {
        while (true) {
            int choice = menuChoice();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    selectStrategy();
                    getQuery();
                    context = new Context(strategy);
                    context.execute(query, people, mappingPeople());
                    break;
                case 2:
                    printAllData();
                    break;
                case 0:
                    exit();
                default:
                    System.out.println("Incorrect option! Try again.\n");
            }
        }
    }

    public Map<String, List<Integer>> mappingPeople() {
        Map<String, List<Integer>> invertedIndexes = new HashMap<>();
        for (int i = 0; i < people.size(); i++) {
            for (String peopleTerm : people.get(i).toUpperCase().split("\\s+")) { //Split people info(words) into separate word
                List<Integer> indexes = invertedIndexes.getOrDefault(peopleTerm, new ArrayList<>());
                indexes.add(i);
                invertedIndexes.put(peopleTerm, indexes);
            }
        }

        return invertedIndexes;
    }


    private void selectStrategy() {
        System.out.println("\nSelect a matching strategy: ALL, ANY, NONE");
        queryType = QueryType.valueOf(scanner.nextLine().toUpperCase());

        switch (queryType) {
            case ALL:
                strategy = new AllStrategy();
                break;
            case ANY:
                strategy = new AnyStrategy();
                break;
            case NONE:
                strategy = new NoneStrategy();
                break;
        }
    }

    private void getQuery() {
        System.out.println("\nEnter a name or email to search all suitable people.");
        query = scanner.nextLine();
        System.out.println();
    }

    private void printAllData() {
        System.out.println("=== List of people ===");
        people.forEach(System.out::println);
        System.out.println();
    }

    private List<String> getPeople(String fileName) {
        List<String> people = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNextLine()) {
                people.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return people;
    }

    private int menuChoice() {
        System.out.println("\n=== Menu ===\n" +
                "1. Find a person\n" +
                "2. Print all people\n" +
                "0. Exit");

        return scanner.nextInt();
    }

    private void exit() {
        System.out.println("Bye!");
        System.exit(1);
    }
}
