package pcd.lab08.actors.step3_multiple_behav;

import akka.actor.*;
import pcd.lab08.actors.step3_multiple_behav.MsgProtocol.*;

public class RunActorWithMultipleBehaviours {
  public static void main(String[] args) throws Exception  {

	  final ActorSystem system = ActorSystem.create("myActorSystem");		
	  final ActorRef myActor =  system.actorOf(Props.create(ActorWithBehaviours.class), "myActor");

	  /* The next two msgs will be unhandled, because not managed in the initial behaviour.
	  The actor has just been created, so it's using its starting behaviour (the one
	  returned by createReceive(). MsgOne and MsgTwo don't match that behavaiour, so they
	  won't be handled. */
	  myActor.tell(new MsgOne(), null);
	  myActor.tell(new MsgTwo(), null);
	  
	  /* give time to the logging system to setup */
	  Thread.sleep(1000);

	  /* The next msgs will be managed and will cause a transition to new behaviours, up to stopped */
	  myActor.tell(new MsgZero(), null);
	  myActor.tell(new MsgOne(), null);
	  myActor.tell(new MsgTwo(), null);
  
	  // these msgs will be not delivered since the actor stopped
	  myActor.tell(new MsgZero(), null);
	  myActor.tell(new MsgOne(), null);
	  myActor.tell(new MsgTwo(), null);



  }
}
