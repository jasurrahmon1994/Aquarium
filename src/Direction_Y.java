
public enum Direction_Y {

    UP(1),
    NONE(0),
    DOWN(-1);

    public int direction_Y_ind;

    Direction_Y(int Y)
    {
        direction_Y_ind = Y;
    }

    public static int getRandom_direction_Y(int Y)
    {
        if(Y!=0 && Y != Aquarium.size-1)
        {
            Y = Y + values()[ (int) (Math.random() * (values().length))].direction_Y_ind;
        }
        else if(Y >= Aquarium.size-1)
        {
            Y = Y + values()[ (int) (Math.random() * (values().length-1)) + 1 ].direction_Y_ind;
        }
        else
        {
            Y = Y + values()[ (int) (Math.random() * (values().length-1))].direction_Y_ind;
        }
        return (Y);
    }
}
