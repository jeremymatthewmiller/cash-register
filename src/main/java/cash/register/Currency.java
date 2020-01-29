package cash.register;

public enum Currency {
  TWENTY(20),
  TEN(10),
  FIVE(5),
  TWO(2),
  ONE(1);

  public final int value;

  Currency(int value) {
    this.value = value;
  }
}
