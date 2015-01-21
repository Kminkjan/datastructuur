package test;

import main.Pert;
import org.junit.Test;

public class PertTest {

    @Test
    public void testNormalExample() {
        System.out.println("Normal example");

        Pert pert = new Pert();

        pert.createRelation("A", "B", 3);
        pert.createRelation("A", "D", 1);
        pert.createRelation("A", "G", 3);
        pert.createRelation("B", "C", 1);
        pert.createRelation("B", "F", 1);
        pert.createRelation("D", "E", 1);
        pert.createRelation("E", "F", 1);
        pert.createRelation("G", "F", 2);
        pert.createRelation("F", "C", 1);

        pert.calculateMin();

        pert.print();
    }

    @Test
    public void testMultiStartVertices() {
        System.out.println("Multi Start example");

        Pert pert = new Pert();

        pert.createRelation("A", "B", 3);
        pert.createRelation("A", "D", 1);
        pert.createRelation("A", "G", 3);
        pert.createRelation("Z", "B", 50);
        pert.createRelation("B", "C", 1);
        pert.createRelation("B", "F", 1);
        pert.createRelation("D", "E", 1);
        pert.createRelation("E", "F", 1);
        pert.createRelation("G", "F", 2);
        pert.createRelation("F", "C", 1);

        pert.calculateMin();

        pert.print();
    }

    @Test
    public void testMultiEndVertices() {
        System.out.println("Multi End example");

        Pert pert = new Pert();

        pert.createRelation("A", "B", 3);
        pert.createRelation("A", "D", 1);
        pert.createRelation("A", "G", 3);
        pert.createRelation("B", "C", 1);
        pert.createRelation("B", "F", 1);
        pert.createRelation("D", "E", 1);
        pert.createRelation("E", "F", 1);
        pert.createRelation("G", "F", 2);
        pert.createRelation("F", "C", 1);
        pert.createRelation("B", "X", 3);
        pert.createRelation("F", "X", 2);

        pert.calculateMin();

        pert.print();
    }

    @Test
    public void testMultiAllVertices() {
        System.out.println("Multi All example");

        Pert pert = new Pert();

        pert.createRelation("A", "B", 3);
        pert.createRelation("A", "D", 1);
        pert.createRelation("A", "G", 3);

        pert.createRelation("Z", "B", 50);

        pert.createRelation("B", "C", 1);
        pert.createRelation("B", "F", 1);
        pert.createRelation("D", "E", 1);
        pert.createRelation("E", "F", 1);
        pert.createRelation("G", "F", 2);
        pert.createRelation("F", "C", 1);

        pert.createRelation("B", "X", 3);
        pert.createRelation("F", "X", 2);

        pert.calculateMin();

        pert.print();
    }

}