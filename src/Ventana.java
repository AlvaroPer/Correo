import javax.mail.internet.AddressException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import javax.mail.PasswordAuthentication;

public class Ventana extends JFrame implements ActionListener {


    JLabel etiquetaPara, etiquetaCC, etiquetaCO, etiquetaArchivo, etiquetaPath, etiquetaTexto, etiquetaAsunto;
    JTextField txtPara, txtCO, txtCC, txtAsunto;
    JTextArea txtTexto;
    JButton btnEnviar, btnBorrar, btnArchivo;
    JPanel panelBotones;
    Container container;

    public void initGUI() {

        instancias();
        configurarContainer();
        pack();
        this.setSize(new Dimension(500,500));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        acciones();
        setVisible(true);

    }

    private void acciones() {
        btnBorrar.addActionListener(this);
        btnEnviar.addActionListener(this);
    }

    public void configurarConstraint(int posX, int posY,int fill, int anchor
            ,double pesX, double pesY, int tamX, int tamY, JComponent component){
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = posX;
        constraints.gridy = posY;
        constraints.fill = fill ;
        constraints.anchor= anchor;
        constraints.weightx = pesX;
        constraints.weighty = pesY;
        constraints.gridwidth= tamX;
        constraints.gridheight= tamY;
        container.add(component,constraints);

    }

    private void configurarContainer() {
        // pongo layout que quiero en el panel o contenedor que me interesa
        container.setLayout(new GridBagLayout());

        // Fila 0 Columna 0
        configurarConstraint(0,0,GridBagConstraints.HORIZONTAL,GridBagConstraints.CENTER,0,0,1,1,etiquetaPara);
        // Fila 0 Columna 1
        configurarConstraint(1,0,GridBagConstraints.HORIZONTAL,GridBagConstraints.CENTER,0.2,0,3,1,txtPara);
        // Fila 1 Columna 0
        configurarConstraint(0,1,GridBagConstraints.HORIZONTAL,GridBagConstraints.CENTER,0,0, 1,1,etiquetaCC);
        // Fila 1 Columna 1
        configurarConstraint(1,1,GridBagConstraints.HORIZONTAL,GridBagConstraints.CENTER,0.2,0,1,1,txtCC);
        // Fila 1 Columno 2
        configurarConstraint(2,1,GridBagConstraints.HORIZONTAL,GridBagConstraints.CENTER,0.2,0,1,1,etiquetaCO);
        // Fila 1 Columna 3
        configurarConstraint(3,1,GridBagConstraints.HORIZONTAL,GridBagConstraints.CENTER,0.8,0,1,1,txtCO);
        // Fila 2 Columna 0
        configurarConstraint(0,2,GridBagConstraints.HORIZONTAL,GridBagConstraints.CENTER,0,0, 1,1,etiquetaArchivo);
        // Fila 2 Columna 1
        configurarConstraint(1,2,GridBagConstraints.HORIZONTAL,GridBagConstraints.CENTER,0.2,0, 1,1,btnArchivo);
        // Fila 2 Columna 2
        configurarConstraint(2,2,GridBagConstraints.HORIZONTAL,GridBagConstraints.CENTER,0.2,0, 1,1,etiquetaPath);
        // Fila 0 Columna 3
        configurarConstraint(0,3,GridBagConstraints.HORIZONTAL,GridBagConstraints.CENTER,0,0, 1,1,etiquetaAsunto);
        // Fila 1 Columna 3
        configurarConstraint(1,3,GridBagConstraints.HORIZONTAL,GridBagConstraints.CENTER,0.2,0,3,1, txtAsunto);
        // Fila 0 Columna 4
        configurarConstraint(0,4,GridBagConstraints.HORIZONTAL,GridBagConstraints.NORTH,0,0, 1,1,etiquetaTexto);
        // Fila 1 Columna 4
        configurarConstraint(1,4,GridBagConstraints.BOTH,GridBagConstraints.CENTER,0.2,1, 3,1,new JScrollPane(txtTexto));
        configurarConstraint(0,5, GridBagConstraints.BOTH,GridBagConstraints.CENTER,1,0,4,1,panelBotones);

    }

    private void instancias() {
        etiquetaAsunto = new JLabel("Asunto");
        etiquetaPara = new JLabel("Para");
        etiquetaCC = new JLabel("CC");
        etiquetaCO = new JLabel("CCO");
        etiquetaArchivo = new JLabel("Archivo");
        etiquetaPath = new JLabel("");
        etiquetaTexto = new JLabel("Text");
        txtAsunto = new JTextField();
        txtCC = new JTextField();
        txtCO = new JTextField();
        txtPara = new JTextField();
        txtTexto = new JTextArea();
        btnBorrar = new JButton("Borrar");
        btnEnviar = new JButton("Enviar");
        btnArchivo = new JButton("Adjuntar...");
        container = getContentPane();
        panelBotones = new JPanel();
        //panelBotones.setLayout(new GridLayout(1,2,10,10));
        panelBotones.add(btnEnviar);
        panelBotones.add(btnBorrar);

    }

    public void vaciarCampos(){
        txtTexto.setText("");
        txtPara.setText("");
        txtCO.setText("");
        txtCC.setText("");
        txtAsunto.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==btnBorrar){
            vaciarCampos();
        } else if (e.getSource() == btnEnviar){

        }
    }

    properties = new Properties();
    properties.put("mail.smtp.host", "smtp.gmail.com");
    properties.put("mail.smtp.starttls.enable", "true");
    properties.put("mail.smtp.port", "587");
    properties.put("mail.smtp.auth", "true");
    properties.put("mail.user", "alvaroperez191@gmail.com");
    properties.put("mail.password", "password");

    Session session = Session.getInstance(properties, null);

    MimeMessage mimeMessage = new MimeMessage(session);

    try {
        mimeMessage.setText(texto.getText());
        mimeMessage.setFrom(new InternetAddress("alvaroperez191@gmail.com"));
        InternetAddress[]direcciones= new InternetAddress[]{new InternetAddress(txtPara.getText().toString())};
        mimeMessage.setRecipients(Message.RecipientType.TO,direcciones);
        mimeMessage.setSubject(txtAsunto.getText().toString())
        }

    catch (MessagingException e1) {
        e1.printStackTrace();
        }

    Transport transport = session.getTransport("smtp");
    transport.connect(properties.getProperty("mail.user"), properties.getProperty("mail.password"));
    transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
    transport.close();

}