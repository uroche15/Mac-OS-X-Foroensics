package Programs;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import javax.swing.*;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

public class DocReader extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	File docFile = null;
    WordExtractor docExtractor = null;
    WordExtractor exprExtractor = null;
    
    private JButton browseDocButton;
    private JTextArea docContentTextArea;
    private JTextField docPathTextField;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JScrollPane jScrollPane1;

    public DocReader() {
        initializeGUIComponents();
        browseDocButton.addActionListener(this);
        setLocationRelativeTo(null);
        setTitle("Doc Reader");
    }

    
    private void initializeGUIComponents() {
        GridBagConstraints gridBagConstraints;

        jLabel2 = new JLabel();
        docPathTextField = new JTextField();
        browseDocButton = new JButton();
        jLabel3 = new JLabel();
        jScrollPane1 = new JScrollPane();
        docContentTextArea = new JTextArea();

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
        getContentPane().add(docPathTextField, gridBagConstraints);

        browseDocButton.setText("Browse");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(11, 18, 0, 0);
        getContentPane().add(browseDocButton, gridBagConstraints);

        jLabel3.setText("File Content:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 9;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(75, 39, 0, 0);
        getContentPane().add(jLabel3, gridBagConstraints);

        docContentTextArea.setColumns(20);
        docContentTextArea.setRows(5);
        jScrollPane1.setViewportView(docContentTextArea);

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
        if (e.getSource() == browseDocButton) {
            FileDialog fileDialog = new FileDialog(new JFrame());
            //fileDialog.setFile("*.doc");
            fileDialog.setMode(FileDialog.LOAD);
            fileDialog.setTitle("Doc Explorer");
            //fileDialog.setFile("*.*");
            fileDialog.setEnabled(true);
            fileDialog.toFront();
            fileDialog.show();
            String docFilePath = fileDialog.getDirectory() + fileDialog.getFile();
            docContentTextArea.setText("");
            if (fileDialog.getFile().endsWith(".doc")) {
                docPathTextField.setText(docFilePath);
                try {
                    docFile = new File(docFilePath);
                    //A FileInputStream obtains input bytes from a file. 
                    FileInputStream fis = new FileInputStream(docFile.getAbsolutePath());
                    //A HWPFDocument used to read document file from FileInputStream
                    HWPFDocument doc = new HWPFDocument(fis);
                    docExtractor = new WordExtractor(doc);

                    //This Array stores each line from the document file.
                    String[] docArray = docExtractor.getParagraphText();

                    for (int i = 0; i < docArray.length; i++) {
                        if (docArray[i] != null) {
                            docContentTextArea.append("Line " + i + " : " + docArray[i] + "\n");
                        }
                    }
                } catch (Exception exep) {
                    System.out.println(exep.getMessage());
                }
            } else if (fileDialog.getFile().endsWith(".docx")) {
                docPathTextField.setText(docFilePath);
                try {
                    docFile = new File(docFilePath);
                    XWPFDocument documentX = new XWPFDocument(new FileInputStream(docFile.getAbsolutePath()));
                    List<XWPFParagraph> lines = documentX.getParagraphs();
                    for (int i = 0; i < lines.size(); i++) {
                        docContentTextArea.append("Line " + i + " : " + lines.get(i).getParagraphText() + "\n");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Not a doc file");
            }

        }
    }

}

