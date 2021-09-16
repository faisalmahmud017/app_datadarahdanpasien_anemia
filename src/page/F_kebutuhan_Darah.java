/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package page;

//import com.placeholder.PlaceHolder;
import connection.koneksi;
import java.awt.HeadlessException;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Akbar Rifai
 */
public class F_kebutuhan_Darah extends javax.swing.JFrame {

    DefaultTableModel tabel_model;

    /**
     * Creates new form F_Perkantong_Darah
     */
    public F_kebutuhan_Darah() {
        initComponents();
        setIconImage(java.awt.Toolkit.getDefaultToolkit().getImage("src/icon/Logo_yayasan.png"));
        setTitle("(Data Kebutuhan Kantong Darah Pasien) - Aplikasi treatment darah pasien Anemia");

//        PlaceHolder holder1 = new PlaceHolder(cari_no_medis, "No. Medis / NIK ");
//        PlaceHolder holder2 = new PlaceHolder(cari_tabel_data, "N0. Treatment / NO. Medis");

        tabel_model_darah();
        tampil_database();
waktu();

        no_rek_medis.setEnabled(false);
        nama_pasien.setEnabled(false);
        nik_pasien.setEnabled(false);
        lahir.setEnabled(false);
        gol_dar.setEnabled(false);
        jns_kelamin.setEnabled(false);
        jml_darah.setEnabled(false);

        harga_kantong.setEnabled(false);
        total_harga.setEnabled(false);
        btn_edit.setEnabled(false);
        btn_delete.setEnabled(false);
    }

    private void waktu() {
//        this.setLocationRealtiveTo(null);
        new Thread() {
            int waktumulai;

            @Override
            public void run() {
                while (waktumulai == 0) {
                    Calendar kalender = new GregorianCalendar();
                    int tahun = kalender.get(Calendar.YEAR);
                    int bulan = kalender.get(Calendar.MONTH) + 1;
                    int hari = kalender.get(Calendar.DAY_OF_MONTH);
                    int jam = kalender.get(Calendar.HOUR_OF_DAY);
                    int menit = kalender.get(Calendar.MINUTE);
                    int detik = kalender.get(Calendar.SECOND);
                    String tanggal = hari + " / " + bulan + " / " + tahun;
                    String waktu = jam + " : " + menit + " : " + detik;
//                    if (AM_PM == 1) {
//                        siang_malam = "PM";
//                    } else {
//                        siang_malam = "AM";
//                    }
//                    String time = jam + " : " + menit + " : " + detik;
//                    String day = siang_malam;
                    lbltanggal.setText(tanggal);
                    lbljam.setText(waktu);
                }
            }
        }.start();
    }

    public void tabel_model_darah() {
        tabel_model = new DefaultTableModel();
        tabel_darah.setModel(tabel_model);
        tabel_model.addColumn("NO.Rekam Medis");
        tabel_model.addColumn("Nama");
        tabel_model.addColumn("NIK");
        tabel_model.addColumn("Tgl Lahir");
        tabel_model.addColumn("Gol Darah");
        tabel_model.addColumn("Gender");
        tabel_model.addColumn("Jml Darah dibutuhkan");
        tabel_model.addColumn("Jml CC Darah");
        tabel_model.addColumn("Jml Kantong Darah");
        tabel_model.addColumn("Total Harga Darah");
        tabel_model.addColumn("Tanggal");
    }

