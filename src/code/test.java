package code;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.awt.*;
import java.awt.event.*;
import java.awt.CardLayout;

public class test extends JFrame implements ActionListener{

	private static JTextField textField;
	private static JPasswordField textFieldPass;
	private static JButton btninscription;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					test window = new test();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * Create the application.
	 */
	public test() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		this.setBounds(100, 100, 550, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new GridLayout(3, 1, 0, 0));
		
		textField = new JTextField("Pseudo");
		textField.setFont(new Font("Segoe UI Semibold", Font.BOLD, 17));
		this.getContentPane().add(textField, BorderLayout.CENTER);
		textField.setColumns(10);
		textFieldPass = new JPasswordField("Mot de passe");
		textFieldPass.setFont(new Font("Segoe UI Semibold", Font.BOLD, 17));
		this.getContentPane().add(textFieldPass, BorderLayout.CENTER);
		textFieldPass.setColumns(1);
		
		
		btninscription = new JButton("Inscription");
		this.getContentPane().add(btninscription, BorderLayout.SOUTH);
		btninscription.addActionListener(this);
	}
	
	public static void sauverEnBase(){
		 String nom = textField.getText(); char[] motdepasse= textFieldPass.getPassword();
		String url = "jdbc:mysql://localhost/ergo";
		String login="root";
		String password="";
		Connection cn = null;
		Statement st = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			cn = (Connection) DriverManager.getConnection(url,login,password);
			st = (Statement) cn.createStatement();
			String sql = "INSERT INTO user (nom, prenom, pseudo, motdepasse, mail, poids, taille, naissance) VALUES ('nom','prenom','" +nom+ "','" +motdepasse+ "','mail','1','1','2016-01-01')" ;
			st.executeUpdate(sql);

		}catch(SQLException e){
			
			e.printStackTrace();
		}catch(ClassNotFoundException e ){
			e.printStackTrace();
		}finally{
			try{
				cn.close();
				st.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == btninscription){
			sauverEnBase();
		}
		
	}

}
