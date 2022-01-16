/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.Domaine;

import java.awt.Point;
import java.io.Serializable;

/**
 *
 * @author Solution
 */
public class CoordonneeEcran  implements Serializable{
   private double hauteurDrawingPanel;
   private double longueurDrawingPanel;

   private double offsetY;
   private double offsetX;
   
   private double fStartPanX;
   
   private double fStartPanY;
   
   private double fScaleX;
   
   private double fScaleY;
   int taillePanelEnMillimetre ;
    private static final CoordonneeEcran SINGLETON_INSTANCE = new CoordonneeEcran();
    public static CoordonneeEcran getInstance() {
      return SINGLETON_INSTANCE;
    }
    
    public CoordonneeEcran() {
        this.offsetX = 0.0;
        this.offsetY = 0.0;
        this.fStartPanX = 0.0;
        this.fStartPanY = 0.0;
        this.fScaleX = 1.0;
        this.fScaleY = 1.0;
        this.taillePanelEnMillimetre=100;
    }
   
   

    /**
     * Get the value of fScaleY
     *
     * @return the value of fScaleY
     */
    public double getfScaleY() {
        return fScaleY;
    }

    /**
     * Set the value of fScaleY
     *
     * @param fScaleY new value of fScaleY
     */
    public void setfScaleY(double fScaleY) {
        this.fScaleY = fScaleY;
    }


    /**
     * Get the value of fScaleX
     *
     * @return the value of fScaleX
     */
    public double getfScaleX() {
        return fScaleX;
    }

    /**
     * Set the value of fScaleX
     *
     * @param fScaleX new value of fScaleX
     */
    public void setfScaleX(double fScaleX) {
        this.fScaleX = fScaleX;
    }


    /**
     * Get the value of fStartPanY
     *
     * @return the value of fStartPanY
     */
    public double getfStartPanY() {
        return fStartPanY;
    }

    /**
     * Set the value of fStartPanY
     *
     * @param fStartPanY new value of fStartPanY
     */
    public void setfStartPanY(double fStartPanY) {
        this.fStartPanY = fStartPanY;
    }


    /**
     * Get the value of fStartPanX
     *
     * @return the value of fStartPanX
     */
    public double getfStartPanX() {
        return fStartPanX;
    }

    /**
     * Set the value of fStartPanX
     *
     * @param fStartPanX new value of fStartPanX
     */
    public void setfStartPanX(double fStartPanX) {
        this.fStartPanX = fStartPanX;
    }

    /**
     * Get the value of offsetY
     *
     * @return the value of offsetY
     */
    public double getOffsetY() {
        return offsetY;
    }

    /**
     * Set the value of offsetY
     *
     * @param offsetY new value of offsetY
     */
    public void setOffsetY(double offsetY) {
        this.offsetY = offsetY;
    }

 

    /**
     * Get the value of offsetX
     *
     * @return the value of offsetX
     */
    public double getOffsetX() {
        return offsetX;
    }

    /**
     * Set the value of offsetX
     *
     * @param offsetX new value of offsetX
     */
    public void setOffsetX(double offsetX) {
        this.offsetX = offsetX;
    }
   
   
    /**
     * Get the value of hauteurDrawingPanel
     *
     * @return the value of hauteurDrawingPanel
     */
    protected double getHauteurDrawingPanel() {
        return hauteurDrawingPanel;
    }

    /**
     * Set the value of hauteurDrawingPanel
     *
     * @param hauteurDrawingPanel new value of hauteurDrawingPanel
     */
    protected void setHauteurDrawingPanel(double hauteurDrawingPanel) {
        this.hauteurDrawingPanel = hauteurDrawingPanel;
    }

    /**
     * Get the value of longueurDrawingPanel
     *
     * @return the value of longueurDrawingPanel
     */
    protected double getLongueurDrawingPanel() {
        return longueurDrawingPanel;
    }

    /**
     * Set the value of longueurDrawingPanel
     *
     * @param longueurDrawingPanel new value of longueurDrawingPanel
     */
    protected void setLongueurDrawingPanel(double longueurDrawingPanel) {
        this.longueurDrawingPanel = longueurDrawingPanel;
    }

