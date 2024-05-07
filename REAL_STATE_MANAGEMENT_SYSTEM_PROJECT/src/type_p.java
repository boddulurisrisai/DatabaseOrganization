
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author admin
 */
public class type_p {

    private int id;
    private String name;
    private String description;
    
    
    // create the geters and seters
    public Integer getId()
    {
        return id;
    }
    
    public void setId(Integer ID)
    {
        this.id = ID;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String NAME)
    {
        this.name = NAME;
    }
    
    public String getDescription()
    {
        return this.description;
    }
    
    public void setDescription(String DESCRIPTION)
    {
        this.description = DESCRIPTION;
    }
    
    
    // create the class constructors
    public type_p(){}
    
    public type_p(Integer ID,String NAME, String DESCRIPTION)
    {
        this.id = ID;
        this.name = NAME;
        this.description = DESCRIPTION;
    }
    
    
    // create a function to insert - edit - remove type
    public boolean execTypeQuery(String queryType, type_p type)
    {
        
        PreparedStatement ps;
        
        // add a new type
        if(queryType.equals("add"))
        {
            try {
                // query -> INSERT INTO `property_type`(`name`, `description`) VALUES (?,?)
                ps = conn.getTheConnection().prepareStatement("INSERT INTO `property_type`(`name`, `description`) VALUES (?,?)");
                ps.setString(1, type.getName());
                ps.setString(2, type.getDescription());
                
                return (ps.executeUpdate() > 0);
                
            } catch (SQLException ex) {
                Logger.getLogger(type_p.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
        
        // add a new type
        else if(queryType.equals("edit"))
        {
            try {
                // query -> UPDATE `property_type` SET `name`=?,`description`=? WHERE `id` = ?
                ps = conn.getTheConnection().prepareStatement("UPDATE `property_type` SET `name`=?,`description`=? WHERE `id` = ?");
                ps.setString(1, type.getName());
                ps.setString(2, type.getDescription());
                ps.setInt(3, type.getId());
                
                return (ps.executeUpdate() > 0);
                
            } catch (SQLException ex) {
                Logger.getLogger(type_p.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
        
        // add a new type
        else if(queryType.equals("remove"))
        {
            try {
                // query -> DELETE FROM `property_type` WHERE `id`= ?
                ps = conn.getTheConnection().prepareStatement("DELETE FROM `property_type` WHERE `id`= ?");
                ps.setInt(1, type.getId());
                
                return (ps.executeUpdate() > 0);
                
            } catch (SQLException ex) {
                Logger.getLogger(type_p.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
 
        else{
            JOptionPane.showMessageDialog(null, "Enter The Correct Query( add,edit,remove )", "Invalid Operation", 2);
            return false;
        }
        
    }
    
    
    // create a function to return a list of all types in a HashMap
    // string is the key
    // integer is the value
    public HashMap<String,Integer> getTypesMap()
    {
        HashMap<String, Integer> map = new HashMap<>();
        
        Statement st;
        ResultSet rs;
        
        try {
            
            st = conn.getTheConnection().createStatement();
            rs = st.executeQuery("SELECT * FROM `property_type`");
            
            type_p type;
            
            while(rs.next())
            {
                type = new type_p(rs.getInt(1), rs.getString(2), rs.getString(3));
                
                map.put(type.getName(), type.getId());
            }
                    
        } catch (SQLException ex) {
            Logger.getLogger(type_p.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return map;
    }
    
    
    // create a function to get a type data by id
    // we can use getTypesMap() function to get the id and name
    // but we want the description to
    public type_p getTypeById(Integer id)
    {
        PreparedStatement ps;
        ResultSet rs;
        
        type_p type = new type_p();
        
        try {
            
                ps = conn.getTheConnection().prepareStatement("SELECT * FROM `property_type` WHERE `id`=?");
                ps.setInt(1, id);
                rs = ps.executeQuery();
                
                if(rs.next())
                {
                    type.setId(id);
                    type.setName(rs.getString(2));
                    type.setDescription(rs.getString(3));
                }
            
            } 
            catch (SQLException ex) {
            Logger.getLogger(type_p.class.getName()).log(Level.SEVERE, null, ex);
        }
          return type;
    }
    
}
