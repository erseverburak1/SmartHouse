public class Operations {

    double[] houseHourlyConsumption= new double[24];
    PvPanel pvPanel = new PvPanel(0.1);
    Battery battery = new Battery();

    public void loadShift(Appliance[] appliances) {         //LOAD-SHIFTING method is implemented in this method.

        for (int i = 0; i < appliances.length; i++) {         // the loop for all appliances.
            for (int j = 17; j < 22; j++) {   //  the loop for appliances that works in peak times.

                if (houseHourlyConsumption[j] > 5) {  // threshold check
                    if(appliances[i].getPriority()>1){ // priority check
                        if(appliances[i].getActiveHours()[j][0]==j && appliances[i].getActiveHours()[j][1]==j+1){ // peak time check
                            for(int k =0;k<appliances[i].getWorkingHour();k++) {    // changing to a new time interval.
                                appliances[i].getActiveHours()[k][0] = k;             // shifting process
                                appliances[i].getActiveHours()[k][1] = k + 1;           // shifting process
                                houseHourlyConsumption[k] = houseHourlyConsumption[k]+(appliances[i].getHourlyConsumed()*appliances[i].getNumberOfItems()); // shifting process
                            }
                                for(int u=appliances[i].getStartingHours();u<appliances[i].getFinishingHours();u++){ // deleting its old time interval.
                                    appliances[i].getActiveHours()[u][0]=0;         // deleting process
                                    appliances[i].getActiveHours()[u][1]=0;         // deleting process
                                    houseHourlyConsumption[u] = houseHourlyConsumption[u]-(appliances[i].getHourlyConsumed()*appliances[i].getNumberOfItems()); // deleting process
                                }
                            break;
                        }
                    }
                    }
                }
            }
    }

    public double[] houseHourlyConsumptionFinder(Appliance[] appliances){ // Smart House's overall electric consumption throughout a 24 hours time interval is implemented in this method.

        for(int i =0 ; i<appliances.length;i++){    // loop for all appliances
            for(int j =0;j<24;j++){                 // loop for the hours
                for(int k=0;k<24;k++){
                    if(appliances[i].getActiveHours()[j][0]==k && appliances[i].getActiveHours()[j][1]==k+1 ){  //checking if the appliance is in the given time interval. ex:(0,1),(1-2)..
                        houseHourlyConsumption[j]=appliances[i].getHourlyConsumed()*appliances[i].getNumberOfItems()+houseHourlyConsumption[j]; //if it is, add the consumption of the appliance to that interval.
                    }
                }
            }
        }
        for(int i=8;i<=17;i++){     // PV panel's electricity supply of 100W between 8-17
            houseHourlyConsumption[i] = houseHourlyConsumption[i] -pvPanel.getElectricSupply();
        }
        for(int i =17;i<22;i++){    // Integrated battery's electric supply of 800W between the peak times.(17-22)
            houseHourlyConsumption[i] = houseHourlyConsumption[i] -battery.getDischarge();
        }
        return houseHourlyConsumption;
        }

    public double priceCalculator(double[] house){  // Calculating energy prices using triple-time tariff
        double totalPrice=0;

        for(int i =0;i<24;i++){
            if(i>=6 && i<17){       //
                totalPrice= totalPrice + houseHourlyConsumption[i]*0.7195;
            }
            if(i>=17 && i<22){
                totalPrice=totalPrice+houseHourlyConsumption[i]*1.0567;
            }
            if(i<6 || i>=22){
                totalPrice=totalPrice+houseHourlyConsumption[i]*0.4498;
            }
        }
        return totalPrice;
    }
}