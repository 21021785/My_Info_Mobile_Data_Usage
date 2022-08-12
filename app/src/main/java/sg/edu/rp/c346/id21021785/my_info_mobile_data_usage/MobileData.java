package sg.edu.rp.c346.id21021785.my_info_mobile_data_usage;

public class MobileData {

    private String quarter;
    private String volume;

    public MobileData(String quarter, String volume) {
        this.quarter = quarter;
        this.volume = volume;
    }

    public String getQuarter() {
        return quarter;
    }

    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    @Override
    public String toString() {
        return "MobileData{" +
                "quarter='" + quarter + '\'' +
                ", volume=" + volume +
                '}';
    }
}
