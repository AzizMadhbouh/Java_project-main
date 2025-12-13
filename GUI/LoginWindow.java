package GUI;
import javax.swing.*;
public class LoginWindow {  
    public LoginWindow(){
        JFrame login =new JFrame("login");
        //
        JLabel l1 =new JLabel("User");
        JTextField user=new JTextField();
        JLabel l2 =new JLabel("password");
        JPasswordField password =new  JPasswordField();
        //
        JButton okButton=new JButton("ok");
        JButton cancelButton =new JButton("cancel");
        //
        login.add(l1);
        login.add(user);
        login.add(l2);
        login.add(password);
        
        login.add(okButton);
        login.add(cancelButton);
        //
        login.setVisible(true);
    }
    
}
