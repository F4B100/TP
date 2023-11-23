import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void print_char(char a,int num){
        for (int i = 1;i <= num;i++){System.out.print(a);}
    }

    public static long get_distance(int bus_1,int bus_2){
        return distances[bus_1][bus_2];
    }

    static Scanner sc = new Scanner(System.in);

    static final String[] CITIES = {"Porto","Aveiro","Braga","Coimbra","Lisboa","Fátima"};
    static final int[][] distances ={
            {0,50,60,130,300,200},
            {50,0,130,70,250,140},
            {60,130,0,180,370,250},
            {130,70,180,0,200,90},
            {300,250,370,200,0,130},
            {200,140,250,90,130,0}
            };

    public static long[][] build_matrix(int rows, int columns){
        long [][] matrix = new long[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = Long.parseLong(String.valueOf(sc.next()));
            }
            sc.nextLine();
        }
        return matrix;
    }

    public static void print_matrix(long[][] input){
        for (int i = 0; i < input.length; i++) {
            System.out.printf("bus%d :",i);
            for (int j = 0; j < input[0].length; j++) {
                System.out.print("\t"+input[i][j]);
            }
            System.out.println();
        }
    }

    public static long[][] build_distance_matrix(long[][] input){
        long[][] output= new long[input.length][input[0].length];
        for (int i = 0; i < output.length; i++) {
            output[i][0] = 0;
            for (int j = 1; j < output[0].length; j++) {
                output[i][j]= distances[(int) input[i][j-1]]
                                       [(int) input[i][j]];
            }
        }
        return output;
    }

    public static long[] build_total_distance_matrix(long[][] input){
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
    }

    public static void print_sum(long[] to_sum){
        long sum = 0;
        for (long l : to_sum) {
            sum += l;
        }
        System.out.printf("Total de km a percorrer pela frota = %d km%n",sum);
    }

    public static long[][] get_max_distance_and_day(long[][] input){
        long max = 0, sum;
        int num_dias = 0;
        long [] days = new long[input.length];
        for (int i = 0; i < input[0].length; i++) {
            sum = 0;
            for (long[] l: input) {
                sum += l[i];
            }
            if (sum > max){
                max = sum;
                Arrays.fill(days, 0);
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
        System.out.printf("máximo de kms num dia: (%d km), dias: [%d",input[0][0],input[1][0]);
        for (int i = 1;i < input[1].length;i++) {
            if(input[1][i] != 0)
                System.out.printf(",%d",input[1][i]);
        }
        System.out.println("]");
    }

    public static long[] get_duplicate_day_buses(long[][] input){
        long[] output = new long[input.length];
        int num_rep = 0;
        for (int i = 0; i < input.length; i++) {
            for (int j = 1; j < input[0].length; j++) {
                if(input[i][j-1] == input[i][j]){
                    output[num_rep]=i;
                    num_rep += 1;
                }
            }
        }
        return output;
    }

    public static void print_duplicate_buses(long[] input){
        System.out.printf("Autocarros que permanecem mais de 1 dia consecutivo na mesma cidade: Bus%d %n",input[0]);
        for (int l = 1;l<input.length;l++) {
            if(input[l] != 0){
                System.out.printf("Bus%d ",input[l]);
            }
        }
        System.out.println();
    }

    public static void bus(){
        //alinea a------------------------------------------------------------
        String data = sc.nextLine();
        int num_autocarro = Integer.parseInt(sc.next()),
             num_dias = Integer.parseInt(sc.next());
        long[][] planeamento;
        planeamento = build_matrix(num_autocarro,num_dias);
        int[] alinea_j = {Integer.parseInt(sc.next()),Integer.parseInt(sc.next())};
        //alinea b------------------------------------------------------------
        print_matrix(planeamento);
        //alinea c------------------------------------------------------------
        long[][] distance_matrix = build_distance_matrix(planeamento);
        print_matrix(distance_matrix);
        //alinea d------------------------------------------------------------
        long[]  total_distance = build_total_distance_matrix(distance_matrix);
        print_total_distance_matrix(total_distance);
        //alinea e------------------------------------------------------------
        print_sum(total_distance);
        //alinea f------------------------------------------------------------
        print_max_distance_and_day(get_max_distance_and_day(distance_matrix));
        //alinea g------------------------------------------------------------
        print_duplicate_buses(get_duplicate_day_buses(planeamento));
    }

    public static void main(String[] args) {
        bus();
    }
}