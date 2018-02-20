import java.util.Deque;
import java.util.ArrayDeque;
import java.util.Scanner;

class RPN {
	public static void main(String[] args){
		//最終的に数値スタックに、演算子スタックの値がPushされる
		Deque<String> stack = new ArrayDeque<String>();		//数値スタック
		Deque<String> stack2 = new ArrayDeque<String>();	//演算子スタック

		Scanner scan = new Scanner(System.in);
		String str;
		int num = 0;

		while(true){
			System.out.print(">");
			str = scan.next();		//数値または演算子を入力
			
			//=が入力された時の処理
			if(str.equals("=")){
				while(true){
					try{
						stack.offerFirst(stack2.pollFirst());
					}catch(NullPointerException e){
						break;
					}
				}
				test(stack);
				test(stack2);
				break;
			}

			try{
				num = Integer.parseInt(str);
				//数値をPush
				stack.offerFirst(str);
			//演算子が入力された時
			}catch(NumberFormatException e){
				//スタックが0のとき
				if(stack2.size() == 0){	
					stack2.offerFirst(str);
				//+,-が入力された時
				}else if(str.equals("+") || str.equals("-")){
					//演算子スタックが*,/だった時
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
			test(stack);	//数値スタックを全て表示
			test(stack2);	//演算子スタックを全て表示
		}
	}

	//スタックの中身を全て表示する
	public static void test(Deque<String> deque){
		System.out.print("|");
		String[] tmp = deque.toArray(new String[0]);
		for(int i=tmp.length-1;i>=0;i--){
			System.out.print(tmp[i] + "|");
		}
		System.out.println();
	}	
}