package assignment.fourweeks.gitapi;

import java.util.HashMap;
import java.util.Map;

public class Participant {

    String userName;

    Map<Integer, Boolean> homeworkResults;

    public Participant(String userName) {
        this.userName = userName;
        homeworkResults = new HashMap<>();
    }

    public double getRate(double total) {
        long count = homeworkResults.values().stream().filter(value -> value).count();
        return count * 100 / total;
    }

    public void markHomeworkIsDone(int index) {
        this.homeworkResults.put(index, true);
    }
}