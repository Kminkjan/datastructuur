package main;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class Main {

	public static void main(String[] args) {

		System.out.println("Lets go Akka");
		System.out.println("Ik loop, ik liep, ik heb geliept");
		System.out.println("DOEWNLOADET");
		System.out.println("POP THE HOOD");
		ActorSystem system  = ActorSystem.create("HIApp");
		ActorRef hiActor= system.actorOf(Props.create(HiActor.class),"HiActor"); 
		hiActor.tell(Message.HI,null);  
		/*  second  argument  is ActorRef of  sender*/
		hiActor.tell(Message.HELLO,null);  
		hiActor.tell(Message.NO_GREETING,null);  
		hiActor.tell(Message.INSULT,null);  
	}
}
