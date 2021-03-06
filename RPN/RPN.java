import java.util.Deque;
import java.util.ArrayDeque;
import java.util.Scanner;

class RPN {

		//最終的に数値スタックに、演算子スタックの値がPushされる
		private static Deque<String> stack = new ArrayDeque<String>();		//数値スタック
		private static Deque<String> stack2 = new ArrayDeque<String>();	//演算子スタック
		private static Scanner scan = new Scanner(System.in);
		private static String[] op = {"+", "-", "*", "/"};

	public static void main(String[] args){
		boolean match;
		String in;

		//最初の入力確認
		while(true){
			in = input();

			if(in.equals("0")){
				System.out.println("! 最初に0は入力できません");
			}else if(opMatch(in)){
				System.out.println("! 最初に演算子は入力できません");
			}else if(!numMatch(in)){
				System.out.println("! 最初に数値以外は入力できません");
			}else{
				reversePolish(in);
				break;
			}
		}
		
		//2回目以降の入力
		while(true){
			in = input();

			//=が入力された時の処理
			if(in.equals("=")){
				while(true){
					try{
						stack.offerFirst(stack2.pollFirst());
					}catch(NullPointerException e){
						break;
					}
				}
				System.out.println("stack >" + stack);
				System.out.println("stack2>" + stack2);
				System.out.println();
				break;
			}else if(!numMatch(in) && !opMatch(in)){
				System.out.println("! 数値、演算子以外は入力できません");
			}else{
				reversePolish(in);
			}
		}
		calculation();
	}

	//入力処理
	public static String input(){
		System.out.print(">");
		return scan.next();
	}

	//逆ポーランドでスタックに溜めていく
	public static void reversePolish(String str){
		try{
			int num = Integer.parseInt(str);
			//数値をPush
			stack.offerFirst(str);
		//演算子が入力された時
		}catch(NumberFormatException e){
			//スタックが0のとき
			if(stack2.size() == 0){	
				stack2.offerFirst(str);
				//+,-が入力された時、演算子スタックの先頭の演算子との優先順位確認
				}else if(str.equals("+") || str.equals("-")){
					//演算子スタックが*,/と優先順位が高いものが先に入っていた場合
					if(stack2.peekFirst().equals("*") || stack2.peekFirst().equals("/")){
						//演算子スタックからPopして、数値スタックにPush
						stack.offerFirst(stack2.pollFirst());
						stack2.offerFirst(str);
					//優先順位に問題ない時、演算子スタックにPush
					}else{
						stack2.offerFirst(str);
					}
			//*,-だった時、演算子スタックにPush
			}else{
				stack2.offerFirst(str);
			}
		}
		System.out.println("stack >" + stack);
		System.out.println("stack2>" + stack2);
	}

	//逆ポーランドの計算
	public static void calculation(){
		int num, num2;
		Deque<String> cal = new ArrayDeque<String>();

		while(stack.size() != 0){
			if(numMatch(stack.peekLast())){
				cal.offerFirst(stack.pollLast());
			}else if(opMatch(stack.peekLast())){
				num2 = Integer.parseInt(cal.pollFirst());
				num = Integer.parseInt(cal.pollFirst());
				System.out.println(num + stack.peekLast()  + num2);
				switch(stack.pollLast()){
					case "+":
						cal.offerFirst(String.valueOf(num + num2));
						break;
					case "-":
						cal.offerFirst(String.valueOf(num - num2));
						break;
					case "*":
						cal.offerFirst(String.valueOf(num * num2));
						break;
					case "/":
						cal.offerFirst(String.valueOf(num / num2));
						break;
				}
			}
			System.out.println("stack >" + stack);
			System.out.println("stack2>" + stack2);
			System.out.println("cal   >" + cal);
			System.out.println();
		}

		System.out.println("A>" + cal.pollFirst());
	}

	//数値とマッチしているか確認
	public static boolean numMatch(String str){
		try{
			int num = Integer.parseInt(str);
			return true;
		}catch(NumberFormatException e){
			return false;
		}
	}

	//演算子とマッチしているか確認
	public static boolean opMatch(String str){
		for(String tmp: op){
			if(str.equals(tmp)){
				return true;
			}
		}
		return false;
	}
}
