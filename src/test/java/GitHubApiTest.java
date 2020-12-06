
import assignment.Token;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.jupiter.api.*;
import org.kohsuke.github.*;

import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS) //인스턴스 공용화
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // Order어노테이션으로 순서 지정
public class GitHubApiTest {


    static GitHub gitHub;
    static List<GHIssue> ghIssues;
    static HashSet<String> login = new HashSet<>(200);
    static Map<String, Integer> map = new HashMap<>(200);




    @BeforeAll
    public void TokenAccess() throws IOException { // non static
        gitHub = new GitHubBuilder().withOAuthToken(Token.TOKEN.getValue()).build();
    }

    @BeforeEach
    void setUp() throws IOException {
        GHRepository ghRepository = gitHub.getRepository("whiteship/live-study"); // whiteship/live-study에 연결한다.\
        ghIssues = ghRepository.getIssues(GHIssueState.ALL); // 모든 이슈를 가져온다.
    }

    @AfterEach
    void Reset() {
        login.removeAll(login);
    }

    @DisplayName("1. github API에 연결한다.")
    @Test @Order(1)
    public void apiConnect() {
        assertNotNull(gitHub);
    }

    @DisplayName("2. whiteship/live-study 의 모든 이슈를 가져온다")
    @Test @Order(2)
    public void liveStudyIssue() throws Exception {
        assertEquals(18, ghIssues.size(),()-> "총 이슈 갯수는 18개 여야 한다.");
        assertEquals("18주차 과제: 스트림", ghIssues.get(0).getTitle(),()-> "18주차 과제: 스트림 이여야 한다.");
    }

    @DisplayName("3. 중복없이 Login 정보로 commnet를 단 유저정보를 조회한다. ☺️")
    @Test @Order(3)
    public void issueSee() throws Exception {
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



    @DisplayName("4. 참여횟수를 저장하고 퍼센트를 출력한다. \uD83D\uDC37")
    @Test @Order(4)
    public void attendSave() throws IOException {
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
        assertEquals(1, (int) map.get("HyeonWuJeon"), () -> "참여횟수가 1이 아니다");
    }

    @Nested
    @DisplayName("주차별 테스트") @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @TestInstance(TestInstance.Lifecycle.PER_METHOD) //인스턴스 공용화
    class Context_week_Test {
        // 주차별 테스트
        void WeekTest(int Week) throws IOException {
            for (GHIssueComment ghIssue : ghIssues.get(Week).getComments()) {
                login.add(ghIssue.getUser().getLogin()); //중복댓글을 제외하고 저장한다.
            }
        }

        @Test
        @DisplayName("1 주차에 HyunWuJeon는 참가하였다.") @Order(1)
        void Hyunwoo1Week() throws IOException {
            final int Week = 17;
            WeekTest(Week);
            System.out.println("login.size() = " + login.size());
            assertTrue(login.contains("HyeonWuJeon"));
        }
        @Test
        @DisplayName("2 주차에 HyunWuJeon는 참가하였다.") @Order(2)
        void Hyunwoo2Week() throws IOException {
            final int Week = 16;
            WeekTest(Week);
            System.out.println("2login.size() = " + login.size());
            assertTrue(login.contains("HyeonWuJeon"));
        }
        @Test
        @DisplayName("3 주차에 HyunWuJeon는 참가하지 못했다.") @Order(3)
        void Hyunwoo3Week() throws IOException {
            final int Week = 15;
            WeekTest(Week);
            System.out.println("3login.size() = " + login.size());
            assertFalse(login.contains("HyeonWuJeon"));
        }
    }
}

