package GUI;
import javax.swing.*;
public class HomeWindow {
    JFrame home;
    public HomeWindow() {

        home = new JFrame("Welcome");
        home.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        home.setSize(300, 200);

        
        JRadioButton option1=new JRadioButton("register");
        JRadioButton option2=new JRadioButton("login");
        ButtonGroup group =new ButtonGroup();
        group.add(option1);
        group.add(option2);
        home.add(option1);
        home.add(option2);
        
        JButton okButton=new JButton();
        JButton cancelButton =new JButton();
        home.add(okButton);
        home.add(cancelButton);

        
        home.setVisible(true);
    }
}
