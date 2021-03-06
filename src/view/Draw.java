/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

/**
 *
 * @author Alexander
 */
import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import model.Client;
import model.Fish;
import model.img;

public class Draw extends javax.swing.JPanel {

    private img Background;
    private LinkedList<Fish> ListFish;
    
    private Client tmp;

    /**
     * Creates new form Draw1
     */
    public Draw() {
        initComponents();
        this.ListFish = new LinkedList<>();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(51, 51, 51));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        int x=(int) evt.getPoint().getX();
        int y=(int) evt.getPoint().getY();
        tmp.SendClick(x,y);
    }//GEN-LAST:event_formMouseClicked

    public void Receive(img Bg) {
        if (Bg != null) {
            this.Background = Bg;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {

        try {
            int value = this.getHeight() / 3;
            super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
            g.drawImage(this.Background.getIcon().getImage(), this.Background.getX(), this.Background.getY(), this.Background.getWidth(),
                    this.Background.getHeight(), this);

            if (!ListFish.isEmpty()) {
//                System.out.println(ListFish);
                ListFish.forEach((element) -> {
                    if (element.getDirec() == 1) {
                        g.drawImage(element.getIcon().getImage(), element.getX(), element.getY(), element.getWidth(),
                                element.getHeight(), this);
                    } else {
                        g.drawImage(element.getIcon().getImage(), element.getX() + element.getWidth(), element.getY(), -element.getWidth(), element.getHeight(), this);
                    }
                });
            } else {
                this.ListFish = new LinkedList<>();

                repaint();
            }
        } catch (Exception e) {
        }
        repaint();
    }

    /**
     * @return the ListFish
     */
    public LinkedList<Fish> getListFish() {
        return ListFish;
    }

    /**
     * @param ListFish the ListFish to set
     */
    public void setListFish(LinkedList<Fish> ListFish) {
        this.ListFish = ListFish;
    }

    /**
     * @return the tmp
     */
    public Client getTmp() {
        return tmp;
    }

    /**
     * @param tmp the tmp to set
     */
    public void setTmp(Client tmp) {
        this.tmp = tmp;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
