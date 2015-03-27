package Programs;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;



public class PDFReader extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	File pdfFile = null;
    
    private JButton browsePdfButton;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JScrollPane jScrollPane1;
    private JTextArea pdfContentTextArea;
    private JTextField pdfPathTextField;
    
    public PDFReader() {
        initializeGUIComponents();
        browsePdfButton.addActionListener(this);
        setLocationRelativeTo(null);
        setTitle("PDF Reader");
    }

                           
    private void initializeGUIComponents() {
        GridBagConstraints gridBagConstraints;

        jLabel2 = new JLabel();
        pdfPathTextField = new JTextField();
        browsePdfButton = new JButton();
        jLabel3 = new JLabel();
        jScrollPane1 = new JScrollPane();
        pdfContentTextArea = new JTextArea();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new GridBagLayout());

        jLabel2.setText("File Path:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(15, 39, 0, 0);
        getContentPane().add(jLabel2, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 217;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(12, 22, 0, 0);
        getContentPane().add(pdfPathTextField, gridBagConstraints);

        browsePdfButton.setText("Browse");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(11, 18, 0, 0);
        getContentPane().add(browsePdfButton, gridBagConstraints);

        jLabel3.setText("File Content:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 9;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(75, 39, 0, 0);
        getContentPane().add(jLabel3, gridBagConstraints);

        pdfContentTextArea.setColumns(20);
        pdfContentTextArea.setRows(5);
        jScrollPane1.setViewportView(pdfContentTextArea);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 552;
        gridBagConstraints.ipady = 267;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(18, 22, 11, 10);
        getContentPane().add(jScrollPane1, gridBagConstraints);

        pack();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == browsePdfButton) {
            FileDialog fileDialog = new FileDialog(new JFrame());
            fileDialog.setFile("*.pdf");
            fileDialog.setMode(FileDialog.LOAD);
            fileDialog.setTitle("PDF Explorer");
            //fileDialog.setFile("*.*");
            fileDialog.setEnabled(true);
            fileDialog.toFront();
            fileDialog.show();
            String pdfFilePath = fileDialog.getDirectory() + fileDialog.getFile();
            pdfContentTextArea.setText("");
            if (fileDialog.getFile().endsWith(".pdf")) {
                pdfPathTextField.setText(pdfFilePath);
                try {
                    pdfFile = new File(pdfFilePath);
                    
                    pdfContentTextArea.setText(readFromPDF(pdfFile));
                } catch (Exception exep) {
                    System.out.println(exep.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(this, "Not a PDF file");
            }
        }
    }
    
    public String readFromPDF(File file) {
        String content = "";
        try {
            PdfReader reader = new PdfReader(file.getAbsolutePath());
            PdfReaderContentParser parser = new PdfReaderContentParser(reader);
            TextExtractionStrategy strategy;
            for (int i = 1; i <= reader.getNumberOfPages(); i++) {
                strategy = parser.processContent(i, new SimpleTextExtractionStrategy());
                content += strategy.getResultantText();
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }   
        return content;
    }
}