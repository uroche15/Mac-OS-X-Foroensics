package Programs;

import java.awt.FileDialog;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.*;

public class TextReader extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	File textFile = null;
    private JButton browseTextButton;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JScrollPane jScrollPane1;
    private JTextArea textContentTextArea;
    private JTextField textPathTextField;

    public TextReader() {
        initializeGUIComponents();
        browseTextButton.addActionListener(this);
        setLocationRelativeTo(null);
        setTitle("Text Reader");
    }

    private void initializeGUIComponents() {
        GridBagConstraints gridBagConstraints;

        jLabel2 = new JLabel();
        textPathTextField = new JTextField();
        browseTextButton = new JButton();
        jLabel3 = new JLabel();
        jScrollPane1 = new JScrollPane();
        textContentTextArea = new JTextArea();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new GridBagLayout());

        jLabel2.setText("File Path:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(15, 39, 0, 0);
        getContentPane().add(jLabel2, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 217;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 22, 0, 0);
        getContentPane().add(textPathTextField, gridBagConstraints);

        browseTextButton.setText("Browse");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 18, 0, 0);
        getContentPane().add(browseTextButton, gridBagConstraints);

        jLabel3.setText("File Content:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 9;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(75, 39, 0, 0);
        getContentPane().add(jLabel3, gridBagConstraints);

        textContentTextArea.setColumns(20);
        textContentTextArea.setRows(5);
        jScrollPane1.setViewportView(textContentTextArea);

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
        if (e.getSource() == browseTextButton) {
            FileDialog fileDialog = new FileDialog(new JFrame());
            fileDialog.setFile("*.txt");
            fileDialog.setMode(FileDialog.LOAD);
            fileDialog.setTitle("Text Explorer");
            fileDialog.setEnabled(true);
            fileDialog.toFront();
            fileDialog.show();
            String textFilePath = fileDialog.getDirectory() + fileDialog.getFile();
            textContentTextArea.setText("");
            if (fileDialog.getFile().endsWith(".txt")) {
                textPathTextField.setText(textFilePath);
                BufferedReader br = null;
                try {
                    textFile = new File(textFilePath);
                    //A FileInputStream obtains input bytes from a file. 

                    if (textFile.exists()) {
                        System.out.println("File exists");
                    } else {
                        System.out.println("File not found!");
                        System.exit(0);
                    }

                    String sCurrentLine;
                    br = new BufferedReader(new FileReader(textFilePath));
                    while ((sCurrentLine = br.readLine()) != null) {
                        //System.out.println(sCurrentLine);
                        textContentTextArea.append(sCurrentLine + "\n");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    try {
                        if (br != null) {
                            br.close();
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Not a Text file");
            }
        }
    }
}
