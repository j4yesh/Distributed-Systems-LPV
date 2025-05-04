import java.util.*;

public class bullyAlgo {
    private List<Integer> processes;
    private int coordinator;

    public bullyAlgo(List<Integer> processes, int coordinator) {
        Collections.sort(processes);
        this.processes = processes;
        this.coordinator = coordinator;
    }

    public void startElection(int initiator) {
        System.out.println("\nProcess " + initiator + " starts an election.");
        List<Integer> higher = new ArrayList<>();
        for (int p : processes) {
            if (p > initiator) {
                higher.add(p);
                System.out.println("Process " + initiator + " sends election message to " + p);
            }
        }

        if (higher.isEmpty()) {
            coordinator = initiator;
            System.out.println("Process " + initiator + " becomes the new coordinator.");
        } else {
            System.out.println("Higher processes respond to " + initiator);
            int maxId = Collections.max(higher);
            coordinator = maxId;
            System.out.println("Process " + maxId + " becomes the new coordinator.");
        }
    }

    public static void main(String[] args) {
        List<Integer> processes = Arrays.asList(1, 2, 3, 4, 5);
        bullyAlgo bully = new bullyAlgo(processes, 5);
        bully.startElection(5);
    }

}
