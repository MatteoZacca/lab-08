package pcd.lab08.actors.value_exchange_problem.centralyzed_solution;

import akka.actor.ActorRef;

public interface CentralyzedSolutionProtocol {
    // worker sends this to itself (just to generate its random value)
    public record BootMsg(String name, int numWorkers, ActorRef central) {};

    // woker → CentralActor: "here is my randomly-picked value"
    public record ReportValue(int value, int numWorkers, ActorRef workerRef) {};

}
