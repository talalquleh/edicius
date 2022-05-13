package src.tiles;

import src.game.Game;
import src.helpers.IntVector2D;
//import helpers.Vector2i;
import src.state.State;
import src.worlds.GameMap;

import java.util.*;

public class Node {
    public IntVector2D tile;
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

    public Node(IntVector2D tile, Node nodeParent, double gCost, double hCost) {
        this.tile = tile;
        this.nodeParent = nodeParent;
        this.gCost = gCost;
        this.hCost = hCost;
        this.fCost = this.gCost + this.hCost;
    }
    public String toString() { return this.tile.toString(); }

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

    public static List<Node> findPath(IntVector2D start, IntVector2D goal, State state) {
        Set<Node> openList = new HashSet<Node>();
        Map<IntVector2D, Node> mapToNode = new HashMap<IntVector2D, Node>();
        Node current = new Node(start, null, 0, IntVector2D.getOctileDistance(start, goal));
        mapToNode.put(start, current);
        openList.add(current);
        int[][] tiles = state.getGameMap().getTilesValues();
        System.out.println("searching for a path from " + start + " to " + goal);
        while (openList.size() > 0) {
            //sort tiles from closest to further
            current = Collections.min(openList, sortNodes);
            if (current.tile.equals(goal)) {
                //construct the path
                List<Node> path = new ArrayList<Node>();
                //till we reach the start we will add the parent
                while (current.nodeParent != null) {
                    path.add(current);
                    current = current.nodeParent;
                }
                openList.clear();
                return path;
            }
            openList.remove(current);
            //excluding walls from the adjacency list all walls , and the ones we have visited
           for (IntVector2D at : current.tile.getNeighbors(tiles.length, tiles[0].length)) {
                if (tiles[at.getX()][at.getY()] != 0) continue;
                double gCost = current.gCost + 1;
                double hCost = gCost + IntVector2D.getOctileDistance(at, goal);
                Node node;
                if (!mapToNode.containsKey(at)) {
                    node = new Node(at, current, gCost, hCost);
                    mapToNode.put(at, node);
                } else {
                    node = mapToNode.get(at);
                    if (gCost < node.gCost) {
                        node.nodeParent = current;
                        node.gCost = gCost;
                        node.hCost = hCost;
                    } else continue;
                }
                if (!openList.contains(node)) openList.add(node);
            }

        }
        return null;

    }

    public static boolean vecInList(List<Node> list, IntVector2D vec) {
        for (Node n : list) {
            if (n.tile.equals(vec)) return true;
        }
        return false;
    }

    public int hashCode() {
        return this.tile.hashCode();
    }
    public boolean equals(Object other) {
        if (other == null || !(other instanceof Node)) return false;
        return this.tile.equals(((Node)other).tile);
    }


}


