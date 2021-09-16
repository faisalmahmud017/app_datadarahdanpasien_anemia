/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import page.*;

/**
 *
 * @author Faisal <faismud017@gmail.com>
 */
public class user_session {

    private static String nama;

    public static String tampil_nama() {
        return nama;
    }

    public static void simpan_nama(String nama) {
        user_session.nama = nama;
    }
}
