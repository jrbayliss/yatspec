

Formatting:
Make the formatting better. Requests formatting is too big
```
Given
When
Then 
[] [] []
```

Button to replay the scenario

Button to copy the curl command

Spring backend as a proxy. Replay scenarios. Run with setup, run without.

```
@Test("Should add 2 numbers")
public void test1() {
    Assertions.assertTrue(
      2 == 3, 
      () -> "Numbers " + 2 + " and " + 3 + " are not equal!");
}

```

add dummies for anyString()

annotate the model for data validation and boundary tests

upgrade to junit5

Evaluate https://truth.dev/