    public void tampil_database() {
        try {

// memanggil data pasien dari database yaitu di tabel pasien yg ada di databas
            koneksi connect = new koneksi(); // memanggil class koneksi
            connect.konek();
            Connection con = connect.con;
            Statement stat = connect.stm;
            String sql = "SELECT * FROM kebutuhan_darah_pasien ORDER BY no_rek_medis DESC";
            ResultSet res = stat.executeQuery(sql);

//pengecekan terhadap data pasien di database
// get.String disesuaikan dengan field data pasien yg di databse yaitu di tabel pasien
            while (res.next()) {
                Object[] obj = new Object[14];
                obj[0] = res.getString("no_rek_medis");
                obj[1] = res.getString("nama");
                obj[2] = res.getString("nik");
                obj[3] = res.getString("tgl_lahir");
                obj[4] = res.getString("gol_darah");
                obj[5] = res.getString("jen_kel");
                obj[6] = res.getString("darah_dibutuhkan");
                obj[7] = res.getString("jml_cc_darah");
                obj[8] = res.getString("jml_kantong");
                obj[9] = res.getString("total_harga");
                obj[10] = res.getString("tanggal");

                tabel_model.addRow(obj);
            }
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, err.getMessage());
        }
    }

    public void bersihkaninput() {
        no_rek_medis.setText("");
        nama_pasien.setText("");
        nik_pasien.setText("");
        lahir.setDate(null);
        gol_dar.setText("");
        jns_kelamin.setText("");
        jml_darah.setText("");
        cc_darah.setText("");
        kantong_darah.setText("");
        total_harga.setText("");
        tanggal.setDate(null);

        btn_edit.setEnabled(false);
        btn_delete.setEnabled(false);
        save.setEnabled(true);
        no_rek_medis.setEnabled(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField3 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        no_rek_medis = new javax.swing.JTextField();
        nama_pasien = new javax.swing.JTextField();
        nik_pasien = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jns_kelamin = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jml_darah = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        cc_darah = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        harga_kantong = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        total_harga = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        kantong_darah = new javax.swing.JTextField();
        save = new javax.swing.JButton();
        clean = new javax.swing.JButton();
        jToggleButton1 = new javax.swing.JToggleButton();
        jLabel13 = new javax.swing.JLabel();
        tanggal = new com.toedter.calendar.JDateChooser();
        gol_dar = new javax.swing.JTextField();
        lahir = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_darah = new javax.swing.JTable()
        {
            public boolean isCellEditable(int rowIndex, int colIndex)
            {
                return false; //Disallow the editing of any cell
            }
        };
        cari_tabel_data = new javax.swing.JTextField();
        cari_pasien = new javax.swing.JButton();
        btn_edit = new javax.swing.JToggleButton();
        btn_delete = new javax.swing.JToggleButton();
        jLabel14 = new javax.swing.JLabel();
        lbltanggal = new javax.swing.JLabel();
        lbljam = new javax.swing.JLabel();
        cari_no_medis = new javax.swing.JTextField();
        btn_rek_medis = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
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

        jTextField3.setText("jTextField3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 102, 102));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icon_data_darah~1.png"))); // NOI18N
        jLabel1.setText("DATA KANTONG DARAH PASIEN");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 3, -1, 50));

        jPanel2.setBackground(new java.awt.Color(255, 102, 102));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("No.Rekam Medis");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Nama");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, -1, -1));

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("NIK");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, -1));

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Tanggal Lahir");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, -1, -1));

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Golongan Darah");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, -1, -1));

        no_rek_medis.setBackground(java.awt.Color.white);
        no_rek_medis.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        no_rek_medis.setForeground(new java.awt.Color(0, 0, 0));
        jPanel2.add(no_rek_medis, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 30, 180, 30));

        nama_pasien.setBackground(java.awt.Color.white);
        nama_pasien.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        nama_pasien.setForeground(new java.awt.Color(0, 0, 0));
        jPanel2.add(nama_pasien, new org.netbeans.lib.awtextra.AbsoluteConstraints(138, 80, 180, -1));

        nik_pasien.setBackground(java.awt.Color.white);
        nik_pasien.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        nik_pasien.setForeground(new java.awt.Color(0, 0, 0));
        nik_pasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nik_pasienActionPerformed(evt);
            }
        });
        jPanel2.add(nik_pasien, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 120, 180, -1));

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Tanggal");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 280, -1, -1));

        jns_kelamin.setBackground(java.awt.Color.white);
        jns_kelamin.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jns_kelamin.setForeground(new java.awt.Color(0, 0, 0));
        jPanel2.add(jns_kelamin, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 260, 180, -1));

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Jumlah Darah Yang Dibutuhkan");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 30, -1, -1));

        jml_darah.setBackground(java.awt.Color.white);
        jml_darah.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jml_darah.setForeground(new java.awt.Color(0, 0, 0));
        jPanel2.add(jml_darah, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 30, 170, -1));

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Jumlah CC Darah");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 70, -1, -1));

        cc_darah.setBackground(java.awt.Color.white);
        cc_darah.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        cc_darah.setForeground(new java.awt.Color(0, 0, 0));
        jPanel2.add(cc_darah, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 70, 170, -1));

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Harga Per Kantong");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 160, -1, -1));

        harga_kantong.setBackground(java.awt.Color.white);
        harga_kantong.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        harga_kantong.setForeground(new java.awt.Color(0, 0, 0));
        harga_kantong.setText("975000");
        jPanel2.add(harga_kantong, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 150, 170, -1));

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Total Harga Darah");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 200, 130, -1));

        total_harga.setBackground(java.awt.Color.white);
        total_harga.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        total_harga.setForeground(new java.awt.Color(0, 0, 0));
        jPanel2.add(total_harga, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 230, 250, -1));

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Jumlah Kantong Darah");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 110, -1, -1));

        kantong_darah.setBackground(java.awt.Color.white);
        kantong_darah.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        kantong_darah.setForeground(new java.awt.Color(0, 0, 0));
        jPanel2.add(kantong_darah, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 110, 170, -1));

        save.setBackground(new java.awt.Color(0, 255, 0));
        save.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Save_25px.png"))); // NOI18N
        save.setText("SAVE");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });
        jPanel2.add(save, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 200, 105, -1));

        clean.setBackground(new java.awt.Color(255, 255, 51));
        clean.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        clean.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/broom_25px.png"))); // NOI18N
        clean.setText("CLEAN");
        clean.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cleanActionPerformed(evt);
            }
        });
        jPanel2.add(clean, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 260, 105, -1));

        jToggleButton1.setText("Hitung Harga");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jToggleButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 190, -1, -1));

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Jenis Kelamin");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, -1, -1));

        tanggal.setBackground(java.awt.Color.white);
        tanggal.setForeground(new java.awt.Color(0, 0, 0));
        jPanel2.add(tanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 270, 200, -1));

        gol_dar.setBackground(new java.awt.Color(255, 255, 255));
        gol_dar.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        gol_dar.setForeground(new java.awt.Color(0, 0, 0));
        jPanel2.add(gol_dar, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 220, 180, -1));

        lahir.setBackground(new java.awt.Color(255, 255, 255));
        lahir.setForeground(new java.awt.Color(0, 0, 0));
        jPanel2.add(lahir, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 170, 180, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, 1010, 320));

        tabel_darah.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "NO.Rekam Medis", "Nama", "NIK", "Tgl Lahir", "Gol Darah", "Gender", "Jml Darah dibutuhkan", "Jml CC Darah", "Jml Kantong Darah", "Total Harga Darah", "Tanggal"
            }
        ));
        tabel_darah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_darahMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel_darah);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 490, 1290, 160));

        cari_tabel_data.setBackground(java.awt.Color.white);
        cari_tabel_data.setForeground(new java.awt.Color(0, 0, 0));
        cari_tabel_data.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cari_tabel_dataActionPerformed(evt);
            }
        });
        jPanel1.add(cari_tabel_data, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 440, 220, 30));

        cari_pasien.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        cari_pasien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/search_25px.png"))); // NOI18N
        cari_pasien.setText("CARI");
        cari_pasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cari_pasienActionPerformed(evt);
            }
        });
        jPanel1.add(cari_pasien, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 440, -1, 30));

        btn_edit.setBackground(new java.awt.Color(255, 153, 153));
        btn_edit.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        btn_edit.setForeground(new java.awt.Color(0, 0, 0));
        btn_edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/edit_25px.png"))); // NOI18N
        btn_edit.setText("EDIT");
        btn_edit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editActionPerformed(evt);
            }
        });
        jPanel1.add(btn_edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 430, -1, 40));

        btn_delete.setBackground(new java.awt.Color(255, 0, 0));
        btn_delete.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btn_delete.setForeground(new java.awt.Color(0, 0, 0));
        btn_delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/waste_25px.png"))); // NOI18N
        btn_delete.setText("DELETE");
        btn_delete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteActionPerformed(evt);
            }
        });
        jPanel1.add(btn_delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 430, -1, -1));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/3652191.png"))); // NOI18N
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 0, -1, 55));

        lbltanggal.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lbltanggal.setForeground(new java.awt.Color(0, 0, 0));
        lbltanggal.setText("tanggal");
        jPanel1.add(lbltanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 10, -1, -1));

        lbljam.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lbljam.setForeground(new java.awt.Color(0, 0, 0));
        lbljam.setText("jam");
        jPanel1.add(lbljam, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 30, -1, -1));

        cari_no_medis.setBackground(new java.awt.Color(255, 255, 255));
        cari_no_medis.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(cari_no_medis, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 200, -1));

        btn_rek_medis.setText("CARI");
        btn_rek_medis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_rek_medisActionPerformed(evt);
            }
        });
        jPanel1.add(btn_rek_medis, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 60, 100, -1));

        jButton2.setText("Refresh Tabel");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 450, -1, -1));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/print_25px.png"))); // NOI18N
        jButton1.setText("Print");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 440, -1, -1));

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1312, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 660, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        // TODO add your handling code here:
        koneksi connect = new koneksi(); // memanggil class koneksi
        connect.konek();
        Connection conn = connect.con;

        String rek_medis = no_rek_medis.getText();
        String nama = nama_pasien.getText();
        String nik = nik_pasien.getText();

        String tgl_lahir = null;
        if (lahir.getDate() != null) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            tgl_lahir = format.format(lahir.getDate());
        }

        String gol_darah = gol_dar.getText();

        String jenkel = jns_kelamin.getText();
        String jmlah_darah = jml_darah.getText();
        String jml_cc_darah = cc_darah.getText();
        String kantong = kantong_darah.getText();
        String total = total_harga.getText();

        String tgl_darah = null;
        if (tanggal.getDate() != null) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            tgl_darah = format.format(tanggal.getDate());
        }

        try {
            koneksi DB = new koneksi();
            DB.konek();
            Connection con = DB.con;
            Statement stat = con.createStatement();

            String sql = "INSERT INTO kebutuhan_darah_pasien (no_rek_medis,nama,nik,tgl_lahir,gol_darah,jen_kel,darah_dibutuhkan,jml_cc_darah,jml_kantong,total_harga,tanggal)"
                    + " VALUES('" + rek_medis + "','" + nama + "','" + nik + "','" + tgl_lahir + "','" + gol_darah + ""
                    + "','" + jenkel + "','" + jmlah_darah + "','" + jml_cc_darah + "','" + kantong + "','" + total + ""
                    + "','" + tgl_darah + "')";

            stat.executeUpdate(sql);
            String data[] = new String[14];
            data[0] = rek_medis;
            data[1] = nama;
            data[2] = nik;
            data[3] = tgl_lahir;
            data[4] = gol_darah;
            data[5] = jenkel;
            data[6] = jmlah_darah;
            data[7] = jml_cc_darah;
            data[8] = kantong;
            data[9] = total;
            data[10] = tgl_darah;

            JOptionPane.showMessageDialog(null, "Data berhasil tersimpan !!", "Succes", JOptionPane.INFORMATION_MESSAGE);
            bersihkaninput();
            tabel_model.insertRow(0, data);
            stat.close();
            con.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Kesalahan Input", "Peringatan", JOptionPane.WARNING_MESSAGE);
            System.err.println(e.getMessage());
        }
    }//GEN-LAST:event_saveActionPerformed

    private void cari_tabel_dataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cari_tabel_dataActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cari_tabel_dataActionPerformed

    private void cari_pasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cari_pasienActionPerformed
        // TODO add your handling code here:
        String cari_norm = cari_tabel_data.getText();

        tabel_model = new DefaultTableModel();
        tabel_darah.setModel(tabel_model);

        tabel_darah.setModel(tabel_model);
        tabel_model.addColumn("NO.Rekam Medis");
        tabel_model.addColumn("Nama");
        tabel_model.addColumn("NIK");
        tabel_model.addColumn("Tgl Lahir");
        tabel_model.addColumn("Gol Darah");
        tabel_model.addColumn("Gender");
        tabel_model.addColumn("Jml Darah dibutuhkan");
        tabel_model.addColumn("Jml CC Darah");
        tabel_model.addColumn("Jml Kantong Darah");
        tabel_model.addColumn("Total Harga Darah");
        tabel_model.addColumn("Tanggal");

        try {

            koneksi connect = new koneksi(); // memanggil class koneksi
            connect.konek();
            Connection con = connect.con;
            Statement stat = connect.stm;
            String sql = "SELECT * FROM kebutuhan_darah_pasien WHERE nik LIKE '%" + cari_norm + "%'"
                     + " OR no_rek_medis LIKE '%" + cari_norm + "%'";
            ResultSet res = stat.executeQuery(sql);

            while (res.next()) {
                Object[] obj = new Object[14];
                obj[0] = res.getString("no_rek_medis");
                obj[1] = res.getString("nama");
                obj[2] = res.getString("nik");
                obj[3] = res.getString("tgl_lahir");
                obj[4] = res.getString("gol_darah");
                obj[5] = res.getString("jen_kel");
                obj[6] = res.getString("darah_dibutuhkan");
                obj[7] = res.getString("jml_cc_darah");
                obj[8] = res.getString("jml_kantong");
                obj[9] = res.getString("total_harga");
                obj[10] = res.getString("tanggal");

                tabel_model.addRow(obj);

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data tidak ditemukan" + e.getMessage());
        }
    }//GEN-LAST:event_cari_pasienActionPerformed

    private void cleanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cleanActionPerformed
        // TODO add your handling code here:
        bersihkaninput();
          no_rek_medis.setEnabled(false);
    }//GEN-LAST:event_cleanActionPerformed

    private void btn_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editActionPerformed
        // TODO add your handling code here:

        String jml_cc_darah = cc_darah.getText();
        String kantong = kantong_darah.getText();
        String total = total_harga.getText();
