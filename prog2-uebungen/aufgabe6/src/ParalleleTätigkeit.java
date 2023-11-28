public class ParalleleTätigkeit extends ZusammengesetzteTätigkeit {
    @Override
    public double getTime() {
        double largestTime = 0;

        for (var job : tätigkeits) {
            double time = job.getTime();
            if (largestTime <= time)
                largestTime = time;
        }

        return largestTime;
    }
}
