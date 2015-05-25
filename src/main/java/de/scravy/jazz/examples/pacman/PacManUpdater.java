package de.scravy.jazz.examples.pacman;

import de.scravy.jazz.UpdateHandler;

public class PacManUpdater implements UpdateHandler<PacManWorld> {

  @Override
  public PacManWorld update(final PacManWorld w, final double time,
      final double delta) {

    w.worldRotation = time;
    w.mouthOpening = Math.abs(30 * Math.sin(time * 5));

    w.posPacManX += w.speedX * delta * 100;
    w.posPacManY += w.speedY * delta * 100;

    if (w.posPacManX > w.width / 2) {
      w.posPacManX -= w.width;
    }
    if (w.posPacManX < w.width / -2) {
      w.posPacManX += w.width;
    }
    if (w.posPacManY > w.height / 2) {
      w.posPacManY -= w.height;
    }
    if (w.posPacManY < w.height / -2) {
      w.posPacManY += w.height;
    }

    return w;
  }

}