no_rek_medis.setEnabled(false);
        String tgl_darah = null;
        if (tanggal.getDate() != null) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            tgl_darah = format.format(tanggal.getDate());
        }

        int row = tabel_darah.getSelectedRow();
        if (row >= 0) {
            int ok = JOptionPane.showConfirmDialog(null, "Yakin Mengedit Data Ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (ok == 0) {
                try {
                    koneksi DB = new koneksi();
                    DB.konek();
                    Connection con = DB.con;
                    String sql = "UPDATE kebutuhan_darah_pasien SET jml_cc_darah ='" + jml_cc_darah + "', jml_kantong='" + kantong + "',"
                            + "total_harga='" + total + "', tanggal='" + tgl_darah + "'"
                            + " WHERE no_rek_medis='" + tabel_darah.getValueAt(row, 0).toString() + "';";
                    PreparedStatement st = con.prepareStatement(sql);
                    st.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Edit Data Sukses");
                    bersihkaninput();
                    btn_edit.setEnabled(false);
                    tabel_model_darah();
                    tampil_database();

                } catch (HeadlessException | SQLException e) {
                    JOptionPane.showMessageDialog(null, "Edit Data Gagal", "Pesan", JOptionPane.ERROR_MESSAGE);
                    System.err.print("err: " + e.getMessage());
                }
            }
        }
    }//GEN-LAST:event_btn_editActionPerformed

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
        // TODO add your handling code here:

        int row = tabel_darah.getSelectedRow();
        if (row >= 0) {
            int ok = JOptionPane.showConfirmDialog(null, "Yakin Mau Hapus?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (ok == 0) {
                try {
                    koneksi DB = new koneksi();
                    DB.konek();
                    Connection con = DB.con;
                    String sql = "DELETE FROM kebutuhan_darah_pasien WHERE no_rek_medis='" + tabel_darah.getValueAt(row, 0).toString() + "';";
                    PreparedStatement st = con.prepareStatement(sql);
                    st.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Delete Data Sukses");
                    btn_edit.setEnabled(false);
                    btn_delete.setEnabled(false);
                    save.setEnabled(true);
                    bersihkaninput();
                    tabel_model.removeRow(row);
                } catch (HeadlessException | SQLException e) {
                    JOptionPane.showMessageDialog(null, "Delete Data gagal\n" + e.getMessage());
                }
            }
        }
    }//GEN-LAST:event_btn_deleteActionPerformed

    private void nik_pasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nik_pasienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nik_pasienActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        // TODO add your handling code here:
        int jml_kantong = Integer.parseInt(kantong_darah.getText());
        int harga_kantong_darah = Integer.parseInt(harga_kantong.getText());

        int total = jml_kantong * harga_kantong_darah;

        total_harga.setText(Integer.toString(total));
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void tabel_darahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_darahMouseClicked
        // TODO add your handling code here:
        save.setEnabled(false);
        btn_edit.setEnabled(true);
        btn_delete.setEnabled(true);
        no_rek_medis.setEnabled(false);
        nama_pasien.setEnabled(false);
        nik_pasien.setEnabled(false);
        lahir.setEnabled(false);
        gol_dar.setEnabled(false);
        total_harga.setEnabled(false);
        try {
            DefaultTableModel model = (DefaultTableModel) tabel_darah.getModel();
            int index = tabel_darah.getSelectedRow();
            java.util.Date tgl = new SimpleDateFormat("yyyy-MM-dd").parse((String) model.getValueAt(index, 3));
            lahir.setDate(tgl);
        } catch (ParseException ex) {
            Logger.getLogger(F_kebutuhan_Darah.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            DefaultTableModel model = (DefaultTableModel) tabel_darah.getModel();
            int index = tabel_darah.getSelectedRow();
            java.util.Date tgl = new SimpleDateFormat("yyyy-MM-dd").parse((String) model.getValueAt(index, 10));
            tanggal.setDate(tgl);
        } catch (ParseException ex) {
            Logger.getLogger(F_kebutuhan_Darah.class.getName()).log(Level.SEVERE, null, ex);
        }

        int i = tabel_darah.getSelectedRow();
        if (i > -1) {
            no_rek_medis.setText(tabel_darah.getValueAt(i, 0).toString());
            nama_pasien.setText(tabel_darah.getValueAt(i, 1).toString());
            nik_pasien.setText(tabel_darah.getValueAt(i, 2).toString());
            gol_dar.setText(tabel_darah.getValueAt(i, 4).toString());
            jns_kelamin.setText(tabel_darah.getValueAt(i, 5).toString());
            jml_darah.setText(tabel_darah.getValueAt(i, 6).toString());
            cc_darah.setText(tabel_darah.getValueAt(i, 7).toString());
            kantong_darah.setText(tabel_darah.getValueAt(i, 8).toString());
            total_harga.setText(tabel_darah.getValueAt(i, 9).toString());
        }
    }//GEN-LAST:event_tabel_darahMouseClicked

    private void btn_rek_medisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_rek_medisActionPerformed
        // TODO add your handling code here:
        String cari_norm = cari_no_medis.getText();
