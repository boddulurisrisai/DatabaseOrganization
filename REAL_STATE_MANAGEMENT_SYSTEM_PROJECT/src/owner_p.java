
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class owner_p {
    
    private int id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String address;
    
    public int getId()
    {
        return this.id;
    }
    
    public void setId(int ID)
    {
        this.id = ID;
    }
    
    public String getFname()
    {
        return this.firstName;
    }
    
    public void setFname(String FNAME)
    {
       this.firstName = FNAME; 
    }
    
    public String getLname()
    {
        return this.lastName;
    }
    
    public void setLname(String LNAME)
    {
       this.lastName = LNAME; 
    }
    
    public String getPhone()
    {
        return this.phone;
    }
    
    public void setPhone(String PHONE)
    {
       this.phone = PHONE; 
    }
    
    public String getEmail()
    {
        return this.email;
    }
    
    public void setEmail(String EMAIL)
    {
       this.email = EMAIL; 
    }
    
    public String getAddress()
    {
        return this.address;
    }
    
    public void setAddress(String ADDRESS)
    {
       this.address = ADDRESS; 
    }
    
    
    public owner_p(){}
    
    public owner_p(int ID, String FNAME, String LNAME, String PHONE, String EMAIL, String ADDRESS)
    {
        this.id = ID;
        this.firstName = FNAME;
        this.lastName = LNAME;
        this.phone = PHONE;
        this.email = EMAIL;
        this.address = ADDRESS;
    }
    
    
     public boolean addNewOwner(owner_p owner)
    {
        PreparedStatement ps;
        
        String addQuery = "INSERT INTO `property_owner`(`fname`, `lname`, `phone`, `email`, `address`) VALUES (?,?,?,?,?)";
        
        try {
            ps = conn.getTheConnection().prepareStatement(addQuery);
            ps.setString(1, owner.getFname());
            ps.setString(2, owner.getLname());
            ps.setString(3, owner.getPhone());
            ps.setString(4, owner.getEmail());
            ps.setString(5, owner.getAddress());
            
            return (ps.executeUpdate() > 0);
            
        } catch (SQLException ex) {
            Logger.getLogger(owner_p.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
    
     public boolean editOwnerData(owner_p owner)
    {
        PreparedStatement ps;
        
        String editQuery = "UPDATE `property_owner` SET `fname`=?,`lname`=?,`phone`=?,`email`=?,`address`=? WHERE `id`=?";
        
        try {
            ps = conn.getTheConnection().prepareStatement(editQuery);
            ps.setString(1, owner.getFname());
            ps.setString(2, owner.getLname());
            ps.setString(3, owner.getPhone());
            ps.setString(4, owner.getEmail());
            ps.setString(5, owner.getAddress());
            ps.setInt(6, owner.getId());
            
            return (ps.executeUpdate() > 0);
            
        } catch (SQLException ex) {
            Logger.getLogger(owner_p.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    
     public boolean deleteOwner(int ownerId)
    {
        PreparedStatement ps;
        
        String deleteQuery = "DELETE FROM `property_owner` WHERE `id`=?";
        
        try {
            ps = conn.getTheConnection().prepareStatement(deleteQuery);
            
            ps.setInt(1, ownerId);
            
            return (ps.executeUpdate() > 0);
            
        } catch (SQLException ex) {
            Logger.getLogger(owner_p.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } 
    }
    
    
     public ArrayList<owner_p> ownersList()
    {
        ArrayList<owner_p> list = new ArrayList<>();
        
        Statement st;
        ResultSet rs;
        
        String selectQuery = "SELECT * FROM `property_owner`";
        
        try {
            
            st = conn.getTheConnection().createStatement();
            rs = st.executeQuery(selectQuery);
            
            owner_p owner;
            
            while (rs.next()) {
                
                owner = new owner_p(rs.getInt(1),
                                    rs.getString(2), 
                                    rs.getString(3),
                                    rs.getString(4), 
                                    rs.getString(5), 
                                    rs.getString(6));
                
                list.add(owner);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(owner_p.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }
    
}
