public enum Direction_X {


    RIGHT(1),
    NONE(0),
    LEFT(-1);

    public int direction_X_ind;

    Direction_X(int X)
    {
        direction_X_ind = X;
    }

    public static int getRandom_direction_X(int x)
    {
        int X=x;

        if(X!=0 && X!=Aquarium.size-1)
        {
            X = X + values()[(int) (Math.random() * (values().length))].direction_X_ind;

        }
        else if(x>=Aquarium.size-1)
        {
            X = X + values()[ (int) (Math.random() * (values().length-1)) + 1 ].direction_X_ind;
        }
        else
        {
            X = X + values()[(int) (Math.random() * (values().length-1))].direction_X_ind;
        }
        return (X);
    }
}


