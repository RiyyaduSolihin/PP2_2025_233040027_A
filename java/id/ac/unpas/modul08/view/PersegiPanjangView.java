/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.modul08.view;
/**
 *
 * @author riyya
 */
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.*;

public class PersegiPanjangView extends JFrame {
    // Komponen UI sebagai atribut
    private JTextField txtPanjang = new JTextField(10);
    private JTextField txtLebar = new JTextField(10);
    private JLabel lblLuas = new JLabel("-");
    private JLabel lblKeliling = new JLabel("-");
    private JButton btnHitungLuas = new JButton("Hitung Luas");
    private JButton btnHitungKeliling = new JButton("Hitung Keliling");
    private JButton btnReset = new JButton("Reset");

    public PersegiPanjangView() {
        // Inisialisasi UI
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 400);
        this.setLayout(new GridLayout(6, 2, 10, 10)); // 6 baris, 2 kolom

        this.add(new JLabel("Panjang:"));
        this.add(txtPanjang);
        this.add(new JLabel("Lebar:"));
        this.add(txtLebar);
        this.add(new JLabel("Hasil Luas:"));
        this.add(lblLuas);
        this.add(new JLabel("Hasil Keliling:"));
        this.add(lblKeliling);

        // Tombol Reset di kiri, tombol Hitung Luas di kanan
        this.add(btnReset);
        this.add(btnHitungLuas);

        // Tombol Hitung Keliling di kiri, spacer di kanan
        this.add(btnHitungKeliling);
        this.add(new JLabel(""));
    }

    // Getter input
    public double getPanjang() {
        return Double.parseDouble(txtPanjang.getText());
    }

    public double getLebar() {
        return Double.parseDouble(txtLebar.getText());
    }

    // Setter hasil
    public void setLuas(double luas) {
        lblLuas.setText(String.valueOf(luas));
    }

    public void setKeliling(double keliling) {
        lblKeliling.setText(String.valueOf(keliling));
    }

    // Error
    public void tampilkanPesanError(String pesan) {
        JOptionPane.showMessageDialog(this, pesan);
    }

    // Reset
    public void resetInput() {
        txtPanjang.setText("");
        txtLebar.setText("");
        lblLuas.setText("-");
        lblKeliling.setText("-");
    }

    // Listener
    public void addHitungLuasListener(ActionListener listener) {
        btnHitungLuas.addActionListener(listener);
    }

    public void addHitungKelilingListener(ActionListener listener) {
        btnHitungKeliling.addActionListener(listener);
    }

    public void addResetListener(ActionListener listener) {
        btnReset.addActionListener(listener);
    }
}