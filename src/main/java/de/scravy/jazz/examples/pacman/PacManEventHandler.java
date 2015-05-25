package de.scravy.jazz.examples.pacman;

import de.scravy.jazz.Direction;
import de.scravy.jazz.Event;
import de.scravy.jazz.EventHandler;

public class PacManEventHandler implements EventHandler<PacManWorld> {

  @Override
  public PacManWorld on(final PacManWorld w, final Event e) {
    switch (e.getType()) {

    case WINDOW_OPENED:
      w.width = e.getWindow().width();
      w.height = e.getWindow().height();
      w.levelP = PacManRenderer.renderLevel(w);
      break;

    case KEY_DOWN:
      switch (e.getKey()) {
      case UP:
        w.setDirection(Direction.UP);
        break;
      case DOWN:
        w.setDirection(Direction.DOWN);
        break;
      case LEFT:
        w.setDirection(Direction.LEFT);
        break;
      case RIGHT:
        w.setDirection(Direction.RIGHT);
        break;
      default:
        break;
      }
      break;

    default:
      break;
    }
    return w;
  }

}
