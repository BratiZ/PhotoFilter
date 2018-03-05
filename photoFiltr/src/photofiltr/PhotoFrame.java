package photofiltr;

import java.awt.FlowLayout;
import javax.swing.JFrame;

public class PhotoFrame extends JFrame{
    
    InterFacePicAndSlider picAndSliderPanel;
    InterFaceButtons buttonsPanel;
    
    public PhotoFrame(){
        super("Photo Filtr");
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
        setResizable( false);
        
        this.picAndSliderPanel = new InterFacePicAndSlider();
        this.buttonsPanel = new InterFaceButtons( this.picAndSliderPanel);
        
        setLayout( new FlowLayout(1,0,0));
        
        add( this.picAndSliderPanel);
        add( this.buttonsPanel);
        
        pack();
        setVisible(true);
    }
    

}
