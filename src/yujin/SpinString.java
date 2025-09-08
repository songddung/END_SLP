// 프로그래머스 문자열 돌리기
package yujin;
import java.util.Scanner;

public class SpinString {
	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String a = sc.next();
        for (int i=0; i < a.length(); i++) {
            System.out.println(a.charAt(i));
        }
        sc.close();
    }
}

/* 접근제어자
public -> 어디서든 접근 가능 (다른 패키지에서도)
private -> 같은 클래스 안에서만 접근 가능
생략(default) -> 같은 패키지 않에서만 접근 가능 
*/

/* charAt(): 문자열에서 특정 위치의 문자 가져옴
char ch = 문자열.charAt(인덱스);
ex) String str = "hello"
	char c1 = str.charAt(0); => 'h'
 */