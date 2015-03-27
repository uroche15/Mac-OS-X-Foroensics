package Programs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;


public class MainFrame extends javax.swing.JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private JButton docReaderButton;
    private JButton extensionSearchButton;
    private JButton fileOpenerButton;
    private JButton pdfReaderButton;
    private JButton stringSearchButton;
    private JButton textReaderButton;
    private JButton plistreader;
    
    public MainFrame() {
        initializeGUIComponents();
        docReaderButton.addActionListener(this);
        textReaderButton.addActionListener(this);
        extensionSearchButton.addActionListener(this);
        fileOpenerButton.addActionListener(this);
        pdfReaderButton.addActionListener(this);
        stringSearchButton.addActionListener(this);
        plistreader.addActionListener(this);
        
        setSize(250, 400);
        setLocationRelativeTo(null);
        setTitle("Main Page");
    }

    
    private void initializeGUIComponents() {

        docReaderButton = new javax.swing.JButton();
        textReaderButton = new javax.swing.JButton();
        pdfReaderButton = new javax.swing.JButton();
        extensionSearchButton = new javax.swing.JButton();
        fileOpenerButton = new javax.swing.JButton();
        stringSearchButton = new javax.swing.JButton();
        plistreader = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridLayout(6, 1, 10, 10));

        docReaderButton.setText("Doc Reader");
        getContentPane().add(docReaderButton);

        textReaderButton.setText("Text Reader");
        getContentPane().add(textReaderButton);

        pdfReaderButton.setText("PDF Reader");
        getContentPane().add(pdfReaderButton);

        extensionSearchButton.setText("File Extension Search");
        getContentPane().add(extensionSearchButton);

        fileOpenerButton.setText("File Opener");
        getContentPane().add(fileOpenerButton);

        stringSearchButton.setText("String Search");
        getContentPane().add(stringSearchButton);
        
        plistreader.setText("Plist Reader");
        getContentPane().add(plistreader);

        pack();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == docReaderButton) {
            new DocReader().setVisible(true);
        } else if (e.getSource() == textReaderButton) {
            new TextReader().setVisible(true);
        } else if (e.getSource() == pdfReaderButton) {
            new PDFReader().setVisible(true);
        } else if (e.getSource() == fileOpenerButton) {
            new FileOpener().setVisible(true);
        } else if (e.getSource() == stringSearchButton) {
            new StringSearch().setVisible(true);
        } else if (e.getSource() == extensionSearchButton) {
            new ExtensionSearch().setVisible(true);
        } else if (e.getSource() == plistreader) {
            new Plist().setVisible(true);
        }
    }
}
