package ai.scintillia.commands;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;


public class MeteoritePlacerTest {


    @Test
    public void oneBlockAntarcticAtOrigin() {

        TestWorld w =new TestWorld();
        w.setBiome(0,0,"Antarctic" );
    BiomeBoundaries result = MeteoritePlacer.getBoundariesHeuristic(0,0,w);

    BiomeBoundaries expected = new BiomeBoundaries(0,0,0,0, "Antarctic");

        Assert.assertEquals(expected,result);


    }

    @Test
    public void oneBlockAntarcticNotAtOrigin() {

        TestWorld w =new TestWorld();
        w.setBiome(1,0,"Antarctic" );
        BiomeBoundaries result = MeteoritePlacer.getBoundariesHeuristic(1,0, w);

        BiomeBoundaries expected = new BiomeBoundaries(1,0,1,0, "Antarctic");

        Assert.assertEquals(expected,result);


    }


    @Test
    public void oneBlockOceanNotAtOrigin() {


        TestWorld w =new TestWorld();
        w.setBiome(1,0,"Ocean" );


        BiomeBoundaries result = MeteoritePlacer.getBoundariesHeuristic(1,0, w);

        BiomeBoundaries expected = new BiomeBoundaries(1,0,1,0, "Ocean");

        Assert.assertEquals(expected,result);


    }

    @Test
    public void twoBlockAntarctic(){

        TestWorld w =new TestWorld();
        w.setBiome(0,0,"Antarctic" );
        w.setBiome(1,0,"Antarctic");

        BiomeBoundaries result = MeteoritePlacer.getBoundariesHeuristic(0,0,w);

        BiomeBoundaries expected = new BiomeBoundaries(0,0,1,0, "Antarctic");

        Assert.assertEquals(expected,result);

    }

    @Test
    public void twoBlockOcean(){

        TestWorld w =new TestWorld();
        w.setBiome(0,0,"Antarctic" );
        w.setBiome(1,0,"Ocean");

        BiomeBoundaries result = MeteoritePlacer.getBoundariesHeuristic(0,0,w);

        BiomeBoundaries expected = new BiomeBoundaries(0,0,0,0, "Antarctic");

        Assert.assertEquals(expected,result);

    }

    @Test
    public void twoBlockAntarcticLeft(){

        TestWorld w =new TestWorld();
        w.setBiome(5,5,"Antarctic" );
        w.setBiome(6,5,"Antarctic");
        w.setBiome(4,5,"Antarctic");

        BiomeBoundaries result = MeteoritePlacer.getBoundariesHeuristic(5,5,w);

        BiomeBoundaries expected = new BiomeBoundaries(4,5,6,5, "Antarctic");

        Assert.assertEquals(expected,result);

    }

    @Test
    public void threeBlockAntarcticUp(){

        TestWorld w =new TestWorld();
        w.setBiome(5,5,"Antarctic" );
        w.setBiome(5,6,"Antarctic");
        w.setBiome(5,7,"Antarctic");

        BiomeBoundaries result = MeteoritePlacer.getBoundariesHeuristic(5,6,w);

        BiomeBoundaries expected = new BiomeBoundaries(5,5,5,7, "Antarctic");

        Assert.assertEquals(expected,result);

    }
    @Test
    public void rectangleBiome(){

        TestWorld w =new TestWorld();
        w.setBiome(-5,7,10,2,"Antarctic" );


        BiomeBoundaries result = MeteoritePlacer.getBoundariesHeuristic(0,5,w);

        BiomeBoundaries expected = new BiomeBoundaries(-5,7,10, 2, "Antarctic");

        Assert.assertEquals(expected,result);


    }

    @Test
    public void irregularBiomeSizeRight(){

        TestWorld w =new TestWorld();
        w.setBiome(0,10,10,0,"Antarctic" );
        w.setBiome(-1,5,"Antarctic" );


        BiomeBoundaries result = MeteoritePlacer.getBoundariesHeuristic(-1 ,5,w);

        BiomeBoundaries expected = new BiomeBoundaries(-1,10,10, 0, "Antarctic");

        Assert.assertEquals(expected,result);


    }

    @Test
    public void irregularBiomeSizeLeft(){

        TestWorld w =new TestWorld();
        w.setBiome(0,10,10,0,"Antarctic" );
        w.setBiome(-1,5,"Antarctic" );


        BiomeBoundaries result = MeteoritePlacer.getBoundariesHeuristic(-1 ,5,w);

        BiomeBoundaries expected = new BiomeBoundaries(-1,10,10, 0, "Antarctic");

        Assert.assertEquals(expected,result);


    }

    @Test
    public void irregularBiomeSize2(){

        TestWorld w =new TestWorld();
        w.setBiome(0,10,10,0,"Antarctic" );
        w.setBiome(5,-1,"Antarctic" );


        BiomeBoundaries result = MeteoritePlacer.getBoundariesHeuristic(5 ,-1,w);

        BiomeBoundaries expected = new BiomeBoundaries(0,10,10, -1, "Antarctic");

        Assert.assertEquals(expected,result);


    }
    @Test
    public void integrationTest(){
        TestWorld w =new TestWorld();
        w.setBiome(0,100,100,0,"Antarctic");
        List<MeteoritePlacer.Coordinate> coordinates=MeteoritePlacer.placeMeteorites(50,50,"Antarctic", w);

        System.out.println(coordinates.toString());

    }

    class TestWorld implements Plane {

    HashMap<TestWorldCoordinate,String> biomeMap = new HashMap<TestWorldCoordinate, String>();

        @Override
        public String getBiome(long x, long y) {
            if(biomeMap.get(new TestWorldCoordinate(x,y)) == null){
                return "unkown";
            }
            return biomeMap.get(new TestWorldCoordinate(x,y) );
        }

        public void setBiome(long topLeftX, long topLeftY, long bottomRightX, long bottomRightY, String biome){
            for(long x=topLeftX; x<=bottomRightX;x++  ){
                for( long y=bottomRightY; y<=topLeftY; y++){
                    biomeMap.put(new TestWorldCoordinate(x,y), biome);
                }
            }
        }

        public void setBiome(long x, long y, String s){

            biomeMap.put(new TestWorldCoordinate(x,y), s );

        }

        class TestWorldCoordinate{
            long x;
            long y;

            public TestWorldCoordinate(long x, long y){
                this.x=x;
                this.y=y;

            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                TestWorldCoordinate that = (TestWorldCoordinate) o;
                return x == that.x &&
                        y == that.y;
            }

            @Override
            public int hashCode() {
                return Objects.hash(x, y);
            }
        }

    }




}