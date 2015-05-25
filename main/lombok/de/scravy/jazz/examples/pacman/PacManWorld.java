package de.scravy.jazz.examples.pacman;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import de.scravy.jazz.Direction;
import de.scravy.jazz.Picture;
import de.scravy.jazz.pictures.mutable.Pictures;

public class PacManWorld {

  double mouthOpening = 0;
  double posPacManX = 0;
  double posPacManY = 0;
  double pacManRotate = 0;
  double speedX = 1;
  double speedY = 0;
  int width;
  int height;

  char[][] level;
  Picture levelP = new Pictures();

  double worldRotation;

  public PacManWorld() throws IOException {
    InputStream in = PacManWorld.class.getResourceAsStream("level0.txt");
    List<String> lines = new ArrayList<String>(36);
    BufferedReader r = new BufferedReader(new InputStreamReader(in));
    String line;
    while ((line = r.readLine()) != null) {
      lines.add(line);
    }
    level = new char[lines.size()][];
    for (int i = 0; i < lines.size(); i++) {
      level[i] = lines.get(i).toCharArray();
    }
  }

  public void setDirection(Direction dir) {
    switch (dir) {
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
    }
  }
}
