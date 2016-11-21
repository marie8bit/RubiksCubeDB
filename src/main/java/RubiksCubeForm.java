import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;

/**
 * Created by yd7581ku on 11/15/2016.
 */
public class RubiksCubeForm extends JFrame implements WindowListener
{
    private JTable tRecords;
    private JPanel rootPanel;
    private JButton button1;

    protected  RubiksCubeForm(TableModel tm){
        setContentPane(rootPanel);
        pack();
        addWindowListener(this);
        setVisible(true);
        setSize(new Dimension(300, 150));
        tRecords.setModel(tm);
        tRecords.setGridColor(Color.BLACK);
        tRecords.getColumnModel().getColumn(0).setPreferredWidth(200);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        System.out.println("Window closing");
        Controller.shutdown();
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
