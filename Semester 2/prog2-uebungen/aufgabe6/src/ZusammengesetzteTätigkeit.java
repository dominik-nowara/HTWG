import java.util.ArrayList;
import java.util.List;

public abstract class ZusammengesetzteTätigkeit implements Tätigkeit {
    protected List<Tätigkeit> tätigkeits = new ArrayList<>();

    /**
     * Add a job to the current list
     * @param tätigkeit Job to add
     */
    @Override
    public void add(Tätigkeit tätigkeit) {
        tätigkeits.add(tätigkeit);
    }

    /**
     * Removes a job from the current list
     * @param tätigkeit Job to remove
     */
    @Override
    public void remove(Tätigkeit tätigkeit) {
        tätigkeits.remove(tätigkeit);
    }

    /**
     * Calculates the amount of jobs in list. If one job is a ComposedJob
     * it gets the amount of their job list
     * @return Amount of jobs in list and children lists
     */
    @Override
    public int getAmount() {
        int amount = 0;

        for (var job: tätigkeits) {
            amount += job.getAmount();
        }

        return amount;
    }

    /**
     * Makes a string out of the list
     * @return Build string from list
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < tätigkeits.size(); i++) {
            if (i > 0)
                stringBuilder.append("\n");
            stringBuilder.append(tätigkeits.get(i).toString());
        }

        return stringBuilder.toString();
    }
}