    public double getZoomScale() {
        return this.fScaleX;
    }
    
    private int worldToScreenX(double fWorldX) {
        return (int)(((fWorldX - offsetX) / this.getRatioMillimetreParPixel()) * fScaleX);
    }
    private int worldToScreenY(double fWorldY) {
        return (int)(((fWorldY - offsetY) / this.getRatioMillimetreParPixel()) * fScaleY);
    }
    private double screenToWorldX(int nScreenX) {
        return (double)(nScreenX * this.getRatioMillimetreParPixel()/ fScaleX + offsetX);
    }
    private double screenToWorldY(int nScreenY) {
        return (double)(nScreenY * this.getRatioMillimetreParPixel()/ fScaleY + offsetY);
    }
    
    public void zoomIn(Point mousePoint, double scrollAmount) {
        double fMouseWorldX_BeforeZoom = screenToWorldX((int)mousePoint.getX());
        double fMouseWorldY_BeforeZoom = screenToWorldY((int)mousePoint.getY());
        this.fScaleX *= scrollAmount;
        this.fScaleY *= scrollAmount;
        double fMouseWorldX_AfterZoom = screenToWorldX((int)mousePoint.getX());
        double fMouseWorldY_AfterZoom = screenToWorldY((int)mousePoint.getY());
        this.offsetX += (fMouseWorldX_BeforeZoom - fMouseWorldX_AfterZoom);
        this.offsetY += (fMouseWorldY_BeforeZoom - fMouseWorldY_AfterZoom);
    }
        
    public double getPixelDistance(double reelDistance) {
        return (((reelDistance) / this.getRatioMillimetreParPixel()) * fScaleX);
    }
    

    public void zoomOut(Point mousePoint, double scrollAmount) {
        double fMouseWorldX_BeforeZoom = screenToWorldX((int)mousePoint.getX());
        double fMouseWorldY_BeforeZoom = screenToWorldY((int)mousePoint.getY());
        this.fScaleX /= scrollAmount;
        this.fScaleY /= scrollAmount;
        double fMouseWorldX_AfterZoom = screenToWorldX((int)mousePoint.getX());
        double fMouseWorldY_AfterZoom = screenToWorldY((int)mousePoint.getY());
        this.offsetX += (fMouseWorldX_BeforeZoom - fMouseWorldX_AfterZoom);
        this.offsetY += (fMouseWorldY_BeforeZoom - fMouseWorldY_AfterZoom);
    }
     public Point getPixelCoord(Point reelPoint) {
        Point screenPoint = new Point(worldToScreenX(reelPoint.getX()), worldToScreenY(reelPoint.getY()));
        return screenPoint;
    }
    
    public Point getCoordonneeReel(Point pixelPoint) {
        Point worldPoint = new Point((int)screenToWorldX((int)pixelPoint.getX()), (int)screenToWorldY((int)pixelPoint.getY()));
        return worldPoint;
    }
    
    public double getReelDistance(double pixelDistance) {
        return (pixelDistance * this.getRatioMillimetreParPixel()/ fScaleX);
    }
   
    public void setStartPan(Point pixelMousePoint) {
        this.fStartPanX = pixelMousePoint.getX();
        this.fStartPanY = pixelMousePoint.getY();
    }
    public void setPan(Point pixelMousePoint) {
        this.offsetX -= (pixelMousePoint.getX() - this.fStartPanX) * this.getRatioMillimetreParPixel() / this.fScaleX;
        this.offsetY -= (pixelMousePoint.getY() - this.fStartPanY) * this.getRatioMillimetreParPixel() / this.fScaleY;
        this.fStartPanX = pixelMousePoint.getX();
        this.fStartPanY = pixelMousePoint.getY();
    }
    
    public void resetPanZoom() {
        this.offsetX = 0.0;
        this.offsetY = 0.0;
        this.fStartPanX = 0.0;
        this.fStartPanY = 0.0;
        this.fScaleX = 1.0;
        this.fScaleY = 1.0;
    }
    
    public double getRatioMillimetreParPixel() {
        return (this.taillePanelEnMillimetre / this.longueurDrawingPanel);
    }
    
}
