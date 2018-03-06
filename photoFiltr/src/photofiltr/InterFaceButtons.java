package photofiltr;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class InterFaceButtons extends JPanel {
    
    private InterFacePicAndSlider IFPAS;
    private ButtonType[] buttons;
    private final String[] buttonNames = { "Wybierz zdjecie", "Resetuj", "Rozjasnij", "Przyciemnij", "Negatyw"};
    
    public InterFaceButtons( InterFacePicAndSlider IFPAS) {
        super();  
        
        this.IFPAS = IFPAS;
        this.setBorder( new EmptyBorder( 5, 5, 5, 5));
        this.setPreferredSize( new Dimension( 200, 500));
        this.setBackground( Color.GREEN);
        
        this.setLayout( new BoxLayout( this, BoxLayout.Y_AXIS));
        
        this.buttons = new ButtonType[ buttonNames.length];
        
        for( int f = 0; f < buttonNames.length; ++f){
            this.buttons[f] = new ButtonType( buttonNames[f]);
            add( this.buttons[f], this);
            add( Box.createRigidArea( new Dimension( 0,10)));
        }
        
        setVisible(true);
    }
    
    class ButtonType extends JButton implements ActionListener{

        public ButtonType( String name) {
            super(name);
            this.setMaximumSize( new Dimension( 190, 50));
            this.setAlignmentX( CENTER_ALIGNMENT);
            addActionListener(this);
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            Object source = ae.getSource();

            if( source == buttons[0]){
                IFPAS.choosePicture();
            }
            
            else if( source == buttons[1]){
                IFPAS.reset();
            }
            
            else if( source == buttons[2]){
                IFPAS.doLightUp();
            }
            
            else if( source == buttons[3]){
                IFPAS.doLightDown();
            }
            
            else if( source == buttons[4]){
                IFPAS.doNegative();
            }
        }
    }

}