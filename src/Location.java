public class Location {

    public int x;

    public int y;


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        Location other = (Location) obj;
        if (x != other.x)
            return false;
        if (y != other.y)
            return false;
        return true;
    }
    public Location(int Size) {
        setRandomLocation(Size);
        synchronized (Aquarium.location_list) {
            Aquarium.location_list.add(this);
        }
    }
    public Location() {

    }
    public void setRandomLocation(int Size){

        int location_exist_ind=0 ;

        while(location_exist_ind==0){

            this.x = (int)(Math.random() * Size);

            this.y = (int)(Math.random() * Size);

            location_exist_ind=checkLocation(this);
        }
    }
    public int checkLocation(Location obj){
        int temp_ind=0;
        synchronized (Aquarium.location_list) {

            if(Aquarium.location_list.size()!=0)
                for(Location loc1 : Aquarium.location_list )
                {
                    if(obj.equals(loc1)){
                        System.out.println("This location" + obj.x
                                + " , "
                                +obj.y+ " is already taken by another fish , so generating the random location again.");
                        temp_ind=0;
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        break;
                    }
                    else temp_ind=1;
                }
            else temp_ind=1;
        }
        return temp_ind;
    }

    public void setNextLocation(int x,int y){

        int X_location = 0;
        int Y_location = 0;

        int location_exist_ind=0 ;

        while(location_exist_ind==0){

            X_location= Direction_X.getRandom_direction_X(x);

            Y_location= Direction_Y.getRandom_direction_Y(y);

            Location temp_loc= new Location();

            temp_loc.setX(X_location);

            temp_loc.setY(Y_location);

            location_exist_ind=checkLocation(temp_loc);

        }
        this.setX(X_location);
        this.setY(Y_location);

    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
}
