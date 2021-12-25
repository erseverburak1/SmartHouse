import org.jfree.ui.RefineryUtilities;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        // File Reading
        FileIO f = new FileIO();
        Appliance[] appliancesWinter;
        Appliance[] appliancesSummer;
        appliancesWinter = f.appliancesReader("winterDay.csv");
        appliancesSummer = f.appliancesReader("summerDay.csv");
        Operations ops = new Operations();
        double[] winterHouseHourlyLoad;
        double[] summerHouseHourlyLoad;

        WinterGraphics winterGraphics = new WinterGraphics("Winter");
        WinterShiftedGraphics winterShiftedGraphics = new WinterShiftedGraphics("Winter Shifted");
        SummerGraphics summerGraphics = new SummerGraphics("Summer");
        SummerShiftedGraphics summerShiftedGraphics = new SummerShiftedGraphics("Summer Shifted");

        // Electric loads and their rated power are printed.

        System.out.println("Welcome to the Smart House.");
        System.out.println("\n---------------------------\n");
        System.out.println("(WINTER DAY VERSION)");
        System.out.println("\n---------------------------\n");
        System.out.println("The list of electric loads and their rated power is listed below.\n");
        for (int i = 0; i < appliancesWinter.length; i++) {
            System.out.println("Electric load: " + appliancesWinter[i].getApplianceName() + ", Rated power: " + appliancesWinter[i].hourlyConsumed + " kwh");
        }

        // Priorities of the electric loads are printed.

        System.out.println("\n---------------------------\n");
        System.out.println("The list of priorities of the electric loads are listed below.\n");
        System.out.println("Priority standard: 'high=1','mean=2','low=3\n");
        for (int i = 0; i < appliancesWinter.length; i++) {
            System.out.println("Electric load: " + appliancesWinter[i].getApplianceName() + ", Priority: " + appliancesWinter[i].getPriority());
        }

        // Working hours of the electric loads are printed.

        System.out.println("\n---------------------------\n");
        System.out.println("The list of working hours of the electric loads are listed below.\n");
        for (int i = 0; i < appliancesWinter.length; i++) {

            System.out.println("Electric load: " + appliancesWinter[i].getApplianceName() + ", Working hours: " + appliancesWinter[i].getStartingHours() + "-" + appliancesWinter[i].getFinishingHours());
        }

        // The daily loading of the home is printed.

        System.out.println("\n---------------------------\n");
        System.out.println("The daily loading of the home is below.\n");
        double sumLoad = 0.0;
        for (int i = 0; i < appliancesWinter.length; i++) {
            sumLoad = sumLoad + appliancesWinter[i].getNumberOfItems() * appliancesWinter[i].getHourlyConsumed() * appliancesWinter[i].getWorkingHour();
        }
        System.out.println(String.format("%.2f",sumLoad )+ " kw");

        // The decision of the PV panel is printed.

        System.out.println("\n---------------------------\n");

        System.out.println("A PV panel will be used with a 100W power in the time interval of 8am-5pm.");

        // The decision of the threshold value(maximum power allowed) for peak time is printed.

        System.out.println("\n---------------------------\n");

        System.out.println("The threshold value is 5kw for peak time.");

        System.out.println("\n---------------------------\n");

        //Daily load of the house is printed below(Original State).

        System.out.println("Winter day daily load of the house is printed below.\n");
        winterHouseHourlyLoad = ops.houseHourlyConsumptionFinder(appliancesWinter);
        for (int i = 0; i < 24; i++) {
            System.out.println(i+"-"+(i+1)+": "+String.format("%.2f", winterHouseHourlyLoad[i])+"kw");
        }

        //Total energy price of one whole day is printed below.

        System.out.println("\nWinter day total price: "+String.format("%.4f", ops.priceCalculator(winterHouseHourlyLoad))+" TL");

        System.out.println("\n---------------------------\n");

        //Daily load of the house after the shift-load operation is printed below(New State).

        System.out.println("Winter day daily load of the house after the load-shifting method is printed below.\n");

        ops.loadShift(appliancesWinter);

        for (int i = 0; i < 24; i++) {
            System.out.println(i+"-"+(i+1)+": "+String.format("%.2f", winterHouseHourlyLoad[i])+"kw");
        }

        //Total energy price of one whole load-shifted day is printed below.

        System.out.println("\nWinter day load-shifted total price: "+ String.format("%.4f",ops.priceCalculator(winterHouseHourlyLoad))+" TL");

        System.out.println("\n---------------------------\n");

        System.out.println("As seen above load-shifted price of one day is much more cheaper than the original one.");

        System.out.println("\n---------------------------\n");

        System.out.println("Maximum peak of the home load after the load-shift is 5.99kw between 1am-2am.\n");
        System.out.println("Therefore we integrated a battery with a capacity of 3kWh in the system.");

        for(int i =0;i<winterHouseHourlyLoad.length;i++){
            winterHouseHourlyLoad[i]=0;
        }

        System.out.println("\n---------------------------\n");

        System.out.println("\n---------------------------\n");

        System.out.println("\n---------------------------\n");



        System.out.println("(SUMMER VERSİON)");

        System.out.println("\n---------------------------\n");

        //Daily load of the house is printed below(Original State).

        System.out.println("Summer day daily load of the house is printed below.\n");
        summerHouseHourlyLoad = ops.houseHourlyConsumptionFinder(appliancesSummer);

        for (int i = 0; i < 24; i++) {
            System.out.println(i+"-"+(i+1)+": "+String.format("%.2f", summerHouseHourlyLoad[i])+"kw");
        }

        //Total energy price of one whole day is printed below.

        System.out.println("\nSummer day total price: "+String.format("%.4f", ops.priceCalculator(summerHouseHourlyLoad))+" TL");

        System.out.println("\n---------------------------\n");

        //Daily load of the house after the shift-load operation is printed below(New State).

        System.out.println("Summer day daily load of the house after the load-shifting method is printed below.\n");

        ops.loadShift(appliancesSummer);

        for (int i = 0; i < 24; i++) {
            System.out.println(i+"-"+(i+1)+": "+String.format("%.2f", summerHouseHourlyLoad[i])+"kw");
        }

        //Total energy price of one whole load-shifted day is printed below.

        System.out.println("\nSummer day load-shifted total price: "+ String.format("%.4f",ops.priceCalculator(summerHouseHourlyLoad))+" TL");

        System.out.println("\n---------------------------\n");

        System.out.println("As seen above, like winter version, also in the summer version, load-shifted price of one day is much more cheaper than the original one.");

        System.out.println("\n---------------------------\n");

        winterGraphics.pack();
        RefineryUtilities.centerFrameOnScreen(winterGraphics);
        winterGraphics.setVisible(true);

        winterShiftedGraphics.pack();
        RefineryUtilities.centerFrameOnScreen(winterShiftedGraphics);
        winterShiftedGraphics.setVisible(true);


        summerGraphics.pack();
        RefineryUtilities.centerFrameOnScreen(summerGraphics);
        summerGraphics.setVisible(true);

        summerShiftedGraphics.pack();
        RefineryUtilities.centerFrameOnScreen(summerShiftedGraphics);
        summerShiftedGraphics.setVisible(true);

    }

}

