import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class Frame extends JFrame implements ComponentListener  {

    private int width = 800;
    private int height = 800;

    private Object[][] data = new Object[23][2];

    private String[] columnNames = {"Done","To-Do"};
    JTable table;
    JTextField doneTextField = new JTextField();
    JTextField tasksTextField = new JTextField();



    public Frame (String[] importedData,boolean[] importedDone) {
        data = new Object[][]{{new Boolean(false), ""},
                {new Boolean(false), ""},
                {new Boolean(false), ""},
                {new Boolean(false), ""},
                {new Boolean(false), ""},
                {new Boolean(false), ""},
                {new Boolean(false), ""},
                {new Boolean(false), ""},
                {new Boolean(false), ""},
                {new Boolean(false), ""},
                {new Boolean(false), ""},
                {new Boolean(false), ""},
                {new Boolean(false), ""},
                {new Boolean(false), ""},
                {new Boolean(false), ""},
                {new Boolean(false), ""},
                {new Boolean(false), ""},
                {new Boolean(false), ""},
                {new Boolean(false), ""},
                {new Boolean(false), ""},
                {new Boolean(false), ""},
                {new Boolean(false), ""},
                {new Boolean(false), ""}


        };


        for (int i = 0; i < importedData.length && i <= 22; i++) {

               data[i][1] = (Object) importedData[i];

        }

        for (int i = 0; i < importedData.length && i <= 22; i++) {
            if (importedDone[i]) {
                data[i][0] = (Object) new Boolean(true);
            }


        }




        table = new JTable(data, columnNames) {
            @Override
            public Class getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return Boolean.class;
                    default:
                        return String.class;
                }

            }

        };
        String column_names[]= {"Done","task"};
        JTableHeader tableHeader = new JTableHeader();
        tableHeader = table.getTableHeader();

        int value = (int) (30.0 / 500.0 * width);
        table.getColumnModel().getColumn(0).setPreferredWidth(value);
        table.getColumnModel().getColumn(1).setPreferredWidth(500-value);

        table.setRowHeight(0, 60);
        table.setRowHeight(30);
        table.setBounds(0,50,width,height-50);
        table.setBorder(BorderFactory.createLineBorder(Color.black));
        table.setShowGrid(true);

        table.setGridColor(Color.black);

        table.setOpaque(true);

        doneTextField.setText("Done");
        doneTextField.setEditable(false);
        doneTextField.setBounds(0,0, value,50);
        doneTextField.setBackground(Color.CYAN);
        doneTextField.setOpaque(true);


        tasksTextField.setText("Tasks");
        tasksTextField.setEditable(false);
        tasksTextField.setBounds(value,0, width-value,50);
        tasksTextField.setBackground(Color.orange);
        tasksTextField.setOpaque(true);



        this.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                Filer filer = new Filer();
                filer.clearFile();

                String[] exportedData = new String[data.length];
                String oneLine;
                for (int i = 0; i < data.length; i++) {
                    oneLine = Boolean.toString((Boolean) data[i][0]) + "," + (String) data[i][1];
                    exportedData[i] = oneLine;
                }

                filer.writeToFile((String[]) exportedData);
                e.getWindow().dispose();
            }
        });

        this.add(doneTextField);
        this.add(tasksTextField);
        this.add(table);

        this.setLayout(null);
        this.setSize(width,height);
        this.addComponentListener(this);
        this.setTitle("To-Do List");


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.validate();
    }


    @Override
    public void componentResized(ComponentEvent e) {

        height = this.getHeight();
        width = this.getWidth();

        this.setSize(width,height);
        int value = (int) (30.0 / 500.0 * width);


        table.setBounds(0,50,width,height-50);
        table.getColumnModel().getColumn(0).setPreferredWidth(value);
        table.getColumnModel().getColumn(0).setWidth(value);
        table.getColumnModel().getColumn(1).setPreferredWidth(width-value);

        doneTextField.setBounds(0,0, value,50);
        tasksTextField.setBounds(value, 0 , width-value,50);

    }

    @Override
    public void componentMoved(ComponentEvent e) {}

    @Override
    public void componentShown(ComponentEvent e) { }

    @Override
    public void componentHidden(ComponentEvent e) {}
}


