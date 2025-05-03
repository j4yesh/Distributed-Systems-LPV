import java.util.*;

public class ringAlgo {
    private List<Integer> processes;
    private int coordinator;

    public ringAlgo(List<Integer> processes) {
        this.processes = processes;
        this.coordinator = Collections.max(processes);
    }

    public void startElection(int initiator) {
        System.out.println("\nProcess " + initiator + " starts an election.");
        List<Integer> electionMessage = new ArrayList<>();
        int index = processes.indexOf(initiator);

        for (int i = 0; i < processes.size(); i++) {
            int nextIndex = (index + i) % processes.size();
            int currentProcess = processes.get(nextIndex);
            electionMessage.add(currentProcess);
            System.out.println("Process " + currentProcess + " receives and forwards election message: " + electionMessage);
        }

        int newCoordinator = Collections.max(electionMessage);
        coordinator = newCoordinator;
        System.out.println("Process " + newCoordinator + " becomes the new coordinator.");
    }

    public static void main(String[] args) {
        List<Integer> processes = Arrays.asList(1, 2, 3, 4, 5);
        ringAlgo ring = new ringAlgo(processes);
        ring.startElection(2);
    }
}
