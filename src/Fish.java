public class Fish implements Runnable {

    private int lifeSpan = 0;
    private String fishName = "";
    private Location location;

    private Gender gender;

    public Fish() {
    }

    public Fish(int AquariumSize, int lifeSpan, String fishName, Gender gender) {
        this.lifeSpan = lifeSpan;
        this.fishName = fishName;
        this.gender = gender;
        setRandomLocation(AquariumSize);
        synchronized (Aquarium.fishList) {
            Aquarium.fishList.add(this);
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < lifeSpan; i++) {
            if (i == 0)
                System.out.println(" Initial location of Fish and its gender " + fishName + ":" + gender.name() + " Location is " + this.getLocation().getX() + " , " + this.getLocation().getY());

            this.setNextLocation(this.getLocation().getX(), this.getLocation().getY());

            System.out.println(" Move number is " + (i + 1) + ". New location of " + fishName + " is " + this.getLocation());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setRandomLocation(int Size) {

        int location_exist_ind = 0;

        while (location_exist_ind == 0) {
            Location loc = new Location();
            loc.setX((int) (Math.random() * Size));
            loc.setY((int) (Math.random() * Size));
            this.setLocation(loc);

            location_exist_ind = checkLocation(this);
        }
    }

    public int checkLocation(Fish obj) {
        int temp_ind = 0;
        synchronized (Aquarium.fishList) {

            if (Aquarium.fishList.size() != 0)
                for (Fish fish : Aquarium.fishList) {
                    if (obj.getLocation().equals(fish.getLocation())) {
                        System.out.println(fish + " and " + obj + " met at " + obj.getLocation());
                        if(fish.getGender() != obj.getGender()){
                            Fish child = new Fish();
                            child.setFishName("Child of " + fish.getFishName());
                            Location child_loc = new Location();
                            child_loc.setX(Direction_X.getRandom_direction_X(fish.getLocation().getX()));
                            child_loc.setY(Direction_Y.getRandom_direction_Y(fish.getLocation().getY()));
                            child.setLocation(child_loc);
                            child.setGender(Gender.getRandomGender((int) (fish.getLifeSpan() * Math.random())));
                            Aquarium.fishList.add(child);
                            System.out.println("Now they have child: " + child);
                        }
                        temp_ind = 0;
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        break;
                    } else temp_ind = 1;
                }
            else temp_ind = 1;
        }
        return temp_ind;
    }

    public void setNextLocation(int x, int y) {

        int X_location = 0;
        int Y_location = 0;

        int location_exist_ind = 0;
        Location temp_loc = new Location();

        while (location_exist_ind == 0) {

            X_location = Direction_X.getRandom_direction_X(x);

            Y_location = Direction_Y.getRandom_direction_Y(y);

            Fish fish = new Fish();

            temp_loc.setX(X_location);

            temp_loc.setY(Y_location);
            fish.setLocation(temp_loc);

            location_exist_ind = checkLocation(fish);

        }
        this.setLocation(temp_loc);

    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getLifeSpan() {
        return lifeSpan;
    }

    public void setLifeSpan(int lifeSpan) {
        this.lifeSpan = lifeSpan;
    }

    public String getFishName() {
        return fishName;
    }

    public void setFishName(String fishName) {
        this.fishName = fishName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return fishName + " : " + gender;
    }
}