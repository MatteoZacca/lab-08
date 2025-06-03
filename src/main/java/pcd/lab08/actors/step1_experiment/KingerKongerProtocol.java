package pcd.lab08.actors.step1_experiment;

import akka.actor.ActorRef;

public interface KingerKongerProtocol {
    public static record ChildMsg (ActorRef kongRef) {}
    public static record KongMsg (long count, ActorRef kongRef) {}
    public static record KingMsg (long count, ActorRef kingRef) {}
}
