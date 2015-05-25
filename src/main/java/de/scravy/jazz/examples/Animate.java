package de.scravy.jazz.examples;

import de.scravy.jazz.Animation;
import de.scravy.jazz.Jazz;
import de.scravy.jazz.Picture;
import de.scravy.jazz.pictures.mutable.Pictures;
import de.scravy.jazz.pictures.mutable.Rectangle;

public class Animate {

  public static void main(final String... args) {
    Jazz.animate(
        "Simple Animation",
        1000, 600,
        new Animation() {

          private final Pictures pictures = new Pictures(
              new Rectangle(200, 200).color(0, 0, 0, 0.5)
                  .filled(true).rotate(0),
              new Rectangle(200, 200).color(0, 0, 0, 0.5)
                  .filled(true).rotate(0)
              );

          @Override
          public void update(final double time, final double delta) {
            pictures.get(0).remove().rotate(time * 40);
            pictures.get(1).remove().rotate(time * -40);
          }

          @Override
          public Picture getPicture() {
            return pictures;
          }
        })
        .onClose(new Runnable() {
          @Override
          public void run() {
            System.exit(0);
          }
        })
        .antiAlias(true);
    ;
  }
}
