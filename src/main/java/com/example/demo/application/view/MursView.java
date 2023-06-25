package com.example.demo.application.view;

import com.example.demo.application.model.Coor;
import javafx.scene.CacheHint;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class MursView extends ArrayList<MurView> {

    private ImageView cachedImageView;  // ImageView for cached walls

    public MursView(int nb) {
        super(nb);
        for (int i = 0; i < nb; i++) {
            this.add(new MurView());
        }

        // Create an ImageView for caching the walls
        cachedImageView = createCachedWallsImageView();
    }

    public void setPositions(ArrayList<Coor> c) {
        for (int i = 0; i < c.size() && i < this.size(); i++) {
            this.get(i).setPosition(c.get(i).getX(), c.get(i).getY());
        }
    }

    public ImageView createCachedWallsImageView() {
        // Create a new ImageView with dimensions matching the walls
        ImageView imageView = new ImageView();

        // Set the dimensions of the ImageView to match the walls
        double width = 20;
        double height = 20;
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);

        // Enable image caching for the ImageView
        imageView.setCache(true);
        imageView.setCacheHint(CacheHint.SPEED);

        // Set the image of the ImageView to the cached walls image
        imageView.setImage(createCachedWallsImage());

        return imageView;
    }

    private Image createCachedWallsImage() {
        // Create a new WritableImage with dimensions matching the walls
        WritableImage cachedImage = new WritableImage(500,500);

        // Create a canvas with the same dimensions as the walls
        Canvas canvas = new Canvas(500,500);

        // Get the graphics context of the canvas
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Render the walls onto the canvas
        gc.setFill(Color.BLACK);  // Set the desired wall color
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // Render each individual wall element onto the canvas (replace this with your actual wall rendering logic)
        for (MurView wall: this) {
            gc.setFill(Color.PURPLE);  // Set the desired wall color
            gc.fillRect(wall.getX(), wall.getY(), wall.getWidth(), wall.getHeight());
        }

        // Snapshot the canvas onto the cached image
        canvas.snapshot(null, cachedImage);

        // Return the cached image
        return cachedImage;
    }

    // ...
}
/*public class MursView extends ArrayList<MurView>{

    public int getNumMurs(){
        return this.size();
    }

    public MursView(int nb){
        for(int i = this.size(); i<nb; i++)
        this.add(new MurView());
    }


    public void setPositions(ArrayList<Coor> c){
        for (int i = 0; i<c.size() && i<this.size();i++){
            this.get(i).setPosition(c.get(i).getX(),c.get(i).getY());
            //System.out.println("entered here ");
        }
    }




}*/
