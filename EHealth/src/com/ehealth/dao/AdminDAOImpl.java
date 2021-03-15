package com.ehealth.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class AdminDAOImpl implements AdminDAO{
    Connection conn;
    PreparedStatement ps;
    @Override
    public boolean checkAdmin(String username, String password)
    {
        boolean check = false;
        try{
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement("SELECT * FROM admins WHERE username = ? and password = ?");
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                check = true;
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return check;
    }
    
}
