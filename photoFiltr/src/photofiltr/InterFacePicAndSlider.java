package photofiltr;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
        
        this.setBorder( new EmptyBorder(5, 5, 5, 5));
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
    
    public void doLightUp(){
        this.imgPanel.LightUp( this.getSliderValue());
    }
    public void doLightDown(){
        this.imgPanel.LightDown( this.getSliderValue());
    }
    public void choosePicture(){
        System.out.println("choosePicture");
    }
    public void reset(){
        this.imgPanel.ResetPicture();
    }
    //----
    class SliderPanel extends JPanel implements ChangeListener{

        private JSlider slider;
        private JTextField textSlider;

        public SliderPanel() {
            super();

            setPreferredSize( new Dimension( 600, 50));
            setBackground( Color.GREEN);
            setLayout( new FlowLayout(0));

            this.slider = new JSlider( JSlider.HORIZONTAL, 0, 255, 0);        
            this.slider.setPreferredSize( new Dimension( 550,30));
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

        private Image image;
        
        private int height = 440,
                    width = 590;

        public ImgPanel() {
            super();

            setPreferredSize( new Dimension( 600, 450));

            try{
                this.image = ImageIO.read( new File( "example.jpg"));
                this.scalImg();
            }
            catch( Exception e){
                System.out.println( "Bad File.jpg start");
            }
        }
        
        private void scalImg(){ 
            if( this.image.getHeight( this) < 440 )
                this.height = this.image.getHeight(this);

            if( this.image.getWidth( this) < 590 )
                this.width = this.image.getWidth(this);
            try{
               this.image = this.image.getScaledInstance( width, height, 10);
                
            }catch( Exception e){
                System.out.println("Bad file.jpg scall" + "\n" + e + "\n");
            }

        }
        
        public void ResetPicture(){
            try{
                this.image = ImageIO.read( new File( "example.jpg"));
                this.scalImg();
            }
            catch( IOException e){
                System.out.println( "Bad File.jpg resetpicture");
            }
            repaint();
        }

    public void LightUp( int transValue){
        BufferedImage BuffImg = ((ToolkitImage) image).getBufferedImage();

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

                if( r < 255 - transValue)
                    r = r + transValue;
                else
                    r = 255;

                if( g < 255 - transValue)
                    g = g + transValue;
                else
                    g = 255;

                if( b < 255 - transValue)
                    b = b + transValue;
                else
                    b = 255;

                p = (a<<24) | (r<<16) | (g<<8) | b;
                BuffImg.setRGB(f, h, p);
            }
        }
        repaint();
    }
        
        
    public void LightDown( int transValue){
        
        BufferedImage BuffImg = ((ToolkitImage) image).getBufferedImage();
        
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

                if( r > 0 + transValue)
                    r = r - transValue;
                else
                    r = 0;

                if( g > 0 + transValue)
                    g = g - transValue;
                else
                    g = 0;

                if( b > 0 + transValue)
                    b = b - transValue;
                else
                    b = 0;

                p = (a<<24) | (r<<16) | (g<<8) | b;
                BuffImg.setRGB(f, h, p);
            }
       }

        repaint();
    }
        
        public void doNegative(){
           BufferedImage BuffImg = ((ToolkitImage) image).getBufferedImage();
                        
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
           repaint();
        }

        @Override
        public void paint( Graphics g){
            
            g.drawImage( image, 0, 0, this);
        }

    }
}