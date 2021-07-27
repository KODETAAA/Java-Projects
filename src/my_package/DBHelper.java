/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my_package;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Psalm XXIII V. Alonzo
 * version 1.0
 * @date 3/10/2021
 */
public class DBHelper {
    Connection con = null;
    Statement st = null;
    PreparedStatement pt = null;
    public void connectDB() throws Exception{
        con = DriverManager.getConnection("jdbc:derby://localhost:1527/dbCreate", "alonzo", "alonzo");
        System.out.println("Connected to Database!");
    }
    
    public boolean insertRecord(String fname, String uname, String pass, int phone, String gen, int id, String addr){
       boolean flag = false;
        
        try {
            st = con.createStatement();
            String sql = "Insert into tblUser values ('"+fname+"','"+uname+"','"+pass+"',"+phone+",'"+gen+"','"+addr+"',"+id+")";
            
        if(st.executeUpdate(sql) == 1)
                flag = true;
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
            return flag;
        }
    
     public boolean insertRecordResevation(String des,String from, String date, String time, int tourist, String vec, int id){
       boolean flag = false;
        
        try {
            st = con.createStatement();
            String sql = "Insert into tblReservationRecord values ('"+des+"','"+from+"','"+date+"','"+time+"',"+tourist+",'"+vec+"',"+id+")";
            
            if(st.executeUpdate(sql) == 1)
                flag = true;
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
            return flag;
        }
    
    public ResultSet displayAllRecord(){
       ResultSet rss = null;
        try {
            st = con.createStatement();
            String sql = "Select * from tblReservationRecord";
             rss = st.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rss;
    }
    
     public ResultSet displayByDestination(String des){
       ResultSet rss = null;
        try {
            
            st = con.createStatement();
             
            String sql = "Select * from tblReservationRecord where destination = '"+des+"'";
             rss = st.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rss;
    }
     
     public boolean deleteReservationByReservationId(String id){
       boolean flag = false;
       
        try {
            String sql = "Delete from tblReservationRecord where Reservation_Id =? ";
            pt = con.prepareStatement(sql);           
            pt.setString(1,id);
            pt.executeUpdate();
             flag = true;
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
       return flag;
    }
     
    public boolean updateReservation(String des,String from, String date, String time, int tourist, String vec, int reid){
         boolean flag = false;
         //int id = Integer.parseInt(reid);
         //int id = Integer.parseInt(reid);
        try {
            st = con.createStatement();
            String sql = "Update tblReservationRecord set destination = '"+des+"', Starting_From = '"+from+"', Date = '"+date+"', Time = '"+time+"', Number_Of_People = "+tourist+", Type_Of_Vehicle = '"+vec+"' where Reservation_Id = "+reid+" ";
           if(st.executeUpdate(sql) == 1) {
               flag = true;
           }
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
            return flag;
        }
          return flag;
     }
    
     public ResultSet displayAllUsers(){
       ResultSet rss = null;
        try {
            st = con.createStatement();
            String sql = "Select * from tblUser";
             rss = st.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rss;
    }
  
     public boolean updateUser(String fname,String uname, String pass, int phone, String gen, String addr, int id){
         boolean flag = false;
         //int id = Integer.parseInt(reid);
         //int id = Integer.parseInt(reid);
        try {
            st = con.createStatement();
            String sql = "Update tblUser set fullname = '"+fname+"', Username = '"+uname+"', Password = '"+pass+"', Phone = "+phone+", Gender = '"+gen+"', Address = '"+addr+"' where TouristID = "+id+"";
           if(st.executeUpdate(sql) == 1) {
               flag = true;
           }
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
            return flag;
        }
          return flag;
     }
     
     public ResultSet displayByUsername(String user){
       ResultSet rss = null;
        try {
            
            st = con.createStatement();
             
            String sql = "Select * from tblUser where Username = '"+user+"'";
             rss = st.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rss;
    }
    
     public boolean deleteUserByTouristId(String id){
       boolean flag = false;
       
       
        try {
            String sql = "Delete from tblUser where TouristID =? ";
            pt = con.prepareStatement(sql);           
            pt.setString(1,id);
            pt.executeUpdate();
             flag = true;
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
       return flag;
    }
     int count = 3;
     
     public  ResultSet verifyLogin(String user, String pass){
        
        ResultSet rs = null;
        
        try {
            st = con.createStatement();
            String login = "SELECT * FROM tblUser WHERE Username='"+user+"' AND Password='"+pass+"'";
            rs = st.executeQuery(login);
            
           
            //if(count != 0){
            if(rs.next()){
                 JOptionPane.showMessageDialog(null, "Access Approved!");
                 MenuFrame mf = new MenuFrame();
                 mf.setVisible(true);
                  
            }else{
                //JOptionPane.showMessageDialog(null, "Wrong Info \t Attempts Left: " +count);
                     //--count;
                     JOptionPane.showMessageDialog(null, "Access Denied!");
                      
                     FrameLogin sad = new FrameLogin();
                     sad.setVisible(true);
            }
            /*else{
                JOptionPane.showMessageDialog(null, "Wrong Info \t Attempts Left: " +count);
                     --count;  
            }*/
           
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        return rs;
     }
    
   
       
    public void disconnectDB(){
        if(con != null)
            try {
                con.close();
                System.out.println("Disconnected");
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
}
