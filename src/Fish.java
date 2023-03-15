public class Fish extends Location implements Runnable{

    int moves=0;
    int fishnum=0;
    public Fish(int AquariumSize, int moves , int fishnum) {
        super(AquariumSize);
        this.moves=moves;
        this.fishnum=fishnum;
    }
    @Override
    public void run() {
        for(int i=0;i< moves;i++){
            if(i==0)
                System.out.println(" Initial location of Fish " + fishnum + " Location is "+this.x + " , "+ this.y);

            this.setNextLocation(x, y);

            System.out.println(" Location of Fish " + fishnum + " Move number is "+ i + " , new location is "+this.x + " , " + this.y);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
