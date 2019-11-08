import java.util.*;
import java.util.stream.*;
//ビンゴのクラス
public class Bingo{
	public static void main(String... args){
		Scanner sc=new Scanner(System.in);
		Random random=new Random();
		BingoMatrix bingo=new BingoMatrix();
		//最初にビンゴカードを表示する
		System.out.println(bingo);
		while(bingo.reachNum()>=0){
			System.out.println("リーチ数："+bingo.reachNum());
			String _1=sc.nextLine();
			int x=random.nextInt(75)+1;
			System.out.println("出た数字："+x);
			if(bingo.call(x)){
				System.out.println("Hit");
			}else{
				System.out.println("Deviate");
			}
			System.out.println(bingo);
		}
		System.out.println("congratulation!!!");
	}
	
	//ビンゴカードを表している
	public final static class BingoMatrix{
		/*
		ビンゴカードのそれぞれの目の数を表す。
		ただし、-1のときはすでに穴が開いているとする。
		*/
		int[][] matrix=new int[5][5];
		final String separator="----------------";
		BingoMatrix(){
			init();
		}
		/*
		ビンゴカードの目を初期化する。
		*/
		private void init(){
			for(int i=0;i<5;i++){
				//[1,15],[16,30]...の中から被らないようにランダムで目を決める
				//そのために[1,15],[16,30]...のリストを用意しシャッフルした後、前から5つ選ぶ。
				List<Integer> list=IntStream.rangeClosed(1+i*15,(i+1)*15).boxed().collect(Collectors.toList());
				Collections.shuffle(list);
				for(int j=0;j<5;j++){
					matrix[j][i]=list.get(j);
				}
			}
			//真ん中は最初から空いていることを忘れずに
			matrix[2][2]=-1;
		}
		/*
		ある数がコール（どう表現するかわからないのでここではコールと呼ぶ）されたとき、
		その値がカードに有るならば穴をあけてtrueを返す。
		ないならばfalseを返す。
		*/
		public boolean call(int x){
			for(int i=0;i<5;i++){
				for(int j=0;j<5;j++){
					if(matrix[i][j]==x){
						matrix[i][j]=-1;
						return true;
					}
				}
			}
			return false;
		}
		/*
		リーチの数を返す
		ただしビンゴが成立しているときは-1を返す。
		if(num==4)...if(num==5)...のところがちょっとくどいのでうまいことしたい；
		*/
		public int reachNum(){
			int result=0;
			for(int i=0;i<5;i++){
				int num=0;
				for(int j=0;j<5;j++){
					if(matrix[i][j]==-1)num++;
				}
				if(num==4) result++;
				if(num==5) return -1;
			}
			for(int j=0;j<5;j++){
				int num=0;
				for(int i=0;i<5;i++){
					if(matrix[i][j]==-1)num++;
				}
				if(num==4)result++;
				if(num==5) return -1;
			}
			int num=0;
			for(int i=0;i<5;i++){
				if(matrix[i][i]==-1)num++;
			}
			if(num==4)result++;
			if(num==5) return -1;
			num=0;
			for(int i=0;i<5;i++){
				if(matrix[i][4-i]==-1)num++;;
			}
			if(num==4)result++;
			if(num==5) return -1;
			return result;
			//naname
		}
		/*
		
		B,I,N,G,Oを全角にしていることに注意
		*/
		@Override
		public String toString(){
			StringBuilder sb=new StringBuilder();
			sb.append(separator+"\n");
			sb.append("|Ｂ|Ｉ|Ｎ|Ｇ|Ｏ|\n");
			sb.append(separator+"\n");
			for(int i=0;i<5;i++){
				sb.append("|"+Arrays.stream(matrix[i]).mapToObj(j->{
					if(j==-1){
						return "■";
					}else{
						return (j<10?" ":"")+j;
					}
					
				}).collect(Collectors.joining("|"))+"|\n");
				sb.append(separator+"\n");
			}
			return sb.toString();
		}
		
	}
}
