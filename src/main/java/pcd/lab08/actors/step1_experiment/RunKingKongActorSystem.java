package pcd.lab08.actors.step1_experiment;

import akka.actor.*;

public class RunKingKongActorSystem {
    public static void main(String[] args) {

        final ActorSystem system = ActorSystem.create("ping-pong-actor-system");
        log(system.toString());
        final ActorRef firstChild = system.actorOf(Props.create(FirstChild.class), "first-child");
        log(firstChild.path().toString());
        firstChild.tell(new FirstChild.ChildMsgWithoutArgument(), null);

    }

    private static void log (String print) {
        System.out.println("[" + Thread.currentThread().getName() + "] ---> log in RunKingKongActorSystem ---> " + print);
    }


}
