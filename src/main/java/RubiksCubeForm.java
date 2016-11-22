import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.ResultSet;
import java.util.Objects;

/**
 * Created by yd7581ku on 11/15/2016.
 */
public class RubiksCubeForm extends JFrame implements WindowListener
{
    private JTable tRecords;
    private JPanel rootPanel;
    private JButton button1;
    private Object newV;
    private Object oldV;


    protected  RubiksCubeForm(TableModel tm, CubeDB db){
        setContentPane(rootPanel);
        pack();
        addWindowListener(this);
        setVisible(true);
        setSize(new Dimension(300, 150));
        tRecords.setModel(tm);
        tRecords.setGridColor(Color.BLACK);
        tRecords.getColumnModel().getColumn(0).setPreferredWidth(200);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        tRecords.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {

                if ( "tableCellEditor".equals(evt.getPropertyName())) {
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
                            tm.resultSet = db.updateResultSet(col, newValue, oldValue);
                            tRecords.setModel(tm);
                        } catch (Exception e) {
                            e.printStackTrace();
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
