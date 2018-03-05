package photofiltr;

import java.awt.HeadlessException;
import java.io.IOException;
import javax.swing.JFrame;

public class PhotoFrame extends JFrame{

    public PhotoFrame() throws HeadlessException, IOException {
        super();
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        
        add( new PhotoPanel());
        
        pack();
        setVisible(true);
    }
    

}
