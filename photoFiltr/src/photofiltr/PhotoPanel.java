package photofiltr;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PhotoPanel extends JPanel implements MouseListener{

    int width,
        height,
        choice = 0,
        transValue = 120;
    
    Image image;
    BufferedImage img;
    File f = null;
    
    void loadImage() throws IOException{
        this.image = new ImageIcon("Obr.jpg").getImage();
        this.width = this.image.getWidth(this);
        this.height = this.image.getHeight(this);
    }
    
    public PhotoPanel() throws IOException {
        super();
        addMouseListener(this);
        this.loadImage();
        setPreferredSize( new Dimension( this.width, this.height));
        
    }
    
    void LightUp(){
        try{
            this.f = new File("obr.jpg");
            this.img = ImageIO.read(f);
        }
        catch(Exception e){
            System.out.println( e);
        }
        
        int p,
            a,
            r,
            g,
            b,
            widthIMG = img.getWidth(),
            heightIMG = img.getHeight();

        for( int f = 0; f < widthIMG; ++f){
            for( int h = 0; h < heightIMG; ++h){
                p = img.getRGB(f, h);
                a = (p>>24) & 0xff;
                r = (p>>16) & 0xff;
                g = (p>>8) & 0xff;
                b = p & 0xff;
                
                if( r < 255 - this.transValue)
                    r = r + this.transValue;
                else
                    r = 255;
                
                if( g < 255 - this.transValue)
                    g = g + this.transValue;
                else
                    g = 255;
                
                if( b < 255 - this.transValue)
                    b = b + this.transValue;
                else
                    b = 255;
                
                p = (a<<24) | (r<<16) | (g<<8) | b;
                img.setRGB(f, h, p);
            }
       }
        
        try{
            f = new File("Obr2.jpg");
            ImageIO.write(img, "jpg", f);
        }
        catch(IOException e){
            System.out.println(e);
        }
    }
    
    void LightDown(){
        try{
            this.f = new File("obr.jpg");
            this.img = ImageIO.read(f);
        }
        catch(Exception e){
            System.out.println( e);
        }
        int p,
            a,
            r,
            g,
            b,
            widthIMG = img.getWidth(),
            heightIMG = img.getHeight();

        for( int f = 0; f < widthIMG; ++f){
            for( int h = 0; h < heightIMG; ++h){
                p = img.getRGB(f, h);
                a = (p>>24) & 0xff;
                r = (p>>16) & 0xff;
                g = (p>>8) & 0xff;
                b = p & 0xff;
                
                if( r > 0 + this.transValue)
                    r = r - this.transValue;
                else
                    r = 0;
                
                if( g > 0 + this.transValue)
                    g = g - this.transValue;
                else
                    g = 0;
                
                if( b > 0 + this.transValue)
                    b = b - this.transValue;
                else
                    b = 0;
                
                p = (a<<24) | (r<<16) | (g<<8) | b;
                img.setRGB(f, h, p);
            }
       }
        
        try{
            f = new File("Obr2.jpg");
            ImageIO.write(img, "jpg", f);
        }
        catch(IOException e){
            System.out.println(e);
        }
    }
    
    void Revers(){
        try{
            this.f = new File("obr.jpg");
            this.img = ImageIO.read(f);
        }
        catch(Exception e){
            System.out.println( e);
        }
        int p,
            a,
            r,
            g,
            b,
            widthIMG = img.getWidth(),
            heightIMG = img.getHeight();

        for( int f = 0; f < widthIMG; ++f){
            for( int h = 0; h < heightIMG; ++h){
                p = img.getRGB(f, h);
                a = (p>>24) & 0xff;
                r = (p>>16) & 0xff;
                g = (p>>8) & 0xff;
                b = p & 0xff;
                
                r = 255 - r;
                g = 255 - g;
                b = 255 - b;
                
                p = (a<<24) | (r<<16) | (g<<8) | b;
                img.setRGB(f, h, p);
            }
       }
        
        try{
            f = new File("Obr2.jpg");
            ImageIO.write(img, "jpg", f);
        }
        catch(IOException e){
            System.out.println(e);
        }
    }
    
    @Override
    public void paint( Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        
        if( choice%3 == 1)
            this.LightUp();

        else if( choice%3 == 2)
            this.LightDown();
        
        else if( this.choice%3 == 0)
            this.Revers();
        
        this.image = new ImageIcon("Obr2.jpg").getImage();
        g2d.drawImage(image, 0, 0, this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
          if( e.getButton() == 1){
            choice++;
            repaint();
        }
        else if( e.getButton() == 3){
            choice--;
            repaint();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
      
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
}
