package frame;

import helpers.koneksi;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class KabupatenInputFrame extends JFrame{
    private JPanel panel1;
    private JPanel mainPanel;
    private JTextField idTextField;
    private JTextField namaTextField;
    private JPanel buttonPanel;
    private JButton simpanButton;
    private JButton batalButton;

    private int id;

    public void setId(int id){
        this.id = id;
    }

    public KabupatenInputFrame(){
        batalButton.addActionListener(e -> {
            dispose();
        });
        simpanButton.addActionListener(e ->{
            String nama = namaTextField.getText();
            Connection c = koneksi.getConnection();
            PreparedStatement ps;
            try {
                if (id == 0) {
                    String insertSQL = "INSERT INTO kabupaten VALUES (NULL, ?)";
                    ps = c.prepareStatement(insertSQL);
                    ps.setString(1,nama);
                    ps.executeUpdate();
                    dispose();
                } else {
                    String updateSQL = "UPDATE kabupaten SET nama= ? WHERE id= ?";
                    ps = c.prepareStatement(updateSQL);
                    ps.setString(1,nama);
                    ps.setInt(2,id);
                    ps.executeUpdate();
                    dispose();
                }

            } catch (SQLException ex) {
                throw  new RuntimeException(ex);
            }
        });
        init();
    }

    public void isiKomponen(){
        Connection c = koneksi.getConnection();
        String findSQL = "SELECT * FROM kabupaten WHERE id= ?";
        PreparedStatement ps = null;
        try {
            ps = c.prepareStatement(findSQL);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                idTextField.setText(String.valueOf(rs.getInt("id")));
                namaTextField.setText(rs.getString("nama"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void init(){
        setContentPane(mainPanel);
        setTitle("Input Kabupaten");
        pack();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
