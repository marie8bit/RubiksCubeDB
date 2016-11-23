import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 * Created by yd7581ku on 11/15/2016.
 */
public class RubiksCubeForm extends JFrame implements WindowListener
{
    private JTable tRecords;
    private JPanel rootPanel;
    private JTextField ttxtHolder;
    private JTextField txtRecord;
    private JButton addButton;
    private JButton deleteButton;
    private Object newV;
    private Object oldV;


    protected  RubiksCubeForm(TableModel tm, CubeDB db){
        setContentPane(rootPanel);
        pack();
        addWindowListener(this);
        setVisible(true);
        setSize(new Dimension(600, 650));
        tRecords.setModel(tm);
        tRecords.setGridColor(Color.BLACK);
        tRecords.getColumnModel().getColumn(0).setPreferredWidth(200);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        tRecords.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {

                if ( "tableCellEditor".equals(evt.getPropertyName())) {
                    String primary = tRecords.getValueAt(tRecords.getSelectedRow(), 0).toString();
                    newV = evt.getNewValue();
                    oldV = evt.getOldValue();
                    if (oldV != null) {
                        TableCellEditor oldVal = (TableCellEditor) oldV;
                        //Object s = oldVal.getCellEditorValue();
                        String newValue = oldVal.getCellEditorValue().toString();
                        int col = tRecords.getSelectedColumn();
                        int rw =tRecords.getSelectedRow();

                        String oldValue = tRecords.getValueAt(rw,col).toString();
                        try {
                            ResultSet updateResultSet = CubeDB.updateResultSet(col, newValue, primary);
                            TableModel tM = new TableModel(updateResultSet);
                            tRecords.setModel(tM);
                            tRecords.getColumnModel().getColumn(0).setPreferredWidth(200);

                        } catch (Exception e) {
                            e.printStackTrace();
                            System.out.println("here");
                        }
                    }

//                if (newVal!=null){
//                TableCellEditor newVal= tRecords.getCellEditor(tRecords.getSelectedRow(),tRecords.getSelectedColumn());
                    // Object val = oldV.getCellEditorValue();

//                Class<?> nv =newVal.getCellEditorValue().getClass();}
//                tRecords.getEditingColumn();
                    //if (val!=null){
                    //System.out.println(val.toString());}
//                String prop = evt.getPropertyName();
//                System.out.println(Objects.toString(newV)+prop);
//                int col = tRecords.getSelectedColumn();
//                int rw =tRecords.getSelectedRow();
//                String name = tRecords.getValueAt(rw,col).toString();
//                System.out.println(name);
//                Object ob =tRecords.getCellEditor().getCellEditorValue();
//                if ((oldV != null) && !Objects.equals(newV, oldV)){
//                    System.out.println(Objects.toString(newV)+Objects.toString(oldV));
//                };

                }
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    ResultSet updateResultSet = CubeDB.addRow(ttxtHolder.getText(),txtRecord.getText());
                    TableModel tM = new TableModel(updateResultSet);
                    tRecords.setModel(tM);
                    tRecords.getColumnModel().getColumn(0).setPreferredWidth(200);

                }
                catch(SQLException s){
                    System.out.println("HereAdd");
                }
                catch (java.lang.Exception j){
                    System.out.println("whaa?");
                }

            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String primary = tRecords.getValueAt(tRecords.getSelectedRow(), 0).toString();
                try {
                    ResultSet updateResultSet = CubeDB.deleteRow(primary);
                    TableModel tM = new TableModel(updateResultSet);
                    tRecords.setModel(tM);
                    tRecords.getColumnModel().getColumn(0).setPreferredWidth(200);

                }
                catch(SQLException s){
                    System.out.println("HereDelete");
                }
                catch (java.lang.Exception j){
                    System.out.println("whaa?");
                }
            }
        });
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
