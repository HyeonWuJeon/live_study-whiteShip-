package assignment.gitapi;

import assignment.Token;
import org.kohsuke.github.*;

import java.io.IOException;
import java.util.*;

public class GithubApi {

    static GitHub gitHub;
    static HashSet<String> login = new HashSet<>(200);
    static Map<String, Integer> map = new HashMap<>(200);


    public static void main(String[] args) throws IOException {
        gitHub = new GitHubBuilder().withOAuthToken(Token.TOKEN.getValue()).build(); // git API에 접근한다
        GHRepository ghRepository = gitHub.getRepository("whiteship/live-study"); //레포지토리 정보를 가져온다.
        List<GHIssue> ghIssues = ghRepository.getIssues(GHIssueState.ALL); //모든 이슈를 가져온다.
        /**
         * 이슈별로 코멘트를 단 사용자 정보를 중복을 제거하여 저장한다.
         */
        for (GHIssue ghIssue : ghIssues) {
            for (GHIssueComment comment : ghIssue.getComments()) {
                login.add(comment.getUser().getLogin());
            }

            /**
             * 사용자 이름 : 코멘트 횟수를  key, value 형식으로 저장한다.
             */
            for (String s : login) {
                if (!map.containsKey(s)) {
                    map.put(s, 1);
                }
                int value = map.get(s);
                map.put(s, ++value);
            }
        }

        /**
         * 참여 퍼센트를 출력한다.
         */
        Iterator<String> keys = map.keySet().iterator();
        while (keys.hasNext()) {
            String key = keys.next();
            double percent = (double) (map.get(key) * 100) / 18;
            System.out.println("[Key]:" + key + " [Value]:" + String.format("%.2f", percent) + "%");
        }
    }
}
