/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package page;

//import com.placeholder.PlaceHolder;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import connection.koneksi;
import java.io.File;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
//import javafx.scene.chart.PieChart.Data;
import javax.swing.*;
import javax.swing.table.*;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Kelompok 3 - S6G
 */
public class C_master_data_pasien extends javax.swing.JFrame {

    Statement stat;
    ResultSet rs;
    String sql;

    DefaultTableModel tabel_model;

    /**
     * Creates new form master_data_pasien
     */
    public C_master_data_pasien() {
        initComponents();
        setIconImage(java.awt.Toolkit.getDefaultToolkit().getImage("src/icon/Logo_yayasan.png"));
        setTitle("(Data Pasien) - Aplikasi treatment darah pasien Anemia");
//        PlaceHolder holder1 = new PlaceHolder(txt_cari, "Searching...");

        //pemanggilan class koneksi database yang sudah kita buat pada class koneksi.java
        koneksi DB = new koneksi();
        DB.konek();
        stat = DB.stm;

        no_treatments();
        no_treatment.setEnabled(false);
        btn_edit.setEnabled(false);
        btn_delete.setEnabled(false);
        model_tabel_pasien();
        tampil_database();
    }

    public void bersihkaninput() {
        txt_nik.setText("");
        txt_nama.setText("");
        radiobtn_jenkel.clearSelection();
        inp_ttl.setDate(null);
        txt_alamat.setText("");
        txt_tlp.setText("");
        inp_goldar.setSelectedItem("Pilih");
    }

    public void model_tabel_pasien() {
        tabel_model = new DefaultTableModel();
        //digunakan untuk memberi heading / judul /field pada kolom di tabel_pasien
        tabel_pasien.setModel(tabel_model);
        // "table_pasien sesuaikan dengan nama field jTable"
        tabel_model.addColumn("NIK");
        tabel_model.addColumn("Nama");
        tabel_model.addColumn("Jenis Kelamin");
        tabel_model.addColumn("Tanggal Lahir");
        tabel_model.addColumn("Alamat");
        tabel_model.addColumn("No. Tlp");
        tabel_model.addColumn("Gol. Darah");
        tabel_model.addColumn("No. Treament");
    }

