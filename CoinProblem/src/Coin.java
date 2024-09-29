public class Coin extends Object {
    private boolean isFake = false;

    public Coin(int weight) {
        setWeight(weight);
    }

    public boolean isFake() {
        return isFake;
    }

    public void setFake(boolean fake) {
        isFake = fake;
    }
}
