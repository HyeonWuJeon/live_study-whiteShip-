package assignment.fourweeks.gitapi;

import assignment.Token;
import org.kohsuke.github.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class GithubApi {
    static GitHub github;
    static GHRepository ghRepository;

    public static void main(String[] args) throws IOException {
        GithubApi app = new GithubApi();
        app.printBoard();
    }

    private void connect() throws IOException {
        github = new GitHubBuilder().withOAuthToken(Token.TOKEN.getName()).build();
        ghRepository = github.getRepository(Token.REPOSITORY.getName());

    }

    private void printBoard() throws IOException {
        connect();
        List<Participant> participants = new ArrayList<>();
        for (int index = 1 ; index <= 18 ; index++) {
            GHIssue issue = ghRepository.getIssue(index);
            List<GHIssueComment> comments = issue.getComments();

            for (GHIssueComment comment : comments) {
                Participant participant = findParticipant(participants, comment.getUserName());
                participant.markHomeworkIsDone(index);
            }
        }



        participants.sort(Comparator.comparing(p -> p.userName));
        participants.forEach(p ->  {
            if(p.userName.equals("HyeonWuJeon")) {
                System.out.println(" ======== 나의 참석율 ======" );
                System.out.printf("| %s %s | %.2f%% |\n", p.userName, checkMark(p), p.getRate(18));
                System.out.println("=============================");
                System.out.println();
                System.out.println();
            }
            System.out.printf("| %s %s | %.2f%% |\n", p.userName, checkMark(p), p.getRate(18));
        }

        );
    }

    private Participant findParticipant(List<Participant> participants, String userName) {
        if (isNewUser(participants, userName)) {
            Participant participant = new Participant(userName);
            participants.add(participant);
            return participant;
        } else {
            Optional<Participant> first = participants.stream().filter(p -> p.userName.equals(userName)).findFirst();
            return first.orElseThrow(() -> new NullPointerException());
        }
    }

    private String checkMark(Participant p) {
        StringBuilder result = new StringBuilder();
        for (int i = 1 ; i < 19 ; i++) {
            if(p.homeworkResults.containsKey(i) && p.homeworkResults.get(i)) {
                result.append("|:white_check_mark:");
            } else {
                result.append("|");
            }
        }
        return result.toString();
    }

    private boolean isNewUser(List<Participant> participants, String userName) {
        return participants.stream().noneMatch(p -> p.userName.equals(userName));
    }

}