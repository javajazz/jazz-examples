package de.scravy.jazz.examples;

import de.scravy.jazz.Event;
import de.scravy.jazz.Jazz;
import de.scravy.jazz.Picture;
import de.scravy.jazz.World;
import de.scravy.jazz.pictures.mutable.Pictures;
import de.scravy.jazz.pictures.mutable.Pie;

public class Arcs {

  public static void main(String... args) {
    Jazz.play(
        "Woohoo", 800, 600, new World() {

          double mouthOpening = 0;
          double posPacManX = 0;
          double posPacManY = 0;
          double pacManRotate = 0;
          double speedX = 1;
          double speedY = 0;

          @Override
          public void update(double time, double delta) {
            mouthOpening = Math.abs(30 * Math.sin(time * 5));

            posPacManX += speedX * delta * 50;
            posPacManY += speedY * delta * 50;
          }

          @Override
          public Picture getPicture() {
            return new Pictures(
                new Pie(200, 200,
                    pacManRotate + mouthOpening,
                    pacManRotate + 360 - mouthOpening)
                    .translate(posPacManX, posPacManY)
                    .filled(true)
            );
          }

          @Override
          public void on(Event e) {
            switch (e.getType()) {
            case KEY_DOWN:
              switch (e.getKey()) {
              case DOWN:
                speedX = 0;
                speedY = -1;
                pacManRotate = -90;
                break;
              case UP:
                speedX = 0;
                speedY = 1;
                pacManRotate = 90;
                break;
              case LEFT:
                speedX = -1;
                speedY = 0;
                pacManRotate = 180;
                break;
              case RIGHT:
                speedX = 1;
                speedY = 0;
                pacManRotate = 0;
                break;
              default:
                break;
              }
              break;
            default:
              break;
            }
          }
        }).onClose(new Runnable() {
      @Override
      public void run() {
        System.exit(0);
      }
    }).maxFps(120);
  }

}
