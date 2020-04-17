// Index Injector


public class IndexInject implements Runnable {
    private int value;
    private boolean stop = false;

    public IndexInject(int a){
        this.value = a ;
    }

    @Override
    public void run() {
        do {
            this.value = (int) Math.random() * 1;
            try {
                Thread.sleep(1000);
                System.out.println(value);
            } catch (Exception e) {
                System.out.println("Zombie habis");
            }
        } while (!stop);
    }

    public int getValue() {
        return value;
    }
}