//        cari_no_medis.setText("");

        try {

            koneksi connect = new koneksi(); // memanggil class koneksi
            connect.konek();
           Connection con = connect.con;
            Statement stat = connect.stm;

            String sql = "SELECT * FROM treatment_rekam_medis WHERE no_rek_medis='" + cari_norm + "'";
            ResultSet res = stat.executeQuery(sql);

            while (res.next()) {
                no_rek_medis.setText(res.getString("no_rek_medis"));
                nama_pasien.setText(res.getString("nama"));
                nik_pasien.setText(res.getString("nik"));
                lahir.setDate(res.getDate("tgl_lahir"));
                gol_dar.setText(res.getString("gol_darah"));
                jns_kelamin.setText(res.getString("jen_kel"));
                jml_darah.setText(res.getString("jml_darah"));

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data yang dicari tidak terdaftar : \n" + e.getMessage(), "Pesan", JOptionPane.ERROR_MESSAGE);
            System.err.print("err: " + e.getMessage());
        }
    }//GEN-LAST:event_btn_rek_medisActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        tabel_model_darah();
        tampil_database();
    }//GEN-LAST:event_jButton2ActionPerformed

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
            String namafile = "src/laporan/laporandarah_pasien.jasper";
            koneksi DB = new koneksi();
            DB.konek();
            Connection con = DB.con;
            
            HashMap parameter = new HashMap();
            
            parameter.put("nom_medis", no_rek_medis.getText());
             File report_file = new File(namafile);
             JasperReport jasperReport=(JasperReport) JRLoader.loadObject(report_file.getPath());
            JasperPrint jp = JasperFillManager.fillReport(jasperReport, parameter, con);
            JasperViewer.viewReport(jp, false);
      
            
            

        } catch (JRException e) {
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
            java.util.logging.Logger.getLogger(F_kebutuhan_Darah.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(F_kebutuhan_Darah.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(F_kebutuhan_Darah.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(F_kebutuhan_Darah.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new F_kebutuhan_Darah().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btn_delete;
    private javax.swing.JToggleButton btn_edit;
    private javax.swing.JButton btn_rek_medis;
    private javax.swing.JTextField cari_no_medis;
    private javax.swing.JButton cari_pasien;
    private javax.swing.JTextField cari_tabel_data;
    private javax.swing.JTextField cc_darah;
    private javax.swing.JButton clean;
    private javax.swing.JTextField gol_dar;
    private javax.swing.JTextField harga_kantong;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JTextField jml_darah;
    private javax.swing.JTextField jns_kelamin;
    private javax.swing.JTextField kantong_darah;
    private com.toedter.calendar.JDateChooser lahir;
    private javax.swing.JLabel lbljam;
    private javax.swing.JLabel lbltanggal;
    private javax.swing.JTextField nama_pasien;
    private javax.swing.JTextField nik_pasien;
    private javax.swing.JTextField no_rek_medis;
    private javax.swing.JButton save;
    private javax.swing.JTable tabel_darah;
    private com.toedter.calendar.JDateChooser tanggal;
    private javax.swing.JTextField total_harga;
    // End of variables declaration//GEN-END:variables
}
