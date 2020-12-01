package Interview;

import java.util.Optional;

public class UserStats {
    Optional<Long> visitCount;

    public UserStats(Optional<Long> count){
        this.visitCount = count;
    }
    public Optional<Long> getVisitCount(){
        return visitCount;
    }
}
