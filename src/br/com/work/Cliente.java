package br.com.work;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import javax.swing.*;


public class Cliente extends JFrame implements ActionListener, KeyListener {
   
	private static final long serialVersionUID = 1L;
    private JPanel pnlContent;
    private JButton btnEnviar;
    private JButton btnSair;
    private JLabel lblHistorico;
    private JLabel lblMensagem;
    private JTextArea texto;
    private JTextField txtMsg;
    private JTextField txtIp;
    private JTextField txtPorta;
    private JTextField txtNome;
    private Socket socket;
    private OutputStream out;
    private Writer ouw;
    private BufferedWriter bfw;

    
    public Cliente() throws IOException{
    	
    	JLabel lblMensagem = new JLabel("Verificar!");
    	
    	txtIp = new JTextField("127.0.0.1");
    	txtPorta = new JTextField("12345");
    	txtNome = new JTextField("Nome");
    	
    	Object[] texts = {lblMensagem, txtIp, txtPorta, txtNome};
    	JOptionPane.showMessageDialog(null,texts);
    	
    	pnlContent = new JPanel();
    	texto = new JTextArea(100,50);
    	texto.setEditable(false);
    	texto.setBackground(new Color(240,240,240));
    	txtMsg = new JTextField(20);
    	
	
    }
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	


}
