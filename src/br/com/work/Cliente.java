package br.com.work;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


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
    	
    	
    	lblMensagem = new JLabel("Mensagem");
    	btnEnviar = new JButton("Enviar");
    	btnEnviar.setToolTipText("Enviar Mensagem");
        btnSair = new JButton("Sair");
        btnSair.setToolTipText("Sair do Chat");
        
        btnEnviar.addActionListener(this);
        btnSair.addActionListener(this);
        btnEnviar.addKeyListener(this);
        txtMsg.addKeyListener(this);
        
        JScrollPane scroll = new JScrollPane(texto);
        texto.setLineWrap(true);
        
        pnlContent.add(lblHistorico);
        pnlContent.add(scroll);
        pnlContent.add(lblMensagem);
        pnlContent.add(txtMsg);
        pnlContent.add(btnSair);
        pnlContent.add(btnEnviar);
        pnlContent.setBackground(Color.LIGHT_GRAY);
        
        texto.setBorder(BorderFactory.createEtchedBorder(Color.BLUE, Color.BLUE));
        txtMsg.setBorder(BorderFactory.createEtchedBorder(Color.BLUE, Color.BLUE));
        
        setTitle(txtNome.getText());
        setContentPane(pnlContent);
        setLocationRelativeTo(null);
        setResizable(false);
        setSize(500,300);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
	
    
    public void conectar() throws IOException{
    	
    	 socket = new Socket(txtIp.getText(),Integer.parseInt(txtPorta.getText()));
    	  out = socket.getOutputStream();
          ouw = new OutputStreamWriter(out);
          bfw = new BufferedWriter(ouw);
          bfw.write(txtNome.getText()+"\r\n");
          bfw.flush();
      }
 
    public void enviarMensagem(String msg) throws IOException{
    	
    	if(msg.equals("Sair")) {
    		bfw.write("Desconectado\r\n");
    		texto.append("Desconectando \r\n ");
    	}else {
    		bfw.write(msg + "\r\n");
    		texto.append(txtNome.getText() + "diz: " + txtMsg.getText()+ "\r\n");
    	}
    	bfw.flush();
    	txtMsg.setText("");
    	
    	
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
