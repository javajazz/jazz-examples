package de.scravy.jazz.examples.dw;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

import de.scravy.jazz.Color;
import de.scravy.jazz.Event;
import de.scravy.jazz.Jazz;
import de.scravy.jazz.Picture;
import de.scravy.jazz.World;
import de.scravy.jazz.Event.Type;
import de.scravy.jazz.grids.DefaultTileFactory;
import de.scravy.jazz.grids.HexagonGrid;
import de.scravy.jazz.grids.TileDecorator;
import de.scravy.jazz.grids.TileEventHandler;
import de.scravy.jazz.grids.TilePos;
import de.scravy.jazz.pictures.mutable.Pictures;

public class DWWorld extends World {

  private HexagonGrid<DWTile> grid;
  private final static List<Color> colors = new ArrayList<Color>();
  {
    colors.add(Color.PURPLE);
    colors.add(Color.OLIVE);
    colors.add(Color.ORANGE);
    colors.add(Color.BLUE);
    colors.add(Color.YELLOW);
    colors.add(Color.CYAN);
    colors.add(Color.BROWN);
    colors.add(Color.DARK_GREEN);
    colors.add(Color.AZURE);
  }

  public DWWorld() {

  }

  private void init(int numPlayers, int each) {

    grid = new HexagonGrid<DWTile>(

        new DefaultTileFactory<DWTile>(),

        new TileDecorator<DWTile>() {
          @Override
          public Picture decorate(DWTile tile, Picture picture) {
            if (tile == null) {
              return picture;
            }
            return picture.filled(true).color(tile.getColor());
          }
        },

        new TileEventHandler<DWTile>() {
          @Override
          public void on(Event ev, DWTile tile) {

          }
        },

        15, 30, 25);

    Deque<Color> colors = new ArrayDeque<>();

    for (int i = 0; i < numPlayers; i++) {
      colors.add(DWWorld.colors.get(i));
    }

    List<DWTile> tiles = new ArrayList<DWTile>(32);
    for (Color c : colors) {
      for (int j = 0; j < each; j++) {
        tiles.add(new DWTile(c));
      }
    }
    Jazz.shuffle(tiles);

    int n = grid.getWidth() * grid.getHeight();

    int countries = (int) Math.ceil(Math.sqrt(colors.size() * each));
    int w = grid.getWidth() / countries;
    int h = grid.getHeight() / countries;

    Iterator<DWTile> tilesIt = tiles.iterator();
    for (int i = 0; i < countries; i++) {
      for (int j = 0; j < countries; j++) {
        int x = i * w + Jazz.randomInt(w);
        int y = j * h + Jazz.randomInt(h);

        if (tilesIt.hasNext()) {
          grid.setTileAt(x, y, tilesIt.next());
          n--;
        }
      }
    }

    while (n > 0) {
      for (int i = 0; i < grid.getWidth(); i++) {
        for (int j = 0; j < grid.getHeight(); j++) {

          TilePos p = new TilePos(i, j);
          DWTile t = grid.getTileAt(p);

          if (t == null) {
            continue;
          }

          List<Integer> xs = new ArrayList<Integer>();
          for (int k = 0; k < 6; k++) {
            xs.add(k);
          }
          Jazz.shuffle(xs);

          neighbors: for (int x : xs) {
            switch (x) {
            case 0: {
              TilePos p2 = grid.right(p);
              if (p2 != null && grid.getTileAt(p2) == null) {
                grid.setTileAt(p2, t);
                n--;
                break neighbors;
              }
              break;
            }
            case 1: {
              TilePos p2 = grid.topRight(p);
              if (p2 != null && grid.getTileAt(p2) == null) {
                grid.setTileAt(p2, t);
                n--;
                break neighbors;
              }
              break;
            }
            case 2: {
              TilePos p2 = grid.topLeft(p);
              if (p2 != null && grid.getTileAt(p2) == null) {
                grid.setTileAt(p2, t);
                n--;
                break neighbors;
              }
              break;
            }
            case 3: {
              TilePos p2 = grid.left(p);
              if (p2 != null && grid.getTileAt(p2) == null) {
                grid.setTileAt(p2, t);
                n--;
                break neighbors;
              }
              break;
            }
            case 4: {
              TilePos p2 = grid.bottomLeft(p);
              if (p2 != null && grid.getTileAt(p2) == null) {
                grid.setTileAt(p2, t);
                n--;
                break neighbors;
              }
              break;
            }
            case 5: {
              TilePos p2 = grid.bottomRight(p);
              if (p2 != null && grid.getTileAt(p2) == null) {
                grid.setTileAt(p2, t);
                n--;
                break neighbors;
              }
              break;
            }
            }
          }
        }
      }
    }
  }

  @Override
  public Picture getPicture() {
    if (grid != null) {
      return grid.getPicture();
    }
    return new Pictures();
  }

  @Override
  public void update(double time, double delta) {

  }

  @Override
  public void on(Event ev) {
    if (ev.getType() == Type.CLICK) {
      init(8, 4);
    }
  }
}
