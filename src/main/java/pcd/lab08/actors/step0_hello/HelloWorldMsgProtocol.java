package pcd.lab08.actors.step0_hello;

public class HelloWorldMsgProtocol {

	/* Protocollo di messaggi: in Akka Typed in Java si definisce tipicamente un'interfaccia
	o una classe sealed rispetto ai messaggi. Ciascun messaggio è un oggetto immutabile
	(spesso un record o una classe static final) */

	/* messages types */
	
	public static record SayHello (String content) {}

}
