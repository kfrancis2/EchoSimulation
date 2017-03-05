import javax.swing.*;

public class MainControlGUI {
    static JFrame frame;

    public MainControlGUI() {
        frame = new JFrame("Echo Control Panel");
    }

    public void create() {
        JLabel connecting = new JLabel("Connecting...");
        JButton cancel = new JButton("Cancel");
        cancel.setActionCommand("End Session");
        cancel.addActionListener(new ButtonListener());
        JPanel panel = new JPanel();

        panel.add(connecting);
        panel.add(cancel);
        frame.add(panel);

        frame.setSize(800,500);
        frame.setVisible(true);
    }

    public void connected() {
        frame.getContentPane().removeAll();

        JPanel panel = new JPanel();
        JLabel connected = new JLabel("Connected to Arduino.");
        panel.add(connected);

        JButton calibrate = new JButton("Calibrate");
        JButton usePrevious = new JButton("Use Previous Calibration");
        JButton closeButton = new JButton("Close");

        calibrate.setActionCommand("Calibrate");
        usePrevious.setActionCommand("Use Previous");
        closeButton.setActionCommand("End Session");

        ButtonListener listener = new ButtonListener();
        calibrate.addActionListener(listener);
        usePrevious.addActionListener(listener);
        closeButton.addActionListener(listener);

        panel.add(calibrate);
        panel.add(usePrevious);
        panel.add(closeButton);

        frame.add(panel);

        frame.setSize(800,500);
        frame.setVisible(true);
    }

    public void disconnected() {
        frame.getContentPane().removeAll();

        JPanel panel = new JPanel();
        JLabel connected = new JLabel("Leap Sensor has been disconnected.");
        JButton close = new JButton("Close");
        close.setActionCommand("End Session");
        close.addActionListener(new ButtonListener());

        panel.add(connected);
        panel.add(close);

        frame.add(panel);
        frame.setVisible(true);
    }

    static void close() {
        frame.dispose();
        ProbeDetection.setEndSession(true);
    }
}
