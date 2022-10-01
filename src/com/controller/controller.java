
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.controller;
import com.view.View;
import java.sql.SQLException;
/**
 *
 * @author MSI
 */
public interface controller {
    public void Simpan(View siswa) throws SQLException;
    public void Reset(View siswa) throws SQLException;
    public void Ubah(View siswa) throws SQLException;
    public void Baru(View siswa) throws SQLException;
    public void Tampil(View siswa) throws SQLException;
}
