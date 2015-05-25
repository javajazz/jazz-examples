package de.scravy.jazz.examples;

import de.scravy.jazz.Animation;
import de.scravy.jazz.Colors;
import de.scravy.jazz.Jazz;
import de.scravy.jazz.Picture;
import de.scravy.jazz.pictures.mutable.Pictures;
import de.scravy.jazz.pictures.mutable.Rectangle;

public class Funky extends Animation {

  public static void main(final String... args) {
    Jazz.animate("Funky town", 1000, 600, new Funky());
  }

  private Pictures pictures = new Pictures();

  public Funky() {
    final Rectangle r = new Rectangle(50, 50).filled(true);

    for (int i = -525; i <= 525; i += 50) {
      for (int j = -325; j <= 325; j += 50) {
        this.pictures
            .add(r.clone().color(Colors.randomColor()).translate(i, j));
      }
    }
  }

  @Override
  public void update(final double time, final double delta) {
    for (final Picture p : this.pictures) {
      p.color(Colors.randomColor(-1, 0, 0));
    }
    this.pictures.reset()
        .scale(2 * Math.sin(time), 2 * Math.sin(time))
        .rotate(time * 100);
  }

  @Override
  public Picture getPicture() {
    return this.pictures;
  }

}
