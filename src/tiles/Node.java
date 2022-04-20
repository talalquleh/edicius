package tiles;

import game.Game;
import helpers.Vector2D;
//import helpers.Vector2i;
import state.State;
import worlds.GameMap;

import java.util.*;

public class Node {
    public Vector2D tile;
    public Node nodeParent;

    public double fCost, gCost, hCost;
    /*
    initializing Node to represent a tile in our map,
    needed to implement our A* search Algorithm
    hCost-> is the heuristic path (straight line distance)
    gCost-> node to node cost
    fCost->combination of both
    note:lower cost doesn't necessarily mean shortest distance
    and our algorithm will take into consideration the cost if we give it enough details(further development).
     */

    public Node(Vector2D tile, Node nodeParent, double gCost, double hCost) {
        this.tile = tile;
        this.nodeParent = nodeParent;
        this.gCost = gCost;
        this.hCost = hCost;
        this.fCost = this.gCost + this.hCost;
    }

    /*
    comparing nodes' distances
     */
    private static Comparator<Node> sortNodes = new Comparator<Node>() {
        public int compare(Node n0, Node n1) {
            if (n1.fCost < n0.fCost) return +1;
            if (n1.fCost > n0.fCost) return -1;

            return 0;
        }

    };

    public static List<Node> findPath(Vector2D start, Vector2D goal, State state) {
        List<Node> openList = new ArrayList<Node>();
        List<Node> closedList = new ArrayList<Node>();
        Node current = new Node(start, null, 0, Vector2D.getDistance(start, goal));
        openList.add(current);
//        System.out.println("searching for a path");
        while (openList.size() > 0) {
            //sort tiles from closest to further
            Collections.sort(openList, sortNodes);
            current = openList.get(0);
            if (current.tile.equals(goal)) {
                //construct the path
                List<Node> path = new ArrayList<Node>();
                //till we reach the start we will add the parent
                while (current.nodeParent != null) {
                    path.add(current);
                    current = current.nodeParent;
                }
                openList.clear();
                closedList.clear();
                return path;
            }
            openList.remove(current);
            closedList.add(current);
            //excluding walls from the adjacency list all walls , and the ones we have visited
            Tile[][] tiles = state.getGameMap().getTiles();
            for (int i = 0; i < 9; i++) {
                if (i == 4) continue;
                //be careful when  casting between int and double !!
                int x = (int) (current.tile.getX() / Game.SPRITE_SIZE );
                int y = (int) (current.tile.getY() / Game.SPRITE_SIZE );
//                int xi = ((i % 3) - 1);
//                int yi = ((i / 3) - 1);
                System.out.println(x);
                System.out.println(y);
//                System.out.println(xi);
//                System.out.println(yi);
//                System.out.println(tiles[4][4])  Tile[][] tiles = state.getGameMap().getTiles();
//        Camera camera = state.getCamera();
//        startX = Math.max(0, startX);
//        startY = Math.max(0, startY);
//        endX = Math.min(tiles.length, endX);
//        endY = Math.min(tiles[0].length, endY);
//        for (int x = startX; x < endX; x++) {
//            for (int y = startY; y < endY; y++) {;
//                if (state.getMap("map1.txt")[x][y]== 1){
//                    tiles[x][y].isSolid=true ;
//                   System.out.println("ss");
//                }
                 Tile at= (tiles[x+xi][y+yi]);
                if(at==null) continue;
                if(at.isSolid) continue;
                Vector2D a = new Vector2D(x + xi, y + yi);
                double gCost = current.gCost + Vector2D.getDistance(current.tile, a);
                double hCost = Vector2D.getDistance(a, goal);
                Node node = new Node(a, current, gCost, hCost);
                if (vecInList(closedList, a) && gCost >= node.gCost) continue;
                if (!vecInList(openList, a) || gCost < node.gCost) openList.add(node);


            }

        }
        closedList.clear();
        return null;

    }

    public static boolean vecInList(List<Node> list, Vector2D vec) {
        for (Node n : list) {
            if (n.tile.equals(vec)) return true;
        }
        return false;
    }


}
