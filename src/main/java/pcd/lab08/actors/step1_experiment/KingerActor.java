package pcd.lab08.actors.step1_experiment;

import akka.actor.*;
import pcd.lab08.actors.step1_experiment.KingerKongerProtocol.*;

public class KingerActor extends AbstractActor {

    public Receive createReceive() {
        return receiveBuilder()
                .match(ChildMsg.class, this::onChildMsg)
                .match(KongMsg.class, this::onKongMsg)
                .build();
    }

    private void onChildMsg(ChildMsg msg) {
        log("entered in onChildMsg");
        log("this.getSelf(): " + this.getSelf());
        msg.kongRef().tell(new KingMsg(0, this.getSelf()), this.getSelf());
    }

    private void onKongMsg(KongMsg msg) {
        log("entered in onKongMsg: got kong " + msg.count() + " => king " + (msg.count() + 1));
        msg.kongRef().tell(new KingMsg(msg.count() + 1, this.getSelf()), this.getSelf());
    }

    private void log (String msg) {
        System.out.println("[" + Thread.currentThread().getName() + "]" + "log in KingerActor ---> " + msg);
    }

}
