
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class BaseBall {

	public int[] com_Answer() {

		int[] com_Answer = new int[3];
		Random rand = new Random();
		for (int i = 0; i < com_Answer.length; i++) {
			com_Answer[i] = rand.nextInt(9)+1;

			for (int k = 0; k < i; k++) {
				if (com_Answer[i] == com_Answer[k]) {
					i -= 1;
				}
			}
		}
		return com_Answer;
	}

	public int user_Answer(int[] com_Answer) {
		Scanner sc = new Scanner(System.in);
		System.out.print("숫자를 입력해주세요 ");
		System.out.print("ex)" + Arrays.toString(com_Answer) + " : ");
		int user_Answer = sc.nextInt();
		// 123

		if (user_Answer / 100 < 1 || user_Answer / 100 > 9) {
			System.out.println("세자리 숫자를 입력해 주세요.");
			return 0;
		}
		return user_Answer;
	}

	public int[] make_user_Answer_arr(int user_Answer) {

		int[] user_Answer_arr = new int[3];
		user_Answer_arr[0] = user_Answer / 100;
		user_Answer_arr[1] = (user_Answer - user_Answer_arr[0] * 100) / 10;
		user_Answer_arr[2] = user_Answer - (user_Answer_arr[0] * 100 + user_Answer_arr[1] * 10);

		return user_Answer_arr;
	}

	public int overapCheck(int[] user_Answer_arr) {
		int count = 0;

		for (int i = 0; i < user_Answer_arr.length; i++) {
			for (int k = 0; k < i; k++) {
				if (user_Answer_arr[i] == user_Answer_arr[k]) {
					count++;
				}
			}
		}
		if (count != 0) {
			System.out.println("중복되지 않는 세자리 숫자를 입력해주세요");
		}
		return count;
	}

	public HashMap<String, Integer> test(int[] user_Answer_arr, int[] com_Answer) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();

		int strike = 0;
		int ball = 0;
		for (int i = 0; i < com_Answer.length; i++) {
			if (com_Answer[i] == user_Answer_arr[i]) {
				strike++;
			} else if (com_Answer[i] == user_Answer_arr[0] || com_Answer[i] == user_Answer_arr[1]
					|| com_Answer[i] == user_Answer_arr[2]) {
				ball++;
			}
		}

		map.put("strike", strike);
		map.put("ball", ball);

		return map;
	}

	public int printResult(HashMap<String, Integer> map) {
		int count = 0;
		
		if (map.get("strike") > 0) {
			System.out.println(map.get("strike") + "스트라이크");
		}
		if (map.get("ball") > 0) {
			System.out.println(map.get("ball") + "볼");
		}
		if(map.get("strike")==3){
			System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임종료");
			return 1;
		}
		return count;
		
	}

	public static void main(String[] args) {

		BaseBall baseBall = new BaseBall();
		int[] com_Answer = baseBall.com_Answer();

		for (;;) {
			int user_Answer = baseBall.user_Answer(com_Answer);
			if (user_Answer != 0) { // 세자리 통과
				int[] user_Answer_arr = baseBall.make_user_Answer_arr(user_Answer);
				if (baseBall.overapCheck(user_Answer_arr) == 0) {// 중복통과
					
					HashMap<String, Integer> map = new HashMap<String, Integer>();
					map = baseBall.test(user_Answer_arr, com_Answer);
					
					if(baseBall.printResult(map)!=0){
						break;
					}
					
				}
			}

			// for문 닫기
		}
		// main 닫기
	}
}
