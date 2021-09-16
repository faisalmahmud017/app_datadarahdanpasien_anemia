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
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
public class E_obat_kelator_besi extends javax.swing.JFrame {

    DefaultTableModel tabel_model;

    /**
     * Creates new form E_obat_kelator_besi
     */
//    koneksi connect;
    public E_obat_kelator_besi() {
        initComponents();
        setIconImage(java.awt.Toolkit.getDefaultToolkit().getImage("src/icon/Logo_yayasan.png"));
        setTitle("(Data Obat Kelator Besi) - Aplikasi treatment darah pasien Anemia");

//        PlaceHolder holder1 = new PlaceHolder(txt_cari, "No.RM / nik / Nama");
        tabel_model_obat();
        get_database();
        btn_edit.setEnabled(false);
        btn_delete.setEnabled(false);
        no_rm.setEnabled(false);
        txt_nama.setEnabled(false);
        txt_nik.setEnabled(false);
        date_tgl_lahir.setEnabled(false);
        jns_jaminan.setEnabled(false);
        harga_obat.setEnabled(false);
        jumlah_dosis.setEnabled(false);
        total_harga.setEnabled(false);
//        setLocationRelativeTo(null);
        tipe_obat_combobox();

    }

    public void tipe_obat_combobox() {

        try {
            koneksi connect = new koneksi(); // memanggil class koneksi
            connect.konek();
            Connection con = connect.con;
            Statement stat = connect.stm;
            String sql = "SELECT DISTINCT tipe_obat FROM daftar_obat";
            ResultSet res = stat.executeQuery(sql);

            while (res.next()) {
                tipe_obat.addItem(res.getString("tipe_obat"));
            }

            res.last();
            int jumlahdata = res.getRow();
            res.first();

        } catch (SQLException e) {
        }
    }

    public void clear_inputan() {
        cari_data_rm.setText("");
        no_rm.setText("");
        txt_nik.setText("");
        txt_nama.setText("");
        date_tgl_lahir.setDate(null);
        jns_jaminan.setSelectedItem("Pilih");
        tipe_obat.setSelectedItem("Pilih");
        harga_obat.setText("");
        bb.setText("");
        jumlah_dosis.setText("");
        tgl_permintaan_obat.setDate(null);
        permohonan_jml_obat.setText("");
        realisasi_jml_obat.setText("");
        nama_obat.setSelectedItem(" ");
        total_harga.setText("");
        

    }

    public void tabel_model_obat() {
        tabel_model = new DefaultTableModel();
        //digunakan untuk memberi heading / judul /field pada kolom di tabel_pasien
        tabel_kelatorbesi.setModel(tabel_model);
        // "table_pasien sesuaikan dengan nama field jTable"
        tabel_model.addColumn("No. Rek Medis");
        tabel_model.addColumn("NIK");
        tabel_model.addColumn("Nama");
        tabel_model.addColumn("Tgl Lahir");
        tabel_model.addColumn("Jaminan");
        tabel_model.addColumn("Tipe Obat");
        tabel_model.addColumn("Berat Badan");
        tabel_model.addColumn("Jml Dosis");
        tabel_model.addColumn("Nama Obat");
        tabel_model.addColumn("Perm. Jml Obat");
        tabel_model.addColumn("Realisasi Obat");
        tabel_model.addColumn("Tgl Perm. Obat");
        tabel_model.addColumn("Total Harga");
    }

