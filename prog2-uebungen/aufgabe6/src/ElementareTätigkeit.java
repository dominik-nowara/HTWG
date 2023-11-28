import java.lang.RuntimeException.*;

public class ElementareTätigkeit implements Tätigkeit {
    private final double time;
    private final String desc;

    /**
     * Creates new job with default parameters
     */
    public ElementareTätigkeit() {
        this.time = 0;
        this.desc = "Empty description";
    }

    /**
     * Creates new elementary job with parameters
     * @param desc Description of this job
     * @param time Time this job takes up
     */
    public ElementareTätigkeit(String desc, double time) {
        this.time = time;
        this.desc = desc;
    }

    /**
     * Returns how much time current jobs takes up
     * @return Time of current job
     */
    @Override
    public double getTime() {
        return time;
    }

    /**
     * Not used in this class
     * @param tätigkeit No use
     */
    @Override
    public void add(Tätigkeit tätigkeit) {
        return;
    }

    /**
     * Not used in this class
     * @param tätigkeit No use
     */
    @Override
    public void remove(Tätigkeit tätigkeit) {
        return;
    }

    /**
     * This class has always the amount of 1
     * @return amount 1
     */
    @Override
    public int getAmount() {
        return 1;
    }

    /**
     * Just returns current job's description
     * @return Description of job
     */
    @Override
    public String toString() {
        return desc;
    }
}
