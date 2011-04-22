import java.util.Scanner;
import java.util.StringTokenizer;

public class MatrixEncryption {
	static final int ASCIIOFFSET=64;
	
	
	public static void main(String args[]){
		Scanner in = new Scanner(System.in);
		String[] results = new String[5];
		for(int j = 0; j < 5; j++)
		{
			String input = in.nextLine();
			StringTokenizer str = new StringTokenizer(input, ",");
			char dir = str.nextToken().trim().charAt(0);
			String toEncode = str.nextToken().trim();
			double[] encMat = new double[4];
			for(int i = 0; i < 4; i++)
				encMat[i] = Double.parseDouble(str.nextToken().trim());

			results[j] = (matToStr(matToMat(strToMat(toEncode), encMat, dir)));
		}
		for(String result: results)
			System.out.println(result);
	}
	public static String matToStr(int[][] mat)
	{	

		String toReturn = "";
		for(int i = 0; i < mat.length; i++)
		{
			if(mat[i][0] == 27)
				toReturn += " ";
			else
				toReturn += (char)(mat[i][0] + ASCIIOFFSET);

			if(mat[i][1] == 27)
				toReturn += " ";
			else
				toReturn += (char)(mat[i][1] + ASCIIOFFSET);
		}

		return toReturn;
	}
	public static int[][] matToMat(int[][] mat, double[] encMat, char dir)
	{	
		
		//Decryption
		if(dir == 'D')
		{
			double[] tempMat = new double[4];
			double dif = encMat[0]*encMat[3] - encMat[1]*encMat[2];
			tempMat[0] = encMat[3]/dif;
			tempMat[1] = -encMat[1]/dif;
			tempMat[2] = -encMat[2]/dif;
			tempMat[3] = encMat[0]/dif;
			encMat = tempMat;
		
		//END ERROR SECTION
		}

		//Multiplication of Matrices
		int[][] toReturn = new int[mat.length][mat[0].length];

		for(int i = 0; i < mat.length; i++)
		{
			System.out.println(encMat[0] * mat[i][0] + encMat[1] * mat[i][1]);
			toReturn[i][0] = (int)(encMat[0] * mat[i][0] + encMat[1] * mat[i][1]);
			toReturn[i][1] = (int)(encMat[2] * mat[i][0] + encMat[3] * mat[i][1]);
			
			if(toReturn[i][0] > 27)
				toReturn[i][0] = toReturn[i][0] % 27;
			if(toReturn[i][1] > 27)
				toReturn[i][1] = toReturn[i][1] % 27;

			while(toReturn[i][0] < 1)
				toReturn[i][0] += 27;
			while(toReturn[i][1] < 1)
				toReturn[i][1] += 27;			
		}
		
		return toReturn;
	}
/*	public static void printMat(int[][] mat)
	{
		for(int i = 0; i < mat.length; i++){
			for(int j = 0; j < mat[0].length; j++){
				System.out.print(mat[i][j] + " ");
			}
			System.out.println();
		}
	}*/
	public static int[][] strToMat(String str)
	{
		if(str.length()%2 == 1)
			str = str + " ";
		int[][] toReturn = new int[str.length()/2][2];
		for(int i = 0; i<str.length()/2; i++)
		{
			int k = i*2;
			int j = i*2+1;
			int charVal = (int)str.charAt(k)-64;
			if(charVal < 1 || charVal > 26)
				charVal = 27;
			toReturn[i][0] = charVal;
			//Same for next letter
			charVal = (int)str.charAt(j) - 64;
			if(charVal < 1 || charVal > 26)
				charVal = 27;
			toReturn[i][1] = charVal;    
		}
		for(int i = 0; i < toReturn.length; i++){
			for(int j = 0; j < toReturn[0].length; j++){
				System.out.print(toReturn[i][j] + " ");
			}
			System.out.println();
		}
		return toReturn;
	}
}
