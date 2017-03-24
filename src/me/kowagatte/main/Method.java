package me.kowagatte.main;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
public class Method {
	@SuppressWarnings("resource")
	public static void main(String[] args){
		Scanner reader = new Scanner(System.in);
		System.out.println("Enter a equation: ");
		String function = reader.next();
		System.out.println("Enter its derivitive: ");
		String derivitive = reader.next();
		System.out.println("Enter max range value: ");
		int integer = reader.nextInt();	
		List<Double> zeros = new Method().getAllZeros(function, derivitive, integer);
		System.out.println("");
		System.out.println("Functions zeros to the nearest 12 digits.");
		for(double d : zeros){
			System.out.println(d);
		}
	}
	public boolean isOpposite(String function, int value){
		Expression fe = new ExpressionBuilder(function).variable("x").build();
		fe.setVariable("x", value);
		double SOL_ONE = fe.evaluate();
		fe.setVariable("x", value+1);
		double SOL_TWO = fe.evaluate();
		if(((SOL_ONE > 0) && (SOL_TWO < 0)) || ((SOL_ONE < 0) && (SOL_TWO > 0)))
			return true;
		return false;
	}
	public List<Double> getAllZeros(String function, String derivitive, int max){
		int min = -max;
		List<Double> zeros = new ArrayList<Double>();
		for(int i = min; i < max; i++){
			if(isOpposite(function, i)){
				double value = (((i)+(i+1))/2);
				zeros.add(getZeroOfFunction(function, derivitive, value));
			}
		}
		return zeros;
	}
	public double getZeroOfFunction(String function, String derivitive, double integer){	
		double difference = 1;
		double nextinteger;
		Expression fe = new ExpressionBuilder(function).variable("x").build();
		Expression de = new ExpressionBuilder(derivitive).variable("x").build();
		double functionOUT;
		double derivitiveOUT;
		while(difference > 0.000000000001){
			fe.setVariable("x", integer);
			functionOUT = fe.evaluate();
			de.setVariable("x", integer);
			derivitiveOUT = de.evaluate();
			nextinteger = integer - (functionOUT / derivitiveOUT);
			difference = Math.abs(integer-nextinteger);
			integer = nextinteger;
		}
		return integer;
	}
}