    public void get_database() {
        try {
            
// memanggil data pasien dari database yaitu di tabel pasien yg ada di databas
            koneksi connect = new koneksi(); // memanggil class koneksi
            connect.konek();
            Connection con = connect.con;
            Statement stat = connect.stm;
            String sql = "SELECT * FROM obat_kelator_besi;";
            ResultSet res = stat.executeQuery(sql);

//pengecekan terhadap data pasien di database
// get.String disesuaikan dengan field data pasien yg di databse yaitu di tabel pasien
            while (res.next()) {
                Object[] obj = new Object[14];
                obj[0] = res.getString("no_rek_medis");
                obj[1] = res.getString("nik");
                obj[2] = res.getString("nama");
                obj[3] = res.getString("tgl_lahir");
                obj[4] = res.getString("jenis_jaminan");
                obj[5] = res.getString("tipe_obat");
                obj[6] = res.getString("berat_badan");
                obj[7] = res.getString("jml_dosis");
                obj[8] = res.getString("nama_obat");
                obj[9] = res.getString("permohonan_jml_obat");
                obj[10] = res.getString("realisasi_obat");
                obj[11] = res.getString("harga");
                obj[12] = res.getString("tgl_permintaan_obat");

                tabel_model.addRow(obj);
            }
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, "Error\n" + err.getMessage());
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        nik = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        cari_data_rm = new javax.swing.JTextPane();
        jPanel2 = new javax.swing.JPanel();
        realisasi_obat1 = new javax.swing.JLabel();
        total_harga = new javax.swing.JTextField();
        tanggal_obat1 = new javax.swing.JLabel();
        tgl_permintaan_obat = new com.toedter.calendar.JDateChooser();
        realisasi_jml_obat = new javax.swing.JTextField();
        realisasi_obat = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        txt_cari = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        save = new javax.swing.JToggleButton();
        clean = new javax.swing.JToggleButton();
        txt_cari_norm = new javax.swing.JToggleButton();
        jPanel3 = new javax.swing.JPanel();
        tipe_obat = new javax.swing.JComboBox<>();
        jenis_obat = new javax.swing.JLabel();
        berat_badan = new javax.swing.JLabel();
        bb = new javax.swing.JTextField();
        jumlah_dosis = new javax.swing.JTextField();
        dosis_real = new javax.swing.JLabel();
        hitung_obat = new javax.swing.JButton();
        nama = new javax.swing.JLabel();
        tgl_lahir = new javax.swing.JLabel();
        jaminan = new javax.swing.JLabel();
        jns_jaminan = new javax.swing.JComboBox<>();
        date_tgl_lahir = new com.toedter.calendar.JDateChooser();
        txt_nik = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tanggal_obat = new javax.swing.JLabel();
        harga_obat = new javax.swing.JTextField();
        nama_obat = new javax.swing.JComboBox<>();
        tanggal_obat3 = new javax.swing.JLabel();
        tanggal_obat2 = new javax.swing.JLabel();
        permohonan_obat = new javax.swing.JLabel();
        permohonan_jml_obat = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        no_rm = new javax.swing.JTextField();
        txt_nama = new javax.swing.JTextField();
        btn_delete = new javax.swing.JToggleButton();
        btn_edit = new javax.swing.JToggleButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabel_kelatorbesi = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
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

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/doctors_folder_40px.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 10, -1, -1));

        jLabel2.setBackground(new java.awt.Color(153, 255, 51));
        jLabel2.setFont(new java.awt.Font("Times New Roman", 3, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 51));
        jLabel2.setText("DATA OBAT KELATOR BESI");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 20, -1, 26));

        nik.setForeground(new java.awt.Color(0, 0, 0));
        nik.setText("No. RM");
        jPanel1.add(nik, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

        cari_data_rm.setBackground(new java.awt.Color(255, 255, 255));
        cari_data_rm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cari_data_rmKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(cari_data_rm);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, 199, 30));

        jPanel2.setBackground(new java.awt.Color(255, 255, 0));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        realisasi_obat1.setForeground(new java.awt.Color(0, 0, 0));
        realisasi_obat1.setText("Total Harga");
        jPanel2.add(realisasi_obat1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 120, -1));

        total_harga.setBackground(new java.awt.Color(255, 255, 255));
        total_harga.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        total_harga.setForeground(new java.awt.Color(0, 0, 0));
        total_harga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                total_hargaActionPerformed(evt);
            }
        });
        jPanel2.add(total_harga, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 40, 180, -1));

        tanggal_obat1.setForeground(new java.awt.Color(0, 0, 0));
        tanggal_obat1.setText("TANGGAL PERMINTAAN OBAT");
        jPanel2.add(tanggal_obat1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 170, -1));

        tgl_permintaan_obat.setBackground(java.awt.Color.white);
        tgl_permintaan_obat.setForeground(new java.awt.Color(0, 0, 0));
        jPanel2.add(tgl_permintaan_obat, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 80, 220, -1));

        realisasi_jml_obat.setBackground(java.awt.Color.white);
        realisasi_jml_obat.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        realisasi_jml_obat.setForeground(new java.awt.Color(0, 0, 0));
        realisasi_jml_obat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                realisasi_jml_obatActionPerformed(evt);
            }
        });
        jPanel2.add(realisasi_jml_obat, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, 199, -1));

        realisasi_obat.setForeground(new java.awt.Color(0, 0, 0));
        realisasi_obat.setText("REALISASI JUMLAH OBAT");
        jPanel2.add(realisasi_obat, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, -1));

        jButton2.setText("Hitung");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 40, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 280, 490, 120));

        txt_cari.setBackground(new java.awt.Color(255, 255, 255));
        txt_cari.setForeground(new java.awt.Color(0, 0, 0));
        txt_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_cariActionPerformed(evt);
            }
        });
        jPanel1.add(txt_cari, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 430, 177, -1));

        jButton1.setText("CARI");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 430, -1, -1));

        save.setBackground(new java.awt.Color(102, 204, 0));
        save.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        save.setForeground(new java.awt.Color(0, 0, 51));
        save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Save_25px.png"))); // NOI18N
        save.setText("SAVE");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });
        jPanel1.add(save, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 280, -1, -1));

        clean.setBackground(new java.awt.Color(255, 255, 0));
        clean.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        clean.setForeground(new java.awt.Color(0, 0, 51));
        clean.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/broom_25px.png"))); // NOI18N
        clean.setText("CLEAN");
        clean.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cleanActionPerformed(evt);
            }
        });
        jPanel1.add(clean, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 330, -1, -1));

        txt_cari_norm.setText("Cari no. RM");
        txt_cari_norm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_cari_normActionPerformed(evt);
            }
        });
        jPanel1.add(txt_cari_norm, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 60, -1, -1));

        jPanel3.setBackground(new java.awt.Color(51, 255, 102));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tipe_obat.setBackground(java.awt.Color.white);
        tipe_obat.setForeground(new java.awt.Color(0, 0, 0));
        tipe_obat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih" }));
        tipe_obat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipe_obatActionPerformed(evt);
            }
        });
        jPanel3.add(tipe_obat, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 50, 211, -1));

        jenis_obat.setForeground(new java.awt.Color(0, 0, 0));
        jenis_obat.setText("TIPE OBAT");
        jPanel3.add(jenis_obat, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 60, -1, -1));

        berat_badan.setForeground(new java.awt.Color(0, 0, 0));
        berat_badan.setText("BERAT BADAN");
        jPanel3.add(berat_badan, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 100, -1, -1));

        bb.setBackground(java.awt.Color.white);
        bb.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        bb.setForeground(new java.awt.Color(0, 0, 0));
        bb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bbActionPerformed(evt);
            }
        });
        jPanel3.add(bb, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 90, 211, -1));

        jumlah_dosis.setBackground(java.awt.Color.white);
        jumlah_dosis.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jumlah_dosis.setForeground(new java.awt.Color(0, 0, 0));
        jPanel3.add(jumlah_dosis, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 130, 129, -1));

        dosis_real.setForeground(new java.awt.Color(0, 0, 0));
        dosis_real.setText("JUMLAH DOSIS");
        jPanel3.add(dosis_real, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 130, -1, -1));

        hitung_obat.setText("HITUNG");
        hitung_obat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hitung_obatActionPerformed(evt);
            }
        });
        jPanel3.add(hitung_obat, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 130, -1, -1));

        nama.setForeground(new java.awt.Color(0, 0, 0));
        nama.setText("NAMA PASIEN");
        jPanel3.add(nama, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, -1, -1));

        tgl_lahir.setForeground(new java.awt.Color(0, 0, 0));
        tgl_lahir.setText("TANGGAL LAHIR");
        jPanel3.add(tgl_lahir, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, -1));

        jaminan.setForeground(new java.awt.Color(0, 0, 0));
        jaminan.setText("JENIS JAMINAN");
        jPanel3.add(jaminan, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 20, -1, -1));

        jns_jaminan.setBackground(java.awt.Color.white);
        jns_jaminan.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jns_jaminan.setForeground(new java.awt.Color(0, 0, 0));
        jns_jaminan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih", "BPJS", "SWASTA", "TUNAI" }));
        jns_jaminan.setToolTipText("");
        jns_jaminan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jns_jaminanActionPerformed(evt);
            }
        });
        jPanel3.add(jns_jaminan, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 10, 199, -1));

        date_tgl_lahir.setForeground(new java.awt.Color(0, 0, 0));
        jPanel3.add(date_tgl_lahir, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 130, 199, -1));

        txt_nik.setBackground(new java.awt.Color(255, 255, 255));
        txt_nik.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        txt_nik.setForeground(new java.awt.Color(0, 0, 0));
        txt_nik.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nikActionPerformed(evt);
            }
        });
        jPanel3.add(txt_nik, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 50, 200, -1));

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("NIK");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, -1));

        tanggal_obat.setForeground(new java.awt.Color(0, 0, 0));
        tanggal_obat.setText("HARGA SATUAN");
        jPanel3.add(tanggal_obat, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 60, 170, -1));

        harga_obat.setBackground(java.awt.Color.white);
        harga_obat.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        harga_obat.setForeground(new java.awt.Color(0, 0, 0));
        jPanel3.add(harga_obat, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 50, 190, -1));

        nama_obat.setBackground(java.awt.Color.white);
        nama_obat.setForeground(new java.awt.Color(0, 0, 0));
        nama_obat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nama_obatActionPerformed(evt);
            }
        });
        jPanel3.add(nama_obat, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 10, 220, -1));

        tanggal_obat3.setForeground(new java.awt.Color(0, 0, 0));
        tanggal_obat3.setText("NAMA OBAT");
        jPanel3.add(tanggal_obat3, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 20, 160, -1));

        tanggal_obat2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        tanggal_obat2.setForeground(new java.awt.Color(0, 0, 0));
        tanggal_obat2.setText("Rp.");
        jPanel3.add(tanggal_obat2, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 50, 30, -1));

        permohonan_obat.setForeground(new java.awt.Color(0, 0, 0));
        permohonan_obat.setText("PERMOHONAN JUMLAH OBAT");
        jPanel3.add(permohonan_obat, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 100, -1, -1));

        permohonan_jml_obat.setBackground(java.awt.Color.white);
        permohonan_jml_obat.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        permohonan_jml_obat.setForeground(new java.awt.Color(0, 0, 0));
        permohonan_jml_obat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                permohonan_jml_obatActionPerformed(evt);
            }
        });
        jPanel3.add(permohonan_jml_obat, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 90, 199, -1));

        jLabel4.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("No. Rek Medis");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        no_rm.setBackground(new java.awt.Color(255, 255, 255));
        no_rm.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        no_rm.setForeground(new java.awt.Color(0, 0, 0));
        no_rm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                no_rmActionPerformed(evt);
            }
        });
        jPanel3.add(no_rm, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, 180, -1));

        txt_nama.setBackground(new java.awt.Color(255, 255, 255));
        txt_nama.setForeground(new java.awt.Color(0, 0, 0));
        jPanel3.add(txt_nama, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 90, 200, -1));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 100, 1160, 170));

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
        jPanel1.add(btn_delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 420, -1, -1));

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
        jPanel1.add(btn_edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 420, -1, -1));

        tabel_kelatorbesi.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "No. Rek Medis", "NIK", "Nama", "Tgl Lahir", "Jaminan", "Tipe Obat", "Berat Badan", "Jml Dosis", "Nama Obat", "Perm. Jml Obat", "Realisasi Obat", "Tgl Perm. Obat", "Total Harga"
            }
        ));
        tabel_kelatorbesi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_kelatorbesiMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabel_kelatorbesi);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 470, 1340, 160));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/print_25px.png"))); // NOI18N
        jButton3.setText("Print");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 420, -1, -1));

        jButton4.setText("Refresh Tabel");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 430, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1370, 660));

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

    private void permohonan_jml_obatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_permohonan_jml_obatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_permohonan_jml_obatActionPerformed

    private void realisasi_jml_obatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_realisasi_jml_obatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_realisasi_jml_obatActionPerformed

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        // TODO add your handling code here:
        koneksi connect = new koneksi(); // memanggil class koneksi
        connect.konek();
        Connection conn = connect.con;

        String rek_medis = no_rm.getText();
        String nik = txt_nik.getText();
        String nama = txt_nama.getText();

        String tgl_lahir = null;
        if (date_tgl_lahir.getDate() != null) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            tgl_lahir = format.format(date_tgl_lahir.getDate());
        }

        String jaminan = (String) jns_jaminan.getSelectedItem(); // untuk mengambil nilai dari jComboBox  berdasarkan list item dengan perintah getSelectedItem() ke data String.
        String tipeobat = (String) tipe_obat.getSelectedItem();
        String b_badan = bb.getText();
        String jml_dosis = jumlah_dosis.getText();
        String namaobat = (String) nama_obat.getSelectedItem();
        String permohonan_obat = permohonan_jml_obat.getText();
        String realisasi_obat = realisasi_jml_obat.getText();
        String ttl_harga = total_harga.getText();

        String tanggal = null;
        if (tgl_permintaan_obat.getDate() != null) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            tanggal = format.format(tgl_permintaan_obat.getDate());
        }

        try {
            koneksi DB = new koneksi();
            DB.konek();
            Connection con = DB.con;
            Statement stat = con.createStatement();

            String sql = "INSERT INTO obat_kelator_besi (no_rek_medis,nik,nama,tgl_lahir,jenis_jaminan,tipe_obat,berat_badan,jml_dosis,nama_obat,permohonan_jml_obat,realisasi_obat,harga,tgl_permintaan_obat)"
                    + " VALUES('" + rek_medis + "','" + nik + "','" + nama + "','" + tgl_lahir + "','" + jaminan + ""
                    + "','" + tipeobat + "','" + b_badan + "','" + jml_dosis + "','" + namaobat
                    + "','" + permohonan_obat + "','" + realisasi_obat + "','" + ttl_harga + "','" + tanggal + "')";

            stat.executeUpdate(sql);
            String data[] = new String[14];
            data[0] = rek_medis;
            data[1] = nik;
            data[2] = nama;
            data[3] = tgl_lahir;
            data[4] = jaminan;
            data[5] = tipeobat;
            data[6] = b_badan;
            data[7] = jml_dosis;
            data[8] = namaobat;
            data[9] = permohonan_obat;
            data[10] = realisasi_obat;
            data[11] = ttl_harga;
            data[12] = tanggal;
            JOptionPane.showMessageDialog(null, "Data berhasil tersimpan !!", "Succes", JOptionPane.INFORMATION_MESSAGE);
            clear_inputan();
            tabel_model.insertRow(0, data);
            stat.close();
            con.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Kesalahan Input", "Peringatan", JOptionPane.WARNING_MESSAGE);
            System.err.println(e.getMessage());
        }
    }//GEN-LAST:event_saveActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String cari = txt_cari.getText();

        DefaultTableModel tabel_model = new DefaultTableModel();
        //digunakan untuk memberi heading / judul /field pada kolom di tabel_pasien
        tabel_kelatorbesi.setModel(tabel_model);
        // "table_pasien sesuaikan dengan nama field jTable"
        tabel_model.addColumn("No. Rek Medis");
        tabel_model.addColumn("NIK");
        tabel_model.addColumn("Nama");
        tabel_model.addColumn("Tgl Lahir");
        tabel_model.addColumn("Jaminan");
        tabel_model.addColumn("Tipe Obat");
        tabel_model.addColumn("Berat Badan");
        tabel_model.addColumn("Jml Dosis");
        tabel_model.addColumn("Nama Obat");
        tabel_model.addColumn("Perm. Jml Obat");
        tabel_model.addColumn("Realisasi Obat");
        tabel_model.addColumn("Tgl Perm. Obat");
        tabel_model.addColumn("Total Harga");

        try {

            koneksi connect = new koneksi(); // memanggil class koneksi
            connect.konek();
            Connection con = connect.con;
            Statement stat = connect.stm;
            String sql = "SELECT * FROM obat_kelator_besi WHERE nik LIKE '%"
                    + cari + "%'"
                    + "OR no_treatment LIKE '%" + cari + "%'";
            ResultSet res = stat.executeQuery(sql);

            while (res.next()) {
                Object[] obj = new Object[13];
                obj[0] = res.getString("no_rek_medis");
                obj[1] = res.getString("nik");
                obj[2] = res.getString("nama");
                obj[3] = res.getString("tgl_lahir");
                obj[4] = res.getString("jenis_jaminan");
                obj[5] = res.getString("tipe_obat");
                obj[6] = res.getString("berat_badan");
                obj[7] = res.getString("jml_dosis");
                obj[8] = res.getString("nama_obat");
                obj[9] = res.getString("permohonan_jml_obat");
                obj[10] = res.getString("realisasi_obat");
                obj[11] = res.getString("harga");
                obj[12] = res.getString("tgl_permintaan_obat");

                tabel_model.addRow(obj);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Tidak Ditemukan\n" + e.getMessage());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txt_cari_normActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cari_normActionPerformed
        // TODO add your handling code here:
        String cari_rm = cari_data_rm.getText();
//        cari_data_rm.setEnabled(false);

        try {
            koneksi connect = new koneksi(); // memanggil class koneksi
            connect.konek();
            Connection con = connect.con;
            Statement stat = connect.stm;
            String sql = "SELECT * FROM treatment_rekam_medis WHERE no_rek_medis='" + cari_rm + "'";
            ResultSet res = stat.executeQuery(sql);

            while (res.next()) {
                no_rm.setText(res.getString("no_rek_medis"));
                txt_nik.setText(res.getString("nik"));
                txt_nama.setText(res.getString("nama"));
                java.util.Date tgl = new SimpleDateFormat("yyyy-MM-dd").parse((String) res.getString("tgl_lahir"));
                date_tgl_lahir.setDate(tgl);
                jns_jaminan.setSelectedItem(res.getString("jenis_jaminan"));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error\n" + e.getMessage());
        }
    }//GEN-LAST:event_txt_cari_normActionPerformed

    private void txt_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_cariActionPerformed

    private void hitung_obatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hitung_obatActionPerformed
        //String berat_badan=bb.getText();
        String jml_dosis = (String) tipe_obat.getSelectedItem();
        double jml_dosis_obat = 0;

        if (jml_dosis.equalsIgnoreCase("DEFERIPRONE TAB")) {
            jml_dosis_obat = 4.5 * Integer.parseInt(bb.getText());
        } else if (jml_dosis.equalsIgnoreCase("DEFERASIROX")) {
            jml_dosis_obat = 3 * Integer.parseInt(bb.getText());
        } else if (jml_dosis.equalsIgnoreCase("DEFEROXAMINE")) {
            jml_dosis_obat = 0 * Integer.parseInt(bb.getText());
        } else if (jml_dosis.equalsIgnoreCase("DEFERIPRONE SYRUP")) {
            jml_dosis_obat = (25 * Integer.parseInt(bb.getText()));
        }

        //        String.valueOf(jml_dosis_obat);
        jumlah_dosis.setText("" + jml_dosis_obat);

        nama_obat.addItem("nama_obat");
    }//GEN-LAST:event_hitung_obatActionPerformed

    private void bbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bbActionPerformed

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
        // TODO add your handling code here:
//        String tipe_obat = txt_tipe_obat.getText();
//        String nama_obat = txt_nama_obat.getText();
//        String harga_obat = txt_harga_obat.getText();
        int row = tabel_kelatorbesi.getSelectedRow();
        if (row >= 0) {
            int ok = JOptionPane.showConfirmDialog(null, "Yakin Mau Hapus?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (ok == 0) {
                try {
                    koneksi DB = new koneksi();
                    DB.konek();
                    Connection con = DB.con;
                    String sql = "DELETE FROM obat_kelator_besi WHERE no_rek_medis='" + tabel_kelatorbesi.getValueAt(row, 0).toString() + "';";
                    PreparedStatement st = con.prepareStatement(sql);
                    st.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Delete Data Sukses");
                    btn_edit.setEnabled(false);
                    btn_delete.setEnabled(false);
                    save.setEnabled(true);
                    clear_inputan();
                    tabel_model.removeRow(row);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Delete Data gagal\n" + e.getMessage());
                }
            }
        }
    }//GEN-LAST:event_btn_deleteActionPerformed

    private void btn_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editActionPerformed

        String tipeobat = (String) tipe_obat.getSelectedItem();
        String b_badan = bb.getText();
        String dosis = jumlah_dosis.getText();
        String namaobat = (String) nama_obat.getSelectedItem();
        String perm_obat = permohonan_jml_obat.getText();
        String realisasi = realisasi_jml_obat.getText();
        String harga = total_harga.getText();
        String tanggal = null;
        if (tgl_permintaan_obat.getDate() != null) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            tanggal = format.format(tgl_permintaan_obat.getDate());
        }

        int row = tabel_kelatorbesi.getSelectedRow();
        if (row >= 0) {
            int ok = JOptionPane.showConfirmDialog(null, "Yakin Mengedit Data Ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (ok == 0) {
                try {
                    koneksi DB = new koneksi();
                    DB.konek();
                    Connection con = DB.con;
                    String sql = "UPDATE obat_kelator_besi SET  tipe_obat='" + tipeobat + "', berat_badan='" + b_badan + "', "
                            + "jml_dosis='" + dosis + "', nama_obat='" + namaobat + "', permohonan_jml_obat='" + perm_obat + "', "
                            + "realisasi_obat='" + realisasi + "', harga='" + harga + "', tgl_permintaan_obat='" + tanggal + "'"
                            + " WHERE no_rek_medis='" + tabel_kelatorbesi.getValueAt(row, 0).toString() + "';";

                    PreparedStatement st = con.prepareStatement(sql);
                    st.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Edit Data Sukses");
                    clear_inputan();
                    btn_edit.setEnabled(false);
                    save.setEnabled(true);
                    btn_delete.setEnabled(false);
                    tabel_model_obat();
                    get_database();

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Edit Data Gagal", "Pesan", JOptionPane.ERROR_MESSAGE);
                    System.err.print("err: " + e.getMessage());
                }
            }
        }
    }//GEN-LAST:event_btn_editActionPerformed

    private void cari_data_rmKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cari_data_rmKeyPressed
        // TODO add your handling code here:
        int kode = evt.getKeyCode();
        if (kode == evt.VK_ENTER) {
            txt_cari_norm.requestFocus();
        }
    }//GEN-LAST:event_cari_data_rmKeyPressed

    private void total_hargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_total_hargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_total_hargaActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        int hargaobat = Integer.parseInt(harga_obat.getText());
        int realisasi = Integer.parseInt(realisasi_jml_obat.getText());

        int ttl_harga = hargaobat * realisasi;
        total_harga.setText(Integer.toString(ttl_harga));

