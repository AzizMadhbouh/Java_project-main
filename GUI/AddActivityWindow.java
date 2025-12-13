package GUI;
import javax.swing.*;
public class AddActivityWindow {
    public AddActivityWindow(){
        JFrame activity =new JFrame("ADD ACTIVITY");
        //
        JLabel l1 =new JLabel("activity");
        JTextField activityField=new JTextField(); 
        JLabel l2=new JLabel("type");
        JTextField type=new JTextField(); 
        JLabel l3=new JLabel("date");
        JTextField date=new JTextField(); 
        //
        JButton okButton =new JButton();
        JButton cancelButton=new JButton();
        //
        activity.add(l1);
        activity.add(activityField);
        activity.add(l2);
        activity.add(type);
        activity.add(l3);
        activity.add(date);
        activity.add(okButton);
        activity.add(cancelButton);
    }

}
