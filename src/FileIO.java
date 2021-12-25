import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class FileIO {
    public Appliance[] appliancesReader(String dataCsv) throws IOException { // A Reader function that creates and returns an array of Appliances.

        Scanner sc = new Scanner(new File(dataCsv));
        Appliance[] appliances = new Appliance[21];
        int count = 0;
        while (sc.hasNext()) {
            String line = sc.next();
            String[] lineSplit = line.split(",");
            Appliance applianceR = applianceConstructor(lineSplit);
            appliances[count] = applianceR;
            count++;
        }
        sc.close();
        return appliances;
    }

    private static Appliance applianceConstructor(String[] data) {      // A function that creates and returns a single appliance Object.

        String applianceName=data[0];
        int numberOfItems=Integer.parseInt(data[1]);
        double hourlyConsumed=Double.parseDouble(data[2]);
        int priority=Integer.parseInt(data[3]);
        int startingHours = Integer.parseInt(data[4]);
        int finishingHours = Integer.parseInt(data[5]);
        int workingHour=Integer.parseInt(data[6]);
        int[][] activeHours = new int[24][2];
        for (int i =startingHours;i<finishingHours;i++) {
             activeHours[i][0] = i;
             activeHours[i][1]=i+1;
        }
        return new Appliance(applianceName,numberOfItems,hourlyConsumed,priority,startingHours,finishingHours,workingHour,activeHours);
    }
}
