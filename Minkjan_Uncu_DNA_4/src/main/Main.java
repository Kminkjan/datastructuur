package main;

public class Main {

	public static void main(String[] args) {

		Pert pert = new Pert();
//		pert.addVertex("A");
//		pert.addVertex("B");
//		pert.addVertex("C");
//		pert.addVertex("D");
//		pert.addVertex("E");
//		pert.addVertex("F");
//		pert.addVertex("G");

		pert.createRelation("A", "B", 3);
		pert.createRelation("A", "D", 1);
		pert.createRelation("A", "G", 3);

		pert.createRelation("B", "C", 1);
		pert.createRelation("B", "F", 1);

		pert.createRelation("D", "E", 1);
		pert.createRelation("E", "F", 1);
		pert.createRelation("G", "F", 2);
		pert.createRelation("F", "C", 1);

		pert.print();
		
	}

}
