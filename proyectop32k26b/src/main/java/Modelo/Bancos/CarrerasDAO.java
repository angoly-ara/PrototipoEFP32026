/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
 // Angoly Camila Araujo Mayen 9959-24-17623

package Modelo.Bancos;

import Controlador.Bancos.clsCarreras;
import Controlador.clsUsuarioConectado;
import Modelo.BitacoraDAO;
import Modelo.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarrerasDAO {

    private static final int APL_CODIGO = 5900;

    // ── LISTAR TODOS ────────────────────────────────────────────
    public List<clsCarreras> listar() {
        List<clsCarreras> lista = new ArrayList<>();
        String sql = "SELECT * FROM carreras";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                clsCarreras c = new clsCarreras();
                c.setCodigo_carrera(rs.getString("codigo_carrera"));
                c.setNombre_carrera(rs.getString("nombre_carrera"));
                c.setCodigo_facultad(rs.getString("codigo_facultad"));
                c.setEstatus_carrera(rs.getString("estatus_carrera"));
                lista.add(c);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    // ── INSERTAR ────────────────────────────────────────────────
    public void insert(clsCarreras c) {
        String sql = "INSERT INTO carreras (codigo_carrera, nombre_carrera, "
                   + "codigo_facultad, estatus_carrera) VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, c.getCodigo_carrera());
            ps.setString(2, c.getNombre_carrera());
            ps.setString(3, c.getCodigo_facultad());
            ps.setString(4, c.getEstatus_carrera());
            ps.executeUpdate();

            new BitacoraDAO().insert(clsUsuarioConectado.getUsuId(), APL_CODIGO, "INSERT Carreras");

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al insertar carrera", e);
        }
    }

    // ── ACTUALIZAR ──────────────────────────────────────────────
    public void update(clsCarreras c) {
        String sql = "UPDATE carreras SET nombre_carrera=?, codigo_facultad=?, "
                   + "estatus_carrera=? WHERE codigo_carrera=?";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, c.getNombre_carrera());
            ps.setString(2, c.getCodigo_facultad());
            ps.setString(3, c.getEstatus_carrera());
            ps.setString(4, c.getCodigo_carrera());
            int rows = ps.executeUpdate();

            if (rows == 0) throw new RuntimeException("No se encontró la carrera para actualizar");

            new BitacoraDAO().insert(clsUsuarioConectado.getUsuId(), APL_CODIGO, "UPDATE Carreras");

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al actualizar carrera", e);
        }
    }

    // ── ELIMINAR ────────────────────────────────────────────────
    public void delete(String codigo) {
        String sql = "DELETE FROM carreras WHERE codigo_carrera=?";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, codigo);
            int rows = ps.executeUpdate();

            if (rows == 0) throw new RuntimeException("No se encontró la carrera para eliminar");

            new BitacoraDAO().insert(clsUsuarioConectado.getUsuId(), APL_CODIGO, "DELETE Carreras");

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al eliminar carrera", e);
        }
    }

    // ── CONSULTAR POR CÓDIGO ─────────────────────────────────────
    public clsCarreras query(String codigo) {
        clsCarreras c = null;
        String sql = "SELECT * FROM carreras WHERE codigo_carrera=?";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, codigo);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    c = new clsCarreras();
                    c.setCodigo_carrera(rs.getString("codigo_carrera"));
                    c.setNombre_carrera(rs.getString("nombre_carrera"));
                    c.setCodigo_facultad(rs.getString("codigo_facultad"));
                    c.setEstatus_carrera(rs.getString("estatus_carrera"));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al consultar carrera", e);
        }
        return c;
    }

    // ── ELIMINAR TODOS ───────────────────────────────────────────
    public void deleteAll() throws Exception {
        try (Connection conn = Conexion.getConnection()) {
            conn.createStatement().executeUpdate("SET FOREIGN_KEY_CHECKS = 0");
            conn.createStatement().executeUpdate("DELETE FROM carreras");
            conn.createStatement().executeUpdate("SET FOREIGN_KEY_CHECKS = 1");
        }
    }
}