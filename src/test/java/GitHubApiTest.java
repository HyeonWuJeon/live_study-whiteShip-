
import assignment.Token;
import org.junit.jupiter.api.*;
import org.kohsuke.github.*;

import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS) //인스턴스 공용화
public class GitHubApiTest {


    static GitHub gitHub;
    static List<GHIssue> ghIssues;
    static HashSet<String> login = new HashSet<>(200);
    static Map<String, Integer> map = new HashMap<>(200);

    @BeforeAll
    public void TokenAccess() throws IOException {
        gitHub = new GitHubBuilder().withOAuthToken(Token.TOKEN.getValue()).build();
    }

    @BeforeEach
    void setUp() throws IOException {
        GHRepository ghRepository = gitHub.getRepository("whiteship/live-study"); // whiteship/live-study에 연결한다.\
        ghIssues = ghRepository.getIssues(GHIssueState.ALL); // 모든 이슈를 가져온다.
    }

    @DisplayName("1. github API에 연결한다.")
    @Test
    void ApiConnect() {
        assertNotNull(gitHub);
    }

    @DisplayName("2. whiteship/live-study 의 모든 이슈를 가져온다")
    @Test
    void LiveStudyIssue() throws Exception {
        assertEquals(18, ghIssues.size(), "총 이슈 갯수는 18개 여야 한다.");
        assertEquals("18주차 과제: 스트림", ghIssues.get(0).getTitle(), "18주차 과제: 스트림 이여야 한다.");
    }

    @DisplayName("3. 중복없이 Login 정보로 commnet를 단 유저정보를 조회한다.")
    @Test
    void 이슈별유저정보조회() throws Exception {
        for (GHIssue ghIssue : ghIssues) {
            for (GHIssueComment comment : ghIssue.getComments()) { // 각 이슈마다 저장
                assertAll("Comment",
                        () -> assertNotNull(comment.getUser().getLogin()));
//                        () -> assertNotNull(comment.getUser().getEmail()), // NULL 값 존재한다.
//                        () -> assertNotNull(comment.getUser().getName()) // NULL 값 존재한다.
//                );

                login.add(comment.getUser().getLogin()); //중복댓글을 제외하고 저장한다.
            }
        }
        assertNotNull(login);
        System.out.println("login.size() = " + login.size());
    }



    @DisplayName("4. 참여횟수를 저장하고 퍼센트를 출력한다.")
    @Test
    void 참여횟수저장() throws Exception {
        for (GHIssue ghIssue : ghIssues) {
            for (GHIssueComment comment : ghIssue.getComments()) { // 각 이슈마다 저장
                login.add(comment.getUser().getLogin()); //중복댓글을 제외하고 저장한다.
            }
            for (String s : login) {
                if (!map.containsKey(s)) {// 첫 값일 경우
                    map.put(s, 1);
                } else { // 두번째 부터
                    int value = map.get(s);
                    map.put(s, ++value);
                }
            }
        }
        System.out.println("map.size() = " + map.size());
        Iterator<String> keys = map.keySet().iterator();
        while(keys.hasNext()){
            String key = keys.next();
            double percent = (double) (map.get(key) * 100) / 18;
            System.out.println("[Key]:" + key + " [Value]:" +  String.format("%.2f", percent)+"%");
        }
        assertEquals(2, (int) map.get("HyeonWuJeon"), "전현우는 두번 참석 했다.");
    }

}

