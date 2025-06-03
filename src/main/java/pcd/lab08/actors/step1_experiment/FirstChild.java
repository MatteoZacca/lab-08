package pcd.lab08.actors.step1_experiment;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import pcd.lab08.actors.step1_experiment.KingerKongerProtocol.*;


public class FirstChild extends AbstractActor {

    public Receive createReceive() {
        return receiveBuilder()
                .match(ChildMsgWithoutArgument.class, this::onChildMsg)
                .build();
    }

    private void onChildMsg(ChildMsgWithoutArgument msg) {
        log("this.getContext(): " + this.getContext().toString());
        ActorRef kinger = this.getContext().actorOf(Props.create(KingerActor.class), "kinger-child");
        ActorRef konger = this.getContext().actorOf(Props.create(KongerActor.class), "konger-child");
        log("this.getSelf(): " + this.getSelf().toString());
        kinger.tell(new ChildMsg(konger), this.getSelf());
    }

    private static void log (String msg) {
        System.out.println("[" + Thread.currentThread().getName() + "]: log in FirstChild.java ---> " + msg);
    }

    //public static class ChildMsgWithoutArgument {}
    public static record ChildMsgWithoutArgument() {}

}
