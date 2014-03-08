 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String...args) {
//		String[][] inmatrix = { {"a", "a", "a"}, {"a", "a", "a"}, {"a", "a", "a"}};
//		FloodFill problem = new FloodFill(inmatrix, 3, 3);
//		problem.printmatrix();
//		System.out.println("=====");
//		problem.setflood(0, 0, "b");
//		problem.printmatrix();
		readfromstdin(args);
		
	}
	
	
	public static void readfromstdin(String...args) {
		try{
			BufferedReader br = 
	                      new BufferedReader(new InputStreamReader(System.in));
	 
			String input;
			String firstline = br.readLine();
			String[] linesplit = firstline.split(" ");
			int ydim = Integer.parseInt(linesplit[0]);
			int xdim = Integer.parseInt(linesplit[1]);
			String[][] inmatrix = new String[ydim][xdim];
			int count = 0;
			while(count < ydim){
				input=br.readLine();
				for (int i = 0; i < input.length(); i++) {
					inmatrix[count][i] = Character.toString(input.charAt(i));
				}
				count += 1;
			}
			FloodFill fldproblem = new FloodFill(inmatrix, xdim, ydim);
			while((input=br.readLine())!=null) {
				String[] cmdline = input.split(" ");
				
			}
		}catch(IOException io){
			io.printStackTrace();
		}	
	}

}



final class FloodFill {
	
	String[][] matrixproblem = null;
	int xdim = 0;
	int ydim = 0;
	int[] UP = {0, 1};
	int[] DOWN = {0, -1};
	int[] LEFT = {-1, 0};
	int[] RIGHT = {1, 0};
	int[][] directions = {UP, DOWN, LEFT, RIGHT};
	public FloodFill(String[][] inmatrix, int xdim, int ydim) {
		this.matrixproblem = inmatrix;
		this.xdim = xdim;
		this.ydim = ydim;
	}
	
	public boolean islegal(int xcord, int ycord) {
		return xcord < this.xdim && ycord < this.ydim && xcord >= 0 && ycord >= 0;
	}
	
	public int[][] findneighborhood(int xcord, int ycord, Object element) {
		int[][] resultset = new int[4][2];
		for(int i = 0; i < this.directions.length; i++) {
			int[] v = this.directions[i];
			if ((islegal(xcord + v[0], ycord + v[1])) && (this.matrixproblem[xcord + v[0]][ycord + v[1]].equals(element))) {
				int[] adding = {xcord + v[0], ycord + v[1]};
				resultset[i] = adding;
			}
		}
		return resultset;
	}
	
	public void setflood(int xcord, int ycord, String newelt) {
		Object oldelt = this.matrixproblem[xcord][ycord];
		if(oldelt == newelt) {
			return;
		} else {
			this.matrixproblem[xcord][ycord] = newelt;
			int[][] neighborhood = findneighborhood(xcord, ycord, oldelt);
			for(int i = 0; i < neighborhood.length; i++) {
				int[] entry = neighborhood[i];
				if(entry != null) {
					setflood(entry[0], entry[1], newelt);
				}
			}
		}
	}
	
	public void printmatrix() {
		for(int i = 0; i < this.matrixproblem.length; i++) {
			String printstring = "";
			for(int j = 0; j < this.matrixproblem[i].length; j++) {
				printstring += this.matrixproblem[i][j];
			}
			System.out.println(printstring);
		}
	}
	
}