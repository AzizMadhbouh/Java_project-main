package GUI;

import javax.swing.*;

public class RegisterWindow {
    public RegisterWindow(){
        JFrame register=new JFrame();
        //
        JLabel p1 =new JLabel("Name");
        JTextField name =new JTextField();

        JLabel p2=new JLabel("First Name");
        JTextField firstName=new JTextField();

        JLabel p3=new JLabel("E-Mail");
        JTextField mail=new JTextField();

        JLabel p4=new JLabel("Club");
        JTextField club=new JTextField();
        //
        JButton okButton=new JButton("ok");
        JButton cancelButton=new JButton("cancel");
        //
        register.add(p1);
        register.add(p2);
        register.add(p3);
        register.add(p4);
        register.add(name);
        register.add(firstName);
        register.add(mail);
        register.add(club);
        register.add(okButton);
        register.add(cancelButton);
        //
        register.setVisible(true);

    }
}
