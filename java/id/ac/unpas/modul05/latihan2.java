/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.modul05;

/**
 *
 * @author riyya
 */
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class latihan2 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
         public void run() {
             JFrame frame = new JFrame("Jendela dengan label");
             frame.setSize(400, 300);
             frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
             
           JLabel label = new JLabel("ini adalah JLabel.");
           frame.add(label);
             
             frame.setVisible(true);
         }
      });
    }
}
