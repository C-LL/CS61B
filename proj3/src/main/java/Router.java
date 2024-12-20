import edu.princeton.cs.algs4.MinPQ;
import org.junit.Test;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class provides a shortestPath method for finding routes between two points
 * on the map. Start by using Dijkstra's, and if your code isn't fast enough for your
 * satisfaction (or the autograder), upgrade your implementation by switching it to A*.
 * Your code will probably not be fast enough to pass the autograder unless you use A*.
 * The difference between A* and Dijkstra's is only a couple of lines of code, and boils
 * down to the priority you use to order your vertices.
 */
public class Router {
    /**
     * Return a List of longs representing the shortest path from the node
     * closest to a start location and the node closest to the destination
     * location.
     * @param g The graph to use.
     * @param stlon The longitude of the start location.
     * @param stlat The latitude of the start location.
     * @param destlon The longitude of the destination location.
     * @param destlat The latitude of the destination location.
     * @return A list of node id's in the order visited on the shortest path.
     */
    public static List<Long> shortestPath(GraphDB g, double stlon, double stlat,
                                          double destlon, double destlat) {
        Long source = g.closest(stlon, stlat);
        Long destination = g.closest(destlon, destlat);
        List<Long> path = new LinkedList<>();

        for(Long v : g.vertices()){
            g.visited.put(v, false);
            g.dist.put(v, Double.MAX_VALUE);
        }
        g.hDist.put(source, 0.0);
        MinPQ<Long> minheap = new MinPQ<>(Comparator.comparingDouble((Long id) -> g.hDist.get(id)));
//        MinPQ<Long> minheap = new MinPQ<>(Comparator.comparingDouble((Long id) ->
//                                  GraphDB.distance(stlon, stlat, g.nodes.get(id).lon, g.nodes.get(id).lat)
//                                + greatCricleDistance(destlon, destlat, g.nodes.get(id).lon, g.nodes.get(id).lat)));
        minheap.insert(source);

        boolean targetFound = false;
        while(!targetFound){
            Long current = minheap.delMin();
            path.add(current);
            g.visited.put(current, true);
            if(Objects.equals(current, destination)){
                targetFound = true;
                return path;
            }
            for(Long id : g.adjacent(current)){
                if(!g.visited.get(id)){
                    g.visited.put(id, true);
                    g.dist.put(id, g.dist.get(current) + g.distance(current, id));
                    g.hDist.put(id, g.dist.get(id) + greatCricleDistance(g.nodes.get(id).lon, g.nodes.get(id).lat,
                                                                    destlon, destlat));
                    minheap.insert(id);
                }
            }
        }
        return path; // FIXME
    }

