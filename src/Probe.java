import com.google.firebase.*;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Probe {
    private String region;
    private DatabaseReference ref;
    private String userID = "yWzgI0q39TRtvJUFhaapLFOfJAJ3";
    private HashMap<String, ImagingRegion> calibratedRegions;


    public Probe(HashMap<String, ImagingRegion> calRegions) {
        region = "No Contact";//TODO

        final FirebaseDatabase database = ProbeDetection.getDatabase();
        ref = database.getReference(userID + "/ProbeData");

        calibratedRegions = calRegions;
    }

    public void analyzeInput(String input) {
        String[] split = input.split(",");
        String newRegion = "";
        if (split.length == 4) {
            int sensorRegion = Integer.parseInt(split[0]);
            double xAng = Double.parseDouble(split[1]);
            double yAng = Double.parseDouble(split[2]);
            double zAng = Double.parseDouble(split[3]);

            ImagingRegion checkRegion;

            for (String positionName : calibratedRegions.keySet()) {
                checkRegion = calibratedRegions.get(positionName);

                if (checkRegion.getRegionNum() == sensorRegion) {
                    if (xAng > checkRegion.getxAngMin() && xAng < checkRegion.getxAngMax() && yAng > checkRegion.getyAngMin() && yAng < checkRegion.getyAngMax() && zAng > checkRegion.getzAngMin() && zAng < checkRegion.getzAngMax()) {
                        newRegion = positionName;
                    }
                }
            }
        } else if (input.equals("No Contact")) {
            newRegion = "No Contact"; //REGION = 6 IF NOT TOUCHING ONE OF THE 4 SENSORS
        } else {
            newRegion = "None"; //REGION = 7 IF NOT SURE WHAT'S HAPPENING
        }
        System.out.println("NewRegion = " + newRegion);
        sendToFirebase(newRegion);
    }

    public void sendToFirebase(String newRegion) {
        if (!newRegion.equals(region)) {
            HashMap<String, String> regionMap = new HashMap<>();
            regionMap.put("region", newRegion);
            ref.setValue(regionMap);
            region = newRegion;
        }
    }
}
