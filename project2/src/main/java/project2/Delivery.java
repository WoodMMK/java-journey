/*
 * Members
 * Kunanont 115
 * Chatpum 118
 * Maimongkol 268
 * Sakolkiat 273
 */
package project2;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


class Fleet{
    final protected int max;
    private int available;
    
    Fleet(int max){
        this.max = max;
        available = max;
    }
    synchronized public void Jong(int num){
        if(available-num>=0){
            available-=num;
            System.out.println("current : "+available);
        }
        else{
            System.out.println("No Bike left");
        }
        
    }
    public int getAvailable(){
        return this.available;
    }
    public void report(){
        System.out.println("There is "+ getAvailable()+ " cars left");
    }
}

class BikeFleet extends Fleet{
    BikeFleet(int num){
        super(num);
    }
}
class TruckFleet extends Fleet{
    TruckFleet(int num){
        super(num);
    }
}

class SellerThread
{

}

class DeliveryShop
{

}

class DeliveryThread
{
    
}
class MyThread extends Thread{
    Fleet fp;
    public MyThread(String name, Fleet fp){
        super(name);
        this.fp = fp;
        this.start();
    }
    public void run(){
        for(int i = 0; i<3; i++){
           fp.Jong(2); 
        }
    }
}

public class Delivery {
    public static void main(String []args){
        Delivery mainapp = new Delivery();
        mainapp.runSimulation();
    }
    public Scanner readConfig(){
        String config_filename = "config_1.txt";
        String mainPath = "src/main/java/project2/";
        
        String productInput;
        Scanner productScan = null;
        
        while (productScan == null) {
            try {
                productScan = new Scanner(new File(mainPath + config_filename));
            } catch (FileNotFoundException e) {
                System.err.println(e);
                System.out.println("Enter correct file name: ");
                config_filename = new Scanner(System.in).nextLine();
            }
        }
        return productScan;
    }
    public void runSimulation(){
        Scanner configScan = readConfig();
        
        if(configScan != null){
            while(configScan.hasNextLine()){
                System.out.println(configScan.nextLine());
            }
            configScan.close();
        }
        
        BikeFleet myBikeFleet = new BikeFleet(10);
        MyThread MT1 = new MyThread("M1", myBikeFleet);
        MyThread MT2 = new MyThread("M2", myBikeFleet);
        MyThread MT3 = new MyThread("M3", myBikeFleet);
        
        try {
            MT1.join();
            MT2.join();
            MT3.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Delivery.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        System.out.println("end simulation");
    }
    
}
