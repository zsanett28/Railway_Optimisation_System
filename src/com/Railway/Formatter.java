package com.Railway;

import com.Railway.graph.Edge;
import com.Railway.graph.PriceWeight;
import com.Railway.graph.TimeWeight;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;

public final class Formatter {

    private Formatter(){

    }

    public static String formatResult(List<Edge> path) {
        double totalPrice = 0;
        Duration totalDuration = Duration.ZERO;
        StringBuilder result = new StringBuilder("<html><table>").append("<tr><th></th><th></th><th></th><th></th><th></th><th></th><th></th></tr>");

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
                    result.append("<tr><td colspan=\"5\"><i>").append("Waiting at ").append(edge.getFromNode()).append(": ").append(formatDuration(waitingTime)).append("</i></td></tr>");
                }
            }

            result.append(formatEdge(edge));
            prevEdge = edge;
        }

        result.append("</table><br>");
        result.append(String.format("<b>Total time:</b> %s<br/><b>Total Price:</b> %.2f RON", formatDuration(totalDuration), totalPrice));
        return result + "</html>";
    }

    private static String formatDuration(Duration duration) {
        return String.format("%dh %02dm",
                duration.toHours(),
                duration.toMinutesPart());
    }

    private static String formatEdge(Edge edge) {
        return "<tr>" +
                "<td>" + edge.getFromNode().getCityName() + "</td>" +
                "<td>" + "--->" + "</td>" +
                "<td>" + edge.getToNode().getCityName() + "</td>" +
                "<td>" + edge.getStartTime() + "</td>" +
                "<td>" + "-" + "</td>" +
                "<td>" + edge.getEndTime() + "</td>" +
                "<td>" + edge.getPrice() + " RON" + "</td>" +
                "</tr>";
    }
}
