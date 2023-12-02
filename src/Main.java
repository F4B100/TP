import java.util.Scanner;

public class Main {

    public static void print_char(char a,int num){
        for (int i = 1;i <= num;i++){System.out.print(a);}
    }

    public static long[] cut(long[] input, int len){
        long[] output = new long[len];
        for (int i = 0; i < len; i++) {
            output[i] = input[i];
        }
        return output;
    }

    public static boolean vector_has_duplicates(int[] vector){
        for (int i = 0; i < vector.length-1; i++) {
            if (vector[i] == vector[i+1]){
                return true;
            }
        }
        return false;
    }

    public static boolean vector_equal (int[] vector){
        for (int i = 0; i < vector.length - 1; i++) {
            if(vector[i] != vector[i+1]){
                return false;
            }
        }
        return true;
    }

    public static long sum_array_members(long[] array){
        long sum = 0;
        for (long num: array) {
            sum += num;
        }
        return sum;
    }

    public static long[] fill_Array(long[] array,long to_fill){
        long [] filled = new long[array.length];
        for (int i = 0; i < array.length; i++) {
            filled[i] = to_fill;
        }
        return filled;
    }

    public static int[][] transpose (int[][] input){
        int[][] output = new int[input[0].length][input.length];
        for (int i = 0; i < input[0].length; i++) {
            for (int j = 0; j < input.length; j++) {
                output[i][j] = input[j][i];
            }
        }
        return output;
    }

    static Scanner sc = new Scanner(System.in);

    static final String[] CITIES = {"Porto","Aveiro","Braga","Coimbra","Lisboa","Fátima"};
    static final int[][] DISTANCES ={
            {0,50,60,130,300,200},
            {50,0,130,70,250,140},
            {60,130,0,180,370,250},
            {130,70,180,0,200,90},
            {300,250,370,200,0,130},
            {200,140,250,90,130,0}
    };

