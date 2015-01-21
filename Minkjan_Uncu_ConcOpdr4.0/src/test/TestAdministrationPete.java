package test;

import akka.actor.ActorRef;
import main.AdministrationPete;

/**
 * Created by Kris on 21-1-2015.
 */
public class TestAdministrationPete extends AdministrationPete {
    TestAdministrationPete(String name, String color, ActorRef sint) {
        super(name, color, sint);
    }
}
