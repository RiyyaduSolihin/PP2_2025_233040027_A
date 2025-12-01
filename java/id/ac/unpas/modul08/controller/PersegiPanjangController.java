/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.modul08.controller;
/**
 *
 * @author riyya
 */
import id.ac.unpas.modul08.model.PersegiPanjangModel;
import id.ac.unpas.modul08.view.PersegiPanjangView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PersegiPanjangController {
    private PersegiPanjangModel model;
    private PersegiPanjangView view;

    public PersegiPanjangController(PersegiPanjangModel model, PersegiPanjangView view) {
        this.model = model;
        this.view = view;

        // Hubungkan tombol-tombol di View 
        this.view.addHitungLuasListener(new HitungLuasListener());
        this.view.addHitungKelilingListener(new HitungKelilingListener());
        this.view.addResetListener(new ResetListener());
    }

    // Listener untuk tombol Hitung Luas
    class HitungLuasListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double p = view.getPanjang();
                double l = view.getLebar();

                model.setPanjang(p);
                model.setLebar(l);
                model.hitungLuas();

                double hasilLuas = model.getLuas();
                view.setLuas(hasilLuas);
            } catch (NumberFormatException ex) {
                view.tampilkanPesanError("Masukkan angka yang valid!");
            }
        }
    }

    // Listener untuk tombol Hitung Keliling
    class HitungKelilingListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double p = view.getPanjang();
                double l = view.getLebar();

                model.setPanjang(p);
                model.setLebar(l);
                model.hitungKeliling();

                double hasilKeliling = model.getKeliling();
                view.setKeliling(hasilKeliling);
            } catch (NumberFormatException ex) {
                view.tampilkanPesanError("Masukkan angka yang valid!");
            }
        }
    }

    // Listener untuk tombol Reset
    class ResetListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.resetInput();
        }
    }
}