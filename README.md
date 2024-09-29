# Problem with coins:
There is a number of coins - n

n is an integer, can be both even and odd.
1 of the coins is counterfeit.

The counterfeit is the same as the regular coin, the only difference is the weight.

The weight of the counterfeit coin is 1. 
The weight of the regular coin is 2.

It is required to find the counterfeit coin using only scales and the coins.

## So, I made 3 options for solving,
### First:
If the number of coins is not even - we take 1 coin into the buffer, divide the remaining coins into 2 groups, and weigh them. If the scales on the left go down, then the counterfeit coin is on the right. If the scales on the right go down, then the counterfeit coin is on the left
in these cases, we take the lighter pile and repeat until the scales show balance, or we get to comparing the 2 remaining coins.
If the scales show balance, then the coin we put down is counterfeit.

### Second:
If the number of coins is not even and it is more than 4 - we take 3 coins, If the number is not even and less than 5, then we take only 1 coin.
If the number is even - we take 2 coins each.
If the scales on the left go down - then the counterfeit coin is in the right pile, if the scales on the right go down - then the counterfeit coin is in the left pile.
We repeat until the scales show balance, or we get to comparing the 2 remaining coins.
If the scales show balance - the coin is in the pile that we put aside, if there is 1 coin in the pile, then it is counterfeit, if there are 2 coins in the pile, we compare them, the one that is lighter is counterfeit. If there are 3 coins in the pile, we take 2 and compare them, if the coin on the left or right is lighter, then it is counterfeit, if the scales show balance, then the remaining coin is counterfeit.

### Third:
we take 2 coins and compare them.
we repeat this until we find a fake, or until we reach 1 coin (if the number of coins was not even) and this means that this coin is fake.


## Conclusion:
### After writing the code, I came to the conclusion that:
### In the case of an example of *71 coins*:
For small calculations, the best algorithm in terms of time is **the third**, it will require *35 weighings*, but will be executed faster than others because it will use variables.

**The first** algorithm uses *6 weighings*, and will be slightly faster than the third, but almost 3 times slower than the third algorithm.

**The second** algorithm will be the longest of the proposed ones, slightly longer than the second. but it will require only *5 weighings*.

### In the case of an example of *10,000 coins*:

**The third** algorithm will be much longer than the others (almost 3 times longer than the first) and will require as many as *5,000 weighings*.

**The second** will show the best result both in time and in weighings.
it will need to *weigh 12 times*.

**The first** algorithm will take about 2 times longer than the second one, and will require *13 weighings*.
