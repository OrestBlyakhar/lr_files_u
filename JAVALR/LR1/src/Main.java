import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int N;

        // Якщо число N подане через аргумент командного рядка – зчитати його
        if(args.length > 0){
            N = Integer.parseInt(args[0]);
        } else {
            // Інакше попросити користувача ввести N з клавіатури
            Scanner sc = new Scanner(System.in);
            System.out.print("Введіть кількість чисел Люка: ");
            N = sc.nextInt();
            sc.close();
        }

        // Створюємо масив об’єктів для перших N чисел Люка
        LucasNumber[] lucasNumbers = new LucasNumber[N];
        for(int i = 0; i < N; i++){
            lucasNumbers[i] = new LucasNumber(i + 1);// (i+1) бо послідовність починається з 1
        }

        // Виводимо всі числа Люка та відмічаємо квадрати
        System.out.println("Перші " + N + " чисел Люка та інформація про квадрати:");
        for(LucasNumber ln : lucasNumbers){
            System.out.println("L(" + ln.getIndex() + ") = " + ln.getValue() + (ln.isPerfectSquare() ? " (квадрат)" : ""));
        }
    }
}
