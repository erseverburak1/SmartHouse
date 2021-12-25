public class PvPanel {
    private double electricSupply =0.1; //0.1kw = 100w of supply

    public PvPanel(double electricSupply) {
        this.electricSupply = electricSupply;
    }

    public double getElectricSupply() {
        return electricSupply;
    }
}
