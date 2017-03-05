import com.google.firebase.database.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class Calibration {
    private HashMap<String, ImagingRegion> calibratedRegions;
    private DatabaseReference ref;
    private String userID = "yWzgI0q39TRtvJUFhaapLFOfJAJ3";
    private FirebaseDatabase database;
    static Boolean wait;
    private String[] positionNames = {"Parasternal (Long Axis)", "Parasternal (Short Axis)", "Apical (4 Chamber)", "Subxiphoid (4 Chamber)", "Subxiphoid (Outlets)", "Suprasternal (Arch)"};

    public Calibration() {
        calibratedRegions = new HashMap<>();
        database = ProbeDetection.getDatabase();
    }

    public void calibrate(String positionName) {
        String[] split = ProbeDetection.getInputLine().split(",");
        if (split.length == 4) {
            int region = Integer.parseInt(split[0]);
            double xAng = Double.parseDouble(split[1]);
            double yAng = Double.parseDouble(split[2]);
            double zAng = Double.parseDouble(split[3]);

            ImagingRegion imagingRegion = new ImagingRegion(region, xAng, yAng, zAng);
            calibratedRegions.put(positionName, imagingRegion);
            System.out.println(calibratedRegions);
        }
    }

    public HashMap<String, ImagingRegion> getCalibratedRegions() {
        return calibratedRegions;
    }

    public void sendToFirebase(String saveName) {
        ref = database.getReference(userID + "/SavedCalibrations/Sensors/" + saveName);
        ref.setValue(calibratedRegions);
    }

//    private HashMap<String, ImagingRegion> formatForFirebase() {
//        HashMap<String, ImagingRegion> newFormat = new HashMap<>();
//        for (String strKey : calibratedRegions.keySet()) {
//            newFormat.put(Integer.toString(intKey), calibratedRegions.get(intKey));
//        }
//        return newFormat;
//    }

    public HashMap<String, HashMap<String, ImagingRegion>> getCalibrationsFromFirebase() {
        ref = database.getReference(userID + "/SavedCalibrations/Sensors");

        HashMap<String, HashMap<String, ImagingRegion>> savedCals = new HashMap<>();
        wait = true;
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                HashMap<String, ImagingRegion> childMap = new HashMap<>();
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    for (int i=1; i<5; i++) { //TODO: CHANGE BASED ON NUMBER OF SENSORS!
                        try {//TODO: FIGURE OUT HOW THIS CHANGES WITH STRING KEYSET
                            childMap.put(positionNames[i], new ImagingRegion((HashMap<String, Double>) (((ArrayList) child.getValue()).get(i)))); //TODO: CHECK IF THIS WORKS!!
                            System.out.println(childMap);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    savedCals.put(child.getKey(), childMap);
                    childMap = new HashMap<>();
                }
                wait = false;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // ...
            }
        });

        while(wait) {
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        ProbeDetection.setSavedCals(savedCals);

        return savedCals;
    }
}
