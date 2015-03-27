package Programs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class WelcomePage extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private JButton enterButton;
    private JLabel jLabel1;
    private JLabel jLabel2;
    
    public WelcomePage() {
        initializeGUIComponents();
        enterButton.addActionListener(this);
        setLocationRelativeTo(null);
        setTitle("Mac OS X Toolkit");
    }

    
    private void initializeGUIComponents() {
        GridBagConstraints gridBagConstraints;

        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        enterButton = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new GridBagLayout());

        jLabel1.setFont(new Font("Tahoma", 1, 12)); 
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel1.setText("Mac OS X ToolKit");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 284;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(42, 10, 0, 10);
        getContentPane().add(jLabel1, gridBagConstraints);

        jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel2.setText("Welcome to Mac OS X Toolkit. Press Enter to continue");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 109;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(79, 10, 0, 10);
        getContentPane().add(jLabel2, gridBagConstraints);

        enterButton.setText("Enter");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 19;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(50, 165, 77, 0);
        getContentPane().add(enterButton, gridBagConstraints);

        pack();
    }
    
    public static void main(String args[]) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } 

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WelcomePage().setVisible(true);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == enterButton) {
            new MainFrame().setVisible(true);
            dispose();
        }
    }
}