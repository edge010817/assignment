import java.util.Scanner;

class caesar { 
	public static void main(String[] args){
		boolean flg = false;
		caesarProc cp = new caesarProc();
		Scanner scan = new Scanner(System.in);
		int k = scan.nextInt();
		for(int l=0;l<k;l++){
			cp.shift();
		}
		System.out.println(cp.getString());
		/*for(int k=0;k<29;k++){
			cp.shift();
			if(cp.decipher()){
				flg = true;
				break;
			}
		}
		if(flg == true){
			System.out.println("解読に成功しました");
			System.out.println(cp.getString());
		}else{
			System.out.println("解読に失敗しました");
		}*/
	}
}

class caesarProc{
	private String code = "qdq-gi.q-a ziatmxxitmdqibtqi-ustbi ri.qmoqrcxi.qbubu zir -ibtqi-qp-qaai ripmymsqkir -ibtqi-qy dmxi ri.cnxuoi rruoumxakir -ibtqiqzmobyqzbkii-q.qmxi -imyqzpyqzbi rixmeaki -puzmzoqai -i-qscxmbu zaimzpir -i btq-iymbbq-a;iz -iatmxximzgi.q-a zinqiuzimzgiemgipuao-uyuzmbqpimsmuzabir -ia. za -uzsiacotiimi.qbubu zj";
	private String usableCode = "abcdefghijklmnopqrstuvwxyz .,-";
	private String hintCode = "person";
	private StringBuilder sb;

	public caesarProc(){
		sb = new StringBuilder();
		sb.append(code);
	}

	public boolean decipher(){
		for(int i=0;i<code.length();i++){
			for(int j=0;j<hintCode.length();j++){
				if(code.charAt(i) != hintCode.charAt(j)){
					break;
				}else if(code.charAt(i) == hintCode.charAt(j) && j == 5){
					return true;
				}
			}
		}
		return false;
	}

	public void shift(){
		for(int i=0;i<sb.length();i++){
			for(int j=0;j<usableCode.length();j++){
				if(sb.charAt(i) == usableCode.charAt(j)){
					if(j > 28){
						//sb.append(usableCode.charAt(0));
						sb.setCharAt(i, usableCode.charAt(0));
					}else{
						//sb.append(usableCode.charAt(j+1));
						sb.setCharAt(i, usableCode.charAt(j+1));	
					}
					break;
				}else if(sb.charAt(i) == ';'){
					break;
				}
			}
		}
	}

	/*public void delete(){
		//sb.delete(0, 29);
		sb.setLength(0);
	}*/

	public String getString(){
		return sb.toString();
	}
}
