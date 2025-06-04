package pcd.lab08.actors.step4_multiple_behav_stash;

import akka.actor.*;
import pcd.lab08.actors.step4_multiple_behav_stash.MsgProtocol.*;

/* In Akka, stashing is the mechanism by which an actor can temporarily set aside (buffer)
incoming messages that it cannot - or does not yet want to - process in its current behavior.
Later, when the actor transitions to a state where those messages can be handled, it "unstashes"
them, letting them be delivered to the new behavior in FIFO order.
When you call unstashAll(), they are reinserted into the mailbox in exactly that same order.
If your actor extends AbstractActorWithStash, you get a Stash instance "for free" via the
stash() and unstashAll() methods. */

public class RunActorWithMultipleBehavioursAndStashing {
  public static void main(String[] args) throws Exception  {

	  final ActorSystem system = ActorSystem.create("myActorSystem");		
	  final ActorRef myActor =  system.actorOf(Props.create(ActorWithBehavioursAndStashing.class), "myActor");


	  /* The following two msgs are not handled in the initial behaviour.
	  However they are not lost, they are stashed */
	  myActor.tell(new MsgOne(), null);
	  myActor.tell(new MsgTwo(), null);
	  
	  /* Give time to the logging system to setup */
	  Thread.sleep(1000);

	  /* The next msg will cause a transition to the new behaviour,
	  where the stashed msgs will be unstashed and processed. */
	  myActor.tell(new MsgZero(), null);
  
	  System.out.println("done");
  }
}
