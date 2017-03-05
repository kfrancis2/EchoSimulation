import javax.swing.*;
import java.util.HashMap;

public class PreviousCalibrationsGUI {
    private JFrame frame;
    private Calibration calibration;

    public PreviousCalibrationsGUI() {
        frame = new JFrame();
        calibration = new Calibration();
    }

    public void create() {
        JLabel instructions = new JLabel("Please select from the saved calibrations below:");
        JPanel panel = new JPanel();
        panel.add(instructions);

        HashMap<String, HashMap<String, ImagingRegion>> savedCals = calibration.getCalibrationsFromFirebase();

        for (String key : savedCals.keySet()) {
            JButton button = new JButton(key);
            button.setActionCommand(key);
            button.addActionListener(new PreviousCalibrationsListener());
            panel.add(button);
        }

        frame.add(panel);
        frame.setSize(800,500);
        frame.setVisible(true);
    }

    public void close() {
        frame.dispose();
    }
}