    /* Calculate the great circle distance between two points on earth
     * @param lon1 The longitude of the start location.
     * @param lat1 The latitude of the start location.
     * @param lon2 The longitude of the destination location.
     * @param lat2 The latitude of the destination location.
     * @return the heuristic distance(great-circle distance).
     */
    public static double greatCricleDistance(double lon1, double lat1, double lon2, double lat2){
        double R = 3963;
        double deltaLon = (lon2 - lon1) * Math.PI / 180;
        double deltaLat = (lat2 - lat1) * Math.PI / 180;
        double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2) +
                Math.cos(lat1 * Math.PI / 180) * Math.cos(lat2 * Math.PI / 180) *
                        Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }

    /**
     * Create the list of directions corresponding to a route on the graph.
     * @param g The graph to use.
     * @param route The route to translate into directions. Each element
     *              corresponds to a node from the graph in the route.
     * @return A list of NavigatiionDirection objects corresponding to the input
     * route.
     */
    public static List<NavigationDirection> routeDirections(GraphDB g, List<Long> route) {
        List<NavigationDirection> directions = new LinkedList<>();
        Long scource = route.get(0);
        NavigationDirection start = new NavigationDirection();
        start.direction = NavigationDirection.START;
        start.way = g.ways.get(scource).name;
        start.distance = 0.0;
        directions.add(start);

        for(Long id : route){
            if(route.contains(id)){
                continue;
            }
            double alpha = 180 / Math.PI * Math.atan2(g.nodes.get(id).lon, g.nodes.get(id).lat);
            NavigationDirection curId = new NavigationDirection();
            curId.way = g.ways.get(id).name;
            curId.distance = g.dist.get(id);
            curId.direction = directionHelpter(alpha);
            directions.add(curId);
        }
        return directions; // FIXME
    }
    public static int directionHelpter(double alpha){
        if(alpha > -15 && alpha < 15){
            return 1;
        }else if(alpha > -30 && alpha <= -15){
            return 2;
        }else if(alpha >= 15 && alpha < 30){
            return 3;
        }else if(alpha > -100 && alpha <= -30){
            return 4;
        }else if(alpha >= 30 && alpha < 100){
            return 5;
        }else if(alpha <= -100){
            return 6;
        }else{
            return 7;
        }
    }

    /**
     * Class to represent a navigation direction, which consists of 3 attributes:
     * a direction to go, a way, and the distance to travel for.
     */
    public static class NavigationDirection {

        /** Integer constants representing directions. */
        public static final int START = 0;
        public static final int STRAIGHT = 1;
        public static final int SLIGHT_LEFT = 2;
        public static final int SLIGHT_RIGHT = 3;
        public static final int RIGHT = 4;
        public static final int LEFT = 5;
        public static final int SHARP_LEFT = 6;
        public static final int SHARP_RIGHT = 7;

        /** Number of directions supported. */
        public static final int NUM_DIRECTIONS = 8;

        /** A mapping of integer values to directions.*/
        public static final String[] DIRECTIONS = new String[NUM_DIRECTIONS];

        /** Default name for an unknown way. */
        public static final String UNKNOWN_ROAD = "unknown road";
        
        /** Static initializer. */
        static {
            DIRECTIONS[START] = "Start";
            DIRECTIONS[STRAIGHT] = "Go straight";
            DIRECTIONS[SLIGHT_LEFT] = "Slight left";
            DIRECTIONS[SLIGHT_RIGHT] = "Slight right";
            DIRECTIONS[LEFT] = "Turn left";
            DIRECTIONS[RIGHT] = "Turn right";
            DIRECTIONS[SHARP_LEFT] = "Sharp left";
            DIRECTIONS[SHARP_RIGHT] = "Sharp right";
        }

        /** The direction a given NavigationDirection represents.*/
        int direction;
        /** The name of the way I represent. */
        String way;
        /** The distance along this way I represent. */
        double distance;

        /**
         * Create a default, anonymous NavigationDirection.
         */
        public NavigationDirection() {
            this.direction = STRAIGHT;
            this.way = UNKNOWN_ROAD;
            this.distance = 0.0;
        }

        public String toString() {
            return String.format("%s on %s and continue for %.3f miles.",
                    DIRECTIONS[direction], way, distance);
        }

        /**
         * Takes the string representation of a navigation direction and converts it into
         * a Navigation Direction object.
         * @param dirAsString The string representation of the NavigationDirection.
         * @return A NavigationDirection object representing the input string.
         */
        public static NavigationDirection fromString(String dirAsString) {
            String regex = "([a-zA-Z\\s]+) on ([\\w\\s]*) and continue for ([0-9\\.]+) miles\\.";
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(dirAsString);
            NavigationDirection nd = new NavigationDirection();
            if (m.matches()) {
                String direction = m.group(1);
                if (direction.equals("Start")) {
                    nd.direction = NavigationDirection.START;
                } else if (direction.equals("Go straight")) {
                    nd.direction = NavigationDirection.STRAIGHT;
                } else if (direction.equals("Slight left")) {
                    nd.direction = NavigationDirection.SLIGHT_LEFT;
                } else if (direction.equals("Slight right")) {
                    nd.direction = NavigationDirection.SLIGHT_RIGHT;
                } else if (direction.equals("Turn right")) {
                    nd.direction = NavigationDirection.RIGHT;
                } else if (direction.equals("Turn left")) {
                    nd.direction = NavigationDirection.LEFT;
                } else if (direction.equals("Sharp left")) {
                    nd.direction = NavigationDirection.SHARP_LEFT;
                } else if (direction.equals("Sharp right")) {
                    nd.direction = NavigationDirection.SHARP_RIGHT;
                } else {
                    return null;
                }

                nd.way = m.group(2);
                try {
                    nd.distance = Double.parseDouble(m.group(3));
                } catch (NumberFormatException e) {
                    return null;
                }
                return nd;
            } else {
                // not a valid nd
                return null;
            }
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof NavigationDirection) {
                return direction == ((NavigationDirection) o).direction
                    && way.equals(((NavigationDirection) o).way)
                    && distance == ((NavigationDirection) o).distance;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(direction, way, distance);
        }
    }
}
