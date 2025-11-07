/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.modul06;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


/**
 *
 * @author riyya
 */
public class Latihan2 {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Konverter Suhu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);
        frame.setLayout(new FlowLayout());

      
        JLabel labelCelcius = new JLabel("Celcius:");
        JTextField inputCelcius = new JTextField(10);
        JButton tombolKonversi = new JButton("Konversi");
        JLabel labelFahrenheit = new JLabel("Fahrenheit:");
        JLabel hasilFahrenheit = new JLabel(""); 

        // tombol
        tombolKonversi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String teksInput = inputCelcius.getText();
                try {
                    double celcius = Double.parseDouble(teksInput);
                    double fahrenheit = (celcius * 9 / 5) + 32;
                    hasilFahrenheit.setText(String.format("%.2f °F", fahrenheit));
                } catch (NumberFormatException ex) {
                    hasilFahrenheit.setText("Input tidak valid!");
                }
            }
        });

        frame.add(labelCelcius);
        frame.add(inputCelcius);
        frame.add(tombolKonversi);
        frame.add(labelFahrenheit);
        frame.add(hasilFahrenheit);

        frame.setVisible(true);
    }

}
