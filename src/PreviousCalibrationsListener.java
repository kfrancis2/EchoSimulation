import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class PreviousCalibrationsListener implements ActionListener {
    private HashMap<String, HashMap<String, ImagingRegion>> savedCals;
    private PreviousCalibrationsGUI previousCalibrationsGUI;

    public PreviousCalibrationsListener() {
        savedCals = ProbeDetection.getSavedCals();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        previousCalibrationsGUI = ProbeDetection.getPrevCalGUI();
        String calName = e.getActionCommand();
        HashMap<String, ImagingRegion> selectedCal = savedCals.get(calName);
        System.out.println(selectedCal);
        ProbeDetection.setCalibratedRegions(selectedCal);
        previousCalibrationsGUI.close();

    }
}
