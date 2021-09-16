/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package page;

import connection.koneksi;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author ASUS
 */
public class G_Tagihan_Pasien extends javax.swing.JFrame {

    DefaultTableModel tabel_model;

    /**
     * Creates new form Tagihan_Pasien
     */
    public G_Tagihan_Pasien() {
        initComponents();
        setIconImage(java.awt.Toolkit.getDefaultToolkit().getImage("src/icon/Logo_yayasan.png"));
        setTitle("(Data Tagihan Pasien) - Aplikasi treatment darah pasien Anemia");

        waktu();
        tabel_model_tagihan();
        tampil_database();

        btn_edit.setEnabled(false);
        btn_delete.setEnabled(false);
        no_rek_medis.setEnabled(false);
        txt_nik.setEnabled(false);
        txt_nama.setEnabled(false);
        jns_kelamin.setEnabled(false);
        gol_dar.setEnabled(false);
        jns_jaminan.setEnabled(false);
        realisasi_obat.setEnabled(false);
        nama_obat.setEnabled(false);
        harga_obat.setEnabled(false);
        ttl_harga_obat.setEnabled(false);
        jml_kantong.setEnabled(false);
        hrg_kantong.setEnabled(false);
        ttl_harga_darah.setEnabled(false);
        ttl_tagihan.setEnabled(false);
    }

