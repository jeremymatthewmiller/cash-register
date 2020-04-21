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

  public static Currency fromInt(final int value) {
    for (Currency c : Currency.values()) {
      if (c.value == value) {
        return c;
      }
    }
    return null;
  }
}
