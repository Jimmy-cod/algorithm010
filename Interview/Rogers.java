package Interview;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Rogers {

    Map<Long, Long> count(Map<String, UserStats>... visits) {
        Map<Long, Long> res = new HashMap<>();
        if (visits == null ) return res;
        for(Map<String, UserStats> m : visits){
            if (m!=null){
                for(String mkey : m.keySet()){
                    try{
                        Long id = Long.parseLong(mkey);
                        UserStats uStats = m.get(mkey);
                        if (uStats !=null){
                            Optional<Long> value = uStats.getVisitCount();
                            if (value!=null && value.isPresent()){
                                Long vistCount = value.get();
                                if (res.containsKey(id)){
                                    vistCount = vistCount + res.get(id);
                                }
                                res.put(id,vistCount);
                            }
                        }
                    }catch  (NumberFormatException e){
                        //ignore
                    }
                }
            }
        }

        return res;
    }


    Map<Long, Long> count2(Map<String, UserStats>... visits) {
        Map<Long, Long> res = new HashMap<>();
        if (visits == null ) return res;
        for(Map<String, UserStats> m : visits) {
            if (m != null) {
                m.entrySet()
                    .stream()
                    .filter(entry -> isValid(entry.getKey(),entry.getValue()))
                    .forEach((entry) -> {
                        Long id = Long.valueOf(entry.getKey()) ;
                        Long vistCount = entry.getValue().getVisitCount().get();
                        res.put(id,vistCount+ res.getOrDefault(id, new Long(0)));
                    });
            }
        }
        return res;
    }

    boolean isValid(String key, UserStats value){
        if (key==null || value == null || !isLong(key)){
            return false;
        }
        if (value.getVisitCount() == null || !value.getVisitCount().isPresent())
            return false;
        return true;
    }
    boolean isLong(String s){
        try{
            Long.parseLong(s);
            return true;
        }catch  (NumberFormatException e){
            //ignore
        }
        return false;
    }
}

class test {

    public static void main(String[] args) {
        Map<String, UserStats> visitStats1 = new HashMap<>();
        visitStats1.put("123", new UserStats(Optional.of(new Long(2))));
        visitStats1.put("456", new UserStats(null));
        visitStats1.put(null, null);
        visitStats1.put("444", null);

        visitStats1.put("567", new UserStats(Optional.empty()));

        visitStats1.put("234", new UserStats(Optional.of(new Long(1))));

        Map<String, UserStats> visitStats2 = new HashMap<>();
        Optional<Long> count2 = Optional.of(new Long(3));
        visitStats2.put("123", new UserStats(count2));

        Rogers rogers = new Rogers();
        Map<Long, Long> res = rogers.count2(visitStats1,visitStats2,null);

//        Map<Long, Long> res = rogers.count(null);
        res.forEach((K,V)->{
            System.out.println("Key:"+K+"; Value:"+V);
        });
    }
}


