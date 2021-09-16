/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package page;

//import com.placeholder.PlaceHolder;
import connection.koneksi;
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
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Adam Arya Soleh
 */
public class D_master_treatment_pasien extends javax.swing.JFrame {

    DefaultTableModel tabel_model;

    /**
     * Creates new form NewJFrame
     */
    public D_master_treatment_pasien() {
        initComponents();
        setIconImage(java.awt.Toolkit.getDefaultToolkit().getImage("src/icon/Logo_yayasan.png"));
        setTitle("(Data Treatment Pasien) - Aplikasi treatment darah pasien Anemia");

//        PlaceHolder holder1 = new PlaceHolder(cari_no_rm, "No. Treatment / NIK");
        waktu();

        btn_edit.setEnabled(false);
        btn_delete.setEnabled(false);

        rekam_medis.setEnabled(false);
        nama_pasien.setEnabled(false);
        nik_pasien.setEnabled(false);
        date_tgl_lahir.setEnabled(false);
        goldar.setEnabled(false);
        jns_kelamin.setEnabled(false);
        no_rekam_medis();
        tabel_model_treatment();
        tampil_database();
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

    public void bersihkaninput() {
        cari_no_rm.setText("");
        rekam_medis.setText("");
        nama_pasien.setText("");
        nik_pasien.setText("");
        date_tgl_lahir.setDate(null);
        goldar.setSelectedItem("Pilih");
        jns_kelamin.setText("");
        berat_badanpasien.setText("");
        txt_ttinggi.setText("");
        txt_kadarhb.setText("");
        jumlah_darah.setText("");
        tgl_transfusi_drh.setDate(null);
        jns_jaminan.setSelectedItem("Pilih");
    }

    public void tabel_model_treatment() {
        tabel_model = new DefaultTableModel();
        tabel_treatment.setModel(tabel_model);
        tabel_model.addColumn("No. Rec Medis");
        tabel_model.addColumn("Nama");
        tabel_model.addColumn("NIK");
        tabel_model.addColumn("Tgl Lahir");
        tabel_model.addColumn("Gol. Darah");
        tabel_model.addColumn("Gender");
        tabel_model.addColumn("Berat");
        tabel_model.addColumn("Tinggi");
        tabel_model.addColumn("Kadar HB");
        tabel_model.addColumn("Jml Darah DiButuhkan");
        tabel_model.addColumn("Tgl Transfusi");
        tabel_model.addColumn("Jns Jaminan");
    }

    public void tampil_database() { // getData() dibuat untuk memanggil data pasien dari database dan menampilkannya di tabel_pasien

        try {

// memanggil data pasien dari database yaitu di tabel pasien yg ada di databas
            koneksi connect = new koneksi(); // memanggil class koneksi
            connect.konek();
            Connection con = connect.con;
            Statement stat = connect.stm;
            String sql = "SELECT * FROM treatment_rekam_medis ORDER BY nik DESC";
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
                obj[6] = res.getString("berat_badan");
                obj[7] = res.getString("tinggi_badan");
                obj[8] = res.getString("kadar_hb");
                obj[9] = res.getString("jml_darah");
                obj[10] = res.getString("tgl_transfusi");
                obj[11] = res.getString("jenis_jaminan");

                tabel_model.addRow(obj);

//
            }
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, err.getMessage());
        }
    }

    private void no_rekam_medis() {
        try {
            koneksi connect = new koneksi(); // memanggil class koneksi
            connect.konek();

            String sql = "SELECT * FROM treatment_rekam_medis ORDER BY no_rek_medis DESC";
//            String sql = "SELECT * FROM pasien WHERE nik='123'";
            Statement stat = connect.stm;
            ResultSet res = stat.executeQuery(sql);

            if (res.next()) {
                String rek_medis = res.getString("no_rek_medis").substring(4);
                String AN = "" + (Integer.parseInt(rek_medis) + 1);
                String Nol = "";

                if (AN.length() == 1) {
                    Nol = "000000";
                } else if (AN.length() == 2) {
                    Nol = "00000";
                } else if (AN.length() == 3) {
                    Nol = "0000";
                } else if (AN.length() == 4) {
                    Nol = "000";
                } else if (AN.length() == 5) {
                    Nol = "00";
                } else if (AN.length() == 6) {
                    Nol = "0";
                } else if (AN.length() == 7) {
                    Nol = "";
                }

                rekam_medis.setText("TRMA" + Nol + AN);

            } else {
                rekam_medis.setText("TRMA000001");

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

        jLabel15 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        cari_no_rm = new javax.swing.JTextField();
        cari_pasien = new javax.swing.JButton();
        cari_dt_tabel = new javax.swing.JTextField();
        cari_pasienlagi = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        lbltanggal = new javax.swing.JLabel();
        lbljam = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_treatment = new javax.swing.JTable()
        {
            public boolean isCellEditable(int rowIndex, int colIndex)
            {
                return false; //Disallow the editing of any cell
            }
        };
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jumlah_darah = new javax.swing.JTextField();
        tgl_transfusi_drh = new com.toedter.calendar.JDateChooser();
        hitung_darah = new javax.swing.JButton();
        save = new javax.swing.JButton();
        clean = new javax.swing.JButton();
        jns_jaminan = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        berat_badanpasien = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        goldar = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        date_tgl_lahir = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        nama_pasien = new javax.swing.JTextField();
        rekam_medis = new javax.swing.JTextField();
        nik_pasien = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txt_ttinggi = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txt_kadarhb = new javax.swing.JTextField();
        jns_kelamin = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        btn_edit = new javax.swing.JToggleButton();
        btn_delete = new javax.swing.JToggleButton();
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

        jLabel15.setText("jLabel15");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cari_no_rm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cari_no_rmActionPerformed(evt);
            }
        });
        jPanel1.add(cari_no_rm, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 220, 30));

        cari_pasien.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        cari_pasien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/search_25px.png"))); // NOI18N
        cari_pasien.setText("CARI");
        cari_pasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cari_pasienActionPerformed(evt);
            }
        });
        jPanel1.add(cari_pasien, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 70, -1, 30));
        jPanel1.add(cari_dt_tabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 450, 225, -1));

        cari_pasienlagi.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        cari_pasienlagi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/search_25px.png"))); // NOI18N
        cari_pasienlagi.setText("CARI");
        cari_pasienlagi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cari_pasienlagiActionPerformed(evt);
            }
        });
        jPanel1.add(cari_pasienlagi, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 440, -1, -1));
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(1042, 40, -1, -1));

        lbltanggal.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lbltanggal.setText("tanggal");
        jPanel1.add(lbltanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(1260, 10, -1, -1));

        lbljam.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lbljam.setText("jam");
        jPanel1.add(lbljam, new org.netbeans.lib.awtextra.AbsoluteConstraints(1260, 30, -1, -1));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/3652191.png"))); // NOI18N
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220, 0, -1, 55));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 102, 102));
        jLabel1.setText("Treatment Kebutuhan Darah Pasien");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 0, -1, -1));

        tabel_treatment.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        tabel_treatment.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "No.Rec Medis", "Nama", "NIK", "Tgl Lahir", "Gol. Darah", "Gender", "Berat", "Tinggi", "Kadar Hb", "Jml Darah DiButuhkan", "Tgl Transfusi", "Jns Jaminan"
            }
        ));
        tabel_treatment.setRowHeight(18);
        tabel_treatment.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_treatmentMouseClicked(evt);
            }
        });
        tabel_treatment.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabel_treatmentKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tabel_treatment);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 490, 1340, 150));

        jPanel2.setBackground(new java.awt.Color(153, 255, 153));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel10.setText("Tanggal Transfusi");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 190, 180, -1));

        jLabel9.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel9.setText("Jumlah Darah Yang Di Butuhkan");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 150, -1, -1));
        jPanel2.add(jumlah_darah, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 140, 129, -1));

        tgl_transfusi_drh.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.add(tgl_transfusi_drh, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 180, 190, -1));

        hitung_darah.setText("HITUNG");
        hitung_darah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hitung_darahActionPerformed(evt);
            }
        });
        jPanel2.add(hitung_darah, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 140, -1, -1));

        save.setBackground(new java.awt.Color(0, 255, 0));
        save.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Save_25px.png"))); // NOI18N
        save.setText("SAVE");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });
        jPanel2.add(save, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 270, 105, -1));

        clean.setBackground(new java.awt.Color(255, 255, 51));
        clean.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        clean.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/broom_25px.png"))); // NOI18N
        clean.setText("CLEAN");
        clean.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cleanActionPerformed(evt);
            }
        });
        jPanel2.add(clean, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 270, 105, -1));

        jns_jaminan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih", "BPJS", "TUNAI", "SWASTA" }));
        jPanel2.add(jns_jaminan, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 230, 110, -1));

        jLabel8.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel8.setText("Jenis Jaminan");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 240, 99, -1));

        jLabel7.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel7.setText("Berat Badan");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 30, 99, -1));
        jPanel2.add(berat_badanpasien, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 20, 130, -1));

        jLabel6.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel6.setText("Golongan Darah");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 210, 99, -1));

        goldar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih", "A", "B", "AB", "O" }));
        goldar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goldarActionPerformed(evt);
            }
        });
        jPanel2.add(goldar, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 210, 80, -1));

        jLabel5.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel5.setText("Tanggal Lahir");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 160, 99, -1));

        date_tgl_lahir.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.add(date_tgl_lahir, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 160, 180, -1));

        jLabel4.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel4.setText("NIK");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 120, 99, -1));

        jLabel3.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel3.setText("Nama");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 80, 99, -1));
        jPanel2.add(nama_pasien, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 80, 260, -1));
        jPanel2.add(rekam_medis, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 30, 170, -1));
        jPanel2.add(nik_pasien, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 120, 260, -1));

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel2.setText("No. Rekam Medis");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 30, -1, -1));

        txt_ttinggi.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        txt_ttinggi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_ttinggiActionPerformed(evt);
            }
        });
        jPanel2.add(txt_ttinggi, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 60, 130, -1));

        jLabel16.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel16.setText("Tinggi Badan");
        jPanel2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 70, 90, -1));

        jLabel17.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel17.setText("Kg");
        jPanel2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 20, -1, -1));

        jLabel18.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel18.setText("Cm");
        jPanel2.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 60, -1, -1));

        jLabel19.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel19.setText("Kadar Hb");
        jPanel2.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 110, -1, 20));

        txt_kadarhb.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        txt_kadarhb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_kadarhbActionPerformed(evt);
            }
        });
        jPanel2.add(txt_kadarhb, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 100, 160, -1));
        jPanel2.add(jns_kelamin, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 250, 130, -1));

        jLabel20.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel20.setText("Jenis Kelamin");
        jPanel2.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 250, 99, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, 1160, 320));

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
        jPanel1.add(btn_edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 440, -1, 40));

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
        jPanel1.add(btn_delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 440, -1, -1));

        jButton2.setText("Refresh Tabel");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 450, -1, -1));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/print_25px.png"))); // NOI18N
        jButton1.setText("Print");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 440, -1, -1));

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
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1357, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 651, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void goldarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goldarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_goldarActionPerformed

    private void cari_no_rmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cari_no_rmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cari_no_rmActionPerformed

    private void txt_ttinggiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_ttinggiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_ttinggiActionPerformed

    private void txt_kadarhbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_kadarhbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_kadarhbActionPerformed

    private void cari_pasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cari_pasienActionPerformed
        // TODO add your handling code here:
        String cari_norm = cari_no_rm.getText();

        try {

            koneksi connect = new koneksi(); // memanggil class koneksi
            connect.konek();
//            Connection conn = connect.con;
            Statement stat = connect.stm;

            String sql = "SELECT * FROM pasien WHERE nik LIKE '%" + cari_norm + "%'"
                    + " OR no_treatment LIKE '%" + cari_norm + "%'";
            ResultSet res = stat.executeQuery(sql);

            while (res.next()) {
                nama_pasien.setText(res.getString("nama"));
                nik_pasien.setText(res.getString("nik"));
                date_tgl_lahir.setDate(res.getDate("tgl_lahir"));
                goldar.setSelectedItem(res.getString("gol_darah"));
                jns_kelamin.setText(res.getString("jen_kel"));

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data yang dicari tidak terdaftar : \n" + e.getMessage(), "Pesan", JOptionPane.ERROR_MESSAGE);
            System.err.print("err: " + e.getMessage());
        }

    }//GEN-LAST:event_cari_pasienActionPerformed

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        // TODO add your handling code here:
        koneksi connect = new koneksi(); // memanggil class koneksi
        connect.konek();
        Connection conn = connect.con;

        String rek_medis = rekam_medis.getText();
        String nama = nama_pasien.getText();
        String nik = nik_pasien.getText();

        String tgl_lahir = null;
        if (date_tgl_lahir.getDate() != null) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            tgl_lahir = format.format(date_tgl_lahir.getDate());
        }

        String gol_darah = (String) goldar.getSelectedItem(); // untuk mengambil nilai dari jComboBox  berdasarkan list item dengan perintah getSelectedItem() ke data String.
        if (gol_darah == "A") { // kondisi data yang diambil dari jComboBox berdasarkan list item, gunakan operator pemberi nilai sama dengan (==), dan untuk nilai kondisi karakternya harus sama dengan karakter list item jComboBox.
            gol_darah = "A";
        } else if (gol_darah == "B") {
            gol_darah = "B";
        } else if (gol_darah == "AB") {
            gol_darah = "AB";
        } else if (gol_darah == "O") {
            gol_darah = "O";
        } else if (gol_darah == "Pilih") {
            gol_darah = "";
        }

        String jenkel = jns_kelamin.getText();
        String b_badan = berat_badanpasien.getText();
        String t_badan = txt_ttinggi.getText();
        String kadar_hb = txt_kadarhb.getText();
        String jml_darah = jumlah_darah.getText();

        String tgl_transfusi = null;
        if (tgl_transfusi_drh.getDate() != null) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            tgl_transfusi = format.format(tgl_transfusi_drh.getDate());
        }

        String jaminan = (String) jns_jaminan.getSelectedItem(); // untuk mengambil nilai dari jComboBox  berdasarkan list item dengan perintah getSelectedItem() ke data String.
        if (jaminan == "BPJS") { // kondisi data yang diambil dari jComboBox berdasarkan list item, gunakan operator pemberi nilai sama dengan (==), dan untuk nilai kondisi karakternya harus sama dengan karakter list item jComboBox.
            jaminan = "BPJS";
        } else if (jaminan == "TUNAI") {
            jaminan = "TUNAI";
        } else if (jaminan == "SWASTA") {
            jaminan = "SWASTA";
        } else if (jaminan == "Pilih") {
            jaminan = "";
        }

        try {
            koneksi DB = new koneksi();
            DB.konek();
            Connection con = DB.con;
            Statement stat = con.createStatement();

            String sql = "INSERT INTO treatment_rekam_medis (no_rek_medis,nama,nik,tgl_lahir,gol_darah,jen_kel,berat_badan,tinggi_badan,kadar_hb,jml_darah,tgl_transfusi,jenis_jaminan)"
                    + " VALUES('" + rek_medis + "','" + nama + "','" + nik + "','" + tgl_lahir + "','" + gol_darah + ""
                    + "','" + jenkel + "','" + b_badan + "','" + t_badan + "','" + kadar_hb + "','" + jml_darah + ""
                    + "','" + tgl_transfusi + "','" + jaminan + "')";

            stat.executeUpdate(sql);
            String data[] = new String[14];
            data[0] = rek_medis;
            data[1] = nama;
            data[2] = nik;
            data[3] = tgl_lahir;
            data[4] = gol_darah;
            data[5] = jenkel;
            data[6] = b_badan;
            data[7] = t_badan;
            data[8] = kadar_hb;
            data[9] = jml_darah;
            data[10] = tgl_transfusi;
            data[13] = jaminan;
            JOptionPane.showMessageDialog(null, "Data berhasil tersimpan !!", "Succes", JOptionPane.INFORMATION_MESSAGE);
            bersihkaninput();
            no_rekam_medis();
            tabel_model.insertRow(0, data);
            stat.close();
            con.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Kesalahan Input", "Peringatan", JOptionPane.WARNING_MESSAGE);
            System.err.println(e.getMessage());
        }
    }//GEN-LAST:event_saveActionPerformed

    private void hitung_darahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hitung_darahActionPerformed
        // TODO add your handling code here:
        double kadar_hb = Double.parseDouble(txt_kadarhb.getText());
        double b_badan = Double.parseDouble(berat_badanpasien.getText());

        double jml_kbutuhan_darah = (12 - kadar_hb) * 4 * b_badan;

        jumlah_darah.setText(Double.toString(jml_kbutuhan_darah));
    }//GEN-LAST:event_hitung_darahActionPerformed

    private void btn_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editActionPerformed
        // TODO add your handling code here:

        String b_badan = berat_badanpasien.getText();
        String t_badan = txt_ttinggi.getText();
        String kadar_hb = txt_kadarhb.getText();
        String jml_darah = jumlah_darah.getText();

        String tgl_transfusi = null;
        if (tgl_transfusi_drh.getDate() != null) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            tgl_transfusi = format.format(tgl_transfusi_drh.getDate());
        }

        String jaminan = (String) jns_jaminan.getSelectedItem(); // untuk mengambil nilai dari jComboBox  berdasarkan list item dengan perintah getSelectedItem() ke data String.
        if (jaminan == "BPJS") { // kondisi data yang diambil dari jComboBox berdasarkan list item, gunakan operator pemberi nilai sama dengan (==), dan untuk nilai kondisi karakternya harus sama dengan karakter list item jComboBox.
            jaminan = "BPJS";
        } else if (jaminan == "TUNAI") {
            jaminan = "TUNAI";
        } else if (jaminan == "SWASTA") {
            jaminan = "SWASTA";
        } else if (jaminan == "Pilih") {
            jaminan = "";
        }

        int row = tabel_treatment.getSelectedRow();
        if (row >= 0) {
            int ok = JOptionPane.showConfirmDialog(null, "Yakin Mengedit Data Ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (ok == 0) {
                try {
                    koneksi DB = new koneksi();
                    DB.konek();
                    Connection con = DB.con;
                    String sql = "UPDATE treatment_rekam_medis SET berat_badan ='" + b_badan + "', tinggi_badan='" + t_badan + "',"
                            + "kadar_hb='" + kadar_hb + "', jenis_jaminan='" + jaminan + "'"
                            + " WHERE no_rek_medis='" + tabel_treatment.getValueAt(row, 0).toString() + "';";
                    PreparedStatement st = con.prepareStatement(sql);
                    st.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Edit Data Sukses");
                    bersihkaninput();
                    btn_edit.setEnabled(false);
                    tabel_model_treatment();
                    tampil_database();

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Edit Data Gagal", "Pesan", JOptionPane.ERROR_MESSAGE);
                    System.err.print("err: " + e.getMessage());
                }
            }
        }
    }//GEN-LAST:event_btn_editActionPerformed

    private void tabel_treatmentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_treatmentMouseClicked
        // TODO add your handling code here:

        save.setEnabled(false);
        btn_edit.setEnabled(true);
        btn_delete.setEnabled(true);
        try {
            DefaultTableModel model = (DefaultTableModel) tabel_treatment.getModel();
            int index = tabel_treatment.getSelectedRow();
            java.util.Date tgl = new SimpleDateFormat("yyyy-MM-dd").parse((String) model.getValueAt(index, 3));
            date_tgl_lahir.setDate(tgl);
        } catch (ParseException ex) {
            Logger.getLogger(D_master_treatment_pasien.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            DefaultTableModel model = (DefaultTableModel) tabel_treatment.getModel();
            int index = tabel_treatment.getSelectedRow();
            java.util.Date tgl = new SimpleDateFormat("yyyy-MM-dd").parse((String) model.getValueAt(index, 10));
            tgl_transfusi_drh.setDate(tgl);
        } catch (ParseException ex) {
            Logger.getLogger(D_master_treatment_pasien.class.getName()).log(Level.SEVERE, null, ex);
        }

        int i = tabel_treatment.getSelectedRow();
        if (i > -1) {
            rekam_medis.setText(tabel_treatment.getValueAt(i, 0).toString());
            nama_pasien.setText(tabel_treatment.getValueAt(i, 1).toString());
            nik_pasien.setText(tabel_treatment.getValueAt(i, 2).toString());
            goldar.setSelectedItem(tabel_treatment.getValueAt(i, 4).toString());
            jns_kelamin.setText(tabel_treatment.getValueAt(i, 5).toString());
            berat_badanpasien.setText(tabel_treatment.getValueAt(i, 6).toString());
            txt_ttinggi.setText(tabel_treatment.getValueAt(i, 7).toString());
            txt_kadarhb.setText(tabel_treatment.getValueAt(i, 8).toString());
            jumlah_darah.setText(tabel_treatment.getValueAt(i, 9).toString());
            jns_jaminan.setSelectedItem(tabel_treatment.getValueAt(i, 10).toString());
        }
    }//GEN-LAST:event_tabel_treatmentMouseClicked

    private void cleanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cleanActionPerformed
        // TODO add your handling code here:
        bersihkaninput();
        btn_edit.setEnabled(false);
        btn_delete.setEnabled(false);
        save.setEnabled(false);
        no_rekam_medis();
    }//GEN-LAST:event_cleanActionPerformed

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
        // TODO add your handling code here:

        int row = tabel_treatment.getSelectedRow();
        if (row >= 0) {
            int ok = JOptionPane.showConfirmDialog(null, "Yakin Mau Hapus?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (ok == 0) {
                try {
                    koneksi DB = new koneksi();
                    DB.konek();
                    Connection con = DB.con;
                    String sql = "DELETE FROM treatment_rekam_medis WHERE no_rek_medis='" + tabel_treatment.getValueAt(row, 0).toString() + "';";
                    PreparedStatement st = con.prepareStatement(sql);
                    st.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Delete Data Sukses");
                    btn_edit.setEnabled(false);
                    btn_delete.setEnabled(false);
                    save.setEnabled(true);
                    
                    bersihkaninput();
                    no_rekam_medis();
                    tabel_model.removeRow(row);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Delete Data gagal\n" + e.getMessage());
                }
            }
        }
    }//GEN-LAST:event_btn_deleteActionPerformed

    private void cari_pasienlagiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cari_pasienlagiActionPerformed
        // TODO add your handling code here:
        String cari = cari_dt_tabel.getText();

        tabel_model = new DefaultTableModel();
        tabel_treatment.setModel(tabel_model);

        tabel_model.addColumn("No. Rec Medis");
        tabel_model.addColumn("Nama");
        tabel_model.addColumn("NIK");
        tabel_model.addColumn("Tgl Lahir");
        tabel_model.addColumn("Gol. Darah");
        tabel_model.addColumn("Gender");
        tabel_model.addColumn("Berat");
        tabel_model.addColumn("Tinggi");
        tabel_model.addColumn("Kadar HB");
        tabel_model.addColumn("Jml Darah DiButuhkan");
        tabel_model.addColumn("Tgl Transfusi");
        tabel_model.addColumn("Jns Jaminan");

        try {

            koneksi connect = new koneksi(); // memanggil class koneksi
            connect.konek();
            Connection con = connect.con;
            Statement stat = connect.stm;
            String sql = "SELECT * FROM treatment_rekam_medsi WHERE nik LIKE '%"
                    + cari + "%'"
                    + "OR no_treatment LIKE '%" + cari + "%'";
            ResultSet res = stat.executeQuery(sql);

            while (res.next()) {
                Object[] obj = new Object[14];
                obj[0] = res.getString("no_rek_medis");
                obj[1] = res.getString("nama");
                obj[2] = res.getString("nik");
                obj[3] = res.getString("tgl_lahir");
                obj[4] = res.getString("gol_darah");
                obj[5] = res.getString("jen_kel");
                obj[6] = res.getString("berat_badan");
                obj[7] = res.getString("tinggi_badan");
                obj[8] = res.getString("kadar_hb");
                obj[9] = res.getString("jml_darah");
                obj[10] = res.getString("tgl_transfusi");
                obj[11] = res.getString("jenis_jaminan");

                tabel_model.addRow(obj);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_cari_pasienlagiActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        tabel_model_treatment();
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
            String namafile = "src/laporan/laporantreatment_pasien.jasper";
            koneksi DB = new koneksi();
            DB.konek();
            Connection con = DB.con;
            
            HashMap parameter = new HashMap();
            
            parameter.put("nom_medis", rekam_medis.getText());
             File report_file = new File(namafile);
             JasperReport jasperReport=(JasperReport) JRLoader.loadObject(report_file.getPath());
            JasperPrint jp = JasperFillManager.fillReport(jasperReport, parameter, con);
            JasperViewer.viewReport(jp, false);
      
            
            

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data Tidak dapat dicetak\n" + e.getMessage(), "Pesan", JOptionPane.ERROR_MESSAGE);
            System.err.print("err: " + e.getMessage());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tabel_treatmentKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabel_treatmentKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tabel_treatmentKeyPressed

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
            java.util.logging.Logger.getLogger(D_master_treatment_pasien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(D_master_treatment_pasien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(D_master_treatment_pasien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(D_master_treatment_pasien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
                new D_master_treatment_pasien().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField berat_badanpasien;
    private javax.swing.JToggleButton btn_delete;
    private javax.swing.JToggleButton btn_edit;
    private javax.swing.JTextField cari_dt_tabel;
    private javax.swing.JTextField cari_no_rm;
    private javax.swing.JButton cari_pasien;
    private javax.swing.JButton cari_pasienlagi;
    private javax.swing.JButton clean;
    private com.toedter.calendar.JDateChooser date_tgl_lahir;
    private javax.swing.JComboBox<String> goldar;
    private javax.swing.JButton hitung_darah;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
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
    private javax.swing.JComboBox<String> jns_jaminan;
    private javax.swing.JTextField jns_kelamin;
    private javax.swing.JTextField jumlah_darah;
    private javax.swing.JLabel lbljam;
    private javax.swing.JLabel lbltanggal;
    private javax.swing.JTextField nama_pasien;
    private javax.swing.JTextField nik_pasien;
    private javax.swing.JTextField rekam_medis;
    private javax.swing.JButton save;
    private javax.swing.JTable tabel_treatment;
    private com.toedter.calendar.JDateChooser tgl_transfusi_drh;
    private javax.swing.JTextField txt_kadarhb;
    private javax.swing.JTextField txt_ttinggi;
    // End of variables declaration//GEN-END:variables

    private String setText(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
