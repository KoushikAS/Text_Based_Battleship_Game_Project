package edu.duke.ka266.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PlacementTest {
  @Test
  public void test_equals() {
    Coordinate c1 = new Coordinate(1, 2);
    Coordinate c2 = new Coordinate(1, 2);
    Coordinate c3 = new Coordinate(1, 3);

    Placement p1 = new Placement(c1, 'v');
    Placement p2 = new Placement(c2, 'v');
    Placement p3 = new Placement("B2v");
    Placement p4 = new Placement(c3, 'a');
    Placement p5 = new Placement(c1, 'a');
    Placement p6 = new Placement(c3, 'v');

    assertEquals(p1, p1); // equals should be reflexsive
    assertEquals(p1, p2); // different objects bu same contents
    assertEquals(p1, p3); // different objects bu same contents
    assertNotEquals(p1, p4); // different contents
    assertNotEquals(p1, p5);
    assertNotEquals(p1, p6);
    assertNotEquals(p3, "B2v"); // different types
  }

  @Test
  public void test_hashCode() {
    Coordinate c1 = new Coordinate(1, 2);
    Coordinate c2 = new Coordinate(1, 2);
    Coordinate c3 = new Coordinate(0, 3);

    Placement p1 = new Placement(c1, 'v');
    Placement p2 = new Placement(c2, 'v');
    Placement p3 = new Placement("B2v");
    Placement p4 = new Placement(c3, 'a');
    Placement p5 = new Placement(c1, 'a');
    Placement p6 = new Placement(c3, 'v');

    assertEquals(p1.hashCode(), p2.hashCode());
    assertEquals(p1.hashCode(), p3.hashCode());
    assertNotEquals(p1.hashCode(), p4.hashCode());
    assertNotEquals(p1.hashCode(), p5.hashCode());
    assertNotEquals(p1.hashCode(), p6.hashCode());
  }

   @Test
  void test_string_constructor_valid_cases() {
    Coordinate c1 = new Coordinate("B3");
    Placement p1 = new Placement(c1, 'v');
    assertEquals('V', p1.getOrientation());
    assertEquals(c1, p1.getWhere());
    Placement p2 = new Placement("B3a");
    assertEquals(c1, p2.getWhere());
    assertEquals('A', p2.getOrientation());
    Placement p3 = new Placement("B3A");
    assertEquals(c1, p3.getWhere());
    assertEquals('A', p3.getOrientation());
      }

  
  @Test
  public void test_string_constructor_error_cases() {
    assertThrows(IllegalArgumentException.class, () -> new Placement("A00"));   
    assertThrows(IllegalArgumentException.class, () -> new Placement("A"));   
    assertThrows(IllegalArgumentException.class, () -> new Placement(null));
    assertThrows(IllegalArgumentException.class, () -> new Placement("A0@"));
    assertThrows(IllegalArgumentException.class, () -> new Placement("A0"));
    assertThrows(IllegalArgumentException.class, () -> new Placement("A0/"));
    assertThrows(IllegalArgumentException.class, () -> new Placement("A0:"));
    assertThrows(IllegalArgumentException.class, () -> new Placement("A0"));
    assertThrows(IllegalArgumentException.class, () -> new Placement("A"));
    }

}
