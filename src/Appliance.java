public class Appliance {
    String applianceName;   // 1st attribute that comes from data
    int numberOfItems;      // 2nd attribute that comes from data
    double hourlyConsumed;  // 3rd attribute that comes from data
    int priority;           // 4th attribute that comes from data
    int startingHours;      // 5th attribute that comes from data
    int finishingHours;     // 6th attribute that comes from data
    int workingHour;        // 7th attribute that comes from data
    int[][] activeHours;    // Derived attribute

    //Below is the constructor method to create appliance objects

    public Appliance(String applianceName, int numberOfItems, double hourlyConsumed, int priority, int startingHours, int finishingHours, int workingHour, int[][] activeHours) {
        this.applianceName = applianceName;
        this.numberOfItems = numberOfItems;
        this.hourlyConsumed = hourlyConsumed;
        this.priority = priority;
        this.startingHours = startingHours;
        this.finishingHours = finishingHours;
        this.workingHour = workingHour;
        this.activeHours = activeHours;
    }

    // Below are the getter functions of the appliance objects

    public String getApplianceName() {
        return applianceName;
    }
    public int getNumberOfItems() {
        return numberOfItems;
    }
    public double getHourlyConsumed() {
        return hourlyConsumed;
    }
    public int getPriority() {
        return priority;
    }
    public int getStartingHours() {return startingHours;}
    public int getFinishingHours() {
        return finishingHours;
    }
    public int getWorkingHour() {
        return workingHour;
    }
    public int[][] getActiveHours() {return activeHours;}

    //Below is the toString method of the appliance objects.(To print out the results.)

    @Override
    public String toString() {
        return "Appliance{" +
                "applianceName='" + applianceName + '\'' +
                ", numberOfItems=" + numberOfItems +
                ", hourlyConsumed=" + hourlyConsumed +
                ", priority=" + priority +
                ", startingHours=" + (startingHours) +
                ", finishingHours=" + (finishingHours) +
                ", workingHour='" + workingHour + '\'' +
                '}';
    }
}