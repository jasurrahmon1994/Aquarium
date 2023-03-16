public class Fish implements Runnable {

    private int lifeSpan;
    private String fishName;
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
                System.out.println("Fish initialization: " + this);

            this.setNextLocation();

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
            Location loc = new Location((int) (Math.random() * Size), (int) (Math.random() * Size));
            this.setLocation(loc);

            location_exist_ind = checkLocation(this);
        }
    }

    public int checkLocation(Fish obj) {
        int temp_ind = 0;
        synchronized (Aquarium.fishList) {

            if (Aquarium.fishList.size() != 0)
                for (Fish fish : Aquarium.fishList) {
                    boolean locCheck = obj.getLocation().equals(fish.getLocation());
                    boolean nameCheck = obj.getFishName().equals(fish.getFishName());
                    boolean genderCheck = obj.getGender().equals(fish.getGender());
                    if (locCheck && !nameCheck && !genderCheck) {
                        Fish child = new Fish();
                        child.setFishName("Child of " + fish.getFishName() + " and " + obj.getFishName());
                        Location child_loc = new Location(Direction_X.getRandom_direction_X(fish.getLocation().getX()),
                                Direction_Y.getRandom_direction_Y(fish.getLocation().getY()));
                        child.setLocation(child_loc);
                        child.setGender(Gender.getRandomGender((int) (fish.getLifeSpan() * Math.random())));
                        Aquarium.childFishList.add(child);
                        System.out.println("Now " + fish + " || " + obj + " have child: " + child);
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

    public void setNextLocation() {

        int X_location = 0;
        int Y_location = 0;

        int location_exist_ind = 0;

        while (location_exist_ind == 0) {

            X_location = Direction_X.getRandom_direction_X(this.getLocation().getX());

            Y_location = Direction_Y.getRandom_direction_Y(this.getLocation().getY());
            Location tempLoc = new Location(X_location, Y_location);
            this.setLocation(tempLoc);
            location_exist_ind = checkLocation(this);

        }

    }
    @Override
    public String toString() {
        return fishName + " : " + gender + " : " + location;
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




}