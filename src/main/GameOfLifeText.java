package main;

import java.util.Scanner;
import java.util.Set;

import model.Position;
import model.Turtle;
import model.WorldTurtle;
import view.Printer;
import controller.EpochRunner;

public class GameOfLifeText {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		Printer printer = new Printer();
		Turtle turtle = new WorldTurtle(new Position(0, 0));
		Set<Position> positions = turtle.penDown().right().right().penUp()
				.down().left().left().penDown().penUp().down().right()
				.penDown().getDraft();
//		positions.add(new Position(-1, -1));
//		positions.add(new Position(-1, 0));
//		positions.add(new Position(-1, 1));
//		positions.add(new Position(0, -1));
//		positions.add(new Position(1, 0));

		EpochRunner runner = new EpochRunner(positions);
		String input = "";

		printer.printWorld(runner.getWorld());
		System.out.println("Wanna go on?");
		input = in.nextLine();

		while (input.isEmpty()) {
			printer.printWorld(runner.runEpoch());
			System.out.println("Wanna go on?");
			input = in.nextLine();
		}

		in.close();
	}

}
