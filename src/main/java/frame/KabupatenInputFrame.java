package frame;

import javax.swing.*;

public class KabupatenInputFrame extends JFrame{
    private JPanel panel1;
    private JPanel mainPanel;
    private JTextField idTextField;
    private JTextField namaTextField;
    private JPanel buttonPanel;
    private JButton simpanButton;
    private JButton batalButton;

    public KabupatenInputFrame(){
        batalButton.addActionListener(e -> {
            dispose();
        });
        init();
    }

    public void init(){
        setContentPane(mainPanel);
        setTitle("Input Kabupeten");
        pack();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