    public void tampil_database() { // getData() dibuat untuk memanggil data pasien dari database dan menampilkannya di tabel_pasien

        try {

// memanggil data pasien dari database yaitu di tabel pasien yg ada di databas
            koneksi connect = new koneksi(); // memanggil class koneksi
            connect.konek();
            Connection con = connect.con;
            Statement stat = connect.stm;
            String sql = "SELECT * FROM pasien ORDER BY nik DESC";
            ResultSet res = stat.executeQuery(sql);

//pengecekan terhadap data pasien di database
// get.String disesuaikan dengan field data pasien yg di databse yaitu di tabel pasien
            while (res.next()) {
                Object[] obj = new Object[13];
                obj[0] = res.getString("nik");
                obj[1] = res.getString("nama");
                obj[2] = res.getString("jen_kel");
                obj[3] = res.getString("tgl_lahir");
                obj[4] = res.getString("alamat");
                obj[5] = res.getString("no_tlp");
                obj[6] = res.getString("gol_darah");
                obj[7] = res.getString("no_treatment");

                tabel_model.addRow(obj);

//
            }
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, err.getMessage());
        }
    }

    private void no_treatments() {
        try {
            koneksi connect = new koneksi(); // memanggil class koneksi
            connect.konek();
            String sql = "SELECT * FROM pasien ORDER BY no_treatment DESC";
            Statement stat = connect.stm;
            ResultSet res = stat.executeQuery(sql);

            if (res.next()) {
                String no_rekam_medis = res.getString("no_treatment").substring(3);
                String AN = "" + (Integer.parseInt(no_rekam_medis) + 1);
                String Nol = "";

                if (AN.length() == 1) {
                    Nol = "0000";
                } else if (AN.length() == 2) {
                    Nol = "000";
                } else if (AN.length() == 3) {
                    Nol = "00";
                } else if (AN.length() == 4) {
                    Nol = "0";
                } else if (AN.length() == 5) {
                    Nol = "";
                }

                no_treatment.setText("NTH" + Nol + AN);

            } else {
                no_treatment.setText("NTH00001");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.println("Error" + e.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        radiobtn_jenkel = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabel_pasien = new javax.swing.JTable()
        {
            public boolean isCellEditable(int rowIndex, int colIndex)
            {
                return false; //Disallow the editing of any cell
            }
        };
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txt_nik = new javax.swing.JTextField();
        txt_nama = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        rd_laki_laki = new javax.swing.JRadioButton();
        rd_perempuan = new javax.swing.JRadioButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        inp_goldar = new javax.swing.JComboBox<>();
        inp_ttl = new com.toedter.calendar.JDateChooser();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_alamat = new javax.swing.JTextArea();
        jLabel16 = new javax.swing.JLabel();
        no_treatment = new javax.swing.JTextField();
        btn_save = new javax.swing.JToggleButton();
        clean = new javax.swing.JToggleButton();
        jLabel17 = new javax.swing.JLabel();
        txt_tlp = new javax.swing.JTextField();
        txt_cari = new javax.swing.JTextField();
        btn_search = new javax.swing.JToggleButton();
        btn_edit = new javax.swing.JToggleButton();
        btn_delete = new javax.swing.JToggleButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMe = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabel_pasien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "NIK", "Nama", "Jenis Kelamin", "Tanggal Lahir", "Alamat", "No. Telp", "Gol. Darah", "No. Treatment"
            }
        ));
        tabel_pasien.setRowHeight(20);
        tabel_pasien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_pasienMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabel_pasien);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 450, 1060, 190));

        jPanel3.setBackground(new java.awt.Color(153, 153, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setText("NIK");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 17, 136, 28));

        txt_nik.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jPanel3.add(txt_nik, new org.netbeans.lib.awtextra.AbsoluteConstraints(168, 17, 231, -1));

        txt_nama.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jPanel3.add(txt_nama, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 60, 231, -1));

        jLabel6.setText("Nama");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 136, 22));

        jLabel3.setText("Jenis Kelamin");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 136, -1));

        rd_laki_laki.setBackground(new java.awt.Color(255, 255, 255));
        radiobtn_jenkel.add(rd_laki_laki);
        rd_laki_laki.setText("Laki-laki");
        rd_laki_laki.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        rd_laki_laki.setOpaque(false);
        rd_laki_laki.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rd_laki_lakiActionPerformed(evt);
            }
        });
        jPanel3.add(rd_laki_laki, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 110, -1, 18));

        rd_perempuan.setBackground(new java.awt.Color(255, 255, 255));
        radiobtn_jenkel.add(rd_perempuan);
        rd_perempuan.setText("Perempuan");
        rd_perempuan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        rd_perempuan.setOpaque(false);
        rd_perempuan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rd_perempuanActionPerformed(evt);
            }
        });
        jPanel3.add(rd_perempuan, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 110, -1, 18));

        jLabel12.setText("Tanggal Lahir");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 136, 28));

        jLabel15.setText("Golongan Darah");
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 80, 138, 28));

        inp_goldar.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        inp_goldar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih", "A", "B", "O", "AB" }));
        jPanel3.add(inp_goldar, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 80, 89, -1));

        inp_ttl.setBackground(new java.awt.Color(255, 255, 255));
        inp_ttl.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                inp_ttlPropertyChange(evt);
            }
        });
        jPanel3.add(inp_ttl, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 140, 231, 28));

        jLabel18.setText("Alamat");
        jPanel3.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 127, 28));

        txt_alamat.setColumns(20);
        txt_alamat.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        txt_alamat.setRows(5);
        jScrollPane1.setViewportView(txt_alamat);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 180, 228, -1));

        jLabel16.setText("No. Treatment");
        jPanel3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 140, 86, 28));

        no_treatment.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        no_treatment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                no_treatmentActionPerformed(evt);
            }
        });
        jPanel3.add(no_treatment, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 140, 140, -1));

        btn_save.setBackground(new java.awt.Color(102, 204, 0));
        btn_save.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        btn_save.setForeground(new java.awt.Color(0, 0, 51));
        btn_save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Save_25px.png"))); // NOI18N
        btn_save.setText("SAVE");
        btn_save.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_saveActionPerformed(evt);
            }
        });
        jPanel3.add(btn_save, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 230, -1, -1));

        clean.setBackground(new java.awt.Color(255, 255, 0));
        clean.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        clean.setForeground(new java.awt.Color(0, 0, 51));
        clean.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/broom_25px.png"))); // NOI18N
        clean.setText("CLEAN");
        clean.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        clean.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cleanActionPerformed(evt);
            }
        });
        jPanel3.add(clean, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 230, -1, -1));

        jLabel17.setText("No. Telp");
        jPanel3.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 30, 86, 28));

        txt_tlp.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        txt_tlp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tlpActionPerformed(evt);
            }
        });
        jPanel3.add(txt_tlp, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 30, 231, -1));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 70, 880, 290));

        txt_cari.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txt_cari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_cariKeyReleased(evt);
            }
        });
        jPanel1.add(txt_cari, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, 200, 35));

        btn_search.setBackground(new java.awt.Color(204, 204, 204));
        btn_search.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/search_25px.png"))); // NOI18N
        btn_search.setText("Searching");
        btn_search.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_searchActionPerformed(evt);
            }
        });
        jPanel1.add(btn_search, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 390, -1, 35));

        btn_edit.setBackground(new java.awt.Color(255, 153, 153));
        btn_edit.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        btn_edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/edit_25px.png"))); // NOI18N
        btn_edit.setText("EDIT");
        btn_edit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editActionPerformed(evt);
            }
        });
        jPanel1.add(btn_edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 390, -1, -1));

        btn_delete.setBackground(new java.awt.Color(255, 0, 0));
        btn_delete.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btn_delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/waste_25px.png"))); // NOI18N
        btn_delete.setText("DELETE");
        btn_delete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteActionPerformed(evt);
            }
        });
        jPanel1.add(btn_delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 390, -1, -1));

        jButton2.setText("Refresh Tabel");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 410, -1, -1));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icon laporan~1.png"))); // NOI18N
        jLabel1.setText("DATA PASIEN");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 0, -1, -1));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/print_25px.png"))); // NOI18N
        jButton1.setText("Print");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 390, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1120, 670));

        jMenuBar1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dashboard_15px.png"))); // NOI18N
        jMenu1.setText("Menu");
        jMenu1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        jMenuItem5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jMenuItem5.setText("Halaman Menu");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem5);

        jMenuItem6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jMenuItem6.setText("Daftar Obat");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem6);

        jMenuItem7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jMenuItem7.setText("Data Pasien");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem7);

        jMenuItem8.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jMenuItem8.setText("Data Treatment Pasien");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem8);

        jMenuItem9.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jMenuItem9.setText("Data Obat Kelator Besi");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem9);

        jMenuItem12.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jMenuItem12.setText("Data Kebutuhan Darah Pasien");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem12);

        jMenuItem10.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jMenuItem10.setText("Data Tagihan Pasien");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem10);

        jMenuBar1.add(jMenu1);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/pasien_15px.png"))); // NOI18N
        jMenu2.setText("Data pasien");
        jMenu2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        jMe.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jMe.setText("Reservasi Data Pasien");
        jMe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMeActionPerformed(evt);
            }
        });
        jMenu2.add(jMe);

        jMenuItem1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jMenuItem1.setText("Treatment Pasien");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuItem3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jMenuItem3.setText("Obat Kelator Besi");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuItem11.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jMenuItem11.setText("Kebutuhan Darah Pasien");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem11);

        jMenuItem4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jMenuItem4.setText("Tagihan Pasien");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void no_treatmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_no_treatmentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_no_treatmentActionPerformed

    private void btn_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_saveActionPerformed
        // TODO add your handling code here:
        // memanggil data pasien dari database yaitu di tabel pasien yg ada di database
        koneksi connect = new koneksi(); // memanggil class koneksi
        connect.konek();
        Connection conn = connect.con;
        String nik = txt_nik.getText();
        String nama = txt_nama.getText();
        String JK = null;
        if (rd_laki_laki.isSelected()) { // Jika rd_Laki_laki di pilih, maka String JK akan terisi text yg di ambil dari rd_laki-laki, jika tidak maka String JK akan terisi text dari rd_Perempuan.
            JK = rd_laki_laki.getText();
        } else {
            JK = rd_perempuan.getText();
        }

        String tgl_lahir = null;
        if (inp_ttl.getDate() != null) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            tgl_lahir = format.format(inp_ttl.getDate());
        }
        String alamat = txt_alamat.getText();
        String no_telp = txt_tlp.getText();

        String gol_darah = (String) inp_goldar.getSelectedItem(); // untuk mengambil nilai dari jComboBox  berdasarkan list item dengan perintah getSelectedItem() ke data String.
        if (gol_darah == "A") { // kondisi data yang diambil dari jComboBox berdasarkan list item, gunakan operator pemberi nilai sama dengan (==), dan untuk nilai kondisi karakternya harus sama dengan karakter list item jComboBox.
            gol_darah = "A";
        } else if (gol_darah == "B") {
            gol_darah = "B";
        } else if (gol_darah == "O") {
            gol_darah = "O";
        } else if (gol_darah == "AB") {
            gol_darah = "AB";
        } else if (gol_darah == "Pilih") {
            gol_darah = "";
        }

        String notreatment = no_treatment.getText();

        try {
            koneksi DB = new koneksi();
            DB.konek();
            Connection con = DB.con;
            Statement stat = con.createStatement();

            sql = "INSERT INTO pasien (nik,nama,jen_kel,tgl_lahir,alamat,no_tlp,gol_darah,no_treatment) "
                    + "VALUES('" + nik + "','" + nama + "','" + JK + "','" + tgl_lahir + "','" + alamat + ""
                    + "','" + no_telp + "','" + gol_darah + "','" + notreatment + "')";

            stat.executeUpdate(sql);
            String data[] = new String[11];
            data[0] = nik;
            data[1] = nama;
            data[2] = JK;
            data[3] = tgl_lahir;
            data[4] = alamat;
            data[5] = no_telp;
            data[6] = gol_darah;
            data[7] = notreatment;
            JOptionPane.showMessageDialog(null, "Data berhasil tersimpan !!", "Succes", JOptionPane.INFORMATION_MESSAGE);
            bersihkaninput();
            no_treatments();
            tabel_model.insertRow(0, data);
            stat.close();
            con.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Kesalahan Input", "Peringatan", JOptionPane.WARNING_MESSAGE);
            System.err.println(e.getMessage());
        }

    }//GEN-LAST:event_btn_saveActionPerformed

    private void rd_laki_lakiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rd_laki_lakiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rd_laki_lakiActionPerformed

    private void rd_perempuanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rd_perempuanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rd_perempuanActionPerformed

    private void inp_ttlPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_inp_ttlPropertyChange
        // TODO add your handling code here:
        if (inp_ttl.getDate() != null) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String tgl_lahir = format.format(inp_ttl.getDate());
        }
    }//GEN-LAST:event_inp_ttlPropertyChange

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
        // TODO add your handling code here:
        String nik = txt_nik.getText();
        String nama = txt_nama.getText();

        int row = tabel_pasien.getSelectedRow();
        if (row >= 0) {
            int ok = JOptionPane.showConfirmDialog(null, "Yakin Mau Hapus?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (ok == 0) {
                try {
                    koneksi DB = new koneksi();
                    DB.konek();
                    Connection con = DB.con;
                    String sql = "DELETE FROM pasien WHERE nik='" + tabel_pasien.getValueAt(row, 0).toString() + "';";
                    PreparedStatement st = con.prepareStatement(sql);
                    st.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Delete Data Sukses");
                    btn_edit.setEnabled(false);
                    btn_delete.setEnabled(false);
                    btn_save.setEnabled(true);
                    no_treatments();
                    bersihkaninput();
                    tabel_model.removeRow(row);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Delete Data gagal\n" + e.getMessage());
                }
            }
        }
    }//GEN-LAST:event_btn_deleteActionPerformed

    private void btn_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_searchActionPerformed
        // TODO add your handling code here:
        String cari = txt_cari.getText();
        tabel_model = new DefaultTableModel();
        //digunakan untuk memberi heading / judul /field pada kolom di tabel_pasien
        tabel_pasien.setModel(tabel_model);
        // "table_pasien sesuaikan dengan nama field jTable"
        tabel_model.addColumn("NIK");
        tabel_model.addColumn("Nama");
        tabel_model.addColumn("Jenis Kelamin");
        tabel_model.addColumn("Tanggal Lahir");
        tabel_model.addColumn("Alamat");
        tabel_model.addColumn("No. Tlp");
        tabel_model.addColumn("Gol. Darah");
        tabel_model.addColumn("No. Treatment");

        try {

            koneksi connect = new koneksi(); // memanggil class koneksi
            connect.konek();
            Connection con = connect.con;
            Statement stat = connect.stm;
            String sql = "SELECT * FROM pasien WHERE nik='"+cari+"'";
            ResultSet res = stat.executeQuery(sql);

//pengecekan terhadap data pasien di database
// get.String disesuaikan dengan field data pasien yg di databse yaitu di tabel pasien
            while (res.next()) {
                Object[] obj = new Object[13];
                obj[0] = res.getString("nik");
                obj[1] = res.getString("nama");
                obj[2] = res.getString("jen_kel");
                obj[3] = res.getString("tgl_lahir");
                obj[4] = res.getString("alamat");
                obj[5] = res.getString("no_tlp");
                obj[6] = res.getString("gol_darah");
                obj[7] = res.getString("no_treatment");

                tabel_model.addRow(obj);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }//GEN-LAST:event_btn_searchActionPerformed

    private void txt_cariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cariKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_cariKeyReleased

    private void tabel_pasienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_pasienMouseClicked
        // TODO add your handling code here:
        btn_save.setEnabled(false);
        btn_edit.setEnabled(true);
        btn_delete.setEnabled(true);
        String jen_kel = tabel_model.getValueAt(tabel_pasien.getSelectedRow(), 2).toString();
        if (jen_kel.equals(rd_laki_laki.getActionCommand())) {
            rd_laki_laki.setSelected(true);
        } else {
            rd_perempuan.setSelected(true);
        }

        try {
            DefaultTableModel model = (DefaultTableModel) tabel_pasien.getModel();
            int index = tabel_pasien.getSelectedRow();
            java.util.Date tgl = new SimpleDateFormat("yyyy-MM-dd").parse((String) model.getValueAt(index, 3));
            inp_ttl.setDate(tgl);
        } catch (ParseException ex) {
            Logger.getLogger(C_master_data_pasien.class.getName()).log(Level.SEVERE, null, ex);
        }

        int i = tabel_pasien.getSelectedRow();
        if (i > -1) {
            txt_nik.setText(tabel_pasien.getValueAt(i, 0).toString());
            txt_nama.setText(tabel_pasien.getValueAt(i, 1).toString());
            txt_alamat.setText(tabel_pasien.getValueAt(i, 4).toString());
            txt_tlp.setText(tabel_pasien.getValueAt(i, 5).toString());
            inp_goldar.setSelectedItem(tabel_pasien.getValueAt(i, 6).toString());
            no_treatment.setText(tabel_pasien.getValueAt(i, 7).toString());
        }
    }//GEN-LAST:event_tabel_pasienMouseClicked

    private void cleanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cleanActionPerformed
        // TODO add your handling code here:
        btn_save.setEnabled(true);
        btn_edit.setEnabled(false);
        btn_delete.setEnabled(false);
        bersihkaninput();
    }//GEN-LAST:event_cleanActionPerformed

    private void btn_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editActionPerformed
        // TODO add your handling code here:
        String nik = txt_nik.getText();
        String nama = txt_nama.getText();
        String JK = "";
        if (rd_laki_laki.isSelected()) { // Jika rd_Laki_laki di pilih, maka String JK akan terisi text yg di ambil dari rd_laki-laki, jika tidak maka String JK akan terisi text dari rd_Perempuan.
            JK = rd_laki_laki.getText();
        } else {
            JK = rd_perempuan.getText();
        }

        String tgl_lahir = null;
        if (inp_ttl.getDate() != null) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            tgl_lahir = format.format(inp_ttl.getDate());
        }
        String alamat = txt_alamat.getText();
        String no_telp = txt_tlp.getText();

        String gol_darah = (String) inp_goldar.getSelectedItem(); // untuk mengambil nilai dari jComboBox  berdasarkan list item dengan perintah getSelectedItem() ke data String.
        if (gol_darah == "A") { // kondisi data yang diambil dari jComboBox berdasarkan list item, gunakan operator pemberi nilai sama dengan (==), dan untuk nilai kondisi karakternya harus sama dengan karakter list item jComboBox.
            gol_darah = "A";
        } else if (gol_darah == "B") {
            gol_darah = "B";
        } else if (gol_darah == "O") {
            gol_darah = "O";
        } else if (gol_darah == "AB") {
            gol_darah = "AB";
        }

        String notreatment = no_treatment.getText();

        int row = tabel_pasien.getSelectedRow();
        if (row >= 0) {
            int ok = JOptionPane.showConfirmDialog(null, "Yakin Mengedit Data Ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (ok == 0) {
                try {
                    koneksi DB = new koneksi();
                    DB.konek();
                    Connection con = DB.con;
                    String sql = "UPDATE pasien SET nik ='" + nik + "', nama='" + nama + "',"
                            + "jen_kel='" + JK + "', tgl_lahir='" + tgl_lahir + "', alamat='" + alamat + "',"
                            + "no_tlp='" + no_telp + "',"
                            + "gol_darah='" + gol_darah + "', no_treatment='" + notreatment + "'"
                            + " WHERE nik='" + tabel_pasien.getValueAt(row, 0).toString() + "';";

                    PreparedStatement st = con.prepareStatement(sql);
                    st.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Edit Data Sukses");
                    btn_edit.setEnabled(false);
                    btn_delete.setEnabled(false);
                    btn_save.setEnabled(true);
                    bersihkaninput();
//                    btn_edit.setEnabled(false);
                    model_tabel_pasien();
                    tampil_database();

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Edit Data Gagal\n" + e.getMessage(), "Pesan", JOptionPane.ERROR_MESSAGE);
                    System.err.print("err: " + e.getMessage());
                }
            }
        }
    }//GEN-LAST:event_btn_editActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        model_tabel_pasien();
        tampil_database();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txt_tlpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tlpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tlpActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        new B_Homepage().setVisible(true);
        dispose();
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
        new Master_Daftar_Obat().setVisible(true);
        dispose();
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // TODO add your handling code here:
        new C_master_data_pasien().setVisible(true);
        dispose();
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        // TODO add your handling code here:
        new D_master_treatment_pasien().setVisible(true);
        dispose();
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        // TODO add your handling code here:
        new E_obat_kelator_besi().setVisible(true);
        dispose();
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        // TODO add your handling code here:
        new F_kebutuhan_Darah().setVisible(true);
        dispose();
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        // TODO add your handling code here:
        new G_Tagihan_Pasien().setVisible(true);
        dispose();
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
        // TODO add your handling code here:
        new B_Homepage().setVisible(true);
        dispose();
    }//GEN-LAST:event_jMenu1ActionPerformed

    private void jMeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMeActionPerformed
        // TODO add your handling code here:
        new C_master_data_pasien().setVisible(true);
        dispose();
    }//GEN-LAST:event_jMeActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        new D_master_treatment_pasien().setVisible(true);
        dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        new E_obat_kelator_besi().setVisible(true);
        dispose();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        // TODO add your handling code here:
        new F_kebutuhan_Darah().setVisible(true);
        dispose();
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        new G_Tagihan_Pasien().setVisible(true);
        dispose();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try {
            String namafile = "src/laporan/laporan_data_pasien.jasper";
            koneksi DB = new koneksi();
            DB.konek();
            Connection con = DB.con;
            
            HashMap parameter = new HashMap();
            
            
             File report_file = new File(namafile);
             JasperReport jasperReport=(JasperReport) JRLoader.loadObject(report_file);
            JasperPrint jp = JasperFillManager.fillReport(jasperReport, parameter, con);
            JasperViewer.viewReport(jp, false);
      JasperViewer.setDefaultLookAndFeelDecorated(true);
            
            

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data Tidak dapat dicetak\n" + e.getMessage(), "Pesan", JOptionPane.ERROR_MESSAGE);
            System.err.print("err: " + e.getMessage());
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(C_master_data_pasien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(C_master_data_pasien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(C_master_data_pasien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(C_master_data_pasien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new C_master_data_pasien().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btn_delete;
    private javax.swing.JToggleButton btn_edit;
    private javax.swing.JToggleButton btn_save;
    private javax.swing.JToggleButton btn_search;
    private javax.swing.JToggleButton clean;
    private javax.swing.JComboBox<String> inp_goldar;
    private com.toedter.calendar.JDateChooser inp_ttl;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenuItem jMe;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField no_treatment;
    private javax.swing.ButtonGroup radiobtn_jenkel;
    private javax.swing.JRadioButton rd_laki_laki;
    private javax.swing.JRadioButton rd_perempuan;
    private javax.swing.JTable tabel_pasien;
    private javax.swing.JTextArea txt_alamat;
    private javax.swing.JTextField txt_cari;
    private javax.swing.JTextField txt_nama;
    private javax.swing.JTextField txt_nik;
    private javax.swing.JTextField txt_tlp;
    // End of variables declaration//GEN-END:variables
}
