import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

class Main {
    public static void main(String[] args) {
        // put your code here
        List<Integer> integerList = Arrays.stream(new Scanner(System.in).nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayList::new));

        integerList.removeIf(e -> integerList.indexOf(e) % 2 == 0);
        Collections.reverse(integerList);

        integerList.forEach(e -> System.out.print(e + " "));
    }
}