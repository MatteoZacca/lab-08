package pcd.lab08.actors.step1_experiment;

import akka.actor.AbstractActor;
import pcd.lab08.actors.step1_experiment.KingerKongerProtocol.*;

public class KongerActor extends AbstractActor {

    public Receive createReceive() {
        return receiveBuilder()
                .match(KingMsg.class, this::onKingMsg)
                .build();
    }

    private void onKingMsg(KingMsg msg) {
        log("entered in onKingMsg: got king " + msg.count() + " => kong " + (msg.count() + 1));
        msg.kingRef().tell(new KongMsg(msg.count() + 1, this.getSelf()), this.getSelf());
    }

    private static void log(String msg) {
        System.out.println("log in KongerActor ---> " + msg);
    }


}
