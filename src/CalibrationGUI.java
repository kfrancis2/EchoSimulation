import javax.swing.*;

public class CalibrationGUI {
    private JFrame frame;
    private CalibrationListener calibrationListener;
    private JPanel panel;
    private JLabel instructions;
    private static JTextField textField;
    private String[] positionNames = {"Parasternal (Long Axis)", "Parasternal (Short Axis)", "Apical (4 Chamber)", "Subxiphoid (4 Chamber)", "Subxiphoid (Outlets)", "Suprasternal (Arch)"};

    public CalibrationGUI() {
        frame = new JFrame("Calibration");
        panel = new JPanel();
        instructions = new JLabel("Hold probe in correct location and angle to view desired image. Press OK to record this position.");
    }

    public void create() {
        calibrationListener = new CalibrationListener();
        JLabel imageLabel = new JLabel(positionNames[0] + " Calibration:");

        JButton okButton = new JButton("OK");
        okButton.setActionCommand("Calibrate Position");
        okButton.addActionListener(calibrationListener);

        panel.add(instructions);
        panel.add(imageLabel);
        panel.add(okButton);
        frame.add(panel);

        frame.setSize(800,500);
        frame.setVisible(true);
    }

    public void nextImage(int imageNumber) {
        frame.getContentPane().removeAll();
        panel = new JPanel();
        JLabel imageLabel = new JLabel(positionNames[imageNumber] + " Calibration:");

        JButton okButton = new JButton("OK");
        okButton.setActionCommand("Calibrate Position");
        okButton.addActionListener(calibrationListener);

        panel.add(instructions);
        panel.add(imageLabel);
        panel.add(okButton);
        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    public void finish() {
        frame.getContentPane().removeAll();
        panel = new JPanel();

        JLabel complete = new JLabel("Calibration Complete. Would you like to save this calibration?");
        JButton saveButton = new JButton("Yes");
        saveButton.setActionCommand("Save");
        saveButton.addActionListener(calibrationListener);

        JButton noSaveButton = new JButton("No");
        noSaveButton.setActionCommand("Don't Save");
        noSaveButton.addActionListener(calibrationListener);

        panel.add(complete);
        panel.add(saveButton);
        panel.add(noSaveButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    public void save() {
        frame.getContentPane().removeAll();
        panel = new JPanel();

        JLabel saveAs = new JLabel("Enter a name for your calibration:");
        textField = new JTextField(20);
        JButton saveAsButton = new JButton("Save");

        saveAsButton.setActionCommand("Save as");
        saveAsButton.addActionListener(calibrationListener);

        panel.add(saveAs);
        panel.add(textField);
        panel.add(saveAsButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    public void close() {
        frame.dispose();
    }

    static JTextField getTextField() {
        return textField;
    }

    public void noSaveNameError() {
        JOptionPane.showMessageDialog(null, "Please enter a name before saving.");
    }
}
