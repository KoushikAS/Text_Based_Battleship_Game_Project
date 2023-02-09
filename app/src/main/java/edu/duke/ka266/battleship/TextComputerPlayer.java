package edu.duke.ka266.battleship;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.function.Function;

public class TextComputerPlayer extends TextPlayer {

  private int coordinateCounter;

  public TextComputerPlayer(String TextPlayer, Board<Character> theBoard, BufferedReader inputSource, PrintStream out,
      AbstractShipFactory<Character> shipFactory) {
    super(TextPlayer, theBoard, inputSource, out, shipFactory);
    this.coordinateCounter = 0;
  }

  public void doOnePlacement(Placement p, Function<Placement, Ship<Character>> createFn) {
    Ship<Character> s = createFn.apply(p);
    theBoard.tryAddShip(s);
  }

  @Override
  public void doPlacementPhase() throws IOException {

    doOnePlacement(new Placement("A0H"), (p) -> shipFactory.makeSubmarine(p));
    doOnePlacement(new Placement("A2H"), (p) -> shipFactory.makeSubmarine(p));
    doOnePlacement(new Placement("A6H"), (p) -> shipFactory.makeDestroyer(p));
    doOnePlacement(new Placement("B0H"), (p) -> shipFactory.makeDestroyer(p));
    doOnePlacement(new Placement("B6H"), (p) -> shipFactory.makeDestroyer(p));
    doOnePlacement(new Placement("C0U"), (p) -> shipFactory.makeBattleship(p));
    doOnePlacement(new Placement("E0D"), (p) -> shipFactory.makeBattleship(p));
    doOnePlacement(new Placement("C4D"), (p) -> shipFactory.makeBattleship(p));
    doOnePlacement(new Placement("I0r"), (p) -> shipFactory.makeCarrier(p));
    doOnePlacement(new Placement("o0l"), (p) -> shipFactory.makeCarrier(p));
  }

  @Override
  public void playOneTurn(Board<Character> enemyBoard, BoardTextView enemyView, String enemyName) throws IOException {

    out.print(this.view.displayMyOwnBoard());

  }
}
