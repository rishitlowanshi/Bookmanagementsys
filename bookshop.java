Connection con;
PreparedStatement pst;
ResultSet rs;
 
public void Connect()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/javacrud", "root","");
        }
        catch (ClassNotFoundException ex)
        {
          ex.printStackTrace();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
 
    }
    
//AddRecords

public void actionPerformed(ActionEvent e)
{
String bname,edition,price;
bname = txtbname.getText();
edition = txtedition.getText();
price = txtprice.getText();
try {
pst = con.prepareStatement("insert into book(name,edition,price)values(?,?,?)");
pst.setString(1, bname);
pst.setString(2, edition);
pst.setString(3, price);
pst.executeUpdate();
JOptionPane.showMessageDialog(null, "Record Addedddd!!!!!");
table_load();
          
txtbname.setText("");
txtedition.setText("");
txtprice.setText("");
txtbname.requestFocus();
   }
 
catch (SQLException e1)
        {
e1.printStackTrace();
}


//View Records


  public void table_load()
    {
     try
     {
    pst = con.prepareStatement("select * from book");
    rs = pst.executeQuery();
    table.setModel(DbUtils.resultSetToTableModel(rs));
}
     catch (SQLException e)
     {
     e.printStackTrace();
  }
    }
    
    
    
//Form Constructor

public JavaCrud() {
initialize();
Connect();
table_load();
}

//Search Record

public void keyReleased(KeyEvent e) {
try {
            String id = txtbid.getText();
 
                pst = con.prepareStatement("select name,edition,price from book where id = ?");
                pst.setString(1, id);
                ResultSet rs = pst.executeQuery();
 
            if(rs.next()==true)
            { 
                String name = rs.getString(1);
                String edition = rs.getString(2);
                String price = rs.getString(3);
                
                txtbname.setText(name);
                txtedition.setText(edition);
                txtprice.setText(price);
            }  
            else
            {
             txtbname.setText("");
             txtedition.setText("");
                txtprice.setText("");
                
            }
        }
catch (SQLException ex) {
          
        }
}

//Delete

String bid;
bid  = txtbid.getText();
try {
pst = con.prepareStatement("delete from book where id =?");
            pst.setString(1, bid);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Record Delete!!!!!");
            table_load();
          
            txtbname.setText("");
            txtedition.setText("");
            txtprice.setText("");
            txtbname.requestFocus();
}
 
            catch (SQLException e1) {
e1.printStackTrace();
}
