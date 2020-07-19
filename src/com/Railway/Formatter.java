package com.Railway;

import com.Railway.graph.Edge;
import com.Railway.graph.PriceWeight;
import com.Railway.graph.TimeWeight;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public final class Formatter {

    private Formatter(){

    }

    public static String formatResult(List<Edge> path) {
        double totalPrice = 0;
        Duration totalDuration = Duration.ZERO;
        List<String> lines = new ArrayList<>();

        Edge prevEdge = null;
        PriceWeight priceWeight = new PriceWeight();
        TimeWeight timeWeight = new TimeWeight();

        for (Edge edge : path) {
            totalPrice += priceWeight.getWeight(edge);
            totalDuration = totalDuration.plus(timeWeight.getWeight(edge));

            if (prevEdge != null) {
                Duration waitingTime = Duration.between(prevEdge.getEndTime(), edge.getStartTime());

                if (waitingTime.compareTo(Duration.ZERO) < 0) {
                    waitingTime = waitingTime.plusDays(1);
                }

                if (waitingTime.compareTo(Duration.of(1, ChronoUnit.MINUTES)) >= 0) {
                    lines.add("Waiting at " + edge.getFromNode() + "\t" + formatDuration(waitingTime));
                }
            }

            lines.add(formatEdge(edge));
            prevEdge = edge;
        }

        lines.add("");
        lines.add(String.format("Total time: %s\t Total Price: %.2f RON", formatDuration(totalDuration), totalPrice));
        return "<html>" + String.join("<br/>", lines) + "</html>";
    }

    private static String formatDuration(Duration duration) {
        return String.format("%dh %02dm",
                duration.toHours(),
                duration.toMinutesPart());
    }

    private static String formatEdge(Edge edge) {
        return edge.getFromNode().getCityName() + "-->" + edge.getToNode().getCityName() +
                "\t" + edge.getStartTime() + "-" + edge.getEndTime() +
                "\t" + edge.getPrice() + " RON";
    }
}
