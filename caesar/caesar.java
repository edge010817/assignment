import java.util.Scanner;

class caesar { 
	public static void main(String[] args){
		caesarProc cp = new caesarProc();
		cp.decipher();
	}
}

class caesarProc{
	private String code = "qdq-gi.q-a ziatmxxitmdqibtqi-ustbi ri.qmoqrcxi.qbubu zir -ibtqi-qp-qaai ripmymsqkir -ibtqi-qy dmxi ri.cnxuoi rruoumxakir -ibtqiqzmobyqzbkii-q.qmxi -imyqzpyqzbi rixmeaki -puzmzoqai -i-qscxmbu zaimzpir -i btq-iymbbq-a;iz -iatmxximzgi.q-a zinqiuzimzgiemgipuao-uyuzmbqpimsmuzabir -ia. za -uzsiacotiimi.qbubu zj";
	private String usableCode = "abcdefghijklmnopqrstuvwxyz .,-";
	private String hintCode = "person";
	private StringBuilder sb;
	private int index;

	public caesarProc(){
		sb = new StringBuilder();
		sb.append(code);
		index = 0;
	}

	public void decipher(){
		for(int c=0;c<sb.length();c++){
			shift();
			if(sb.indexOf(hintCode) != -1){
				System.out.println("解読に成功しました\n" + (c+1) + "回シフトしました\n"  + sb.toString());
				break;
			}	
		}
	}

	public void shift(){
		for(int i=0;i<sb.length();i++){
			for(int j=0;j<usableCode.length();j++){
				if(sb.charAt(i) == usableCode.charAt(j)){
					if(j >= usableCode.length()-1){
						sb.setCharAt(i, usableCode.charAt(0));
					}else{
						sb.setCharAt(i, usableCode.charAt(j+1));
						break;
					}
				}

			}
		}
	}
}
