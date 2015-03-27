package Programs;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;
import javax.swing.*;


public class ExtensionSearch extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	String extension = "";
    String path = "";
    
    private JButton browseButton;
    private JTextField extensionTextField;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JScrollPane jScrollPane1;
    private JTextField pathTextField;
    private JTextArea pathsTextArea;
    private JButton searchButton;

    public ExtensionSearch() {
        initializeGUIComponents();
        browseButton.addActionListener(this);
        searchButton.addActionListener(this);
        setLocationRelativeTo(null);
        setTitle("Extension Search");
    }

    
    private void initializeGUIComponents() {
        GridBagConstraints gridBagConstraints;

        jLabel4 = new JLabel();
        extensionTextField = new JTextField();
        jLabel2 = new JLabel();
        pathTextField = new JTextField();
        browseButton = new JButton();
        searchButton = new JButton();
        jLabel3 = new JLabel();
        jScrollPane1 = new JScrollPane();
        pathsTextArea = new JTextArea();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new GridBagLayout());

        jLabel4.setText("Extension");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 31;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(14, 10, 0, 0);
        getContentPane().add(jLabel4, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 124;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(11, 18, 0, 0);
        getContentPane().add(extensionTextField, gridBagConstraints);

        jLabel2.setText("Path To Search");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(38, 10, 0, 0);
        getContentPane().add(jLabel2, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 217;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(36, 17, 0, 0);
        getContentPane().add(pathTextField, gridBagConstraints);

        browseButton.setText("Browse");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(35, 18, 0, 0);
        getContentPane().add(browseButton, gridBagConstraints);

        searchButton.setText("Search");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(18, 111, 0, 0);
        getContentPane().add(searchButton, gridBagConstraints);

        jLabel3.setText("Results:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.ipadx = 42;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(87, 10, 0, 0);
        getContentPane().add(jLabel3, gridBagConstraints);

        pathsTextArea.setColumns(20);
        pathsTextArea.setRows(5);
        jScrollPane1.setViewportView(pathsTextArea);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 515;
        gridBagConstraints.ipady = 190;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(26, 9, 11, 10);
        getContentPane().add(jScrollPane1, gridBagConstraints);

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
                pathTextField.setText("" + chooser.getSelectedFile());
            } else {
                System.out.println("No Selection ");
            }
        } else if (e.getSource() == searchButton) {
            path = pathTextField.getText().trim();
            extension = extensionTextField.getText().trim();
            if (!path.equalsIgnoreCase("") && !extension.equalsIgnoreCase("")) {
                GenericExtFilter filter = new GenericExtFilter(extension);
                File dir = new File(path);

                if (dir.isDirectory() == false) {
                    JOptionPane.showMessageDialog(this, "Directory does not exist: " + path);
                    return;
                }

                // list out all the file name and filter by the extension
                String[] list = dir.list((FilenameFilter) filter);

                if (list.length == 0) {
                    JOptionPane.showMessageDialog(this, "no files in this directory contain the extension: " + extension);
                    return;
                }

                for (String file : list) {
                    String temp = new StringBuffer(path).append(File.separator)
                            .append(file).toString();
                    pathsTextArea.append("file : " + temp + "\n");
                }
            }else{
                JOptionPane.showMessageDialog(this, "Values cannot be null!!!");
            }

        }
    }

    public class GenericExtFilter implements FilenameFilter {

        private String ext;

        public GenericExtFilter(String ext) {
            this.ext = ext;
        }

        public boolean accept(File dir, String name) {
            return (name.endsWith(ext));
        }
    }
}
