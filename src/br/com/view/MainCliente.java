package br.com.view;

import java.awt.Color;
import java.awt.Font;
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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ImageIcon;

public class MainCliente extends JFrame implements ActionListener, KeyListener {
	   
		private static final long serialVersionUID = 1L;
	    private JPanel pnlContent;
	    private JButton btnEnviar;
	    private JButton btnVideo;
	    private JButton btnSair;
	    private JLabel lblHistorico;

	   // private JLabel lblMensagem;
	    private JLabel lblIp;
	    private JLabel lblPorta;
	    private JLabel lblNome;
	    private JTextArea texto;
	    private JTextField txtMsg;
	    private JTextField txtIp;
	    private JTextField txtPorta;
	    private JTextField txtNome;
	    private Socket socket;
	    private OutputStream out;
	    private Writer ouw;
	    private BufferedWriter bfw;

	    
	    public MainCliente() throws IOException{
	    	 
	    	//tela de login
	    	JLabel lblMensage = new JLabel("Bem vindo ao Call The Nature! \r\n");
	    	JLabel lblInfo = new JLabel("Por favor confirme as informações abaixo.\r\n");
	    	lblMensage.setFont(new Font("Dialog",Font.ITALIC, 20));
	    	lblMensage.setForeground(Color.BLACK);
	    	lblInfo.setForeground(Color.darkGray);
	    	
	    	lblIp = new JLabel("Digite o IP de sua máquina:");
	    	lblIp.setForeground(Color.darkGray);
	    	txtIp = new JTextField("127.0.0.1 - IP padrão");
	    	lblPorta = new JLabel("Digite a porta do Servidor:");
	    	lblPorta.setForeground(Color.darkGray);
	    	txtPorta = new JTextField("Sua porta aqui ");
	    	lblNome = new JLabel("Digite seu nome:");
	    	lblNome.setForeground(Color.darkGray);
	    	txtNome = new JTextField("Seu nome aqui");
	    	
	    	Object[] texts = {lblMensage,lblInfo,lblIp,txtIp,lblPorta,txtPorta,lblNome, txtNome};
	    	JOptionPane.showMessageDialog(null,texts,"Call The Nature!",JOptionPane.INFORMATION_MESSAGE,new ImageIcon("Image/trees.png"));
	    	
	    	
	    	
	    	//Tela das mensagens
	    	pnlContent = new JPanel();
	    	texto = new JTextArea(20,70);
	    	texto.setEditable(false);
	    	texto.setBackground(new Color(240,249,240));
	    	txtMsg = new JTextField(56);
	    	
	    	
	    	
	    	 lblHistorico = new JLabel("Mensagens");
	    	 lblHistorico.setFont(new Font("Dialog",Font.BOLD, 18));
	    	 //lblMensagem = new JLabel("Digite aqui: ");
	         btnEnviar = new JButton("Enviar");
	         btnEnviar.setToolTipText("Clique aqui para enviar Mensagem");
	         btnVideo = new JButton("Video Chamada");
	         btnVideo.setToolTipText("Clique aqui para iniciar uma chamada de Video");
	         btnSair = new JButton("Sair");
	         btnSair.setToolTipText("Clique aqui para sair do Chat");
	         btnEnviar.addActionListener(this);
	         btnSair.addActionListener(this);
	         btnEnviar.addKeyListener(this);
	         txtMsg.addKeyListener(this);
	         
	      
	         
	         
	         
	         JScrollPane scroll = new JScrollPane(texto);
	         texto.setLineWrap(true);
	         pnlContent.add(lblHistorico);
	         pnlContent.add(scroll);
	         //pnlContent.add(lblMensagem);
	         pnlContent.add(txtMsg);
	         pnlContent.add(btnVideo);
	         pnlContent.add(btnSair);
	         pnlContent.add(btnEnviar);
	         pnlContent.setBackground(new Color(204,227,211));
	        // texto.setBorder(BorderFactory.createEtchedBorder(Color.darkGray, Color.darkGray));
	         //txtMsg.setBorder(BorderFactory.createEtchedBorder(Color.darkGray, Color.darkGray));
	         
	         setTitle(txtNome.getText());
	         setContentPane(pnlContent);
	         setLocationRelativeTo(null);
	         setResizable(false);
	         setSize(900,500);
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

	    /***
	     * Método usado para enviar mensagem para o server socket
	     * @param msg do tipo String
	     * @throws IOException retorna IO Exception caso dê algum erro.
	     */
	    public void enviarMensagem(String msg) throws IOException{

	        if(msg.equals("Sair")){
	            bfw.write("Desconectado \r\n");
	            texto.append("Desconectado \r\n");
	        }else{
	            bfw.write(msg+"\r\n");
	            texto.append( txtNome.getText() + ": " + txtMsg.getText()+"\r\n");
	        }
	        bfw.flush();
	        txtMsg.setText("");
	    }

	    /**
	     * Método usado para receber mensagem do servidor
	     * @throws IOException retorna IO Exception caso dê algum erro.
	     */
	    public void escutar() throws IOException{

	        InputStream in = socket.getInputStream();
	        InputStreamReader inr = new InputStreamReader(in);
	        BufferedReader bfr = new BufferedReader(inr);
	        String msg = "";

	        while(!"Sair".equalsIgnoreCase(msg))

	            if(bfr.ready()){
	                msg = bfr.readLine();
	                if(msg.equals("Sair"))
	                    texto.append("Servidor caiu! \r\n");
	                else
	                    texto.append(msg+"\r\n");
	            }
	    }

	    /***
	     * Método usado quando o usuário clica em sair
	     * @throws IOException retorna IO Exception caso dê algum erro.
	     */
	    public void sair() throws IOException{

	        enviarMensagem("Sair");
	        bfw.close();
	        ouw.close();
	        out.close();
	        socket.close();
	    }

	    @Override
	    public void actionPerformed(ActionEvent e) {

	        try {
	            if(e.getActionCommand().equals(btnEnviar.getActionCommand()))
	                enviarMensagem(txtMsg.getText());
	            else
	            if(e.getActionCommand().equals(btnSair.getActionCommand()))
	                sair();
	        } catch (IOException e1) {
	            // TODO Auto-generated catch block
	            e1.printStackTrace();
	        }
	    }

	    @Override
	    public void keyPressed(KeyEvent e) {

	        if(e.getKeyCode() == KeyEvent.VK_ENTER){
	            try {
	                enviarMensagem(txtMsg.getText());
	            } catch (IOException e1) {
	                // TODO Auto-generated catch block
	                e1.printStackTrace();
	            }
	        }
	    }

	    @Override
	    public void keyReleased(KeyEvent arg0) {
	        // TODO Auto-generated method stub
	    }

	    @Override
	    public void keyTyped(KeyEvent arg0) {
	        // TODO Auto-generated method stub
	    }

	    public static void main(String []args) throws IOException{

	        MainCliente app = new MainCliente();
	        app.conectar();
	        app.escutar();
	    }
	}