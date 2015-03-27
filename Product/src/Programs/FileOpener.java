package Programs;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;


public class FileOpener extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private JButton browseButton;
    private JLabel jLabel2;
    private JButton openButton;
    private JTextField pathTextField;
    
    public FileOpener() {
        initializeGUIComponents();
        browseButton.addActionListener(this);
        openButton.addActionListener(this);
        setLocationRelativeTo(null);
        setTitle("File Opener");
    }

    private void initializeGUIComponents() {
        GridBagConstraints gridBagConstraints;

        jLabel2 = new JLabel();
        pathTextField = new JTextField();
        browseButton = new JButton();
        openButton = new JButton();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new GridBagLayout());

        jLabel2.setText("Path To Search");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(105, 49, 0, 0);
        getContentPane().add(jLabel2, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 217;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(103, 22, 0, 0);
        getContentPane().add(pathTextField, gridBagConstraints);

        browseButton.setText("Browse");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(102, 18, 0, 65);
        getContentPane().add(browseButton, gridBagConstraints);

        openButton.setText("Open");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(33, 98, 119, 0);
        getContentPane().add(openButton, gridBagConstraints);

        pack();
    }                        
   
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == browseButton) {
            FileDialog fileDialog = new FileDialog(new JFrame());
            fileDialog.setFile("*.txt");
            fileDialog.setMode(FileDialog.LOAD);
            fileDialog.setTitle("File Explorer");
            fileDialog.setEnabled(true);
            fileDialog.toFront();
            fileDialog.show();
            String textFilePath = fileDialog.getDirectory() + fileDialog.getFile();
            pathTextField.setText(textFilePath);
        } else if (e.getSource() == openButton) {
            String filePath = pathTextField.getText().trim();
            try {
                if (!filePath.equalsIgnoreCase("")) {
                    File file = new File(filePath);
                    if (file.exists() && file.isFile()) {
                        if (Desktop.isDesktopSupported()) {
                            Desktop.getDesktop().edit(file);
                        }
                    }else{
                        JOptionPane.showMessageDialog(this, "File doesnt exists!!!");
                    }
                }else{
                    JOptionPane.showMessageDialog(this, "Path name cannot be null!!!");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
    }
}
