/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.modul10.controller;

/**
 *
 * @author riyya
 */
import id.ac.unpas.modul10.db.KoneksiDB;
import id.ac.unpas.modul10.view.MahasiswaView;

import javax.swing.*;
import java.sql.*;

public class MahasiswaController {
    private final MahasiswaView view;

    public MahasiswaController(MahasiswaView view) {
        this.view = view;
        initListeners();
        loadData();
    }

    private void initListeners() {
        view.btnSimpan.addActionListener(e -> tambahData());
        view.btnEdit.addActionListener(e -> ubahData());
        view.btnHapus.addActionListener(e -> hapusData());
        view.btnClear.addActionListener(e -> kosongkanForm());
        view.btnCari.addActionListener(e -> cariData(view.txtCari.getText()));
        view.tableMahasiswa.getSelectionModel().addListSelectionListener(e -> isiFormDariTabel());
    }

    private void loadData() {
        view.model.setRowCount(0);
        try (Connection conn = KoneksiDB.configDB();
             Statement stm = conn.createStatement();
             ResultSet res = stm.executeQuery("SELECT * FROM mahasiswa")) {
            int no = 1;
            while (res.next()) {
                view.model.addRow(new Object[]{
                        no++,
                        res.getString("nama"),
                        res.getString("nim"),
                        res.getString("jurusan")
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Gagal Load Data: " + e.getMessage());
        }
    }

    private void tambahData() {
        String nama = view.txtNama.getText().trim();
        String nim = view.txtNIM.getText().trim();
        String jurusan = view.txtJurusan.getText().trim();

        if (nama.isEmpty() || nim.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Data tidak boleh kosong!");
            return;
        }

        try (Connection conn = KoneksiDB.configDB()) {
            PreparedStatement cek = conn.prepareStatement("SELECT * FROM mahasiswa WHERE nim = ?");
            cek.setString(1, nim);
            ResultSet rs = cek.executeQuery();
            if (rs.next()) {
                JOptionPane.showMessageDialog(view, "NIM sudah terdaftar!");
                return;
            }

            PreparedStatement pst = conn.prepareStatement("INSERT INTO mahasiswa (nama, nim, jurusan) VALUES (?, ?, ?)");
            pst.setString(1, nama);
            pst.setString(2, nim);
            pst.setString(3, jurusan);
            pst.execute();
            JOptionPane.showMessageDialog(view, "Data Berhasil Disimpan");
            loadData();
            kosongkanForm();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Gagal Simpan: " + e.getMessage());
        }
    }

    private void ubahData() {
        try (Connection conn = KoneksiDB.configDB()) {
            PreparedStatement pst = conn.prepareStatement("UPDATE mahasiswa SET nama = ?, jurusan = ? WHERE nim = ?");
            pst.setString(1, view.txtNama.getText());
            pst.setString(2, view.txtJurusan.getText());
            pst.setString(3, view.txtNIM.getText());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(view, "Data Berhasil Diubah!");
            loadData();
            kosongkanForm();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Gagal Edit: " + e.getMessage());
        }
    }

    private void hapusData() {
        try (Connection conn = KoneksiDB.configDB()) {
            PreparedStatement pst = conn.prepareStatement("DELETE FROM mahasiswa WHERE nim = ?");
            pst.setString(1, view.txtNIM.getText());
            pst.execute();
            JOptionPane.showMessageDialog(view, "Data Berhasil Dihapus");
            loadData();
            kosongkanForm();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Gagal Hapus: " + e.getMessage());
        }
    }

    private void cariData(String keyword) {
        view.model.setRowCount(0);
        try (Connection conn = KoneksiDB.configDB()) {
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM mahasiswa WHERE nama LIKE ?");
            pst.setString(1, "%" + keyword + "%");
            ResultSet res = pst.executeQuery();
            int no = 1;
            while (res.next()) {
                view.model.addRow(new Object[]{
                        no++,
                        res.getString("nama"),
                        res.getString("nim"),
                        res.getString("jurusan")
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Gagal Cari Data: " + e.getMessage());
        }
    }

    private void kosongkanForm() {
        view.txtNama.setText("");
        view.txtNIM.setText("");
        view.txtJurusan.setText("");
    }

    private void isiFormDariTabel() {
        int row = view.tableMahasiswa.getSelectedRow();
        if (row != -1) {
            view.txtNama.setText(view.model.getValueAt(row, 1).toString());
            view.txtNIM.setText(view.model.getValueAt(row, 2).toString());
            view.txtJurusan.setText(view.model.getValueAt(row, 3).toString());
        }
    }
}
