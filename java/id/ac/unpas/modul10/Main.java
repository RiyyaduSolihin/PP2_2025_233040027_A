/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.modul10;

/**
 *
 * @author riyya
 */
import id.ac.unpas.modul10.view.MahasiswaView;
import id.ac.unpas.modul10.controller.MahasiswaController;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MahasiswaView view = new MahasiswaView();
            new MahasiswaController(view);
            view.setVisible(true);
        });
    }
}