package pcd.lab08.actors.step2_counter;

import akka.actor.*;
import static pcd.lab08.actors.step2_counter.CounterMsgProtocol.*;

public class CounterActor extends AbstractActor {

	private int count;
	
	private CounterActor() {
		count = 0;
	}

	public Receive createReceive() {
		return receiveBuilder()
				.match(IncMsg.class, this::onIncMsg)
				.match(GetValueMsg.class, this::onGetValueMsg)
	            .build();
	}

	private void onIncMsg(IncMsg msg) {
		count++;
	}

	private void onGetValueMsg(GetValueMsg msg) {
		log("this.getSelf(): " + this.getSelf());
		msg.replyTo().tell(new CounterValueMsg(count), this.getSelf());
	}

	private static void log(String msg) {
		System.out.println("Debug in [CounterActor] ---> " + msg);
	}
	
}
