package MBO.java;

import java.util.Random;

/**
 * Created by joero on 2/3/2017.
 */
public class Train {
    int id;
    int unloading;

    public Train(){
        this.unloading = generateUnloading();
    }

    public Train(int id){
        this.id = id;
        this.unloading = generateUnloading();
    }

    public int getId(){
        return id;
    }

    public int getUnloading(){
        return unloading;
    }

    public void unload(){
        this.unloading = generateUnloading();
    }

    private int generateUnloading() {
        return new Random().nextInt(222 - 74) + 74;
    }


}
