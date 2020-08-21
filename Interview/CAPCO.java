package Interview;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CAPCO {
    public static void main(String[] args) {

        if ("s" instanceof String)

        System.out.println(1);
    }

    public static String uniqueString_1(List<String> words) {

        LinkedHashMap<String, Integer> map = new LinkedHashMap<>(words.size());

        for (String s : words) {
            Integer count = map.getOrDefault(s, 0);
            map.put(s, count + 1);
        }

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }

        return "";
    }

    public static String uniqueString_2(List<String> words) {
        Map<String, Long> stringCountMap = words.stream()
                .collect(Collectors.groupingBy(s -> s, LinkedHashMap::new, Collectors.counting()));
        for (String s : stringCountMap.keySet()) {
            if (stringCountMap.get(s) == 1) {
                return s;
            }
        }
        return "";
    }

    public static String uniqueString(List<String> words) {
//        return words.stream()
//                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
//                .entrySet()
//                .stream()
//                .filter(entry -> entry.getValue() == 1)
//                .findFirst()
//                .map(Map.Entry::getKey)
//                .orElse(null);

        StringBuilder result = new StringBuilder();
        words.stream()
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() == 1)
                .findFirst()
                .ifPresent(entry -> result.append(entry.getKey()));
        return result.toString();
    }

}
