import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalibrationListener implements ActionListener{
    private Calibration calibration;
    private CalibrationGUI calibrationGUI;
    private int calCounter;
    private String[] positionNames = {"Parasternal (Long Axis)", "Parasternal (Short Axis)", "Apical (4 Chamber)", "Subxiphoid (4 Chamber)", "Subxiphoid (Outlets)", "Suprasternal (Arch)"};


    public CalibrationListener() {
        calibration = new Calibration();
        calibrationGUI = ProbeDetection.getCalibrationGUI();
        calCounter = 0;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("Calibrate Position")) {
            calibration.calibrate(positionNames[calCounter]);
            if (calCounter < 4) {
                calibrationGUI.nextImage(++calCounter);
            } else {
                calCounter = 0;
                calibrationGUI.finish();
            }
        } else if (command.equals("Don't Save")) {
            ProbeDetection.setCalibratedRegions(calibration.getCalibratedRegions());
            calibrationGUI.close();
        } else if (command.equals("Save")) {
            calibrationGUI.save();
            ProbeDetection.setCalibratedRegions(calibration.getCalibratedRegions());
        } else if (command.equals("Save as")) {
            String text = CalibrationGUI.getTextField().getText();
            if (!text.equals("")) {
                calibration.sendToFirebase(text);
                calibrationGUI.close();
            } else {
                calibrationGUI.noSaveNameError();
            }
        }
    }

}
