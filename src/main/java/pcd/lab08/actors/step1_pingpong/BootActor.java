package pcd.lab08.actors.step1_pingpong;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;


public class BootActor extends AbstractActor {

	public Receive createReceive() {
		return receiveBuilder()
				.match(BootMsg.class, this::onBootMsg)
	            .build();
	}

	private void onBootMsg(BootMsg msg) {
		ActorRef pinger = this.getContext().actorOf(Props.create(PingerActor.class), "pinger");
		ActorRef ponger = this.getContext().actorOf(Props.create(PongerActor.class), "ponger");
		pinger.tell(new pcd.lab08.actors.step1_pingpong.PingerPongerProtocol.BootMsg(ponger), this.getSelf());
	}

	/* messages */
	
	public static class BootMsg {}
}
