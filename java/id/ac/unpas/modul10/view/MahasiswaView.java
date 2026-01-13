/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.modul10.view;
/**
 *
 * @author riyya
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MahasiswaView extends JFrame {
    public JTextField txtNama, txtNIM, txtJurusan, txtCari;
    public JButton btnSimpan, btnEdit, btnHapus, btnClear, btnCari;
    public JTable tableMahasiswa;
    public DefaultTableModel model;

    public MahasiswaView() {
        setTitle("Aplikasi Mahasiswa MVC");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Form
        JPanel panelForm = new JPanel(new GridLayout(4, 2, 10, 10));
        panelForm.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        txtNama = new JTextField();
        txtNIM = new JTextField();
        txtJurusan = new JTextField();
        panelForm.add(new JLabel("Nama:")); panelForm.add(txtNama);
        panelForm.add(new JLabel("NIM:")); panelForm.add(txtNIM);
        panelForm.add(new JLabel("Jurusan:")); panelForm.add(txtJurusan);

        // Tombol
        JPanel panelTombol = new JPanel(new FlowLayout());
        btnSimpan = new JButton("Simpan");
        btnEdit = new JButton("Edit");
        btnHapus = new JButton("Hapus");
        btnClear = new JButton("Clear");
        panelTombol.add(btnSimpan); panelTombol.add(btnEdit);
        panelTombol.add(btnHapus); panelTombol.add(btnClear);

        // Panel Cari
        JPanel panelCari = new JPanel(new FlowLayout());
        txtCari = new JTextField(15);
        btnCari = new JButton("Cari");
        panelCari.add(new JLabel("Cari Nama:")); panelCari.add(txtCari); panelCari.add(btnCari);

        // Tabel
        model = new DefaultTableModel(new Object[]{"No", "Nama", "NIM", "Jurusan"}, 0);
        tableMahasiswa = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tableMahasiswa);

        // Layout
        JPanel panelAtas = new JPanel(new BorderLayout());
        panelAtas.add(panelCari, BorderLayout.NORTH);
        panelAtas.add(panelForm, BorderLayout.CENTER);
        panelAtas.add(panelTombol, BorderLayout.SOUTH);

        add(panelAtas, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }
}