//        double ttl_harga = hargaobat * realisasi;
//        total_harga.setText(Double.toString(ttl_harga));
//        NumberFormat nf = NumberFormat.getNumberInstance(new Locale("in", "ID"));
//        total_harga.setText(nf.format(ttl_harga));
//        Locale localeID = new Locale("in", "ID");
//        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(new Locale("In", "ID")); // merubah jadi Rp 00,000,000.00
//        total_harga.setText(formatRupiah.format(ttl_harga));
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tipe_obatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipe_obatActionPerformed
        // TODO add your handling code here:

        try {
            koneksi connect = new koneksi(); // memanggil class koneksi
            connect.konek();
            Connection con = connect.con;
            Statement stat = connect.stm;

            String jnis_obat = (String) tipe_obat.getSelectedItem();
            String sql = "SELECT nama_obat FROM daftar_obat WHERE tipe_obat='" + jnis_obat + "'";
            ResultSet res = stat.executeQuery(sql);

            nama_obat.removeAllItems();
            while (res.next()) {

                nama_obat.addItem(res.getString("nama_obat"));
            }

            res.last();
            int jumlahdata = res.getRow();
            res.first();

        } catch (SQLException e) {
        }
    }//GEN-LAST:event_tipe_obatActionPerformed

    private void nama_obatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nama_obatActionPerformed
        // TODO add your handling code here:
        try {
            koneksi connect = new koneksi(); // memanggil class koneksi
            connect.konek();
            Connection con = connect.con;
            Statement stat = connect.stm;

            String nma_obat = (String) nama_obat.getSelectedItem();
            String sql = "SELECT harga_obat FROM daftar_obat WHERE nama_obat='" + nma_obat + "'";
            ResultSet res = stat.executeQuery(sql);

            while (res.next()) {
                harga_obat.setText(res.getString("harga_obat"));

            }

            res.last();
            int jumlahdata = res.getRow();
            res.first();

        } catch (SQLException e) {
        }
    }//GEN-LAST:event_nama_obatActionPerformed

    private void no_rmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_no_rmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_no_rmActionPerformed

    private void jns_jaminanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jns_jaminanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jns_jaminanActionPerformed

    private void txt_nikActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nikActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nikActionPerformed

    private void cleanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cleanActionPerformed
        // TODO add your handling code here:
        clear_inputan();
        save.setEnabled(true);
        btn_edit.setEnabled(false);
        btn_delete.setEnabled(false);
    }//GEN-LAST:event_cleanActionPerformed

    private void tabel_kelatorbesiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_kelatorbesiMouseClicked
        // TODO add your handling code here:
        save.setEnabled(false);
        btn_edit.setEnabled(true);
        btn_delete.setEnabled(true);
        
         try {
            DefaultTableModel model = (DefaultTableModel) tabel_kelatorbesi.getModel();
            int index = tabel_kelatorbesi.getSelectedRow();
            java.util.Date tgl = new SimpleDateFormat("yyyy-MM-dd").parse((String) model.getValueAt(index, 3));
            date_tgl_lahir.setDate(tgl);
        } catch (ParseException ex) {
            Logger.getLogger(D_master_treatment_pasien.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            DefaultTableModel model = (DefaultTableModel) tabel_kelatorbesi.getModel();
            int index = tabel_kelatorbesi.getSelectedRow();
            java.util.Date tgl = new SimpleDateFormat("yyyy-MM-dd").parse((String) model.getValueAt(index, 12));
            tgl_permintaan_obat.setDate(tgl);
        } catch (ParseException ex) {
            Logger.getLogger(D_master_treatment_pasien.class.getName()).log(Level.SEVERE, null, ex);
        }

        int i = tabel_kelatorbesi.getSelectedRow();
        if (i > -1) {
            no_rm.setText(tabel_kelatorbesi.getValueAt(i, 0).toString());
            txt_nik.setText(tabel_kelatorbesi.getValueAt(i, 1).toString());
            txt_nama.setText(tabel_kelatorbesi.getValueAt(i, 2).toString());
            jns_jaminan.setSelectedItem(tabel_kelatorbesi.getValueAt(i, 4).toString());
            tipe_obat.setSelectedItem(tabel_kelatorbesi.getValueAt(i, 5).toString());
            bb.setText(tabel_kelatorbesi.getValueAt(i, 6).toString());
            jumlah_dosis.setText(tabel_kelatorbesi.getValueAt(i, 7).toString());
            nama_obat.setSelectedItem(tabel_kelatorbesi.getValueAt(i, 8).toString());
permohonan_jml_obat.setText(tabel_kelatorbesi.getValueAt(i, 9).toString());
realisasi_jml_obat.setText(tabel_kelatorbesi.getValueAt(i, 10).toString());
total_harga.setText(tabel_kelatorbesi.getValueAt(i, 11).toString());
        }
    }//GEN-LAST:event_tabel_kelatorbesiMouseClicked

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

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
try {
            String namafile = "src/laporan/data_obat_kelator_besi.jasper";
            koneksi DB = new koneksi();
            DB.konek();
            Connection con = DB.con;
            
            HashMap parameter = new HashMap();
            
            parameter.put("nom_medis", no_rm.getText());
             File report_file = new File(namafile);
             JasperReport jasperReport=(JasperReport) JRLoader.loadObject(report_file.getPath());
            JasperPrint jp = JasperFillManager.fillReport(jasperReport, parameter, con);
            JasperViewer.viewReport(jp, false);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data Tidak dapat dicetak\n" + e.getMessage(), "Pesan", JOptionPane.ERROR_MESSAGE);
            System.err.print("err: " + e.getMessage());
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        tabel_model_obat();
        get_database();
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(E_obat_kelator_besi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(E_obat_kelator_besi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(E_obat_kelator_besi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(E_obat_kelator_besi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new E_obat_kelator_besi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField bb;
    private javax.swing.JLabel berat_badan;
    private javax.swing.JToggleButton btn_delete;
    private javax.swing.JToggleButton btn_edit;
    private javax.swing.JTextPane cari_data_rm;
    private javax.swing.JToggleButton clean;
    private com.toedter.calendar.JDateChooser date_tgl_lahir;
    private javax.swing.JLabel dosis_real;
    private javax.swing.JTextField harga_obat;
    private javax.swing.JButton hitung_obat;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel jaminan;
    private javax.swing.JLabel jenis_obat;
    private javax.swing.JComboBox<String> jns_jaminan;
    private javax.swing.JTextField jumlah_dosis;
    private javax.swing.JLabel nama;
    private javax.swing.JComboBox<String> nama_obat;
    private javax.swing.JLabel nik;
    private javax.swing.JTextField no_rm;
    private javax.swing.JTextField permohonan_jml_obat;
    private javax.swing.JLabel permohonan_obat;
    private javax.swing.JTextField realisasi_jml_obat;
    private javax.swing.JLabel realisasi_obat;
    private javax.swing.JLabel realisasi_obat1;
    private javax.swing.JToggleButton save;
    private javax.swing.JTable tabel_kelatorbesi;
    private javax.swing.JLabel tanggal_obat;
    private javax.swing.JLabel tanggal_obat1;
    private javax.swing.JLabel tanggal_obat2;
    private javax.swing.JLabel tanggal_obat3;
    private javax.swing.JLabel tgl_lahir;
    private com.toedter.calendar.JDateChooser tgl_permintaan_obat;
    private javax.swing.JComboBox<String> tipe_obat;
    private javax.swing.JTextField total_harga;
    private javax.swing.JTextField txt_cari;
    private javax.swing.JToggleButton txt_cari_norm;
    private javax.swing.JTextField txt_nama;
    private javax.swing.JTextField txt_nik;
    // End of variables declaration//GEN-END:variables

    //To change body of generated methods, choose Tools | Templates.
}
