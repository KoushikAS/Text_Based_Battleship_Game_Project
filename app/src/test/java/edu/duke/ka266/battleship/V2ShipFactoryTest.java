package edu.duke.ka266.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class V2ShipFactoryTest {

  @Test
  public void test_makeSubmarine() {

    Placement p1 = new Placement(new Coordinate(1, 1), 'o');
    V2ShipFactory v2 = new V2ShipFactory();
    assertThrows(IllegalArgumentException.class, () -> v2.makeSubmarine(p1));

    Placement p2 = new Placement(new Coordinate(1, 1), 'v');
    Ship<Character> s1 = v2.makeSubmarine(p2);
    assertEquals(s1.getName(), "Submarine");
    for (int i = 1; i <= 2; i++) {
      assertTrue(s1.occupiesCoordinates(new Coordinate(i, 1)));
      assertEquals(s1.getDisplayInfoAt(new Coordinate(i, 1), true), 's');
    }

    Placement p3 = new Placement(new Coordinate(1, 1), 'h');
    Ship<Character> s2 = v2.makeSubmarine(p3);
    for (int i = 1; i <= 2; i++) {
      assertTrue(s2.occupiesCoordinates(new Coordinate(1, i)));
      assertEquals(s2.getDisplayInfoAt(new Coordinate(1, i), true), 's');
    }
  }

  @Test
  public void test_makeDestroyer() {

    Placement p1 = new Placement(new Coordinate(1, 1), 'o');
    V2ShipFactory v2 = new V2ShipFactory();
    assertThrows(IllegalArgumentException.class, () -> v2.makeDestroyer(p1));

    Placement p2 = new Placement(new Coordinate(1, 1), 'v');
    Ship<Character> s1 = v2.makeDestroyer(p2);
    assertEquals(s1.getName(), "Destroyer");
    for (int i = 1; i <= 2; i++) {
      assertTrue(s1.occupiesCoordinates(new Coordinate(i, 1)));
      assertEquals(s1.getDisplayInfoAt(new Coordinate(i, 1), true), 'd');
    }

    Placement p3 = new Placement(new Coordinate(1, 1), 'h');
    Ship<Character> s2 = v2.makeDestroyer(p3);
    for (int i = 1; i <= 2; i++) {
      assertTrue(s2.occupiesCoordinates(new Coordinate(1, i)));
      assertEquals(s2.getDisplayInfoAt(new Coordinate(1, i), true), 'd');
    }
  }

  @Test
  public void test_makeBattlesShip() {

    Placement p1 = new Placement(new Coordinate(1, 1), 'o');
    V2ShipFactory v2 = new V2ShipFactory();
    assertThrows(IllegalArgumentException.class, () -> v2.makeBattleship(p1));

    Placement p2 = new Placement(new Coordinate(1, 1), 'u');
    Ship<Character> s1 = v2.makeBattleship(p2);
    assertEquals(s1.getName(), "BattleShip");

    // Test Upper
    p2 = new Placement(new Coordinate(1, 1), 'u');
    s1 = v2.makeBattleship(p2);
    assertEquals(s1.getDisplayInfoAt(new Coordinate(1, 2), true), 'b');
    assertEquals(s1.getDisplayInfoAt(new Coordinate(2, 1), true), 'b');
    assertEquals(s1.getDisplayInfoAt(new Coordinate(2, 2), true), 'b');
    assertEquals(s1.getDisplayInfoAt(new Coordinate(2, 3), true), 'b');

    // Test Right
    p2 = new Placement(new Coordinate(1, 1), 'r');
    s1 = v2.makeBattleship(p2);
    assertEquals(s1.getDisplayInfoAt(new Coordinate(2, 2), true), 'b');
    assertEquals(s1.getDisplayInfoAt(new Coordinate(1, 1), true), 'b');
    assertEquals(s1.getDisplayInfoAt(new Coordinate(2, 1), true), 'b');
    assertEquals(s1.getDisplayInfoAt(new Coordinate(3, 1), true), 'b');

    // Test Down
    p2 = new Placement(new Coordinate(1, 1), 'd');
    s1 = v2.makeBattleship(p2);

    assertEquals(s1.getDisplayInfoAt(new Coordinate(2, 2), true), 'b');
    assertEquals(s1.getDisplayInfoAt(new Coordinate(1, 1), true), 'b');
    assertEquals(s1.getDisplayInfoAt(new Coordinate(1, 2), true), 'b');
    assertEquals(s1.getDisplayInfoAt(new Coordinate(1, 3), true), 'b');

    // Test Left

    p2 = new Placement(new Coordinate(1, 1), 'l');
    s1 = v2.makeBattleship(p2);

    assertEquals(s1.getDisplayInfoAt(new Coordinate(2, 1), true), 'b');
    assertEquals(s1.getDisplayInfoAt(new Coordinate(1, 2), true), 'b');
    assertEquals(s1.getDisplayInfoAt(new Coordinate(2, 2), true), 'b');
    assertEquals(s1.getDisplayInfoAt(new Coordinate(3, 2), true), 'b');
  }

  @Test
  public void test_makeCarrier() {

    Placement p1 = new Placement(new Coordinate(1, 1), 'o');
    V2ShipFactory v2 = new V2ShipFactory();
    assertThrows(IllegalArgumentException.class, () -> v2.makeCarrier(p1));

    Placement p2 = new Placement(new Coordinate(1, 1), 'u');
    Ship<Character> s1 = v2.makeCarrier(p2);
    assertEquals(s1.getName(), "Carrier");

    // Test Upper
    p2 = new Placement(new Coordinate(1, 1), 'u');
    s1 = v2.makeCarrier(p2);
    assertEquals(s1.getDisplayInfoAt(new Coordinate(1, 1), true), 'c');
    assertEquals(s1.getDisplayInfoAt(new Coordinate(2, 1), true), 'c');
    assertEquals(s1.getDisplayInfoAt(new Coordinate(3, 1), true), 'c');
    assertEquals(s1.getDisplayInfoAt(new Coordinate(4, 1), true), 'c');
    assertEquals(s1.getDisplayInfoAt(new Coordinate(3, 2), true), 'c');
    assertEquals(s1.getDisplayInfoAt(new Coordinate(4, 2), true), 'c');
    assertEquals(s1.getDisplayInfoAt(new Coordinate(5, 2), true), 'c');

    // Test Right
    p2 = new Placement(new Coordinate(1, 1), 'r');
    s1 = v2.makeCarrier(p2);
    assertEquals(s1.getDisplayInfoAt(new Coordinate(1, 2), true), 'c');
    assertEquals(s1.getDisplayInfoAt(new Coordinate(1, 3), true), 'c');
    assertEquals(s1.getDisplayInfoAt(new Coordinate(1, 4), true), 'c');
    assertEquals(s1.getDisplayInfoAt(new Coordinate(1, 5), true), 'c');
    assertEquals(s1.getDisplayInfoAt(new Coordinate(2, 1), true), 'c');
    assertEquals(s1.getDisplayInfoAt(new Coordinate(2, 2), true), 'c');
    assertEquals(s1.getDisplayInfoAt(new Coordinate(2, 3), true), 'c');

    // Test Down
    p2 = new Placement(new Coordinate(1, 1), 'd');
    s1 = v2.makeCarrier(p2);

    assertEquals(s1.getDisplayInfoAt(new Coordinate(1, 1), true), 'c');
    assertEquals(s1.getDisplayInfoAt(new Coordinate(2, 1), true), 'c');
    assertEquals(s1.getDisplayInfoAt(new Coordinate(3, 1), true), 'c');
    assertEquals(s1.getDisplayInfoAt(new Coordinate(2, 2), true), 'c');
    assertEquals(s1.getDisplayInfoAt(new Coordinate(3, 2), true), 'c');
    assertEquals(s1.getDisplayInfoAt(new Coordinate(4, 2), true), 'c');
    assertEquals(s1.getDisplayInfoAt(new Coordinate(5, 2), true), 'c');

    // Test Left

    p2 = new Placement(new Coordinate(1, 1), 'l');
    s1 = v2.makeCarrier(p2);

    assertEquals(s1.getDisplayInfoAt(new Coordinate(1, 3), true), 'c');
    assertEquals(s1.getDisplayInfoAt(new Coordinate(1, 4), true), 'c');
    assertEquals(s1.getDisplayInfoAt(new Coordinate(1, 5), true), 'c');
    assertEquals(s1.getDisplayInfoAt(new Coordinate(2, 1), true), 'c');
    assertEquals(s1.getDisplayInfoAt(new Coordinate(2, 2), true), 'c');
    assertEquals(s1.getDisplayInfoAt(new Coordinate(2, 3), true), 'c');
    assertEquals(s1.getDisplayInfoAt(new Coordinate(2, 4), true), 'c');

  }

}