    public void bersihkaninput() {
        no_rm.setText("");
        no_rek_medis.setText("");
        txt_nik.setText("");
        txt_nama.setText("");
        jns_kelamin.setText("");
        gol_dar.setText("");
        jns_jaminan.setText("");
        realisasi_obat.setText("");
        nama_obat.setText("");
        harga_obat.setText("");
        ttl_harga_obat.setText("");
        jml_kantong.setText("");
        ttl_harga_darah.setText("");
        ttl_tagihan.setText("");
        tgl_tagihan.setDate(null);
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

    public void tabel_model_tagihan() {
        tabel_model = new DefaultTableModel();
        tabel_tagihan.setModel(tabel_model);

        tabel_model.addColumn("No. RM");
        tabel_model.addColumn("NIK");
        tabel_model.addColumn("NAMA");
        tabel_model.addColumn("Gender");
        tabel_model.addColumn("Goldar");
        tabel_model.addColumn("Jaminan");
        tabel_model.addColumn("Jml Obat");
        tabel_model.addColumn("Nama Obat");
        tabel_model.addColumn("Ttl Harga Darah");
        tabel_model.addColumn("Jml Kantong Darah");
        tabel_model.addColumn("Ttl Harga Darah");
        tabel_model.addColumn("Tagihan");
        tabel_model.addColumn("Tgl Tagihan");
    }

    public void tampil_database() { // getData() dibuat untuk memanggil data pasien dari database dan menampilkannya di tabel_pasien

        try {

// memanggil data pasien dari database yaitu di tabel pasien yg ada di databas
            koneksi connect = new koneksi(); // memanggil class koneksi
            connect.konek();
            Connection con = connect.con;
            Statement stat = connect.stm;
            String sql = "SELECT * FROM tagihan_pasien ORDER BY nik DESC";
            ResultSet res = stat.executeQuery(sql);

//pengecekan terhadap data pasien di database
// get.String disesuaikan dengan field data pasien yg di databse yaitu di tabel pasien
            while (res.next()) {
                Object[] obj = new Object[14];
                obj[0] = res.getString("no_rek_medis");
                obj[1] = res.getString("nik");
                obj[2] = res.getString("nama");
                obj[3] = res.getString("jen_kel");
                obj[4] = res.getString("gol_darah");
                obj[5] = res.getString("jenis_jaminan");
                obj[6] = res.getString("jml_realisasi_obat");
                obj[7] = res.getString("nama_obat");
                obj[8] = res.getString("total_harga_obat");
                obj[9] = res.getString("jml_kantong_darah");
                obj[10] = res.getString("total_harga_darah");
                obj[11] = res.getString("total_tagihan");
                obj[12] = res.getString("tanggal_tagihan");

                tabel_model.addRow(obj);

//
            }
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, err.getMessage());
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

        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        realisasi_obat = new javax.swing.JTextField();
        harga_obat = new javax.swing.JTextField();
        ttl_harga_obat = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        nama_obat = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jml_kantong = new javax.swing.JTextField();
        hrg_kantong = new javax.swing.JTextField();
        ttl_harga_darah = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        no_rek_medis = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_nama = new javax.swing.JTextField();
        jns_kelamin = new javax.swing.JTextField();
        jns_jaminan = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txt_nik = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        gol_dar = new javax.swing.JTextField();
        cari_tagihan = new javax.swing.JTextField();
        cari_pasienlagi = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        ttl_tagihan = new javax.swing.JTextField();
        tgl_tagihan = new com.toedter.calendar.JDateChooser();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_tagihan = new javax.swing.JTable()
        {
            public boolean isCellEditable(int rowIndex, int colIndex)
            {
                return false; //Disallow the editing of any cell
            }
        };
        cari_pasienlagi1 = new javax.swing.JButton();
        no_rm = new javax.swing.JTextField();
        save = new javax.swing.JButton();
        clean = new javax.swing.JButton();
        btn_edit = new javax.swing.JToggleButton();
        btn_delete = new javax.swing.JToggleButton();
        jLabel18 = new javax.swing.JLabel();
        lbltanggal = new javax.swing.JLabel();
        lbljam = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
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
        setBackground(new java.awt.Color(0, 102, 255));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(204, 204, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Cooper Black", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 0));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icon_pembayaran~1.png"))); // NOI18N
        jLabel1.setText("TAGIHAN PELAYANAN PASIEN");
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 0, -1, -1));

        jPanel3.setBackground(new java.awt.Color(51, 153, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        realisasi_obat.setBackground(new java.awt.Color(255, 255, 255));
        realisasi_obat.setForeground(new java.awt.Color(0, 0, 0));
        realisasi_obat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                realisasi_obatActionPerformed(evt);
            }
        });
        jPanel3.add(realisasi_obat, new org.netbeans.lib.awtextra.AbsoluteConstraints(165, 11, 179, -1));

        harga_obat.setBackground(new java.awt.Color(255, 255, 255));
        harga_obat.setForeground(new java.awt.Color(0, 0, 0));
        jPanel3.add(harga_obat, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 90, 179, -1));

        ttl_harga_obat.setBackground(new java.awt.Color(255, 255, 255));
        ttl_harga_obat.setForeground(new java.awt.Color(0, 0, 0));
        jPanel3.add(ttl_harga_obat, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 130, 179, -1));

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("JML Realisasi OBAT");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 15, -1, -1));

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("HARGA OBAT");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, -1, -1));

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("TOTAL HARGA OBAT");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, -1));

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("NAMA OBAT");
        jPanel3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, -1, -1));

        nama_obat.setBackground(new java.awt.Color(255, 255, 255));
        nama_obat.setForeground(new java.awt.Color(0, 0, 0));
        jPanel3.add(nama_obat, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 50, 179, -1));

        jPanel4.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 100, 420, 160));

        jPanel2.setBackground(new java.awt.Color(51, 153, 255));

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("JML KANTONG DARAH");

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("HARGA PER KANTONG ");

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("TOTAL HARGA DARAH ");

        jml_kantong.setBackground(new java.awt.Color(255, 255, 255));
        jml_kantong.setForeground(new java.awt.Color(0, 0, 0));

        hrg_kantong.setBackground(new java.awt.Color(255, 255, 255));
        hrg_kantong.setForeground(new java.awt.Color(0, 0, 0));
        hrg_kantong.setText("975000");

        ttl_harga_darah.setBackground(new java.awt.Color(255, 255, 255));
        ttl_harga_darah.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(27, 27, 27)
                        .addComponent(hrg_kantong, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(27, 27, 27)
                        .addComponent(ttl_harga_darah, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(31, 31, 31)
                        .addComponent(jml_kantong, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(97, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jml_kantong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel7)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(hrg_kantong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel8)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(ttl_harga_darah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );

        jPanel4.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 270, 420, 130));

        jPanel1.setBackground(new java.awt.Color(0, 153, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        no_rek_medis.setBackground(new java.awt.Color(255, 255, 255));
        no_rek_medis.setForeground(new java.awt.Color(0, 0, 0));
        no_rek_medis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                no_rek_medisActionPerformed(evt);
            }
        });
        jPanel1.add(no_rek_medis, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, 228, -1));

        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("No. Rek Medis");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("NAMA");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Jenis Kelamin");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, -1));

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Jenis Jaminan");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, -1, -1));

        txt_nama.setBackground(new java.awt.Color(255, 255, 255));
        txt_nama.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(txt_nama, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 100, 228, -1));

        jns_kelamin.setBackground(new java.awt.Color(255, 255, 255));
        jns_kelamin.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(jns_kelamin, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 150, 230, -1));

        jns_jaminan.setBackground(new java.awt.Color(255, 255, 255));
        jns_jaminan.setForeground(new java.awt.Color(0, 0, 0));
        jns_jaminan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jns_jaminanActionPerformed(evt);
            }
        });
        jPanel1.add(jns_jaminan, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 230, 230, -1));

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("NIK");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

        txt_nik.setBackground(new java.awt.Color(255, 255, 255));
        txt_nik.setForeground(new java.awt.Color(0, 0, 0));
        txt_nik.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nikActionPerformed(evt);
            }
        });
        jPanel1.add(txt_nik, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, 228, -1));

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("GOLDAR");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, -1, -1));

        gol_dar.setBackground(new java.awt.Color(255, 255, 255));
        gol_dar.setForeground(new java.awt.Color(0, 0, 0));
        gol_dar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gol_darActionPerformed(evt);
            }
        });
        jPanel1.add(gol_dar, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 190, 230, -1));

        jPanel4.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 100, 420, 290));

        cari_tagihan.setBackground(new java.awt.Color(255, 255, 255));
        cari_tagihan.setForeground(new java.awt.Color(0, 0, 0));
        jPanel4.add(cari_tagihan, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 490, 240, -1));

        cari_pasienlagi.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        cari_pasienlagi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/search_25px.png"))); // NOI18N
        cari_pasienlagi.setText("CARI");
        cari_pasienlagi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cari_pasienlagiActionPerformed(evt);
            }
        });
        jPanel4.add(cari_pasienlagi, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 490, -1, 30));

        jPanel5.setBackground(new java.awt.Color(102, 255, 102));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setText("HITUNG");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 10, -1, -1));

        jLabel14.setFont(new java.awt.Font("Cooper Black", 2, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 0, 51));
        jLabel14.setText("TANGGAL TAGIHAN");
        jPanel5.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 212, -1));

        ttl_tagihan.setBackground(new java.awt.Color(255, 255, 255));
        ttl_tagihan.setForeground(new java.awt.Color(0, 0, 0));
        ttl_tagihan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ttl_tagihanActionPerformed(evt);
            }
        });
        jPanel5.add(ttl_tagihan, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, 200, -1));

        tgl_tagihan.setBackground(new java.awt.Color(255, 255, 255));
        tgl_tagihan.setForeground(new java.awt.Color(0, 0, 0));
        jPanel5.add(tgl_tagihan, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 40, 200, -1));

        jLabel17.setFont(new java.awt.Font("Cooper Black", 2, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 0, 51));
        jLabel17.setText("TOTAL TAGIHAN");
        jPanel5.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 212, -1));

        jPanel4.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 400, 560, 80));

        tabel_tagihan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "No. RM", "NIK", "NAMA", "Gender", "Goldar", "Jaminan", "Jml Obat", "Nama Obat", "Ttl Harga Obat", "Jml Kantong Darah", "Ttl Harga Darah", "Tagihan", "Tgl Tagihan"
            }
        ));
        tabel_tagihan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_tagihanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel_tagihan);

        jPanel4.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 530, 1290, 120));

        cari_pasienlagi1.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        cari_pasienlagi1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/search_25px.png"))); // NOI18N
        cari_pasienlagi1.setText("CARI");
        cari_pasienlagi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cari_pasienlagi1ActionPerformed(evt);
            }
        });
        jPanel4.add(cari_pasienlagi1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 60, -1, 30));

        no_rm.setBackground(new java.awt.Color(255, 255, 255));
        no_rm.setForeground(new java.awt.Color(0, 0, 0));
        jPanel4.add(no_rm, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 240, -1));

        save.setBackground(new java.awt.Color(0, 255, 0));
        save.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Save_25px.png"))); // NOI18N
        save.setText("SAVE");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });
        jPanel4.add(save, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 420, 105, -1));

        clean.setBackground(new java.awt.Color(255, 255, 51));
        clean.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        clean.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/broom_25px.png"))); // NOI18N
        clean.setText("CLEAN");
        clean.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cleanActionPerformed(evt);
            }
        });
        jPanel4.add(clean, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 420, 105, -1));

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
        jPanel4.add(btn_edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 420, -1, 40));

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
        jPanel4.add(btn_delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 420, -1, -1));

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/3652191.png"))); // NOI18N
        jPanel4.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 0, -1, 55));

        lbltanggal.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lbltanggal.setForeground(new java.awt.Color(0, 0, 0));
        lbltanggal.setText("tanggal");
        jPanel4.add(lbltanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 10, -1, -1));

        lbljam.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lbljam.setForeground(new java.awt.Color(0, 0, 0));
        lbljam.setText("jam");
        jPanel4.add(lbljam, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 30, -1, -1));

        jButton2.setText("Refresh Tabel");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 490, -1, -1));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/print_25px.png"))); // NOI18N
        jButton3.setText("Print");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 480, -1, -1));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1320, 660));

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

    private void no_rek_medisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_no_rek_medisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_no_rek_medisActionPerformed

    private void realisasi_obatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_realisasi_obatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_realisasi_obatActionPerformed

    private void ttl_tagihanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ttl_tagihanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ttl_tagihanActionPerformed

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

    private void cari_pasienlagiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cari_pasienlagiActionPerformed
        // TODO add your handling code here:

        String cari = cari_tagihan.getText();
        tabel_model = new DefaultTableModel();
        tabel_tagihan.setModel(tabel_model);
        tabel_model.addColumn("No. RM");
        tabel_model.addColumn("NIK");
        tabel_model.addColumn("NAMA");
        tabel_model.addColumn("Gender");
        tabel_model.addColumn("Goldar");
        tabel_model.addColumn("Jaminan");
        tabel_model.addColumn("Jml Obat");
        tabel_model.addColumn("Nama Obat");
        tabel_model.addColumn("Ttl Harga Darah");
        tabel_model.addColumn("Jml Kantong Darah");
        tabel_model.addColumn("Ttl Harga Darah");
        tabel_model.addColumn("Tagihan");
        tabel_model.addColumn("Tgl Tagihan");

        try {

            koneksi connect = new koneksi(); // memanggil class koneksi
            connect.konek();
            Connection con = connect.con;
            Statement stat = connect.stm;
            String sql = "SELECT * FROM tagihan_pasien WHERE nik like '%"
                    + cari + "%'"
                    + "or no_rek_medis like '%" + cari + "%'";
            ResultSet res = stat.executeQuery(sql);

//pengecekan terhadap data pasien di database
// get.String disesuaikan dengan field data pasien yg di databse yaitu di tabel pasien
            while (res.next()) {
                Object[] obj = new Object[14];
                obj[0] = res.getString("no_rek_medis");
                obj[1] = res.getString("nik");
                obj[2] = res.getString("nama");
                obj[3] = res.getString("jen_kel");
                obj[4] = res.getString("gol_darah");
                obj[5] = res.getString("jenis_jaminan");
                obj[6] = res.getString("jml_realisasi_obat");
                obj[7] = res.getString("nama_obat");
                obj[8] = res.getString("total_harga_obat");
                obj[9] = res.getString("jml_kantong_darah");
                obj[10] = res.getString("total_harga_darah");
                obj[11] = res.getString("total_tagihan");
                obj[12] = res.getString("tanggal_tagihan");

                tabel_model.addRow(obj);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }//GEN-LAST:event_cari_pasienlagiActionPerformed

    private void txt_nikActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nikActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nikActionPerformed

    private void gol_darActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gol_darActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_gol_darActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        int harga_obat = Integer.parseInt(ttl_harga_obat.getText());
        int harga_darah = Integer.parseInt(ttl_harga_darah.getText());

        double total_tagihan = harga_obat + harga_darah;
////total_harga.setText(Double.toString(ttl_harga));
//        String tagihan = Double.toString(ttl_tagihan);
//        Locale localeID = new Locale("In", "ID");
//        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(new Locale("In ", "ID")); // merubah jadi Rp 00,000,000.00
//        ttl_tagihan.setText(formatRupiah.format(total_tagihan));
        
                NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(new Locale("In", "ID")); // merubah jadi Rp 00,000,000.00
        ttl_tagihan.setText(formatRupiah.format(total_tagihan));
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cari_pasienlagi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cari_pasienlagi1ActionPerformed
        // TODO add your handling code here:
        String norm = no_rm.getText();
        try {

            koneksi connect = new koneksi(); // memanggil class koneksi
            connect.konek();
            Connection conn = connect.con;
            Statement stat = connect.stm;

            String sql = "SELECT * FROM treatment_rekam_medis WHERE no_rek_medis='" + norm + "'"
                    + " OR nik='" + norm + "'";
            ResultSet res = stat.executeQuery(sql);

            while (res.next()) {
                no_rek_medis.setText(res.getString("no_rek_medis"));
                txt_nik.setText(res.getString("nik"));
                String nik = res.getString("nik");
                txt_nama.setText(res.getString("nama"));
                jns_kelamin.setText(res.getString("jen_kel"));
                gol_dar.setText(res.getString("gol_darah"));

                Statement statt = conn.createStatement();
                String obat = "SELECT * FROM pasien WHERE nik='" + res.getString("nik") + "'";
                ResultSet res3 = statt.executeQuery(obat);
                if (res3.next()) {
                    jns_jaminan.setText(res3.getString("no_treatment"));
                }
            }

            Statement stat1 = conn.createStatement();
            String sql1 = "SELECT * FROM obat_kelator_besi WHERE no_rek_medis='" + norm + "'"
                    + " OR nik='" + norm + "'";
            ResultSet res1 = stat1.executeQuery(sql1);
            while (res1.next()) {
                realisasi_obat.setText(res1.getString("realisasi_obat"));
                nama_obat.setText(res1.getString("nama_obat"));
                ttl_harga_obat.setText(res1.getString("harga"));
                jns_jaminan.setText(res1.getString("jenis_jaminan"));
                Statement stat3 = conn.createStatement();
                String obat = "SELECT * FROM daftar_obat WHERE nama_obat='" + res1.getString("nama_obat") + "'";
                ResultSet res2 = stat3.executeQuery(obat);
                if (res2.next()) {
                    harga_obat.setText(res2.getString("harga_obat"));
                }
            }

            Statement stat4 = conn.createStatement();
            String sql3 = "SELECT * FROM kebutuhan_darah_pasien WHERE no_rek_medis='" + norm + "'"
                    + " OR nik='" + norm + "'";
            ResultSet res3 = stat4.executeQuery(sql3);
            while (res3.next()) {
                jml_kantong.setText(res3.getString("jml_kantong"));
                ttl_harga_darah.setText(res3.getString("total_harga"));
            }
            stat.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data yang dicari tidak terdaftar : \n" + e.getMessage(), "Pesan", JOptionPane.ERROR_MESSAGE);
            System.err.print("err: " + e.getMessage());
        }
    }//GEN-LAST:event_cari_pasienlagi1ActionPerformed

    private void jns_jaminanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jns_jaminanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jns_jaminanActionPerformed

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        // TODO add your handling code here:
        koneksi connect = new koneksi(); // memanggil class koneksi
        connect.konek();
        Connection conn = connect.con;

        String rek_medis = no_rek_medis.getText();
        String nik = txt_nik.getText();
        String nama = txt_nama.getText();
        String jenkel = jns_kelamin.getText();
        String goldar = gol_dar.getText();
        String jaminan = jns_jaminan.getText();
        String realisasi = realisasi_obat.getText();
        String namaobat = nama_obat.getText();
        String ttal_hrg_obat = ttl_harga_obat.getText();
        String kantong = jml_kantong.getText();
        String harga_darah = ttl_harga_darah.getText();
        String tagihan = ttl_tagihan.getText();

        String tgal_tagihan = null;
        if (tgl_tagihan.getDate() != null) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            tgal_tagihan = format.format(tgl_tagihan.getDate());
        }

        try {
            koneksi DB = new koneksi();
            DB.konek();
            Connection con = DB.con;
            Statement stat = con.createStatement();

            String sql = "INSERT INTO tagihan_pasien (no_rek_medis,nik,nama,jen_kel,gol_darah,jenis_jaminan,jml_realisasi_obat,nama_obat,total_harga_obat,jml_kantong_darah,total_harga_darah,total_tagihan,tanggal_tagihan)"
                    + " VALUES('" + rek_medis + "','" + nik + "','" + nama + "','" + jenkel + "','" + goldar + ""
                    + "','" + jaminan + "','" + realisasi + "','" + namaobat + "','" + ttal_hrg_obat + "','" + kantong + ""
                    + "','" + harga_darah + "','" + tagihan + "','" + tgal_tagihan + "')";

            stat.executeUpdate(sql);
            String data[] = new String[14];
            data[0] = rek_medis;
            data[1] = nik;
            data[2] = nama;
            data[3] = jenkel;
            data[4] = goldar;
            data[5] = jaminan;
            data[6] = realisasi;
            data[7] = namaobat;
            data[8] = ttal_hrg_obat;
            data[9] = kantong;
            data[10] = harga_darah;
            data[11] = tagihan;
            data[12] = tgal_tagihan;
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

    private void cleanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cleanActionPerformed
        // TODO add your handling code here:
        bersihkaninput();
        btn_edit.setEnabled(false);
        btn_delete.setEnabled(false);
        save.setEnabled(true);
    }//GEN-LAST:event_cleanActionPerformed

    private void btn_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editActionPerformed
        // TODO add your handling code here:
        String tgal_tagihan = null;
        if (tgl_tagihan.getDate() != null) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            tgal_tagihan = format.format(tgl_tagihan.getDate());
        }

        int row = tabel_tagihan.getSelectedRow();
        if (row >= 0) {
            int ok = JOptionPane.showConfirmDialog(null, "Yakin Mengedit Data Ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (ok == 0) {
                try {
                    koneksi DB = new koneksi();
                    DB.konek();
                    Connection con = DB.con;
                    String sql = "UPDATE tagihan_pasien SET tanggal_tagihan ='" + tgal_tagihan
                            + " WHERE no_rek_medis='" + tabel_tagihan.getValueAt(row, 1).toString() + "';";
                    PreparedStatement st = con.prepareStatement(sql);
                    st.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Edit Data Sukses");
                    bersihkaninput();
                    btn_edit.setEnabled(false);
                    tabel_model_tagihan();
                    tampil_database();

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Edit Data Gagal", "Pesan", JOptionPane.ERROR_MESSAGE);
                    System.err.print("err: " + e.getMessage());
                }
            }
        }
    }//GEN-LAST:event_btn_editActionPerformed

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
        // TODO add your handling code here:

        int row = tabel_tagihan.getSelectedRow();
        if (row >= 0) {
            int ok = JOptionPane.showConfirmDialog(null, "Yakin Mau Hapus?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (ok == 0) {
                try {
                    koneksi DB = new koneksi();
                    DB.konek();
                    Connection con = DB.con;
                    String sql = "DELETE FROM tagihan_pasien WHERE no_rek_medis='" + tabel_tagihan.getValueAt(row, 0).toString() + "';";
                    PreparedStatement st = con.prepareStatement(sql);
                    st.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Delete Data Sukses");
                    btn_edit.setEnabled(false);
                    btn_delete.setEnabled(false);
                    save.setEnabled(true);
                    bersihkaninput();
                    tabel_model.removeRow(row);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Delete Data gagal\n" + e.getMessage());
                }
            }
        }
    }//GEN-LAST:event_btn_deleteActionPerformed

    private void tabel_tagihanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_tagihanMouseClicked
        // TODO add your handling code here:
        btn_delete.setEnabled(true);
        btn_edit.setEnabled(true);
        save.setEnabled(false);