    public static int[][] build_matrix(int rows, int columns){
        int [][] matrix = new int[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = Integer.parseInt(sc.next());
            }
            sc.nextLine();
        }
        return matrix;
    }

    public static void print_matrix(int[][] input){
        for (int i = 0; i < input.length; i++) {
            System.out.printf("bus%d :",i);
            for (int j = 0; j < input[0].length; j++) {
                System.out.print("\t"+input[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static int[][] build_distance_matrix(int[][] input){
        int[][] output= new int[input.length][input[0].length];
        for (int i = 0; i < output.length; i++) {
            output[i][0] = 0;
            for (int j = 1; j < output[0].length; j++) {
                output[i][j]= DISTANCES[input[i][j-1]]
                        [input[i][j]];
            }
        }
        return output;
    }

    public static long[] build_total_distance_matrix(int[][] input){
        long[] output = new long[input.length];
        //adds all values in one line on the input matrix and puts that value in the output matrix
        for (int i = 0; i < output.length; i++) {
            long sum = 0;
            for (int j = 0; j < input[0].length; j++) {
                sum += input[i][j];
            }
            output[i] = sum;
        }
        return output;
    }

    public static void print_total_distance_matrix(long[] total_distances){
        for (int i = 0; i < total_distances.length; i++) {
            System.out.printf("Bus%d : %d Km%n",i,total_distances[i]);
        }
        System.out.println();
    }

    public static void print_sum(long[] to_sum){
        long sum = 0;
        for (long l : to_sum) {
            sum += l;
        }
        System.out.printf("Total de km a percorrer pela frota = %d km%n%n",sum);
    }

    public static long[][] get_max_distance_and_day(int[][] input){
        long max = 0, sum;
        int num_dias = 0;
        long [] days = new long[input.length];
        for (int i = 0; i < input[0].length; i++) {
            sum = 0;
            for (int[] l: input) {
                sum += l[i];
            }
            if (sum > max){
                max = sum;
                days = fill_Array(days, 0);
                num_dias = 0;
                days[num_dias] = i;
            } else if (sum == max) {
                num_dias += 1;
                days[num_dias] = i;
            }
        }
        return new long[][]{{max}, days};
    }

    public static void print_max_distance_and_day(long[][] input){
        //this function prints the day in which the combined
        // distance traveled by all the buses is the biggest
        System.out.printf("máximo de kms num dia: (%d km), dias: [%d",input[0][0],input[1][0]);
        for (int i = 1;i < input[1].length;i++) {
            if(input[1][i] != 0)
                System.out.printf(",%d",input[1][i]);
        }
        System.out.println("]\n");
    }

    public static long[] get_duplicate_day_buses(int[][] input){
        long[] output = new long[input.length];
        int num_rep = 0;
        //this function uses the vector has duplicates function to check
        //if a bus stays in a city for more than a day
        for (int i = 0; i < input.length; i++) {
            if (vector_has_duplicates(input[i])){
                output[num_rep] = i;
                num_rep ++;
            }
        }
        return cut(output,num_rep);
    }

    public static void print_duplicate_buses(long[] input){
        //this function prints the buses that stayed more than one day in the same city
        System.out.printf("Autocarros que permanecem mais de 1 dia consecutivo na mesma cidade: Bus%d %n",input[0]);
        for (int l = 1;l<input.length;l++) {
            System.out.printf("Bus%d ",input[l]);
        }
        System.out.println();
    }

    public static long[]get_coinciding_days(int[][] input){
        long[] output = new long[2];
        //the following for loop calls the vector_equal function which returns true
        // if and only if all members of the array are equal and then stores the
        // day and bus.
        for (int i = 0; i < input.length; i++) {
            if(vector_equal(input[i])){
                output[0] = i;
                output[1] = input[i][0];
            }
        }
        return output;
    }

    public static void print_last_coinciding_day(long[] input){
        //this function prints the last day that all the buses were in the same city
        System.out.printf("No dia <%d>, todos os autocarros estarão em <%s>%n",input[0],CITIES[(int) input[1]]);
        System.out.println();
    }

    public static double[] calculate_histogram (long[] input){
        long total = sum_array_members(input);
        double[] output = new double[input.length];
        int i = 0;
        //this for loop calculates the percentage of the total distance traveled by each bus
        for (long l:input) {
            output[i] = l/(double)total;
            i++;
        }
        return output;
    }

    public static void print_histogtam(double[] input){
        int number,i=0;
        //this for loop prints the percentage of the total distance traveled by the bus
        //as a histogram with 10 or fewer asterisks the number of asterisks is the
        //integer division of the percentage traveled by 10
        for (double l:input) {
            number = (int) Math.floor(l*10);
            System.out.printf("Bus%d (%.2f%%) :",i,100*l);
            print_char('*',number);
            System.out.println();
            i++;
        }
        System.out.println();
    }

    public static int[] get_closest_bus(int[] input, int[][] planeamento){
        int[] output = new int[2];
        int distance, i;

        //this if statement makes sure the distance variable has a value before going into the main loop
        if (input[0] == 0){
            distance = DISTANCES[planeamento[input[0]][input[1]]][planeamento[1][input[1]]];
            output[0] = 1;
            output[1] = input[1];
        }else {
            distance = DISTANCES[planeamento[input[0]][0]][planeamento[input[0]][0]];
        }

        //this loop checks if the distance between two buses is less than the previous smallest distance
        //this way the output will be the closest bus to the inputted bus
        for (i = 0; i < planeamento.length ; i++){
            if(i != input[0]) {
                if (DISTANCES[planeamento[input[0]][input[1]]][planeamento[i][input[1]]] < distance) {
                    distance = DISTANCES[planeamento[input[0]][input[1]]][planeamento[i][input[1]]];
                    output[0] = i;
                    output[1] = input[1];
                }
            }
        }
        return output;
    }

    public static void print_closest_bus(int[] bus1, int[] bus2, int[][] planeamento){
        //this function prints the chosen bus and its closest bus in the chosen day
        System.out.printf("No dia <%d>, <Bus%d> estará em <%s>. Bus<%d> é o mais próximo. Está em <%s> a <%d km>",
                bus1[1],bus1[0],CITIES[planeamento[bus1[0]][bus1[1]]],bus2[0],CITIES[planeamento[bus2[0]][bus2[1]]],
                DISTANCES[planeamento[bus1[0]][bus1[1]]][planeamento[bus2[0]][bus2[1]]]);
    }

    public static void bus(){
        //alinea a------------------------------------------------------------
        //↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ this variable is never used
        String data = sc.nextLine();
        int num_autocarro = Integer.parseInt(sc.next()),
                num_dias = Integer.parseInt(sc.next());
        int[][] planeamento;
        planeamento = build_matrix(num_autocarro,num_dias);
        int[] alinea_j = {Integer.parseInt(sc.next()),Integer.parseInt(sc.next())};
        //alinea b------------------------------------------------------------
        System.out.println("b)");
        print_matrix(planeamento);
        //alinea c------------------------------------------------------------
        System.out.println("c)");
        int[][] distance_matrix = build_distance_matrix(planeamento);
        print_matrix(distance_matrix);
        //alinea d------------------------------------------------------------
        System.out.println("d)");
        long[]  total_distance = build_total_distance_matrix(distance_matrix);
        print_total_distance_matrix(total_distance);
        //alinea e------------------------------------------------------------
        System.out.println("e)");
        print_sum(total_distance);
        //alinea f------------------------------------------------------------
        System.out.println("f)");
        print_max_distance_and_day(get_max_distance_and_day(distance_matrix));
        //alinea g------------------------------------------------------------
        System.out.println("g)");
        print_duplicate_buses(get_duplicate_day_buses(planeamento));
        //alinea h------------------------------------------------------------
        System.out.println("h)");
        print_last_coinciding_day(get_coinciding_days(transpose(planeamento)));
        //alinea i------------------------------------------------------------
        System.out.println("i)");
        print_histogtam(calculate_histogram(total_distance));
        //alinea j------------------------------------------------------------
        System.out.println("j)");
        print_closest_bus(alinea_j,get_closest_bus(alinea_j,planeamento),planeamento);
    }

    public static void main(String[] args) {
        bus();
    }
}
