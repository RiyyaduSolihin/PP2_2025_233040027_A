/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.modul09;
/**
 *
 * @author riyya
 */
import javax.swing.*;
import java.awt.*;
import java.io.*;

public class AplikasiFileIO extends JFrame {

    // UI
    private JTextArea textArea;
    private JFileChooser fileChooser;

    // Tombol
    private JButton btnOpenText, btnSaveText, btnAppendText;
    private JButton btnSaveBinary, btnLoadBinary;
    private JButton btnSaveObject, btnLoadObject;

    public AplikasiFileIO() {
        super("Aplikasi File IO Lengkap");
        setSize(750, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        textArea = new JTextArea();
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        fileChooser = new JFileChooser();

        // Tombol
        btnOpenText = new JButton("Buka Text");
        btnSaveText = new JButton("Simpan Text");
        btnAppendText = new JButton("Append Text");
        btnSaveBinary = new JButton("Simpan Config (Binary)");
        btnLoadBinary = new JButton("Muat Config (Binary)");
        btnSaveObject = new JButton("Simpan UserConfig");
        btnLoadObject = new JButton("Muat UserConfig");

        JPanel panel = new JPanel();
        panel.add(btnOpenText);
        panel.add(btnSaveText);
        panel.add(btnAppendText);
        panel.add(btnSaveBinary);
        panel.add(btnLoadBinary);
        panel.add(btnSaveObject);
        panel.add(btnLoadObject);

        add(new JScrollPane(textArea), BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);

        // Event
        btnOpenText.addActionListener(e -> bukaFileTeks());
        btnSaveText.addActionListener(e -> simpanFileTeks());
        btnAppendText.addActionListener(e -> appendFileTeks());
        btnSaveBinary.addActionListener(e -> simpanConfigBinary());
        btnLoadBinary.addActionListener(e -> muatConfigBinary());
        btnSaveObject.addActionListener(e -> simpanUserConfig());
        btnLoadObject.addActionListener(e -> muatUserConfig());

        // Latihan 2
        bacaLastNotes();
    }

    //
    // BUKA FILE TEKS
    // 
    private void bukaFileTeks() {
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            BufferedReader reader = null;

            try {
                reader = new BufferedReader(new FileReader(file));
                textArea.setText("");
                String line;
                while ((line = reader.readLine()) != null) {
                    textArea.append(line + "\n");
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Gagal membaca file");
            } finally {
                try {
                    if (reader != null) reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 
    // SIMPAN FILE (OVERWRITE)
    // 
    private void simpanFileTeks() {
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                 BufferedWriter last = new BufferedWriter(new FileWriter("last_notes.txt"))) {

                writer.write(textArea.getText());
                last.write(textArea.getText());

                JOptionPane.showMessageDialog(this, "File berhasil disimpan");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Gagal menyimpan file");
            }
        }
    }

    // 
    // APPEND FILE (TAMBAH ISI)
    // 
    private void appendFileTeks() {
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            try (BufferedWriter writer =
                         new BufferedWriter(new FileWriter(file, true))) {

                writer.newLine();
                writer.write(textArea.getText());

                JOptionPane.showMessageDialog(this, "Teks berhasil ditambahkan");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Gagal append file");
            }
        }
    }

    // 
    // AUTO LOAD last_notes.txt
    // 
    private void bacaLastNotes() {
        File file = new File("last_notes.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            textArea.setText("");
            while ((line = reader.readLine()) != null) {
                textArea.append(line + "\n");
            }
        } catch (IOException e) {
            // jika tidak ada, diam saja
        }
    }

    // 
    // SIMPAN CONFIG BINARY
    // 
    private void simpanConfigBinary() {
        try (DataOutputStream dos =
                     new DataOutputStream(new FileOutputStream("config.bin"))) {

            dos.writeInt(textArea.getFont().getSize());
            JOptionPane.showMessageDialog(this, "Config binary disimpan");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Gagal simpan binary");
        }
    }

    // 
    // MUAT CONFIG BINARY
    // 
    private void muatConfigBinary() {
        try (DataInputStream dis =
                     new DataInputStream(new FileInputStream("config.bin"))) {

            int size = dis.readInt();
            textArea.setFont(new Font("Monospaced", Font.PLAIN, size));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Config belum ada");
        }
    }

    // 
    // SIMPAN OBJECT (SERIALIZATION)
    // 
    private void simpanUserConfig() {
        UserConfig config = new UserConfig();
        config.setUsername("Iyad");
        config.setFontSize(textArea.getFont().getSize());

        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream("userconfig.ser"))) {

            oos.writeObject(config);
            JOptionPane.showMessageDialog(this, "UserConfig disimpan");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Gagal simpan object");
        }
    }

    // 
    // MUAT OBJECT (DESERIALIZATION)
    //
    private void muatUserConfig() {
        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream("userconfig.ser"))) {

            UserConfig config = (UserConfig) ois.readObject();
            textArea.setFont(new Font("Monospaced", Font.PLAIN, config.getFontSize()));

            JOptionPane.showMessageDialog(this,
                    "Username: " + config.getUsername());
        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Gagal muat object");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
                new AplikasiFileIO().setVisible(true)
        );
    }
}


