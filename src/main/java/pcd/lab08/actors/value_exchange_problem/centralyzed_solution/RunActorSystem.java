package pcd.lab08.actors.value_exchange_problem.centralyzed_solution;

import akka.actor.*;
import pcd.lab08.actors.value_exchange_problem.centralyzed_solution.CentralyzedSolutionProtocol.*;

import java.util.ArrayList;
import java.util.List;

public class RunActorSystem {
    public static void main(String[] args) throws Exception{
        ActorSystem system = ActorSystem.create("actor-system");

        List<ActorRef> workers = new ArrayList<>();
        int numWorkers = 10;

        ActorRef centralActor = system.actorOf(Props.create(CentralActor.class), "master-actor");

        for (int i = 0; i < numWorkers; i++) {
            ActorRef worker = system.actorOf(Props.create(WorkerActor.class), "worker-" + i);
            workers.add(worker);
        }

        for (ActorRef worker: workers) {
            worker.tell(new BootMsg(worker.path().name(), numWorkers, centralActor), ActorRef.noSender());
        }

        Thread.sleep(3000);
        system.terminate();
    }
}
