package main.java.engine.logic;

import main.java.engine.graphics.Tile;

import java.util.*;

public class PathFinder {
    private ArrayList<Tile> tiles;

    public PathFinder(ArrayList<Tile> tiles) {
        this.tiles = tiles;
    }

    public LinkedList<Tile> findPath(Tile start, Tile goal) {
        HashMap<Tile, Tile> cameFrom = new HashMap<>();
        HashMap<Tile, Double> costSoFar = new HashMap<>();
        PriorityQueue<Tile> frontier = new PriorityQueue<>(Comparator.comparingDouble(t -> heuristic(goal, t) + costSoFar.getOrDefault(t, Double.MAX_VALUE)));

        frontier.add(start);
        cameFrom.put(start, null);
        costSoFar.put(start, 0.0);

        while (!frontier.isEmpty()) {
            Tile current = frontier.poll();

            if (current.equals(goal)) {
                break;
            }

            for (Tile next : getNeighbors(current)) {
                double newCost = costSoFar.get(current) + 1;
                if (!costSoFar.containsKey(next) || newCost < costSoFar.get(next)) {
                    costSoFar.put(next, newCost);
                    double priority = newCost + heuristic(goal, next);
                    frontier.add(next);
                    cameFrom.put(next, current);
                }
            }
        }

        LinkedList<Tile> path = new LinkedList<>();
        Tile current = goal;
        while (current != null) {
            path.addFirst(current);
            current = cameFrom.get(current);
        }

        return path;
    }

    private double heuristic(Tile a, Tile b) {
        return Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY());
    }

    private ArrayList<Tile> getNeighbors(Tile tile) {
        ArrayList<Tile> neighbors = new ArrayList<>();
        for (Tile t : tiles) {
            if (Math.abs(t.getX() - tile.getX()) + Math.abs(t.getY() - tile.getY()) == 1) {
                neighbors.add(t);
            }
        }
        return neighbors;
    }
}