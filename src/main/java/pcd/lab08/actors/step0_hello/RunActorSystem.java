package pcd.lab08.actors.step0_hello;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.ActorSystem;

public class RunActorSystem {

	public static void main(String[] args) {
		// ActorSystem.create(rootBehavior, "systemName")
		final ActorSystem system = ActorSystem.create("my-actor-system");

		// il metodo actorOf chiede all'ActorSystem di istanziare un nuovo attore figlio,
		// restituisce un ActorRef collegato a quell'attore
		// Props.create(HelloWorldActor.class) sta dicendo: “Per creare il nuovo attore,
		// usa il costruttore pubblico zero‐arg di HelloWorldActor.”
		final ActorRef helloActor =  system.actorOf(Props.create(HelloWorldActor.class), "my-actor");
		// si inviano messaggi con actorRef.tell(msg)
		helloActor.tell(new HelloWorldMsgProtocol.SayHello("World"), null);
		helloActor.tell(new HelloWorldMsgProtocol.SayHello("World Again"), null);
	}

}
