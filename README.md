# Cash Register
This application is the implementation of a cash register. This cash register will accept bills in denominations of $1, $2, $5, $10, and $20s.
When given a charge and amount of payment, the cash register will calculate and return the required change.
When exact change cannot be made, the user will be notified.
The cash register will provide implementation for the following features:

1. **`show`**       output the current number of each denomination in the register in format
                    $<total> <# of 20’s> <# of 10’s> <# of 5’s> <# of 2’s> <# of 1’s>
1. **`put`**     adds some number of each denomination from the register, then print the
                    current state. Format of output from show command
1. **`take`**       removes some number of each denomination from the register, then print the
                    current state. Format of output from show command.
1. **`change`**     Makes change for some amount of money. Output should be denominations of
                    change for the value asked in format <# of 20’s> <# of 10’s> <# of 5’s> <# of
                    2’s> <# of 1’s>, e.g. 0 0 4 0 0. Changing should also deduct the resulting
                    denominations from the register.

## Required Software
1.  [Gradle](https://gradle.org/install/)
1.	[Lombok](https://projectlombok.org/setup/overview)

## Building with Gradle
To start the application:
```shell script
./gradlew run
```

To run the unit tests:
```shell script
./gradlew test
```

## Example Usage
Following are some examples of how the commands of the program will be used.

After starting the program and waiting for a command:
```shell script
ready
```

Show the current state of the cash register with the total and each denomination.
Assume that the cash register was initialized with
1 $20 bill, 2 $10 bills, 3 $5 bills, 4 $2 bills, and 5 $1 bills
Output format = $Total $20's $10's $5's $2's $1's
Total=$68 $20x1 $10x2 $5x3 $2x4 $1x5
```shell script
> show
$68 1 2 3 4 5
```

Put bills in each denomination in: $20's $10's $5's $2's $1's
then show the current state
```shell script
> put 1 2 3 0 5
$128 2 4 6 4 10
```

Take bills in each denomination out: $20's $10's $5's $2's $1's
then show the current state
```shell script
> take 1 4 3 0 10
$43 1 0 3 4 0
```

Make change for a given amount by showing the number of each denomination the
vendor needs to return: $20's $10's $5's $2's $1's and remove money
from the cash register
```shell script
> change 11
0 0 1 3 0
```

If there is not enough funds in the register or no change can be made, show an error
```shell script
> change 14
sorry
```

To exit the program
```shell script
> quit
Bye
```