package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Position;
import view.TextPrinter;
import controller.EpochRunner;

public class GameOfLifeText {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		TextPrinter printer = new TextPrinter();
		List<Position> positions = new ArrayList<Position>();
		positions.add(new Position(-1, -1));
		positions.add(new Position(-1, 0));
		positions.add(new Position(-1, 1));
		positions.add(new Position(0, -1));
		positions.add(new Position(1, 0));
		
		EpochRunner runner = new EpochRunner(positions);
		String input = "";
		
		printer.printWorld(runner.getWorld());
		System.out.println("Wanna go on?");
		input = in.nextLine();
		
		while(input.isEmpty()){
			printer.printWorld(runner.runEpoch());
			System.out.println("Wanna go on?");
			input = in.nextLine();
		}
		
		in.close();
	}

}
