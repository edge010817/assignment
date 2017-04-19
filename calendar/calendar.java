import java.util.Scanner;
import java.util.Calendar;

//年と月を入力してカレンダーを出力する
class calendar {
	public static void main(String[] args){
		calTools cal = new calTools();
		cal.input();
		cal.outCal();
	}
}

class calTools {
	private int year;
	private int month;
	private int first;
	private int end;
	private int cnt;

	public void input(){
		Scanner scan = new Scanner(System.in);
		while(true){
			print("年を入力してください\n>");
			year = scan.nextInt();
			print("月を入力してください\n>");
			month = scan.nextInt();

			if(month < 13){
				break;
			}else{
				println("月は1〜12の値で入力してください");
			}
		}
	}

	public int eod(){
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.set(year, month-1, 1);
		return cal.getActualMaximum(Calendar.DATE);
	}

	public void outCal(){
		println(year + "年" + month + "月");
		println("日 月 火 水 木 金 土");
		
		first = zeller(year, month);
		end = eod();
		cnt = 1;

		for(int i=0;i<first;i++){
			print("   ");
		}
		for(int i=first;i<7;i++){
			print(cnt +"  ");
			cnt++;
		}
		println("");

		while(cnt <= end){
			for(int i=0;i<7;i++){
				if(cnt >  end){
					break;
				}else if(cnt > 9){
					print(cnt + " ");
				}else{
					print(cnt + "  ");
				}
				cnt++;
			}
			println("");
		}
	} 

	//0 1 2 3 4 5 6
	//土日月火水木金
	//ただし、1月は13月 2月は14月
	//m = 1,2の場合は、y=y-1,m=m+12
	//h = (y + (y/4) - (y/100) + (y/400) + ((13*m+8)/5) + d) % 7
	public int zeller(int y, int m){
		if(month == 1){
			y--;
			//m += 12;
			m = 13;
		}else if(month == 2){
			y--;
			//m += 13;
			m = 14;
		}
		//return (y + y/4 - y/100 + y/400 + (13*m+8)/5 + 1) % 7;
		return (365*y + y/4 - y/100 + y/400 + (306*(m+1))/10 + 1 - 428) % 7;
	}

	//西暦年号が4で割り切れる年をうるう年とする。
	//西暦年号が100で割り切れて400で割り切れない年は平年とする。
	public int leapYear(int y){
		if(y % 4 == 0 && (y % 100 != 0 || y % 400 == 0)){
			return 1;
		}else{
			return 0;
		}
	}

	//改行なし標準出力
	public void print(String str){
		System.out.print(str);
	}

	//改行あり標準出力
	public void println(String str){
		System.out.println(str);
	}
}
