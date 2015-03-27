package Programs;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;


public class StringSearch extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	String filepath;
    String word;
    String extension;
    
    private JButton browseButton;
    private JTextField extensionTextField;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JScrollPane jScrollPane1;
    private JTextField pathTextField;
    private JTextArea pathsTextArea;
    private JButton searchButton;
    private JTextField searchWordTextField;
    
    public StringSearch() {
        initializeGUIComponents();
        browseButton.addActionListener(this);
        searchButton.addActionListener(this);
        setLocationRelativeTo(null);
        setTitle("String Search");
    }

    
    private void initializeGUIComponents() {
        GridBagConstraints gridBagConstraints;

        jLabel1 = new JLabel();
        searchWordTextField = new JTextField();
        jLabel2 = new JLabel();
        pathTextField = new JTextField();
        browseButton = new JButton();
        jLabel3 = new JLabel();
        jScrollPane1 = new JScrollPane();
        pathsTextArea = new JTextArea();
        searchButton = new JButton();
        jLabel4 = new JLabel();
        extensionTextField = new JTextField();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new GridBagLayout());

        jLabel1.setText("Word To Search");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(45, 10, 0, 0);
        getContentPane().add(jLabel1, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 124;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(42, 18, 0, 0);
        getContentPane().add(searchWordTextField, gridBagConstraints);

        jLabel2.setText("Path To Search");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(44, 10, 0, 0);
        getContentPane().add(jLabel2, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 217;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(41, 18, 0, 0);
        getContentPane().add(pathTextField, gridBagConstraints);

        browseButton.setText("Browse");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(40, 18, 0, 0);
        getContentPane().add(browseButton, gridBagConstraints);

        jLabel3.setText("Paths:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.ipadx = 42;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(27, 10, 0, 0);
        getContentPane().add(jLabel3, gridBagConstraints);

        pathsTextArea.setColumns(20);
        pathsTextArea.setRows(5);
        jScrollPane1.setViewportView(pathsTextArea);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 15;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 402;
        gridBagConstraints.ipady = 157;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(27, 18, 11, 32);
        getContentPane().add(jScrollPane1, gridBagConstraints);

        searchButton.setText("Search");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(25, 123, 0, 0);
        getContentPane().add(searchButton, gridBagConstraints);

        jLabel4.setText("Extension");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.ipadx = 31;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(45, 29, 0, 0);
        getContentPane().add(jLabel4, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 11;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 124;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(42, 18, 0, 32);
        getContentPane().add(extensionTextField, gridBagConstraints);

        pack();
    }                       
          
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == browseButton) {
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new java.io.File("/Users/Umer/"));
            chooser.setDialogTitle("Select Folder");
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            chooser.setAcceptAllFileFilterUsed(false);
            
            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                
                filepath = "" + chooser.getCurrentDirectory();
                pathTextField.setText(filepath);
            } else {
                filepath = "";
            }
            
        } else if (e.getSource() == searchButton) {
            word = searchWordTextField.getText().trim();
            pathsTextArea.setText("");
            extension = extensionTextField.getText().trim();
            if (!filepath.equalsIgnoreCase("") && !word.equalsIgnoreCase("") && !extension.equals("")) {
                System.out.println("here");
                try {
                    String textToMatch = word;
                    ArrayList<String> paths = new ArrayList<String>();
                    String content;
                    int found = 0;
                    int notFound = 0;
                    FilenameFilter filter = new FilenameFilter() {
                        public boolean accept(File dir, String name) {
                            return name.endsWith(extension);
                        }
                    };
                    
                    File path = new File(filepath);
                    File[] listOfFiles = path.listFiles(filter);
                    for (File file : listOfFiles) {
                        if (extension.equalsIgnoreCase(".doc")) {
                            content = readFromDoc(file);
                            if (content.contains(textToMatch)) {
                                paths.add(file.getAbsolutePath());
                                found++;
                            } else {
                                notFound++;
                            }
                        } else if (extension.equalsIgnoreCase(".docx")) {
                            content = readFromDocx(file);
                            if (content.contains(textToMatch)) {
                                paths.add(file.getAbsolutePath());
                                found++;
                            } else {
                                notFound++;
                            }
                        } else if (extension.equalsIgnoreCase(".pdf")) {
                            content = readFromPDF(file);
                            if (content.contains(textToMatch)) {
                                paths.add(file.getAbsolutePath());
                                found++;
                            } else {
                                notFound++;
                            }
                        } else {
                            content = FileUtils.readFileToString(file);
                            if (content.contains(textToMatch)) {
                                paths.add(file.getAbsolutePath());
                                found++;
                            } else {
                                notFound++;
                            }
                        }
                        
                    }
                    for (String pth : paths) {
                        pathsTextArea.append(pth + "\n");
                    }
                    JOptionPane.showMessageDialog(this, "Found in " + found + " files.\nNot found in " + notFound + " files.");
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
    
    public String readFromDoc(File file) {
        String content = "";
        try {
            FileInputStream fis = new FileInputStream(file.getAbsolutePath());
            HWPFDocument doc = new HWPFDocument(fis);
            WordExtractor docExtractor = new WordExtractor(doc);
            
            String[] docArray = docExtractor.getParagraphText();
            
            for (int i = 0; i < docArray.length; i++) {
                if (docArray[i] != null) {
                    content += docArray[i];
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return content;
    }
    
    public String readFromDocx(File file) {
        String content = "";
        try {
            XWPFDocument documentX = new XWPFDocument(new FileInputStream(file.getAbsolutePath()));
            List<XWPFParagraph> lines = documentX.getParagraphs();
            for (int i = 0; i < lines.size(); i++) {
                content += lines.get(i).getParagraphText();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
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
