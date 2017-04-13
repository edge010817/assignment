import java.util.Scanner;
import java.util.Random;

//数字は3つ
//1つの数字は1〜6の範囲を持つ
//数字はそれぞれ別であり、重複しない
//入力した数字が答えの中にあり、場所もあっている場合にはヒットとして数える
//入力した数字が答えの中にあるが、場所はあってない場合ブローとして数える
//3つの数字すべてがヒットになったら、ゲームクリア

public class hitblow {
	private Random ran;

	public hitblow(){
		ran = new Random();
	}

	public static void main(String[] args){
		int[] question = new int[3];
		int[] answer = new int[3];

		Tools tool = new Tools();
	
		do{
			question = tool.randomize(question);
		}while(tool.comparison(question));

		for(int i=0;i<question.length;i++){
			System.out.print(question[i]);
		}
		System.out.println();

		answer = tool.input();

		tool.hitandblow(question, answer);	


	}
}

class Tools {
	private Random ran;
	private Scanner scan;
	private String in;
	private int hit, blow;
	private boolean[] loc;

	public Tools(){
		ran = new Random();
	}

	//Hit&Blowの判定処理
	public void hitandblow(int[] q, int[] a){
		reset(q);

		for(int i=0;i<q.length;i++){
			if(q[i] == a[i]){
				hit += 1;
				loc[i] = true;
				print(String.valueOf(hit));
				continue;
			}
			for(int j=0;j<a.length;j++){
				if(q[i] == a[j] && loc[j] == false){
					print(String.valueOf(blow));
					blow += 1;
					break;
				}
			}
		}
		if(hit == 3){
			print("Clear");
		}else{
			print("hit=" + hit + " blow=" + blow);
		}
	}

	//各種変数、配列の初期化
	public void reset(int[] resettmp){
		hit = 0;
		blow = 0;
		if(loc == null){
			loc = new boolean[resettmp.length];
		}else{
			for(int i=0;i<resettmp.length;i++){
				loc[i] = false;
			} 
		}
	}

	//答えの入力
	public int[] input(){
		do{
			scan = new Scanner(System.in);
			System.out.print("答えを入力してください\n>");
			in = scan.nextLine();
		}while(!digitCheck(in));

		return outputInt(in);
	}

	//String→int型配列に変換
	public int[] outputInt(String str){
		int[] tmp = new int[str.length()];
		for(int i=0;i<tmp.length;i++){
			tmp[i] = Integer.parseInt(String.valueOf(str.charAt(i)));
		}
		return tmp;
	}

	//標準出力
	public void print(String str){
		System.out.println(str);	
	}

	//桁数チェック
	public boolean digitCheck(String str){
		try{
			Integer.parseInt(str);
			if(str.length() != 3){
				print("数値は3桁で入力してください");
				return false;
			}else if(comparison(outputInt(str))){
				print("一桁ずつ同じ数値は使えません");
				return false;
			}
			return true;
		}catch(NumberFormatException e){
			print("数値のみで入力してください");
			return false;
		}
	}

	//乱数生成
	public int[] randomize(int[] rantmp){
		for(int i=0;i<rantmp.length;i++){
			rantmp[i] = ran.nextInt(5)+1;
		}
		return rantmp;
	} 

	//配列の値の重複チェック
	public boolean comparison(int[] comtmp){
		if(comtmp[0] == comtmp[1] || comtmp[0] == comtmp[2] || comtmp[1] == comtmp[2]){
			return true;
		}else{
			return false;
		}
	}
}
