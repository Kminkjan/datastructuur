package main;

import akka.actor.UntypedActor;

public class HiActor extends UntypedActor{
	String name;
	public HiActor(String name){
		this.name = name;
	}

	@Override
	public void onReceive(Object message) throws Exception {
		
	}
	
}
