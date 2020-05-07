
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class SudokuSolver implements ActionListener{
	private int[][] field;
	private int[][] checkArray;
	private Frame frame;
	private Console console;
	private ArrayList<Field> list;
	
	public SudokuSolver(Frame frame, Console console){
		field = new int[9][9];
		checkArray = new int[2][10];
		this.frame = frame;
		this.console = console;
		list = new ArrayList<Field>();
		frame.getButton_1().addActionListener(this);
		frame.getButton_2().addActionListener(this);
		console.setText("Tragen sie das Sudoku ein und klicken Sie auf lösen.");
	}
	
	public void getInput(){
		list.clear();
		Square[][] helper = new Square[3][3];
		int counter = 0;
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				helper[i][j] = (Square) frame.getList().get(counter);
				counter ++;
			}
		}
		
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				for(int k = 0; k < 3; k++){
					for(int l = 0; l < 3; l++){
						Field field = (Field) helper[i][k].getList().get(l + (3 * j));
						list.add(field);
						}
					}
			}
		}

		counter = 0;
		for(int i = 0; i < field.length; i++){
			for(int j = 0; j < field.length; j++){
				field[i][j] = Integer.valueOf(list.get(counter).getText());
				counter ++;
			}
		}
	}
	
	public void print_On_Console(){
		String output = "";
		for(int i = 0; i < field.length; i++){
			for(int j = 0; j < field.length; j++){
				output = output + " " + field[i][j];
			}
			output = output + "\n";
		}
		console.setText(output);
	}
	
	public void print_On_Field(){
		int counter = 0;
		for(int i = 0; i < field.length; i++){
			for(int j = 0; j < field.length; j++){
				list.get(counter).setText(String.valueOf(field[i][j]));
				counter++;
			}
		}
	}
	
	public void resetField() {
		for(int i = 0; i < list.size(); i++) {
			list.get(i).setText("0");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == frame.getButton_1()){
			getInput();
			console.setText("Sudoku wird gelöst");
			initCheckArray();
			solve(0, 0, 1);
			console.setText("Sudoku wurde gelöst");
			printField();
			print_On_Field();
			print_On_Console();
		}
		if(e.getSource() == frame.getButton_2()) {
			resetField();
		}
		
	}
	
	public void initCheckArray(){
		for(int i = 0; i < 10; i++){
			checkArray[0][i] = i;
			checkArray[1][i] = 0;
		}
	}
	
	public void resetCheckArray(){
		for(int i = 0; i < 10; i++){
			checkArray[1][i] = 0;
		}
	}
	
	public boolean checkCheckArray(){
		for(int i = 1; i < 10; i++){
			if(checkArray[1][i] > 1){
				return false;
			}
		}
		return true;
	}
	
	public void printCheckArray(){
		for(int i = 0; i < 2; i++){
			for(int j = 0; j < 10; j++){
				System.out.print(String.valueOf(checkArray[i][j]));
			}	
			System.out.print("\n");
		}
	}
	
	public void printField(){
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				System.out.print(String.valueOf(field[i][j]));
			}	
			System.out.print("\n");
		}
	}
	
	public boolean checkRow(int index){
		resetCheckArray();
		for(int i = 0; i < field.length; i++){
			checkArray[1][(field[index][i])]++;
		}
		if(checkCheckArray()){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean checkCollum(int index){
		resetCheckArray();
		for(int i = 0; i < field.length; i++){
			checkArray[1][(field[i][index])]++;
		}
		if(checkCheckArray()){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean checkSquare(int reihenIndex, int spaltenIndex){
		resetCheckArray();
		int i, j = 0;
		if(reihenIndex < 3){
			reihenIndex = 0;
		}
		else if(reihenIndex < 6){
			reihenIndex = 3;
		}
		else{
			reihenIndex = 6;
		}
		
		if(spaltenIndex < 3){
			spaltenIndex = 0;
		}
		else if(spaltenIndex < 6){
			spaltenIndex = 3;
		}
		else{
			spaltenIndex = 6;
		}
		
		i = reihenIndex;
		j = spaltenIndex;
		
		for(i = reihenIndex;i < (reihenIndex + 3); i++){
			for(j = spaltenIndex; j < (spaltenIndex + 3); j++){
				checkArray[1][(field[i][j])]++;
			}
		}
		
		if(checkCheckArray()){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean checkForZero(){
		for(int i = 0;i < 9; i++){
			for(int j = 0; j < 9; j++){
				if(field[i][j] == 0){
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean solve(int reihenIndex, int spaltenIndex, int number){
		if(field[reihenIndex][spaltenIndex] == 0 && number < 10 && !checkForZero()){
			field[reihenIndex][spaltenIndex] = number;
			if(checkRow(reihenIndex) && checkCollum(spaltenIndex) && checkSquare(reihenIndex, spaltenIndex) && spaltenIndex < 8){
				if(solve(reihenIndex, spaltenIndex + 1, 1)) {
					return true;
				}
			}
			if(checkRow(reihenIndex) && checkCollum(spaltenIndex) && checkSquare(reihenIndex, spaltenIndex) && spaltenIndex == 8 && reihenIndex < 8){
				if(solve(reihenIndex + 1, 0, 1)) {
					return true;
				}
			}
			if(checkRow(reihenIndex) && checkCollum(spaltenIndex) && checkSquare(reihenIndex, spaltenIndex) && checkForZero()){
				return true;
			}
			
			field[reihenIndex][spaltenIndex] = 0;
			if(solve(reihenIndex, spaltenIndex , number + 1)) {
				return true;
			}
		}
		else{
			if(spaltenIndex < 8 && number < 10 && !checkForZero()){
				if(solve(reihenIndex, spaltenIndex + 1, number)) {
					return true;
				}
			}
			if(spaltenIndex == 8 && reihenIndex < 8 && number < 10 && !checkForZero()){
				if(solve(reihenIndex + 1, 0, number)) {
					return true;
				}
			}
			
		}
				return false;
	}
	
	
	
}
