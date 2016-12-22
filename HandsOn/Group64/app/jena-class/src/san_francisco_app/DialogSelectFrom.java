package san_francisco_app;
import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class DialogSelectFrom extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox<String> comboBox1;
    private String result;

    DialogSelectFrom() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(e -> onOK());

        buttonCancel.addActionListener(e -> onCancel());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        result = null;
    }

    private void onOK() {
        if (comboBox1.getSelectedIndex()==0)
            JOptionPane.showMessageDialog(this, "Nothing Selected");
        else {
            result = (String) comboBox1.getSelectedItem();
            this.setVisible(false);
        }
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    void setComboContent(List<String> content) {
        comboBox1.removeAllItems();
        comboBox1.addItem("-- Select one --");
        for (String title : content) {
            comboBox1.addItem(title);
        }
    }

    String getResult() {
        dispose();
        return result;
    }
}
