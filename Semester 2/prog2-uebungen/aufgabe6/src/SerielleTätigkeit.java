public class SerielleTätigkeit extends ZusammengesetzteTätigkeit {
    @Override
    public double getTime() {
        double calculateTime = 0;
        for (Tätigkeit tätigkeit : tätigkeits)
            calculateTime += tätigkeit.getTime();
        return calculateTime;
    }
}
