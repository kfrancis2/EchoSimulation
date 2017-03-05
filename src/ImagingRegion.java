
import java.util.HashMap;

public class ImagingRegion {
    private int regionNum;
    private double xAngMin;
    private double yAngMin;
    private double zAngMin;
    private double xAngMax;
    private double yAngMax;
    private double zAngMax;

    public ImagingRegion(int regionNum, double xAng, double yAng, double zAng) {
        double angTol = 34.00001;

        this.regionNum = regionNum;
        this.xAngMin = xAng - angTol;
        this.xAngMax = xAng + angTol;
        this.yAngMin = yAng - angTol;
        this.yAngMax = yAng + angTol;
        this.zAngMin = zAng - angTol;
        this.zAngMax = zAng + angTol;
        System.out.println("xAng: " + xAngMin + ", " + xAngMax);
        System.out.println("yAng: " + yAngMin + ", " + yAngMax);
        System.out.println("zAng: " + zAngMin + ", " + zAngMax);
    }

    public ImagingRegion(HashMap<String, Double> hashMap) {
        this.xAngMin = hashMap.get("xAngMin");
        this.xAngMax = hashMap.get("xAngMax");
        this.yAngMin = hashMap.get("yAngMin");
        this.yAngMax = hashMap.get("yAngMax");
        this.zAngMin = hashMap.get("zAngMin");
        this.zAngMax = hashMap.get("zAngMax");
    }


    public double getxAngMin() {
        return xAngMin;
    }

    public double getyAngMin() {
        return yAngMin;
    }

    public double getzAngMin() {
        return zAngMin;
    }

    public double getxAngMax() {
        return xAngMax;
    }

    public double getyAngMax() {
        return yAngMax;
    }

    public double getzAngMax() {
        return zAngMax;
    }

    public int getRegionNum() {
        return regionNum;
    }
}
