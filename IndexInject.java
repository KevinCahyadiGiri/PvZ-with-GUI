// Index Injector


public class IndexInject implements Runnable {
    private volatile int value;
    private boolean stop = false;

    public IndexInject(int a){
        this.value = a ;
    }

    @Override
    public void run() {
        do {
            this.value = (int) Math.random() * 10;
            try {
                Thread.sleep(1000);
                System.out.println(value);
            } catch (Exception e) {
                System.out.println("Zombie habis");
            }
            this.value = (int) Math.random() * 10;
        } while (!stop);
    }

    public int getValue() {
        return this.value;
    }
}