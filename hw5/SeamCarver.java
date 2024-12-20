import edu.princeton.cs.algs4.Picture;

import java.awt.*;

public class SeamCarver {
    private Picture picture;
    public SeamCarver(Picture picture){
        this.picture = picture;
    }
    public Picture picture(){                       // current picture
        return this.picture;
    }
    public     int width(){                         // width of current picture
        return picture.width();
    }
    public     int height(){                        // height of current picture
        return picture.height();
    }
    private int cal(int x1, int y1, int x2, int y2) {
        Color color1 = picture.get(x1, y1);
        Color color2 = picture.get(x2, y2);
        int rx = color1.getRed() - color2.getRed();
        int gx = color1.getGreen() - color2.getGreen();
        int bx = color1.getBlue() - color2.getBlue();
        return rx * rx + gx * gx + bx * bx;
    }
    public  double energy(int x, int y){            // energy of pixel at column x and row y
        if(x < 0 || x >= width() || y < 0 || y >= height()){
            throw new IndexOutOfBoundsException();
        }
        int x1 = x - 1 < 0 ? width() - 1 : x - 1;
        int x2 = x + 1 >= width() ? 0 : x + 1;
        int y1 = y - 1 < 0 ? height() - 1 : y - 1;
        int y2 = y + 1 >= height() ? 0 : y + 1;
        return cal(x1, y, x2, y) + cal(x, y1, x, y2);
    }
    public   int[] findHorizontalSeam(){            // sequence of indices for horizontal seam
//        double[][] energy = new double[height()][width()];
        int[] seam = new int[width()];
        double[][] energy = calEnergy(width(), height());
        // transform the matrix energy
        double[][] tEnergy = new double[width()][height()];
        for(int y = 0; y < height(); y++){
            for(int x = 0; x < width(); x++){
                tEnergy[x][y] = energy[y][x];
            }
        }
        energy = cumulativeEnergy(tEnergy, height(), width());
        seam = findVerticalseamHelpter(energy, height(), width());
        return seam;
    }
    private double[][] calEnergy(int width, int height){
        double[][] energy = new double[height][width];
        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                energy[y][x] = energy(x, y);
            }
        }
        return energy;
    }
    private double[][] cumulativeEnergy(double[][] energy, int width, int height){
        for(int y = 1; y < height; y++){
            for(int x = 0; x < width; x++){
                if(x == 0) {
                    energy[y][x] += Math.min(energy[y-1][x], energy[y-1][x+1]);
                }else if(x == width - 1){
                    energy[y][x] += Math.min(energy[y-1][x-1], energy[y-1][x]);
                }else{
                    energy[y][x] += Math.min(Math.min(energy[y-1][x-1], energy[y-1][x]), energy[y-1][x+1]);
                }
            }
        }
        return energy;
    }
    private int[] findVerticalseamHelpter(double[][] energy, int width, int height){
        double minEnergy = Double.MAX_VALUE;
        int minEnergyX = 0;
        int[] seam = new int[height];
//        double[][] energy = calEnergy(width, height);
//        energy = cumulativeEnergy(energy, width, height);
        // find lowest energy in height-1 row
        for(int x = 0; x < width; x++){
            if(energy[0][x] < minEnergy){
                minEnergy = energy[0][x];
                minEnergyX = x;
            }
            seam[0] = minEnergyX;
        }
        for(int y = 1; y < height; y++){
            double Energyx_1 = energy[y][minEnergyX-1];
            double Energyx = energy[y][minEnergyX];
            double Energyx1 = energy[y][minEnergyX+1];
            if(energy[y][minEnergyX-1] < energy[y][minEnergyX]){
                if(energy[y][minEnergyX-1] < energy[y][minEnergyX+1]){
                    minEnergyX = minEnergyX - 1;
                } else{
                    minEnergyX = minEnergyX + 1;
                }
            }else{
                if(energy[y][minEnergyX] > energy[y][minEnergyX+1]){
                    minEnergyX = minEnergyX + 1;
                }
            }
            seam[y] = minEnergyX;
        }
        return seam;
    }
    public   int[] findVerticalSeam(){              // sequence of indices for vertical seam
        int[] seam = new int[height()];
//        double[][] energy = new double[height()][width()];
        double[][] energy = calEnergy(width(), height());
        energy = cumulativeEnergy(energy, width(), height());
        seam = findVerticalseamHelpter(energy, width(), height());
        return seam;
    }
    private void removeValid(int[] seam){
        for(int i = 1; i < seam.length - 1; i++){
            if(Math.abs(seam[i] - seam[i-1]) > 1){
                throw new IllegalArgumentException("The difference between two adjacent elements is greater than one");
            }
        }
    }
    public static Picture transformPicture(Picture picture){
        Picture p = new Picture(picture.height(), picture.width());
        for(int x = 0; x < picture.width(); x++){
            for(int y = 0; y < picture.height(); y++){
                p.set(y, x, picture.get(x, y));
            }
        }
        return p;
    }
    private Picture removeHelpter(Picture picture, int[] seam){
        Picture p = new Picture(width()-1, height());
        for(int j = 0;j < height(); j++) {
            for (int i = width() - 2; i >= seam[i]; i--) {
                p.set(j, i, picture.get(j, i + 1));
            }
        }
        return p;
    }
    public    void removeHorizontalSeam(int[] seam){   // remove horizontal seam from picture
        removeValid(seam);
        picture = transformPicture(removeHelpter(transformPicture(picture), seam));
    }
    public    void removeVerticalSeam(int[] seam){     // remove vertical seam from picture
        removeValid(seam);
        picture = removeHelpter(picture, seam);
    }
}