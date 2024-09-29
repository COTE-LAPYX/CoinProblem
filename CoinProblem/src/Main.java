import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //#region coin creation
        List<Coin> coins = new ArrayList<>();
        int totalCoinCount = 71;

        for (int i = 0; i < totalCoinCount; i++) {
            coins.add(new Coin(2));

            if (i == totalCoinCount - 2) {
                Coin fakeCoin = new Coin(1);
                fakeCoin.setFake(true);
                coins.add(fakeCoin);
                break;
            }
        }
        //#endregion

        System.out.println("Total coins: " + coins.size());

        //CALIBRATION to get true time measurements results
        firstSolution(coins);
        secondSolution(coins);
        thirdSolution(coins);

        //RESULTS
        System.out.println(firstSolution(coins)); // 71 - 1 = 70; 70 : 2 = 35; 35 - 1 = 34; 34 : 2 = 17; 17 - 1 = 16; 16 / 2 = 8; 8 : 2 = 4; 4 : 2 = 2; 2 : 2 = 1;  TOTAL = 6;
        System.out.println(secondSolution(coins)); // 71 - 3 = 68; 68 / 2 = 34; 34 - 2 = 32; 32 / 2 = 16; 16 - 2 = 14; 14 / 2 = 7; 7 - 3 = 4; 4 / 2 = 2; 2 / 2; TOTAL = 5
        System.out.println(thirdSolution(coins)); // 71 - 1 = 70; 70 - 2 = 68; 68 - 2 = 66; ... ; TOTAL = 35;

        /*
        Conclusion:
        1st solution uses 6 weights for 71 coins, and it's the second fastest of these three.
        2nd solution uses 5 weights for 71 coins, and it's the slowest of these three.
        3rd solution uses 35 weights for 71 coins, and it's the fastest of these three.

        The reason why 3rd solution is the fastest is that It isn't using the Lists, and just the variables. Even although it needs 35 weights for 71 coins.
        The reason why 1st solution is the second fastest is that It is using the Lists and variables, but, It also needs just 6 weights for 71 coins.
        The reason why 2nd solution is the slowest fastest is that It is using just the Lists, but, It needs only 5 weights for 71 coins.
         */
    }

    private static String firstSolution(List<Coin> coins) {
        long start = System.nanoTime();

        Weigher weigher = new Weigher();
        List<Object> coinListTotal = new ArrayList<>(coins);
        List<Object> coinListLeft;
        List<Object> coinListRight;
        Coin coinBuffer = null;

        boolean foundFakeCoin = false;
        int weightTimes = 0;

        while (!foundFakeCoin) {
            coinListLeft = new ArrayList<>(coinListTotal.subList(0, coinListTotal.size() / 2));
            coinListRight = new ArrayList<>(coinListTotal.subList(coinListTotal.size() / 2, coinListTotal.size()));

            if ((coinListTotal.size() % 2) != 0) {
                coinBuffer = (Coin) coinListRight.remove(0);
            }

            switch (weigher.weight(coinListLeft, coinListRight)) {
                case RIGHT -> {
                    if (coinListLeft.size() == 1) {
                        Coin coin = (Coin) coinListLeft.get(0);
                        if (coin.isFake()) {
                            foundFakeCoin = true;
                        }
                    } else {
                        coinListTotal = new ArrayList<>(coinListLeft);
                    }
                }
                case LEFT -> {
                    if (coinListRight.size() == 1) {
                        Coin coin = (Coin) coinListRight.get(0);
                        if (coin.isFake()) {
                            foundFakeCoin = true;
                        }
                    } else {
                        coinListTotal = new ArrayList<>(coinListRight);
                    }
                }
                case EQUALS -> {
                    if (coinBuffer != null && coinBuffer.isFake()) {
                        foundFakeCoin = true;
                    }
                }
            }

            weightTimes++;
        }

        long end = System.nanoTime();
        return "1st solution requires " + weightTimes + " weights and " + (end - start) + " nano seconds";
    }

    private static String secondSolution(List<Coin> coins) {
        long start = System.nanoTime();

        Weigher weigher = new Weigher();
        List<Object> coinListTotal = new ArrayList<>(coins);
        List<Object> coinListLeft;
        List<Object> coinListRight;
        List<Object> coinListBuffer;

        boolean foundFakeCoin = false;
        int weightTimes = 0;

        while (!foundFakeCoin) {
            coinListBuffer = new ArrayList<>();

            if ((coinListTotal.size() % 2) != 0) {
                if (coinListTotal.size() > 4) {
                    coinListBuffer.add(coinListTotal.remove(0));
                    coinListBuffer.add(coinListTotal.remove(0));
                    coinListBuffer.add(coinListTotal.remove(0));
                } else {
                    coinListBuffer.add(coinListTotal.remove(0));
                }
            } else if (coinListTotal.size() > 3) {
                coinListBuffer.add(coinListTotal.remove(0));
                coinListBuffer.add(coinListTotal.remove(0));
            }

            coinListLeft = new ArrayList<>(coinListTotal.subList(0, coinListTotal.size() / 2));
            coinListRight = new ArrayList<>(coinListTotal.subList(coinListTotal.size() / 2, coinListTotal.size()));

            switch (weigher.weight(coinListLeft, coinListRight)) {
                case RIGHT -> {
                    if (coinListLeft.size() == 1) {
                        Coin coin = (Coin) coinListLeft.get(0);
                        if (coin.isFake()) {
                            foundFakeCoin = true;
                        }
                    } else {
                        coinListTotal = new ArrayList<>(coinListLeft);
                    }
                }
                case LEFT -> {
                    if (coinListRight.size() == 1) {
                        Coin coin = (Coin) coinListRight.get(0);
                        if (coin.isFake()) {
                            foundFakeCoin = true;
                        }
                    } else {
                        coinListTotal = new ArrayList<>(coinListRight);
                    }
                }
                case EQUALS -> {
                    if (coinListBuffer.size() == 1) {
                        Coin coin = (Coin) coinListBuffer.get(0);
                        if (coin.isFake()) {
                            foundFakeCoin = true;
                        }
                    } else {
                        coinListTotal = new ArrayList<>(coinListBuffer);
                    }
                }
            }

            weightTimes++;
        }

        long end = System.nanoTime();
        return "2nd solution requires " + weightTimes + " weights and " + (end - start) + " nano seconds";
    }

    private static String thirdSolution(List<Coin> coins) {
        long start = System.nanoTime();

        Weigher weigher = new Weigher();
        List<Coin> coinListTotal = new ArrayList<>(coins);
        Coin coinLeft;
        Coin coinRight;
        Coin coinBuffer;

        boolean foundFakeCoin = false;
        int weightTimes = 0;

        while (!foundFakeCoin) {
            coinLeft = coinListTotal.remove(0);
            coinRight = coinListTotal.remove(0);

            switch (weigher.weight(coinLeft, coinRight)) {
                case RIGHT -> {
                    if (coinLeft.isFake()) {
                        foundFakeCoin = true;
                    }
                }
                case LEFT -> {
                    if (coinRight.isFake()) {
                        foundFakeCoin = true;
                    }
                }
                case EQUALS -> {
                    if (coinListTotal.size() == 1){
                        coinBuffer = coinListTotal.remove(0);

                        if (coinBuffer != null && coinBuffer.isFake()) {
                            foundFakeCoin = true;
                        }
                    }
                }
            }

            weightTimes++;
        }

        long end = System.nanoTime();
        return "3rd solution requires " + weightTimes + " weights and " + (end - start) + " nano seconds";
    }
}