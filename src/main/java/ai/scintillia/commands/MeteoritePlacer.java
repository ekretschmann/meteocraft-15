package ai.scintillia.commands;

import net.minecraft.command.CommandSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.ArrayList;
import java.util.List;

public class MeteoritePlacer {


    public static BiomeBoundaries getBiomeBoundaries(long x, long y , Plane w){

        String biome =w.getBiome(x,y);
        long currentX=x;
        if(biome.equals("unkown")){
            throw new RuntimeException("This should not happen");
        }

        Coordinate rightX=runRight(x,y,biome,w);

        Coordinate leftX=runLeft(x,y,biome,w);

        Coordinate bottomY= runDown(x,y,biome,w);

        Coordinate topY=runUp(x,y,biome,w);

        Coordinate diagonalTopRight=runTopRight(x,y,biome,w);
        Coordinate diagonalBottomRight=runBottomRight(x,y,biome,w);
        Coordinate diagonalTopLeft=runTopLeft(x,y,biome,w);
        Coordinate diagonalBottomLeft=runBottomLeft(x,y,biome,w);


//        BiomeBoundaries result=new BiomeBoundaries(leftX.x,bottomY.y,rightX.x,topY.y,w.getBiome(x,y));
        long maxX=Math.max(Math.max(rightX.x,diagonalTopRight.x),diagonalBottomRight.x);

        long maxY=Math.max(Math.max(topY.y,diagonalTopRight.y),diagonalTopLeft.y);
//
        long minX=Math.min(Math.min(leftX.x,diagonalTopLeft.x),diagonalBottomLeft.x);

        long minY=Math.min(Math.min(bottomY.y,diagonalBottomLeft.y),diagonalBottomRight.y );


        BiomeBoundaries result=new BiomeBoundaries(minX,maxY , maxX, minY,w.getBiome(x,y));

        return result;
    }

    public static List<Coordinate> placeMeteorites(long x, long y, String biome, Plane w){
        return placeMeteorites(x,y,biome,w,0.01f);
    }

    public static List<Coordinate> placeMeteorites(long x, long y, String biome, Plane w, float probability){
            List<Coordinate> result = new ArrayList<Coordinate>();
        BiomeBoundaries boundaries =getBoundariesHeuristic(x,y,w);
        for( x= boundaries.getMinX(); x<=boundaries.getMaX(); x++){
            for(y = boundaries.getMinY(); y<=boundaries.getMaxY(); y++ ){
                if(Math.random()<probability  ){
                    if( w.getBiome(x,y).contains(biome)){
                        result.add(new Coordinate(x,y));
                    }

                }
            }

        }
        return result;
    }

    private static Coordinate runBottomLeft(long x, long y, String biome, Plane w) {
        while (    w.getBiome(x-1,y-1).equals(biome) ){
            x--;
            y--;
        }
        return new Coordinate(x,y);
    }

    private static Coordinate runTopLeft(long x, long y, String biome, Plane w) {
        while (    w.getBiome(x-1,y+1).equals(biome) ){
            x--;
            y++;
        }
        return new Coordinate(x,y);
    }

    private static Coordinate runBottomRight(long x, long y, String biome, Plane w) {
        while (    w.getBiome(x+1,y-1).equals(biome) ){
            x++;
            y--;
        }
        return new Coordinate(x,y);
    }

    public static BiomeBoundaries getBoundariesHeuristic(long x, long y, Plane w){

        String biome =w.getBiome(x,y);
        long currentX=x;
        if(biome.equals("unkown")){
            throw new RuntimeException("This should not happen");
        }

        Coordinate rightX=runRight(x,y,biome,w);

        Coordinate leftX=runLeft(x,y,biome,w);

        long middleX= (leftX.x+rightX.x)/2;

        Coordinate bottomY= runDown(middleX,y,biome,w);

        Coordinate topY=runUp(middleX,y,biome,w);
        long middleY=(topY.y+bottomY.y)/2;

        return  getBiomeBoundaries(middleX,middleY,w);

//        return new BiomeBoundaries(x-20,y+20,x+20, -20,w.getBiome())
    }



    private static Coordinate runUp(long x, long y, String biome, Plane w) {
        while (    w.getBiome(x,y+1).equals(biome) ){

            y++;
        }
        return new Coordinate(x,y);
    }

    private static Coordinate runDown(long x, long y, String biome, Plane w) {

        while (    w.getBiome(x,y-1).equals(biome) ){

            y--;
        }
        return new Coordinate(x,y);

    }

    private static Coordinate runLeft(long x, long y, String biome, Plane w) {
        while (   w.getBiome(x-1,y).equals(biome) ){
            x--;
        }
        return new Coordinate(x,y);
    }

    private static Coordinate runRight(long x, long y, String biome, Plane w) {

        while (    w.getBiome(x+1,y).equals(biome) ){
            x++;
        }
        return new Coordinate(x,y);
    }

    private static Coordinate runTopRight(long x, long y, String biome, Plane w){
        while (    w.getBiome(x+1,y+1).equals(biome) ){
            x++;
            y++;
        }
        return new Coordinate(x,y);
    }


    public static class Coordinate{
        long x,y;
        public Coordinate(long x, long y){
            this.x=x;
            this.y=y;
        }

        @Override
        public String toString() {
            return "Coordinate{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}
