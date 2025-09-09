// 홀짝 구분하기
package yujin;

import java.util.*;


public class P181944 {
	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if (n % 2 != 0) {
            System.out.print(n + " is odd");
        } else System.out.print(n + " is even");
    }
}

// ' ' → 문자(char) : 한 글자
// " " → 문자열(String) : 문장