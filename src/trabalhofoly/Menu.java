/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhofoly;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ogarcia
 */
public class Menu extends javax.swing.JFrame {

    /**
     * Creates new form Menu
     */
    public Menu() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jogo = new javax.swing.JButton();
        tempo = new javax.swing.JButton();
        vendaFato = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jogo.setText("Jogo");
        jogo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jogoActionPerformed(evt);
            }
        });

        tempo.setText("Tempo");
        tempo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tempoActionPerformed(evt);
            }
        });

        vendaFato.setText("jButton3");
        vendaFato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vendaFatoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(jogo)
                        .addGap(73, 73, 73)
                        .addComponent(tempo))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(113, 113, 113)
                        .addComponent(vendaFato)))
                .addContainerGap(156, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jogo)
                    .addComponent(tempo))
                .addGap(34, 34, 34)
                .addComponent(vendaFato)
                .addContainerGap(142, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jogoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jogoActionPerformed
         Conexao c = new Conexao();
        Connection con = c.getConnection();
        Connection dw = c.getDW();
        System.out.println("Conectado!");
        // pega a conexão e o Statement        
        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement("SELECT distinct genero,empresa FROM games.jogo J " +
                " left join games.empresa E on J.empresa_id = E.id");
             ResultSet rs = stmt.executeQuery();
             System.out.print(rs);
            while (rs.next()) {                
                String sql = "insert into jogo " +
                    "(genero,empresa)" +
                    " values (?,?)";

                // prepared statement para inserção
                PreparedStatement stmtDw = dw.prepareStatement(sql);

                // seta os valores                
                stmtDw.setString(1,rs.getString("genero"));
                stmtDw.setString(2,rs.getString("empresa"));                

                // executa
                stmtDw.execute();
                stmtDw.close();
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
        }          
    }//GEN-LAST:event_jogoActionPerformed

    private void tempoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tempoActionPerformed
          Conexao c = new Conexao();
        Connection con = c.getConnection();
        Connection dw = c.getDW();
        System.out.println("Conectado!");
        // pega a conexão e o Statement        
        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement("SELECT * from jogo");
             ResultSet rs = stmt.executeQuery();
             System.out.print(rs);
            while (rs.next()) {                
                String sql = "insert into tempo " +
                    "(ano,decada)" +
                    " values (?,?)";                
                // prepared statement para inserção
                PreparedStatement stmtDw = dw.prepareStatement(sql);                
                // seta os valores
                char digito1 =  rs.getString("ano").charAt(rs.getString("ano").length()-1);
                char digito2 =  rs.getString("ano").charAt(rs.getString("ano").length()-2);
                
                if(Character.getNumericValue(digito1) == 0){
                    digito1 = '2';
                    
                    digito2 = '0';                   
                }else{
                    digito2 = '0';                    
                }
                String decada = digito1+""+digito2;
                System.out.println("Decada:"+digito1+digito2);
                stmtDw.setString(1,rs.getString("ano"));
                stmtDw.setString(2,decada);                             
                // executa
                stmtDw.execute();
                stmtDw.close();
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
        }          
    }//GEN-LAST:event_tempoActionPerformed

    private void vendaFatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vendaFatoActionPerformed
        Conexao c = new Conexao();
        Connection con = c.getConnection();
        Connection dw = c.getDW();
        System.out.println("Conectado!");
        // pega a conexão e o Statement        
        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement("SELECT V.local,quantidade,genero,empresa, ano FROM games.venda V " +
            "left join games.jogo J " +
            "on J.id = V.jogo_id " +
            "left join games.empresa E " +
            "on J.empresa_id = E.id " +
            " where local = 'Vendas Total'");            
             ResultSet rs = stmt.executeQuery();
             System.out.print(rs);
                          
            while (rs.next()) {                                
                String fk = "select * from jogo where genero = ? and empresa = ?";
                PreparedStatement stmtFk = dw.prepareStatement(fk);
                System.out.println("Genero"+rs.getString("genero"));
                stmtFk.setString(1,rs.getString("genero"));
                stmtFk.setString(2,rs.getString("empresa"));
                ResultSet rsFk = stmtFk.executeQuery();
                rsFk.first();
                int idJogo = rsFk.getInt("idJogo");
                //pegando FK tempo
                fk = "select * from tempo where ano = ?";
                stmtFk = dw.prepareStatement(fk);   
                stmtFk.setString(1,rs.getString("ano"));
                rsFk = stmtFk.executeQuery();
                rsFk.first();
                int idTempo = rsFk.getInt("idTempo");
                stmtFk.close();

                // prepared statement para inserção
                String sql = "insert into venda_fato " +
                    "(unidades_vendidas,jogo_id,tempo_id)" +
                    " values (?,?,?)";
                PreparedStatement stmtDw = dw.prepareStatement(sql);

                // seta os valores                
                stmtDw.setString(1,rs.getString("quantidade"));
                stmtDw.setInt(2,idJogo);                
                stmtDw.setInt(3,idTempo);

                // executa
                stmtDw.execute();
                stmtDw.close();
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
        }          
    }//GEN-LAST:event_vendaFatoActionPerformed

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
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jogo;
    private javax.swing.JButton tempo;
    private javax.swing.JButton vendaFato;
    // End of variables declaration//GEN-END:variables
}
