
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*; 
public class Login extends JFrame implements ActionListener {
    JButton signup,login,clear;
    JTextField cardtxt;
    JPasswordField pintxt;

    Login(){
        setLayout(null);
        setTitle("ATM");
        setSize(800,600);
        setLocation(600,150);
        getContentPane().setBackground(Color.WHITE);
        ImageIcon icon1=new ImageIcon(ClassLoader.getSystemResource("icons/logo.jpg"));
        Image image1=icon1.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT);
        icon1=new ImageIcon(image1);
        JLabel label1=new JLabel(icon1);
        label1.setBounds(100,65,100,100);
        add(label1);
        JLabel text1=new JLabel("WELCOME TO ATM");
        text1.setBounds(230,100,400,50);
        text1.setFont(new Font("OSWARD",Font.BOLD,40));
        add(text1);
        JLabel cardno=new JLabel("Card no  :");
        cardno.setBounds(150,200,200,30);
        cardno.setFont(new Font("Raleway",Font.BOLD,25));
        add(cardno);
        cardtxt=new JTextField();
        cardtxt.setBounds(300, 200, 250, 30);
        cardtxt.setFont(new Font("Arial",Font.BOLD,25));
        add(cardtxt);

        JLabel pin=new JLabel("PIN         :");
        pin.setBounds(150,270,200,30);
        pin.setFont(new Font("Raleway",Font.BOLD,25));
        add(pin);
        pintxt=new JPasswordField();
        pintxt.setBounds(300, 270, 250, 30);
        pintxt.setFont(new Font("Arial",Font.BOLD,25));
        add(pintxt);
        login=new JButton( "LOGIN");
        login.setBounds(300, 330, 100, 30);
        login.setBackground(Color.BLACK);
        login.setForeground(Color.WHITE);
        login.addActionListener(this);
        add(login);
        clear=new JButton( "CLEAR");
        clear.setBounds(448, 330, 100, 30);
        clear.setBackground(Color.BLACK);
        clear.setForeground(Color.WHITE);
        clear.addActionListener(this);
        add(clear);
        signup=new JButton( "SIGN UP");
        signup.setBounds(300, 380, 250, 30);
        signup.setBackground(Color.BLACK);
        signup.setForeground(Color.WHITE);
        signup.addActionListener(this);
        add(signup);
        
        setVisible(true);
        
    }
    public void actionPerformed(ActionEvent a){
        if(a.getSource()==login){
            String card=cardtxt.getText();
            String pass=pintxt.getText();
            System.out.println(card);
            System.out.println(pass);
            String Q1="select pin from account_info where acc_no='"+card+"' and pin='"+pass+"'";
            try{
                 Cnction connection=new Cnction();
                 
                ResultSet rs=connection.s.executeQuery(Q1);
                if(rs.next()){
                    setVisible(false);
                    new Atm(card);
                }
                else {
                    JOptionPane.showMessageDialog(null, "Invalid card number or pin");
                    return;
                }
            }
            catch(Exception e){
                System.out.println(e);

            }

        }
        else if(a.getSource()==signup){
            setVisible(false);
            new SignUpOne();
        }
        else if(a.getSource()==clear){
            cardtxt.setText("");
            pintxt.setText("");
        }
    }

    public static void main(String[] args) throws Exception {
       new Login();
    }
}
