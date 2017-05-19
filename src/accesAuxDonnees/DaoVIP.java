/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesAuxDonnees;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;
import tables.VIP;

/**
 *
 * @author Alain
 */
public class DaoVIP {

    private final Connection connexion;

    public DaoVIP(Connection connexion) {
        this.connexion = connexion;
    }

    public void recupererVIP(List<VIP> lesVIP) throws SQLException {
        String requete = "select * from VIP";
        Statement stmt = connexion.createStatement();
        ResultSet rset = stmt.executeQuery(requete);
        while (rset.next()) {       // traitement du résulat
            int numVIP = rset.getInt(1);
            String nom = rset.getString(2);
            String prenom = rset.getString(3);
            String civilite = rset.getString(4);
            LocalDate datenaissance = rset.getDate(5).toLocalDate();
            String lieunaissance = rset.getString(6);
            String role = rset.getString(7);
            String statut = rset.getString(8);
            String pays = rset.getString(9);

            VIP personne = new VIP(numVIP, nom, prenom, civilite, datenaissance, lieunaissance, role, statut, pays);
            lesVIP.add(personne);
        }
        rset.close();
        stmt.close();
    } // recupererGroupes

    public void lireLesVIP(List<VIP> lesVIP) throws SQLException {
        String requete = "select * from VIP";
        PreparedStatement pstmt = connexion.prepareStatement(requete);
        ResultSet rset = pstmt.executeQuery(requete);
        while (rset.next()) {       // traitement du résulat
            int numVIP = rset.getInt(1);
            String nom = rset.getString(2);
            String prenom = rset.getString(3);
            String civilite = rset.getString(4);
            LocalDate datenaissance = rset.getDate(5).toLocalDate();
            String lieunaissance = rset.getString(6);
            String role = rset.getString(7);
            String statut = rset.getString(8);
            String pays = rset.getString(9);
            VIP temp = new VIP(numVIP, nom, prenom, civilite, datenaissance, lieunaissance, role, statut, pays);
            lesVIP.add(temp);
        }
        rset.close();
        pstmt.close();
    }

    public void supprimerVIP(int numVIP) throws SQLException {
        String requete = "delete from VIP where numVIP = ?";
        PreparedStatement pstmt = connexion.prepareStatement(requete);
        pstmt.setInt(1, numVIP);
        pstmt.executeUpdate();
        pstmt.close();
    }

    public void insererVIP(VIP vip) throws SQLException {
        String requete = "insert into EMP (empno, ename, job, hiredate, deptno) values(?,?,?,?,?)";
        PreparedStatement pstmt = connexion.prepareStatement(requete);
        pstmt.setInt(1, vip.getNumVIP());
        pstmt.setString(2, vip.getNom());
        pstmt.setString(3, vip.getPrenom());
        pstmt.setString(4, vip.getCivilite());
        pstmt.setDate(5, java.sql.Date.valueOf(vip.getDatenaissance()));
        pstmt.setString(6, vip.getLieunaissance());
        pstmt.setString(7, vip.getRole());
        pstmt.setString(8, vip.getStatut());
        pstmt.setString(9, vip.getPays());
        pstmt.executeUpdate();
        pstmt.close();
    }

} // class DaoGroupe