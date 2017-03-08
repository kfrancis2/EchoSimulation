
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginListener implements ActionListener {
    private LoginGUI loginGUI;

    public LoginListener() {
        loginGUI = ProbeDetection.getLoginGUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("Log in")) {
            //TODO: get log in info from LoginGUI; validate with Firebase? OR just take username not password;
            String username = loginGUI.getUserNameInput().getText();
            if (!username.equals("")) {
                ProbeDetection.setUsername(username);
                loginGUI.setStatus(true);
                loginGUI.close();
            } else {
                JOptionPane.showMessageDialog(null, "Please enter a username.");
            }
        }
    }
}
