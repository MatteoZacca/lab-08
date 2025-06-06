package pcd.lab08.actors.value_exchange_problem.centralyzed_solution;

import akka.actor.*;
import pcd.lab08.actors.value_exchange_problem.centralyzed_solution.CentralyzedSolutionProtocol.*;

import java.util.Random;

public class WorkerActor extends AbstractActor {
    private int actorValue;
    private ActorRef central;

    public Receive createReceive() {
        return receiveBuilder()
                .match(BootMsg.class, this::onBootMsg)
                .build();
    }

    private void onBootMsg(BootMsg msg) {
        // save the reference to the central actor
        this.central = msg.central();
        // generate our random integer
        var gen = new Random();
        actorValue = gen.nextInt(1000);

        log("[" + msg.name() + "] booted with value: " + actorValue);

        // send that value to the CentralActor
        central.tell(new ReportValue(actorValue, msg.numWorkers(), getSelf()), getSelf());
    }

    private static void log (String msg) {
        System.out.println(msg);
    }
}
