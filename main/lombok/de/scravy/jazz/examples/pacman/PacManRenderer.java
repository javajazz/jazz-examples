package de.scravy.jazz.examples.pacman;

import de.scravy.jazz.Color;
import de.scravy.jazz.Picture;
import de.scravy.jazz.Renderer;
import de.scravy.jazz.pictures.mutable.Pictures;
import de.scravy.jazz.pictures.mutable.Pie;
import de.scravy.jazz.pictures.mutable.Square;

public class PacManRenderer implements Renderer<PacManWorld> {

  static Picture renderLevel(PacManWorld w) {
    Pictures p = new Pictures();

    double height = w.height / (double) w.level.length;
    double width = w.width / (double) w.level[0].length;

    double side = Math.min(height, width);

    double offsetX = side * w.level[0].length / 2.0;
    double offsetY = side * w.level.length / 2.0;

    for (int i = 0; i < w.level.length; i++) {
      for (int j = 0; j < w.level[i].length; j++) {
        Square s = new Square(side).translate(j * side,
            (w.level.length - i) * side);
        switch (w.level[i][j]) {
        case ' ':
          s.color(Color.WHITE);
          break;
        case '.':
          s.color(Color.YELLOW);
          break;
        case 'o':
          s.color(Color.RED);
          break;
        case '#':
          s.color(Color.BLACK);
          break;
        case '-':
        case '|':
        case '/':
        case '\\':
          s.color(Color.BLUE);
          break;
        }
        p.add(s.filled(true));
      }
    }

    return p.translate(-offsetX - side / 2.0, -offsetY - side / 2.0)
        .rotate(0);
  }

  @Override
  public Picture render(PacManWorld w) {
    return new Pictures(
        w.levelP.remove().rotate(w.worldRotation),
        new Pie(200, 200,
            w.pacManRotate + w.mouthOpening,
            w.pacManRotate + 360 - w.mouthOpening)
            .translate(w.posPacManX, w.posPacManY)
            .filled(true));
  }

}
