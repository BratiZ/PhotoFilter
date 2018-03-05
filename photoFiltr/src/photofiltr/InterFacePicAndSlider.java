package photofiltr;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import sun.awt.image.ToolkitImage;

public class InterFacePicAndSlider extends JPanel{
    
    private SliderPanel sliderPanel;
    private ImgPanel imgPanel;

    public InterFacePicAndSlider() {
        super();
        
        this.setBorder( new EmptyBorder(5, 5, 5, 0));
        setPreferredSize( new Dimension( 600, 500));
        setBackground( Color.GREEN);
        
        this.sliderPanel = new SliderPanel();
        this.imgPanel = new ImgPanel();
        
        setLayout( new BorderLayout());
        
        add( this.sliderPanel, BorderLayout.SOUTH);
        add( this.imgPanel, BorderLayout.CENTER);
        
        setVisible(true);
    }
    
    public int getSliderValue(){
        return this.sliderPanel.getSliderValue();
    }
    
    public void doNegative(){
            this.imgPanel.doNegative();
        }
    //----
    class SliderPanel extends JPanel implements ChangeListener{

        private JSlider slider;
        private JTextField textSlider;

        public SliderPanel() {
            super();

            setPreferredSize( new Dimension( 600, 50));
            setBackground( Color.GREEN);
            setLayout( new FlowLayout(2));

            this.slider = new JSlider( JSlider.HORIZONTAL, 0, 255, 0);        
            this.slider.setPreferredSize( new Dimension( 555,30));
            this.slider.addChangeListener( this);

            this.textSlider = new JTextField();
            this.textSlider.setText( this.slider.getValue() + "");
            this.textSlider.setPreferredSize( new Dimension( 30, 30));

            this.add( this.slider);
            this.add( this.textSlider);
        }

        public int getSliderValue(){
            return this.slider.getValue();
        }
        
        

        @Override
        public void stateChanged(ChangeEvent ce) {
            Object source = ce.getSource();

            if( source == this.slider){
                this.textSlider.setText( this.slider.getValue() + "");
            }
        }
    }
    //----
    class ImgPanel extends JPanel{

        private Image img;
        private int height = 450,
                    width = 590;

        public ImgPanel() {
            super();

            setPreferredSize( new Dimension( 600, 450));

            try{
                this.img = ImageIO.read( new File( "example.jpg"));
                this.scalImg();
            }
            catch( Exception e){
                System.out.println( "Bad File.jpg");
            }
        }

        public void setImage( Image img){
            this.img = img;
            this.scalImg();
        }
        
        private void scalImg(){ 
            if( this.img.getHeight( this) < 450 )
                this.height = this.img.getHeight(this);

            if( this.img.getWidth( this) < 590 )
                this.width = this.img.getWidth(this);

            this.img = img.getScaledInstance( width, height, 100);
        }
        
        public void doNegative(){
            BufferedImage BuffImg = ((ToolkitImage) img).getBufferedImage();
                        
            int p,
                a,
                r,
                g,
                b,
                widthIMG = BuffImg.getWidth(),
                heightIMG = BuffImg.getHeight();

            for( int f = 0; f < widthIMG; ++f){
                for( int h = 0; h < heightIMG; ++h){
                    p = BuffImg.getRGB(f, h);
                    a = (p>>24) & 0xff;
                    r = (p>>16) & 0xff;
                    g = (p>>8) & 0xff;
                    b = p & 0xff;

                    r = 255 - r;
                    g = 255 - g;
                    b = 255 - b;

                    p = (a<<24) | (r<<16) | (g<<8) | b;
                    BuffImg.setRGB(f, h, p);
                }
           }
            
           this.img = BuffImg;
           repaint();
        }

        @Override
        public void paint( Graphics g){
            g.drawImage( img, 0, 0, this);
        }

    }
}