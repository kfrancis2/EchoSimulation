import javax.swing.*;

public class LoginGUI {
    private JFrame frame;
    private JPanel panel;
    private Boolean status;
    private JTextField userNameInput;

    public LoginGUI() {
        status = false;
    }

    public void create() {
        frame = new JFrame();
        panel = new JPanel();

        JLabel userName = new JLabel("Username: ");
        userNameInput = new JTextField(20);

        JLabel pw = new JLabel("Password: ");
        JTextField pwInput = new JTextField(20);

        JButton login = new JButton("Log in");
        login.setActionCommand("Log in");
        login.addActionListener(new LoginListener());

        panel.add(userName);
        panel.add(userNameInput);
        panel.add(pw);
        panel.add(pwInput);
        panel.add(login);
        frame.add(panel);

        frame.setSize(800,500);
        frame.setVisible(true);
    }

    public void close() {
        frame.dispose();
    }

    public JTextField getUserNameInput() {
        return userNameInput;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
