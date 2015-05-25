package de.scravy.jazz.examples;

import java.awt.geom.AffineTransform;
import java.io.IOException;

import de.scravy.jazz.Animation;
import de.scravy.jazz.Color;
import de.scravy.jazz.Jazz;
import de.scravy.jazz.Picture;
import de.scravy.jazz.pictures.mutable.Bitmap;
import de.scravy.jazz.pictures.mutable.Pictures;
import de.scravy.jazz.pictures.mutable.Rectangle;

public class Images {

  public static void main(String... args) throws IOException {

    Jazz.animate(
        "On this page you see a little girl giggling at a Hippopotamus",
        1400, 900, new Animation() {

          Bitmap bitmap = new Bitmap(Images.class, "hippo.png")
              .scale(0.5, 0.5);
          AffineTransform s = bitmap.getTransform();
          double x[] = { 400, 400, -400, -400, 400, -400, 0, 0, 0 };
          double y[] = { 300, -300, 300, -300, 0, 0, 300, -300, 0 };
          AffineTransform t[] = new AffineTransform[x.length];
          Pictures pictures = new Pictures(new Rectangle(1400, 900)
              .color(Color.BLACK).filled(true));

          {
            for (int i = 0; i < t.length; i++) {
              Bitmap b = bitmap.clone().reset()
                  .translate(x[i], y[i]);
              t[i] = b.getTransform();
              pictures.add(b);
            }
          }

          @Override
          public void update(double time, double delta) {
            for (int i = 0; i < t.length; i++) {
              pictures.get(i + 1).reset().transform(s)
                  .rotate(time * (i + 1) * Math.pow(-1, i))
                  .transform(t[i]);
            }
          }

          @Override
          public Picture getPicture() {
            return pictures;
          }

        }).maxFps(80);

  }
}
