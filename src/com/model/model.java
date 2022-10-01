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

    @Override
    public void Reset(View siswa) throws SQLException {
                siswa.txtNIS.setText("");
    siswa.txtNama.setText("");
    siswa.rbLaki.setSelected(true);
    siswa.cbJurusan.setSelectedIndex(0);
    }

    @Override
    public void Ubah(View siswa) throws SQLException {
                if (siswa.rbLaki.isSelected()){
        jk = "Laki-laki";
        } 
        else{
        jk = "Perempuan";
        }
        try{
        Connection con = koneksi.getcon();
        String sql = "UPDATE siswa SET nama=?, jenis_kelamin=?, "+"jurusan=? WHERE NIS=?";
        PreparedStatement prepare =con.prepareStatement(sql);
        prepare.setString(4, siswa.txtNIS.getText());
        prepare.setString(1, siswa.txtNama.getText());
        prepare.setString(2, jk);
        prepare.setString(3, (String) siswa.cbJurusan.getSelectedItem());
        prepare.executeUpdate();
        JOptionPane.showMessageDialog(null, "Data berhasil diubah");
        }
        catch (Exception e){
            System.out.println(e);
        }
        finally{
        Tampil (siswa);
        siswa.setLebarKolom();
        Baru(siswa);
        }
    }

    @Override
    public void Baru(View siswa) throws SQLException {
        siswa.txtNIS.setText("");
    siswa.txtNama.setText("");
    siswa.rbLaki.setSelected(true);
    siswa.cbJurusan.setSelectedIndex(0);
    }

  @Override
    public void Tampil(View siswa) throws SQLException {
       siswa.tblmodel.getDataVector().removeAllElements();
    siswa.tblmodel.fireTableDataChanged();
    try{
    Connection con = koneksi.getcon();
    Statement stt = con.createStatement();
    String sql = "SELECT * FROM siswa ORDER BY NIS ASC";
    ResultSet res = stt.executeQuery(sql);
    while (res.next()){
        Object[] ob = new Object[8];
        ob[0] = res.getString(1);
        ob[1] = res.getString(2);
        ob[2] = res.getString(3);
        ob[3] = res.getString(4);
        siswa.tblmodel.addRow(ob);
    }
    }catch(Exception e){
        System.out.println(e);
    }
    }

    @Override
    public void Hapus(View siswa) throws SQLException {
           try {
            Connection con = koneksi.getcon();
            String sql = "DELETE FROM siswa WHERE NIS =?";
            PreparedStatement prepare = con.prepareStatement(sql);
            prepare.setString(1, siswa.txtNIS.getText());
            prepare.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
            prepare.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        finally{
        Tampil(siswa);
        siswa.setLebarKolom();
            Baru(siswa);
        }
    }
}
