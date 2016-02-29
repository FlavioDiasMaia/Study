/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static GUI.conexao.ps;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.YES_NO_OPTION;

/**
 *
 * @author Flávio
 * #Studying graphical interface
 */
public class dsa extends javax.swing.JFrame {

    public dsa() { //To set the frame size according to the resolution of the computer.
      initComponents();
      jtablearquivos();
      Toolkit kit = Toolkit.getDefaultToolkit();  
      Dimension tamTela = kit.getScreenSize(); //Self explanatory
      int larg = (int) (tamTela.width * 1.0);  
      int alt =  (int) (tamTela.height * 0.95);  
      setSize(larg,alt); //Changing according to the width and height
      this.setLocationRelativeTo(null);
      
        
                
    } 
    String id1 = "";
    public void jtablearquivos(){ //Table config
        
        javax.swing.table.DefaultTableModel dtm4 = (javax.swing.table.DefaultTableModel)jtablearquivos.getModel();  
        dtm4.setNumRows(0);  
        
        int k=0;
       try{
        conexao.Conectar();
        conexao.sql = "SELECT data,nomefile FROM arquivo ORDER BY data DESC";//Coding for get information for table 
        conexao.ps = conexao.con.prepareStatement(conexao.sql);
        conexao.rs = conexao.ps.executeQuery();
        while(conexao.rs.next())
        {
            
            dtm4.addRow(new Object[]{" ","  "," "}); 
            jtablearquivos.setValueAt(conexao.rs.getString(1),k,0);  
            jtablearquivos.setValueAt(conexao.rs.getString(2),k,1);  
            k++;
        }
        
        
        }catch(SQLException ex){
               JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Erro ao popular tabelas!", JOptionPane.ERROR_MESSAGE);
           }
    }
    
     
   
 
    
    String titulo1 = "";
    public void gettableconteudo(){ //For get files of table
        
        int selecionada = jtablearquivos.getSelectedRow();
        if (selecionada == -1) {
            JOptionPane.showMessageDialog(null," Arquivo não selecionado corretamente !");
        }   
        String id = jtablearquivos.getValueAt(selecionada, 0).toString();
        id1 = id;
        String titulo = jtablearquivos.getValueAt(selecionada, 1).toString();
        titulo1 = titulo;
        
        
        
    }
    
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        jLabel3.setVisible(false);
        exportarapolice2();
        gettableconteudo();
       
        try {
        java.awt.Desktop.getDesktop().open( new File("C:\\rubinhosis\\exportacoes\\" + titulo1 ) );
        } catch (IOException ex) {
            Logger.getLogger(dsa.class.getName()).log(Level.SEVERE, null, ex);
        }
   
        
      
       
    }
    
    public void exportarapolice(){ //export file of table
    gettableconteudo();
    conexao.Conectar();
    File f = null;
    try {
        conexao.sql = "SELECT data, nomefile, arquivo FROM arquivo WHERE data = ?"; 
        conexao.ps = conexao.con.prepareStatement(conexao.sql); 
        ps.setString(1, id1);
        ResultSet rs = ps.executeQuery(); 
        if ( rs.next() ){ 
            byte [] bytes = rs.getBytes("arquivo"); 
            String nome = rs.getString("nomefile");
            f = new File("C:\\rubinhosis\\exportacoes\\" + nome); 
            FileOutputStream fos = new FileOutputStream( f);
            fos.write( bytes ); 
            fos.close(); 
        }
        rs.close(); 
        ps.close(); 
        
         
    } catch (SQLException ex) { 
        ex.printStackTrace();
    }
    catch (IOException ex) {
        ex.printStackTrace();
    }

}
    
public void exportarapolice2(){
    gettableconteudo();
    conexao.Conectar();
    File f = null;
    try {
        conexao.sql = "SELECT data, nomefile, arquivo FROM arquivo WHERE data = ?"; 
        conexao.ps = conexao.con.prepareStatement(conexao.sql); 
        ps.setString(1, id1);
        ResultSet rs = ps.executeQuery(); 
        if ( rs.next() ){ 
            byte [] bytes = rs.getBytes("arquivo"); 
            String nome = rs.getString("nomefile");
            f = new File("C:\\rubinhosis\\cache\\"+nome); 
            FileOutputStream fos = new FileOutputStream( f);
            fos.write( bytes ); 
            fos.close(); 
        }
        rs.close(); 
        ps.close(); 
        
         
    } catch (SQLException ex) { 
        ex.printStackTrace();
    }
    catch (IOException ex) {
        ex.printStackTrace();
    }

}
    
    //successful operation warning
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        jLabel3.setText("Lembrando que os documentos são exportados por padrão para : C:\\exportacoes\\");
        exportarapolice();
        JOptionPane.showMessageDialog(null, "Apólice exportada com sucesso", "Exportar Apólice",JOptionPane.INFORMATION_MESSAGE);
        
        
       
    }
    
    public void preenchercomlike(){ //for using %LIKE to occupy Table
        
        
        javax.swing.table.DefaultTableModel dtm4 = (javax.swing.table.DefaultTableModel)jtablearquivos.getModel();  
        dtm4.setNumRows(0);  
        
       int k=0;
       try{
        conexao.Conectar();
        conexao.sql = "SELECT data,nomefile FROM arquivo WHERE nomefile LIKE ? ";
        conexao.ps = conexao.con.prepareStatement(conexao.sql); 
        ps.setString(1,"%"+jTextField2.getText()+"%");
        conexao.rs = conexao.ps.executeQuery();
        
        
        
        while(conexao.rs.next())
        {
            
            dtm4.addRow(new Object[]{" ","  "," "}); 
            jtablearquivos.setValueAt(conexao.rs.getString(1),k,0);  
            jtablearquivos.setValueAt(conexao.rs.getString(2),k,1);  
            k++;
        }
        
        
        }catch(SQLException ex){
               JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Erro ao popular tabelas!", JOptionPane.ERROR_MESSAGE);
           }
        
    }
    
    
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        
        preenchercomlike();
        
        
    }

    private void jTextField2KeyPressed(java.awt.event.KeyEvent evt) {
        
        preenchercomlike();
    }

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {
        
        new Menu().setVisible(true);
        this.setVisible(false);
    }

    private void jButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseReleased
        
        
    }
 