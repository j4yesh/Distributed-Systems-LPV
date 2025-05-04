class TokenRingProcess extends Thread {
    private int id;
    private static volatile int tokenHolder = 0;
    private static final int NUM_PROCESSES = 5;
    private static volatile boolean[] wantsToEnter = new boolean[NUM_PROCESSES];

    public TokenRingProcess(int id) {
        this.id = id;
    }

    public void run() {
        while (true) {
            if (id == tokenHolder) {
                if (wantsToEnter[id]) {
                    enterCriticalSection();
                    wantsToEnter[id] = false;
                }
                passToken();
                try {
                    Thread.sleep(1000); // Delay to simulate real-world token passing
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void enterCriticalSection() {
        System.out.println("Process " + id + " is entering critical section.");
        try {
            Thread.sleep(500); // Simulate work in critical section
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Process " + id + " is exiting critical section.");
    }

    private void passToken() {
        tokenHolder = (tokenHolder + 1) % NUM_PROCESSES;
        System.out.println("Process " + id + " passed token to Process " + tokenHolder);
    }

    // Static method to request critical section
    public static void requestCS(int id) {
        wantsToEnter[id] = true;
    }

    public static void main(String[] args) {
        TokenRingProcess[] processes = new TokenRingProcess[NUM_PROCESSES];
        for (int i = 0; i < NUM_PROCESSES; i++) {
            processes[i] = new TokenRingProcess(i);
            processes[i].start();
        }

        // Simulate random critical section requests
        new Thread(() -> {
            while (true) {
                int requester = (int) (Math.random() * NUM_PROCESSES);
                System.out.println("Process " + requester + " requested critical section.");
                requestCS(requester);
                try {
                    Thread.sleep(3000); // New request every 3 seconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
