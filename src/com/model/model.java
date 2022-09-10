/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.model;

import com.view.View;
import com.controller.controller;
import com.koneksi.koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
/**
 *
 * @author MSI
 */
public class model implements com.controller.controller{
  String jk;  

    @Override
    public void Simpan(View siswa) throws SQLException {
        if (siswa.rbLaki.isSelected()){
        jk = "Laki-Laki";
        }
        else {
        jk = "Perempuan";
        }
        try {
        Connection con = koneksi.getcon();
        String sql = "Insert Into siswa Values(?,?,?,?)";
        PreparedStatement prepare = con.prepareStatement(sql);
        prepare.setString(1, siswa.txtNIS.getText());
        prepare.setString(2, siswa.txtNama.getText());
        prepare.setString(3, jk);
        prepare.setString(4, (String)siswa.cbJurusan.getSelectedItem());
        prepare.executeUpdate();
        JOptionPane.showMessageDialog(null, "Data berhasil disimpan");
        prepare.close();
            Baru(siswa);
        }
        catch (Exception e){
            System.out.println(e);
        }
        finally{
        Tampil(siswa);
        siswa.setLebarKolom();
                }
    }
}
