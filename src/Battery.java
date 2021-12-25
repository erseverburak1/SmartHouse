public class Battery {
    double capacity=3.00;
    double charge=0.6;
    double discharge=0.8;
    double initialCharge=0.3;
    double dischargeLimit=0.9;
    double rechargeLimit=1.5;
    double hardUsageLimit=2.4;

    public Battery() {
    }

    public double getCapacity() {
        return capacity;
    }

    public double getCharge() {
        return charge;
    }

    public double getDischarge() {
        return discharge;
    }
}