// harga_obat.setText(tabel_tagihan.getValueAt(i, 5).toString());
        int j = tabel_tagihan.getSelectedRow();
        String nma_obat = tabel_tagihan.getValueAt(j, 5).toString();
        try {

            koneksi connect = new koneksi(); // memanggil class koneksi
            connect.konek();
            Connection conn = connect.con;
            Statement stat = connect.stm;

            String sql = "SELECT harga_obat FROM daftar_obat WHERE nama_obat='" + nma_obat + "'";
            ResultSet res = stat.executeQuery(sql);
            while (res.next()) {
                harga_obat.setText(res.getString("harga_obat"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data yang dicari tidak terdaftar : \n" + e.getMessage(), "Pesan", JOptionPane.ERROR_MESSAGE);
            System.err.print("err: " + e.getMessage());
        }

        int i = tabel_tagihan.getSelectedRow();
        if (i > -1) {
            no_rek_medis.setText(tabel_tagihan.getValueAt(i, 0).toString());
            txt_nik.setText(tabel_tagihan.getValueAt(i, 1).toString());
            txt_nama.setText(tabel_tagihan.getValueAt(i, 2).toString());
            jns_kelamin.setText(tabel_tagihan.getValueAt(i, 3).toString());
            gol_dar.setText(tabel_tagihan.getValueAt(i, 4).toString());
            jns_jaminan.setText(tabel_tagihan.getValueAt(i, 5).toString());

            realisasi_obat.setText(tabel_tagihan.getValueAt(i, 6).toString());
            nama_obat.setText(tabel_tagihan.getValueAt(i, 7).toString());
            ttl_harga_obat.setText(tabel_tagihan.getValueAt(i, 8).toString());
            jml_kantong.setText(tabel_tagihan.getValueAt(i, 9).toString());
            ttl_harga_darah.setText(tabel_tagihan.getValueAt(i, 10).toString());
            ttl_tagihan.setText(tabel_tagihan.getValueAt(i, 11).toString());
        }

        try {
            DefaultTableModel model = (DefaultTableModel) tabel_tagihan.getModel();
            int index = tabel_tagihan.getSelectedRow();
            java.util.Date tgl = new SimpleDateFormat("yyyy-MM-dd").parse((String) model.getValueAt(index, 12));
            tgl_tagihan.setDate(tgl);
        } catch (ParseException ex) {
            Logger.getLogger(C_master_data_pasien.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {

            koneksi connect = new koneksi(); // memanggil class koneksi
            connect.konek();
            Connection conn = connect.con;
            Statement stat = connect.stm;
            
            int k = tabel_tagihan.getSelectedRow();
        String name_obat = tabel_tagihan.getValueAt(k, 7).toString();

            String sql = "SELECT harga_obat FROM daftar_obat WHERE nama_obat='" + name_obat + "'";
            ResultSet res = stat.executeQuery(sql);
            while (res.next()) {
                harga_obat.setText(res.getString("harga_obat"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data yang dicari tidak terdaftar : \n" + e.getMessage(), "Pesan", JOptionPane.ERROR_MESSAGE);
            System.err.print("err: " + e.getMessage());
        }
    }//GEN-LAST:event_tabel_tagihanMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        tabel_model_tagihan();
        tampil_database();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        new D_master_treatment_pasien().setVisible(true);
        dispose();

    }//GEN-LAST:event_jMenuItem1ActionPerformed

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

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
try {
            String namafile = "src/laporan/laporantagihan_pasien.jasper";
            koneksi DB = new koneksi();
            DB.konek();
            Connection con = DB.con;
            
            HashMap parameter = new HashMap();
            
            parameter.put("nom_medis", no_rek_medis.getText());
             File report_file = new File(namafile);
             JasperReport jasperReport=(JasperReport) JRLoader.loadObject(report_file.getPath());
            JasperPrint jp = JasperFillManager.fillReport(jasperReport, parameter, con);
            JasperViewer.viewReport(jp, false);
      
            
            

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data Tidak dapat dicetak\n" + e.getMessage(), "Pesan", JOptionPane.ERROR_MESSAGE);
            System.err.print("err: " + e.getMessage());
        }
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(G_Tagihan_Pasien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(G_Tagihan_Pasien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(G_Tagihan_Pasien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(G_Tagihan_Pasien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new G_Tagihan_Pasien().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btn_delete;
    private javax.swing.JToggleButton btn_edit;
    private javax.swing.JButton cari_pasienlagi;
    private javax.swing.JButton cari_pasienlagi1;
    private javax.swing.JTextField cari_tagihan;
    private javax.swing.JButton clean;
    private javax.swing.JTextField gol_dar;
    private javax.swing.JTextField harga_obat;
    private javax.swing.JTextField hrg_kantong;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jml_kantong;
    private javax.swing.JTextField jns_jaminan;
    private javax.swing.JTextField jns_kelamin;
    private javax.swing.JLabel lbljam;
    private javax.swing.JLabel lbltanggal;
    private javax.swing.JTextField nama_obat;
    private javax.swing.JTextField no_rek_medis;
    private javax.swing.JTextField no_rm;
    private javax.swing.JTextField realisasi_obat;
    private javax.swing.JButton save;
    private javax.swing.JTable tabel_tagihan;
    private com.toedter.calendar.JDateChooser tgl_tagihan;
    private javax.swing.JTextField ttl_harga_darah;
    private javax.swing.JTextField ttl_harga_obat;
    private javax.swing.JTextField ttl_tagihan;
    private javax.swing.JTextField txt_nama;
    private javax.swing.JTextField txt_nik;
    // End of variables declaration//GEN-END:variables
}
