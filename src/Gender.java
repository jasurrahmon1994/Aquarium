public enum Gender {
    MALE,
    FEMALE;

    public static Gender getRandomGender(int index){
        return (int) (Math.random() * index) % 2 == 0 ? Gender.FEMALE : Gender.MALE;
    }
}
