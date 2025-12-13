package GUI;
import javax.swing.*;
public class ChoiceWindow {
    public ChoiceWindow(){
        JFrame choice =new JFrame("choice");
        //
        JButton seeUsersButton=new JButton("see users");
        JButton addActivityButton =new JButton("add activity");
        //
        choice.add(seeUsersButton);
        choice.add(addActivityButton);
        //
        choice.setVisible(true);
    }
}
