package GUI;
import javax.swing.*;
public class HomeWindow {
    JFrame home;
    public HomeWindow() {

        home = new JFrame("Welcome");
        home.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        home.setSize(300, 200);

        JButton inscrire = new JButton("S'inscrire");
        JButton connecter = new JButton("Se connecter");

        home.add(inscrire);
        home.add(connecter);

        home.setVisible(true);
    }
}
