package AMyTest;

/**
 * @author Judy
 * @create 2023-08-07-11:04
 */
public class test02 {

    public static void main(String[] args) {
        int a = 100;
        System.out.println(a);
        func(a);
        System.out.println(a);
    }
    public static void func(int num){
        num = 200;
    }
}
