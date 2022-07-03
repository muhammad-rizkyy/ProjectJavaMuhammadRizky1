package frame;

import helpers.ComboBoxItem;
import helpers.koneksi;

import javax.swing.*;
import java.sql.*;

public class JenisMotorInputFrame extends JFrame{
    private JPanel mainPanel;
    private JTextField idTextField;
    private JTextField namaTextField;
    private JPanel buttonPanel;
    private JButton simpanButton;
    private JButton batalButton;
    private JComboBox brandComboBox;
    private JRadioButton tipeARadioButton;
    private JRadioButton tipeBRadioButton;
    private JTextField silinderTextField;
    private JTextField luasTextField;
    private JLabel luasLabel;
    private ButtonGroup klasifikasiButtonGoup;

    private int id;

    public void setId(int id){
        this.id = id;
    }

    public JenisMotorInputFrame(){
        batalButton.addActionListener(e -> {
            dispose();
        });
        simpanButton.addActionListener(e ->{
            String nama = namaTextField.getText();
            if (nama.equals("")){
                JOptionPane.showMessageDialog(null,"Isi Nama Motor","Validasi kata kunci kosong",JOptionPane.WARNING_MESSAGE);
                namaTextField.requestFocus();
                return;
            }

            ComboBoxItem item = (ComboBoxItem) brandComboBox.getSelectedItem();
            int brandId = item.getValue();
            if (brandId == 0){
                JOptionPane.showMessageDialog(null,"Pilih Brand","Validasi ComboBox",JOptionPane.WARNING_MESSAGE);
                brandComboBox.requestFocus();
                return;
            }
            String klasifikasi = "";
            if (tipeARadioButton.isSelected()){
                klasifikasi = "ABS";
            } else if (tipeBRadioButton.isSelected()){
                klasifikasi = "NO ABS";
            } else {
                JOptionPane.showMessageDialog(null,"Pilih klasifikasi","Validasi Data Kosong",JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (silinderTextField.getText().equals("")){
                silinderTextField.setText("0");
            }
            int silinder = Integer.parseInt(silinderTextField.getText());
            if (silinder == 0){
                JOptionPane.showMessageDialog(null,"Isi Populasi","Validasi Data Kosong",JOptionPane.WARNING_MESSAGE);
                silinderTextField.requestFocus();
                return;
            }

            if (luasTextField.getText().equals("")){
                luasTextField.setText("0");
            }

            double diameter = Float.parseFloat(luasTextField.getText());
            if (diameter == 0){
                JOptionPane.showMessageDialog(null,"Isi Luas","Validasi Data Kosong",JOptionPane.WARNING_MESSAGE);
                luasTextField.requestFocus();
                return;
            }

//            String email = emailTextField.getText();
//            if (!email.contains("@") || !email.contains(".")){
//                JOptionPane.showMessageDialog(null,"Isi dengan email valid","Validasi Email",JOptionPane.WARNING_MESSAGE);
//                emailTextField.requestFocus();
//                return;
//            }

            Connection c = koneksi.getConnection();
            PreparedStatement ps;
            try {
                if (id == 0) {
                    String cekSQL = "SELECT * FROM jenismotor WHERE nama= ?";
                    ps = c.prepareStatement(cekSQL);
                    ps.setString(1,nama);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        JOptionPane.showMessageDialog(null,"Data sama sudah ada");
                    } else {
                        String insertSQL = "INSERT INTO jenismotor (id,nama,brand_id,klasifikasi,silinder,diameter) VALUES (NULL, ?, ?, ?, ?, ?)";
                        ps = c.prepareStatement(insertSQL);
                        ps.setString(1,nama);
                        ps.setInt(2,brandId);
                        ps.setString(3,klasifikasi);
                        ps.setInt(4,silinder);
                        ps.setDouble(5,diameter);
                        ps.executeUpdate();
                        dispose();
                    }

                } else {
                    String cekSQL = "SELECT * FROM jenismotor WHERE nama= ? AND id != ?";
                    ps = c.prepareStatement(cekSQL);
                    ps.setString(1,nama);
                    ps.setInt(2,id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        JOptionPane.showMessageDialog(null,"Data sama sudah ada");
                    } else {
                        String updateSQL = "UPDATE jenismotor SET nama= ?, brand_id= ?, klasifikasi = ?, silinder = ?, diameter = ? WHERE id= ?";
                        ps = c.prepareStatement(updateSQL);
                        ps.setString(1,nama);
                        ps.setInt(2,brandId);
                        ps.setString(3,klasifikasi);
                        ps.setInt(4,silinder);
                        ps.setDouble(5,diameter);
                        ps.setInt(6,id);
                        ps.executeUpdate();
                        dispose();
                    }

                }

            } catch (SQLException ex) {
                throw  new RuntimeException(ex);
            }
        });
        kustomisasiKomponen();
        init();
    }
    public void isiKomponen(){
        Connection c = koneksi.getConnection();
        String findSQL = "SELECT * FROM jenismotor WHERE id= ?";
        PreparedStatement ps = null;
        try {
            ps = c.prepareStatement(findSQL);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                idTextField.setText(String.valueOf(rs.getInt("id")));
                namaTextField.setText(rs.getString("nama"));
                silinderTextField.setText(String.valueOf(rs.getInt("silinder")));
                luasTextField.setText(String.valueOf(rs.getDouble("diameter")));
                int kabupatenId = rs.getInt("brand_id");
                for (int i = 0; i < brandComboBox.getItemCount(); i++){
                    brandComboBox.setSelectedIndex(i);
                    ComboBoxItem item = (ComboBoxItem) brandComboBox.getSelectedItem();
                    if (kabupatenId == item.getValue()){
                        break;
                    }
                }
                String klasifikasi = rs.getString("klasifikasi");
                if (klasifikasi != null){
                    if (klasifikasi.equals("TIPE A")){
                        tipeARadioButton.setSelected(true);
                    } else if (klasifikasi.equals("TIPE B")){
                        tipeBRadioButton.setSelected(true);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void init(){
        setContentPane(mainPanel);
        setTitle("Input Jenis Motor");
        pack();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public void kustomisasiKomponen(){
        Connection c = koneksi.getConnection();
        String selectSQL = "SELECT * FROM brandmotor ORDER BY nama";
        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(selectSQL);
            brandComboBox.addItem(new ComboBoxItem(0,"Pilih Brand"));
            while (rs.next()){
                brandComboBox.addItem(new ComboBoxItem(rs.getInt("id"),rs.getString("nama")));
            }
        } catch (SQLException ex){
            throw new RuntimeException(ex);
        }
        klasifikasiButtonGoup = new ButtonGroup();
        klasifikasiButtonGoup.add(tipeARadioButton);
        klasifikasiButtonGoup.add(tipeBRadioButton);

    }
    }

