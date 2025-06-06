package pcd.lab08.actors.value_exchange_problem.centralyzed_solution;

import akka.actor.*;
import pcd.lab08.actors.value_exchange_problem.centralyzed_solution.CentralyzedSolutionProtocol.*;

import java.util.ArrayList;
import java.util.List;

public class CentralActor extends AbstractActor {
    private int expectedWorkers;
    private final List<Integer> allValues = new ArrayList<>();


    public Receive createReceive() {
        return receiveBuilder()
                .match(ReportValue.class, this::onReportValue)
                .build();
    }

    private void onReportValue(ReportValue msg) {
        this.expectedWorkers = msg.numWorkers();
        allValues.add(msg.value());

        if (allValues.size() == expectedWorkers) {
            int minValue = allValues.get(0);
            int maxValue = allValues.get(0);

            for (int value : allValues) {
                if (value < minValue) {
                    minValue = value;
                } else if (value > maxValue) {
                    maxValue = value;
                }
            }

            log("Done!");
            log("Min value: " + minValue);
            log("Max value: " + maxValue);
        }
    }

    private static void log (String print) {
        System.out.println("[CentralActor] ---> " + print);
    }
}
