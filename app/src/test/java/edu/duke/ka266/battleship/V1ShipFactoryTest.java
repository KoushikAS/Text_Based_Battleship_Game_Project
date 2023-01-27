package edu.duke.ka266.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class V1ShipFactoryTest {

  @Test
  public void test_makeSubmarine() {

    Placement p1 = new Placement(new Coordinate(1, 1), 'o');
    V1ShipFactory v1 = new V1ShipFactory();
    assertThrows(IllegalArgumentException.class, () -> v1.makeSubmarine(p1));

    Placement p2 = new Placement(new Coordinate(1, 1), 'v');
    Ship<Character> s1 = v1.makeSubmarine(p2);
    assertEquals(s1.getName(), "Submarine");
    for (int i = 1; i <= 2; i++) {
      assertTrue(s1.occupiesCoordinates(new Coordinate(i, 1)));
      assertEquals(s1.getDisplayInfoAt(new Coordinate(i,1)), 's');
    }

    Placement p3 = new Placement(new Coordinate(1, 1), 'h');
    Ship<Character> s2 = v1.makeSubmarine(p3);
    for (int i = 1; i <= 2; i++) {
      assertTrue(s2.occupiesCoordinates(new Coordinate(1, i)));
      assertEquals(s2.getDisplayInfoAt(new Coordinate(1,i)), 's');
    }
  }

@Test
  public void test_makeBattleShip() {

    Placement p1 = new Placement(new Coordinate(1, 1), 'o');
    V1ShipFactory v1 = new V1ShipFactory();
    assertThrows(IllegalArgumentException.class, () -> v1.makeBattleship(p1));

    Placement p2 = new Placement(new Coordinate(1, 1), 'v');
    Ship<Character> s1 = v1.makeBattleship(p2);
    assertEquals(s1.getName(), "BattleShip");
    for (int i = 1; i <= 2; i++) {
      assertTrue(s1.occupiesCoordinates(new Coordinate(i, 1)));
      assertEquals(s1.getDisplayInfoAt(new Coordinate(i,1)), 'b');
    }

    Placement p3 = new Placement(new Coordinate(1, 1), 'h');
    Ship<Character> s2 = v1.makeBattleship(p3);
    for (int i = 1; i <= 2; i++) {
      assertTrue(s2.occupiesCoordinates(new Coordinate(1, i)));
      assertEquals(s2.getDisplayInfoAt(new Coordinate(1,i)), 'b');
    }
  }

  
@Test
  public void test_makeCarrier() {

    Placement p1 = new Placement(new Coordinate(1, 1), 'o');
    V1ShipFactory v1 = new V1ShipFactory();
    assertThrows(IllegalArgumentException.class, () -> v1.makeCarrier(p1));

    Placement p2 = new Placement(new Coordinate(1, 1), 'v');
    Ship<Character> s1 = v1.makeCarrier(p2);
    assertEquals(s1.getName(), "Carrier");
    for (int i = 1; i <= 2; i++) {
      assertTrue(s1.occupiesCoordinates(new Coordinate(i, 1)));
      assertEquals(s1.getDisplayInfoAt(new Coordinate(i,1)), 'c');
    }

    Placement p3 = new Placement(new Coordinate(1, 1), 'h');
    Ship<Character> s2 = v1.makeCarrier(p3);
    for (int i = 1; i <= 2; i++) {
      assertTrue(s2.occupiesCoordinates(new Coordinate(1, i)));
      assertEquals(s2.getDisplayInfoAt(new Coordinate(1,i)), 'c');
    }
  }

  
@Test
  public void test_makeDestroyer() {

    Placement p1 = new Placement(new Coordinate(1, 1), 'o');
    V1ShipFactory v1 = new V1ShipFactory();
    assertThrows(IllegalArgumentException.class, () -> v1.makeDestroyer(p1));

    Placement p2 = new Placement(new Coordinate(1, 1), 'v');
    Ship<Character> s1 = v1.makeDestroyer(p2);
    assertEquals(s1.getName(), "Destroyer");
    for (int i = 1; i <= 2; i++) {
      assertTrue(s1.occupiesCoordinates(new Coordinate(i, 1)));
      assertEquals(s1.getDisplayInfoAt(new Coordinate(i,1)), 'd');
    }

    Placement p3 = new Placement(new Coordinate(1, 1), 'h');
    Ship<Character> s2 = v1.makeDestroyer(p3);
    for (int i = 1; i <= 2; i++) {
      assertTrue(s2.occupiesCoordinates(new Coordinate(1, i)));
      assertEquals(s2.getDisplayInfoAt(new Coordinate(1,i)), 'd');
    }
  }

  
}
